/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.support.context;
/*    */ 
/*    */ import com.jnj.adf.grid.support.gemfire.ADFTransactionManager;
/*    */ import org.springframework.context.annotation.Bean;
/*    */ import org.springframework.context.annotation.Configuration;
/*    */ import org.springframework.transaction.PlatformTransactionManager;
/*    */ import org.springframework.transaction.annotation.EnableTransactionManagement;
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
/*    */ 
/*    */ 
/*    */ @Configuration
/*    */ @EnableTransactionManagement
/*    */ public class ADFTxConfig
/*    */ {
/*    */   @Bean(name={"adf-tx"})
/*    */   public PlatformTransactionManager txManager()
/*    */   {
/* 35 */     return new ADFTransactionManager();
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\support\context\ADFTxConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */