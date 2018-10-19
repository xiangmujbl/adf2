/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.master.domain.sync;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Sets;
/*     */ import com.jnj.adf.grid.auth.AuthKeyConstants.GridPermission;
/*     */ import com.jnj.adf.grid.auth.AuthKeyConstants.Operations;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GroupPermission
/*     */   extends AbstractPermission
/*     */ {
/*     */   private String groupId;
/*     */   private String groupName;
/*     */   private String parentGroupId;
/*  19 */   private Set<String> regionPaths = Sets.newHashSet();
/*  20 */   private Set<String> remoteServices = Sets.newHashSet();
/*  21 */   private Set<String> parentGroupSet = Sets.newHashSet();
/*     */   
/*     */   public void addParentGroupSet(String parentGroup)
/*     */   {
/*  25 */     this.parentGroupSet.add(parentGroup);
/*     */   }
/*     */   
/*     */   public void addRegionPath(String regionPath) {
/*  29 */     this.regionPaths.add(regionPath);
/*     */   }
/*     */   
/*     */   public void addRemoteService(String serviceName) {
/*  33 */     this.remoteServices.add(serviceName);
/*     */   }
/*     */   
/*     */   public Set<String> getParentGroupSet() {
/*  37 */     return this.parentGroupSet;
/*     */   }
/*     */   
/*     */   public void setParentGroupSet(Set<String> parentGroupSet) {
/*  41 */     this.parentGroupSet = parentGroupSet;
/*     */   }
/*     */   
/*     */   public Set<String> getRegionPaths() {
/*  45 */     return this.regionPaths;
/*     */   }
/*     */   
/*     */   public void setRegionPaths(Set<String> regionPaths) {
/*  49 */     this.regionPaths = regionPaths;
/*     */   }
/*     */   
/*     */   public Set<String> getRemoteServices() {
/*  53 */     return this.remoteServices;
/*     */   }
/*     */   
/*     */   public void setRemoteServices(Set<String> remoteServices) {
/*  57 */     this.remoteServices = remoteServices;
/*     */   }
/*     */   
/*     */   public String getGroupId() {
/*  61 */     return this.groupId;
/*     */   }
/*     */   
/*     */   public void setGroupId(String groupId) {
/*  65 */     this.groupId = groupId;
/*     */   }
/*     */   
/*     */   public String getGroupName() {
/*  69 */     return this.groupName;
/*     */   }
/*     */   
/*     */   public void setGroupName(String groupName) {
/*  73 */     this.groupName = groupName;
/*     */   }
/*     */   
/*     */   public String getParentGroupId() {
/*  77 */     return this.parentGroupId;
/*     */   }
/*     */   
/*     */   public void setParentGroupId(String parentGroupId) {
/*  81 */     this.parentGroupId = parentGroupId;
/*     */   }
/*     */   
/*  84 */   private List<PermissionNode> permissionNodes = Lists.newArrayList();
/*     */   
/*     */   public void addPermissionNode(PermissionNode p) {
/*  87 */     this.permissionNodes.add(p);
/*     */   }
/*     */   
/*     */   public List<PermissionNode> getPermissionNodes() {
/*  91 */     return this.permissionNodes;
/*     */   }
/*     */   
/*     */   public void setPermissionNodes(List<PermissionNode> permissions) {
/*  95 */     this.permissionNodes = permissions;
/*     */   }
/*     */   
/*     */   public static class PermissionNode implements Serializable {
/*     */     private String role;
/*     */     private AuthKeyConstants.Operations operation;
/*     */     private AuthKeyConstants.GridPermission permission;
/*     */     
/*     */     public String getRole() {
/* 104 */       return this.role;
/*     */     }
/*     */     
/*     */     public void setRole(String role) {
/* 108 */       this.role = role;
/*     */     }
/*     */     
/*     */     public AuthKeyConstants.Operations getOperation() {
/* 112 */       return this.operation;
/*     */     }
/*     */     
/*     */     public void setOperation(AuthKeyConstants.Operations operation) {
/* 116 */       this.operation = operation;
/*     */     }
/*     */     
/*     */     public AuthKeyConstants.GridPermission getPermission() {
/* 120 */       return this.permission;
/*     */     }
/*     */     
/*     */     public void setPermission(AuthKeyConstants.GridPermission permission) {
/* 124 */       this.permission = permission;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\master\domain\sync\GroupPermission.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */