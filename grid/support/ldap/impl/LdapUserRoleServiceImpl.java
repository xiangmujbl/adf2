/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.support.ldap.impl;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Sets;
/*     */ import com.jnj.adf.grid.support.ldap.LdapConfigHelper;
/*     */ import com.jnj.adf.grid.support.ldap.LdapUserRoleService;
/*     */ import com.jnj.adf.grid.support.ldap.LdapUserTemplate;
/*     */ import com.jnj.adf.grid.utils.LogUtil;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.naming.NamingException;
/*     */ import javax.naming.directory.Attribute;
/*     */ import javax.naming.directory.Attributes;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.ldap.core.AttributesMapper;
/*     */ import org.springframework.ldap.query.ConditionCriteria;
/*     */ import org.springframework.ldap.query.ContainerCriteria;
/*     */ import org.springframework.ldap.query.LdapQuery;
/*     */ import org.springframework.ldap.query.LdapQueryBuilder;
/*     */ import org.springframework.stereotype.Component;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Component
/*     */ public class LdapUserRoleServiceImpl
/*     */   implements LdapUserRoleService
/*     */ {
/*     */   @Autowired
/*     */   private LdapUserTemplate ldapUserTemplate;
/*     */   @Autowired
/*     */   private LdapUserTemplate ldapGroupTemplate;
/*     */   
/*     */   public Set<String> getUserGroupsById(String userId)
/*     */   {
/*  40 */     String userDistinguishedName = getDistinguishedNameByUserId(userId);
/*  41 */     if (StringUtils.isNotEmpty(userDistinguishedName)) {
/*  42 */       Set<String> userGroups = searchUserGroupsByDistinguishedName(userId, userDistinguishedName);
/*  43 */       return userGroups;
/*     */     }
/*  45 */     return null;
/*     */   }
/*     */   
/*     */   public Set<String> searchUserGroupNames(String groupName, int limit)
/*     */   {
/*  50 */     return searchUserGroupsByName(groupName, limit);
/*     */   }
/*     */   
/*     */ 
/*     */   public Map<String, String> getUserAttributes(String userId, final String... attributes)
/*     */   {
/*  56 */     String userBaseDn = LdapConfigHelper.getLdapUserSearchBaseDn();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  62 */     LdapQuery userQuery = LdapQueryBuilder.query().base(userBaseDn).attributes(attributes).countLimit(1).where("objectclass").is("user").and("sAMAccountName").is(userId);
/*     */     
/*     */ 
/*  65 */     List<Map<String, String>> userAttributes = this.ldapUserTemplate.search(userQuery, new AttributesMapper()
/*     */     {
/*     */       public Map<String, String> mapFromAttributes(Attributes attrs) throws NamingException {
/*  68 */         if (attrs != null) {
/*  69 */           Map<String, String> row = Maps.newHashMap();
/*  70 */           for (String attribute : attributes) {
/*  71 */             Attribute a = attrs.get(attribute);
/*  72 */             if (a != null) {
/*  73 */               row.put(attribute, (String)a.get());
/*     */             }
/*     */           }
/*  76 */           return row;
/*     */         }
/*  78 */         return null;
/*     */       }
/*     */     });
/*     */     
/*  82 */     if ((userAttributes != null) && (!userAttributes.isEmpty())) {
/*  83 */       return (Map)userAttributes.get(0);
/*     */     }
/*  85 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getDistinguishedNameByUserId(String username)
/*     */   {
/*  95 */     String distinguishedName = null;
/*  96 */     String userBaseDn = LdapConfigHelper.getLdapUserSearchBaseDn();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 102 */     LdapQuery userQuery = LdapQueryBuilder.query().base(userBaseDn).attributes(new String[] { "distinguishedName", "sAMAccountName" }).countLimit(1).where("objectclass").is("user").and("sAMAccountName").is(username);
/*     */     
/*     */ 
/* 105 */     List<String> userDistinguishedNames = this.ldapUserTemplate.search(userQuery, new AttributesMapper()
/*     */     {
/*     */       public String mapFromAttributes(Attributes attributes) throws NamingException {
/* 108 */         return (String)attributes.get("distinguishedName").get();
/*     */       }
/*     */     });
/*     */     
/*     */ 
/* 113 */     if ((userDistinguishedNames != null) && (!userDistinguishedNames.isEmpty())) {
/* 114 */       distinguishedName = (String)userDistinguishedNames.get(0);
/*     */     }
/* 116 */     LogUtil.getAppLog().debug("getDistinguishedName username={}, distinguishedName={}", new Object[] { username, distinguishedName });
/*     */     
/* 118 */     return distinguishedName;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private Set<String> searchUserGroupsByDistinguishedName(String userName, String userDistinguishedName)
/*     */   {
/* 129 */     final Set<String> userGroupNames = Sets.newHashSet();
/* 130 */     String groupBaseDn = LdapConfigHelper.getLdapGroupSearchBaseDn();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 135 */     LdapQuery groupQuery = LdapQueryBuilder.query().base(groupBaseDn).attributes(new String[] { "sAMAccountName" }).countLimit(1000).where("objectclass").is("group").and("member").is(userDistinguishedName);
/*     */     
/* 137 */     this.ldapGroupTemplate.search(groupQuery, new AttributesMapper()
/*     */     {
/*     */       public String mapFromAttributes(Attributes attributes) throws NamingException {
/* 140 */         String groupName = (String)attributes.get("sAMAccountName").get();
/* 141 */         userGroupNames.add(groupName);
/* 142 */         return null;
/*     */       }
/*     */       
/*     */ 
/* 146 */     });
/* 147 */     LogUtil.getAppLog().debug("searchUserGroups username={} usergourps={}", new Object[] { userName, userGroupNames });
/* 148 */     return userGroupNames;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private Set<String> searchUserGroupsByName(String keyword, int limit)
/*     */   {
/* 161 */     final Set<String> userGroupNames = Sets.newHashSet();
/* 162 */     String groupBaseDn = LdapConfigHelper.getLdapGroupSearchBaseDn();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 167 */     LdapQuery groupQuery = LdapQueryBuilder.query().base(groupBaseDn).attributes(new String[] { "sAMAccountName" }).countLimit(limit).where("objectclass").is("group").and("sAMAccountName").whitespaceWildcardsLike(keyword);
/*     */     
/* 169 */     this.ldapGroupTemplate.search(groupQuery, new AttributesMapper()
/*     */     {
/*     */       public String mapFromAttributes(Attributes attributes) throws NamingException {
/* 172 */         String groupName = (String)attributes.get("sAMAccountName").get();
/* 173 */         userGroupNames.add(groupName);
/* 174 */         return null;
/*     */       }
/*     */       
/*     */ 
/* 178 */     });
/* 179 */     LogUtil.getAppLog().info("searchUserGroups keyword={}, limit={},  gourps={}", new Object[] { keyword, Integer.valueOf(limit), userGroupNames });
/* 180 */     return userGroupNames;
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\support\ldap\impl\LdapUserRoleServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */