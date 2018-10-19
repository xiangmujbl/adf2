/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.auth;
/*    */ 
/*    */ 
/*    */ public class AuthSalt
/*    */ {
/*    */   private String nonce;
/*    */   private String timestamp;
/*    */   
/*    */   public String getNonce()
/*    */   {
/* 11 */     return this.nonce;
/*    */   }
/*    */   
/*    */   public void setNonce(String nonce) {
/* 15 */     this.nonce = nonce;
/*    */   }
/*    */   
/*    */   public String getTimestamp() {
/* 19 */     return this.timestamp;
/*    */   }
/*    */   
/*    */   public void setTimestamp(String timestamp) {
/* 23 */     this.timestamp = timestamp;
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\auth\AuthSalt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */