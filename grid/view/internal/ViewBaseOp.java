/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.view.internal;
/*     */ 
/*     */ import com.gemstone.gemfire.cache.client.Pool;
/*     */ import com.google.common.collect.Sets;
/*     */ import com.jnj.adf.client.api.IBiz;
/*     */ import com.jnj.adf.client.api.IRemoteService;
/*     */ import com.jnj.adf.grid.auth.impl.AuthSessionContextHelper;
/*     */ import com.jnj.adf.grid.auth.impl.AuthUserPrivilege;
/*     */ import com.jnj.adf.grid.auth.session.AuthInternalSession;
/*     */ import com.jnj.adf.grid.client.api.support.ADFServiceContext;
/*     */ import com.jnj.adf.grid.client.api.support.ADFServiceContext.Keys;
/*     */ import com.jnj.adf.grid.security.ISecurityConfigService;
/*     */ import com.jnj.adf.grid.security.column.IColumnPrivilegeService;
/*     */ import com.jnj.adf.grid.utils.LogUtil;
/*     */ import com.jnj.adf.grid.view.domain.ViewColumnMapping;
/*     */ import com.jnj.adf.grid.view.domain.ViewGroup;
/*     */ import com.jnj.adf.grid.view.domain.ViewMeta;
/*     */ import com.jnj.adf.grid.view.manager.IViewManagerService;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class ViewBaseOp extends RegionBaseOp
/*     */ {
/*     */   public ViewBaseOp() {}
/*     */   
/*     */   public ViewBaseOp(boolean isMaster)
/*     */   {
/*  31 */     super(isMaster);
/*     */   }
/*     */   
/*     */   public ViewBaseOp(String path) {
/*  35 */     super(path);
/*     */   }
/*     */   
/*     */   public ViewBaseOp(String gridId, String locators) {
/*  39 */     super(gridId, locators);
/*     */   }
/*     */   
/*     */   public boolean isView(String path) {
/*  43 */     IRemoteService<IViewManagerService> irs = IBiz.remote(IViewManagerService.class);
/*  44 */     Pool pool = getPool();
/*  45 */     if (pool == null)
/*  46 */       return false;
/*  47 */     return ((IViewManagerService)irs.onServer(pool)).isView(path);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void deleteView(String viewPath)
/*     */   {
/*  55 */     IRemoteService<IViewManagerService> irs = IBiz.remote(IViewManagerService.class);
/*  56 */     Pool pool = getPool();
/*  57 */     if (pool != null)
/*  58 */       ((IViewManagerService)irs.onServer(pool)).deleteView(viewPath);
/*     */   }
/*     */   
/*     */   public List<com.jnj.adf.grid.view.domain.ViewColumn> listColumns(String viewPath) {
/*  62 */     IRemoteService<IViewManagerService> irs = IBiz.remote(IViewManagerService.class);
/*  63 */     Pool pool = getPool();
/*  64 */     if (pool == null)
/*  65 */       return null;
/*  66 */     return ((IViewManagerService)irs.onServer(pool)).listColumns(viewPath);
/*     */   }
/*     */   
/*     */   public List<ViewGroup> listUnionGroups(String viewPath) {
/*  70 */     IRemoteService<IViewManagerService> irs = IBiz.remote(IViewManagerService.class);
/*  71 */     Pool pool = getPool();
/*  72 */     if (pool == null)
/*  73 */       return null;
/*  74 */     return ((IViewManagerService)irs.onServer(pool)).listUnionGroups(viewPath);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void deleteUnion(String viewPath, String unionCode)
/*     */   {
/*  83 */     IRemoteService<IViewManagerService> irs = IBiz.remote(IViewManagerService.class);
/*  84 */     Pool pool = getPool();
/*  85 */     if (pool != null) {
/*  86 */       ((IViewManagerService)irs.onServer(pool)).deleteUnion(viewPath, unionCode);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<ViewColumnMapping> listColumnMappings(String viewPath, String groupCode)
/*     */   {
/*  96 */     IRemoteService<IViewManagerService> irs = IBiz.remote(IViewManagerService.class);
/*  97 */     Pool pool = getPool();
/*  98 */     if (pool == null)
/*  99 */       return null;
/* 100 */     return ((IViewManagerService)irs.onServer(pool)).listColumnMappings(viewPath, groupCode);
/*     */   }
/*     */   
/*     */   public List<ViewMeta> listViewMetas(String rawPath) {
/* 104 */     IRemoteService<IViewManagerService> irs = IBiz.remote(IViewManagerService.class);
/* 105 */     Pool pool = getPool();
/* 106 */     if (pool == null) {
/* 107 */       return null;
/*     */     }
/* 109 */     return ((IViewManagerService)irs.onServer(pool)).listViewMetas(rawPath);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Map<String, List<ViewMeta>> listAllViewMetas()
/*     */   {
/* 117 */     IRemoteService<IViewManagerService> irs = IBiz.remote(IViewManagerService.class);
/* 118 */     Pool pool = getPool();
/* 119 */     if (pool == null) {
/* 120 */       return null;
/*     */     }
/* 122 */     return ((IViewManagerService)irs.onServer(pool)).listAllViewMetas();
/*     */   }
/*     */   
/*     */   public List<ViewMeta> getViewMeta(String viewPath) {
/* 126 */     IRemoteService<IViewManagerService> irs = IBiz.remote(IViewManagerService.class);
/* 127 */     Pool pool = getPool();
/* 128 */     if (pool == null) {
/* 129 */       return null;
/*     */     }
/* 131 */     return ((IViewManagerService)irs.onServer(pool)).getViewMeta(viewPath);
/*     */   }
/*     */   
/*     */   public String deploy(String viewPath) {
/* 135 */     IRemoteService<IViewManagerService> irs = IBiz.remote(IViewManagerService.class);
/* 136 */     Pool pool = getPool();
/* 137 */     if (pool != null)
/* 138 */       return ((IViewManagerService)irs.onServer(pool)).deploy(viewPath);
/* 139 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void fullCreate(String viewPath)
/*     */   {
/* 147 */     ((IViewManagerService)IBiz.lookup(IViewManagerService.class)).fullCreate(viewPath);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getObjectsSourcesByPath(String path)
/*     */   {
/* 155 */     IRemoteService<ISecurityConfigService> irs = IBiz.remote(ISecurityConfigService.class);
/*     */     
/* 157 */     Pool pool = getPool();
/* 158 */     if (pool != null)
/* 159 */       return ((ISecurityConfigService)irs.onServer(pool)).getObjectsSourcesByPath(path);
/* 160 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Map<String, String> getObjectsSourcesByPaths(Set<String> pathSet)
/*     */   {
/* 168 */     IRemoteService<ISecurityConfigService> irs = IBiz.remote(ISecurityConfigService.class);
/*     */     
/* 170 */     Pool pool = getPool();
/* 171 */     if (pool != null)
/* 172 */       return ((ISecurityConfigService)irs.onServer(pool)).getObjectsSourcesByPaths(pathSet);
/* 173 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Set<String> filterPaths(Set<String> paths, String viewPath)
/*     */   {
/* 183 */     IRemoteService<IColumnPrivilegeService> irs = IBiz.remote(IColumnPrivilegeService.class);
/*     */     
/* 185 */     Pool pool = getPool();
/* 186 */     if (pool != null) {
/* 187 */       String userId = null;
/* 188 */       Set<String> userRoles = null;
/* 189 */       AuthInternalSession session = (AuthInternalSession)ADFServiceContext.getValue(ADFServiceContext.Keys.SESSION);
/* 190 */       if (session != null) {
/* 191 */         userId = session.getUserId();
/* 192 */         if (session.getUserPrivilege() != null) {
/* 193 */           userRoles = session.getUserPrivilege().getRoles();
/*     */         }
/*     */       }
/*     */       
/* 197 */       LogUtil.getCoreLog().info("ViewBaseOp filterPaths userId is {} user Roles is {}", new Object[] { userId, userRoles });
/* 198 */       return ((IColumnPrivilegeService)irs.onServer(pool)).filterViewRawPaths(userId, userRoles, paths, viewPath);
/*     */     }
/* 200 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Map<String, Map<String, String>> filterColumns(HashMap<String, Set<String>> columnSet, String viewPath)
/*     */   {
/* 212 */     IRemoteService<IColumnPrivilegeService> irs = IBiz.remote(IColumnPrivilegeService.class);
/*     */     
/* 214 */     Pool pool = getPool();
/* 215 */     if (pool != null) {
/* 216 */       String userId = AuthSessionContextHelper.getUserId();
/* 217 */       Set<String> userRoles = getCurrentUserRoles();
/* 218 */       return ((IColumnPrivilegeService)irs.onServer(pool)).filterViewColumns(userId, userRoles, columnSet, viewPath);
/*     */     }
/* 220 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void pushRule(String viewPath, String ruleName, String content, ViewMeta meta)
/*     */   {
/* 231 */     IRemoteService<IViewManagerService> irs = IBiz.remote(IViewManagerService.class);
/*     */     
/* 233 */     Pool pool = getPool();
/* 234 */     if (pool != null) {
/* 235 */       ((IViewManagerService)irs.onServers(pool)).pushRule(viewPath, ruleName, content, meta);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void pushRules(String viewPath, Map<String, String> rules, List<ViewMeta> metas)
/*     */   {
/* 246 */     IRemoteService<IViewManagerService> irs = IBiz.remote(IViewManagerService.class);
/*     */     
/* 248 */     Pool pool = getPool();
/* 249 */     if (pool != null)
/* 250 */       ((IViewManagerService)irs.onServers(pool)).pushRules(viewPath, rules, metas);
/*     */   }
/*     */   
/*     */   public Set<String> getCurrentUserRoles() {
/* 254 */     Set<String> roles = Sets.newHashSet();
/* 255 */     if (AuthSessionContextHelper.getTlSession() != null)
/*     */     {
/* 257 */       roles.addAll(AuthSessionContextHelper.getTlSession().getUserPrivilege().getRoles());
/*     */     }
/* 259 */     return roles;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Map<String, String> listRules()
/*     */   {
/* 268 */     IRemoteService<IViewManagerService> irs = IBiz.remote(IViewManagerService.class);
/*     */     
/* 270 */     Pool pool = getPool();
/* 271 */     if (pool != null)
/* 272 */       return ((IViewManagerService)irs.onServer(pool)).listRules();
/* 273 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void deleteRules(Set<String> rules)
/*     */   {
/* 282 */     IRemoteService<IViewManagerService> irs = IBiz.remote(IViewManagerService.class);
/*     */     
/* 284 */     Pool pool = getPool();
/* 285 */     if (pool != null) {
/* 286 */       ((IViewManagerService)irs.onServers(pool)).deleteRules(rules);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void invalidCache(String viewPath, Set<String> rules)
/*     */   {
/* 296 */     IRemoteService<IViewManagerService> irs = IBiz.remote(IViewManagerService.class);
/*     */     
/* 298 */     Pool pool = getPool();
/* 299 */     if (pool != null) {
/* 300 */       ((IViewManagerService)irs.onServers(pool)).invalidCache(viewPath, rules);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Set<String> listRawMainPaths(String viewPath)
/*     */   {
/* 309 */     IRemoteService<IViewManagerService> irs = IBiz.remote(IViewManagerService.class);
/*     */     
/* 311 */     Pool pool = getPool();
/* 312 */     if (pool != null)
/* 313 */       return ((IViewManagerService)irs.onServers(pool)).listRawMainPaths(viewPath);
/* 314 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\view\internal\ViewBaseOp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */