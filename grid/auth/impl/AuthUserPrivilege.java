/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.auth.impl;
/*    */ 
/*    */ import com.google.common.collect.Sets;
/*    */ import java.io.Serializable;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AuthUserPrivilege
/*    */   implements Serializable
/*    */ {
/* 13 */   private String userId = null;
/* 14 */   private Set<String> roles = Sets.newHashSet();
/*    */   
/*    */   public String getUserId() {
/* 17 */     return this.userId;
/*    */   }
/*    */   
/*    */   public void setUserId(String userId) {
/* 21 */     this.userId = userId;
/*    */   }
/*    */   
/*    */   public Set<String> getRoles() {
/* 25 */     return this.roles;
/*    */   }
/*    */   
/*    */   public void setRoles(Set<String> roles) {
/* 29 */     this.roles = roles;
/*    */   }
/*    */   
/*    */   public void addRoles(Set<String> roles) {
/* 33 */     if (roles != null) {
/* 34 */       this.roles.addAll(roles);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\auth\impl\AuthUserPrivilege.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */