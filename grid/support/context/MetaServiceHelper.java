/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.support.context;
/*     */ 
/*     */ import com.gemstone.gemfire.cache.Region;
/*     */ import com.gemstone.gemfire.cache.client.ClientCache;
/*     */ import com.gemstone.gemfire.cache.client.ClientCacheFactory;
/*     */ import com.gemstone.gemfire.cache.client.Pool;
/*     */ import com.jnj.adf.client.api.IBiz;
/*     */ import com.jnj.adf.client.api.IRemoteResults;
/*     */ import com.jnj.adf.client.api.IRemoteService;
/*     */ import com.jnj.adf.config.annotations.Path;
/*     */ import com.jnj.adf.config.annotations.RemoteMethod;
/*     */ import com.jnj.adf.config.annotations.RemoteMethod.InvokeTypes;
/*     */ import com.jnj.adf.grid.connect.domain.GridInfo;
/*     */ import com.jnj.adf.grid.data.RegionDefine_v3;
/*     */ import com.jnj.adf.grid.data.schema.ProcessorTypes;
/*     */ import com.jnj.adf.grid.manager.RegionHelper;
/*     */ import com.jnj.adf.grid.utils.LogUtil;
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
/*     */ 
/*     */ public class MetaServiceHelper
/*     */ {
/*     */   static enum KnownMeta
/*     */   {
/*  40 */     BATCHING;
/*     */     
/*     */     private KnownMeta() {}
/*     */   }
/*     */   
/*  45 */   public static final GridInfo fetchGridInfo(Pool fetchPool) { Pool pool = fetchPool;
/*  46 */     IRemoteService<IADFMetaService> srv = IBiz.remote(IADFMetaService.class);
/*  47 */     if (pool == null)
/*     */     {
/*  49 */       pool = ClientCacheFactory.getAnyInstance().getDefaultPool();
/*     */     }
/*  51 */     return ((IADFMetaService)srv.onServer(pool)).getGridName();
/*     */   }
/*     */   
/*     */   public static final IRemoteService<IADFMetaService> remoteService()
/*     */   {
/*  56 */     return IBiz.remote(IADFMetaService.class);
/*     */   }
/*     */   
/*     */   public static final String changeLevel(String path, String level)
/*     */   {
/*  61 */     IRemoteService<IADFMetaService> srv = IBiz.remote(IADFMetaService.class);
/*  62 */     return (String)((IADFMetaService)srv.onServers(path)).changeLogLevel(level).singleResult();
/*     */   }
/*     */   
/*     */   public static final String changeLevel(Pool pool, String level)
/*     */   {
/*  67 */     IRemoteService<IADFMetaService> srv = IBiz.remote(IADFMetaService.class);
/*  68 */     return (String)((IADFMetaService)srv.onServers(pool)).changeLogLevel(level).singleResult();
/*     */   }
/*     */   
/*     */ 
/*     */   @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVER)
/*     */   public RegionDefine_v3 getRegionDefine(@Path String path)
/*     */   {
/*  75 */     Region r = RegionHelper.getRegion(path);
/*  76 */     if ((r != null) && (r.getUserAttribute() != null) && ((r.getUserAttribute() instanceof RegionDefine_v3)))
/*     */     {
/*  78 */       RegionDefine_v3 def = (RegionDefine_v3)r.getUserAttribute();
/*  79 */       return def;
/*     */     }
/*  81 */     return null;
/*     */   }
/*     */   
/*     */   public static final void setBatching(boolean mode, Pool pool)
/*     */   {
/*  86 */     IRemoteService<IADFMetaService> srv = IBiz.remote(IADFMetaService.class);
/*  87 */     String str = ((IADFMetaService)srv.onServers(pool)).setValue(KnownMeta.BATCHING.name(), Boolean.valueOf(mode));
/*  88 */     LogUtil.getCoreLog().info(str);
/*     */   }
/*     */   
/*     */   public static final void enableAQProcessor(ProcessorTypes procType, boolean enabled)
/*     */   {
/*  93 */     enableAQProcessor(procType, enabled, ClientCacheFactory.getAnyInstance().getDefaultPool());
/*     */   }
/*     */   
/*     */ 
/*     */   public static final void enableAQProcessor(ProcessorTypes procType, boolean enabled, Pool pool)
/*     */   {
/*  99 */     IRemoteService<IADFMetaService> srv = IBiz.remote(IADFMetaService.class);
/* 100 */     String result = ((IADFMetaService)srv.onServers(pool)).setAQProcessorEnabled(procType, enabled);
/*     */     
/* 102 */     LogUtil.getCoreLog().info(result);
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\support\context\MetaServiceHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */