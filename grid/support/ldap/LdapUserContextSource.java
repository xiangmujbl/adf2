/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.support.ldap;
/*    */ 
/*    */ import com.gemstone.gemfire.internal.lang.StringUtils;
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.Map;
/*    */ import org.springframework.ldap.core.support.LdapContextSource;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LdapUserContextSource
/*    */   extends LdapContextSource
/*    */ {
/*    */   public LdapUserContextSource()
/*    */   {
/* 17 */     this(null);
/*    */   }
/*    */   
/*    */ 
/*    */   public LdapUserContextSource(String url)
/*    */   {
/* 23 */     if (StringUtils.isEmpty(url)) {
/* 24 */       url = LdapConfigHelper.getLdapUserHostUrl();
/*    */     }
/* 26 */     setUrl(url);
/*    */     
/* 28 */     Map<String, Object> baseEnv = Maps.newHashMap();
/* 29 */     if ((url != null) && (LdapConfigHelper.ifUseSSL()) && (LdapConfigHelper.ifSslTrustAll())) {
/* 30 */       baseEnv.put("java.naming.ldap.factory.socket", "com.jnj.adf.grid.support.ssl.TrustAllSSLSocketFactory");
/*    */     }
/* 32 */     setBaseEnvironmentProperties(baseEnv);
/* 33 */     String userDn = LdapConfigHelper.getLdapBindUserDn();
/* 34 */     setUserDn(userDn);
/* 35 */     String userPwd = LdapConfigHelper.getLdapBindUserPassword();
/* 36 */     setPassword(userPwd);
/*    */     
/* 38 */     setPooled(true);
/*    */   }
/*    */   
/*    */   public void ldapGroupRefresh()
/*    */   {
/* 43 */     setUrl(LdapConfigHelper.getLdapGroupHostUrl());
/* 44 */     refresh();
/*    */   }
/*    */   
/*    */   private void refresh() {
/* 48 */     setUserDn(LdapConfigHelper.getLdapBindUserDn());
/* 49 */     setPassword(LdapConfigHelper.getLdapBindUserPassword());
/*    */     
/* 51 */     Map<String, Object> baseEnv = Maps.newHashMap();
/* 52 */     if ((LdapConfigHelper.ifUseSSL()) && (LdapConfigHelper.ifSslTrustAll())) {
/* 53 */       baseEnv.put("java.naming.ldap.factory.socket", "com.jnj.adf.grid.support.ssl.TrustAllSSLSocketFactory");
/*    */     }
/* 55 */     setBaseEnvironmentProperties(baseEnv);
/*    */   }
/*    */   
/*    */   public void ldapUserRefresh() {
/* 59 */     setUrl(LdapConfigHelper.getLdapUserHostUrl());
/* 60 */     refresh();
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\support\ldap\LdapUserContextSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */