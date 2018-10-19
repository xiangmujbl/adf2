/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.data.raw;
/*    */ 
/*    */ import com.jnj.adf.client.api.JsonObject;
/*    */ import com.jnj.adf.client.api.remote.RawDataValue;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class JsonObjectBuilder
/*    */ {
/*    */   JsonObject jo;
/*    */   
/*    */   public JsonObjectBuilder()
/*    */   {
/* 31 */     this.jo = JsonObject.create();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public JsonObjectBuilder add(String key, Object value)
/*    */   {
/* 41 */     this.jo.append(key, value);
/* 42 */     return this;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public JsonObjectBuilder add(String key, RawDataValue rdvObject, String rdvFieldName)
/*    */   {
/* 55 */     this.jo.append(key, (String)rdvObject.getField(rdvFieldName));
/* 56 */     return this;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public Object toJsonObject()
/*    */   {
/* 64 */     return this.jo;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public String toJsonString()
/*    */   {
/* 72 */     return this.jo.toJson();
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\data\raw\JsonObjectBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */