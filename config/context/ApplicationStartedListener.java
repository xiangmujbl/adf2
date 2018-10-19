/*    */ package com.jnj.adf.dataservice.adfcoreignite.config.context;
/*    */ 
/*    */ import com.jnj.adf.config.support.BeanAdditionalPropertyGenerator;
/*    */ import com.jnj.adf.grid.events.ApplicationConextReadyEvent;
/*    */ import com.jnj.adf.grid.support.hybrid.ADFHybridCacheManager;
/*    */ import com.jnj.adf.grid.support.system.ADFConfigHelper.TYPES;
/*    */ import com.jnj.adf.grid.utils.LogUtil;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import org.springframework.context.ApplicationContext;
/*    */ import org.springframework.context.ApplicationListener;
/*    */ import org.springframework.context.event.ContextRefreshedEvent;
/*    */ 
/*    */ 
/*    */ public class ApplicationStartedListener
/*    */   implements ApplicationListener<ContextRefreshedEvent>
/*    */ {
/*    */   public void onApplicationEvent(ContextRefreshedEvent event)
/*    */   {
/* 19 */     if (event.getApplicationContext().getParent() == null)
/*    */     {
/* 21 */       ApplicationContext applicationContext = event.getApplicationContext();
/* 22 */       LogUtil.getCoreLog().info("application context started event invoked.");
/* 23 */       BeanAdditionalPropertyGenerator beanGenerator = (BeanAdditionalPropertyGenerator)applicationContext.getBean(BeanAdditionalPropertyGenerator.class);
/* 24 */       if (beanGenerator != null) {
/* 25 */         beanGenerator.init();
/*    */       }
/*    */       
/*    */ 
/* 29 */       ADFHybridCacheManager.getInstance().initDefaultConnection();
/* 30 */       applicationContext.publishEvent(new ApplicationConextReadyEvent(ADFConfigHelper.TYPES.CLIENT));
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\config\context\ApplicationStartedListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */