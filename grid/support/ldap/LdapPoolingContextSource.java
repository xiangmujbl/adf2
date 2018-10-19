/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.support.ldap;
/*    */ 
/*    */ import org.springframework.ldap.core.support.LdapContextSource;
/*    */ import org.springframework.ldap.pool.factory.PoolingContextSource;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LdapPoolingContextSource
/*    */   extends PoolingContextSource
/*    */ {
/*    */   public LdapPoolingContextSource() {}
/*    */   
/*    */   public LdapPoolingContextSource(LdapContextSource contextSource)
/*    */   {
/* 17 */     setContextSource(contextSource);
/*    */     
/* 19 */     setMaxActive(LdapConfigHelper.getPoolMaxActive().intValue());
/* 20 */     setMaxIdle(LdapConfigHelper.getPoolMaxIdle().intValue());
/* 21 */     setMaxTotal(LdapConfigHelper.getPoolMaxTotal().intValue());
/* 22 */     setMaxWait(LdapConfigHelper.getPoolMaxWait().intValue());
/* 23 */     setMinIdle(LdapConfigHelper.getPoolMinIdle().intValue());
/*    */     
/* 25 */     setTestOnBorrow(LdapConfigHelper.ifTestOnBorrow());
/* 26 */     setTestWhileIdle(LdapConfigHelper.ifTestOnReturn());
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void refresh()
/*    */   {
/* 37 */     setMaxActive(LdapConfigHelper.getPoolMaxActive().intValue());
/* 38 */     setMaxIdle(LdapConfigHelper.getPoolMaxIdle().intValue());
/* 39 */     setMaxTotal(LdapConfigHelper.getPoolMaxTotal().intValue());
/* 40 */     setMaxWait(LdapConfigHelper.getPoolMaxWait().intValue());
/* 41 */     setMinIdle(LdapConfigHelper.getPoolMinIdle().intValue());
/*    */     
/* 43 */     setTestOnBorrow(LdapConfigHelper.ifTestOnBorrow());
/* 44 */     setTestWhileIdle(LdapConfigHelper.ifTestOnReturn());
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\support\ldap\LdapPoolingContextSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */