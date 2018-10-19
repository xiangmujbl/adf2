/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.data.schema;
/*    */ 
/*    */ import java.util.Map;
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
/*    */ public class MetaValue
/*    */ {
/*    */   private String path;
/*    */   private boolean audit;
/*    */   private Map<String, String> internalKeyMap;
/*    */   private Map<String, String> externalKeyMap;
/*    */   
/*    */   public String getPath()
/*    */   {
/* 30 */     return this.path;
/*    */   }
/*    */   
/* 33 */   public void setPath(String path) { this.path = path; }
/*    */   
/*    */   public boolean isAudit() {
/* 36 */     return this.audit;
/*    */   }
/*    */   
/* 39 */   public void setAudit(boolean audit) { this.audit = audit; }
/*    */   
/*    */   public Map<String, String> getInternalKeyMap() {
/* 42 */     return this.internalKeyMap;
/*    */   }
/*    */   
/* 45 */   public void setInternalKeyMap(Map<String, String> internalKeyMap) { this.internalKeyMap = internalKeyMap; }
/*    */   
/*    */   public Map<String, String> getExternalKeyMap() {
/* 48 */     return this.externalKeyMap;
/*    */   }
/*    */   
/* 51 */   public void setExternalKeyMap(Map<String, String> externalKeyMap) { this.externalKeyMap = externalKeyMap; }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\data\schema\MetaValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */