/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.auth.impl;
/*     */ 
/*     */ import com.google.common.collect.Sets;
/*     */ import com.jnj.adf.grid.auth.AuthMode;
/*     */ import com.jnj.adf.grid.auth.session.AuthInternalSession;
/*     */ import com.jnj.adf.grid.auth.user.AuthUserInfo;
/*     */ import com.jnj.adf.grid.auth.util.Crypto;
/*     */ import com.jnj.adf.grid.utils.JsonUtils;
/*     */ import java.util.Properties;
/*     */ import java.util.Set;
/*     */ import org.springframework.core.NamedThreadLocal;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AuthSessionContextHelper
/*     */ {
/*     */   public static final String SESSION_REGION_NAME = "/_HOST/SESSION";
/*  37 */   private static final ThreadLocal<AuthInternalSession> TL_SESSIONS = new NamedThreadLocal("auth-session-context-tl");
/*  38 */   private static AuthInternalSession session = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static final String CLIENT_SESSION_NAME_KEY = "_CLI_SESSION_AUTH_KEY_";
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  48 */   private static AuthMode mode = AuthMode.TREAD_LOCAL;
/*     */   
/*     */   public static boolean isThreadLocalMode() {
/*  51 */     return AuthMode.TREAD_LOCAL == mode;
/*     */   }
/*     */   
/*     */   public static void setMode(AuthMode authMode) {
/*  55 */     mode = authMode;
/*     */   }
/*     */   
/*     */   public static final void setTlSession(AuthInternalSession internalSession)
/*     */   {
/*  60 */     if (AuthMode.TREAD_LOCAL == mode) {
/*  61 */       TL_SESSIONS.set(internalSession);
/*  62 */     } else if (AuthMode.GLOBAL == mode) {
/*  63 */       session = internalSession;
/*     */     }
/*     */   }
/*     */   
/*     */   public static final AuthInternalSession getTlSession()
/*     */   {
/*  69 */     if (AuthMode.TREAD_LOCAL == mode)
/*  70 */       return (AuthInternalSession)TL_SESSIONS.get();
/*  71 */     if (AuthMode.GLOBAL == mode) {
/*  72 */       return session;
/*     */     }
/*  74 */     return null;
/*     */   }
/*     */   
/*     */   public static final String getToken()
/*     */   {
/*  79 */     AuthInternalSession session = getTlSession();
/*  80 */     if (session != null) {
/*  81 */       return session.getToken();
/*     */     }
/*  83 */     return null;
/*     */   }
/*     */   
/*     */   public static final String getUserId() {
/*  87 */     AuthInternalSession session = getTlSession();
/*  88 */     if (session != null) {
/*  89 */       return session.getUserId();
/*     */     }
/*  91 */     return null;
/*     */   }
/*     */   
/*     */   public static final void reset() {
/*  95 */     if ((AuthMode.TREAD_LOCAL == mode) && (TL_SESSIONS.get() != null)) {
/*  96 */       TL_SESSIONS.remove();
/*  97 */     } else if (AuthMode.GLOBAL == mode) {
/*  98 */       session = null;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static final Properties getMultiUserCredentials()
/*     */   {
/* 107 */     AuthInternalSession authSession = getTlSession();
/* 108 */     Properties prop = new Properties();
/* 109 */     if (authSession == null) {
/* 110 */       prop.setProperty("userId", "anonymous");
/* 111 */       prop.put("roles", JsonUtils.objectToJson(Sets.newHashSet(new String[] { "_ANONYMOUS_", "_ANY_ONE_" })));
/* 112 */       return prop;
/*     */     }
/* 114 */     prop.put("userId", authSession.getUserId());
/* 115 */     prop.put("token", authSession.getToken());
/* 116 */     Set<String> roles = authSession.getUserPrivilege().getRoles();
/* 117 */     if (roles != null) {
/* 118 */       prop.put("roles", JsonUtils.objectToJson(roles));
/*     */     }
/* 120 */     Set<String> groups = authSession.getUserInfo().getAdGroups();
/* 121 */     if (roles != null) {
/* 122 */       prop.put("ad_groups", JsonUtils.objectToJson(groups));
/*     */     }
/* 124 */     return prop;
/*     */   }
/*     */   
/*     */   private static String signature(Properties p) {
/* 128 */     byte[] buffer = JsonUtils.objectToBytes(p);
/* 129 */     return Crypto.sign(buffer);
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\auth\impl\AuthSessionContextHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */