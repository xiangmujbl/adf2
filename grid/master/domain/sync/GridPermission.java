/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.master.domain.sync;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import com.jnj.adf.grid.auth.AuthKeyConstants.GridPermission;
/*    */ import com.jnj.adf.grid.auth.AuthKeyConstants.Operations;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GridPermission
/*    */   extends AbstractPermission
/*    */ {
/* 15 */   private List<PermissionNode> permissionNodes = Lists.newArrayList();
/*    */   
/*    */   public void addPermissionNode(PermissionNode p) {
/* 18 */     this.permissionNodes.add(p);
/*    */   }
/*    */   
/*    */   public List<PermissionNode> getPermissionNodes() {
/* 22 */     return this.permissionNodes;
/*    */   }
/*    */   
/*    */   public void setPermissionNodes(List<PermissionNode> permissions) {
/* 26 */     this.permissionNodes = permissions;
/*    */   }
/*    */   
/*    */   public static class PermissionNode implements Serializable
/*    */   {
/*    */     private String role;
/*    */     private AuthKeyConstants.Operations operation;
/*    */     private AuthKeyConstants.GridPermission permission;
/*    */     
/*    */     public String getRole() {
/* 36 */       return this.role;
/*    */     }
/*    */     
/*    */     public void setRole(String role) {
/* 40 */       this.role = role;
/*    */     }
/*    */     
/*    */     public AuthKeyConstants.Operations getOperation() {
/* 44 */       return this.operation;
/*    */     }
/*    */     
/*    */     public void setOperation(AuthKeyConstants.Operations operation) {
/* 48 */       this.operation = operation;
/*    */     }
/*    */     
/*    */     public AuthKeyConstants.GridPermission getPermission() {
/* 52 */       return this.permission;
/*    */     }
/*    */     
/*    */     public void setPermission(AuthKeyConstants.GridPermission permission) {
/* 56 */       this.permission = permission;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\master\domain\sync\GridPermission.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */