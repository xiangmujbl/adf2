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
/*    */ public class ADFException
/*    */   extends RuntimeException
/*    */ {
/* 20 */   private String exceptionId = null;
/*    */   
/*    */ 
/*    */   private static final long serialVersionUID = -121224795776560634L;
/*    */   
/*    */ 
/*    */ 
/*    */   public ADFException() {}
/*    */   
/*    */ 
/*    */ 
/*    */   public ADFException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
/*    */   {
/* 33 */     super(message, cause, enableSuppression, writableStackTrace);
/*    */   }
/*    */   
/*    */ 
/*    */   public ADFException(String message, Throwable cause)
/*    */   {
/* 39 */     super(message, cause);
/*    */   }
/*    */   
/*    */   public ADFException(ExceptionTypes type, Throwable cause)
/*    */   {
/* 44 */     super(type.getExceptionMessage() + ":" + cause.getMessage(), cause);
/* 45 */     this.exceptionId = type.getExceptionId();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public ADFException(String exceptionId, String message, Throwable cause)
/*    */   {
/* 57 */     super(message, cause);
/* 58 */     this.exceptionId = exceptionId;
/*    */   }
/*    */   
/*    */   public ADFException(String exceptionId, String message)
/*    */   {
/* 63 */     super(message);
/* 64 */     this.exceptionId = exceptionId;
/*    */   }
/*    */   
/*    */   public ADFException(ExceptionTypes type)
/*    */   {
/* 69 */     super(type.getExceptionMessage());
/* 70 */     this.exceptionId = type.getExceptionId();
/*    */   }
/*    */   
/*    */   public ADFException(ExceptionTypes type, String message)
/*    */   {
/* 75 */     super(message);
/* 76 */     this.exceptionId = type.getExceptionId();
/*    */   }
/*    */   
/*    */   public ADFException(String message)
/*    */   {
/* 81 */     super(message);
/*    */   }
/*    */   
/*    */   public ADFException(Throwable cause) {
/* 85 */     super(cause);
/*    */   }
/*    */   
/*    */   public String getExceptionId() {
/* 89 */     return this.exceptionId;
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\common\ADFException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */