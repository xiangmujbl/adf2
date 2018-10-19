/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.connect.impl;
/*     */ 
/*     */ import com.jnj.adf.grid.connect.ADFClient;
/*     */ import com.jnj.adf.grid.connect.ConnectService;
/*     */ import com.jnj.adf.grid.connect.domain.GridInfo;
/*     */ import com.jnj.adf.grid.manager.FederalManager;
/*     */ import com.jnj.adf.grid.manager.GridManager;
/*     */ import com.jnj.adf.grid.manager.federal.FederalHelper;
/*     */ import com.jnj.adf.grid.support.system.ADFConfigHelper;
/*     */ import java.util.List;
/*     */ import org.springframework.beans.factory.FactoryBean;
/*     */ import org.springframework.stereotype.Component;
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
/*     */ @Component
/*     */ public class ConnectServiceImpl
/*     */   implements ConnectService, FactoryBean<ConnectService>
/*     */ {
/*     */   public ConnectService getObject()
/*     */     throws Exception
/*     */   {
/*  42 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Class<?> getObjectType()
/*     */   {
/*  53 */     return ConnectService.class;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isSingleton()
/*     */   {
/*  59 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isConnect(String group)
/*     */   {
/*  65 */     ADFConfigHelper.initConfig();
/*  66 */     boolean masterConnected = FederalManager.getInstance().isConnected();
/*  67 */     if (!masterConnected)
/*  68 */       return false;
/*  69 */     if (masterConnected)
/*     */     {
/*  71 */       List<GridInfo> list = FederalManager.getInstance().listCachedGridData(group);
/*  72 */       if (list != null)
/*     */       {
/*  74 */         for (GridInfo grid : list)
/*     */         {
/*  76 */           boolean status = GridManager.getInstance().isConnect(grid.getGridName());
/*  77 */           if (status)
/*  78 */             return true;
/*     */         }
/*     */       }
/*     */     }
/*  82 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean connect(String enviroment, String group)
/*     */   {
/*  88 */     ADFConfigHelper.initConfig();
/*     */     
/*  90 */     FederalHelper.connectCm(group);
/*  91 */     return true;
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
/*     */ 
/*     */   public void connectGrid()
/*     */   {
/* 105 */     GridManager.getInstance().connectLocator(null);
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
/*     */   public void connectGrid(String locators)
/*     */   {
/* 118 */     GridManager.getInstance().connectLocator(locators);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void connectCm(String cms)
/*     */   {
/* 128 */     connectCm(cms, null);
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
/*     */   public void connectCm(String cms, String group)
/*     */   {
/* 141 */     ADFClient.connectCm(cms, group);
/*     */   }
/*     */   
/*     */   public void disconnectGird()
/*     */   {
/* 146 */     disconnectGird(null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void disconnectGird(String gridId)
/*     */   {
/* 157 */     GridManager.getInstance().disconnect(gridId);
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\connect\impl\ConnectServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */