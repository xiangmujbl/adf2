/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.support.ldap;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.Map;
/*    */ import org.springframework.ldap.core.support.LdapContextSource;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LdapAuthContextSource
/*    */   extends LdapContextSource
/*    */ {
/*    */   public LdapAuthContextSource()
/*    */   {
/* 14 */     refresh();
/*    */   }
/*    */   
/*    */   public void refresh() {
/* 18 */     String url = LdapConfigHelper.getLdapHostUrl();
/* 19 */     setUrl(url);
/*    */     
/* 21 */     Map<String, Object> baseEnv = Maps.newHashMap();
/* 22 */     if ((url != null) && (LdapConfigHelper.ifUseSSL()) && (LdapConfigHelper.ifSslTrustAll())) {
/* 23 */       baseEnv.put("java.naming.ldap.factory.socket", "com.jnj.adf.grid.support.ssl.TrustAllSSLSocketFactory");
/*    */     }
/* 25 */     setBaseEnvironmentProperties(baseEnv);
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\support\ldap\LdapAuthContextSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */