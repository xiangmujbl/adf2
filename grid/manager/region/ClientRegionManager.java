/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.manager.region;
/*    */ 
/*    */ import com.jnj.adf.grid.kit.ADFRegisterFactory;
/*    */ import com.jnj.adf.grid.kit.IRegionPathGetter;
/*    */ import com.jnj.adf.grid.kit.RegionInfo;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import java.util.Collections;
/*    */ import java.util.HashSet;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import java.util.concurrent.ConcurrentHashMap;
/*    */ import org.apache.commons.lang3.StringUtils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ClientRegionManager
/*    */ {
/* 22 */   private static final Map<String, String> gridByPathMap = new ConcurrentHashMap();
/* 23 */   private static final Map<String, Set<String>> pathOnGridMap = new ConcurrentHashMap();
/*    */   
/*    */   public static synchronized void addLocalPath(String fullPath, String gridId)
/*    */   {
/* 27 */     gridByPathMap.put(fullPath, gridId);
/* 28 */     Set<String> sets = (Set)pathOnGridMap.get(gridId);
/* 29 */     if (sets == null)
/*    */     {
/* 31 */       sets = new HashSet();
/* 32 */       pathOnGridMap.put(gridId, sets);
/*    */     }
/* 34 */     sets.add(fullPath);
/*    */   }
/*    */   
/*    */   public static String getCachedGridIdByPath(String fullPath)
/*    */   {
/* 39 */     return (String)gridByPathMap.get(fullPath);
/*    */   }
/*    */   
/*    */   public static synchronized void removeLocalPath(String fullPath)
/*    */   {
/* 44 */     String gridId = (String)gridByPathMap.get(fullPath);
/* 45 */     Set<String> sets = (Set)pathOnGridMap.get(gridId);
/* 46 */     if (sets != null)
/*    */     {
/* 48 */       sets.remove(fullPath);
/*    */     }
/* 50 */     gridByPathMap.remove(fullPath);
/*    */   }
/*    */   
/*    */   public static List<String> getAllPathsByGridId(String gridId)
/*    */   {
/* 55 */     List<String> list = new ArrayList((Collection)pathOnGridMap.get(gridId));
/* 56 */     Collections.sort(list);
/* 57 */     return list;
/*    */   }
/*    */   
/*    */   public static String getRemoteGridIdByPath(String fullPath)
/*    */   {
/* 62 */     String gridId = getCachedGridIdByPath(fullPath);
/* 63 */     if (!StringUtils.isEmpty(gridId))
/*    */     {
/* 65 */       return gridId;
/*    */     }
/*    */     
/* 68 */     IRegionPathGetter regionPathGetter = (IRegionPathGetter)ADFRegisterFactory.getInstance().getRegister(IRegionPathGetter.class);
/* 69 */     RegionInfo rp = regionPathGetter.getRegionPathInfo(fullPath);
/* 70 */     if ((rp != null) && (rp.getGridId() != null))
/*    */     {
/* 72 */       gridId = rp.getGridId();
/* 73 */       return gridId;
/*    */     }
/* 75 */     return null;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static String findGridIdByPath(String fullPath)
/*    */   {
/* 84 */     String gridId = getCachedGridIdByPath(fullPath);
/* 85 */     if (!StringUtils.isEmpty(gridId))
/*    */     {
/* 87 */       return gridId;
/*    */     }
/* 89 */     gridId = getRemoteGridIdByPath(fullPath);
/* 90 */     if (StringUtils.isNotEmpty(gridId))
/*    */     {
/* 92 */       addLocalPath(fullPath, gridId);
/* 93 */       return gridId;
/*    */     }
/* 95 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\manager\region\ClientRegionManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */