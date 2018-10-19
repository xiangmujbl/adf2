/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.web;
/*    */ 
/*    */ import com.jnj.adf.client.api.exceptions.AdfAuthenticationServletException;
/*    */ import com.jnj.adf.grid.auth.AuthTokenManager;
/*    */ import com.jnj.adf.grid.auth.exception.AuthException;
/*    */ import com.jnj.adf.grid.auth.session.AuthInternalSession;
/*    */ import com.jnj.adf.grid.common.ADFException;
/*    */ import java.io.IOException;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletRequestWrapper;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import javax.servlet.http.HttpSession;
/*    */ import org.apache.commons.lang3.StringUtils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AdfAuthHttpServletRequestWrapper
/*    */   extends HttpServletRequestWrapper
/*    */ {
/*    */   public AdfAuthHttpServletRequestWrapper(HttpServletRequest request)
/*    */   {
/* 25 */     super(request);
/*    */   }
/*    */   
/*    */   public void login(String username, String password) throws ServletException
/*    */   {
/*    */     try
/*    */     {
/* 32 */       AuthInternalSession internalSession = AuthTokenManager.login(username, password);
/* 33 */       if (internalSession != null) {
/* 34 */         getSession(true).setAttribute("_CLI_SESSION_AUTH_KEY_", internalSession);
/*    */       }
/*    */     } catch (AuthException e) {
/* 37 */       throw new AdfAuthenticationServletException(e.getErrorCode(), e.getMessage(), e);
/*    */     } catch (ADFException e) {
/* 39 */       if ((e.getCause() != null) && ((e.getCause() instanceof AuthException))) {
/* 40 */         AuthException ex = (AuthException)e.getCause();
/* 41 */         throw new AdfAuthenticationServletException(ex.getErrorCode(), ex.getMessage(), ex);
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public void logout() throws ServletException
/*    */   {
/* 48 */     super.logout();
/* 49 */     HttpSession session = getSession(true);
/* 50 */     session.removeAttribute("_CLI_SESSION_AUTH_KEY_");
/* 51 */     AuthTokenManager.logout();
/*    */   }
/*    */   
/*    */   public boolean authenticate(HttpServletResponse response) throws IOException, ServletException
/*    */   {
/* 56 */     HttpSession session = getSession(true);
/* 57 */     Object sessionObject = session.getAttribute("_CLI_SESSION_AUTH_KEY_");
/* 58 */     if (sessionObject != null) {
/* 59 */       AuthInternalSession authInternalSession = (AuthInternalSession)sessionObject;
/* 60 */       return (StringUtils.isNotEmpty(authInternalSession.getToken())) && (StringUtils.isNotEmpty(authInternalSession.getUserId()));
/*    */     }
/* 62 */     throw new AdfAuthenticationServletException(1003, "SESSION INVALID[Has not login]");
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\web\AdfAuthHttpServletRequestWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */