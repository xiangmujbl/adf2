/*     */ package com.jnj.adf.dataservice.adfcoreignite.client.api;
/*     */ 
/*     */ import com.jnj.adf.grid.utils.JsonUtils;
/*     */ import java.util.Map;
/*     */ import java.util.TreeMap;
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
/*     */ public final class JsonObject
/*     */ {
/*     */   private Map<String, Object> values;
/*     */   
/*     */   private JsonObject()
/*     */   {
/*  37 */     this.values = new TreeMap();
/*     */   }
/*     */   
/*     */ 
/*     */   public static final JsonObject append(String jsonValue)
/*     */   {
/*  43 */     JsonObject jo = new JsonObject();
/*  44 */     jo.values = new TreeMap();
/*  45 */     Map m = (Map)JsonUtils.jsonToObject(jsonValue, Map.class);
/*  46 */     if (m != null)
/*  47 */       jo.values.putAll(m);
/*  48 */     return jo;
/*     */   }
/*     */   
/*     */   public final JsonObject append(JsonObject value)
/*     */   {
/*  53 */     if (value != null)
/*  54 */       this.values.putAll(value.values);
/*  55 */     return this;
/*     */   }
/*     */   
/*     */   public static final JsonObject create()
/*     */   {
/*  60 */     JsonObject jo = new JsonObject();
/*  61 */     jo.values = new TreeMap();
/*  62 */     return jo;
/*     */   }
/*     */   
/*     */   public JsonObject append(String key, String value)
/*     */   {
/*  67 */     this.values.put(key, value);
/*  68 */     return this;
/*     */   }
/*     */   
/*     */   public JsonObject append(String key, Object value)
/*     */   {
/*  73 */     this.values.put(key, value);
/*  74 */     return this;
/*     */   }
/*     */   
/*     */   public String getValue(String key)
/*     */   {
/*  79 */     Object o = this.values.get(key);
/*  80 */     return o == null ? null : String.valueOf(o);
/*     */   }
/*     */   
/*     */   public <T> T getValue(String key, Class<T> ct)
/*     */   {
/*  85 */     Object o = this.values.get(key);
/*  86 */     return o == null ? null : o;
/*     */   }
/*     */   
/*     */   public String toJson()
/*     */   {
/*  91 */     return JsonUtils.objectToJson(this.values);
/*     */   }
/*     */   
/*     */   public Map<String, Object> toMap()
/*     */   {
/*  96 */     return this.values;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 107 */     return toJson();
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\client\api\JsonObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */