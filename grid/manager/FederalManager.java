/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.manager;
/*     */ 
/*     */ import com.jnj.adf.grid.connect.domain.GridInfo;
/*     */ import com.jnj.adf.grid.connect.domain.GridInfoAndStatus;
/*     */ import com.jnj.adf.grid.connect.domain.GridStatus;
/*     */ import com.jnj.adf.grid.manager.federal.CacheService;
/*     */ import com.jnj.adf.grid.manager.federal.impl.DynamicCacheService;
/*     */ import com.jnj.adf.grid.manager.federal.impl.FederalMasterService;
/*     */ import com.jnj.adf.grid.manager.federal.impl.StaticCacheService;
/*     */ import com.jnj.adf.grid.support.hybrid.ADFHybridCacheManager;
/*     */ import com.jnj.adf.grid.support.system.ADFConfigHelper;
/*     */ import com.jnj.adf.grid.support.system.ADFConfigHelper.ITEMS;
/*     */ import com.jnj.adf.grid.utils.LogUtil;
/*     */ import java.util.List;
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
/*     */ public class FederalManager
/*     */ {
/*  36 */   private static final FederalManager INST = new FederalManager();
/*  37 */   private static final FederalMasterService masterService = FederalMasterService.getInstance();
/*     */   private static CacheService cacheService;
/*     */   
/*     */   public static final FederalManager getInstance()
/*     */   {
/*  42 */     return INST;
/*     */   }
/*     */   
/*     */ 
/*     */   public FederalManager()
/*     */   {
/*  48 */     setCacheType();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private void setCacheType()
/*     */   {
/*  56 */     String prop = ADFConfigHelper.getProperty(ITEMS.CACHE_REAL_TIME);
/*  57 */     boolean isRealTimeCache = true;
/*  58 */     if (StringUtils.isNotEmpty(prop))
/*     */     {
/*  60 */       isRealTimeCache = Boolean.parseBoolean(prop);
/*     */     }
/*  62 */     if (isRealTimeCache)
/*     */     {
/*  64 */       LogUtil.getCoreLog().debug("Choose dynamic cache that always get real time infomation.");
/*  65 */       cacheService = DynamicCacheService.getInstance();
/*     */     }
/*     */     else
/*     */     {
/*  69 */       LogUtil.getCoreLog().debug("Choose static cache that get infomation from cache.");
/*  70 */       cacheService = StaticCacheService.getInstance();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void cacheGroup(String group)
/*     */   {
/*  83 */     List<String> gridNames = cacheService.cacheGroup(group);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<GridInfo> listCachedGridData(String group)
/*     */   {
/* 103 */     return cacheService.listGridData(group);
/*     */   }
/*     */   
/*     */   public List<String> listCachedGridName(String group)
/*     */   {
/* 108 */     return cacheService.listGridName(group);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void cacheGrid(GridInfo info)
/*     */   {
/* 118 */     cacheService.cacheGrid(info);
/*     */   }
/*     */   
/*     */   public void updateCache(GridInfo info)
/*     */   {
/* 123 */     cacheService.updateCache(info);
/*     */   }
/*     */   
/*     */   public void removeCache(GridInfo info)
/*     */   {
/* 128 */     cacheService.removeCache(info);
/*     */   }
/*     */   
/*     */   public GridInfo getCachedGridInfo()
/*     */   {
/* 133 */     return cacheService.getGridInfo();
/*     */   }
/*     */   
/*     */   public GridInfo getCachedGridInfo(String gridName)
/*     */   {
/* 138 */     return cacheService.getGridInfo(gridName);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public GridInfoAndStatus getCachedGridAndStatus(String gridName)
/*     */   {
/* 148 */     return cacheService.getGridAndStatus(gridName);
/*     */   }
/*     */   
/*     */   public List<GridInfoAndStatus> listCachedGridAndStatus(String group)
/*     */   {
/* 153 */     return cacheService.listGridAndStatus(group);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void connect(String address)
/*     */   {
/* 164 */     if (!isConnected())
/*     */     {
/* 166 */       LogUtil.getCoreLog().debug("Start connecting to master:{} .", new Object[] { address });
/* 167 */       ADFConfigHelper.setProperty(ITEMS.NAMING_SERVER, address);
/* 168 */       ADFHybridCacheManager.getInstance().initDefaultConnection();
/* 169 */       LogUtil.getCoreLog().debug("Connected to master grid.");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isConnected()
/*     */   {
/* 180 */     return ADFHybridCacheManager.getInstance().isMasterConnected();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void connectAndCacheGroup(String address, String group)
/*     */   {
/* 192 */     connect(address);
/*     */     
/* 194 */     if ((isConnected()) && (StringUtils.isNotEmpty(group)))
/*     */     {
/* 196 */       cacheGroup(group);
/*     */     }
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
/*     */   public boolean addGroup(String groupName)
/*     */   {
/* 225 */     return masterService.addGroup(groupName);
/*     */   }
/*     */   
/*     */   public boolean removeGroup(String groupName)
/*     */   {
/* 230 */     return masterService.removeGroup(groupName);
/*     */   }
/*     */   
/*     */   public boolean updateGroup(String groupName, GridInfo data)
/*     */   {
/* 235 */     return masterService.updateGroup(groupName, data);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<String> listGroupName()
/*     */   {
/* 246 */     return masterService.listGroupName();
/*     */   }
/*     */   
/*     */   public boolean updateGrid(String gridName, GridInfo data)
/*     */   {
/* 251 */     return masterService.updateGrid(gridName, data);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void disconnect()
/*     */   {
/* 261 */     LogUtil.getCoreLog().debug("Master magager don't disconnect to master grid, do nothing!");
/*     */   }
/*     */   
/*     */   public void setGridStatus(String gridName, GridStatus status)
/*     */   {
/* 266 */     masterService.setGridStatus(gridName, status);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void connectGrids(String group)
/*     */   {
/* 276 */     List<String> grids = masterService.listGridName(group);
/* 277 */     if ((grids != null) && (grids.size() == 1))
/*     */     {
/* 279 */       GridManager.getInstance().connectById((String)grids.get(0));
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\manager\FederalManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */