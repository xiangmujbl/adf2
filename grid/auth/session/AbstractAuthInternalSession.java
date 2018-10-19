/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.auth.session;
/*     */ 
/*     */ import com.jnj.adf.grid.auth.impl.AuthUserPrivilege;
/*     */ import com.jnj.adf.grid.auth.user.AuthUserInfo;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractAuthInternalSession
/*     */   implements AuthInternalSession
/*     */ {
/*     */   private static final long serialVersionUID = 3299442521876046645L;
/*  16 */   protected String token = null;
/*  17 */   protected long createTime = 0L;
/*  18 */   protected long lastAccessedTime = 0L;
/*  19 */   protected String userId = null;
/*     */   protected AuthUserPrivilege userPrivilege;
/*     */   protected AuthUserInfo authUserInfo;
/*  22 */   protected Map<String, Object> attributes = new HashMap();
/*     */   
/*     */ 
/*     */ 
/*     */   public AbstractAuthInternalSession() {}
/*     */   
/*     */ 
/*     */   public AbstractAuthInternalSession(String token)
/*     */   {
/*  31 */     this.token = token;
/*  32 */     this.createTime = System.currentTimeMillis();
/*  33 */     this.lastAccessedTime = System.currentTimeMillis();
/*     */   }
/*     */   
/*     */ 
/*     */   public String getToken()
/*     */   {
/*  39 */     this.lastAccessedTime = System.currentTimeMillis();
/*  40 */     return this.token;
/*     */   }
/*     */   
/*     */   public void setToken(String token)
/*     */   {
/*  45 */     this.token = token;
/*     */   }
/*     */   
/*     */   public Map<String, Object> getAttributes()
/*     */   {
/*  50 */     return this.attributes;
/*     */   }
/*     */   
/*     */   public void setAttributes(Map<String, Object> attributes)
/*     */   {
/*  55 */     this.attributes = attributes;
/*     */   }
/*     */   
/*     */ 
/*     */   public long getLastAccessedTime()
/*     */   {
/*  61 */     if (this.lastAccessedTime <= 0L)
/*     */     {
/*  63 */       this.lastAccessedTime = System.currentTimeMillis();
/*     */     }
/*  65 */     return this.lastAccessedTime;
/*     */   }
/*     */   
/*     */   public void setLastAccessedTime(long lastAccessedTime)
/*     */   {
/*  70 */     this.lastAccessedTime = lastAccessedTime;
/*     */   }
/*     */   
/*     */ 
/*     */   public long getCreateTime()
/*     */   {
/*  76 */     if (this.createTime <= 0L)
/*     */     {
/*  78 */       this.createTime = System.currentTimeMillis();
/*     */     }
/*  80 */     return this.createTime;
/*     */   }
/*     */   
/*     */   public void setCreateTime(long createTime)
/*     */   {
/*  85 */     this.createTime = createTime;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setAttribute(String name, Object value)
/*     */   {
/*  91 */     this.lastAccessedTime = System.currentTimeMillis();
/*  92 */     this.attributes.put(name, value);
/*     */   }
/*     */   
/*     */ 
/*     */   public Collection<String> getAttributeNames()
/*     */   {
/*  98 */     this.lastAccessedTime = System.currentTimeMillis();
/*  99 */     return this.attributes.keySet();
/*     */   }
/*     */   
/*     */ 
/*     */   public Object getAttribute(String name)
/*     */   {
/* 105 */     this.lastAccessedTime = System.currentTimeMillis();
/* 106 */     return this.attributes.get(name);
/*     */   }
/*     */   
/*     */ 
/*     */   public void removeAttribute(String name)
/*     */   {
/* 112 */     this.lastAccessedTime = System.currentTimeMillis();
/* 113 */     this.attributes.remove(name);
/*     */   }
/*     */   
/*     */   public String getUserId()
/*     */   {
/* 118 */     return this.userId;
/*     */   }
/*     */   
/*     */   public void setUserId(String userId)
/*     */   {
/* 123 */     this.userId = userId;
/*     */   }
/*     */   
/*     */   public AuthUserPrivilege getUserPrivilege()
/*     */   {
/* 128 */     return this.userPrivilege;
/*     */   }
/*     */   
/*     */   public void setUserPrivilege(AuthUserPrivilege userPrivilege)
/*     */   {
/* 133 */     this.userPrivilege = userPrivilege;
/*     */   }
/*     */   
/*     */   public void setAuthUserInfo(AuthUserInfo userInfo)
/*     */   {
/* 138 */     this.authUserInfo = userInfo;
/*     */   }
/*     */   
/*     */   public AuthUserInfo getAuthUserInfo()
/*     */   {
/* 143 */     return this.authUserInfo;
/*     */   }
/*     */   
/*     */ 
/*     */   public AuthUserInfo getUserInfo()
/*     */   {
/* 149 */     return this.authUserInfo;
/*     */   }
/*     */   
/*     */ 
/*     */   public void clear()
/*     */   {
/* 155 */     this.token = null;
/* 156 */     this.createTime = 0L;
/* 157 */     this.lastAccessedTime = 0L;
/* 158 */     this.userId = null;
/* 159 */     this.userPrivilege = null;
/* 160 */     this.attributes.clear();
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\auth\session\AbstractAuthInternalSession.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */