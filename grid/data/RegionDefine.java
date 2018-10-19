/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.data;
/*    */ 
/*    */ import java.io.Serializable;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RegionDefine
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 6284650697328341029L;
/*    */   private String fullPath;
/*    */   private boolean enabledTemporal;
/*    */   private String[] resolverColumns;
/*    */   private boolean enabledLucene;
/*    */   private boolean enabledSolr;
/*    */   
/*    */   public RegionDefine() {}
/*    */   
/*    */   public RegionDefine(boolean enabledTemporal, boolean enabledLucene)
/*    */   {
/* 42 */     this.enabledTemporal = enabledTemporal;
/* 43 */     this.enabledLucene = enabledLucene;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean isEnabledTemporal()
/*    */   {
/* 49 */     return this.enabledTemporal;
/*    */   }
/*    */   
/*    */   public boolean isEnabledLucene() {
/* 53 */     return this.enabledLucene;
/*    */   }
/*    */   
/*    */   public String[] getResolverColumns() {
/* 57 */     return this.resolverColumns;
/*    */   }
/*    */   
/*    */   public void setResolverColumns(String[] resolverColumns) {
/* 61 */     this.resolverColumns = resolverColumns;
/*    */   }
/*    */   
/* 64 */   public boolean isEnabledSolr() { return this.enabledSolr; }
/*    */   
/*    */   public void setEnabledSolr(boolean enabledSolr) {
/* 67 */     this.enabledSolr = enabledSolr;
/*    */   }
/*    */   
/*    */   public String getFullPath() {
/* 71 */     return this.fullPath;
/*    */   }
/*    */   
/*    */   public void setFullPath(String fullPath) {
/* 75 */     this.fullPath = fullPath;
/*    */   }
/*    */   
/* 78 */   private boolean enableLogicKey = false;
/*    */   
/* 80 */   public boolean isEnableLogicKey() { return this.enableLogicKey; }
/*    */   
/*    */   public void setEnableLogicKey(boolean enableLogicKey) {
/* 83 */     this.enableLogicKey = enableLogicKey;
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\data\RegionDefine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */