/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.auth.provider.impl;
/*    */ 
/*    */ import com.jnj.adf.grid.auth.exception.AuthException;
/*    */ import com.jnj.adf.grid.auth.impl.AuthUserPrivilege;
/*    */ import com.jnj.adf.grid.auth.local.LocalXmlFileAuthLoader;
/*    */ import com.jnj.adf.grid.auth.local.LocalXmlFileAuthLoader.LocalAccount;
/*    */ import com.jnj.adf.grid.auth.local.LocalXmlFileMappingLoader;
/*    */ import com.jnj.adf.grid.auth.provider.AbstractAuthProvider;
/*    */ import com.jnj.adf.grid.auth.provider.AbstractAuthProvider.AuthMode;
/*    */ import com.jnj.adf.grid.auth.user.AuthUserInfo;
/*    */ import java.util.Set;
/*    */ import org.springframework.beans.factory.InitializingBean;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public class LocalAuthProvider
/*    */   extends AbstractAuthProvider
/*    */   implements InitializingBean
/*    */ {
/*    */   public String verify(String username, String pwd)
/*    */     throws AuthException
/*    */   {
/* 25 */     LocalXmlFileAuthLoader.LocalAccount localAccount = LocalXmlFileAuthLoader.getLocalAccountByUserName(username);
/* 26 */     if ((localAccount != null) && 
/* 27 */       (localAccount.validateCreditial(username, pwd))) {
/* 28 */       return username;
/*    */     }
/*    */     
/* 31 */     return null;
/*    */   }
/*    */   
/*    */   public AuthUserInfo findUser(String userId)
/*    */     throws AuthException
/*    */   {
/* 37 */     AuthUserInfo userInfo = null;
/* 38 */     LocalXmlFileAuthLoader.LocalAccount localAccount = LocalXmlFileAuthLoader.getLocalAccountByUserName(userId);
/* 39 */     if (localAccount != null) {
/* 40 */       userInfo = new AuthUserInfo();
/* 41 */       userInfo.setUserId(userId);
/* 42 */       if (localAccount.getAdGroups() != null) {
/* 43 */         userInfo.setAdGroups(localAccount.getAdGroups());
/*    */       }
/*    */     }
/* 46 */     return userInfo;
/*    */   }
/*    */   
/*    */   public AuthUserPrivilege findUserPrivilege(String userId) throws AuthException
/*    */   {
/* 51 */     AuthUserPrivilege privilege = null;
/* 52 */     LocalXmlFileAuthLoader.LocalAccount localAccount = LocalXmlFileAuthLoader.getLocalAccountByUserName(userId);
/* 53 */     if (localAccount != null) {
/* 54 */       privilege = new AuthUserPrivilege();
/* 55 */       privilege.setUserId(userId);
/* 56 */       if (localAccount.getUserRoles() != null) {
/* 57 */         privilege.addRoles(localAccount.getUserRoles());
/*    */       }
/*    */       
/* 60 */       Set<String> grps = localAccount.getAdGroups();
/* 61 */       if (grps != null) {
/* 62 */         for (String grp : grps) {
/* 63 */           Set<String> roles = LocalXmlFileMappingLoader.getLocalRolesByUserGroup(grp);
/* 64 */           if ((roles != null) && (!roles.isEmpty())) {
/* 65 */             privilege.addRoles(roles);
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/*    */     
/*    */ 
/* 72 */     return privilege;
/*    */   }
/*    */   
/*    */ 
/*    */   public void afterPropertiesSet()
/*    */     throws Exception
/*    */   {
/* 79 */     initProviderContext(AbstractAuthProvider.AuthMode.local);
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\auth\provider\impl\LocalAuthProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */