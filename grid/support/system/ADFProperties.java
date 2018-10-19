/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.support.system;
/*    */ 
/*    */ import java.util.Properties;
/*    */ import org.springframework.beans.factory.FactoryBean;
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
/*    */ 
/*    */ public class ADFProperties
/*    */   implements FactoryBean<Properties>
/*    */ {
/*    */   public Properties getObject()
/*    */     throws Exception
/*    */   {
/* 32 */     return ADFConfigHelper.serverConfig;
/*    */   }
/*    */   
/*    */ 
/*    */   public Class<?> getObjectType()
/*    */   {
/* 38 */     return Properties.class;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean isSingleton()
/*    */   {
/* 44 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\support\system\ADFProperties.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */