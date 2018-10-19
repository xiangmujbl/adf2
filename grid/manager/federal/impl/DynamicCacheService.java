/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.manager.federal.impl;
/*    */ 
/*    */ import com.jnj.adf.grid.client.api.support.ADFServiceContext;
/*    */ import com.jnj.adf.grid.connect.domain.GridInfo;
/*    */ import com.jnj.adf.grid.connect.domain.GridInfoAndStatus;
/*    */ import com.jnj.adf.grid.manager.federal.CacheService;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DynamicCacheService
/*    */   implements CacheService
/*    */ {
/* 18 */   private static final DynamicCacheService INST = new DynamicCacheService();
/* 19 */   private static FederalMasterService masterService = FederalMasterService.getInstance();
/*    */   
/*    */   public static final DynamicCacheService getInstance()
/*    */   {
/* 23 */     return INST;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void cacheGrid(GridInfo info) {}
/*    */   
/*    */ 
/*    */ 
/*    */   public GridInfo getGridInfo()
/*    */   {
/* 35 */     return getGridInfo(ADFServiceContext.defaultGrid());
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public GridInfo getGridInfo(String gridName)
/*    */   {
/* 47 */     return masterService.getGridData(gridName);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void updateCache(GridInfo info) {}
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void removeCache(GridInfo info) {}
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public List<String> cacheGroup(String group)
/*    */   {
/* 66 */     return masterService.listGridName(group);
/*    */   }
/*    */   
/*    */ 
/*    */   public List<GridInfo> listGridData(String group)
/*    */   {
/* 72 */     return masterService.listGridData(group);
/*    */   }
/*    */   
/*    */ 
/*    */   public List<String> listGridName(String group)
/*    */   {
/* 78 */     return masterService.listGridName(group);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public GridInfoAndStatus getGridAndStatus(String gridName)
/*    */   {
/* 89 */     return masterService.getGridAndStatus(gridName);
/*    */   }
/*    */   
/*    */ 
/*    */   public List<GridInfoAndStatus> listGridAndStatus(String group)
/*    */   {
/* 95 */     return masterService.listGridAndStatus(group);
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\manager\federal\impl\DynamicCacheService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */