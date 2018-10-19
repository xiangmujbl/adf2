/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.client.api.impl;
/*     */ 
/*     */ import com.gemstone.gemfire.internal.cache.GemFireCacheImpl;
/*     */ import com.gemstone.gemfire.pdx.PdxSerializationException;
/*     */ import com.gemstone.gemfire.pdx.internal.TypeRegistry;
/*     */ import com.jnj.adf.client.api.ADFOperations;
/*     */ import com.jnj.adf.client.api.GridBasicOperations;
/*     */ import com.jnj.adf.client.api.GridLocationOperations;
/*     */ import com.jnj.adf.client.api.GridQueryOperations;
/*     */ import com.jnj.adf.client.api.GridTemporalOperations;
/*     */ import com.jnj.adf.client.api.QueueOperations;
/*     */ import com.jnj.adf.grid.client.api.support.ADFServiceContext;
/*     */ import com.jnj.adf.grid.client.api.support.ADFServiceContext.Keys;
/*     */ import com.jnj.adf.grid.client.api.support.ADFSessionHelper;
/*     */ import com.jnj.adf.grid.client.api.support.ProxyHelper;
/*     */ import com.jnj.adf.grid.common.AdfExceptionConverter;
/*     */ import com.jnj.adf.grid.utils.LogUtil;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import javax.annotation.Resource;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.springframework.beans.factory.FactoryBean;
/*     */ import org.springframework.cglib.proxy.CallbackFilter;
/*     */ import org.springframework.cglib.proxy.Enhancer;
/*     */ import org.springframework.cglib.proxy.MethodInterceptor;
/*     */ import org.springframework.cglib.proxy.MethodProxy;
/*     */ import org.springframework.stereotype.Component;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Component
/*     */ public class ADFOperationsProxy
/*     */   implements FactoryBean<ADFOperations>, MethodInterceptor
/*     */ {
/*     */   @Resource(name="_adf_basic")
/*     */   private GridBasicOperations basicOp;
/*     */   @Resource(name="_adf_query_lucene")
/*     */   private GridQueryOperations queryOp;
/*     */   @Resource
/*     */   private GridTemporalOperations temporalOp;
/*     */   @Resource
/*     */   private GridLocationOperations locationOp;
/*     */   @Resource
/*     */   private QueueOperations queueService;
/*     */   
/*     */   public ADFOperations getObject()
/*     */     throws Exception
/*     */   {
/*  75 */     Enhancer enhance = new Enhancer();
/*  76 */     enhance.setSuperclass(ADFOperationsImpl.class);
/*  77 */     enhance.setCallback(this);
/*  78 */     enhance.setCallbackFilter(new CallbackFilter()
/*     */     {
/*     */ 
/*     */       public int accept(Method method)
/*     */       {
/*     */ 
/*  84 */         return 0;
/*     */       }
/*  86 */     });
/*  87 */     ADFOperationsImpl instance = (ADFOperationsImpl)enhance.create();
/*  88 */     return instance;
/*     */   }
/*     */   
/*     */ 
/*     */   public Class<?> getObjectType()
/*     */   {
/*  94 */     return ADFOperations.class;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isSingleton()
/*     */   {
/* 100 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
/*     */     throws Throwable
/*     */   {
/* 110 */     LogUtil.getCoreLog().trace("{}.{} invoked", new Object[] { getObjectType().getName(), method.getName() });
/* 111 */     boolean isTemporal = ADFServiceContext.getValue(ADFServiceContext.Keys.TEMPORAL) != null;
/* 112 */     boolean isLocation = ADFServiceContext.getValue(ADFServiceContext.Keys.LOCATION) != null;
/*     */     
/* 114 */     if (ProxyHelper.isToString(method))
/*     */     {
/* 116 */       return getClass().getName();
/*     */     }
/*     */     
/* 119 */     boolean withSession = ADFSessionHelper.withSession(method);
/* 120 */     if (withSession)
/* 121 */       ADFServiceContext.before();
/* 122 */     Object result = null;
/*     */     try
/*     */     {
/* 125 */       int i = 0;
/*     */       for (;;)
/*     */       {
/* 128 */         i++;
/*     */         try
/*     */         {
/* 131 */           if (isTemporal) if (ProxyHelper.definedBy(method, new Class[] { this.temporalOp.getClass().getSuperclass() }))
/*     */             {
/* 133 */               result = method.invoke(this.temporalOp, args);
/*     */               break label256; }
/* 135 */           if (isLocation) if (ProxyHelper.definedBy(method, new Class[] { this.locationOp.getClass().getSuperclass() }))
/*     */             {
/* 137 */               result = method.invoke(this.locationOp, args);
/*     */               break label256; }
/* 139 */           if (ProxyHelper.definedBy(method, new Class[] { GridBasicOperations.class }))
/*     */           {
/* 141 */             result = method.invoke(this.basicOp, args);
/*     */           }
/* 143 */           else if (ProxyHelper.definedBy(method, new Class[] { GridQueryOperations.class }))
/*     */           {
/* 145 */             result = method.invoke(this.queryOp, args);
/*     */           }
/*     */           else {
/* 148 */             result = proxy.invokeSuper(obj, args);
/*     */           }
/*     */         }
/*     */         catch (InvocationTargetException e) {
/*     */           label256:
/* 153 */           if (i < 3)
/*     */           {
/*     */ 
/*     */ 
/*     */ 
/* 158 */             Exception adfEx = AdfExceptionConverter.convertToAdfException(e);
/* 159 */             if ((e.getCause() != null) && (e.getCause().getCause() != null) && 
/* 160 */               ((e.getCause().getCause() instanceof PdxSerializationException))) {
/* 161 */               LogUtil.getCoreLog().warn("PdxSerialization error," + adfEx.getMessage());
/* 162 */               GemFireCacheImpl.getInstance().getPdxRegistry().clear();
/* 163 */               LogUtil.getCoreLog().warn("cleared client pdxType! ");
/* 164 */               continue;
/*     */             }
/* 166 */             if (((adfEx.getCause() instanceof IllegalStateException)) && (adfEx.getCause().getMessage().startsWith("Unknown pdx type=")))
/*     */             {
/* 168 */               LogUtil.getCoreLog().warn("PdxSerialization error," + adfEx.getMessage());
/* 169 */               GemFireCacheImpl.getInstance().getPdxRegistry().clear();
/* 170 */               LogUtil.getCoreLog().warn("cleared client pdxType! ");
/* 171 */               continue;
/*     */             }
/*     */             
/* 174 */             throw adfEx;
/*     */           }
/*     */           
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 182 */       AdfExceptionConverter.logError(e, method, args);
/* 183 */       throw AdfExceptionConverter.convertToAdfException(e);
/*     */     }
/*     */     finally
/*     */     {
/* 187 */       if (withSession) {
/* 188 */         ADFServiceContext.after();
/*     */       }
/*     */     }
/* 191 */     return result;
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\client\api\impl\ADFOperationsProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */