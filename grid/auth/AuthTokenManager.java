/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.auth;
/*    */ 
/*    */ import com.jnj.adf.grid.auth.exception.AuthException;
/*    */ import com.jnj.adf.grid.auth.session.AuthInternalSession;
/*    */ import com.jnj.adf.grid.support.context.ADFContext;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AuthTokenManager
/*    */ {
/* 12 */   private static AuthService authService = null;
/*    */   
/*    */   public static void setAuthService(AuthService service)
/*    */   {
/* 16 */     authService = service;
/*    */   }
/*    */   
/*    */   public static AuthInternalSession login(String userName, String password) throws AuthException
/*    */   {
/* 21 */     verifyAuthService();
/* 22 */     AuthInternalSession session = authService.login(userName, password);
/* 23 */     return session;
/*    */   }
/*    */   
/*    */   public static String verify(String userName, String password) throws AuthException
/*    */   {
/* 28 */     verifyAuthService();
/* 29 */     String token = authService.verify(userName, password);
/* 30 */     return token;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   @Deprecated
/*    */   public static void useToken(String token) {}
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public static void logout()
/*    */     throws AuthException
/*    */   {
/* 45 */     verifyAuthService();
/* 46 */     authService.logout();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static AuthInternalSession getAuthSession(AuthInternalSession bindSession)
/*    */     throws AuthException
/*    */   {
/* 57 */     return authService.getAuthSession(bindSession);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   private static void verifyAuthService()
/*    */   {
/* 65 */     if (authService == null)
/*    */     {
/* 67 */       authService = (AuthService)ADFContext.getBean(AuthService.class);
/* 68 */       throw new AuthException("authservice should not be empty.");
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\auth\AuthTokenManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */