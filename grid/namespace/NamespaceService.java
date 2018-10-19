/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.namespace;
/*     */ 
/*     */ import com.gemstone.gemfire.cache.Cache;
/*     */ import com.gemstone.gemfire.cache.CacheFactory;
/*     */ import com.gemstone.gemfire.cache.Region;
/*     */ import com.jnj.adf.client.api.IBiz;
/*     */ import com.jnj.adf.client.api.IRemoteService;
/*     */ import com.jnj.adf.grid.manager.GridManager;
/*     */ import com.jnj.adf.grid.manager.RegionHelper;
/*     */ import com.jnj.adf.grid.namespace.domain.NamespaceDomain;
/*     */ import com.jnj.adf.grid.namespace.master.MasterRegionService;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NamespaceService
/*     */ {
/*     */   public static final String ID = "adf.namespace.service";
/*     */   private static final String MASTER_REGION_NAME = "/_HOST/NAMESPACE";
/*     */   private static final String DG_REGION_NAME = "/_ADF/NAMESPACE";
/*     */   private static final String ALL = "*";
/*  23 */   private static final NamespaceService inst = new NamespaceService();
/*     */   
/*     */   public static final NamespaceService getInstance()
/*     */   {
/*  27 */     return inst;
/*     */   }
/*     */   
/*     */   public MasterRegionService service()
/*     */   {
/*  32 */     IRemoteService<MasterRegionService> srv = IBiz.remote(MasterRegionService.class);
/*  33 */     MasterRegionService mrs = (MasterRegionService)srv.onServer(GridManager.getInstance().getMasterPool());
/*  34 */     return mrs;
/*     */   }
/*     */   
/*     */   public void saveOnMaster(String gridId, String path, String nm)
/*     */   {
/*  39 */     NamespaceDomain nd = new NamespaceDomain();
/*  40 */     nd.genKey(gridId, path);
/*  41 */     nd.genValue(nm);
/*  42 */     service().put("/_HOST/NAMESPACE", nd.getKey(), nd.getValue());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getFromMaster(String gridId, String path)
/*     */   {
/*  53 */     NamespaceDomain nd = new NamespaceDomain();
/*  54 */     nd.genKey(gridId, path);
/*  55 */     return (String)service().get("/_HOST/NAMESPACE", nd.getKey());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void synchronizeWithMaster(String gridId)
/*     */   {
/*  65 */     List<String> list = service().oql("/_HOST/NAMESPACE", "select * from /_HOST/NAMESPACE where gridId=$1", new Object[] { gridId });
/*     */     
/*  67 */     Region dgRegion = CacheFactory.getAnyInstance().getRegion("/_ADF/NAMESPACE");
/*  68 */     if (list != null)
/*     */     {
/*  70 */       for (String value : list)
/*     */       {
/*  72 */         NamespaceDomain nd = new NamespaceDomain();
/*  73 */         nd.parse(value);
/*  74 */         dgRegion.put(nd.getPath(), nd.getNamespace());
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String findNamespace(String path)
/*     */   {
/*  87 */     if (!RegionHelper.isNormalRegion(path))
/*  88 */       return null;
/*  89 */     Region dgRegion = CacheFactory.getAnyInstance().getRegion("/_ADF/NAMESPACE");
/*  90 */     if (dgRegion.containsKey(path))
/*     */     {
/*  92 */       String nm = (String)dgRegion.get(path);
/*  93 */       return nm;
/*     */     }
/*  95 */     if (dgRegion.containsKey("*"))
/*     */     {
/*  97 */       String nm = (String)dgRegion.get("*");
/*  98 */       return nm;
/*     */     }
/* 100 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\namespace\NamespaceService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */