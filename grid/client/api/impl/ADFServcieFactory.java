/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.client.api.impl;
/*    */ 
/*    */ import com.jnj.adf.client.api.ADFService;
/*    */ import org.springframework.beans.BeansException;
/*    */ import org.springframework.context.ApplicationContext;
/*    */ import org.springframework.context.ApplicationContextAware;
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
/*    */ public class ADFServcieFactory
/*    */   implements ApplicationContextAware
/*    */ {
/*    */   ApplicationContext context;
/*    */   
/*    */   public void setApplicationContext(ApplicationContext applicationContext)
/*    */     throws BeansException
/*    */   {
/* 35 */     this.context = applicationContext;
/*    */   }
/*    */   
/*    */   public ADFService createADFService() {
/* 39 */     ADFServiceImpl impl = new ADFServiceImpl();
/* 40 */     return impl;
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\client\api\impl\ADFServcieFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */