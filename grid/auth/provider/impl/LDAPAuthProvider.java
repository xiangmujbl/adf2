/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.auth.provider.impl;
/*     */ 
/*     */ import com.gemstone.gemfire.distributed.DistributedMember;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.jnj.adf.grid.auth.exception.AuthException;
/*     */ import com.jnj.adf.grid.auth.exception.AuthException.ParamNames;
/*     */ import com.jnj.adf.grid.auth.exception.AuthExceptionConverter;
/*     */ import com.jnj.adf.grid.auth.impl.AuthUserPrivilege;
/*     */ import com.jnj.adf.grid.auth.local.LocalXmlFileMappingLoader;
/*     */ import com.jnj.adf.grid.auth.provider.AbstractAuthProvider;
/*     */ import com.jnj.adf.grid.auth.provider.AbstractAuthProvider.AuthMode;
/*     */ import com.jnj.adf.grid.auth.user.AuthUserInfo;
/*     */ import com.jnj.adf.grid.common.GemfireUtil;
/*     */ import com.jnj.adf.grid.support.context.ADFRuntime;
/*     */ import com.jnj.adf.grid.support.ldap.LdapAuthContextSource;
/*     */ import com.jnj.adf.grid.support.ldap.LdapConfigHelper;
/*     */ import com.jnj.adf.grid.support.ldap.LdapUserRoleService;
/*     */ import com.jnj.adf.grid.support.system.ADFConfigHelper;
/*     */ import com.jnj.adf.grid.support.system.ADFConfigHelper.ITEMS;
/*     */ import com.jnj.adf.grid.utils.LogUtil;
/*     */ import java.text.MessageFormat;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.naming.directory.DirContext;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.springframework.beans.factory.InitializingBean;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.data.gemfire.function.execution.GemfireOnMemberFunctionTemplate;
/*     */ import org.springframework.ldap.AuthenticationException;
/*     */ import org.springframework.ldap.CommunicationException;
/*     */ import org.springframework.ldap.NamingException;
/*     */ import org.springframework.ldap.support.LdapUtils;
/*     */ import org.springframework.stereotype.Component;
/*     */ 
/*     */ @Component
/*     */ public class LDAPAuthProvider
/*     */   extends AbstractAuthProvider
/*     */   implements InitializingBean
/*     */ {
/*  40 */   private static final String dnPattern = ;
/*     */   
/*     */   @Autowired
/*     */   private LdapAuthContextSource authContextSource;
/*     */   
/*     */   @Autowired
/*     */   private LdapUserRoleService ldapUserRoleService;
/*     */   
/*     */   public String verify(String username, String pwd)
/*     */     throws AuthException
/*     */   {
/*  51 */     boolean result = false;
/*  52 */     DirContext ctx = null;
/*     */     try {
/*  54 */       String distinguishedName = this.ldapUserRoleService.getDistinguishedNameByUserId(username);
/*  55 */       String principal = MessageFormat.format(dnPattern, new Object[] { distinguishedName });
/*  56 */       ctx = this.authContextSource.getContext(principal, pwd);
/*  57 */       result = true;
/*     */     } catch (AuthenticationException e) {
/*  59 */       LogUtil.getAppLog().error("Authentication error" + e.getMessage());
/*  60 */       String badPwdCount = getBadPasswordCount(username);
/*  61 */       Map<String, String> exceptionParams = Maps.newHashMap();
/*  62 */       exceptionParams.put(AuthException.ParamNames.BAD_PASSWORD_COUNT.name(), badPwdCount);
/*  63 */       throw AuthExceptionConverter.convertException(e, exceptionParams);
/*     */     } catch (CommunicationException e) {
/*  65 */       LogUtil.getAppLog().error("can not connect to ldap server", e);
/*  66 */       throw AuthExceptionConverter.convertException(e);
/*     */     } catch (NamingException e) {
/*  68 */       LogUtil.getAppLog().error("NamingException" + e.getMessage());
/*  69 */       throw AuthExceptionConverter.convertException(e);
/*     */     }
/*     */     finally
/*     */     {
/*  73 */       if (ctx != null) {
/*  74 */         LdapUtils.closeContext(ctx);
/*     */       }
/*     */     }
/*  77 */     if (result) {
/*  78 */       return username;
/*     */     }
/*  80 */     return null;
/*     */   }
/*     */   
/*     */   public AuthUserInfo findUser(String userId)
/*     */     throws AuthException
/*     */   {
/*  86 */     Set<String> userGroups = this.ldapUserRoleService.getUserGroupsById(userId);
/*  87 */     if (userGroups != null) {
/*  88 */       AuthUserInfo userInfo = new AuthUserInfo();
/*  89 */       userInfo.setUserId(userId);
/*  90 */       userInfo.setAdGroups(userGroups);
/*  91 */       return userInfo;
/*     */     }
/*     */     
/*  94 */     return null;
/*     */   }
/*     */   
/*     */   public AuthUserPrivilege findUserPrivilege(String userId) throws AuthException
/*     */   {
/*  99 */     AuthUserPrivilege privilege = new AuthUserPrivilege();
/* 100 */     privilege.setUserId(userId);
/*     */     
/*     */ 
/* 103 */     Set<String> grps = this.ldapUserRoleService.getUserGroupsById(userId);
/* 104 */     if (grps != null) {
/* 105 */       for (String grp : grps) {
/* 106 */         Set<String> roles = LocalXmlFileMappingLoader.getLocalRolesByUserGroup(grp);
/* 107 */         if ((roles != null) && (!roles.isEmpty())) {
/* 108 */           privilege.addRoles(roles);
/*     */         }
/*     */       }
/*     */     }
/* 112 */     Object additionUserRoles = getUserRolesByUserGroupsFromRegion(grps);
/* 113 */     if (additionUserRoles != null) {
/* 114 */       privilege.addRoles((Set)additionUserRoles);
/*     */     }
/* 116 */     return privilege;
/*     */   }
/*     */   
/*     */ 
/*     */   private Set<String> getUserRolesByUserGroupsFromRegion(Set<String> userGroups)
/*     */   {
/* 122 */     if ((ADFConfigHelper.getBoolean(ITEMS.AS_MASTER_GRID)) &&
/* 123 */       (ADFRuntime.getRuntime().isServerSide())) {
/* 124 */       DistributedMember member = GemfireUtil.getAnyMemberOnServerSide();
/* 125 */       if (member != null) {
/* 126 */         LogUtil.getCoreLog().debug("execute function {} on member:{}", new Object[] { "HostMemberFunction.getUserRolesByUserGroupsFromRegion", member });
/* 127 */         GemfireOnMemberFunctionTemplate functionTemplate = new GemfireOnMemberFunctionTemplate(member);
/* 128 */         Object result = functionTemplate.executeAndExtract("HostMemberFunction.getUserRolesByUserGroupsFromRegion", new Object[] { userGroups });
/* 129 */         LogUtil.getCoreLog().debug("execute function {} on member:{} param:{}, result:{}", new Object[] { "HostMemberFunction.getUserRolesByUserGroupsFromRegion", member, userGroups, result });
/* 130 */         if ((result instanceof Set)) {
/* 131 */           LogUtil.getCoreLog().debug("find user roles from master region. userGroups:{}, userRoles;{}", new Object[] { userGroups, (Set)result });
/* 132 */           return (Set)result;
/*     */         }
/*     */       }
/*     */     }
/* 136 */     LogUtil.getCoreLog().debug("not find user roles from master region.");
/* 137 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   private String getBadPasswordCount(String userId)
/*     */   {
/* 143 */     Map<String, String> row = this.ldapUserRoleService.getUserAttributes(userId, new String[] { "badPwdCount" });
/* 144 */     if (row != null) {
/* 145 */       return (String)row.get("badPwdCount");
/*     */     }
/* 147 */     return "";
/*     */   }
/*     */   
/*     */   public void afterPropertiesSet()
/*     */     throws Exception
/*     */   {
/* 153 */     initProviderContext(AbstractAuthProvider.AuthMode.ldap);
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\auth\provider\impl\LDAPAuthProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */