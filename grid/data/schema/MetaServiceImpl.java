/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.data.schema;
/*     */ 
/*     */ import com.gemstone.gemfire.pdx.PdxInstance;
/*     */ import com.jnj.adf.grid.common.GridPathNameUtil;
/*     */ import com.jnj.adf.grid.common.PdxUtils;
/*     */ import com.jnj.adf.grid.data.MetaKey;
/*     */ import com.jnj.adf.grid.data.MetaRegionHelper;
/*     */ import com.jnj.adf.grid.data.MetaService;
/*     */ import com.jnj.adf.grid.data.MetaTypes;
/*     */ import com.jnj.adf.grid.data.ReservedFields;
/*     */ import com.jnj.adf.grid.data.temporal.TemporalManager;
/*     */ import com.jnj.adf.grid.support.context.ADFRuntime;
/*     */ import com.jnj.adf.grid.utils.JsonUtils;
/*     */ import com.jnj.adf.grid.utils.LogUtil;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.ConcurrentSkipListSet;
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
/*     */ public class MetaServiceImpl
/*     */   implements MetaService
/*     */ {
/*  46 */   private static final Map<String, MetaValue> metaValues = new ConcurrentHashMap();
/*     */   
/*  48 */   private static final Set<String> auditPaths = new ConcurrentSkipListSet();
/*     */   
/*     */   public static final String ALL_PATH = "*";
/*     */   
/*     */ 
/*     */   public void enableTemporal(String path, boolean enabled)
/*     */   {
/*  55 */     if (!ADFRuntime.getRuntime().isServerSide())
/*  56 */       return;
/*  57 */     path = GridPathNameUtil.escapePath(path);
/*  58 */     if (enabled) {
/*  59 */       MetaRegionHelper.getInstance().putValue(path, MetaTypes.TEMPORAL, Boolean.valueOf(enabled), true);
/*     */     } else {
/*  61 */       MetaRegionHelper.getInstance().removeValue(path, MetaTypes.TEMPORAL);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean temporalEnabled(String path)
/*     */   {
/*  73 */     if (auditPaths.contains(path))
/*     */     {
/*  75 */       return true;
/*     */     }
/*  77 */     Boolean audit = (Boolean)MetaRegionHelper.getInstance().getValue(path, MetaTypes.TEMPORAL);
/*     */     
/*  79 */     if ((audit != null) && (audit.booleanValue()))
/*     */     {
/*  81 */       auditPaths.add(path);
/*  82 */       return true;
/*     */     }
/*  84 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void registerReadSchema(String path, Map<String, String> internalKeyMap, Map<String, String> externalKeyMap)
/*     */   {
/*  94 */     MetaValue v = new MetaValue();
/*  95 */     v.setPath(path);
/*  96 */     v.setExternalKeyMap(externalKeyMap);
/*  97 */     v.setInternalKeyMap(internalKeyMap);
/*  98 */     metaValues.put(path, v);
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
/*     */   public <V> V applyReadSchema(String path, PdxInstance pdx, Class<V> classType)
/*     */   {
/* 111 */     if (pdx == null)
/* 112 */       return null;
/* 113 */     MetaValue meta = null;
/* 114 */     if (metaValues != null)
/* 115 */       meta = (MetaValue)metaValues.get(path);
/* 116 */     String json = PdxUtils.toJson(pdx);
/* 117 */     if (meta != null)
/*     */     {
/* 119 */       Map map = (Map)JsonUtils.jsonToObject(json, Map.class);
/* 120 */       Iterator<Entry<String, String>> it = meta.getInternalKeyMap().entrySet().iterator();
/* 121 */       while (it.hasNext())
/*     */       {
/* 123 */         Entry<String, String> e = (Entry)it.next();
/* 124 */         map.put(e.getValue(), map.get(e.getKey()));
/*     */       }
/* 126 */       return (V)JsonUtils.convertValue(map, classType);
/*     */     }
/*     */     
/* 129 */     return (V)JsonUtils.jsonToObject(json, classType);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public <V> String applyWriteSchema(String path, V value)
/*     */   {
/* 141 */     if (hasMeta(path))
/*     */     {
/* 143 */       Map<String, Object> map = applyWriteSchemaAsMap(path, value);
/* 144 */       if (map != null) {
/* 145 */         return JsonUtils.objectToJson(map);
/*     */       }
/*     */     }
/* 148 */     return JsonUtils.objectToJson(value);
/*     */   }
/*     */   
/*     */ 
/*     */   public <V> String applyWriteSchema(String path, V value, double latitude, double longitude)
/*     */   {
/* 154 */     Map<String, Object> map = applyWriteSchemaAsMap(path, value);
/* 155 */     map.put(ReservedFields.LOCATION_LATITUDE.fieldName(), Double.valueOf(latitude));
/* 156 */     map.put(ReservedFields.LOCATION_LONGITUDE.fieldName(), Double.valueOf(longitude));
/*     */     
/* 158 */     return JsonUtils.objectToJson(map);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void afterDataStoreStarted()
/*     */   {
/* 169 */     Map<MetaKey, Boolean> map = MetaRegionHelper.getInstance().getCachedValues(MetaTypes.TEMPORAL);
/* 170 */     Iterator<Entry<MetaKey, Boolean>> it = map.entrySet().iterator();
/* 171 */     while (it.hasNext())
/*     */     {
/* 173 */       Entry<MetaKey, Boolean> entry = (Entry)it.next();
/* 174 */       Boolean temporalFlag = (Boolean)entry.getValue();
/* 175 */       if ((temporalFlag != null) && (temporalFlag.booleanValue()))
/*     */       {
/* 177 */         MetaKey key = (MetaKey)entry.getKey();
/* 178 */         TemporalManager.getInstance().buildTemporalList(key.getPath());
/* 179 */         LogUtil.getCoreLog().info("Intialize temporal list for {}", new Object[] { key.getPath() });
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   boolean hasMeta(String path)
/*     */   {
/* 186 */     return metaValues.get(path) != null;
/*     */   }
/*     */   
/*     */   public <V> Map<String, Object> applyWriteSchemaAsMap(String path, V value)
/*     */   {
/* 191 */     MetaValue meta = (MetaValue)metaValues.get(path);
/* 192 */     Map map = (Map)JsonUtils.convertValue(value, Map.class);
/* 193 */     if (meta != null)
/*     */     {
/* 195 */       Iterator<Entry<String, String>> it = meta.getExternalKeyMap().entrySet().iterator();
/* 196 */       while (it.hasNext())
/*     */       {
/* 198 */         Entry<String, String> e = (Entry)it.next();
/* 199 */         map.put(e.getValue(), map.get(e.getKey()));
/* 200 */         map.remove(e.getKey());
/*     */       }
/*     */     }
/* 203 */     return map;
/*     */   }
/*     */   
/*     */ 
/*     */   public void enableAQProcessor(ProcessorTypes procType, boolean enabled)
/*     */   {
/* 209 */     if (!ADFRuntime.getRuntime().isServerSide())
/* 210 */       return;
/* 211 */     HashMap<ProcessorTypes, Boolean> processors = (HashMap)MetaRegionHelper.getInstance().getValue("*", MetaTypes.AQ_PROCESSOR);
/*     */     
/* 213 */     if (processors == null)
/* 214 */       processors = new HashMap();
/* 215 */     processors.put(procType, Boolean.valueOf(enabled));
/* 216 */     MetaRegionHelper.getInstance().putValue("*", MetaTypes.AQ_PROCESSOR, processors, true);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean aqProcessorEnabled(ProcessorTypes procType)
/*     */   {
/* 222 */     HashMap<ProcessorTypes, Boolean> processors = (HashMap)MetaRegionHelper.getInstance().getValue("*", MetaTypes.AQ_PROCESSOR);
/*     */     
/* 224 */     if (processors == null)
/* 225 */       return false;
/* 226 */     Boolean enabled = (Boolean)processors.get(procType);
/* 227 */     if ((enabled != null) && (enabled.booleanValue()))
/*     */     {
/* 229 */       return true;
/*     */     }
/* 231 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Map<String, String> getScheam(String path)
/*     */   {
/* 238 */     MetaValue mv = (MetaValue)metaValues.get(path);
/* 239 */     return mv.getInternalKeyMap();
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\data\schema\MetaServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */