/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.manager;
/*     */ 
/*     */ import com.gemstone.gemfire.cache.Region;
/*     */ import com.gemstone.gemfire.cache.client.ClientRegionFactory;
/*     */ import com.gemstone.gemfire.cache.client.ClientRegionShortcut;
/*     */ import com.gemstone.gemfire.cache.client.Pool;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.jnj.adf.grid.manager.region.ClientRegionCreator;
/*     */ import com.jnj.adf.grid.manager.region.ClientRegionCreatorImpl;
/*     */ import com.jnj.adf.grid.manager.region.ClientRegionManager;
/*     */ import com.jnj.adf.grid.manager.region.LocalRegionCreator;
/*     */ import com.jnj.adf.grid.manager.region.LocalRegionCreatorImpl;
/*     */ import com.jnj.adf.grid.utils.LogUtil;
/*     */ import java.util.List;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.springframework.util.Assert;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RegionBuilder
/*     */ {
/*     */   private String gridName;
/*  25 */   private Pool pool = null;
/*  26 */   private ClientRegionShortcut shortcut = ClientRegionShortcut.PROXY;
/*  27 */   private List<String> fullPaths = Lists.newArrayList();
/*  28 */   private ClientRegionFactory clientRegionFactory = null;
/*     */   private String regionName;
/*  30 */   private static ClientRegionCreator clientRegionCreator = new ClientRegionCreatorImpl();
/*  31 */   private static LocalRegionCreator localRegionCreator = new LocalRegionCreatorImpl();
/*     */   
/*  33 */   public static RegionBuilder newBuilder() { return new RegionBuilder(); }
/*     */   
/*     */   public RegionBuilder setGridName(String gridName)
/*     */   {
/*  37 */     this.gridName = gridName;
/*  38 */     return this;
/*     */   }
/*     */   
/*     */   public RegionBuilder setPool(Pool pool) {
/*  42 */     this.pool = pool;
/*  43 */     return this;
/*     */   }
/*     */   
/*     */   public RegionBuilder setClientRegionShortCut(ClientRegionShortcut shortcut) {
/*  47 */     this.shortcut = shortcut;
/*  48 */     return this;
/*     */   }
/*     */   
/*     */   public RegionBuilder setFullPaths(List<String> fullPaths) {
/*  52 */     if (fullPaths != null) {
/*  53 */       this.fullPaths.addAll(fullPaths);
/*     */     }
/*  55 */     return this;
/*     */   }
/*     */   
/*     */   public RegionBuilder setRegionName(String regionName) {
/*  59 */     this.regionName = regionName;
/*  60 */     return this;
/*     */   }
/*     */   
/*     */   public RegionBuilder setClientRegionFactory(ClientRegionFactory clientRegionFactory) {
/*  64 */     this.clientRegionFactory = clientRegionFactory;
/*  65 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<String> create()
/*     */   {
/*  74 */     Assert.notNull(this.gridName);
/*  75 */     Assert.notNull(this.shortcut);
/*  76 */     List<String> regionNames = Lists.newArrayList();
/*  77 */     List<String> list; if (this.fullPaths.isEmpty()) {
/*  78 */       LogUtil.getCoreLog().debug("start to createLocalRegion all client region, grid:{}, shortcut:{}", new Object[] { this.gridName, this.shortcut });
/*  79 */       list = null;
/*  80 */       if (this.clientRegionFactory != null) {
/*  81 */         list = clientRegionCreator.createAllClientRegions(this.gridName, this.pool, this.clientRegionFactory);
/*     */       } else {
/*  83 */         list = clientRegionCreator.createAllClientRegions(this.gridName, this.pool, this.shortcut);
/*     */       }
/*  85 */       regionNames.addAll(list);
/*     */     } else {
/*  87 */       LogUtil.getCoreLog().debug("start to createLocalRegion client region, grid:{}, shortcut:{}, paths:{}", new Object[] { this.gridName, this.shortcut, this.fullPaths });
/*  88 */       for (String fullPath : this.fullPaths) {
/*  89 */         Region r = null;
/*  90 */         if (this.clientRegionFactory != null) {
/*  91 */           r = clientRegionCreator.createClientRegion(this.gridName, fullPath, this.pool, this.shortcut);
/*     */         } else {
/*  93 */           r = clientRegionCreator.createClientRegion(this.gridName, fullPath, this.pool, this.clientRegionFactory);
/*     */         }
/*  95 */         if (r != null) {
/*  96 */           regionNames.add(r.getFullPath());
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 102 */     return regionNames;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Region createRegion()
/*     */   {
/* 110 */     Assert.notNull(this.gridName);
/* 111 */     Assert.notNull(this.shortcut);
/* 112 */     Assert.notNull(this.regionName);
/* 113 */     Assert.hasLength(this.regionName);
/* 114 */     Region r = null;
/* 115 */     if (this.clientRegionFactory == null) {
/* 116 */       r = clientRegionCreator.createClientRegion(this.gridName, this.regionName, this.pool, this.shortcut);
/*     */     } else {
/* 118 */       r = clientRegionCreator.createClientRegion(this.gridName, this.regionName, this.pool, this.clientRegionFactory);
/*     */     }
/* 120 */     return r;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void destroy()
/*     */   {
/* 127 */     Assert.notNull(this.gridName);
/* 128 */     List<String> allPaths = this.fullPaths;
/* 129 */     if (allPaths.isEmpty()) {
/* 130 */       allPaths = ClientRegionManager.getAllPathsByGridId(this.gridName);
/* 131 */       LogUtil.getCoreLog().debug("destroy all regions of grid, grid:{}, allpaths:{}", new Object[] { this.gridName, allPaths });
/*     */     }
/* 133 */     if (!allPaths.isEmpty()) {
/* 134 */       LogUtil.getCoreLog().debug("destroy regions of grid, grid:{}, allpaths:{}", new Object[] { this.gridName, allPaths });
/* 135 */       clientRegionCreator.destroyClientRegions(allPaths);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Region createLocalRegion()
/*     */   {
/* 145 */     Assert.notNull(this.regionName);
/* 146 */     Assert.hasLength(this.regionName);
/* 147 */     return localRegionCreator.createLocalRegions(this.regionName);
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\manager\RegionBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */