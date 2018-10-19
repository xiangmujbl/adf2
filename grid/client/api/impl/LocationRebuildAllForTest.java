/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.client.api.impl;
/*     */ 
/*     */ import com.gemstone.gemfire.cache.Region;
/*     */ import com.gemstone.gemfire.cache.partition.PartitionRegionHelper;
/*     */ import com.gemstone.gemfire.pdx.PdxInstance;
/*     */ import com.jnj.adf.client.api.JsonObject;
/*     */ import com.jnj.adf.config.annotations.RemoteMethod;
/*     */ import com.jnj.adf.config.annotations.RemoteMethod.InvokeTypes;
/*     */ import com.jnj.adf.config.annotations.RemoteService;
/*     */ import com.jnj.adf.grid.client.api.support.ADFServiceContext;
/*     */ import com.jnj.adf.grid.client.api.support.ADFServiceContext.Keys;
/*     */ import com.jnj.adf.grid.common.PdxUtils;
/*     */ import com.jnj.adf.grid.data.ReservedFields;
/*     */ import com.jnj.adf.grid.manager.RegionHelper;
/*     */ import com.jnj.adf.grid.support.system.ADFConfigHelper;
/*     */ import com.jnj.adf.grid.support.system.ADFConfigHelper.ITEMS;
/*     */ import com.jnj.adf.grid.utils.JsonUtils;
/*     */ import com.jnj.adf.grid.utils.LogUtil;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.springframework.data.geo.Point;
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
/*     */ @RemoteService(securityCheckFlag=false, value="LocationRebuildAllForTest")
/*     */ public class LocationRebuildAllForTest
/*     */ {
/*     */   @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVERS)
/*     */   public List<Boolean> buildLocationAttributes(String locationField, String mappingPath)
/*     */   {
/*  79 */     String path = ADFServiceContext.getPath();
/*  80 */     String gridId = ADFConfigHelper.getProperty(ITEMS.SYSTEM_GRIDNAME);
/*  81 */     ADFServiceContext.setValue(ADFServiceContext.Keys.GRID, gridId);
/*     */     
/*  83 */     Region<String, PdxInstance> mappingRegion = RegionHelper.getRegion(mappingPath);
/*  84 */     if (mappingRegion == null)
/*     */     {
/*  86 */       LogUtil.getCoreLog().error("address->LatLon region {} is null", new Object[] { mappingPath });
/*  87 */       return Arrays.asList(new Boolean[] { Boolean.valueOf(false) });
/*     */     }
/*     */     
/*  90 */     Region<String, PdxInstance> r = RegionHelper.getRegion(path);
/*  91 */     if (r == null)
/*     */     {
/*  93 */       LogUtil.getCoreLog().error("location region {} is null", new Object[] { path });
/*  94 */       return Arrays.asList(new Boolean[] { Boolean.valueOf(false) });
/*     */     }
/*  96 */     if (PartitionRegionHelper.isPartitionedRegion(r))
/*     */     {
/*  98 */       r = PartitionRegionHelper.getLocalPrimaryData(r);
/*     */     }
/*     */     
/* 101 */     LogUtil.getCoreLog().debug("Start to rebuild Region: " + path + " on grid: " + gridId);
/* 102 */     Set<Entry<String, PdxInstance>> entrySet = r.entrySet();
/* 103 */     HashMap<String, PdxInstance> gfMap = new HashMap();
/* 104 */     long cursor = 0L;
/* 105 */     boolean f = false;
/* 106 */     for (Iterator<Entry<String, PdxInstance>> it = entrySet.iterator(); it.hasNext();)
/*     */     {
/* 108 */       f = true;
/* 109 */       cursor += 1L;
/* 110 */       Entry<String, PdxInstance> entry = (Entry)it.next();
/* 111 */       String key = (String)entry.getKey();
/* 112 */       PdxInstance value = (PdxInstance)entry.getValue();
/* 113 */       if ((entry.getValue() instanceof PdxInstance))
/*     */       {
/* 115 */         String locationName = (String)value.getField(locationField);
/* 116 */         if (StringUtils.isNotEmpty(locationName))
/*     */         {
/* 118 */           Point p = getLocationInfo(locationName, mappingRegion);
/* 119 */           if (p != null)
/*     */           {
/* 121 */             String jsonValue = PdxUtils.toJson(value);
/* 122 */             Map<String, Object> map = (Map)JsonUtils.jsonToObject(jsonValue, Map.class);
/* 123 */             map.put(ReservedFields.LOCATION_LATITUDE.fieldName(), Double.valueOf(p.getX()));
/* 124 */             map.put(ReservedFields.LOCATION_LONGITUDE.fieldName(), Double.valueOf(p.getY()));
/* 125 */             String newJsonValue = JsonUtils.objectToJson(map);
/* 126 */             LogUtil.getCoreLog().debug("New JsonValue: {} after adding the location info {}", new Object[] { newJsonValue, p.toString() });
/* 127 */             gfMap.put(key, PdxUtils.fromJson(newJsonValue));
/*     */           }
/*     */           else
/*     */           {
/* 131 */             LogUtil.getCoreLog().error("can not get location info for region {}, key {} and value {}", new Object[] { path, key, 
/* 132 */               PdxUtils.toJson(value) });
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 137 */     if (f)
/*     */     {
/* 139 */       if (gfMap.size() > 0)
/* 140 */         r.putAll(gfMap);
/*     */     }
/* 142 */     LogUtil.getCoreLog().debug("Finish to rebuild Region: " + path + " on grid: " + gridId);
/* 143 */     return Arrays.asList(new Boolean[] { Boolean.valueOf(true) });
/*     */   }
/*     */   
/*     */   private Point getLocationInfo(String locationName, Region<String, PdxInstance> mappingRegion)
/*     */   {
/* 148 */     String key = JsonObject.create().append("address", locationName).toJson();
/* 149 */     PdxInstance instance = (PdxInstance)mappingRegion.get(key);
/* 150 */     if (instance != null)
/*     */     {
/* 152 */       Double lat = getDouble("latitude", instance);
/* 153 */       Double lon = getDouble("longitude", instance);
/* 154 */       if ((lat != null) && (lon != null))
/* 155 */         return new Point(lat.doubleValue(), lon.doubleValue());
/*     */     }
/* 157 */     return null;
/*     */   }
/*     */   
/*     */   private Double getDouble(String fieldName, PdxInstance instance)
/*     */   {
/* 162 */     Object field = instance.getField(fieldName);
/*     */     try
/*     */     {
/* 165 */       if (field == null)
/* 166 */         return null;
/* 167 */       if ((field instanceof Double)) {
/* 168 */         return (Double)field;
/*     */       }
/* 170 */       return Double.valueOf(Double.parseDouble(String.valueOf(field)));
/*     */     }
/*     */     catch (Exception ex)
/*     */     {
/* 174 */       LogUtil.getCoreLog().error("Rebuild Location value:{} can't convert to Double", new Object[] { field }); }
/* 175 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\client\api\impl\LocationRebuildAllForTest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */