/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.manager;
/*     */ 
/*     */ import com.gemstone.gemfire.cache.client.Pool;
/*     */ import com.gemstone.gemfire.cache.client.PoolManager;
/*     */ import com.gemstone.gemfire.cache.client.internal.PoolImpl;
/*     */ import com.jnj.adf.grid.client.api.support.ADFServiceContext;
/*     */ import com.jnj.adf.grid.common.ADFException;
/*     */ import com.jnj.adf.grid.connect.ConnectException;
/*     */ import com.jnj.adf.grid.connect.GridConnection;
/*     */ import com.jnj.adf.grid.connect.GridConnectionManager;
/*     */ import com.jnj.adf.grid.connect.domain.GridInfo;
/*     */ import com.jnj.adf.grid.support.system.ADFConfigHelper;
/*     */ import com.jnj.adf.grid.support.system.ADFConfigHelper.ITEMS;
/*     */ import com.jnj.adf.grid.utils.LogUtil;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.apache.logging.log4j.Logger;
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
/*     */ public class GridManager
/*     */ {
/*  42 */   private static final GridManager INST = new GridManager();
/*     */   
/*     */   public static final GridManager getInstance()
/*     */   {
/*  46 */     return INST;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static final void initClientToken() {}
/*     */   
/*     */ 
/*     */   public synchronized GridInfo connectById(String gridId)
/*     */   {
/*  56 */     if (isConnect(gridId))
/*     */     {
/*  58 */       GridInfo info = GridConnectionManager.find(gridId).fetchGridInfo();
/*     */       
/*  60 */       return info;
/*     */     }
/*  62 */     GridInfo info = FederalManager.getInstance().getCachedGridInfo(gridId);
/*  63 */     if (info == null)
/*     */     {
/*  65 */       throw new ConnectException("No grid info for id:" + gridId);
/*     */     }
/*  67 */     GridConnection conn = doConnection(info);
/*  68 */     if ((conn != null) && (conn.isConnected()))
/*     */     {
/*  70 */       GridConnectionManager.register(info.getGridName(), conn);
/*  71 */       RegionBuilder.newBuilder().setGridName(info.getGridName()).setPool(conn.getPool()).create();
/*  72 */       FederalManager.getInstance().cacheGrid(info);
/*  73 */       ADFServiceContext.recordGridInfo(info);
/*  74 */       return info;
/*     */     }
/*     */     
/*     */ 
/*  78 */     return info;
/*     */   }
/*     */   
/*     */ 
/*     */   private GridConnection doConnection(GridInfo gi)
/*     */   {
/*     */     
/*  85 */     if ((StringUtils.isNotEmpty(gi.getGridName())) && (isConnect(gi.getGridName())))
/*     */     {
/*  87 */       return null;
/*     */     }
/*  89 */     ADFConfigHelper.initConfig();
/*  90 */     GridConnection conn = ADFConnectFactory.createConnection(gi, ADFConnectFactory.ConnTypes.Client);
/*  91 */     boolean success = conn.connect();
/*  92 */     if (success)
/*     */     {
/*  94 */       return conn;
/*     */     }
/*     */     
/*     */ 
/*  98 */     throw new ADFException("Connect grid failed. gridName:" + gi.getGridName() + " locators:" + gi.getLocators());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public GridInfo connectLocator()
/*     */   {
/* 105 */     return connectLocator(null);
/*     */   }
/*     */   
/*     */   public GridInfo connectLocator(String locators)
/*     */   {
/* 110 */     initClientToken();
/* 111 */     GridInfo gridInfo = new GridInfo(null);
/* 112 */     gridInfo.setLocators(locators);
/* 113 */     if (StringUtils.isEmpty(locators))
/*     */     {
/* 115 */       locators = ADFConfigHelper.getConfig(ITEMS.LOCATORS);
/*     */     }
/* 117 */     locators = ADFConfigHelper.processLocalhost(locators);
/* 118 */     gridInfo.setLocators(locators);
/* 119 */     GridConnection conn = doConnection(gridInfo);
/* 120 */     if (conn != null)
/*     */     {
/* 122 */       GridConnectionManager.register(gridInfo.getGridName(), conn);
/* 123 */       RegionBuilder.newBuilder().setGridName(gridInfo.getGridName()).setPool(conn.getPool()).create();
/* 124 */       FederalManager.getInstance().cacheGrid(gridInfo);
/* 125 */       ADFServiceContext.recordGridInfo(gridInfo);
/* 126 */       return gridInfo;
/*     */     }
/*     */     
/*     */ 
/* 130 */     return gridInfo;
/*     */   }
/*     */   
/*     */ 
/*     */   public void disconnect()
/*     */   {
/* 136 */     disconnect(ADFServiceContext.defaultGrid());
/*     */   }
/*     */   
/*     */   public void disconnect(String gridId)
/*     */   {
/* 141 */     GridConnection conn = GridConnectionManager.find(gridId);
/* 142 */     conn.close();
/* 143 */     GridConnectionManager.unregister(gridId);
/*     */   }
/*     */   
/*     */   public Pool getMasterPool()
/*     */   {
/* 148 */     String masterPoolName = GridConnectionManager.getMasterPoolName();
/* 149 */     if (StringUtils.isNotEmpty(masterPoolName))
/*     */     {
/* 151 */       return PoolManager.find(masterPoolName);
/*     */     }
/* 153 */     return null;
/*     */   }
/*     */   
/*     */   public Pool getGemFirePool()
/*     */   {
/* 158 */     String grid = ADFServiceContext.defaultGrid();
/* 159 */     return getGemFirePool(grid);
/*     */   }
/*     */   
/*     */   public Pool getGemFirePool(String gridId)
/*     */   {
/* 164 */     if (StringUtils.isEmpty(gridId))
/*     */     {
/* 166 */       gridId = ADFServiceContext.defaultGrid();
/*     */     }
/* 168 */     GridConnection conn = GridConnectionManager.find(gridId);
/* 169 */     if ((conn != null) && (conn.isConnected()))
/*     */     {
/* 171 */       return PoolManager.find(conn.getPoolName());
/*     */     }
/*     */     
/*     */ 
/* 175 */     GridInfo info = connectById(gridId);
/* 176 */     if (info == null)
/*     */     {
/* 178 */       throw new ADFException("Connect to grid:" + gridId + " failed.");
/*     */     }
/* 180 */     conn = GridConnectionManager.find(gridId);
/* 181 */     if (conn == null)
/* 182 */       throw new ADFException("Connect to grid:" + gridId + " failed.");
/* 183 */     return PoolManager.find(conn.getPoolName());
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isConnect(String gridId)
/*     */   {
/* 189 */     return GridConnectionManager.isConnect(gridId);
/*     */   }
/*     */   
/*     */   public static final void dumpPoolConnect()
/*     */   {
/*     */     try
/*     */     {
/* 196 */       Field f = GridConnectionManager.class.getDeclaredField("connectedPoolCacheMap");
/* 197 */       f.setAccessible(true);
/* 198 */       Map<String, GridConnection> mm = (Map)f.get(GridConnectionManager.class);
/* 199 */       for (GridConnection conn : mm.values())
/*     */       {
/* 201 */         PoolImpl pool = (PoolImpl)conn.getPool();
/* 202 */         LogUtil.getCoreLog().info("max:{},count:{},min:{},stat:{}", new Object[] { Integer.valueOf(pool.getMaxConnections()), Integer.valueOf(pool.getConnectionCount()), 
/* 203 */           Integer.valueOf(pool.getMinConnections()), pool.getStats().toString() });
/*     */       }
/* 205 */       f.setAccessible(false);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 209 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\manager\GridManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */