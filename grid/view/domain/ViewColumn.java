/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.view.domain;
/*    */ 
/*    */ import com.gemstone.gemfire.DataSerializable;
/*    */ import com.jnj.adf.grid.support.gemfire.DataSerializerExt;
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
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
/*    */ public final class ViewColumn
/*    */   implements DataSerializable
/*    */ {
/*    */   private static final long serialVersionUID = -5081936203347804481L;
/*    */   private String viewPath;
/*    */   private String column;
/*    */   
/*    */   public ViewColumn() {}
/*    */   
/*    */   public ViewColumn(String viewPath)
/*    */   {
/* 34 */     this.viewPath = viewPath;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/* 39 */   private boolean primary = false;
/*    */   
/*    */   public String getViewPath() {
/* 42 */     return this.viewPath;
/*    */   }
/*    */   
/*    */   public void setViewPath(String viewPath) {
/* 46 */     this.viewPath = viewPath;
/*    */   }
/*    */   
/*    */   public String getColumn() {
/* 50 */     return this.column;
/*    */   }
/*    */   
/*    */   public void setColumn(String column) {
/* 54 */     this.column = column;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean isPrimary()
/*    */   {
/* 66 */     return this.primary;
/*    */   }
/*    */   
/*    */   public void setPrimary(boolean primary) {
/* 70 */     this.primary = primary;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void toData(DataOutput out)
/*    */     throws IOException
/*    */   {
/* 79 */     DataSerializerExt.toData(getClass(), this, out);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void fromData(DataInput in)
/*    */     throws IOException, ClassNotFoundException
/*    */   {
/* 88 */     DataSerializerExt.fromData(getClass(), this, in);
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\view\domain\ViewColumn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */