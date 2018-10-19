/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.view.domain;
/*     */ 
/*     */ import com.google.common.base.MoreObjects;
/*     */ import com.google.common.base.MoreObjects.ToStringHelper;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
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
/*     */ public class ViewMeta
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -5697733749673696614L;
/*     */   private String viewPath;
/*     */   private String unionCode;
/*     */   private String mainPath;
/*     */   private String sourceSystem;
/*     */   private List<ViewColumnMapping> columns;
/*     */   private String[] pkcols;
/*     */   
/*     */   public ViewMeta() {}
/*     */   
/*     */   public ViewMeta(String viewPath)
/*     */   {
/*  34 */     this.viewPath = viewPath;
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
/*     */   public String getViewPath()
/*     */   {
/*  50 */     return this.viewPath;
/*     */   }
/*     */   
/*     */   public void setViewPath(String viewPath) {
/*  54 */     this.viewPath = viewPath;
/*     */   }
/*     */   
/*     */   public String getUnionCode() {
/*  58 */     return this.unionCode;
/*     */   }
/*     */   
/*     */   public void setUnionCode(String unionCode) {
/*  62 */     this.unionCode = unionCode;
/*     */   }
/*     */   
/*     */   public String getMainPath() {
/*  66 */     return this.mainPath;
/*     */   }
/*     */   
/*     */   public void setMainPath(String mainPath) {
/*  70 */     this.mainPath = mainPath;
/*     */   }
/*     */   
/*     */   public List<ViewColumnMapping> getColumns() {
/*  74 */     return this.columns;
/*     */   }
/*     */   
/*     */   public void setColumns(List<ViewColumnMapping> columns) {
/*  78 */     this.columns = columns;
/*     */   }
/*     */   
/*     */   public String[] getPkcols() {
/*  82 */     return this.pkcols;
/*     */   }
/*     */   
/*     */   public void setPkcols(String[] pkcols) {
/*  86 */     this.pkcols = pkcols;
/*     */   }
/*     */   
/*     */   public String getSourceSystem() {
/*  90 */     return this.sourceSystem;
/*     */   }
/*     */   
/*     */   public void setSourceSystem(String sourceSystem) {
/*  94 */     this.sourceSystem = sourceSystem;
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/*  99 */     return 
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 104 */       MoreObjects.toStringHelper(this).add("viewPath", this.viewPath).add("unionCode", this.unionCode).add("mainPath", this.mainPath).add("sourceSystem", this.sourceSystem).toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\view\domain\ViewMeta.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */