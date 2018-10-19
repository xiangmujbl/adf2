/*    */ package com.jnj.adf.dataservice.adfcoreignite.config.context;
/*    */ 
/*    */ import org.springframework.beans.BeansException;
/*    */ import org.springframework.context.ApplicationContext;
/*    */ import org.springframework.context.ApplicationContextAware;
/*    */ 
/*    */ public class ApplicationContextBeansAware
/*    */   implements ApplicationContextAware
/*    */ {
/*    */   private static ApplicationContext applicationContext;
/*    */   
/*    */   public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
/*    */   {
/* 14 */     applicationContext = applicationContext;
/*    */   }
/*    */   
/*    */   public static <T> T getBeanByType(Class<T> typeClass)
/*    */   {
/* 19 */     return (T)applicationContext.getBean(typeClass);
/*    */   }
/*    */   
/*    */   public static <T> T getBean(String name, Class<T> typeClass) {
/* 23 */     return (T)applicationContext.getBean(name, typeClass);
/*    */   }
/*    */   
/*    */   public static String[] getBeanNamesForType(Class<?> typeClass)
/*    */   {
/* 28 */     return applicationContext.getBeanNamesForType(typeClass);
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\config\context\ApplicationContextBeansAware.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */