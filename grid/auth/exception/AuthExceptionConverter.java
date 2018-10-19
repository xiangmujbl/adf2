/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.auth.exception;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.apache.commons.lang3.StringUtils;
/*    */ import org.springframework.ldap.AuthenticationException;
/*    */ import org.springframework.ldap.CommunicationException;
/*    */ import org.springframework.ldap.NamingException;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AuthExceptionConverter
/*    */ {
/*    */   public static AuthException convertException(NamingException e, Map<String, String> params)
/*    */   {
/* 15 */     if ((e instanceof AuthenticationException))
/* 16 */       return convertAuthenticationException((AuthenticationException)e, params);
/* 17 */     if ((e instanceof CommunicationException)) {
/* 18 */       return convertCommunicationException((CommunicationException)e, params);
/*    */     }
/* 20 */     return new AuthException(1009, e.getMessage());
/*    */   }
/*    */   
/*    */ 
/*    */   public static AuthException convertException(NamingException e)
/*    */   {
/* 26 */     return convertException(e, null);
/*    */   }
/*    */   
/*    */   private static AuthException convertAuthenticationException(AuthenticationException e, Map<String, String> params) {
/* 30 */     String errorMessage = e.getMessage();
/*    */     
/* 32 */     if (errorMessage.indexOf("AcceptSecurityContext error, data 775") > -1) {
/* 33 */       return new AuthException(1005, "user account locked. ", params);
/*    */     }
/* 35 */     String badPasswordCount = (String)params.get(AuthException.ParamNames.BAD_PASSWORD_COUNT.name());
/* 36 */     return new AuthException(1002, "username or password error. " + (
/* 37 */       StringUtils.isNotEmpty(badPasswordCount) ? "attempt " + badPasswordCount + " times." : ""), params);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   private static AuthException convertCommunicationException(CommunicationException e, Map<String, String> params)
/*    */   {
/* 44 */     return new AuthException(1001, "no ldap server avaliable ", params);
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\auth\exception\AuthExceptionConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */