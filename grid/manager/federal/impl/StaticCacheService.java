/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.manager.federal.impl;
/*     */ 
/*     */ import com.jnj.adf.grid.connect.domain.GridInfo;
/*     */ import com.jnj.adf.grid.connect.domain.GridInfoAndStatus;
/*     */ import com.jnj.adf.grid.connect.domain.GridStatus;
/*     */ import com.jnj.adf.grid.manager.GridManager;
/*     */ import com.jnj.adf.grid.manager.federal.CacheService;
/*     */ import com.jnj.adf.grid.utils.LogUtil;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class StaticCacheService
/*     */   implements CacheService
/*     */ {
/*  19 */   private static final StaticCacheService INST = new StaticCacheService();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  27 */   private static final Map<String, List<String>> cachedGroupMap = new ConcurrentHashMap();
/*  28 */   private static final Map<String, GridInfo> cachedGridInfoMap = new ConcurrentHashMap();
/*  29 */   private static final List<GridInfo> cachedGridInfoList = new ArrayList();
/*     */   
/*     */ 
/*     */   public static final StaticCacheService getInstance()
/*     */   {
/*  34 */     return INST;
/*     */   }
/*     */   
/*     */   public List<String> listGridName(String group)
/*     */   {
/*  39 */     List<String> gridNames = (List)cachedGroupMap.get(group);
/*  40 */     LogUtil.getCoreLog().debug("Get grid names from static cache is {}, group is {}", new Object[] { gridNames, group });
/*  41 */     return gridNames;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void cacheGrid(GridInfo info)
/*     */   {
/*  51 */     if (info != null)
/*     */     {
/*  53 */       cachedGridInfoMap.put(info.getGridName(), info);
/*  54 */       cachedGridInfoList.add(info);
/*  55 */       LogUtil.getCoreLog().debug("After cache grid to static cache, cachedGridInfoMap is {}", new Object[] { cachedGridInfoMap });
/*     */     }
/*     */   }
/*     */   
/*     */   public GridInfo getGridInfo()
/*     */   {
/*  61 */     if (cachedGridInfoList.size() > 0)
/*     */     {
/*  63 */       LogUtil.getCoreLog().debug("Get first cache from static cache is {}", new Object[] { cachedGridInfoList.get(0) });
/*  64 */       return (GridInfo)cachedGridInfoList.get(0);
/*     */     }
/*  66 */     return null;
/*     */   }
/*     */   
/*     */   public GridInfo getGridInfo(String gridName)
/*     */   {
/*  71 */     GridInfo info = (GridInfo)cachedGridInfoMap.get(gridName);
/*  72 */     if (info != null)
/*     */     {
/*  74 */       return info;
/*     */     }
/*  76 */     LogUtil.getCoreLog().debug("Get null of grid:{} from static cache, cache grid info fisrt.", new Object[] { gridName });
/*  77 */     info = cacheGridInfo(gridName);
/*  78 */     return info;
/*     */   }
/*     */   
/*     */   public void updateCache(GridInfo info)
/*     */   {
/*  83 */     String gridName = info.getGridName();
/*  84 */     GridInfo oldInfo = (GridInfo)cachedGridInfoMap.get(gridName);
/*  85 */     cachedGridInfoList.remove(oldInfo);
/*     */     
/*  87 */     cachedGridInfoMap.put(gridName, info);
/*  88 */     cachedGridInfoList.add(info);
/*  89 */     LogUtil.getCoreLog().debug("After update cache, cached grid info map from static cache is {}", new Object[] { cachedGridInfoMap });
/*     */   }
/*     */   
/*     */   public void removeCache(GridInfo info)
/*     */   {
/*  94 */     String gridName = info.getGridName();
/*  95 */     GridInfo oldInfo = (GridInfo)cachedGridInfoMap.get(gridName);
/*  96 */     cachedGridInfoList.remove(oldInfo);
/*  97 */     cachedGridInfoMap.remove(gridName);
/*  98 */     LogUtil.getCoreLog().debug("After remove cache:{}, cached grid info map from static cache is {}", new Object[] { info, cachedGridInfoMap });
/*     */   }
/*     */   
/*     */ 
/*     */   private GridInfo cacheGridInfo(String gridName)
/*     */   {
/* 104 */     if (cachedGridInfoMap.containsKey(gridName))
/* 105 */       return (GridInfo)cachedGridInfoMap.get(gridName);
/* 106 */     GridInfo data = FederalMasterService.getInstance().getGridData(gridName);
/* 107 */     LogUtil.getCoreLog().debug("Get grid:{}'s info:{}", new Object[] { gridName, data });
/* 108 */     if (data != null)
/*     */     {
/* 110 */       cacheGrid(data);
/* 111 */       return data;
/*     */     }
/* 113 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<GridInfo> listGridData(String group)
/*     */   {
/* 125 */     List<GridInfo> list = new ArrayList();
/* 126 */     if (StringUtils.isNotBlank(group))
/*     */     {
/* 128 */       List<String> grids = cacheGroup(group);
/* 129 */       for (String grid : grids)
/*     */       {
/* 131 */         GridInfo item = getGridInfo(grid);
/* 132 */         if (item != null)
/* 133 */           list.add(cachedGridInfoMap.get(grid));
/*     */       }
/*     */     }
/* 136 */     LogUtil.getCoreLog().debug("List grid data of group:{} is {}", new Object[] { group, list });
/* 137 */     return list;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<String> cacheGroup(String group)
/*     */   {
/* 146 */     if (cachedGroupMap.containsKey(group))
/* 147 */       return (List)cachedGroupMap.get(group);
/* 148 */     List<String> gridNames = FederalMasterService.getInstance().listGridName(group);
/* 149 */     LogUtil.getCoreLog().debug("Get grids {} from group {}", new Object[] { gridNames, group });
/* 150 */     if ((gridNames == null) || (gridNames.size() <= 0)) {
/* 151 */       LogUtil.getCoreLog().error("Group {} has no grid");
/*     */     }
/* 153 */     for (String gridName : gridNames)
/*     */     {
/* 155 */       cacheGridInfo(gridName);
/*     */     }
/* 157 */     cachedGroupMap.put(group, gridNames);
/* 158 */     LogUtil.getCoreLog().debug("After cache group:{}, cache group map:{}", new Object[] { group, cachedGroupMap });
/* 159 */     return gridNames;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public GridInfoAndStatus getGridAndStatus(String gridName)
/*     */   {
/* 169 */     GridInfoAndStatus entity = new GridInfoAndStatus();
/* 170 */     entity.setInfo(getGridInfo(gridName));
/* 171 */     boolean isConnect = GridManager.getInstance().isConnect(gridName);
/* 172 */     LogUtil.getCoreLog().debug("Get gridInfo:{} .Connect to grid:{} is {}", new Object[] { entity.getInfo(), gridName, Boolean.valueOf(isConnect) });
/* 173 */     if (isConnect)
/*     */     {
/* 175 */       entity.setStatus(GridStatus.clusterReady);
/*     */     }
/*     */     else
/*     */     {
/* 179 */       entity.setStatus(GridStatus.offline);
/*     */     }
/* 181 */     return entity;
/*     */   }
/*     */   
/*     */   public List<GridInfoAndStatus> listGridAndStatus(String group)
/*     */   {
/* 186 */     List<GridInfoAndStatus> list = new ArrayList();
/* 187 */     List<String> grids = listGridName(group);
/* 188 */     for (String grid : grids)
/*     */     {
/* 190 */       GridInfoAndStatus item = getGridAndStatus(grid);
/* 191 */       list.add(item);
/*     */     }
/* 193 */     LogUtil.getCoreLog().debug("List grid and status from group:{} size is {}", new Object[] { group, Integer.valueOf(list.size()) });
/* 194 */     return list;
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\manager\federal\impl\StaticCacheService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */