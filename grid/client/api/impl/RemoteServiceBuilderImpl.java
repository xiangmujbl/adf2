/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.client.api.impl;
/*     */ 
/*     */ import com.gemstone.gemfire.cache.client.Pool;
/*     */ import com.gemstone.gemfire.cache.execute.ResultCollector;
/*     */ import com.gemstone.gemfire.internal.ClassPathLoader;
/*     */ import com.jnj.adf.client.api.IBiz;
/*     */ import com.jnj.adf.client.api.IRemoteService;
/*     */ import com.jnj.adf.client.api.IRemoteServiceBuilder;
/*     */ import com.jnj.adf.config.annotations.RemoteMethod;
/*     */ import com.jnj.adf.config.annotations.RemoteMethod.InvokeTypes;
/*     */ import com.jnj.adf.grid.common.ADFException;
/*     */ import com.jnj.adf.grid.manager.GridManager;
/*     */ import com.jnj.adf.grid.support.IGridService;
/*     */ import com.jnj.adf.grid.support.context.ADFRuntime;
/*     */ import com.jnj.adf.grid.utils.LogUtil;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.springframework.data.gemfire.function.execution.ADFFunctionTemplate;
/*     */ import org.springframework.data.gemfire.function.execution.RemoteParamter;
/*     */ import org.springframework.data.gemfire.remoteService.RemoteServiceHelper;
/*     */ import org.springframework.util.ReflectionUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RemoteServiceBuilderImpl
/*     */   implements IRemoteServiceBuilder
/*     */ {
/*     */   private String srvId;
/*     */   private String methodName;
/*     */   private String path;
/*     */   private String gridId;
/*     */   private String[] parameterTypes;
/*     */   private String[] parameterValues;
/*     */   private String resultCollector;
/*     */   private String[] filters;
/*     */   private boolean fromShell;
/*     */   private Set filterSet;
/*     */   private Class[] _paramClasses;
/*     */   private Object[] _paramsValues;
/*     */   private ResultCollector _resultCollector;
/*     */   private String invokeType;
/*     */   private RemoteMethod.InvokeTypes _invokeType;
/*     */   
/*     */   private void processParameters()
/*     */   {
/*  55 */     if (!StringUtils.isEmpty(this.invokeType))
/*     */     {
/*  57 */       this._invokeType = RemoteMethod.InvokeTypes.valueOf(this.invokeType);
/*     */     }
/*     */     
/*  60 */     if (this.parameterTypes != null)
/*     */     {
/*  62 */       if (this.parameterTypes.length != this.parameterValues.length)
/*     */       {
/*  64 */         LogUtil.getCoreLog().error("parameterTypes length {} not match paramterValues length {}", new Object[] { Integer.valueOf(this.parameterTypes.length), Integer.valueOf(this.parameterValues.length) });
/*  65 */         LogUtil.getCoreLog().error("parameterTypes are {}", new Object[] { Arrays.asList(this.parameterTypes) });
/*  66 */         LogUtil.getCoreLog().error("paramterValues are {}", new Object[] { Arrays.asList(this.parameterValues) });
/*  67 */         return;
/*     */       }
/*     */       
/*  70 */       this._paramClasses = new Class[this.parameterTypes.length];
/*  71 */       int i = 0;
/*  72 */       for (String parameterType : this.parameterTypes)
/*     */       {
/*  74 */         if (StringUtils.isEmpty(parameterType))
/*     */         {
/*  76 */           LogUtil.getCoreLog().error("parameterType is null");
/*  77 */           return;
/*     */         }
/*  79 */         Class type = RemoteServiceHelper.convertToType(parameterType.trim());
/*  80 */         if (type == null)
/*     */         {
/*  82 */           return;
/*     */         }
/*  84 */         this._paramClasses[(i++)] = type;
/*     */       }
/*     */       
/*  87 */       this._paramsValues = new Object[this.parameterValues.length];
/*  88 */       i = 0;
/*  89 */       for (String paramterValue : this.parameterValues)
/*     */       {
/*  91 */         if (StringUtils.isEmpty(paramterValue))
/*     */         {
/*  93 */           LogUtil.getCoreLog().error("paramterValue is null");
/*  94 */           paramterValue = "";
/*     */         }
/*  96 */         Object value = RemoteServiceHelper.convertToObject(paramterValue.trim(), this._paramClasses[i]);
/*  97 */         if (value == null)
/*     */         {
/*  99 */           return;
/*     */         }
/* 101 */         this._paramsValues[i] = value;
/* 102 */         i++; } }
/*     */     Object obj;
/*     */     Method mhd;
/*     */     RemoteMethod ann;
/* 106 */     if (this._invokeType == null)
/*     */     {
/* 108 */       obj = RemoteServiceHelper.lookupBean(this.srvId);
/* 109 */       if (obj != null)
/*     */       {
/* 111 */         if ((obj instanceof IRemoteService))
/* 112 */           obj = ((IRemoteService)obj).getOriginBean();
/* 113 */         mhd = ReflectionUtils.findMethod(obj.getClass(), this.methodName, this._paramClasses);
/* 114 */         ann = (RemoteMethod)mhd.getAnnotation(RemoteMethod.class);
/* 115 */         if (ann != null) {
/* 116 */           this._invokeType = ann.type();
/*     */         }
/*     */       }
/*     */     }
/* 120 */     if (this.filters != null)
/*     */     {
/* 122 */       this.filterSet = new HashSet();
/* 123 */       obj = this.filters;RemoteMethod localRemoteMethod1 = obj.length; for (RemoteMethod localRemoteMethod2 = 0; localRemoteMethod2 < localRemoteMethod1; localRemoteMethod2++) { String filter = obj[localRemoteMethod2];
/*     */         
/* 125 */         this.filterSet.add(filter);
/*     */       }
/*     */     }
/* 128 */     if (!StringUtils.isEmpty(this.resultCollector))
/*     */     {
/*     */       try
/*     */       {
/* 132 */         this._resultCollector = ((ResultCollector)ClassPathLoader.getLatest().forName(this.resultCollector).newInstance());
/*     */       }
/*     */       catch (InstantiationException|IllegalAccessException|ClassNotFoundException e)
/*     */       {
/* 136 */         throw new ADFException("RemoteService:" + this.srvId + " method:" + this.methodName + " resultCollector:" + this.resultCollector + " can't instantiation.", e);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private <T> T executeOnServerSide()
/*     */   {
/* 145 */     if (this._invokeType == RemoteMethod.InvokeTypes.ON_SERVER)
/*     */     {
/* 147 */       RemoteParamter param = new RemoteParamter(this.path, this.srvId, this.methodName, this._paramsValues, this._paramClasses, -1);
/* 148 */       return (T)RemoteServiceHelper.invokeMethodLocally(param, this.filterSet, null);
/*     */     }
/*     */     
/*     */ 
/* 152 */     if (this._invokeType == RemoteMethod.InvokeTypes.ON_SERVERS)
/*     */     {
/* 154 */       ADFFunctionTemplate template = new ADFFunctionTemplate();
/* 155 */       template.onMembers(new String[0]).setResultCollector(this._resultCollector);
/* 156 */       return (T)template.doRemoteCall(this.path, this.srvId, this.methodName, this._paramClasses, this._paramsValues);
/*     */     }
/* 158 */     if (this._invokeType == RemoteMethod.InvokeTypes.ON_REGION)
/*     */     {
/* 160 */       ADFFunctionTemplate template = new ADFFunctionTemplate();
/* 161 */       template.onRegion(this.path, this.filterSet).setResultCollector(this._resultCollector);
/* 162 */       return (T)template.doRemoteCall(this.path, this.srvId, this.methodName, this._paramClasses, this._paramsValues);
/*     */     }
/* 164 */     throw new ADFException("Service:" + this.srvId + " method:" + this.methodName + ", no matched invocation type.");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public <T> T execute()
/*     */   {
/* 171 */     if (ADFRuntime.getRuntime().isClientSide())
/*     */     {
/* 173 */       IGridService srv = (IGridService)IBiz.lookup(IGridService.class);
/* 174 */       if (StringUtils.isNotEmpty(this.path))
/* 175 */         return (T)srv.invokeProxy(this.srvId, this.methodName, this.path, this.parameterTypes, this.parameterValues, this.resultCollector, this.filters, this.invokeType, this.fromShell);
/* 176 */       if (StringUtils.isNotEmpty(this.gridId))
/*     */       {
/* 178 */         LogUtil.getCoreLog().warn("path is null, try to find grid id: {} and retrieve the corresponding pool", new Object[] { this.gridId });
/* 179 */         Pool pool = GridManager.getInstance().getGemFirePool(this.gridId);
/* 180 */         return (T)srv.invokeProxy(this.srvId, this.methodName, pool, this.parameterTypes, this.parameterValues, this.resultCollector, this.filters, this.invokeType, this.fromShell);
/*     */       }
/*     */       
/* 183 */       throw new ADFException("No path/No pool specified for service:" + this.srvId + " method:" + this.methodName);
/*     */     }
/*     */     
/* 186 */     if (isFromShell())
/*     */     {
/* 188 */       IGridService srv = (IGridService)IBiz.lookup(IGridService.class);
/* 189 */       return (T)srv.invokeProxy(this.srvId, this.methodName, this.path, this.parameterTypes, this.parameterValues, this.resultCollector, this.filters, this.invokeType, this.fromShell);
/*     */     }
/*     */     
/*     */ 
/* 193 */     processParameters();
/* 194 */     return (T)executeOnServerSide();
/*     */   }
/*     */   
/*     */ 
/*     */   public String getSrvId()
/*     */   {
/* 200 */     return this.srvId;
/*     */   }
/*     */   
/*     */   public IRemoteServiceBuilder setSrvId(String srvId)
/*     */   {
/* 205 */     this.srvId = srvId;
/* 206 */     return this;
/*     */   }
/*     */   
/*     */   public String getMethod()
/*     */   {
/* 211 */     return this.methodName;
/*     */   }
/*     */   
/*     */   public IRemoteServiceBuilder setMethod(String method)
/*     */   {
/* 216 */     this.methodName = method;
/* 217 */     return this;
/*     */   }
/*     */   
/*     */   public String getPath()
/*     */   {
/* 222 */     return this.path;
/*     */   }
/*     */   
/*     */   public IRemoteServiceBuilder setPath(String path)
/*     */   {
/* 227 */     this.path = path;
/* 228 */     return this;
/*     */   }
/*     */   
/*     */   public String getGridId()
/*     */   {
/* 233 */     return this.gridId;
/*     */   }
/*     */   
/*     */   public IRemoteServiceBuilder setGridId(String gridId)
/*     */   {
/* 238 */     this.gridId = gridId;
/* 239 */     return this;
/*     */   }
/*     */   
/*     */   public String[] getParameterTypes()
/*     */   {
/* 244 */     return this.parameterTypes;
/*     */   }
/*     */   
/*     */   public IRemoteServiceBuilder setParameterTypes(String... parameterTypes)
/*     */   {
/* 249 */     this.parameterTypes = parameterTypes;
/* 250 */     return this;
/*     */   }
/*     */   
/*     */   public String[] getParameterValues()
/*     */   {
/* 255 */     return this.parameterValues;
/*     */   }
/*     */   
/*     */   public IRemoteServiceBuilder setParameterValues(String... parameterValues)
/*     */   {
/* 260 */     this.parameterValues = parameterValues;
/* 261 */     return this;
/*     */   }
/*     */   
/*     */   public String getResultCollector()
/*     */   {
/* 266 */     return this.resultCollector;
/*     */   }
/*     */   
/*     */   public IRemoteServiceBuilder setResultCollector(String resultCollector)
/*     */   {
/* 271 */     this.resultCollector = resultCollector;
/* 272 */     return this;
/*     */   }
/*     */   
/*     */   public String[] getFilters()
/*     */   {
/* 277 */     return this.filters;
/*     */   }
/*     */   
/*     */   public IRemoteServiceBuilder setFilters(String... filters)
/*     */   {
/* 282 */     this.filters = filters;
/* 283 */     return this;
/*     */   }
/*     */   
/*     */   public boolean isFromShell()
/*     */   {
/* 288 */     return this.fromShell;
/*     */   }
/*     */   
/*     */   public IRemoteServiceBuilder setFromShell(boolean fromShell)
/*     */   {
/* 293 */     this.fromShell = fromShell;
/* 294 */     return this;
/*     */   }
/*     */   
/*     */   public String getInvokeType()
/*     */   {
/* 299 */     return this.invokeType;
/*     */   }
/*     */   
/*     */   public IRemoteServiceBuilder setInvokeType(String invokeType)
/*     */   {
/* 304 */     this.invokeType = invokeType;
/*     */     
/* 306 */     return this;
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\client\api\impl\RemoteServiceBuilderImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */