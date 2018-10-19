/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.auth.exception;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import com.jnj.adf.grid.common.ADFException;
/*    */ import java.util.Map;
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
/*    */ public class AuthException
/*    */   extends ADFException
/*    */ {
/*    */   public static final int NO_LDAP_SERVER_AVALIABLE_ERROR = 1001;
/*    */   public static final int USERNAME_PASSWORD_ERROR = 1002;
/*    */   public static final int SESSION_INVALID_ERROR = 1003;
/*    */   public static final int NO_PRIVILEGE_ERROR = 1004;
/*    */   public static final int ACCOUNT_LOCKED_ERROR = 1005;
/*    */   public static final int OTHER_ERROR = 1009;
/*    */   public static final int UNKNOWN_ERROR = 1000;
/* 35 */   private Map<String, String> params = Maps.newHashMap();
/*    */   
/*    */   public static enum ParamNames {
/* 38 */     BAD_PASSWORD_COUNT;
/*    */     
/*    */     private ParamNames() {} }
/*    */   
/* 42 */   int error_code = 0;
/*    */   
/*    */ 
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */ 
/*    */ 
/* 49 */   public AuthException(int code) { this.error_code = code; }
/*    */   
/*    */   public AuthException(int code, String message) {
/* 52 */     super(message);
/* 53 */     this.error_code = code;
/*    */   }
/*    */   
/*    */   public AuthException(int code, String message, Map<String, String> params) {
/* 57 */     super(message);
/* 58 */     this.error_code = code;
/* 59 */     this.params = params;
/*    */   }
/*    */   
/*    */   public AuthException(String message) {
/* 63 */     super(message);
/*    */   }
/*    */   
/* 66 */   public int getErrorCode() { return this.error_code; }
/*    */   
/*    */   public void putParam(String key, String val)
/*    */   {
/* 70 */     this.params.put(key, val);
/*    */   }
/*    */   
/*    */   public String getParam(String key) {
/* 74 */     return (String)this.params.get(key);
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\auth\exception\AuthException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */