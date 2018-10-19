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
/*    */ public final class ViewRefRawRegion
/*    */   implements DataSerializable
/*    */ {
/*    */   private static final long serialVersionUID = 8108244051403063696L;
/*    */   private String viewPath;
/*    */   private String rawPath;
/*    */   private String groupCode;
/*    */   private boolean main;
/*    */   private String joinCondition;
/*    */   
/*    */   public String getViewPath()
/*    */   {
/* 38 */     return this.viewPath;
/*    */   }
/*    */   
/*    */   public void setViewPath(String viewPath) {
/* 42 */     this.viewPath = viewPath;
/*    */   }
/*    */   
/*    */   public String getRawPath() {
/* 46 */     return this.rawPath;
/*    */   }
/*    */   
/*    */   public void setRawPath(String rawPath) {
/* 50 */     this.rawPath = rawPath;
/*    */   }
/*    */   
/*    */   public String getGroupCode() {
/* 54 */     return this.groupCode;
/*    */   }
/*    */   
/*    */   public void setGroupCode(String groupCode) {
/* 58 */     this.groupCode = groupCode;
/*    */   }
/*    */   
/*    */   public boolean isMain() {
/* 62 */     return this.main;
/*    */   }
/*    */   
/*    */   public void setMain(boolean main) {
/* 66 */     this.main = main;
/*    */   }
/*    */   
/*    */   public String getJoinCondition() {
/* 70 */     return this.joinCondition;
/*    */   }
/*    */   
/*    */   public void setJoinCondition(String joinCondition) {
/* 74 */     this.joinCondition = joinCondition;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void toData(DataOutput out)
/*    */     throws IOException
/*    */   {
/* 84 */     DataSerializerExt.toData(getClass(), this, out);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void fromData(DataInput in)
/*    */     throws IOException, ClassNotFoundException
/*    */   {
/* 94 */     DataSerializerExt.fromData(getClass(), this, in);
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\view\domain\ViewRefRawRegion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */