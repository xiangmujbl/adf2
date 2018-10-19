/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.auth;
/*     */ 
/*     */ import com.gemstone.gemfire.management.internal.cli.functions.DeployFunction;
/*     */ import com.gemstone.gemfire.management.internal.cli.functions.ListDeployedFunction;
/*     */ import com.gemstone.gemfire.management.internal.cli.functions.UndeployFunction;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AuthKeyConstants
/*     */ {
/*     */   public static final String KEY_USERNAME = "userId";
/*     */   public static final String KEY_SERVICE_USERNAME = "security-service-account-username";
/*     */   public static final String KEY_SERVICE_PASSWORD = "security-service-account-password";
/*     */   public static final String KEY_TOKEN = "token";
/*     */   public static final String KEY_ROLE = "roles";
/*     */   public static final String KEY_ADGORUPS = "ad_groups";
/*     */   public static final String KEY_CREDENTIAL_SIGNATURE = "_credential_signature_";
/*     */   public static final String defaultAnonymousUserName = "anonymous";
/*     */   public static final String defaultManagerUserName = "manager";
/*     */   public static final String SUPER_ADMIN_ROLE = "_SU_ADMIN_";
/*     */   public static final String GRID_MANAGER_ROLE = "_GD_MANAGER_";
/*     */   public static final String GRID_REGISTER_ROLE = "_M_REGISTER_";
/*     */   public static final String ANONYMOUS_ROLE = "_ANONYMOUS_";
/*     */   public static final String ANY_ONE_ROLE = "_ANY_ONE_";
/*     */   public static final String N_SERVICE_LUCENE = "LuceneIndexer";
/*     */   
/*     */   public static enum GridPermission
/*     */   {
/*  53 */     ALLOW,  DEPENDS_ON,  NO_ALLOW;
/*     */     
/*     */     private GridPermission() {}
/*     */   }
/*     */   
/*     */   public static enum Operations
/*     */   {
/*  60 */     OPERATION_R, 
/*  61 */     OPERATION_W, 
/*  62 */     OPERATION_M, 
/*  63 */     FUNCTION_E;
/*     */     
/*     */     private Operations() {} }
/*     */   
/*  67 */   public static enum PublicRegions { MANAGEMENT_REGION("/_ADF/MANAGEMENT"),  META_REGION("/_ADF/META"),  SESSION_REGION("/_HOST/SESSION");
/*     */     
/*  69 */     private PublicRegions(String v) { this.value = v; }
/*     */     
/*     */     private String value;
/*  72 */     public String getValue() { return this.value; }
/*     */   }
/*     */   
/*     */ 
/*     */   public static enum PublicFunctions
/*     */   {
/*  78 */     LIST_REGION_FUNCTION("com.jnj.adf.grid.list.region"), 
/*  79 */     CLUSTER_STATUS_FUNCTION("com.jnj.adf.grid.status"), 
/*  80 */     SEQUENCEIDSERVICE_F_GETSEQUENCEID("adf.meta.getSequenceId"), 
/*  81 */     SEQUENCEIDSERVICE_F_GETSEQUENCEIDS("adf.meta.getSequenceIds"), 
/*  82 */     REMOTESERVICEFUNCTION_ON_SERVER_SERVICE("adf.remoteServiceExecutor.onSever"), 
/*  83 */     REMOTESERVICEFUNCTION_ON_REGION_SERVICE("adf.remoteServiceExecutor.onRegion"), 
/*  84 */     REMOTESERVICEFUNCTION_ON_SERVER_SERVICE_V2("adf.remoteServiceExecutor.onSever.v2"), 
/*  85 */     REMOTESERVICEFUNCTION_ON_REGION_SERVICE_V2("adf.remoteServiceExecutor.onRegion.v2"), 
/*  86 */     REMOTESECURITY_FUNCTIONNAME_GRIDPERMISSION("SecurityMemberFunction.getDataGridPrivilegeByRegion"), 
/*  87 */     REMOTESECURITY_FUNCTIONNAME_ROLEPERMISSION("SecurityMemberFunction.getDataRolePrivilegeByRegion"), 
/*  88 */     REMOTESECURITY_FUNCTIONNAME_REGIONMETA("SecurityMemberFunction.getObjectMetaByKey"), 
/*  89 */     REMOTESECURITY_FUNCTIONNAME_LIST_REGIONMETA("SecurityMemberFunction.listObjectMetaByKey"), 
/*  90 */     REMOTESECURITY_FUNCTIONNAME_UPDATE_REGIONMETA("SecurityMemberFunction.updateObjectMetaByKey"), 
/*  91 */     REMOTEHOST_FUNCTIONNAME_FIND_USERROLES("HostMemberFunction.getUserRolesByUserGroupsFromRegion"), 
/*  92 */     LIST_DEPLOY_FUNCTION(ListDeployedFunction.ID), 
/*  93 */     DEPLOY_FUNCTION(DeployFunction.ID), 
/*  94 */     UNDEPLOY_FUNCTION(UndeployFunction.ID);
/*     */     
/*     */     private PublicFunctions(String v) {
/*  97 */       this.value = v;
/*     */     }
/*     */     
/* 100 */     public String getValue() { return this.value; }
/*     */     
/*     */     private String value;
/*     */   }
/*     */   
/*     */   public static enum PublicMBeanServiceNames
/*     */   {
/* 107 */     SERVICE_LUCENE("LuceneIndexer");
/*     */     
/*     */ 
/* 110 */     private PublicMBeanServiceNames(String v) { this.value = v; }
/*     */     
/*     */     private String value;
/* 113 */     public String getValue() { return this.value; }
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\auth\AuthKeyConstants.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */