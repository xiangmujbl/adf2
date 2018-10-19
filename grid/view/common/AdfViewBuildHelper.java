/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.view.common;
/*    */ 
/*    */ import com.jnj.adf.client.api.JsonObject;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class AdfViewBuildHelper
/*    */ {
/*    */   public static String buildBaseInfoKey(String viewPath)
/*    */   {
/* 15 */     JsonObject key = JsonObject.create();
/* 16 */     key.append("viewPath", viewPath);
/* 17 */     return key.toJson();
/*    */   }
/*    */   
/*    */   public static String buildColumnKey(String viewPath, String col) {
/* 21 */     JsonObject key = JsonObject.create();
/* 22 */     key.append("viewPath", viewPath);
/* 23 */     key.append("column", col);
/* 24 */     return key.toJson();
/*    */   }
/*    */   
/*    */   public static String buildUnionGroupKey(String viewPath, String code) {
/* 28 */     JsonObject key = JsonObject.create();
/* 29 */     key.append("viewPath", viewPath);
/* 30 */     key.append("unionCode", code);
/* 31 */     return key.toJson();
/*    */   }
/*    */   
/*    */   public static String buildRegionRefKey(String viewPath, String code, String rawPath) {
/* 35 */     JsonObject key = JsonObject.create();
/* 36 */     key.append("viewPath", viewPath);
/* 37 */     key.append("rawPath", rawPath);
/* 38 */     key.append("unionCode", code);
/* 39 */     return key.toJson();
/*    */   }
/*    */   
/*    */   public static String buildColumnMappingKey(String viewPath, String code, String column) {
/* 43 */     JsonObject key = JsonObject.create();
/* 44 */     key.append("viewPath", viewPath);
/* 45 */     key.append("column", column);
/* 46 */     key.append("unionCode", code);
/* 47 */     return key.toJson();
/*    */   }
/*    */   
/*    */   public static String buildDomainDefKey(String id) {
/* 51 */     JsonObject key = JsonObject.create();
/* 52 */     key.append("id", id);
/* 53 */     return key.toJson();
/*    */   }
/*    */   
/*    */   public static String buildRulesKey(String viewPath, String rawPath) {
/* 57 */     JsonObject key = JsonObject.create();
/* 58 */     key.append("viewPath", viewPath);
/* 59 */     key.append("rawPath", rawPath);
/* 60 */     return key.toJson();
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\view\common\AdfViewBuildHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */