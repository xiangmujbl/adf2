/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.client.api.impl;
/*     */ 
/*     */ import com.gemstone.gemfire.cache.Region;
/*     */ import com.gemstone.gemfire.cache.partition.PartitionRegionHelper;
/*     */ import com.gemstone.gemfire.pdx.PdxInstance;
/*     */ import com.jnj.adf.client.api.remote.RawData;
/*     */ import com.jnj.adf.client.api.remote.RawDataTemplate;
/*     */ import com.jnj.adf.client.api.remote.RawDataValue;
/*     */ import com.jnj.adf.grid.common.ADFException;
/*     */ import com.jnj.adf.grid.common.PdxUtils;
/*     */ import com.jnj.adf.grid.data.temporal.TemporalValue;
/*     */ import com.jnj.adf.grid.manager.RegionHelper;
/*     */ import com.jnj.adf.grid.utils.JsonUtils;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
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
/*     */ public class RawDataTemplateImpl
/*     */   implements RawDataTemplate
/*     */ {
/*     */   private Region<Object, Object> region;
/*     */   
/*     */   public RawDataTemplateImpl create(String fullPath)
/*     */     throws ADFException
/*     */   {
/*  46 */     this.region = RegionHelper.getRegion(fullPath);
/*  47 */     if (this.region == null)
/*     */     {
/*  49 */       throw new ADFException("no path found:" + fullPath);
/*     */     }
/*  51 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */   public <T> void save(T value)
/*     */   {
/*  57 */     PdxInstance pdx = toPdx(value);
/*  58 */     this.region.put(UUID.randomUUID().toString(), pdx);
/*     */   }
/*     */   
/*     */   protected <T> PdxInstance toPdx(T value)
/*     */   {
/*  63 */     if ((value instanceof RawDataValue))
/*     */     {
/*  65 */       RawDataValue rv = (RawDataValue)value;
/*  66 */       PdxInstance pdx = rv.toPdx();
/*  67 */       return pdx;
/*     */     }
/*  69 */     if ((value instanceof PdxInstance))
/*     */     {
/*  71 */       return (PdxInstance)value;
/*     */     }
/*     */     
/*     */ 
/*  75 */     byte[] bt = JsonUtils.objectToBytes(value);
/*  76 */     PdxInstance pdx = PdxUtils.fromJson(bt);
/*  77 */     return pdx;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public <T> void put(Object key, T value)
/*     */   {
/*  84 */     PdxInstance pdx = toPdx(value);
/*  85 */     this.region.put(key, pdx);
/*     */   }
/*     */   
/*     */ 
/*     */   public <T> void putAll(Map<Object, Object> map)
/*     */   {
/*  91 */     this.region.putAll(map);
/*     */   }
/*     */   
/*     */ 
/*     */   public <T> void putAll(Map<Object, Object> map, Object aCallbackArgument)
/*     */   {
/*  97 */     this.region.putAll(map, aCallbackArgument);
/*     */   }
/*     */   
/*     */ 
/*     */   public Object delete(Object key)
/*     */   {
/* 103 */     return this.region.remove(key);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean containsKey(Object key)
/*     */   {
/* 109 */     return this.region.containsKey(key);
/*     */   }
/*     */   
/*     */ 
/*     */   public RawData get(Object key)
/*     */   {
/* 115 */     Object o = this.region.get(key);
/*     */     
/*     */ 
/* 118 */     if (o == null)
/*     */     {
/* 120 */       return null;
/*     */     }
/*     */     
/* 123 */     PdxInstance pdx = null;
/* 124 */     if ((o instanceof PdxInstance))
/*     */     {
/* 126 */       pdx = (PdxInstance)o;
/*     */     }
/* 128 */     else if ((o instanceof TemporalValue))
/*     */     {
/* 130 */       TemporalValue tv = (TemporalValue)o;
/* 131 */       pdx = tv.getOriginValue();
/*     */     }
/* 133 */     RawDataValue v = null;
/* 134 */     if (pdx != null) {
/* 135 */       v = new RawDataValueImpl(pdx);
/*     */     }
/* 137 */     RawData raw = new RawDataImpl(key, v, this.region.getFullPath());
/* 138 */     return raw;
/*     */   }
/*     */   
/*     */ 
/*     */   public Object get(Object key, boolean isDirect)
/*     */   {
/* 144 */     Object obj = this.region.get(key);
/* 145 */     return obj;
/*     */   }
/*     */   
/*     */ 
/*     */   public Iterator<Object> localKeyIterator()
/*     */   {
/* 151 */     if (PartitionRegionHelper.isPartitionedRegion(this.region))
/*     */     {
/* 153 */       Region r = PartitionRegionHelper.getLocalPrimaryData(this.region);
/* 154 */       return r.keySet().iterator();
/*     */     }
/* 156 */     return this.region.keySet().iterator();
/*     */   }
/*     */   
/*     */ 
/*     */   public Iterator<Entry<Object, Object>> localEntryIterator()
/*     */   {
/* 162 */     if (PartitionRegionHelper.isPartitionedRegion(this.region))
/*     */     {
/* 164 */       Region r = PartitionRegionHelper.getLocalPrimaryData(this.region);
/* 165 */       return r.entrySet().iterator();
/*     */     }
/* 167 */     return this.region.entrySet().iterator();
/*     */   }
/*     */   
/*     */ 
/*     */   public String getFullPath()
/*     */   {
/* 173 */     return this.region.getFullPath();
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\client\api\impl\RawDataTemplateImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */