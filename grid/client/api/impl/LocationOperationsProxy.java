/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.client.api.impl;
/*     */ 
/*     */ import com.jnj.adf.client.api.GridBasicOperations;
/*     */ import com.jnj.adf.client.api.GridLocationOperations;
/*     */ import com.jnj.adf.grid.client.api.support.ADFServiceContext;
/*     */ import com.jnj.adf.grid.client.api.support.ADFServiceContext.Keys;
/*     */ import com.jnj.adf.grid.client.api.support.ADFSessionHelper;
/*     */ import com.jnj.adf.grid.client.api.support.ProxyHelper;
/*     */ import com.jnj.adf.grid.common.AdfExceptionConverter;
/*     */ import com.jnj.adf.grid.perf.PerfStatManager;
/*     */ import com.jnj.adf.grid.utils.LogUtil;
/*     */ import java.lang.reflect.Method;
/*     */ import javax.annotation.Resource;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.springframework.beans.factory.FactoryBean;
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
/*     */ @Component
/*     */ public class LocationOperationsProxy
/*     */   implements FactoryBean<GridLocationOperations>, MethodInterceptor
/*     */ {
/*     */   @Resource(name="_adf_basic")
/*     */   private GridBasicOperations basicOp;
/*     */   
/*     */   public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
/*     */     throws Throwable
/*     */   {
/*  58 */     long l0 = System.currentTimeMillis();
/*  59 */     boolean withSession = ADFSessionHelper.withSession(method);
/*  60 */     LogUtil.getCoreLog().debug("{}.{} invoked,withSession:{}", new Object[] { getObjectType().getName(), method.getName(), 
/*  61 */       Boolean.valueOf(withSession) });
/*  62 */     if (withSession)
/*  63 */       ADFServiceContext.before();
/*     */     try {
/*     */       Object localObject1;
/*  66 */       if (ProxyHelper.isToString(method))
/*     */       {
/*  68 */         return getClass().getName();
/*     */       }
/*  70 */       if (ProxyHelper.definedBy(method, new Class[] { GridLocationOperations.class }))
/*     */       {
/*  72 */         return proxy.invokeSuper(obj, args);
/*     */       }
/*  74 */       if (ProxyHelper.definedBy(method, new Class[] { GridBasicOperations.class }))
/*     */       {
/*  76 */         return method.invoke(this.basicOp, args);
/*     */       }
/*     */       
/*  79 */       boolean isLocation = ADFServiceContext.getValue(ADFServiceContext.Keys.LOCATION) != null;
/*  80 */       Object localObject2; if (ProxyHelper.isToString(method))
/*     */       {
/*  82 */         return getClass().getName();
/*     */       }
/*  84 */       if (isLocation) if (ProxyHelper.definedBy(method, new Class[] { LocationImplLucene.class }))
/*     */         {
/*  86 */           return proxy.invokeSuper(obj, args);
/*     */         }
/*  88 */       if (ProxyHelper.definedBy(method, new Class[] { GridBasicOperations.class }))
/*     */       {
/*  90 */         return method.invoke(this.basicOp, args);
/*     */       }
/*  92 */       return proxy.invokeSuper(obj, args);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*  96 */       AdfExceptionConverter.logError(e, method, args);
/*  97 */       throw AdfExceptionConverter.convertToAdfException(e);
/*     */     }
/*     */     finally
/*     */     {
/* 101 */       PerfStatManager.record(obj.getClass().getSimpleName(), method.getName(), l0);
/* 102 */       if (withSession) {
/* 103 */         ADFServiceContext.after();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public GridLocationOperations getObject()
/*     */     throws Exception
/*     */   {
/* 111 */     Enhancer enhance = new Enhancer();
/* 112 */     enhance.setSuperclass(LocationImplLucene.class);
/* 113 */     enhance.setCallback(this);
/* 114 */     GridLocationOperations instance = (GridLocationOperations)enhance.create();
/* 115 */     return instance;
/*     */   }
/*     */   
/*     */ 
/*     */   public Class<?> getObjectType()
/*     */   {
/* 121 */     return GridLocationOperations.class;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isSingleton()
/*     */   {
/* 127 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\client\api\impl\LocationOperationsProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */