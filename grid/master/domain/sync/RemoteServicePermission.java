/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.master.domain.sync;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import com.jnj.adf.grid.auth.AuthKeyConstants.GridPermission;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RemoteServicePermission
/*    */   extends AbstractPermission
/*    */ {
/*    */   private String serviceName;
/* 14 */   private List<PermissionNode> permissionNodes = Lists.newArrayList();
/*    */   
/*    */   public String getServiceName() {
/* 17 */     return this.serviceName;
/*    */   }
/*    */   
/*    */   public void setServiceName(String serviceName) {
/* 21 */     this.serviceName = serviceName;
/*    */   }
/*    */   
/*    */   public void addPermissionNode(PermissionNode p)
/*    */   {
/* 26 */     this.permissionNodes.add(p);
/*    */   }
/*    */   
/*    */   public List<PermissionNode> getPermissionNodes() {
/* 30 */     return this.permissionNodes;
/*    */   }
/*    */   
/*    */   public void setPermissionNodes(List<PermissionNode> permissions) {
/* 34 */     this.permissionNodes = permissions;
/*    */   }
/*    */   
/*    */   public static class PermissionNode implements Serializable {
/*    */     private String role;
/*    */     private AuthKeyConstants.GridPermission permission;
/*    */     
/* 41 */     public String getRole() { return this.role; }
/*    */     
/*    */     public void setRole(String role) {
/* 44 */       this.role = role;
/*    */     }
/*    */     
/*    */     public AuthKeyConstants.GridPermission getPermission() {
/* 48 */       return this.permission;
/*    */     }
/*    */     
/*    */     public void setPermission(AuthKeyConstants.GridPermission permission) {
/* 52 */       this.permission = permission;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\master\domain\sync\RemoteServicePermission.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */