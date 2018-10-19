/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.support.ldap;
/*    */ 
/*    */ import org.springframework.ldap.core.ContextSource;
/*    */ import org.springframework.ldap.core.LdapTemplate;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LdapUserTemplate
/*    */   extends LdapTemplate
/*    */ {
/*    */   public LdapUserTemplate() {}
/*    */   
/*    */   public LdapUserTemplate(ContextSource contextSource)
/*    */   {
/* 15 */     super(contextSource);
/* 16 */     setIgnorePartialResultException(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\support\ldap\LdapUserTemplate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */