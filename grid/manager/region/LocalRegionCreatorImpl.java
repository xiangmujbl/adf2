/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.manager.region;
/*     */ 
/*     */ import com.gemstone.gemfire.cache.AttributesFactory;
/*     */ import com.gemstone.gemfire.cache.DataPolicy;
/*     */ import com.gemstone.gemfire.cache.Region;
/*     */ import com.gemstone.gemfire.cache.RegionAttributes;
/*     */ import com.gemstone.gemfire.cache.Scope;
/*     */ import com.gemstone.gemfire.internal.cache.GemFireCacheImpl;
/*     */ import com.gemstone.gemfire.internal.cache.InternalRegionArguments;
/*     */ import com.jnj.adf.grid.common.ADFException;
/*     */ import com.jnj.adf.grid.common.GridPathNameUtil;
/*     */ import com.jnj.adf.grid.support.system.ADFConfigHelper;
/*     */ import com.jnj.adf.grid.support.system.ADFConfigHelper.ITEMS;
/*     */ import com.jnj.adf.grid.utils.LogUtil;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ public class LocalRegionCreatorImpl
/*     */   implements LocalRegionCreator
/*     */ {
/*     */   public Region createLocalRegions(String fullPath)
/*     */   {
/*  26 */     List<String> list = new ArrayList();
/*  27 */     fullPath = GridPathNameUtil.escapePath(fullPath);
/*  28 */     if (fullPath.indexOf("/") == -1)
/*     */     {
/*  30 */       list.add(fullPath);
/*     */     }
/*     */     else
/*     */     {
/*  34 */       String[] paths = fullPath.split("/");
/*  35 */       for (int i = 0; i < paths.length - 1; i++)
/*     */       {
/*  37 */         String path = paths[i];
/*  38 */         if (!StringUtils.isEmpty(path))
/*  39 */           list.add(paths[i]);
/*     */       }
/*     */     }
/*  42 */     Region parentRegion = createParentRegion(list);
/*  43 */     return parentRegion;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void destroyLocalRegions(String fullPath) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private Region createParentRegion(List<String> paths)
/*     */   {
/*  59 */     Region parent = null;
/*  60 */     GemFireCacheImpl cache = GemFireCacheImpl.getInstance();
/*  61 */     for (String path : paths)
/*     */     {
/*  63 */       if (parent == null)
/*     */       {
/*  65 */         parent = cache.getRegion(path);
/*  66 */         if (parent != null) {}
/*     */       }
/*     */       else {
/*  69 */         LogUtil.getCoreLog().debug("Create parent regions for paths {}", new Object[] { paths });
/*  70 */         AttributesFactory rf = new AttributesFactory();
/*  71 */         rf.setDataPolicy(DataPolicy.REPLICATE);
/*  72 */         rf.setScope(Scope.DISTRIBUTED_ACK);
/*  73 */         RegionAttributes<Object, Object> ra = rf.create();
/*  74 */         if (parent != null)
/*     */         {
/*  76 */           Region sub = parent.getSubregion(path);
/*  77 */           if (sub == null) {
/*  78 */             parent = parent.createSubregion(path, ra);
/*     */           } else
/*  80 */             parent = sub;
/*  81 */           LogUtil.getCoreLog().debug("Create sub parent : {} sucessful.", new Object[] { path });
/*     */         }
/*     */         else
/*     */         {
/*  85 */           InternalRegionArguments internalArgs = new InternalRegionArguments();
/*  86 */           internalArgs.setIsUsedForMetaRegion(true);
/*     */           try
/*     */           {
/*  89 */             String s = ADFConfigHelper.getConfig(ITEMS.GRID_META_VISIBLE);
/*  90 */             boolean metaVisble = Boolean.valueOf(s).booleanValue();
/*  91 */             if (!metaVisble) {
/*  92 */               parent = cache.createVMRegion(path, ra, internalArgs);
/*     */             } else
/*  94 */               parent = cache.createVMRegion(path, ra);
/*  95 */             LogUtil.getCoreLog().debug("Create root : {} ", new Object[] { path });
/*     */           }
/*     */           catch (Exception e)
/*     */           {
/*  99 */             throw new ADFException("Paths:" + paths, e);
/*     */           }
/*     */         }
/*     */       } }
/* 103 */     return parent;
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\manager\region\LocalRegionCreatorImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */