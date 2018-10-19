/*     */ package com.jnj.adf.dataservice.adfcoreignite.config;
/*     */ 
/*     */ import com.gemstone.gemfire.cache.execute.ResultCollector;
/*     */ import com.jnj.adf.client.api.IRemoteService;
/*     */ import com.jnj.adf.config.annotations.RemoteMethod;
/*     */ import com.jnj.adf.config.annotations.RemoteMethod.InvokeTypes;
/*     */ import com.jnj.adf.grid.namespace.ExecutionContextHelper;
/*     */ import com.jnj.adf.grid.namespace.ExecutionContextImpl;
/*     */ import com.jnj.adf.grid.support.system.ADFConfigHelper;
/*     */ import com.jnj.adf.grid.support.system.ADFConfigHelper.ITEMS;
/*     */ import com.jnj.adf.grid.utils.LogUtil;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.springframework.cglib.proxy.Enhancer;
/*     */ import org.springframework.cglib.proxy.MethodInterceptor;
/*     */ import org.springframework.cglib.proxy.MethodProxy;
/*     */ import org.springframework.core.annotation.AnnotationUtils;
/*     */ import org.springframework.data.gemfire.function.execution.ADFFunctionTemplate;
/*     */ import org.springframework.util.ReflectionUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CglibRemoteServiceProxy<T>
/*     */   implements MethodInterceptor
/*     */ {
/*     */   private T originBean;
/*     */   private Map<Method, RemoteMethod> invokeTypeMap;
/*     */   private Set<Method> methodSet;
/*     */   private IRemoteService<T> proxyInst;
/*     */   private String serviceId;
/*  56 */   private ThreadLocal<ADFFunctionTemplate> tl_template = new ThreadLocal();
/*     */   
/*     */   private boolean clientApi;
/*     */   
/*     */   public static final boolean CGLIB_USECACHE = true;
/*     */   static final String _getOriginBean = "getOriginBean";
/*     */   static final String _iterableResults = "iterableResults";
/*     */   static final String _getResultColector = "getResultColector";
/*     */   
/*     */   public CglibRemoteServiceProxy()
/*     */   {
/*  67 */     this.invokeTypeMap = new HashMap();
/*  68 */     this.methodSet = new HashSet();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private ADFFunctionTemplate getTemplate()
/*     */   {
/*  75 */     ADFFunctionTemplate t = (ADFFunctionTemplate)this.tl_template.get();
/*  76 */     if (t == null)
/*     */     {
/*  78 */       t = new ADFFunctionTemplate();
/*  79 */       this.tl_template.set(t);
/*     */     }
/*  81 */     if (this.clientApi)
/*     */     {
/*  83 */       t.setClientApiUse(this.clientApi);
/*     */     }
/*  85 */     return t;
/*     */   }
/*     */   
/*     */   public IRemoteService<T> bindClientApi(T originBean, String serviceId)
/*     */   {
/*  90 */     return bind(true, originBean, serviceId);
/*     */   }
/*     */   
/*     */   public IRemoteService<T> bind(T originBean, String serviceId)
/*     */   {
/*  95 */     return bind(false, originBean, serviceId);
/*     */   }
/*     */   
/*     */   protected IRemoteService<T> bind(boolean isClientApi, T originBean, String serviceId)
/*     */   {
/* 100 */     this.clientApi = isClientApi;
/* 101 */     this.originBean = originBean;
/* 102 */     this.serviceId = serviceId;
/* 103 */     Class originClass = null;
/* 104 */     Enhancer enhance = new Enhancer();
/* 105 */     enhance.setUseCache(true);
/* 106 */     if (isClientApi)
/*     */     {
/* 108 */       originClass = (Class)originBean;
/* 109 */       enhance.setInterfaces(new Class[] { originClass, IRemoteService.class });
/*     */     }
/*     */     else
/*     */     {
/* 113 */       originClass = originBean.getClass();
/* 114 */       enhance.setSuperclass(originClass);
/* 115 */       enhance.setInterfaces(new Class[] { IRemoteService.class });
/*     */     }
/* 117 */     enhance.setClassLoader(originClass.getClassLoader());
/* 118 */     enhance.setCallback(this);
/* 119 */     Method[] methods = ReflectionUtils.getAllDeclaredMethods(originClass);
/*     */     
/* 121 */     for (Method method : methods)
/*     */     {
/* 123 */       RemoteMethod ann = (RemoteMethod)AnnotationUtils.getAnnotation(method, RemoteMethod.class);
/* 124 */       if (ann != null)
/*     */       {
/* 126 */         this.invokeTypeMap.put(method, ann);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 131 */       this.methodSet.add(method);
/*     */     }
/*     */     try
/*     */     {
/* 135 */       this.proxyInst = ((IRemoteService)enhance.create());
/* 136 */       LogUtil.getCoreLog().trace("Implementation IRemoteService for class:{}, proxy is:{}", new Object[] {originBean
/* 137 */         .getClass().getName(), this.proxyInst.getClass() });
/*     */     }
/*     */     catch (IllegalArgumentException e)
/*     */     {
/* 141 */       LogUtil.getCoreLog().error("Class:{}", new Object[] { originBean.getClass().getName(), e });
/*     */     }
/*     */     
/* 144 */     return this.proxyInst;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 152 */   private ThreadLocal<ResultCollector> tl_rc = new ThreadLocal();
/* 153 */   private ThreadLocal<Iterable> tl_ite = new ThreadLocal();
/*     */   
/*     */   public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
/*     */     throws Throwable
/*     */   {
/* 158 */     Object result = null;
/*     */     try
/*     */     {
/* 161 */       LogUtil.getCoreLog().trace("{}.{},template:{}", new Object[] { this.originBean.getClass().getSimpleName(), method.getName(), this.tl_template
/* 162 */         .get() });
/* 163 */       if (method.getDeclaringClass().equals(IRemoteService.class))
/*     */       {
/* 165 */         if ("getOriginBean".equals(method.getName()))
/* 166 */           return this.originBean;
/* 167 */         if ("iterableResults".equals(method.getName()))
/* 168 */           return this.tl_ite.get();
/* 169 */         if ("getResultColector".equals(method.getName())) {
/* 170 */           return this.tl_rc.get();
/*     */         }
/* 172 */         Object restulst = method.invoke(getTemplate(), args);
/* 173 */         if ((restulst instanceof IRemoteService))
/* 174 */           return this.proxyInst;
/* 175 */         return restulst;
/*     */       }
/*     */       
/* 178 */       RemoteMethod ann = (RemoteMethod)this.invokeTypeMap.get(method);
/* 179 */       if (ann != null)
/*     */       {
/* 181 */         String versionNo = ADFConfigHelper.getConfig(ITEMS.ADF_VERSION_NO);
/* 182 */         ExecutionContextImpl ecc = (ExecutionContextImpl)ExecutionContextHelper.currentExecutionContext();
/* 183 */         ecc.setVersion(versionNo);
/* 184 */         result = getTemplate().remoteCall(this.serviceId, method, args);
/* 185 */         LogUtil.getCoreLog().trace("RemoteService {}.{} invoked {}.", new Object[] { obj.getClass().getSimpleName(), method.getName(), ann
/* 186 */           .type().name() });
/*     */       }
/*     */       else
/*     */       {
/* 190 */         LogUtil.getCoreLog().trace("RemoteService {}.{} invoked locally.", new Object[] { obj.getClass().getSimpleName(), method
/* 191 */           .getName() });
/* 192 */         result = method.invoke(this.originBean, args);
/*     */       }
/* 194 */       if ((result instanceof Iterable))
/* 195 */         this.tl_ite.set((Iterable)result);
/* 196 */       ResultCollector rc = getTemplate().getResultColector();
/* 197 */       if (rc == null) {
/* 198 */         this.tl_rc.remove();
/*     */       } else
/* 200 */         this.tl_rc.set(rc);
/* 201 */       this.tl_template.remove();
/* 202 */       return result;
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 206 */       this.tl_template.remove();
/* 207 */       throw e;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public IRemoteService<T> getProxyInst()
/*     */   {
/* 214 */     return this.proxyInst;
/*     */   }
/*     */   
/*     */ 
/*     */   public void close()
/*     */   {
/* 220 */     this.originBean = null;
/* 221 */     this.invokeTypeMap.clear();
/* 222 */     this.invokeTypeMap = null;
/* 223 */     this.methodSet.clear();
/* 224 */     this.methodSet = null;
/* 225 */     this.proxyInst = null;
/* 226 */     this.tl_ite.remove();
/* 227 */     this.tl_rc.remove();
/* 228 */     this.tl_template.remove();
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\config\CglibRemoteServiceProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */