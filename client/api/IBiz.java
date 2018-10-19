/*     */ package com.jnj.adf.dataservice.adfcoreignite.client.api;
/*     */ 
/*     */ import com.jnj.adf.grid.client.api.impl.RemoteResultsImpl;
/*     */ import com.jnj.adf.grid.client.api.impl.RemoteServiceBuilderImpl;
/*     */ import com.jnj.adf.grid.common.ADFException;
/*     */ import com.jnj.adf.grid.manager.RemoteServiceManager;
/*     */ import com.jnj.adf.grid.support.context.ADFContext;
/*     */ import com.jnj.adf.grid.support.context.ADFRuntime;
/*     */ import com.jnj.adf.grid.utils.LogUtil;
/*     */ import org.apache.logging.log4j.Logger;
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
/*     */ public final class IBiz
/*     */ {
/*     */   public static final <T> IRemoteResults<T> toRemoteResults(T t)
/*     */   {
/*  36 */     RemoteResultsImpl<T> impl = new RemoteResultsImpl();
/*  37 */     impl.addResult(t);
/*  38 */     return impl;
/*     */   }
/*     */   
/*     */   public static final <T> T lookup(Class<T> cls)
/*     */   {
/*  43 */     T inst = ADFContext.getBean(cls);
/*  44 */     if ((inst instanceof IRemoteService)) {
/*  45 */       return inst;
/*     */     }
/*  47 */     return remote(cls);
/*     */   }
/*     */   
/*     */   public static final <T> T lookup(String srvId)
/*     */   {
/*  52 */     if (ADFRuntime.getRuntime().isClientSide())
/*     */     {
/*  54 */       T inst = ADFContext.getBean(srvId);
/*  55 */       return inst;
/*     */     }
/*     */     
/*     */ 
/*  59 */     return RemoteServiceManager.getInstance().getRemoteService(srvId);
/*     */   }
/*     */   
/*     */ 
/*     */   public static final <T> IRemoteService<T> remote(T bean)
/*     */   {
/*  65 */     IRemoteService<T> rs = RemoteServiceManager.getInstance().getRemoteService(bean);
/*  66 */     return rs;
/*     */   }
/*     */   
/*     */   public static final <T> IRemoteService<T> remote(Class<T> beanClass)
/*     */   {
/*  71 */     T bean = ADFContext.getBean(beanClass);
/*  72 */     if (bean == null)
/*     */     {
/*  74 */       if (beanClass.isInterface()) {
/*  75 */         throw new ADFException("No interface " + beanClass.getSimpleName() + " found!");
/*     */       }
/*     */       try
/*     */       {
/*  79 */         bean = beanClass.newInstance();
/*     */       }
/*     */       catch (InstantiationException|IllegalAccessException e)
/*     */       {
/*  83 */         LogUtil.getCoreLog().error(e);
/*  84 */         return null;
/*     */       }
/*     */     }
/*     */     
/*  88 */     IRemoteService<T> rs = RemoteServiceManager.getInstance().getRemoteService(bean);
/*     */     
/*  90 */     return rs;
/*     */   }
/*     */   
/*     */   public static final IRemoteServiceBuilder remoteServiceBuilder()
/*     */   {
/*  95 */     RemoteServiceBuilderImpl impl = new RemoteServiceBuilderImpl();
/*  96 */     return impl;
/*     */   }
/*     */   
/*     */ 
/*     */   public static final <T> T executeRemoteService(String srvId, String method, String path, String gridId, String[] parameterTypes, String[] parameterValues, String resultCollector, String[] filters)
/*     */   {
/* 102 */     return (T)executeRemoteService(srvId, method, path, gridId, parameterTypes, parameterValues, resultCollector, filters, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static final <T> T executeRemoteService(String srvId, String method, String path, String gridId, String[] parameterTypes, String[] parameterValues, String resultCollector, String[] filters, String invokeType)
/*     */   {
/* 109 */     return (T)executeRemoteService(srvId, method, path, gridId, parameterTypes, parameterValues, resultCollector, filters, null, false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static final <T> T executeRemoteService(String srvId, String method, String path, String gridId, String[] parameterTypes, String[] parameterValues, String resultCollector, String[] filters, String invokeType, boolean fromShell)
/*     */   {
/* 119 */     IRemoteServiceBuilder builder = remoteServiceBuilder().setSrvId(srvId).setMethod(method).setParameterTypes(parameterTypes).setPath(path).setGridId(gridId).setParameterValues(parameterValues).setResultCollector(resultCollector).setFilters(filters).setInvokeType(invokeType).setFromShell(fromShell);
/* 120 */     return (T)builder.execute();
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\client\api\IBiz.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */