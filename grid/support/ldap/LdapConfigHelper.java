/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.support.ldap;
/*     */ 
/*     */ import com.jnj.adf.grid.utils.LogUtil;
/*     */ import java.io.IOException;
/*     */ import java.net.URL;
/*     */ import java.util.Properties;
/*     */ import org.apache.logging.log4j.Logger;
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
/*     */ public class LdapConfigHelper
/*     */ {
/*     */   private static final String LDAP_HOST_URL = "ldap.host.url";
/*     */   private static final String LDAP_AUTH_USERDN_PATTERN = "ldap.auth.userDn";
/*     */   private static final String LDAP_BIND_USERDN = "ldap.bind.userDn";
/*     */   private static final String LDAP_BIND_PASSWORD = "ldap.bind.password";
/*     */   private static final String LDAP_USER_HOST_URL = "ldap.host.url";
/*     */   private static final String LDAP_USER_BASEDN = "ldap.user.search.baseDn";
/*     */   private static final String LDAP_GROUP_HOST_URL = "ldap.group.search.url";
/*     */   private static final String LDAP_GROUP_BASEDN = "ldap.group.search.baseDn";
/*     */   private static final String LDAP_SSL_USE_SSL = "ldap.ssl.useSSL";
/*     */   private static final String LDAP_SSL_TRUST_ALL = "ldap.ssl.trust.all";
/*     */   private static final String LDAP_POOL_MAX_ACTIVE = "ldap.pool.max.active";
/*     */   private static final String LDAP_POOL_MAX_TOTAL = "ldap.pool.max.total";
/*     */   private static final String LDAP_POOL_MAX_IDLE = "ldap.pool.max.idle";
/*     */   private static final String LDAP_POOL_MIN_IDLE = "ldap.pool.min.idle";
/*     */   private static final String LDAP_POOL_MAX_WAIT = "ldap.pool.max.wait";
/*     */   private static final String LDAP_POOL_TEST_ON_BORROW = "ldap.pool.test.on.borrow";
/*     */   private static final String LDAP_POOL_TEST_ON_RETURN = "ldap.pool.test.on.return";
/*     */   
/*     */   public static String getLdapHostUrl()
/*     */   {
/*  42 */     return getProperty("ldap.host.url");
/*     */   }
/*     */   
/*     */   public static String getLdapUserHostUrl() {
/*  46 */     return getProperty("ldap.host.url");
/*     */   }
/*     */   
/*     */   public static String getLdapGroupHostUrl()
/*     */   {
/*  51 */     return getProperty("ldap.group.search.url");
/*     */   }
/*     */   
/*     */   public static String getLdapAuthUserDnPattern() {
/*  55 */     return getProperty("ldap.auth.userDn");
/*     */   }
/*     */   
/*     */   public static String getLdapBindUserDn() {
/*  59 */     return getProperty("ldap.bind.userDn");
/*     */   }
/*     */   
/*     */   public static String getLdapBindUserPassword() {
/*  63 */     return getProperty("ldap.bind.password");
/*     */   }
/*     */   
/*     */   public static String getLdapUserSearchBaseDn() {
/*  67 */     return getProperty("ldap.user.search.baseDn");
/*     */   }
/*     */   
/*     */   public static String getLdapGroupSearchBaseDn() {
/*  71 */     return getProperty("ldap.group.search.baseDn");
/*     */   }
/*     */   
/*     */   public static boolean ifSslTrustAll() {
/*  75 */     return getBoolean("ldap.ssl.trust.all").booleanValue();
/*     */   }
/*     */   
/*     */   public static boolean ifUseSSL() {
/*  79 */     return getBoolean("ldap.ssl.useSSL").booleanValue();
/*     */   }
/*     */   
/*     */   public static boolean ifTestOnBorrow()
/*     */   {
/*  84 */     return getBoolean("ldap.pool.test.on.borrow").booleanValue();
/*     */   }
/*     */   
/*     */   public static boolean ifTestOnReturn()
/*     */   {
/*  89 */     return getBoolean("ldap.pool.test.on.return").booleanValue();
/*     */   }
/*     */   
/*     */   public static Integer getPoolMaxActive() {
/*  93 */     return getInteger("ldap.pool.max.active", Integer.valueOf(20));
/*     */   }
/*     */   
/*     */   public static Integer getPoolMaxTotal() {
/*  97 */     return getInteger("ldap.pool.max.total", Integer.valueOf(40));
/*     */   }
/*     */   
/*     */   public static Integer getPoolMaxIdle()
/*     */   {
/* 102 */     return getInteger("ldap.pool.max.idle", Integer.valueOf(10));
/*     */   }
/*     */   
/*     */   public static Integer getPoolMinIdle() {
/* 106 */     return getInteger("ldap.pool.min.idle", Integer.valueOf(5));
/*     */   }
/*     */   
/*     */   public static Integer getPoolMaxWait() {
/* 110 */     return getInteger("ldap.pool.max.wait", Integer.valueOf(2000));
/*     */   }
/*     */   
/*     */   private static Boolean getBoolean(String key)
/*     */   {
/* 115 */     String val = getProperty(key);
/* 116 */     if (val != null) {
/* 117 */       return Boolean.valueOf(Boolean.parseBoolean(val));
/*     */     }
/* 119 */     return Boolean.valueOf(false);
/*     */   }
/*     */   
/*     */   private static Integer getInteger(String key, Integer def)
/*     */   {
/* 124 */     String val = getProperty(key);
/* 125 */     if (val != null) {
/*     */       try {
/* 127 */         return Integer.decode(val);
/*     */       }
/*     */       catch (NumberFormatException localNumberFormatException) {}
/*     */     }
/*     */     
/*     */ 
/* 133 */     return def;
/*     */   }
/*     */   
/* 136 */   private static String[] configFiles = { "ldap-default.properties", "ldap.properties" };
/* 137 */   private static Properties properties = new Properties();
/*     */   
/* 139 */   private static String getProperty(String key) { if (properties.isEmpty()) {
/* 140 */       loadProperties();
/*     */     }
/* 142 */     return properties.getProperty(key);
/*     */   }
/*     */   
/*     */   public static synchronized void loadProperties() {
/* 146 */     if (properties.isEmpty()) {
/* 147 */       for (String configFile : configFiles) {
/* 148 */         URL url = LdapConfigHelper.class.getClassLoader().getResource(configFile);
/* 149 */         if (url != null) {
/*     */           try {
/* 151 */             Properties pro = new Properties();
/* 152 */             pro.load(url.openStream());
/* 153 */             properties.putAll(pro);
/*     */           } catch (IOException e) {
/* 155 */             LogUtil.getAppLog().error("", e);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static void reloadProperties()
/*     */   {
/* 164 */     properties.clear();
/* 165 */     loadProperties();
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\support\ldap\LdapConfigHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */