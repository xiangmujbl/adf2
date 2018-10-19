/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.support.ldap;
/*    */ 
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract interface LdapUserRoleService
/*    */ {
/*    */   public abstract String getDistinguishedNameByUserId(String paramString);
/*    */   
/*    */   public abstract Set<String> getUserGroupsById(String paramString);
/*    */   
/*    */   public abstract Map<String, String> getUserAttributes(String paramString, String... paramVarArgs);
/*    */   
/*    */   public abstract Set<String> searchUserGroupNames(String paramString, int paramInt);
/*    */   
/*    */   public Set<String> searchUserGroupNames(String groupName)
/*    */   {
/* 45 */     return searchUserGroupNames(groupName, 100);
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\support\ldap\LdapUserRoleService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */