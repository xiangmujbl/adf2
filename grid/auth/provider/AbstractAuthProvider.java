/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.auth.provider;
/*    */ 
/*    */ import com.jnj.adf.grid.support.context.ADFRuntime;
/*    */ import com.jnj.adf.grid.support.system.ADFConfigHelper.ITEMS;
/*    */ import com.jnj.adf.grid.utils.LogUtil;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ public abstract class AbstractAuthProvider implements IAuthProvider
/*    */ {
/*    */   public static enum AuthMode
/*    */   {
/* 12 */     local,  remote,  ldap;
/*    */     
/*    */     private AuthMode() {} }
/* 15 */   protected boolean enabled = false;
/*    */   
/* 17 */   protected int priority = -1;
/*    */   
/* 19 */   private static String[] supportModes = null;
/*    */   
/* 21 */   static { LogUtil.getCoreLog().debug("AbstractAuthProvider start to calculate auth.mode");
/* 22 */     String supprtModeStr = com.jnj.adf.grid.support.system.ADFConfigHelper.getConfig(ADFConfigHelper.ITEMS.AUTH_MODE);
/* 23 */     if (org.apache.commons.lang3.StringUtils.isEmpty(supprtModeStr)) {
/* 24 */       supprtModeStr = calculateSupportModes();
/* 25 */       LogUtil.getCoreLog().info("AbstractAuthProvider calculate auth.mode by runtime. \n security.auth.mode={}", new Object[] { supprtModeStr });
/*    */     }
/* 27 */     LogUtil.getCoreLog().debug("AbstractAuthProvider auth.mode: {}", new Object[] { supprtModeStr });
/* 28 */     supportModes = supprtModeStr.split(",");
/*    */   }
/*    */   
/*    */   protected void initProviderContext(AuthMode authMode)
/*    */   {
/* 33 */     int i = 0; for (int len = supportModes.length; i < len; i++) {
/* 34 */       String mode = supportModes[i];
/* 35 */       if (authMode.name().equalsIgnoreCase(mode)) {
/* 36 */         this.enabled = true;
/* 37 */         this.priority = i;
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   private static String calculateSupportModes()
/*    */   {
/* 47 */     boolean asMaster = com.jnj.adf.grid.support.system.ADFConfigHelper.getBoolean(ADFConfigHelper.ITEMS.AS_MASTER_GRID);
/* 48 */     boolean serverSide = ADFRuntime.getRuntime().isServerSide();
/* 49 */     if ((serverSide) && (asMaster))
/* 50 */       return "local,ldap";
/* 51 */     if (serverSide) {
/* 52 */       return "remote,ldap";
/*    */     }
/* 54 */     return "remote";
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean enabled()
/*    */   {
/* 60 */     return this.enabled;
/*    */   }
/*    */   
/*    */   public int priority()
/*    */   {
/* 65 */     return this.priority;
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\auth\provider\AbstractAuthProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */