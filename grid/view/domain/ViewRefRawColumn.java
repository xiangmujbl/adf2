/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.view.domain;
/*     */ 
/*     */ import com.gemstone.gemfire.DataSerializable;
/*     */ import com.jnj.adf.grid.support.gemfire.DataSerializerExt;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
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
/*     */ public final class ViewRefRawColumn
/*     */   implements DataSerializable
/*     */ {
/*     */   private static final long serialVersionUID = -6351998424165695765L;
/*     */   private String viewPath;
/*     */   private String column;
/*     */   private String groupCode;
/*     */   private String rawPath;
/*     */   private String rawColumn;
/*     */   private String defaultVal;
/*     */   
/*     */   public String getViewPath()
/*     */   {
/*  39 */     return this.viewPath;
/*     */   }
/*     */   
/*     */   public void setViewPath(String viewPath) {
/*  43 */     this.viewPath = viewPath;
/*     */   }
/*     */   
/*     */   public String getColumn() {
/*  47 */     return this.column;
/*     */   }
/*     */   
/*     */   public void setColumn(String column) {
/*  51 */     this.column = column;
/*     */   }
/*     */   
/*     */   public String getGroupCode() {
/*  55 */     return this.groupCode;
/*     */   }
/*     */   
/*     */   public void setGroupCode(String groupCode) {
/*  59 */     this.groupCode = groupCode;
/*     */   }
/*     */   
/*     */   public String getRawPath() {
/*  63 */     return this.rawPath;
/*     */   }
/*     */   
/*     */   public void setRawPath(String rawPath) {
/*  67 */     this.rawPath = rawPath;
/*     */   }
/*     */   
/*     */   public String getRawColumn() {
/*  71 */     return this.rawColumn;
/*     */   }
/*     */   
/*     */   public void setRawColumn(String rawColumn) {
/*  75 */     this.rawColumn = rawColumn;
/*     */   }
/*     */   
/*     */   public String getDefaultVal() {
/*  79 */     return this.defaultVal;
/*     */   }
/*     */   
/*     */   public void setDefaultVal(String defaultVal) {
/*  83 */     this.defaultVal = defaultVal;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void toData(DataOutput out)
/*     */     throws IOException
/*     */   {
/*  94 */     DataSerializerExt.toData(getClass(), this, out);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void fromData(DataInput in)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/* 105 */     DataSerializerExt.fromData(getClass(), this, in);
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\view\domain\ViewRefRawColumn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */