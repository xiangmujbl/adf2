/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.support.ldap;
/*    */ 
/*    */ import org.springframework.context.annotation.Bean;
/*    */ import org.springframework.context.annotation.Configuration;
/*    */ import org.springframework.ldap.pool.factory.PoolingContextSource;
/*    */ import org.springframework.ldap.pool.validation.DirContextValidator;
/*    */ 
/*    */ @Configuration
/*    */ public class LdapBeanConfiguration
/*    */ {
/*    */   @Bean
/*    */   public LdapUserContextSource ldapUserContextSource()
/*    */   {
/* 14 */     String url = LdapConfigHelper.getLdapUserHostUrl();
/* 15 */     return new LdapUserContextSource(url);
/*    */   }
/*    */   
/*    */   @Bean
/*    */   public PoolingContextSource poolingContextSource() {
/* 20 */     LdapPoolingContextSource poolingContextSource = new LdapPoolingContextSource(ldapUserContextSource());
/* 21 */     poolingContextSource.setDirContextValidator(defaultDirContextValidator());
/* 22 */     return poolingContextSource;
/*    */   }
/*    */   
/*    */   @Bean
/*    */   public LdapUserTemplate ldapUserTemplate() {
/* 27 */     LdapUserTemplate lut = new LdapUserTemplate(poolingContextSource());
/* 28 */     return lut;
/*    */   }
/*    */   
/*    */   @Bean
/*    */   public LdapUserContextSource ldapGroupContextSource()
/*    */   {
/* 34 */     String url = LdapConfigHelper.getLdapGroupHostUrl();
/* 35 */     return new LdapUserContextSource(url);
/*    */   }
/*    */   
/*    */   @Bean
/*    */   public PoolingContextSource poolingGroupContextSource()
/*    */   {
/* 41 */     LdapPoolingContextSource poolingContextSource = new LdapPoolingContextSource(ldapGroupContextSource());
/* 42 */     poolingContextSource.setDirContextValidator(defaultDirContextValidator());
/* 43 */     return poolingContextSource;
/*    */   }
/*    */   
/*    */   @Bean
/*    */   public LdapUserTemplate ldapGroupTemplate() {
/* 48 */     LdapUserTemplate lut = new LdapUserTemplate(poolingGroupContextSource());
/* 49 */     return lut;
/*    */   }
/*    */   
/*    */   @Bean
/*    */   public DirContextValidator defaultDirContextValidator() {
/* 54 */     return new org.springframework.ldap.pool.validation.DefaultDirContextValidator();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   @Bean
/*    */   public LdapAuthContextSource ldapAuthContextSource()
/*    */   {
/* 65 */     return new LdapAuthContextSource();
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\support\ldap\LdapBeanConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */