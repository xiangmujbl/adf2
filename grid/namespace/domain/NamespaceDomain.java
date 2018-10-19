/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.namespace.domain;
/*    */ 
/*    */ import com.jnj.adf.client.api.JsonObject;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NamespaceDomain
/*    */ {
/*    */   public static final String _GRIDID = "gridId";
/*    */   public static final String _PATH = "path";
/*    */   public static final String _NAMESPACE = "_NAMESPACE";
/*    */   private JsonObject key;
/*    */   private JsonObject value;
/*    */   
/*    */   public void parse(String jsonValue)
/*    */   {
/* 23 */     this.value = JsonObject.append(jsonValue);
/*    */   }
/*    */   
/*    */   public JsonObject genKey(String gridId, String path) {
/* 27 */     this.key = JsonObject.create().append("gridId", gridId).append("path", path);
/* 28 */     return this.key;
/*    */   }
/*    */   
/*    */   public JsonObject genValue(String namespace)
/*    */   {
/* 33 */     this.value = JsonObject.create().append(this.key).append("_NAMESPACE", namespace);
/* 34 */     return this.value;
/*    */   }
/*    */   
/*    */ 
/*    */   public JsonObject genValue(JsonObject jk, String namespace)
/*    */   {
/* 40 */     this.value = JsonObject.create().append(jk).append("_NAMESPACE", namespace);
/* 41 */     return this.value;
/*    */   }
/*    */   
/*    */   public String getKey()
/*    */   {
/* 46 */     return this.key.toJson();
/*    */   }
/*    */   
/*    */   public String getValue()
/*    */   {
/* 51 */     return this.value.toJson();
/*    */   }
/*    */   
/*    */   public String getPath()
/*    */   {
/* 56 */     return this.value.getValue("path");
/*    */   }
/*    */   
/*    */   public String getNamespace()
/*    */   {
/* 61 */     return this.value.getValue("_NAMESPACE");
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\namespace\domain\NamespaceDomain.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */