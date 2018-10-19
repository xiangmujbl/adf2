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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class ViewGroup
/*    */   implements DataSerializable
/*    */ {
/*    */   private static final long serialVersionUID = 119168627134882732L;
/*    */   private String viewPath;
/*    */   private String unionCode;
/*    */   private String mainPath;
/*    */   private String sourceSystem;
/*    */   
/*    */   public String getViewPath()
/*    */   {
/* 41 */     return this.viewPath;
/*    */   }
/*    */   
/*    */   public void setViewPath(String viewPath) {
/* 45 */     this.viewPath = viewPath;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void toData(DataOutput out)
/*    */     throws IOException
/*    */   {
/* 64 */     DataSerializerExt.toData(getClass(), this, out);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void fromData(DataInput in)
/*    */     throws IOException, ClassNotFoundException
/*    */   {
/* 73 */     DataSerializerExt.fromData(getClass(), this, in);
/*    */   }
/*    */   
/*    */   public String getMainPath() {
/* 77 */     return this.mainPath;
/*    */   }
/*    */   
/*    */   public void setMainPath(String mainPath) {
/* 81 */     this.mainPath = mainPath;
/*    */   }
/*    */   
/*    */   public String getUnionCode() {
/* 85 */     return this.unionCode;
/*    */   }
/*    */   
/*    */   public void setUnionCode(String unionCode) {
/* 89 */     this.unionCode = unionCode;
/*    */   }
/*    */   
/*    */   public String getSourceSystem() {
/* 93 */     return this.sourceSystem;
/*    */   }
/*    */   
/*    */   public void setSourceSystem(String sourceSystem) {
/* 97 */     this.sourceSystem = sourceSystem;
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\view\domain\ViewGroup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */