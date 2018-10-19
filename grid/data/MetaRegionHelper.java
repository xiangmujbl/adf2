/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.data;
/*     */ 
/*     */ import com.gemstone.gemfire.cache.EntryEvent;
/*     */ import com.gemstone.gemfire.cache.Region;
/*     */ import com.gemstone.gemfire.cache.util.CacheListenerAdapter;
/*     */ import com.gemstone.gemfire.internal.concurrent.ConcurrentHashSet;
/*     */ import com.gemstone.gemfire.pdx.PdxInstance;
/*     */ import com.jnj.adf.grid.common.PdxUtils;
/*     */ import com.jnj.adf.grid.manager.RegionHelper;
/*     */ import com.jnj.adf.grid.utils.JsonUtils;
/*     */ import com.jnj.adf.grid.utils.LogUtil;
/*     */ import java.util.Collection;
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
/*     */ public class MetaRegionHelper
/*     */ {
/*  51 */   private static final Map<String, Map<String, Set<MetaKey>>> localCache = new ConcurrentHashMap();
/*     */   
/*  53 */   private static final Map<String, Set<MetaKey>> propCache = new ConcurrentHashMap();
/*     */   private static final String META_REGION_NAME = "META";
/*     */   
/*     */   private void cacheKeys(MetaKey key)
/*     */   {
/*  58 */     Set<MetaKey> list = getMetaKeySet(key);
/*  59 */     list.add(key);
/*     */     
/*  61 */     Set<MetaKey> list2 = cacheSetByProp(key.getProperty());
/*  62 */     list2.add(key);
/*     */   }
/*     */   
/*     */   private Set<MetaKey> cacheSetByProp(String prop) {
/*  66 */     Set<MetaKey> set = (Set)propCache.get(prop);
/*  67 */     if (set == null)
/*     */     {
/*  69 */       set = new ConcurrentHashSet();
/*  70 */       propCache.put(prop, set);
/*     */     }
/*  72 */     return set;
/*     */   }
/*     */   
/*     */   private Set<MetaKey> getMetaKeySet(MetaKey key)
/*     */   {
/*  77 */     String path = key.getPath();
/*  78 */     String prop = key.getProperty();
/*  79 */     return getMetaKeySet(path, prop);
/*     */   }
/*     */   
/*     */   private Set<MetaKey> getMetaKeySet(String path, String prop)
/*     */   {
/*  84 */     Map<String, Set<MetaKey>> propertyMap = (Map)localCache.get(path);
/*  85 */     if (propertyMap == null)
/*     */     {
/*  87 */       propertyMap = new ConcurrentHashMap();
/*  88 */       localCache.put(path, propertyMap);
/*     */     }
/*  90 */     Set<MetaKey> list = (Set)propertyMap.get(prop);
/*  91 */     if (list == null)
/*     */     {
/*  93 */       list = new ConcurrentHashSet();
/*  94 */       propertyMap.put(prop, list);
/*     */     }
/*  96 */     return list;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   class MetaRegionListener
/*     */     extends CacheListenerAdapter<MetaKey, Object>
/*     */   {
/*     */     MetaRegionListener() {}
/*     */     
/*     */ 
/*     */ 
/*     */     public void afterCreate(EntryEvent<MetaKey, Object> event)
/*     */     {
/* 111 */       Set<MetaKey> list = MetaRegionHelper.this.getMetaKeySet((MetaKey)event.getKey());
/* 112 */       list.add(event.getKey());
/*     */       
/* 114 */       Set<MetaKey> list2 = MetaRegionHelper.this.cacheSetByProp(((MetaKey)event.getKey()).getProperty());
/* 115 */       list2.add(event.getKey());
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public void afterDestroy(EntryEvent<MetaKey, Object> event)
/*     */     {
/* 128 */       Set<MetaKey> list = MetaRegionHelper.this.getMetaKeySet((MetaKey)event.getKey());
/* 129 */       list.remove(event.getKey());
/* 130 */       Set<MetaKey> list2 = MetaRegionHelper.this.cacheSetByProp(((MetaKey)event.getKey()).getProperty());
/* 131 */       list2.remove(event.getKey());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/* 136 */   private static final MetaRegionHelper inst = new MetaRegionHelper();
/*     */   
/*     */ 
/*     */   private Region<MetaKey, Object> configRegion;
/*     */   
/*     */ 
/*     */ 
/*     */   public static final MetaRegionHelper getInstance()
/*     */   {
/* 145 */     return inst;
/*     */   }
/*     */   
/*     */   public static final Region<MetaKey, Object> getMetaRegion()
/*     */   {
/* 150 */     if (inst.configRegion == null)
/*     */     {
/* 152 */       inst.configRegion = RegionHelper.getMetaRegion("META");
/* 153 */       Iterator<Entry<MetaKey, Object>> it = inst.configRegion.entrySet().iterator();
/* 154 */       while (it.hasNext())
/*     */       {
/* 156 */         Entry<MetaKey, Object> entry = (Entry)it.next();
/* 157 */         MetaKey k = (MetaKey)entry.getKey();
/* 158 */         inst.cacheKeys(k);
/*     */       }
/*     */     }
/* 161 */     return inst.configRegion;
/*     */   }
/*     */   
/*     */   public <V> V getValue(String path, MetaTypes type, Object id)
/*     */   {
/* 166 */     MetaKey k = new MetaKey(path, type);
/* 167 */     return (V)getMetaRegion().get(k);
/*     */   }
/*     */   
/*     */   public <V> V getValue(String path, MetaTypes type)
/*     */   {
/* 172 */     return (V)getValue(path, type, null);
/*     */   }
/*     */   
/*     */   public <V> void putValue(String path, MetaTypes type, V value, boolean directWrite)
/*     */   {
/* 177 */     putValue(path, type, null, value, directWrite);
/*     */   }
/*     */   
/*     */   public <V> void putValue(String path, MetaTypes type, Object id, V value, boolean directWrite)
/*     */   {
/* 182 */     if (value == null)
/*     */     {
/* 184 */       LogUtil.getCoreLog().warn("Want to save null value in meta region.path:{}, type:{}", new Object[] { path, type.name() });
/* 185 */       return;
/*     */     }
/* 187 */     MetaKey key = new MetaKey(path, type);
/*     */     
/* 189 */     if (directWrite)
/*     */     {
/* 191 */       getMetaRegion().put(key, value);
/*     */     }
/*     */     else
/*     */     {
/* 195 */       byte[] bytes = JsonUtils.objectToBytes(value);
/* 196 */       PdxInstance pdx = PdxUtils.fromJson(bytes);
/* 197 */       if (pdx != null)
/*     */       {
/* 199 */         getMetaRegion().put(key, pdx);
/* 200 */         LogUtil.getCoreLog().debug("Meta data writed. path:{},type:{},id:{},value:{}", new Object[] { path, type.name(), id, value });
/*     */       }
/*     */       else
/*     */       {
/* 204 */         LogUtil.getCoreLog().error("Meta data write failed. path:{},type:{},id:{},value:{}", new Object[] { path, type.name(), id, value });
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public <V> void removeValue(String path, MetaTypes type)
/*     */   {
/* 211 */     MetaKey key = new MetaKey(path, type);
/* 212 */     getMetaRegion().remove(key);
/*     */   }
/*     */   
/*     */   public <V> Collection<V> getCachedValues(String path, MetaTypes type)
/*     */   {
/* 217 */     Set<MetaKey> keys = getMetaKeySet(path, type.name());
/* 218 */     Map<MetaKey, V> map = getMetaRegion().getAll(keys);
/* 219 */     return map.values();
/*     */   }
/*     */   
/*     */   public <V> V getCachedValue(String path, MetaTypes type)
/*     */   {
/* 224 */     Set<MetaKey> keys = getMetaKeySet(path, type.name());
/* 225 */     if ((keys != null) && (keys.size() > 0))
/*     */     {
/* 227 */       MetaKey key = (MetaKey)keys.iterator().next();
/* 228 */       return (V)getMetaRegion().get(key);
/*     */     }
/* 230 */     return null;
/*     */   }
/*     */   
/*     */   public <V> Map<MetaKey, V> getCachedValues(MetaTypes type)
/*     */   {
/* 235 */     Set<MetaKey> keys = cacheSetByProp(type.name());
/* 236 */     if (keys != null)
/*     */     {
/* 238 */       Map<MetaKey, V> map = getMetaRegion().getAll(keys);
/* 239 */       return map;
/*     */     }
/* 241 */     return null;
/*     */   }
/*     */   
/*     */   public static void main(String[] args)
/*     */   {
/* 246 */     inst.putValue("gfs/cost", MetaTypes.TEMPORAL, Boolean.valueOf(true), true);
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\data\MetaRegionHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */