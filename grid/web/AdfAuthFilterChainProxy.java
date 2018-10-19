/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.web;
/*    */ 
/*    */ import com.jnj.adf.grid.auth.AuthMode;
/*    */ import com.jnj.adf.grid.auth.AuthTokenManager;
/*    */ import com.jnj.adf.grid.auth.impl.AuthSessionContextHelper;
/*    */ import com.jnj.adf.grid.auth.session.AuthInternalSession;
/*    */ import java.io.IOException;
/*    */ import javax.servlet.Filter;
/*    */ import javax.servlet.FilterChain;
/*    */ import javax.servlet.FilterConfig;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.ServletRequest;
/*    */ import javax.servlet.ServletResponse;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpSession;
/*    */ import org.springframework.beans.BeansException;
/*    */ import org.springframework.context.ApplicationContext;
/*    */ import org.springframework.context.ApplicationContextAware;
/*    */ import org.springframework.stereotype.Component;
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
/*    */ @Component("adfAuthenticationFilterChain")
/*    */ public class AdfAuthFilterChainProxy
/*    */   implements Filter, ApplicationContextAware
/*    */ {
/*    */   private ApplicationContext applicationContext;
/*    */   
/*    */   public void setApplicationContext(ApplicationContext applicationContext)
/*    */     throws BeansException
/*    */   {
/* 47 */     this.applicationContext = applicationContext;
/*    */   }
/*    */   
/*    */   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
/*    */     throws IOException, ServletException
/*    */   {
/* 53 */     HttpServletRequest httpRequest = (HttpServletRequest)request;
/* 54 */     String uri = httpRequest.getRequestURI();
/* 55 */     if (!checkExcludePattern(uri)) {
/* 56 */       HttpSession session = httpRequest.getSession(true);
/* 57 */       Object sessionObject = session.getAttribute("_CLI_SESSION_AUTH_KEY_");
/* 58 */       if (sessionObject != null) {
/* 59 */         AuthInternalSession authInternalSession = (AuthInternalSession)sessionObject;
/* 60 */         AuthSessionContextHelper.setTlSession(AuthTokenManager.getAuthSession(authInternalSession));
/*    */       }
/*    */     }
/* 63 */     AdfAuthHttpServletRequestWrapper requestWrapper = new AdfAuthHttpServletRequestWrapper(httpRequest);
/* 64 */     chain.doFilter(requestWrapper, response);
/*    */     
/* 66 */     if (AuthSessionContextHelper.isThreadLocalMode()) {
/* 67 */       AuthSessionContextHelper.reset();
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   private boolean checkExcludePattern(String uri)
/*    */   {
/* 77 */     return false;
/*    */   }
/*    */   
/*    */   public void init(FilterConfig filterConfig) throws ServletException
/*    */   {
/* 82 */     AuthSessionContextHelper.setMode(AuthMode.TREAD_LOCAL);
/*    */   }
/*    */   
/*    */   public void destroy() {}
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\web\AdfAuthFilterChainProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */