/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.data.temporal;
/*     */ 
/*     */ import com.gemstone.gemfire.cache.Region;
/*     */ import com.gemstone.gemfire.cache.partition.PartitionRegionHelper;
/*     */ import com.gemstone.gemfire.pdx.PdxInstance;
/*     */ import com.jnj.adf.grid.common.GridPathNameUtil;
/*     */ import com.jnj.adf.grid.data.MetaService;
/*     */ import com.jnj.adf.grid.data.MetaServiceFactory;
/*     */ import com.jnj.adf.grid.data.RegionDefine_v3;
/*     */ import com.jnj.adf.grid.data.temporal.utils.TemporalKeyUtils;
/*     */ import com.jnj.adf.grid.data.temporal.utils.TemporalValueUtils;
/*     */ import com.jnj.adf.grid.manager.RegionHelper;
/*     */ import com.jnj.adf.grid.utils.LogUtil;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentHashMap;
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
/*     */ 
/*     */ 
/*     */ public class TemporalManager
/*     */ {
/*     */   class TemporalListWrapper
/*     */   {
/*  43 */     private Map<String, TemporalListWrapperWithIdentityKey> map = new ConcurrentHashMap();
/*     */     
/*     */     TemporalListWrapper() {}
/*     */     
/*  47 */     public TemporalListWrapperWithIdentityKey getByPath(String path) { path = GridPathNameUtil.escapePath(path);
/*  48 */       TemporalListWrapperWithIdentityKey t = (TemporalListWrapperWithIdentityKey)this.map.get(path);
/*  49 */       if (t == null)
/*     */       {
/*  51 */         t = new TemporalListWrapperWithIdentityKey(TemporalManager.this, path);
/*  52 */         this.map.put(path, t);
/*     */       }
/*  54 */       return t;
/*     */     }
/*     */     
/*     */     public void clearByPath(String path)
/*     */     {
/*  59 */       path = GridPathNameUtil.escapePath(path);
/*  60 */       TemporalListWrapperWithIdentityKey t = (TemporalListWrapperWithIdentityKey)this.map.get(path);
/*  61 */       if (t != null)
/*     */       {
/*  63 */         t.clear();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   class TemporalListWrapperWithIdentityKey
/*     */   {
/*     */     private String path;
/*     */     
/*     */     public TemporalListWrapperWithIdentityKey(String path)
/*     */     {
/*  74 */       this.path = path;
/*     */     }
/*     */     
/*  77 */     private Map<Object, TemporalProcessor> temporalListMap = new ConcurrentHashMap();
/*     */     
/*     */     public String getPath()
/*     */     {
/*  81 */       return this.path;
/*     */     }
/*     */     
/*     */     public TemporalProcessor getProcessor(String identityId)
/*     */     {
/*  86 */       TemporalProcessor list = (TemporalProcessor)this.temporalListMap.get(identityId);
/*  87 */       if (list == null)
/*     */       {
/*  89 */         list = new TemporalProcessor(identityId);
/*     */         
/*  91 */         list.setPath(this.path);
/*  92 */         this.temporalListMap.put(identityId, list);
/*     */       }
/*  94 */       return list;
/*     */     }
/*     */     
/*     */     public void clear()
/*     */     {
/*  99 */       for (TemporalProcessor tpr : this.temporalListMap.values())
/*     */       {
/* 101 */         tpr.clear();
/*     */       }
/* 103 */       this.temporalListMap.clear();
/*     */     }
/*     */     
/*     */     public Map<Object, TemporalProcessor> getTemporalMap()
/*     */     {
/* 108 */       return this.temporalListMap;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/* 113 */   private static final TemporalManager INST = new TemporalManager();
/* 114 */   private TemporalListWrapper temporalStore = new TemporalListWrapper();
/*     */   private MetaService metaService;
/*     */   
/*     */   private TemporalManager()
/*     */   {
/* 119 */     this.metaService = MetaServiceFactory.createMetaService();
/*     */   }
/*     */   
/*     */   public static final TemporalManager getInstance()
/*     */   {
/* 124 */     return INST;
/*     */   }
/*     */   
/*     */ 
/*     */   public TemporalIndexCollection processTemporal(Region<TemporalRKey, PdxInstance> region, String path, Object key, TemporalValue pdx, boolean needCreateIndex)
/*     */   {
/* 130 */     RegionDefine_v3 def = RegionHelper.getRegionDefine(region);
/* 131 */     boolean enableTemporal = (def != null) && (def.isEnabledTemporal());
/* 132 */     boolean enableLucene = (def != null) && (def.isEnabledLucene());
/* 133 */     if (enableTemporal)
/*     */     {
/* 135 */       TemporalRKey tk = (TemporalRKey)key;
/* 136 */       TemporalIndexCollection coll = processTemporal(path, tk, pdx, enableLucene);
/* 137 */       if (!this.metaService.temporalEnabled(path))
/* 138 */         this.metaService.enableTemporal(path, true);
/* 139 */       LogUtil.getCoreLog().debug("TemporalManager process temporal list . key:{} path:{} ", new Object[] { tk, path });
/* 140 */       return coll;
/*     */     }
/* 142 */     return null;
/*     */   }
/*     */   
/*     */   public synchronized TemporalProcessor getTemporalList(String path, String identityKey)
/*     */   {
/* 147 */     return this.temporalStore.getByPath(path).getProcessor(identityKey);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized Map<Object, TemporalProcessor> getTemporalMap(String path)
/*     */   {
/* 157 */     TemporalListWrapperWithIdentityKey wrapper = this.temporalStore.getByPath(path);
/* 158 */     return wrapper.getTemporalMap();
/*     */   }
/*     */   
/*     */ 
/*     */   public TemporalIndexCollection processTemporal(String path, TemporalRKey tk, TemporalValue value, boolean needCreateIndex)
/*     */   {
/* 164 */     TemporalProcessor temporlList = getTemporalList(path, tk.getIdentityKey());
/* 165 */     TemporalKey key = TemporalKeyUtils.convertToKey(tk.getUuid(), value);
/* 166 */     if (key != null)
/*     */     {
/* 168 */       return temporlList.add(key, needCreateIndex);
/*     */     }
/* 170 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public void processTemporal(Region<TemporalRKey, PdxInstance> region, String path, TemporalRKey rkey, TemporalValue value)
/*     */   {
/* 176 */     RegionDefine_v3 def = (RegionDefine_v3)region.getUserAttribute();
/* 177 */     boolean enableTemporal = (def != null) && (def.isEnabledTemporal());
/* 178 */     boolean enableLucene = (def != null) && (def.isEnabledLucene());
/* 179 */     if (enableTemporal)
/*     */     {
/* 181 */       processTemporal(path, rkey, value, enableLucene);
/* 182 */       if (!this.metaService.temporalEnabled(path))
/* 183 */         this.metaService.enableTemporal(path, true);
/* 184 */       LogUtil.getCoreLog().debug("TemporalManager process temporal list . key:{} path:{} ", new Object[] { rkey, path });
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void buildTemporalList(String path)
/*     */   {
/* 193 */     LogUtil.startTimer();
/* 194 */     LogUtil.getCoreLog().debug("Start to rebuild all Temporal List for path:{}", new Object[] { path });
/* 195 */     Region<TemporalRKey, PdxInstance> r = RegionHelper.getRegion(path);
/* 196 */     if (r == null)
/*     */     {
/* 198 */       LogUtil.getCoreLog().error("Region is null for path:{}, Can't rebuild Temporal List!", new Object[] { path });
/*     */     }
/* 200 */     else if (PartitionRegionHelper.isPartitionedRegion(r))
/*     */     {
/* 202 */       r = PartitionRegionHelper.getLocalPrimaryData(r);
/* 203 */       LogUtil.getCoreLog().info("Get region local primary data size:{}, region path:{}", new Object[] { Integer.valueOf(r.size()), path });
/* 204 */       Iterator<Entry<TemporalRKey, PdxInstance>> it = r.entrySet().iterator();
/* 205 */       while (it.hasNext())
/*     */       {
/* 207 */         Entry<TemporalRKey, PdxInstance> entry = (Entry)it.next();
/* 208 */         TemporalRKey rkey = (TemporalRKey)entry.getKey();
/* 209 */         TemporalProcessor processor = getTemporalList(r.getFullPath(), rkey.getIdentityKey());
/* 210 */         TemporalValue tvalue = TemporalValueUtils.pdxToTValue((PdxInstance)entry.getValue());
/* 211 */         TemporalKey key = TemporalKeyUtils.convertToKey(rkey.getUuid(), tvalue);
/* 212 */         processor.add(key);
/*     */       }
/*     */     }
/* 215 */     LogUtil.getCoreLog().debug("Complete to rebuild all Temporal List for path:{} cost:{}", new Object[] { path, Long.valueOf(LogUtil.cost()) });
/*     */   }
/*     */   
/*     */ 
/*     */   public void cleanTemporalList(String path)
/*     */   {
/* 221 */     this.temporalStore.clearByPath(path);
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\data\temporal\TemporalManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */