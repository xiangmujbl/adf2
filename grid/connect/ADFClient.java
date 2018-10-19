/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.connect;
/*     */ 
/*     */ import com.jnj.adf.grid.auth.AuthTokenManager;
/*     */ import com.jnj.adf.grid.auth.session.AuthInternalSession;
/*     */ import com.jnj.adf.grid.connect.domain.GridInfo;
/*     */ import com.jnj.adf.grid.manager.FederalManager;
/*     */ import com.jnj.adf.grid.manager.GridManager;
/*     */ import com.jnj.adf.grid.support.system.ADFConfigHelper;
/*     */ import com.jnj.adf.grid.support.system.ADFConfigHelper.ITEMS;
/*     */ import org.apache.commons.lang3.StringUtils;
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
/*     */ 
/*     */ public class ADFClient
/*     */ {
/*     */   public static final void connectGrid()
/*     */   {
/*  40 */     ADFConfigHelper.initConfig();
/*  41 */     String locators = ADFConfigHelper.getConfig(ITEMS.LOCATORS);
/*  42 */     connectGrid(locators);
/*     */   }
/*     */   
/*     */   public static final boolean login(String name, String passwd)
/*     */   {
/*  47 */     String token = AuthTokenManager.login(name, passwd).getToken();
/*  48 */     if (StringUtils.isEmpty(token))
/*     */     {
/*  50 */       return false;
/*     */     }
/*  52 */     return true;
/*     */   }
/*     */   
/*     */   public static final void useToken(String token)
/*     */   {
/*  57 */     AuthTokenManager.useToken(token);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static final boolean connectGrid(String locators)
/*     */   {
/*  70 */     GridInfo gi = GridManager.getInstance().connectLocator(locators);
/*  71 */     return (gi != null) && (gi.isConnected());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static final boolean connectGridById(String gridId)
/*     */   {
/*  82 */     GridInfo gi = GridManager.getInstance().connectById(gridId);
/*  83 */     return (gi != null) && (gi.isConnected());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static final void connectCm(String cms)
/*     */   {
/*  93 */     connectCm(cms, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static final void connectCm(String serverAddress, String group)
/*     */   {
/* 105 */     FederalManager.getInstance().connectAndCacheGroup(serverAddress, group);
/* 106 */     boolean isConnected = FederalManager.getInstance().isConnected();
/* 107 */     if ((StringUtils.isNotEmpty(group)) && (isConnected))
/*     */     {
/* 109 */       FederalManager.getInstance().connectGrids(group);
/*     */     }
/*     */   }
/*     */   
/*     */   public static final boolean disconnectGird()
/*     */   {
/* 115 */     GridManager.getInstance().disconnect();
/* 116 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static final boolean disconnectGird(String gridId)
/*     */   {
/* 127 */     GridManager.getInstance().disconnect(gridId);
/* 128 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\connect\ADFClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */