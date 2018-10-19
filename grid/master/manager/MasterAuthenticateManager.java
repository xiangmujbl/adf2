/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.master.manager;
/*    */ 
/*    */ import com.gemstone.gemfire.cache.client.Pool;
/*    */ import com.jnj.adf.client.api.IBiz;
/*    */ import com.jnj.adf.client.api.IRemoteService;
/*    */ import com.jnj.adf.grid.auth.exception.AuthException;
/*    */ import com.jnj.adf.grid.connect.GridConnection;
/*    */ import com.jnj.adf.grid.master.IHostAuthenticateRemoteService;
/*    */ import com.jnj.adf.grid.support.hybrid.ADFHybridCacheManager;
/*    */ import com.jnj.adf.grid.utils.LogUtil;
/*    */ import java.util.Set;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MasterAuthenticateManager
/*    */ {
/* 18 */   private static final MasterAuthenticateManager INST = new MasterAuthenticateManager();
/*    */   
/* 20 */   public static final MasterAuthenticateManager getInstance() { return INST; }
/*    */   
/*    */   private IHostAuthenticateRemoteService getRemoteService() {
/* 23 */     IRemoteService<IHostAuthenticateRemoteService> srv = IBiz.remote(IHostAuthenticateRemoteService.class);
/* 24 */     if (srv == null) {
/* 25 */       LogUtil.getCoreLog().debug("can not find IHostAuthenticateRemoteService.class.");
/*    */     }
/* 27 */     GridConnection conn = ADFHybridCacheManager.getInstance().findMaserConnection();
/* 28 */     if (conn == null) {
/* 29 */       LogUtil.getCoreLog().debug("can not find master connection.");
/*    */     }
/* 31 */     LogUtil.getCoreLog().debug("find master connection poolName:" + conn.getPoolName());
/* 32 */     Pool pool = conn.getPool();
/* 33 */     if (pool == null) {
/* 34 */       LogUtil.getCoreLog().debug("can not find master pool.");
/*    */     }
/* 36 */     return (IHostAuthenticateRemoteService)srv.onServer(conn.getPool());
/*    */   }
/*    */   
/*    */   public boolean authenticate(String username, String password) throws AuthException {
/* 40 */     IHostAuthenticateRemoteService remoteService = getRemoteService();
/* 41 */     return remoteService.authenticate(username, password);
/*    */   }
/*    */   
/*    */   public Set<String> getUserRolesByUsername(String username) throws AuthException {
/* 45 */     IHostAuthenticateRemoteService remoteService = getRemoteService();
/* 46 */     return remoteService.getUserRolesByUsername(username);
/*    */   }
/*    */   
/*    */   public Set<String> getUserGroupsByUsername(String username) throws AuthException
/*    */   {
/* 51 */     IHostAuthenticateRemoteService remoteService = getRemoteService();
/* 52 */     return remoteService.getUserGroupsByUsername(username);
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\master\manager\MasterAuthenticateManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */