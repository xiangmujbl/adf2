/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.manager;
/*    */ 
/*    */ import org.springframework.context.ApplicationContext;
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
/*    */ public class ContextManager
/*    */ {
/* 27 */   private static final ContextManager INST = new ContextManager();
/*    */   private ApplicationContext context;
/*    */   
/*    */   public void setContext(ApplicationContext context) {
/* 31 */     this.context = context;
/*    */   }
/*    */   
/*    */   public static final ContextManager getInstance() {
/* 35 */     return INST;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public <T> T getBean(String name)
/*    */   {
/* 44 */     return (T)this.context.getBean(name);
/*    */   }
/*    */   
/*    */ 
/*    */   public <T> T getBean(Class<T> classType)
/*    */   {
/* 50 */     return (T)this.context.getBean(classType);
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\manager\ContextManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */