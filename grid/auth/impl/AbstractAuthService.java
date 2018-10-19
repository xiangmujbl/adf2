/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.auth.impl;
/*     */ 
/*     */ import com.gemstone.gemfire.cache.Region;
/*     */ import com.google.common.collect.Sets;
/*     */ import com.jnj.adf.grid.auth.AuthService;
/*     */ import com.jnj.adf.grid.auth.exception.AuthException;
/*     */ import com.jnj.adf.grid.auth.session.AbstractAuthInternalSession;
/*     */ import com.jnj.adf.grid.auth.session.AuthInternalSession;
/*     */ import com.jnj.adf.grid.auth.session.AuthInternalSessionImpl;
/*     */ import com.jnj.adf.grid.auth.user.AuthUserInfo;
/*     */ import com.jnj.adf.grid.auth.util.Crypto;
/*     */ import com.jnj.adf.grid.manager.RegionHelper;
/*     */ import com.jnj.adf.grid.utils.LogUtil;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractAuthService
/*     */   implements AuthService
/*     */ {
/*     */   public AuthInternalSession login(String userName, String password)
/*     */     throws AuthException
/*     */   {
/*     */     try
/*     */     {
/*  31 */       if (StringUtils.isEmpty(userName))
/*     */       {
/*  33 */         throw new AuthException(1002, "username can not be empty.");
/*     */       }
/*  35 */       if (StringUtils.isEmpty(password))
/*     */       {
/*  37 */         throw new AuthException(1002, "password can not be empty.");
/*     */       }
/*     */       
/*  40 */       String userId = doInternalVerify(userName, password);
/*  41 */       if (userId == null)
/*     */       {
/*  43 */         AuthException authEx = new AuthException(1002, "username or password error.");
/*  44 */         LogUtil.getAuditLog().error("verify ldap error: username={}, message={}", new Object[] { userName, authEx.getMessage() });
/*  45 */         throw authEx;
/*     */       }
/*  47 */       AuthInternalSession session = createSessionIfNecessary(userId);
/*     */       
/*     */ 
/*  50 */       if (RegionHelper.isClientSide())
/*     */       {
/*  52 */         doSaveSession(session);
/*     */       }
/*     */       
/*  55 */       return session;
/*     */     }
/*     */     catch (AuthException authEx)
/*     */     {
/*  59 */       LogUtil.getAuditLog().error("verify ldap error: username={}, message={}", new Object[] { userName, authEx.getMessage() });
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*  64 */       throw authEx;
/*     */     }
/*     */   }
/*     */   
/*     */   public String verify(String username, String pwd)
/*     */     throws AuthException
/*     */   {
/*     */     try
/*     */     {
/*  73 */       if (StringUtils.isEmpty(username))
/*     */       {
/*  75 */         throw new AuthException(1002, "username can not be empty.");
/*     */       }
/*  77 */       if (StringUtils.isEmpty(pwd))
/*     */       {
/*  79 */         throw new AuthException(1002, "password can not be empty.");
/*     */       }
/*     */       
/*  82 */       String userId = doInternalVerify(username, pwd);
/*  83 */       if (userId == null)
/*     */       {
/*  85 */         AuthException authEx = new AuthException(1002, "username or password error.");
/*  86 */         LogUtil.getAuditLog().error("verify ldap error: username={}, message={}", new Object[] { username, authEx.getMessage() });
/*  87 */         throw authEx;
/*     */       }
/*  89 */       return userId;
/*     */     }
/*     */     catch (AuthException authEx)
/*     */     {
/*  93 */       LogUtil.getAuditLog().error("verify ldap error: username={}, message={}", new Object[] { username, authEx.getMessage() });
/*  94 */       throw authEx;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private AuthInternalSession createSessionIfNecessary(String userId)
/*     */   {
/* 106 */     String token = createToken();
/*     */     
/* 108 */     AbstractAuthInternalSession session = new AuthInternalSessionImpl(token);
/* 109 */     session.setUserId(userId);
/* 110 */     AuthUserPrivilege privilege = doInternalFindPrivilege(userId);
/* 111 */     if (privilege == null)
/*     */     {
/* 113 */       privilege = new AuthUserPrivilege();
/* 114 */       privilege.setUserId(userId);
/*     */     }
/*     */     
/* 117 */     privilege.addRoles(Sets.newHashSet(new String[] { "_ANY_ONE_" }));
/* 118 */     session.setUserPrivilege(privilege);
/* 119 */     Set<String> userGroups = Sets.newHashSet();
/* 120 */     AuthUserInfo userInfo = doInternalFindUser(userId);
/* 121 */     session.setAuthUserInfo(userInfo);
/* 122 */     if ((userInfo != null) && (userInfo.getAdGroups() != null))
/*     */     {
/* 124 */       userGroups.addAll(userInfo.getAdGroups());
/*     */     }
/* 126 */     LogUtil.getAuditLog().info("Login with userName={} success. With Groups={} Roles={}", new Object[] { userId, 
/* 127 */       StringUtils.join(userGroups, ","), privilege == null ? "" : privilege.getRoles() });
/*     */     
/* 129 */     AuthSessionContextHelper.setTlSession(getAuthSession(session));
/* 130 */     return session;
/*     */   }
/*     */   
/*     */   public String createToken(String userId)
/*     */     throws AuthException
/*     */   {
/* 136 */     String token = createToken();
/*     */     
/* 138 */     AbstractAuthInternalSession session = new AuthInternalSessionImpl(token);
/* 139 */     session.setUserId(userId);
/* 140 */     AuthUserPrivilege privilege = doInternalFindPrivilege(userId);
/* 141 */     session.setUserPrivilege(privilege);
/* 142 */     AuthSessionContextHelper.setTlSession(getAuthSession(session));
/* 143 */     return token;
/*     */   }
/*     */   
/*     */   public void logout()
/*     */     throws AuthException
/*     */   {
/* 149 */     if (AuthSessionContextHelper.getTlSession() != null)
/*     */     {
/* 151 */       String username = AuthSessionContextHelper.getTlSession().getUserId();
/* 152 */       LogUtil.getAuditLog().info("user logout username={} , token is ***************.", new Object[] { username });
/*     */       
/* 154 */       String token = AuthSessionContextHelper.getToken();
/*     */       
/* 156 */       if (token != null)
/*     */       {
/* 158 */         Region sessionRegion = RegionHelper.getSessionRegion();
/* 159 */         sessionRegion.remove(token);
/*     */       }
/*     */       
/* 162 */       AuthSessionContextHelper.reset();
/*     */     }
/*     */   }
/*     */   
/*     */   public AuthInternalSession getAuthSession(AuthInternalSession bindSession)
/*     */     throws AuthException
/*     */   {
/* 169 */     if ((bindSession != null) && (StringUtils.isNotEmpty(bindSession.getUserId())) && 
/* 170 */       (StringUtils.isNotEmpty(bindSession.getToken())))
/*     */     {
/* 172 */       return bindSession;
/*     */     }
/* 174 */     String token = createToken();
/* 175 */     AbstractAuthInternalSession session = new AbstractAuthInternalSession(token) {};
/* 178 */     session.setUserId("unknown");
/* 179 */     return session;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String doInternalVerify(String username, String pwd)
/*     */     throws AuthException
/*     */   {
/* 189 */     throw new AuthException(1002);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void doSaveSession(AuthInternalSession session) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AuthUserInfo getAuthUserInfo(String userId)
/*     */     throws AuthException
/*     */   {
/* 206 */     return doInternalFindUser(userId);
/*     */   }
/*     */   
/*     */   public AuthUserPrivilege getAuthUserPrivilege(String userId)
/*     */     throws AuthException
/*     */   {
/* 212 */     AuthUserPrivilege privilege = doInternalFindPrivilege(userId);
/* 213 */     return privilege;
/*     */   }
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
/*     */   protected AuthUserInfo doInternalFindUser(String userId)
/*     */     throws AuthException
/*     */   {
/* 228 */     return null;
/*     */   }
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
/*     */   protected AuthUserPrivilege doInternalFindPrivilege(String userId)
/*     */     throws AuthException
/*     */   {
/* 243 */     return null;
/*     */   }
/*     */   
/*     */   private String createToken() throws AuthException
/*     */   {
/* 248 */     String token = Crypto.UUID();
/* 249 */     return token;
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\auth\impl\AbstractAuthService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */