/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.client.api.impl;
/*    */ 
/*    */ import com.jnj.adf.grid.client.api.support.ADFServiceContext;
/*    */ import com.jnj.adf.grid.client.api.support.ADFSessionHelper;
/*    */ import com.jnj.adf.grid.client.api.support.BasicGridService;
/*    */ import com.jnj.adf.grid.client.api.support.ProxyHelper;
/*    */ import com.jnj.adf.grid.common.AdfExceptionConverter;
/*    */ import com.jnj.adf.grid.perf.PerfStatManager;
/*    */ import com.jnj.adf.grid.utils.LogUtil;
/*    */ import java.lang.reflect.Method;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import org.springframework.beans.factory.FactoryBean;
/*    */ import org.springframework.cglib.proxy.MethodInterceptor;
/*    */ import org.springframework.cglib.proxy.MethodProxy;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component("_adf_basic")
/*    */ public class BasicGridServiceProxy
/*    */   implements FactoryBean<BasicGridService>, MethodInterceptor
/*    */ {
/* 42 */   private BasicGridService basicService = new BasicGridServiceImpl();
/*    */   
/*    */   public BasicGridService getObject() throws Exception
/*    */   {
/* 46 */     return this.basicService;
/*    */   }
/*    */   
/*    */ 
/*    */   public Class<?> getObjectType()
/*    */   {
/* 52 */     return BasicGridService.class;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean isSingleton()
/*    */   {
/* 58 */     return true;
/*    */   }
/*    */   
/*    */   public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
/*    */     throws Throwable
/*    */   {
/* 64 */     LogUtil.getCoreLog().debug("{}.{} invoked", new Object[] { getObjectType().getName(), method.getName() });
/* 65 */     boolean withSession = ADFSessionHelper.withSession(method);
/* 66 */     if (withSession)
/* 67 */       ADFServiceContext.before();
/* 68 */     long l0 = System.currentTimeMillis();
/*    */     try {
/*    */       Object localObject1;
/* 71 */       if (ProxyHelper.isToString(method))
/*    */       {
/* 73 */         return getClass().getName();
/*    */       }
/* 75 */       return proxy.invokeSuper(obj, args);
/*    */     }
/*    */     catch (Exception e)
/*    */     {
/* 79 */       AdfExceptionConverter.logError(e, method, args);
/* 80 */       throw AdfExceptionConverter.convertToAdfException(e);
/*    */     }
/*    */     finally {
/* 83 */       PerfStatManager.record(obj.getClass().getSimpleName(), method.getName(), l0);
/* 84 */       if (withSession) {
/* 85 */         ADFServiceContext.after();
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\client\api\impl\BasicGridServiceProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */