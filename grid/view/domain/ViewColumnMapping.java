/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.view.domain;
/*     */ 
/*     */ import com.gemstone.gemfire.DataSerializable;
/*     */ import com.jnj.adf.grid.support.gemfire.DataSerializerExt;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import java.util.Arrays;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ViewColumnMapping
/*     */   implements DataSerializable
/*     */ {
/*     */   private static final long serialVersionUID = 3890153510165358088L;
/*     */   private String viewPath;
/*     */   private String viewColumn;
/*     */   private String unionCode;
/*     */   private String rawPath;
/*     */   private String rawColumn;
/*     */   private String defaultValue;
/*     */   private int generateOrder;
/*     */   private String precdingColumns;
/*     */   private String joinCondition;
/*     */   private String[] generateRules;
/*     */   private String[] updateRules;
/*     */   private String sourceSystem;
/*     */   
/*     */   public ViewColumnMapping() {}
/*     */   
/*     */   public ViewColumnMapping(String viewPath, String viewColumn, String unionCode)
/*     */   {
/*  39 */     this.viewPath = viewPath;
/*  40 */     this.viewColumn = viewColumn;
/*  41 */     this.unionCode = unionCode;
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
/*     */   public String getViewPath()
/*     */   {
/*  58 */     return this.viewPath;
/*     */   }
/*     */   
/*     */   public void setViewPath(String viewPath) {
/*  62 */     this.viewPath = viewPath;
/*     */   }
/*     */   
/*     */   public String getViewColumn() {
/*  66 */     return this.viewColumn;
/*     */   }
/*     */   
/*     */   public void setViewColumn(String viewColumn) {
/*  70 */     this.viewColumn = viewColumn;
/*     */   }
/*     */   
/*     */   public String getUnionCode() {
/*  74 */     return this.unionCode;
/*     */   }
/*     */   
/*     */   public void setUnionCode(String unionCode) {
/*  78 */     this.unionCode = unionCode;
/*     */   }
/*     */   
/*     */   public String getRawPath() {
/*  82 */     return this.rawPath;
/*     */   }
/*     */   
/*     */   public void setRawPath(String rawPath) {
/*  86 */     this.rawPath = rawPath;
/*     */   }
/*     */   
/*     */   public String getRawColumn() {
/*  90 */     return this.rawColumn;
/*     */   }
/*     */   
/*     */   public void setRawColumn(String rawColumn) {
/*  94 */     this.rawColumn = rawColumn;
/*     */   }
/*     */   
/*     */   public String getDefaultValue() {
/*  98 */     return this.defaultValue;
/*     */   }
/*     */   
/*     */   public void setDefaultValue(String defaultValue) {
/* 102 */     this.defaultValue = defaultValue;
/*     */   }
/*     */   
/*     */   public int getGenerateOrder() {
/* 106 */     return this.generateOrder;
/*     */   }
/*     */   
/*     */   public void setGenerateOrder(int generateOrder) {
/* 110 */     this.generateOrder = generateOrder;
/*     */   }
/*     */   
/*     */   public String getJoinCondition() {
/* 114 */     return this.joinCondition;
/*     */   }
/*     */   
/*     */   public void setJoinCondition(String joinCondition) {
/* 118 */     this.joinCondition = joinCondition;
/*     */   }
/*     */   
/*     */   public String[] getGenerateRules() {
/* 122 */     return this.generateRules;
/*     */   }
/*     */   
/*     */   public void setGenerateRules(String[] generateRules) {
/* 126 */     this.generateRules = generateRules;
/*     */   }
/*     */   
/*     */   public String[] getUpdateRules() {
/* 130 */     return this.updateRules;
/*     */   }
/*     */   
/*     */   public void setUpdateRules(String[] updateRules) {
/* 134 */     this.updateRules = updateRules;
/*     */   }
/*     */   
/*     */   public void toData(DataOutput out) throws IOException
/*     */   {
/* 139 */     DataSerializerExt.toData(getClass(), this, out);
/*     */   }
/*     */   
/*     */   public void fromData(DataInput in) throws IOException, ClassNotFoundException
/*     */   {
/* 144 */     DataSerializerExt.fromData(getClass(), this, in);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getPrecdingColumns()
/*     */   {
/* 152 */     return this.precdingColumns;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPrecdingColumns(String precdingColumns)
/*     */   {
/* 160 */     this.precdingColumns = precdingColumns;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 169 */     return 
/*     */     
/*     */ 
/* 172 */       "ViewColumnMapping [viewPath=" + this.viewPath + ", viewColumn=" + this.viewColumn + ", unionCode=" + this.unionCode + ", rawPath=" + this.rawPath + ", rawColumn=" + this.rawColumn + ", defaultValue=" + this.defaultValue + ", generateOrder=" + this.generateOrder + ", precdingColumns=" + this.precdingColumns + ", joinCondition=" + this.joinCondition + ", generateRules=" + Arrays.toString(this.generateRules) + ", updateRules=" + Arrays.toString(this.updateRules) + "]";
/*     */   }
/*     */   
/* 175 */   public String getSourceSystem() { return this.sourceSystem; }
/*     */   
/*     */   public void setSourceSystem(String sourceSystem) {
/* 178 */     this.sourceSystem = sourceSystem;
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\view\domain\ViewColumnMapping.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */