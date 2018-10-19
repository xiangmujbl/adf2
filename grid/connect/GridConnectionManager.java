/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.connect;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import com.jnj.adf.grid.common.ADFException;
/*    */ import com.jnj.adf.grid.connect.domain.GridInfo;
/*    */ import com.jnj.adf.grid.utils.LogUtil;
/*    */ import java.util.Map;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GridConnectionManager
/*    */ {
/* 14 */   private static final Map<String, GridConnection> connectedPoolCacheMap = ;
/* 15 */   private static String masterPoolName = null;
/* 16 */   private static GridConnection masterGridConnection = null;
/* 17 */   private static final Object locked = new Object();
/*    */   
/*    */   public static void register(String gridId, GridConnection connection)
/*    */   {
/* 21 */     LogUtil.getCoreLog().debug("register connection to local cache. girdid:{}, connection pool: {}", new Object[] { gridId, connection
/* 22 */       .getPoolName() });
/* 23 */     if (!connectedPoolCacheMap.containsKey(gridId))
/*    */     {
/* 25 */       synchronized (locked)
/*    */       {
/* 27 */         if (!connectedPoolCacheMap.containsKey(gridId))
/*    */         {
/* 29 */           connectedPoolCacheMap.put(gridId, connection);
/* 30 */           if ((connection.fetchGridInfo() != null) && (connection.fetchGridInfo().isMaster()))
/*    */           {
/* 32 */             LogUtil.getCoreLog().debug("find master grid connection. masterGridName{}, masterPoolName:{}", new Object[] {connection
/* 33 */               .fetchGridInfo().getGridName(), connection.getPoolName() });
/* 34 */             masterGridConnection = connection;
/* 35 */             masterPoolName = connection.getPoolName();
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public static GridConnection find(String gridName)
/*    */   {
/* 44 */     return (GridConnection)connectedPoolCacheMap.get(gridName);
/*    */   }
/*    */   
/*    */   public static boolean exist(String gridName)
/*    */   {
/* 49 */     return connectedPoolCacheMap.containsKey(gridName);
/*    */   }
/*    */   
/*    */   public static String getMasterPoolName()
/*    */   {
/* 54 */     return masterPoolName;
/*    */   }
/*    */   
/*    */   public static GridConnection getMasterGridConnection()
/*    */   {
/* 59 */     return masterGridConnection;
/*    */   }
/*    */   
/*    */   public static boolean isConnect(String gridId)
/*    */   {
/* 64 */     GridConnection connection = find(gridId);
/* 65 */     return (connection != null) && (connection.isConnected());
/*    */   }
/*    */   
/*    */   public static boolean unregister(String gridId)
/*    */   {
/* 70 */     LogUtil.getCoreLog().debug("unregister connection from local cache. girdid:{}, connection pool: {}", new Object[] { gridId });
/* 71 */     if (connectedPoolCacheMap.containsKey(gridId))
/*    */     {
/* 73 */       synchronized (locked)
/*    */       {
/* 75 */         if (connectedPoolCacheMap.containsKey(gridId))
/*    */         {
/* 77 */           GridConnection connection = (GridConnection)connectedPoolCacheMap.get(gridId);
/* 78 */           if (connection.fetchGridInfo().isMaster())
/*    */           {
/* 80 */             LogUtil.getCoreLog().error("you can not close master grid connection.");
/* 81 */             throw new ADFException("you can not close master grid connection.");
/*    */           }
/* 83 */           connectedPoolCacheMap.remove(gridId);
/*    */         }
/*    */       }
/*    */     }
/* 87 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\connect\GridConnectionManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */