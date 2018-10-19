/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.namespace;
/*    */ 
/*    */ public class ExecutionContextImpl
/*    */   implements ExecutionContext
/*    */ {
/*    */   private String namespace;
/*    */   private String version;
/*    */   
/*    */   public ExecutionContextImpl(String namespace)
/*    */   {
/* 11 */     this.namespace = namespace;
/*    */   }
/*    */   
/*    */ 
/*    */   public ExecutionContextImpl(String namespace, String version)
/*    */   {
/* 17 */     this.namespace = namespace;
/* 18 */     this.version = version;
/*    */   }
/*    */   
/*    */ 
/*    */   public ExecutionContextImpl() {}
/*    */   
/*    */ 
/*    */   public String getNamespace()
/*    */   {
/* 27 */     return this.namespace;
/*    */   }
/*    */   
/*    */ 
/*    */   public String getVersion()
/*    */   {
/* 33 */     return this.version;
/*    */   }
/*    */   
/*    */   public void setVersion(String version)
/*    */   {
/* 38 */     this.version = version;
/*    */   }
/*    */   
/*    */   public void setNamespace(String namespace)
/*    */   {
/* 43 */     this.namespace = namespace;
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\namespace\ExecutionContextImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */