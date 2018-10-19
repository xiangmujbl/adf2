/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.manager.federal.impl;
/*     */ 
/*     */ import com.jnj.adf.client.api.IBiz;
/*     */ import com.jnj.adf.client.api.IRemoteService;
/*     */ import com.jnj.adf.grid.connect.GridConnection;
/*     */ import com.jnj.adf.grid.connect.domain.GridInfo;
/*     */ import com.jnj.adf.grid.connect.domain.GridInfoAndStatus;
/*     */ import com.jnj.adf.grid.connect.domain.GridStatus;
/*     */ import com.jnj.adf.grid.manager.federal.FederalGridOperation;
/*     */ import com.jnj.adf.grid.manager.federal.FederalGroupOperation;
/*     */ import com.jnj.adf.grid.master.IHostBizRemoteService;
/*     */ import com.jnj.adf.grid.master.IHostGridRemoteService;
/*     */ import com.jnj.adf.grid.support.hybrid.ADFHybridCacheManager;
/*     */ import com.jnj.adf.grid.utils.LogUtil;
/*     */ import java.util.List;
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
/*     */ public class FederalMasterService
/*     */   implements FederalGridOperation, FederalGroupOperation
/*     */ {
/*  35 */   private static final FederalMasterService INST = new FederalMasterService();
/*     */   
/*     */   public static final FederalMasterService getInstance()
/*     */   {
/*  39 */     return INST;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private IHostGridRemoteService getRemoteService()
/*     */   {
/*  48 */     IRemoteService<IHostGridRemoteService> srv = IBiz.remote(IHostGridRemoteService.class);
/*  49 */     GridConnection conn = ADFHybridCacheManager.getInstance().findMaserConnection();
/*  50 */     if (conn != null)
/*     */     {
/*  52 */       LogUtil.getCoreLog().debug("Remote service grid info is {}", new Object[] { conn.fetchGridInfo() });
/*  53 */       return (IHostGridRemoteService)srv.onServer(conn.getPool());
/*     */     }
/*  55 */     return null;
/*     */   }
/*     */   
/*     */   private IHostBizRemoteService getRegisterRemoteService()
/*     */   {
/*  60 */     IRemoteService<IHostBizRemoteService> srv = IBiz.remote(IHostBizRemoteService.class);
/*  61 */     GridConnection conn = ADFHybridCacheManager.getInstance().findMaserConnection();
/*  62 */     if (conn != null)
/*     */     {
/*  64 */       LogUtil.getCoreLog().debug("Register Remote service grid info is {}", new Object[] { conn.fetchGridInfo() });
/*  65 */       return (IHostBizRemoteService)srv.onServer(conn.getPool());
/*     */     }
/*  67 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public List<String> listGridName()
/*     */   {
/*  73 */     LogUtil.getCoreLog().debug("Start to call remote service to list grid name.");
/*  74 */     IHostGridRemoteService rs = getRemoteService();
/*  75 */     if (rs != null)
/*     */     {
/*  77 */       List<String> result = rs.listGridName();
/*  78 */       LogUtil.getCoreLog().debug("Finished to process list grid name logic.");
/*  79 */       return result;
/*     */     }
/*     */     
/*     */ 
/*  83 */     LogUtil.getCoreLog().error("Can't connet to master grid, do nothing!");
/*  84 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setGridStatus(String gridName, GridStatus status)
/*     */   {
/*  91 */     LogUtil.getCoreLog().debug("Start to call remote service to set grid:{} status to {}.", new Object[] { gridName, status });
/*  92 */     IHostBizRemoteService rs = getRegisterRemoteService();
/*  93 */     if (rs != null)
/*     */     {
/*  95 */       rs.setGridStatus(gridName, status);
/*  96 */       LogUtil.getCoreLog().debug("Finished to process set grid status logic.");
/*     */     }
/*     */     else
/*     */     {
/* 100 */       LogUtil.getCoreLog().error("Can't connet to master grid, do nothing!");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean updateGrid(String gridName, GridInfo data)
/*     */   {
/* 107 */     LogUtil.getCoreLog().debug("Start to call remote service to update grid {}.", new Object[] { data });
/*     */     
/* 109 */     IHostBizRemoteService rs = getRegisterRemoteService();
/* 110 */     if (rs != null)
/*     */     {
/* 112 */       boolean result = rs.updateGrid(gridName, data);
/* 113 */       LogUtil.getCoreLog().debug("Finished to process update grid logic.");
/* 114 */       return result;
/*     */     }
/*     */     
/*     */ 
/* 118 */     LogUtil.getCoreLog().error("Can't connet to master grid, do nothing!");
/* 119 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean addGroup(String groupName)
/*     */   {
/* 125 */     LogUtil.getCoreLog().debug("Start to call remote service to add group:{}.", new Object[] { groupName });
/*     */     
/* 127 */     IHostBizRemoteService rs = getRegisterRemoteService();
/* 128 */     if (rs != null)
/*     */     {
/* 130 */       boolean result = rs.addGroup(groupName);
/* 131 */       LogUtil.getCoreLog().debug("Finished to process add group logic.");
/* 132 */       return result;
/*     */     }
/*     */     
/*     */ 
/* 136 */     LogUtil.getCoreLog().error("Can't connet to master grid, do nothing!");
/* 137 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean removeGroup(String groupName)
/*     */   {
/* 144 */     LogUtil.getCoreLog().debug("Start to call remote service to remove group:{}.", new Object[] { groupName });
/*     */     
/* 146 */     IHostBizRemoteService rs = getRegisterRemoteService();
/* 147 */     if (rs != null)
/*     */     {
/* 149 */       boolean result = rs.removeGroup(groupName);
/* 150 */       LogUtil.getCoreLog().debug("Finished to process remove group logic.");
/* 151 */       return result;
/*     */     }
/*     */     
/*     */ 
/* 155 */     LogUtil.getCoreLog().error("Can't connet to master grid, do nothing!");
/* 156 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<String> listGroupName()
/*     */   {
/* 165 */     LogUtil.getCoreLog().debug("Start to call remote service to list group name.");
/* 166 */     IHostGridRemoteService rs = getRemoteService();
/* 167 */     if (rs != null)
/*     */     {
/* 169 */       List<String> result = rs.listGroupName();
/* 170 */       LogUtil.getCoreLog().debug("Finished to process list group name logic.");
/* 171 */       return result;
/*     */     }
/*     */     
/*     */ 
/* 175 */     LogUtil.getCoreLog().error("Can't connet to master grid, do nothing!");
/* 176 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean updateGroup(String groupName, GridInfo data)
/*     */   {
/* 183 */     LogUtil.getCoreLog().debug("Start to call remote service to update group:{} info:{}.", new Object[] { groupName, data });
/*     */     
/* 185 */     IHostBizRemoteService rs = getRegisterRemoteService();
/* 186 */     if (rs != null)
/*     */     {
/* 188 */       boolean result = rs.updateGroup(groupName, data);
/* 189 */       LogUtil.getCoreLog().debug("Finished to process update group info logic.");
/* 190 */       return result;
/*     */     }
/*     */     
/*     */ 
/* 194 */     LogUtil.getCoreLog().error("Can't connet to master grid, do nothing!");
/* 195 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void groupOnline(GridInfo info)
/*     */   {
/* 202 */     LogUtil.getCoreLog().debug("Start to call remote service to group online {}.", new Object[] { info });
/*     */     
/* 204 */     IHostBizRemoteService rs = getRegisterRemoteService();
/* 205 */     if (rs != null)
/*     */     {
/* 207 */       rs.groupOnline(info);
/* 208 */       LogUtil.getCoreLog().debug("Finished to process group online logic.");
/*     */     }
/*     */     else
/*     */     {
/* 212 */       LogUtil.getCoreLog().error("Can't connet to master grid, do nothing!");
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
/*     */   public List<GridInfo> listGridData(String group)
/*     */   {
/* 225 */     LogUtil.getCoreLog().debug("Start to call remote service to list cached grid data under the group:{} .", new Object[] { group });
/* 226 */     IHostGridRemoteService rs = getRemoteService();
/* 227 */     if (rs != null)
/*     */     {
/* 229 */       List<GridInfo> result = rs.listGridData(group);
/* 230 */       LogUtil.getCoreLog().debug("Finished to process list cached grid data under group logic.");
/* 231 */       return result;
/*     */     }
/*     */     
/*     */ 
/* 235 */     LogUtil.getCoreLog().error("Can't connet to master grid, do nothing!");
/* 236 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public List<String> listGridName(String group)
/*     */   {
/* 243 */     LogUtil.getCoreLog().debug("Start to call remote service to list cached grid name under the group:{} .", new Object[] { group });
/* 244 */     IHostGridRemoteService rs = getRemoteService();
/* 245 */     if (rs != null)
/*     */     {
/* 247 */       List<String> result = rs.listGridName(group);
/* 248 */       LogUtil.getCoreLog().debug("Finished to process list cached grid name under group logic.");
/* 249 */       return result;
/*     */     }
/*     */     
/*     */ 
/* 253 */     LogUtil.getCoreLog().error("Can't connet to master grid, do nothing!");
/* 254 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public GridInfo getGridData(String gridName)
/*     */   {
/* 261 */     LogUtil.getCoreLog().debug("Start to call remote service to get cached grid:{} info", new Object[] { gridName });
/* 262 */     IHostGridRemoteService rs = getRemoteService();
/* 263 */     if (rs != null)
/*     */     {
/* 265 */       GridInfo result = rs.getGridData(gridName);
/* 266 */       LogUtil.getCoreLog().debug("Finished to process get cached grid info.");
/* 267 */       return result;
/*     */     }
/*     */     
/*     */ 
/* 271 */     LogUtil.getCoreLog().error("Can't connet to master grid, do nothing!");
/* 272 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public GridInfoAndStatus getGridAndStatus(String gridName)
/*     */   {
/* 283 */     LogUtil.getCoreLog().debug("Start to call remote service to get cached grid:{} info and status", new Object[] { gridName });
/* 284 */     IHostGridRemoteService rs = getRemoteService();
/* 285 */     if (rs != null)
/*     */     {
/*     */       try
/*     */       {
/* 289 */         GridInfoAndStatus result = rs.getGridAndStatus(gridName);
/* 290 */         LogUtil.getCoreLog().debug("Finished to process get cached grid info and status.");
/* 291 */         return result;
/*     */ 
/*     */       }
/*     */       catch (Exception localException)
/*     */       {
/* 296 */         return null;
/*     */       }
/*     */     }
/*     */     
/* 300 */     LogUtil.getCoreLog().error("Can't connet to master grid, do nothing!");
/* 301 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public List<GridInfoAndStatus> listGridAndStatus(String group)
/*     */   {
/* 307 */     LogUtil.getCoreLog().debug("Start to call remote service to list cached grid info and status under the group:{}", new Object[] { group });
/*     */     
/* 309 */     IHostGridRemoteService rs = getRemoteService();
/* 310 */     if (rs != null)
/*     */     {
/* 312 */       List<GridInfoAndStatus> result = rs.listGridAndStatus(group);
/* 313 */       LogUtil.getCoreLog().debug("Finished to process list cached grid info and status under the group.");
/* 314 */       return result;
/*     */     }
/*     */     
/*     */ 
/* 318 */     LogUtil.getCoreLog().error("Can't connet to master grid, do nothing!");
/* 319 */     return null;
/*     */   }
/*     */   
/*     */   public void gridOnline(GridInfo info) {}
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\manager\federal\impl\FederalMasterService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */