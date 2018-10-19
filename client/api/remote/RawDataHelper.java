/*     */ package com.jnj.adf.dataservice.adfcoreignite.client.api.remote;
/*     */ 
/*     */ import com.gemstone.gemfire.pdx.PdxInstance;
/*     */ import com.jnj.adf.client.api.JsonObject;
/*     */ import com.jnj.adf.grid.client.api.impl.RawDataTemplateImpl;
/*     */ import com.jnj.adf.grid.common.ADFException;
/*     */ import com.jnj.adf.grid.common.PdxUtils;
/*     */ import com.jnj.adf.grid.data.raw.JsonObjectBuilder;
/*     */ import com.jnj.adf.grid.data.raw.RawDataBuilder;
/*     */ import com.jnj.adf.grid.utils.JsonUtils;
/*     */ import com.jnj.adf.grid.utils.LogUtil;
/*     */ import java.util.Map;
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
/*     */ public class RawDataHelper
/*     */ {
/*  38 */   private static RawDataHelper INST = new RawDataHelper();
/*     */   
/*     */   public static final RawDataHelper getInstance()
/*     */   {
/*  42 */     return INST;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RawDataTemplate createTemplate(String fullPath)
/*     */   {
/*     */     try
/*     */     {
/*  54 */       return new RawDataTemplateImpl().create(fullPath);
/*     */ 
/*     */     }
/*     */     catch (ADFException e)
/*     */     {
/*  59 */       LogUtil.getCoreLog().error("create RawDataTemplate error:{}", new Object[] { e.getMessage() });
/*     */     }
/*  61 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object createRawData(Map map)
/*     */   {
/*  72 */     String json = JsonUtils.objectToJson(map);
/*  73 */     PdxInstance valuePdx = PdxUtils.fromJson(json);
/*  74 */     return valuePdx;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RawDataValue fetchByKey(String targetRegionName, String keyFieldName, Object keyValue)
/*     */   {
/*  86 */     JsonObject joTKey = JsonObject.create();
/*  87 */     joTKey.append(keyFieldName, keyValue);
/*     */     
/*  89 */     return fetchByKey(targetRegionName, joTKey.toJson());
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
/*     */   public RawDataValue fetchByKey(String targetRegionName, String keyFieldName, RawDataValue rdv, String valueFieldName)
/*     */   {
/* 102 */     return fetchByKey(targetRegionName, keyFieldName, rdv.getField(valueFieldName));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RawDataValue fetchByKey(String sTargetRegion, String sKeyJson)
/*     */   {
/* 112 */     RawDataTemplate rdt = createTemplate(sTargetRegion);
/* 113 */     RawData rd = rdt.get(sKeyJson);
/* 114 */     if (rd == null)
/*     */     {
/* 116 */       return null;
/*     */     }
/* 118 */     RawDataValue ret = rd.getValue();
/* 119 */     return ret;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String makeJsonKey(String keyField, String value)
/*     */   {
/* 129 */     return new JsonObjectBuilder().add(keyField, value).toJsonString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String makeJsonKey(String keyField, RawDataValue fromValue, String valueField)
/*     */   {
/* 140 */     return new JsonObjectBuilder().add(keyField, fromValue, valueField).toJsonString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RawDataBuilder makeRawData(String key, Object value)
/*     */   {
/* 150 */     return new RawDataBuilder().put(key, value);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RawDataBuilder makeRawData(String key, RawDataValue rdvObject, String rdvFieldName)
/*     */   {
/* 161 */     return new RawDataBuilder().put(key, rdvObject, rdvFieldName);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public JsonObjectBuilder makeJsonObject(String key, Object value)
/*     */   {
/* 171 */     return new JsonObjectBuilder().add(key, value);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public JsonObjectBuilder makeJsonObject(String key, RawDataValue rdvObject, String rdvFieldName)
/*     */   {
/* 182 */     return new JsonObjectBuilder().add(key, rdvObject, rdvFieldName);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String makeKeyFromJson(JsonObject jo, String... keyItemNames)
/*     */   {
/* 193 */     JsonObject jok = JsonObject.create();
/* 194 */     for (String key : keyItemNames) {
/* 195 */       jok.append(key, jo.getValue(key));
/*     */     }
/* 197 */     return jok.toJson();
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
/* 208 */   public Object getField(RawDataValue rdv, String field) { return rdv.getField(field); }
/*     */   
/*     */   public String getString(RawDataValue rdv, String field) {
/* 211 */     Object o = getField(rdv, field);
/* 212 */     return o == null ? null : o.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\client\api\remote\RawDataHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */