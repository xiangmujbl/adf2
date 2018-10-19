/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.master.domain.sync;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ public class GridAllPermission
/*    */   extends AbstractPermission
/*    */ {
/*    */   private GridPermission gridPermission;
/* 11 */   private Map<String, RegionPermission> regionPermissions = Maps.newHashMap();
/* 12 */   private Map<String, RemoteServicePermission> remoteServicePermissions = Maps.newHashMap();
/* 13 */   private Map<String, GroupPermission> groupPermissions = Maps.newHashMap();
/*    */   
/*    */   public void addRegionPermission(String regionName, RegionPermission rp) {
/* 16 */     this.regionPermissions.put(regionName, rp);
/*    */   }
/*    */   
/*    */   public void addRemoteServicePermission(String serviceName, RemoteServicePermission rsp) {
/* 20 */     this.remoteServicePermissions.put(serviceName, rsp);
/*    */   }
/*    */   
/*    */   public void addGroupPermission(String groupId, GroupPermission rsp) {
/* 24 */     this.groupPermissions.put(groupId, rsp);
/*    */   }
/*    */   
/*    */   public Map<String, GroupPermission> getGroupPermissions() {
/* 28 */     return this.groupPermissions;
/*    */   }
/*    */   
/*    */   public GridPermission getGridPermission() {
/* 32 */     return this.gridPermission;
/*    */   }
/*    */   
/*    */   public void setGridPermission(GridPermission gridPermission) {
/* 36 */     this.gridPermission = gridPermission;
/*    */   }
/*    */   
/*    */   public Map<String, RegionPermission> getRegionPermissions() {
/* 40 */     return this.regionPermissions;
/*    */   }
/*    */   
/*    */   public void setRegionPermissions(Map<String, RegionPermission> regionPermissions) {
/* 44 */     this.regionPermissions = regionPermissions;
/*    */   }
/*    */   
/*    */   public Map<String, RemoteServicePermission> getRemoteServicePermissions() {
/* 48 */     return this.remoteServicePermissions;
/*    */   }
/*    */   
/*    */   public void setRemoteServicePermissions(Map<String, RemoteServicePermission> remoteServicePermissions) {
/* 52 */     this.remoteServicePermissions = remoteServicePermissions;
/*    */   }
/*    */   
/*    */   public void setGroupPermissions(Map<String, GroupPermission> groupPermissions) {
/* 56 */     this.groupPermissions = groupPermissions;
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\master\domain\sync\GridAllPermission.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */