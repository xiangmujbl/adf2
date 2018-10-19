/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.manager.region;
/*     */ 
/*     */ import com.gemstone.gemfire.cache.Region;
/*     */ import com.gemstone.gemfire.cache.client.ClientCache;
/*     */ import com.gemstone.gemfire.cache.client.ClientCacheFactory;
/*     */ import com.gemstone.gemfire.cache.client.ClientRegionFactory;
/*     */ import com.gemstone.gemfire.cache.client.ClientRegionShortcut;
/*     */ import com.gemstone.gemfire.cache.client.Pool;
/*     */ import com.gemstone.gemfire.internal.cache.GemFireCacheImpl;
/*     */ import com.jnj.adf.client.api.IBiz;
/*     */ import com.jnj.adf.grid.client.api.IListRegionService;
/*     */ import com.jnj.adf.grid.common.ADFException;
/*     */ import com.jnj.adf.grid.common.GridPathNameUtil;
/*     */ import com.jnj.adf.grid.data.RegionDefine_v3;
/*     */ import com.jnj.adf.grid.manager.GridManager;
/*     */ import com.jnj.adf.grid.manager.RegionHelper;
/*     */ import com.jnj.adf.grid.utils.LogUtil;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.springframework.data.gemfire.function.execution.GemfireOnServerFunctionTemplate;
/*     */ import org.springframework.util.Assert;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ClientRegionCreatorImpl
/*     */   implements ClientRegionCreator
/*     */ {
/*     */   public List<String> createAllClientRegions(String gridId, Pool pool, ClientRegionFactory<?, ?> clientRegionFactory)
/*     */   {
/*  37 */     Assert.notNull(gridId);
/*  38 */     Pool fetchPool = pool;
/*  39 */     if ((fetchPool == null) && (StringUtils.isNotEmpty(gridId))) {
/*  40 */       fetchPool = getPoolByGridName(gridId);
/*     */     }
/*  42 */     Assert.notNull(fetchPool);
/*  43 */     clientRegionFactory.setPoolName(fetchPool.getName());
/*  44 */     List<RegionDefine_v3> fullPathList = listAllPathFromGrid(gridId, fetchPool);
/*  45 */     List<String> names = new ArrayList();
/*  46 */     if (fullPathList != null)
/*     */     {
/*  48 */       for (RegionDefine_v3 def : fullPathList)
/*     */       {
/*  50 */         createProxyRegion(def.getFullPath(), clientRegionFactory);
/*  51 */         LogUtil.getCoreLog().debug("Create proxy region region:{}/{}", new Object[] { gridId, def });
/*     */         
/*  53 */         ClientRegionManager.addLocalPath(def.getFullPath(), gridId);
/*  54 */         names.add(def.getFullPath());
/*     */       }
/*     */     }
/*  57 */     return names;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public List<String> createAllClientRegions(String gridId, Pool pool, ClientRegionShortcut shortcut)
/*     */   {
/*  64 */     Assert.notNull(gridId);
/*  65 */     Pool fetchPool = pool;
/*  66 */     if ((fetchPool == null) && (StringUtils.isNotEmpty(gridId))) {
/*  67 */       fetchPool = getPoolByGridName(gridId);
/*     */     }
/*  69 */     Assert.notNull(fetchPool);
/*  70 */     ClientRegionFactory<?, ?> clientRegionFactory = getClientCache().createClientRegionFactory(shortcut);
/*  71 */     clientRegionFactory.setPoolName(fetchPool.getName());
/*  72 */     return createAllClientRegions(gridId, pool, clientRegionFactory);
/*     */   }
/*     */   
/*     */   public Region createClientRegion(String gridId, String regionName, Pool pool, ClientRegionShortcut shortcut)
/*     */   {
/*  77 */     ClientRegionFactory<?, ?> clientRegionFactory = getClientCache().createClientRegionFactory(shortcut);
/*  78 */     return createClientRegion(gridId, regionName, pool, clientRegionFactory);
/*     */   }
/*     */   
/*     */   public Region createClientRegion(String gridId, String regionName, Pool pool, ClientRegionFactory<?, ?> clientRegionFactory)
/*     */   {
/*  83 */     Assert.notNull(gridId);
/*  84 */     Pool fetchPool = pool;
/*  85 */     if ((fetchPool == null) && (StringUtils.isNotEmpty(gridId))) {
/*  86 */       fetchPool = getPoolByGridName(gridId);
/*     */     }
/*  88 */     clientRegionFactory.setPoolName(fetchPool.getName());
/*  89 */     Region r = createProxyRegion(regionName, clientRegionFactory);
/*  90 */     ClientRegionManager.addLocalPath(regionName, gridId);
/*  91 */     RegionHelper.cacheRegionDefine(regionName);
/*  92 */     LogUtil.getCoreLog().debug("create client region, gridId:{}, region:{}", new Object[] { gridId, regionName });
/*  93 */     return r;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void destroyClientRegions(List<String> fullPaths)
/*     */   {
/* 104 */     for (String regionName : fullPaths)
/*     */     {
/* 106 */       destroyProxyRegion(regionName);
/* 107 */       ClientRegionManager.removeLocalPath(regionName);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void destroyProxyRegion(String regionName)
/*     */   {
/* 115 */     if (StringUtils.isEmpty(regionName)) return;
/* 116 */     ClientCache cc = ClientCacheFactory.getAnyInstance();
/* 117 */     Region r = cc.getRegion(regionName);
/* 118 */     if ((r != null) && (r.subregions(false).size() == 0)) {
/* 119 */       r.localDestroyRegion();
/* 120 */     } else if (r != null) {
/* 121 */       return;
/*     */     }
/* 123 */     destroyProxyRegion(GridPathNameUtil.getParentRegionName(regionName));
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
/*     */   private Region createProxyRegion(String regionName, ClientRegionFactory<?, ?> clientRegionFactory)
/*     */   {
/* 136 */     regionName = GridPathNameUtil.escapePath(regionName);
/* 137 */     String[] names = regionName.split("/");
/* 138 */     Region pr = null;
/* 139 */     Region r = null;
/* 140 */     for (String name : names)
/*     */     {
/* 142 */       if (!StringUtils.isEmpty(name))
/*     */       {
/*     */         try {
/* 145 */           if (pr == null)
/*     */           {
/* 147 */             r = getClientCache().getRegion(name);
/* 148 */             if (r != null)
/*     */             {
/* 150 */               pr = r;
/* 151 */               continue;
/*     */             }
/* 153 */             r = clientRegionFactory.create(name);
/*     */           }
/*     */           else
/*     */           {
/* 157 */             r = pr.getSubregion(name);
/* 158 */             if (r != null)
/*     */             {
/* 160 */               pr = r;
/* 161 */               continue;
/*     */             }
/* 163 */             r = clientRegionFactory.createSubregion(pr, name);
/*     */           }
/*     */           
/*     */         }
/*     */         catch (Exception e)
/*     */         {
/* 169 */           LogUtil.getCoreLog().warn("Create client region failed. path {}, name is {}", new Object[] { regionName, name, e });
/*     */         }
/* 171 */         pr = r;
/*     */       } }
/* 173 */     return r;
/*     */   }
/*     */   
/*     */ 
/*     */   private List<RegionDefine_v3> listAllPathFromGrid(String gridId, Pool pool)
/*     */   {
/* 179 */     Assert.notNull(gridId);
/* 180 */     GemfireOnServerFunctionTemplate template = null;
/* 181 */     if (pool == null) {
/* 182 */       pool = getPoolByGridName(gridId);
/*     */     }
/* 184 */     if (pool == null) {
/* 185 */       throw new ADFException("pool is null, The grid has not connected. gridId:" + gridId);
/*     */     }
/* 187 */     LogUtil.getCoreLog().debug("start to list all regions from grid. gridId : {}, poolName:{}", new Object[] { gridId, pool.getName() });
/*     */     
/* 189 */     IListRegionService lrs = (IListRegionService)IBiz.lookup(IListRegionService.class);
/* 190 */     List<RegionDefine_v3> regionNames = lrs.listNormalRegions(pool);
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
/* 201 */     RegionHelper.cacheRegionDefines(regionNames);
/* 202 */     LogUtil.getCoreLog().debug("before createLocalRegion all client regions. listAllPathFromGrid full paths: {}", new Object[] { regionNames });
/* 203 */     return regionNames;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private Pool getPoolByGridName(String gridName)
/*     */   {
/* 213 */     return GridManager.getInstance().getGemFirePool(gridName);
/*     */   }
/*     */   
/*     */   private GemFireCacheImpl getClientCache()
/*     */   {
/* 218 */     return GemFireCacheImpl.getInstance();
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\manager\region\ClientRegionCreatorImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */