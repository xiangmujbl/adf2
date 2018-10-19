/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.master.manager;
/*    */ 
/*    */ import com.jnj.adf.client.api.IBiz;
/*    */ import com.jnj.adf.client.api.IRemoteService;
/*    */ import com.jnj.adf.grid.common.ADFException;
/*    */ import com.jnj.adf.grid.connect.GridConnection;
/*    */ import com.jnj.adf.grid.master.IHostAuthenticateRemoteService;
/*    */ import com.jnj.adf.grid.master.domain.RegionPath;
/*    */ import com.jnj.adf.grid.support.hybrid.ADFHybridCacheManager;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MasterRegionPathManager
/*    */ {
/* 16 */   private static final MasterRegionPathManager INST = new MasterRegionPathManager();
/*    */   
/* 18 */   public static final MasterRegionPathManager getInstance() { return INST; }
/*    */   
/*    */ 
/*    */   private IHostAuthenticateRemoteService getRemoteService()
/*    */   {
/* 23 */     IRemoteService<IHostAuthenticateRemoteService> srv = IBiz.remote(IHostAuthenticateRemoteService.class);
/* 24 */     GridConnection conn = ADFHybridCacheManager.getInstance().findMaserConnection();
/* 25 */     return (IHostAuthenticateRemoteService)srv.onServer(conn.getPool());
/*    */   }
/*    */   
/*    */   public String getGridIdByRegionPath(String fullPath) throws ADFException {
/* 29 */     IHostAuthenticateRemoteService remoteService = getRemoteService();
/* 30 */     RegionPath path = new RegionPath();
/* 31 */     path.setRegionPath(fullPath);
/* 32 */     return remoteService.getGridIdByRegionPath(path);
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\master\manager\MasterRegionPathManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */