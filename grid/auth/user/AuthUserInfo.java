/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.auth.user;
/*    */ 
/*    */ import com.google.common.collect.Sets;
/*    */ import java.io.Serializable;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AuthUserInfo
/*    */   implements Serializable
/*    */ {
/*    */   private String userId;
/*    */   private String userName;
/*    */   private String gender;
/*    */   private String departCode;
/* 16 */   private Set<String> adGroups = Sets.newHashSet();
/* 17 */   private boolean valid = true;
/*    */   
/*    */   public void setUserId(String userId) {
/* 20 */     this.userId = userId;
/*    */   }
/*    */   
/*    */   public void setUserName(String userName) {
/* 24 */     this.userName = userName;
/*    */   }
/*    */   
/*    */   public void setGender(String gender) {
/* 28 */     this.gender = gender;
/*    */   }
/*    */   
/*    */   public String getUserId() {
/* 32 */     return this.userId;
/*    */   }
/*    */   
/*    */   public String getUserName() {
/* 36 */     return this.userName;
/*    */   }
/*    */   
/*    */   public String getGender() {
/* 40 */     return this.gender;
/*    */   }
/*    */   
/*    */   public String getDepartCode() {
/* 44 */     return this.departCode;
/*    */   }
/*    */   
/*    */   public void setDepartCode(String departCode) {
/* 48 */     this.departCode = departCode;
/*    */   }
/*    */   
/*    */   public boolean isValid() {
/* 52 */     return this.valid;
/*    */   }
/*    */   
/*    */   public void setValid(boolean valid) {
/* 56 */     this.valid = valid;
/*    */   }
/*    */   
/*    */   public Set<String> getAdGroups() {
/* 60 */     return this.adGroups;
/*    */   }
/*    */   
/*    */   public void setAdGroups(Set<String> adGroups) {
/* 64 */     this.adGroups = adGroups;
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\auth\user\AuthUserInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */