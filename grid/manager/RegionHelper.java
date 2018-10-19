/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.manager;
/*     */ 
/*     */ import com.gemstone.gemfire.cache.Cache;
/*     */ import com.gemstone.gemfire.cache.CacheFactory;
/*     */ import com.gemstone.gemfire.cache.Region;
/*     */ import com.gemstone.gemfire.cache.RegionService;
/*     */ import com.gemstone.gemfire.cache.client.ClientRegionFactory;
/*     */ import com.gemstone.gemfire.cache.client.Pool;
/*     */ import com.gemstone.gemfire.cache.client.internal.ProxyRegion;
/*     */ import com.gemstone.gemfire.internal.cache.GemFireCacheImpl;
/*     */ import com.gemstone.gemfire.internal.cache.LocalDataSet;
/*     */ import com.gemstone.gemfire.internal.cache.LocalRegion;
/*     */ import com.jnj.adf.client.api.IRemoteService;
/*     */ import com.jnj.adf.grid.client.api.support.ADFServiceContext;
/*     */ import com.jnj.adf.grid.common.GridPathNameUtil;
/*     */ import com.jnj.adf.grid.connect.GridConnectionManager;
/*     */ import com.jnj.adf.grid.data.RegionDefine_v3;
/*     */ import com.jnj.adf.grid.manager.region.ClientRegionManager;
/*     */ import com.jnj.adf.grid.namespace.ExecutionContextHelper;
/*     */ import com.jnj.adf.grid.support.context.ADFRuntime;
/*     */ import com.jnj.adf.grid.support.context.IADFMetaService;
/*     */ import com.jnj.adf.grid.support.context.MetaServiceHelper;
/*     */ import com.jnj.adf.grid.support.system.ADFConfigHelper;
/*     */ import com.jnj.adf.grid.support.system.ADFConfigHelper.ITEMS;
/*     */ import com.jnj.adf.grid.utils.LogUtil;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.apache.logging.log4j.Logger;
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
/*     */ public final class RegionHelper
/*     */ {
/*     */   public static final String META_PREIFX = "_ADF";
/*     */   public static final String META_VIEW_PREFIX = "_ADFVIEW";
/*     */   public static final String RUNTIME_PREIFX = "_RTM";
/*     */   public static final String HOST_PREFIX = "_HOST";
/*     */   public static final String PATH_SEPARATOR = "/";
/*  54 */   private static Map<String, RegionDefine_v3> cachedDefine = new ConcurrentHashMap();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void init() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static GemFireCacheImpl getClientcache()
/*     */   {
/*  66 */     return GemFireCacheImpl.getInstance();
/*     */   }
/*     */   
/*     */   public static final RegionDefine_v3 getRegionDefine(Region r)
/*     */   {
/*  71 */     if (ADFRuntime.getRuntime().isServerSide())
/*     */     {
/*  73 */       if ((r != null) && (r.getUserAttribute() != null) && ((r.getUserAttribute() instanceof RegionDefine_v3))) {
/*  74 */         return (RegionDefine_v3)r.getUserAttribute();
/*     */       }
/*     */     }
/*     */     else {
/*  78 */       RegionDefine_v3 def = (RegionDefine_v3)cachedDefine.get(r.getFullPath());
/*  79 */       if (def == null)
/*     */       {
/*  81 */         if (def == null)
/*     */         {
/*  83 */           def = new RegionDefine_v3(false, false);
/*     */         }
/*     */         
/*  86 */         if (def != null)
/*  87 */           cachedDefine.put(r.getFullPath(), def);
/*     */       }
/*  89 */       return def;
/*     */     }
/*  91 */     return null;
/*     */   }
/*     */   
/*     */   public static final void cacheRegionDefine(String path)
/*     */   {
/*  96 */     if ((!cachedDefine.containsKey(path)) && (isNormalRegion(path)))
/*     */     {
/*  98 */       RegionDefine_v3 def = ((IADFMetaService)MetaServiceHelper.remoteService().onServer(path)).getRegionDefine(path);
/*  99 */       if (def == null)
/*     */       {
/* 101 */         def = new RegionDefine_v3(false, false);
/*     */       }
/* 103 */       cachedDefine.put(path, def);
/*     */     }
/*     */   }
/*     */   
/*     */   public static final void cacheRegionDefines(List<RegionDefine_v3> list)
/*     */   {
/* 109 */     for (RegionDefine_v3 def : list)
/*     */     {
/* 111 */       cachedDefine.put(def.getFullPath(), def);
/*     */     }
/*     */   }
/*     */   
/*     */   public static final void refreshRegionDefine(String path)
/*     */   {
/* 117 */     RegionDefine_v3 def = ((IADFMetaService)MetaServiceHelper.remoteService().onServer(path)).getRegionDefine(path);
/* 118 */     if (def == null)
/*     */     {
/* 120 */       def = new RegionDefine_v3(false, false);
/*     */     }
/* 122 */     cachedDefine.put(path, def);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String getGridIdByRegionPath(String regionPath)
/*     */   {
/* 133 */     if (StringUtils.isEmpty(regionPath))
/*     */     {
/* 135 */       return null;
/*     */     }
/*     */     
/* 138 */     String fullPath = GridPathNameUtil.escapePath(regionPath);
/* 139 */     if ((isMetaRegion(fullPath)) || (isRuntimeRegion(fullPath)))
/*     */     {
/* 141 */       LogUtil.getCoreLog().debug("{} is meta or host region,can not mapping to grid.");
/* 142 */       return null;
/*     */     }
/* 144 */     String gridId = ClientRegionManager.getCachedGridIdByPath(fullPath);
/* 145 */     if (!StringUtils.isEmpty(gridId))
/*     */     {
/* 147 */       return gridId;
/*     */     }
/* 149 */     gridId = ClientRegionManager.getRemoteGridIdByPath(fullPath);
/* 150 */     if (StringUtils.isNotEmpty(gridId))
/*     */     {
/* 152 */       ClientRegionManager.addLocalPath(fullPath, gridId);
/* 153 */       return gridId;
/*     */     }
/* 155 */     return ADFServiceContext.defaultGrid();
/*     */   }
/*     */   
/*     */   public static <K, V> Region<K, V> getRegion(String path)
/*     */   {
/* 160 */     return getRegion(path, true);
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
/*     */   public static <K, V> Region<K, V> getRegion(String path, boolean withNM)
/*     */   {
/* 173 */     if (!isNormalRegion(path))
/* 174 */       withNM = false;
/* 175 */     Region r = null;
/* 176 */     if (withNM)
/* 177 */       path = ExecutionContextHelper.processNamespace(path);
/* 178 */     if (!isClientSide())
/*     */     {
/*     */ 
/* 181 */       path = GridPathNameUtil.escapePath(path);
/* 182 */       r = CacheFactory.getAnyInstance().getRegion(path);
/*     */     }
/*     */     else
/*     */     {
/* 186 */       r = getClientRegion(path);
/*     */     }
/* 188 */     if (r == null)
/*     */     {
/* 190 */       if (!isClientSide())
/*     */       {
/* 192 */         LogUtil.getCoreLog().info("init Meta Region, path:{},withNM:{},nm:{}", new Object[] { path, Boolean.valueOf(withNM), 
/* 193 */           ExecutionContextHelper.currentNamespace() });
/*     */       }
/*     */       else
/*     */       {
/* 197 */         LogUtil.getCoreLog().warn("Region is null, path:{},withNM:{},nm:{}", new Object[] { path, Boolean.valueOf(withNM), ExecutionContextHelper.currentNamespace() });
/*     */       }
/*     */     }
/* 200 */     return r;
/*     */   }
/*     */   
/*     */   private static boolean checkClientConnect(String gridName)
/*     */   {
/* 205 */     boolean connected = GridConnectionManager.isConnect(gridName);
/* 206 */     if (!connected)
/*     */     {
/* 208 */       GridManager.getInstance().connectById(gridName);
/* 209 */       connected = GridConnectionManager.isConnect(gridName);
/*     */     }
/* 211 */     return connected;
/*     */   }
/*     */   
/*     */   private static <K, V> Region<K, V> getClientRegion(String path)
/*     */   {
/* 216 */     String gridName = getGridIdByRegionPath(path);
/* 217 */     LogUtil.getCoreLog().debug("Get region for path:{}, gridName:{}", new Object[] { path, gridName });
/* 218 */     Region<K, V> r = null;
/* 219 */     if (StringUtils.isNotEmpty(gridName))
/*     */     {
/* 221 */       checkClientConnect(gridName);
/*     */     }
/* 223 */     RegionService regionService = MultipleUserSupporter.getRegionService(gridName);
/*     */     try
/*     */     {
/* 226 */       r = regionService.getRegion(path);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 230 */       LogUtil.getCoreLog().info("Get client region:{}, path:{} , gridname:{}", new Object[] { r, path, gridName });
/*     */     }
/* 232 */     if (r == null)
/*     */     {
/* 234 */       LogUtil.getCoreLog().info("no proxy, create Proxy Region path:{} , gridname:{}", new Object[] { path, gridName });
/* 235 */       createProxyRegion(gridName, path);
/* 236 */       r = regionService.getRegion(path);
/*     */     }
/*     */     
/* 239 */     LogUtil.getCoreLog().debug("Get client region:{}, path:{} , gridname:{}", new Object[] { r, path, gridName });
/* 240 */     return r;
/*     */   }
/*     */   
/*     */   public static final boolean isMetaRegion(Region r)
/*     */   {
/* 245 */     return isMetaRegion(r.getFullPath());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static final boolean isMetaRegion(String fullPath)
/*     */   {
/* 256 */     String path = fullPath;
/* 257 */     if (StringUtils.startsWith(fullPath, "/"))
/* 258 */       path = fullPath.substring(1);
/* 259 */     return (path.startsWith("_ADF")) || (path.startsWith("_ADFVIEW"));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static final boolean isRuntimeRegion(Region r)
/*     */   {
/* 270 */     String path = r.getFullPath().substring(1);
/* 271 */     return path.startsWith("_RTM");
/*     */   }
/*     */   
/*     */   public static final boolean isRuntimeRegion(String fullPath)
/*     */   {
/* 276 */     String path = fullPath.substring(1);
/* 277 */     return path.startsWith("_RTM");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static final boolean isHostRegion(String fullPath)
/*     */   {
/* 289 */     String path = fullPath.substring(1);
/* 290 */     return path.startsWith("_HOST");
/*     */   }
/*     */   
/*     */   public static final boolean isHostRegion(Region r)
/*     */   {
/* 295 */     String path = r.getFullPath().substring(1);
/* 296 */     return path.startsWith("_HOST");
/*     */   }
/*     */   
/*     */   public static final boolean isNormalRegion(Region r)
/*     */   {
/* 301 */     return (!isMetaRegion(r)) && (!isRuntimeRegion(r)) && (!isHostRegion(r));
/*     */   }
/*     */   
/*     */ 
/*     */   public static final boolean isNormalRegion(String fullPath)
/*     */   {
/* 307 */     return (!isMetaRegion(fullPath)) && (!isRuntimeRegion(fullPath)) && (!isHostRegion(fullPath));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static final boolean isClientSide()
/*     */   {
/* 317 */     return !ADFRuntime.getRuntime().isServerSide();
/*     */   }
/*     */   
/*     */   public static <K, V> Region<K, V> getMetaRegion(String path)
/*     */   {
/* 322 */     StringBuilder builder = new StringBuilder();
/* 323 */     builder.append("_ADF").append("/").append(path);
/* 324 */     String fullpath = builder.toString();
/* 325 */     Region r = getRegion(fullpath, false);
/* 326 */     return r;
/*     */   }
/*     */   
/*     */   public static String getMetaRegionFullPath(String path)
/*     */   {
/* 331 */     StringBuilder builder = new StringBuilder();
/* 332 */     builder.append("_ADF").append("/").append(path);
/* 333 */     String fullpath = builder.toString();
/* 334 */     return fullpath;
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
/*     */ 
/*     */   public static Region createProxyRegion(String gridId, String regionName, ClientRegionFactory<?, ?> clientRegionFactory)
/*     */   {
/* 348 */     Region region = RegionBuilder.newBuilder().setGridName(gridId).setRegionName(regionName).setClientRegionFactory(clientRegionFactory).createRegion();
/*     */     
/* 350 */     return region;
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
/*     */   public static Region createProxyRegion(String gridId, String regionName)
/*     */   {
/* 363 */     Region region = RegionBuilder.newBuilder().setGridName(gridId).setRegionName(regionName).createRegion();
/*     */     
/* 365 */     return region;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @Deprecated
/*     */   public static RegionService getRegionServiceForMultiUser(Pool pool)
/*     */   {
/* 378 */     return MultipleUserSupporter.getRegionServiceForMultiUser(pool);
/*     */   }
/*     */   
/*     */ 
/*     */   public static LocalRegion getLocalRegion(Region<?, ?> region)
/*     */   {
/* 384 */     if ((region instanceof LocalDataSet))
/*     */     {
/* 386 */       return ((LocalDataSet)region).getProxy();
/*     */     }
/* 388 */     if ((region instanceof ProxyRegion))
/*     */     {
/* 390 */       return (LocalRegion)((ProxyRegion)region).getRealRegion();
/*     */     }
/* 392 */     return (LocalRegion)region;
/*     */   }
/*     */   
/*     */   public static Region createParentRegion(String fullpath)
/*     */   {
/* 397 */     return RegionBuilder.newBuilder().setRegionName(fullpath).createLocalRegion();
/*     */   }
/*     */   
/*     */ 
/*     */   public static RegionDefine_v3 getRegionDefine(String fullpath)
/*     */   {
/* 403 */     Region r = getRegion(fullpath);
/* 404 */     return getRegionDefine(r);
/*     */   }
/*     */   
/*     */   public static boolean validateRegion(String fullpath)
/*     */   {
/* 409 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <K, V> Region<K, V> getSessionRegion()
/*     */   {
/* 417 */     String gridId = ADFConfigHelper.getProperty(ITEMS.MASTER_GRID_NAME);
/* 418 */     RegionService regionService = MultipleUserSupporter.getRegionService(gridId);
/* 419 */     Region sessionRegion = regionService.getRegion("/_HOST/SESSION");
/* 420 */     if (sessionRegion == null)
/*     */     {
/* 422 */       createProxyRegion(gridId, "/_HOST/SESSION");
/*     */     }
/* 424 */     sessionRegion = regionService.getRegion("/_HOST/SESSION");
/* 425 */     return sessionRegion;
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\manager\RegionHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */