/*    */ package com.jnj.adf.dataservice.adfcoreignite.config.context;
/*    */ 
/*    */ import com.jnj.adf.config.CglibRemoteServiceProxy;
/*    */ import com.jnj.adf.config.annotations.RemoteService;
/*    */ import com.jnj.adf.grid.support.context.ADFRuntime;
/*    */ import com.jnj.adf.grid.utils.LogUtil;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import org.springframework.beans.BeansException;
/*    */ import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
/*    */ import org.springframework.core.annotation.AnnotationUtils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class InternalBeanInstantiationAwareBeanPostProcessor
/*    */   extends InstantiationAwareBeanPostProcessorAdapter
/*    */ {
/*    */   public Object postProcessAfterInitialization(Object bean, String beanName)
/*    */     throws BeansException
/*    */   {
/* 21 */     bean = registerRemoteService(bean, beanName);
/* 22 */     return super.postProcessAfterInitialization(bean, beanName);
/*    */   }
/*    */   
/*    */ 
/*    */   private Object registerRemoteService(Object bean, String beanName)
/*    */   {
/* 28 */     if (ADFRuntime.getRuntime().isDataStoreSide())
/* 29 */       return bean;
/* 30 */     RemoteService ann = (RemoteService)AnnotationUtils.getAnnotation(bean.getClass(), RemoteService.class);
/* 31 */     if (ann == null)
/* 32 */       return bean;
/* 33 */     LogUtil.getCoreLog().debug("Client make proxy remote service {}/{}", new Object[] { beanName, bean.getClass().getSimpleName() });
/* 34 */     CglibRemoteServiceProxy proxy = new CglibRemoteServiceProxy();
/* 35 */     bean = proxy.bind(bean, beanName);
/* 36 */     return bean;
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\config\context\InternalBeanInstantiationAwareBeanPostProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */