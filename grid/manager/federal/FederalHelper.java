/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.manager.federal;
/*    */ 
/*    */ import com.jnj.adf.grid.connect.ADFClient;
/*    */ import com.jnj.adf.grid.support.system.ADFConfigHelper;
/*    */ import com.jnj.adf.grid.support.system.ADFConfigHelper.ITEMS;
/*    */ import com.jnj.adf.grid.utils.LogUtil;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FederalHelper
/*    */ {
/*    */   public static void connectCm(String group)
/*    */   {
/* 18 */     String serverAddress = ADFConfigHelper.getProperty(ITEMS.NAMING_SERVER);
/* 19 */     LogUtil.getCoreLog().info("Connecting to master using naming-server:{}", new Object[] { serverAddress });
/* 20 */     ADFClient.connectCm(serverAddress, group);
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\manager\federal\FederalHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */