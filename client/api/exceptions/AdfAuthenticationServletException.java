/*    */ package com.jnj.adf.dataservice.adfcoreignite.client.api.exceptions;
/*    */ 
/*    */ import javax.servlet.ServletException;
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
/*    */ public class AdfAuthenticationServletException
/*    */   extends ServletException
/*    */ {
/* 25 */   private int error_code = 0;
/*    */   
/*    */   private static final long serialVersionUID = 2657890877671L;
/*    */   
/* 29 */   public AdfAuthenticationServletException(int code) { this.error_code = code; }
/*    */   
/*    */   public AdfAuthenticationServletException(int code, String message) {
/* 32 */     super(message);
/* 33 */     this.error_code = code;
/*    */   }
/*    */   
/* 36 */   public AdfAuthenticationServletException(int code, String message, Throwable throwable) { super(message, throwable);
/* 37 */     this.error_code = code;
/*    */   }
/*    */   
/* 40 */   public AdfAuthenticationServletException(String message) { super(message); }
/*    */   
/*    */   public int getErrorCode() {
/* 43 */     return this.error_code;
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\client\api\exceptions\AdfAuthenticationServletException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */