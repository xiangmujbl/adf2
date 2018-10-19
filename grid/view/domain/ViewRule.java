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
/*    */ public class ViewRule
/*    */   implements DataSerializable
/*    */ {
/*    */   private static final long serialVersionUID = -765015351147445056L;
/*    */   private String viewPath;
/*    */   private String rawPath;
/*    */   private String name;
/*    */   private String content;
/*    */   
/*    */   public String getViewPath()
/*    */   {
/* 26 */     return this.viewPath;
/*    */   }
/*    */   
/* 29 */   public void setViewPath(String viewPath) { this.viewPath = viewPath; }
/*    */   
/*    */   public String getRawPath() {
/* 32 */     return this.rawPath;
/*    */   }
/*    */   
/* 35 */   public void setRawPath(String rawPath) { this.rawPath = rawPath; }
/*    */   
/*    */   public String getName() {
/* 38 */     return this.name;
/*    */   }
/*    */   
/* 41 */   public void setName(String name) { this.name = name; }
/*    */   
/*    */   public String getContent() {
/* 44 */     return this.content;
/*    */   }
/*    */   
/* 47 */   public void setContent(String content) { this.content = content; }
/*    */   
/*    */   public void toData(DataOutput out)
/*    */     throws IOException
/*    */   {
/* 52 */     DataSerializerExt.toData(getClass(), this, out);
/*    */   }
/*    */   
/*    */   public void fromData(DataInput in) throws IOException, ClassNotFoundException
/*    */   {
/* 57 */     DataSerializerExt.fromData(getClass(), this, in);
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\view\domain\ViewRule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */