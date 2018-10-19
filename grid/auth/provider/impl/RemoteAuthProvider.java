/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.auth.provider.impl;
/*    */ 
/*    */ import com.jnj.adf.grid.auth.exception.AuthException;
/*    */ import com.jnj.adf.grid.auth.impl.AuthUserPrivilege;
/*    */ import com.jnj.adf.grid.auth.local.LocalXmlFileMappingLoader;
/*    */ import com.jnj.adf.grid.auth.provider.AbstractAuthProvider;
/*    */ import com.jnj.adf.grid.auth.provider.AbstractAuthProvider.AuthMode;
/*    */ import com.jnj.adf.grid.auth.user.AuthUserInfo;
/*    */ import com.jnj.adf.grid.common.ADFException;
/*    */ import com.jnj.adf.grid.common.ExceptionTypes;
/*    */ import com.jnj.adf.grid.kit.ADFRegisterFactory;
/*    */ import com.jnj.adf.grid.kit.IAuthenticateGetter;
/*    */ import com.jnj.adf.grid.master.manager.MasterAuthenticateManager;
/*    */ import com.jnj.adf.grid.utils.LogUtil;
/*    */ import java.util.Set;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import org.springframework.beans.factory.InitializingBean;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ @Component
/*    */ public class RemoteAuthProvider
/*    */   extends AbstractAuthProvider
/*    */   implements InitializingBean
/*    */ {
/*    */   public String verify(String username, String pwd)
/*    */     throws AuthException
/*    */   {
/* 28 */     boolean r = false;
/*    */     try {
/* 30 */       IAuthenticateGetter authenticateGetter = (IAuthenticateGetter)ADFRegisterFactory.getInstance().getRegister(IAuthenticateGetter.class);
/* 31 */       r = authenticateGetter.authenticate(username, pwd);
/*    */     } catch (AuthException e) {
/* 33 */       LogUtil.getAppLog().error("", e);
/* 34 */       throw e;
/*    */     } catch (ADFException e) {
/* 36 */       if ((ExceptionTypes.login.getExceptionId().equals(e.getExceptionId())) && (e.getCause() != null)) {
/* 37 */         throw new AuthException(e.getCause().getMessage());
/*    */       }
/*    */     }
/* 40 */     if (r) {
/* 41 */       return username;
/*    */     }
/* 43 */     LogUtil.getAppLog().debug("remote auth provider. authenticate fail, username={}, password=********", new Object[] { username });
/* 44 */     return null;
/*    */   }
/*    */   
/*    */   public AuthUserInfo findUser(String userId) throws AuthException
/*    */   {
/* 49 */     AuthUserInfo authUserInfo = new AuthUserInfo();
/* 50 */     authUserInfo.setUserId(userId);
/*    */     try {
/* 52 */       IAuthenticateGetter authenticateGetter = (IAuthenticateGetter)ADFRegisterFactory.getInstance().getRegister(IAuthenticateGetter.class);
/* 53 */       Set<String> userGroups = authenticateGetter.getUserGroups(userId);
/* 54 */       authUserInfo.setAdGroups(userGroups);
/*    */     } catch (Exception e) {
/* 56 */       return authUserInfo;
/*    */     }
/* 58 */     return authUserInfo;
/*    */   }
/*    */   
/*    */   public AuthUserPrivilege findUserPrivilege(String userId) throws AuthException
/*    */   {
/* 63 */     AuthUserPrivilege privilege = new AuthUserPrivilege();
/* 64 */     privilege.setUserId(userId);
/*    */     try {
/* 66 */       IAuthenticateGetter authenticateGetter = (IAuthenticateGetter)ADFRegisterFactory.getInstance().getRegister(IAuthenticateGetter.class);
/* 67 */       Set<String> privilegeRoles = authenticateGetter.getUserPrivilege(userId);
/* 68 */       privilege.addRoles(privilegeRoles);
/*    */       
/* 70 */       Set<String> userGroups = MasterAuthenticateManager.getInstance().getUserGroupsByUsername(userId);
/* 71 */       if (userGroups != null) {
/* 72 */         for (String grp : userGroups) {
/* 73 */           Set<String> roles = LocalXmlFileMappingLoader.getLocalRolesByUserGroup(grp);
/* 74 */           if ((roles != null) && (!roles.isEmpty())) {
/* 75 */             privilege.addRoles(roles);
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/*    */     catch (Exception e) {
/* 81 */       return privilege;
/*    */     }
/* 83 */     return privilege;
/*    */   }
/*    */   
/*    */   public void afterPropertiesSet()
/*    */     throws Exception
/*    */   {
/* 89 */     initProviderContext(AbstractAuthProvider.AuthMode.remote);
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\auth\provider\impl\RemoteAuthProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */