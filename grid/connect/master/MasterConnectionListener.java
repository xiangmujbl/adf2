/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.connect.master;
/*    */ 
/*    */ import com.gemstone.gemfire.cache.client.Pool;
/*    */ import com.gemstone.gemfire.cache.client.PoolManager;
/*    */ import com.jnj.adf.grid.connect.GridConnection;
/*    */ import com.jnj.adf.grid.connect.domain.GridInfo;
/*    */ import com.jnj.adf.grid.events.ApplicationConextReadyEvent;
/*    */ import com.jnj.adf.grid.manager.RegionBuilder;
/*    */ import com.jnj.adf.grid.manager.region.ClientRegionManager;
/*    */ import com.jnj.adf.grid.support.hybrid.ADFHybridCacheManager;
/*    */ import com.jnj.adf.grid.utils.LogUtil;
/*    */ import java.util.Map;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import org.springframework.context.ApplicationListener;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MasterConnectionListener
/*    */   implements ApplicationListener<ApplicationConextReadyEvent>
/*    */ {
/*    */   public void onApplicationEvent(ApplicationConextReadyEvent contextStartedEvent)
/*    */   {
/* 23 */     GridConnection connection = ADFHybridCacheManager.getInstance().findMaserConnection();
/* 24 */     LogUtil.getCoreLog().info("PoolManager get all pools, all pool names:{}", new Object[] { PoolManager.getAll().keySet() });
/* 25 */     if (connection != null) {
/* 26 */       String gridName = connection.fetchGridInfo().getGridName();
/* 27 */       Pool pool = connection.getPool();
/* 28 */       RegionBuilder.newBuilder().setGridName(gridName).setPool(pool).create();
/* 29 */       LogUtil.getCoreLog().info("MasterConnectionListener: create proxy region success. all paths: {}", new Object[] { ClientRegionManager.getAllPathsByGridId(gridName) });
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\connect\master\MasterConnectionListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */