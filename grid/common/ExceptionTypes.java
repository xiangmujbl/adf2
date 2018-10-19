/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.common;
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
/*    */ public enum ExceptionTypes
/*    */ {
/* 24 */   unkonw001("NullPointerException"),  login("login.failed"),  timeout("timeout"),  cacheClosed("cacheClosed"),  invalideGridName("invalideGridName"), 
/* 25 */   cacheWrite("cacheClosed"),  transaction("transaction"),  fetchGridInfo("connect.fetchGridInfo"),  connectNoLocators("connect.NoAvailableLocators"), 
/* 26 */   iBizNotDefine("iBizNotDefine"),  authenticationFail("AuthenticationFail"),  authenrizationFail("authenrizationFail"), 
/* 27 */   invalidDataException("invalidDataException"), 
/* 28 */   others("others"),  nullPath("null path specified");
/*    */   
/*    */   private final String exceptionMessage;
/*    */   
/*    */   private ExceptionTypes(String message)
/*    */   {
/* 34 */     this.exceptionMessage = message;
/*    */   }
/*    */   
/*    */   public String getExceptionId() {
/* 38 */     return name();
/*    */   }
/*    */   
/*    */   public String getExceptionMessage()
/*    */   {
/* 43 */     return this.exceptionMessage;
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\common\ExceptionTypes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */