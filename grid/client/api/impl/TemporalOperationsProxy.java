/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.client.api.impl;
/*     */ 
/*     */ import com.jnj.adf.client.api.GridBasicOperations;
/*     */ import com.jnj.adf.client.api.GridLocationOperations;
/*     */ import com.jnj.adf.client.api.GridQueryOperations;
/*     */ import com.jnj.adf.client.api.GridTemporalBasicOperations;
/*     */ import com.jnj.adf.client.api.GridTemporalOperations;
/*     */ import com.jnj.adf.client.api.GridTemporalQueryOperations;
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
/*     */ @Component
/*     */ public class TemporalOperationsProxy
/*     */   implements FactoryBean<GridTemporalOperations>, MethodInterceptor
/*     */ {
/*     */   @Resource(name="_adf_query_temporal_lucene")
/*     */   private GridQueryOperations queryOp;
/*     */   @Resource(name="_adf_temporal_basic")
/*     */   private GridBasicOperations basicOp;
/*     */   @Resource
/*     */   private GridLocationOperations locationOp;
/*     */   
/*     */   public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
/*     */     throws Throwable
/*     */   {
/*  61 */     LogUtil.getCoreLog().debug("{}.{} invoked", new Object[] { getObjectType().getName(), method.getName() });
/*  62 */     boolean withSession = ADFSessionHelper.withSession(method);
/*  63 */     if (withSession)
/*  64 */       ADFServiceContext.before();
/*  65 */     long l0 = System.currentTimeMillis();
/*     */     try
/*     */     {
/*     */       Object localObject1;
/*  69 */       if (ProxyHelper.isToString(method))
/*     */       {
/*  71 */         return getClass().getName();
/*     */       }
/*  73 */       if (ProxyHelper.definedBy(method, new Class[] { GridTemporalBasicOperations.class, GridTemporalQueryOperations.class, GridTemporalOperations.class }))
/*     */       {
/*     */ 
/*  76 */         return proxy.invokeSuper(obj, args);
/*     */       }
/*  78 */       if (ProxyHelper.definedBy(method, new Class[] { GridLocationOperations.class }))
/*     */       {
/*  80 */         return method.invoke(this.locationOp, args);
/*     */       }
/*  82 */       if (ProxyHelper.definedBy(method, new Class[] { GridQueryOperations.class }))
/*     */       {
/*  84 */         return method.invoke(this.queryOp, args);
/*     */       }
/*     */       
/*  87 */       boolean isTemporal = ADFServiceContext.getValue(ADFServiceContext.Keys.TEMPORAL) != null;
/*  88 */       boolean isLocation = ADFServiceContext.getValue(ADFServiceContext.Keys.LOCATION) != null;
/*  89 */       Object localObject2; if (ProxyHelper.isToString(method))
/*     */       {
/*  91 */         return getClass().getName();
/*     */       }
/*  93 */       if (isTemporal) if (ProxyHelper.definedBy(method, new Class[] { TemporalOperationsImpl.class }))
/*     */         {
/*  95 */           return proxy.invokeSuper(obj, args);
/*     */         }
/*  97 */       if (isTemporal) if (ProxyHelper.definedBy(method, new Class[] { GridBasicOperations.class }))
/*     */         {
/*  99 */           return method.invoke(this.basicOp, args);
/*     */         }
/* 101 */       if (isLocation) { if (ProxyHelper.definedBy(method, new Class[] { this.locationOp.getClass().getSuperclass() }))
/*     */         {
/* 103 */           return method.invoke(this.locationOp, args);
/*     */         }
/*     */       }
/* 106 */       return proxy.invokeSuper(obj, args);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 110 */       AdfExceptionConverter.logError(e, method, args);
/* 111 */       throw AdfExceptionConverter.convertToAdfException(e);
/*     */     }
/*     */     finally
/*     */     {
/* 115 */       PerfStatManager.record(obj.getClass().getSimpleName(), method.getName(), l0);
/* 116 */       if (withSession) {
/* 117 */         ADFServiceContext.after();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public GridTemporalOperations getObject() throws Exception
/*     */   {
/* 124 */     Enhancer enhance = new Enhancer();
/* 125 */     enhance.setSuperclass(TemporalOperationsImpl.class);
/* 126 */     enhance.setCallback(this);
/* 127 */     GridTemporalOperations instance = (GridTemporalOperations)enhance.create();
/* 128 */     return instance;
/*     */   }
/*     */   
/*     */ 
/*     */   public Class<?> getObjectType()
/*     */   {
/* 134 */     return GridTemporalOperations.class;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isSingleton()
/*     */   {
/* 140 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\client\api\impl\TemporalOperationsProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */