/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.client.api.impl;
/*     */ 
/*     */ import com.gemstone.gemfire.cache.EntryNotFoundException;
/*     */ import com.gemstone.gemfire.cache.Region;
/*     */ import com.gemstone.gemfire.pdx.PdxInstance;
/*     */ import com.jnj.adf.client.api.GridBasicOperations;
/*     */ import com.jnj.adf.client.api.IBiz;
/*     */ import com.jnj.adf.client.api.JsonObject;
/*     */ import com.jnj.adf.grid.auth.impl.AuthSessionContextHelper;
/*     */ import com.jnj.adf.grid.client.api.ITemporalRegionService;
/*     */ import com.jnj.adf.grid.client.api.support.ADFServiceContext;
/*     */ import com.jnj.adf.grid.client.api.support.ADFServiceContext.Keys;
/*     */ import com.jnj.adf.grid.client.api.support.ADFServiceContext.QueryTypes;
/*     */ import com.jnj.adf.grid.client.api.support.BasicGridService;
/*     */ import com.jnj.adf.grid.common.ADFException;
/*     */ import com.jnj.adf.grid.common.GridPathNameUtil;
/*     */ import com.jnj.adf.grid.common.PdxUtils;
/*     */ import com.jnj.adf.grid.data.MetaService;
/*     */ import com.jnj.adf.grid.data.MetaServiceFactory;
/*     */ import com.jnj.adf.grid.data.ReservedFields;
/*     */ import com.jnj.adf.grid.data.temporal.TemporalValue;
/*     */ import com.jnj.adf.grid.data.temporal.utils.TemporalHelper;
/*     */ import com.jnj.adf.grid.data.temporal.utils.TemporalUtils;
/*     */ import com.jnj.adf.grid.data.temporal.utils.TemporalValueUtils;
/*     */ import com.jnj.adf.grid.manager.RegionHelper;
/*     */ import com.jnj.adf.grid.manager.logickey.ILogicKeyService;
/*     */ import com.jnj.adf.grid.manager.logickey.LogicKeyHelper;
/*     */ import com.jnj.adf.grid.security.column.ColumnPrivilegeHelper;
/*     */ import com.jnj.adf.grid.support.management.IManagementService;
/*     */ import com.jnj.adf.grid.utils.JsonUtils;
/*     */ import com.jnj.adf.grid.utils.LogUtil;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.springframework.beans.factory.FactoryBean;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BasicGridServiceImpl
/*     */   implements BasicGridService, FactoryBean<BasicGridService>
/*     */ {
/*     */   protected MetaService meta;
/*     */   
/*     */   BasicGridServiceImpl()
/*     */   {
/*  58 */     this.meta = MetaServiceFactory.createMetaService();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Region<?, ?> getRegion()
/*     */   {
/*  69 */     String path = (String)ADFServiceContext.getValue(ADFServiceContext.Keys.PATH);
/*  70 */     ADFServiceContext.QueryTypes queryType = (ADFServiceContext.QueryTypes)ADFServiceContext.getValue(ADFServiceContext.Keys.QUERY_TYPE);
/*  71 */     if (ADFServiceContext.QueryTypes.OnGroup == queryType) {
/*  72 */       String gridId = (String)ADFServiceContext.getValue(ADFServiceContext.Keys.GRID);
/*  73 */       path = GridPathNameUtil.getFullPath(gridId, path);
/*  74 */       return getRegionOnPath(path);
/*     */     }
/*  76 */     return getRegionOnPath(path);
/*     */   }
/*     */   
/*     */   private Region<?, ?> getRegionOnPath(String path)
/*     */   {
/*  81 */     Region<?, ?> region = RegionHelper.getRegion(path);
/*  82 */     if (region == null) {
/*  83 */       LogUtil.getCoreLog().debug("Get region is null, path is :{}", new Object[] { path });
/*     */     }
/*  85 */     return region;
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
/*     */   public boolean containsKey(String key)
/*     */   {
/* 111 */     Region<?, ?> region = getRegion();
/* 112 */     if (region != null)
/*     */     {
/* 114 */       String path = (String)ADFServiceContext.getValue(ADFServiceContext.Keys.PATH);
/* 115 */       if (LogicKeyHelper.enableLogicKey(path)) {
/* 116 */         String value = get(key);
/* 117 */         return StringUtils.isNotBlank(value);
/*     */       }
/*     */       
/* 120 */       return region.containsKey(key);
/*     */     }
/* 122 */     return false;
/*     */   }
/*     */   
/*     */   public String get(String key)
/*     */   {
/* 127 */     return get(key, "");
/*     */   }
/*     */   
/*     */   public String get(String key, String version) {
/* 131 */     return (String)get(key, String.class);
/*     */   }
/*     */   
/*     */   public <V> V get(String key, Class<V> classType)
/*     */   {
/* 136 */     V value = null;
/* 137 */     Region<?, ?> region = getRegion();
/* 138 */     if (region != null)
/*     */     {
/* 140 */       Object pdxValue = null;
/* 141 */       String path = ADFServiceContext.getPath();
/* 142 */       if (LogicKeyHelper.enableLogicKey(path)) {
/* 143 */         pdxValue = ((ILogicKeyService)IBiz.lookup(ILogicKeyService.class)).get(path, key);
/* 144 */       } else if (TemporalHelper.isEnableTemporal(path))
/*     */       {
/* 146 */         pdxValue = getTemporalRegionValue(path, key);
/*     */       } else {
/* 148 */         pdxValue = region.get(key);
/*     */       }
/*     */       
/* 151 */       if (pdxValue == null) {
/* 152 */         return value;
/*     */       }
/* 154 */       if (String.class.equals(classType)) {
/* 155 */         String valueString = PdxUtils.toJson(pdxValue);
/*     */         
/* 157 */         value = valueString;
/* 158 */       } else if ((pdxValue instanceof PdxInstance))
/*     */       {
/* 160 */         value = this.meta.applyReadSchema(path, (PdxInstance)pdxValue, classType);
/*     */       }
/*     */       else {
/* 163 */         throw new ADFException("Unsupported data type " + pdxValue.getClass().getName());
/*     */       }
/*     */       
/* 166 */       value = ColumnPrivilegeHelper.checkColumn(path, value, classType);
/*     */     }
/* 168 */     return value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private PdxInstance getTemporalRegionValue(String path, String key)
/*     */   {
/* 178 */     TemporalParam.correctValidAtAndAsof();
/* 179 */     LogUtil.getCoreLog().debug("validAt:{} asOf:{} ", new Object[] { TemporalUtils.format(TemporalParam.getParam().validAt), 
/* 180 */       TemporalUtils.formatTime(TemporalParam.getParam().asOf) });
/*     */     
/* 182 */     PdxInstance pdxInstance = ((ITemporalRegionService)IBiz.lookup(ITemporalRegionService.class)).getAsof(path, key, TemporalParam.getParam().validAt, 
/* 183 */       TemporalParam.getParam().asOf);
/* 184 */     return pdxInstance;
/*     */   }
/*     */   
/*     */   public Map<String, String> getAll(Collection<String> keys)
/*     */   {
/* 189 */     return getAll(keys, String.class);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public <V> Map<String, V> getAll(Collection<String> keys, Class<V> classType)
/*     */   {
/* 196 */     Map<String, V> valueMap = new HashMap();
/* 197 */     List keysList; List keysList; if ((keys instanceof List)) {
/* 198 */       keysList = (List)keys;
/*     */     } else {
/* 200 */       keysList = new ArrayList(keys);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 208 */     Region<?, ?> region = getRegion();
/* 209 */     Map<String, PdxInstance> rawMap = null;
/* 210 */     String path; Map<String, Object> m; if (region != null) {
/* 211 */       path = ADFServiceContext.getPath();
/* 212 */       Object v; if (LogicKeyHelper.enableLogicKey(path)) {
/* 213 */         rawMap = new HashMap();
/* 214 */         m = ((ILogicKeyService)IBiz.lookup(ILogicKeyService.class)).getAll(path, new HashSet(keys));
/* 215 */         if ((m != null) && (!m.isEmpty())) {
/* 216 */           v = null;
/* 217 */           for (Entry<String, Object> entry : m.entrySet()) {
/* 218 */             v = entry.getValue();
/* 219 */             if (v != null) {
/* 220 */               if ((v instanceof PdxInstance)) {
/* 221 */                 rawMap.put(entry.getKey(), (PdxInstance)v);
/*     */               } else {
/* 223 */                 rawMap.put(entry.getKey(), PdxUtils.objectToPdx(v));
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/* 228 */       } else if (TemporalHelper.isEnableTemporal(path)) {
/* 229 */         LogUtil.getCoreLog().debug("validAt:{} asOf:{} ", new Object[] { TemporalUtils.format(TemporalParam.getParam().validAt), 
/* 230 */           TemporalUtils.formatTime(TemporalParam.getParam().asOf) });
/* 231 */         rawMap = ((ITemporalRegionService)IBiz.lookup(ITemporalRegionService.class)).getAll(path, keysList, TemporalParam.getParam().validAt, 
/* 232 */           TemporalParam.getParam().asOf);
/*     */       } else {
/* 234 */         rawMap = region.getAll(keysList);
/*     */       }
/*     */       
/* 237 */       for (Entry<String, PdxInstance> entry : rawMap.entrySet()) {
/* 238 */         PdxInstance pdxInstance = (PdxInstance)entry.getValue();
/* 239 */         String valueString = "";
/* 240 */         Object key = entry.getKey();
/*     */         String keyString;
/* 242 */         String keyString; if ((key instanceof String)) {
/* 243 */           keyString = (String)key;
/*     */         } else
/* 245 */           keyString = JsonUtils.objectToJson(key);
/* 246 */         if (pdxInstance == null) {
/* 247 */           valueMap.put(keyString, null);
/*     */         }
/*     */         else {
/* 250 */           valueString = PdxUtils.toJson(pdxInstance);
/* 251 */           if (String.class.equals(classType))
/*     */           {
/*     */ 
/* 254 */             valueString = ColumnPrivilegeHelper.checkColumn(path, valueString);
/* 255 */             valueMap.put(keyString, valueString);
/*     */           }
/*     */           else {
/* 258 */             V domainValue = this.meta.applyReadSchema(path, pdxInstance, classType);
/*     */             
/* 260 */             domainValue = ColumnPrivilegeHelper.checkColumn(path, domainValue, classType);
/* 261 */             valueMap.put(keyString, domainValue);
/*     */           }
/*     */         }
/*     */       } }
/* 265 */     return valueMap;
/*     */   }
/*     */   
/*     */   protected <V> PdxInstance valueToPdx(V value) {
/* 269 */     String json = null;
/* 270 */     LocationParam locParam = (LocationParam)ADFServiceContext.getValue(ADFServiceContext.Keys.LOCATION);
/* 271 */     if (locParam != null) {
/* 272 */       if ((value instanceof String)) {
/* 273 */         json = (String)value;
/* 274 */         Map<String, Object> map = (Map)JsonUtils.jsonToObject(json, Map.class);
/* 275 */         map.put(ReservedFields.LOCATION_LATITUDE.fieldName(), Double.valueOf(locParam.latitude));
/* 276 */         map.put(ReservedFields.LOCATION_LONGITUDE.fieldName(), Double.valueOf(locParam.longitude));
/* 277 */         json = JsonUtils.objectToJson(map);
/*     */       } else {
/* 279 */         json = this.meta.applyWriteSchema(ADFServiceContext.getPath(), value, locParam.latitude, locParam.longitude);
/*     */       }
/* 281 */     } else if ((value instanceof String)) {
/* 282 */       json = (String)value;
/*     */     } else {
/* 284 */       json = this.meta.applyWriteSchema(ADFServiceContext.getPath(), value);
/*     */     }
/* 286 */     PdxInstance valuePdx = PdxUtils.fromJson(json);
/* 287 */     return valuePdx;
/*     */   }
/*     */   
/*     */   public <V> V put(String key, V value)
/*     */   {
/* 292 */     return (V)put(key, value, null);
/*     */   }
/*     */   
/*     */   public <V> V put(String key, V value, Map<String, Object> callBackArgs)
/*     */   {
/* 297 */     Map<String, Object> args = getCallbackArgs();
/* 298 */     if ((callBackArgs != null) && (!callBackArgs.isEmpty())) {
/* 299 */       args.putAll(callBackArgs);
/*     */     }
/*     */     
/* 302 */     Region region = getRegion();
/* 303 */     if (region != null) {
/* 304 */       PdxInstance valuePdx = valueToPdx(value);
/*     */       
/*     */ 
/* 307 */       String path = (String)ADFServiceContext.getValue(ADFServiceContext.Keys.PATH);
/* 308 */       if (LogicKeyHelper.enableLogicKey(path))
/*     */       {
/*     */ 
/* 311 */         ((ILogicKeyService)IBiz.lookup(ILogicKeyService.class)).put(path, key, valuePdx, args);
/*     */       }
/* 313 */       else if (TemporalHelper.isEnableTemporal(path)) {
/* 314 */         TemporalValue tvalue = TemporalValueUtils.constructTValue(path, valuePdx);
/* 315 */         ((ITemporalRegionService)IBiz.lookup(ITemporalRegionService.class)).put(path, key, tvalue, args);
/*     */       } else {
/* 317 */         region.put(key, valuePdx, args);
/*     */       } }
/* 319 */     return value;
/*     */   }
/*     */   
/*     */   public <V> void putAll(Map<String, ? extends V> map)
/*     */   {
/* 324 */     putAll(map, null);
/*     */   }
/*     */   
/*     */   public <V> void putAll(Map<String, ? extends V> map, Map<String, Object> callBackArgs)
/*     */   {
/* 329 */     Map<String, Object> args = getCallbackArgs();
/* 330 */     if ((callBackArgs != null) && (!callBackArgs.isEmpty())) {
/* 331 */       args.putAll(callBackArgs);
/*     */     }
/*     */     
/* 334 */     Set<String> keys = new HashSet();
/* 335 */     Map transfer = new HashMap();
/*     */     
/* 337 */     Region region = getRegion();
/*     */     
/* 339 */     if (region != null)
/*     */     {
/* 341 */       String path = (String)ADFServiceContext.getValue(ADFServiceContext.Keys.PATH);
/* 342 */       boolean enableLogicKey = LogicKeyHelper.enableLogicKey(path);
/*     */       
/* 344 */       boolean isEnableTemporal = TemporalHelper.isEnableTemporal(path);
/*     */       
/* 346 */       for (Iterator<String> i = map.keySet().iterator(); i.hasNext();) {
/* 347 */         String key = (String)i.next();
/* 348 */         V value = map.get(key);
/* 349 */         PdxInstance valuePdx = valueToPdx(value);
/*     */         
/* 351 */         keys.add(key);
/*     */         
/* 353 */         if (isEnableTemporal) {
/* 354 */           TemporalValue tvalue = TemporalValueUtils.constructTValue(path, valuePdx);
/* 355 */           if (tvalue != null)
/* 356 */             transfer.put(key, tvalue);
/*     */         } else {
/* 358 */           transfer.put(key, valuePdx);
/*     */         }
/*     */       }
/*     */       
/* 362 */       if (enableLogicKey) {
/* 363 */         ((ILogicKeyService)IBiz.lookup(ILogicKeyService.class)).putAll(path, keys, transfer, args);
/* 364 */       } else if (isEnableTemporal) {
/* 365 */         ((ITemporalRegionService)IBiz.lookup(ITemporalRegionService.class)).putAll(path, keys, transfer, args);
/*     */       } else {
/* 367 */         region.putAll(transfer, args);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected Map<String, Object> getCallbackArgs() {
/* 373 */     Boolean audit = (Boolean)ADFServiceContext.getValue(ADFServiceContext.Keys.AUDIT);
/* 374 */     Boolean batching = (Boolean)ADFServiceContext.getValue(ADFServiceContext.Keys.BATCHING);
/* 375 */     JsonObject jo = JsonObject.create();
/* 376 */     if (audit != null) {
/* 377 */       jo.append("adf.import.audit", audit);
/*     */     }
/* 379 */     if (batching != null) {
/* 380 */       jo.append("adf.import.batching", batching);
/*     */     }
/* 382 */     String userId = AuthSessionContextHelper.getUserId();
/* 383 */     if (StringUtils.isNotEmpty(userId)) {
/* 384 */       jo.append("adf.import.userid", userId);
/*     */     }
/* 386 */     return jo.toMap();
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean remove(String key)
/*     */   {
/* 392 */     Map<String, Object> args = getCallbackArgs();
/* 393 */     Region region = getRegion();
/* 394 */     if (region != null) {
/*     */       try {
/* 396 */         String path = ADFServiceContext.getPath();
/* 397 */         boolean isEnableTemporal = TemporalHelper.isEnableTemporal(path);
/* 398 */         if (LogicKeyHelper.enableLogicKey(path)) {
/* 399 */           ((ILogicKeyService)IBiz.lookup(ILogicKeyService.class)).remove(path, key);
/* 400 */         } else if (isEnableTemporal)
/*     */         {
/*     */ 
/* 403 */           long validFrom = TemporalUtils.now(true);
/* 404 */           long validTo = TemporalUtils.max();
/* 405 */           ((ITemporalRegionService)IBiz.lookup(ITemporalRegionService.class)).remove(path, key, validFrom, validTo, args);
/*     */         } else {
/* 407 */           region.destroy(key, args);
/*     */         }
/* 409 */         return true;
/*     */       }
/*     */       catch (EntryNotFoundException localEntryNotFoundException) {}
/*     */     }
/*     */     
/* 414 */     return false;
/*     */   }
/*     */   
/*     */   public void removeAll()
/*     */   {
/* 419 */     Region region = getRegion();
/* 420 */     String fullPath = region.getFullPath();
/*     */     
/* 422 */     IManagementService mgr = (IManagementService)IBiz.lookup(IManagementService.class);
/* 423 */     List<String> list = mgr.clean(fullPath);
/* 424 */     LogUtil.getCoreLog().debug("path:{},results:{}", new Object[] { fullPath, list });
/*     */   }
/*     */   
/*     */   public boolean softRemove(String key)
/*     */   {
/* 429 */     Region<?, ?> region = getRegion();
/* 430 */     if (region != null) {
/* 431 */       PdxInstance pdx = (PdxInstance)region.get(key);
/* 432 */       if (pdx != null) {
/* 433 */         Map<String, Object> valueMap = PdxUtils.pdxToMap(pdx);
/* 434 */         valueMap.put(ReservedFields.DELETED_FLAG.fieldName(), Boolean.valueOf(true));
/* 435 */         put(key, valueMap);
/*     */       }
/*     */     }
/* 438 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public BasicGridService getObject()
/*     */     throws Exception
/*     */   {
/* 448 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Class<?> getObjectType()
/*     */   {
/* 458 */     return GridBasicOperations.class;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isSingleton()
/*     */   {
/* 468 */     return true;
/*     */   }
/*     */   
/*     */   public Entry<String, String> getEntry(String key)
/*     */   {
/* 473 */     return getEntry(key, String.class);
/*     */   }
/*     */   
/*     */   public <V> Entry<String, V> getEntry(String key, Class<V> classType)
/*     */   {
/* 478 */     Entry<String, V> value = null;
/* 479 */     Region<?, ?> region = getRegion();
/* 480 */     if (region != null) {
/* 481 */       Object pdxValue = null;
/* 482 */       String path = ADFServiceContext.getPath();
/* 483 */       if (LogicKeyHelper.enableLogicKey(path)) {
/* 484 */         pdxValue = ((ILogicKeyService)IBiz.lookup(ILogicKeyService.class)).get(path, key);
/* 485 */       } else if (TemporalHelper.isEnableTemporal(path)) {
/* 486 */         pdxValue = getTemporalRegionValue(path, key);
/*     */       } else
/* 488 */         pdxValue = region.get(key);
/* 489 */       if (pdxValue == null)
/* 490 */         return value;
/* 491 */       if (String.class.equals(classType)) {
/* 492 */         String valueString = PdxUtils.toJson(pdxValue);
/*     */         
/* 494 */         value = new EntryImpl(key, ColumnPrivilegeHelper.checkColumn(path, valueString));
/*     */       } else {
/* 496 */         V entryValue = this.meta.applyReadSchema(path, (PdxInstance)pdxValue, classType);
/*     */         
/*     */ 
/* 499 */         entryValue = ColumnPrivilegeHelper.checkColumn(path, entryValue, classType);
/* 500 */         value = new EntryImpl(key, entryValue);
/*     */       }
/*     */     }
/* 503 */     return value;
/*     */   }
/*     */   
/*     */   protected Entry<String, PdxInstance> getEntry(String adfKey, Region<?, ?> region) {
/* 507 */     PdxInstance pdxInstance = (PdxInstance)region.get(adfKey);
/* 508 */     if (pdxInstance != null) {
/* 509 */       return new EntryImpl(adfKey, pdxInstance);
/*     */     }
/* 511 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\client\api\impl\BasicGridServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */