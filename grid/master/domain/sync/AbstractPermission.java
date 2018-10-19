/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.master.domain.sync;
/*    */ 
/*    */ import com.gemstone.gemfire.DataSerializable;
/*    */ import com.jnj.adf.grid.support.gemfire.DataSerializerExt;
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ public class AbstractPermission
/*    */   implements DataSerializable
/*    */ {
/*    */   private String gridId;
/*    */   
/*    */   public String getGridId()
/*    */   {
/* 17 */     return this.gridId;
/*    */   }
/*    */   
/*    */   public void setGridId(String gridId) {
/* 21 */     this.gridId = gridId;
/*    */   }
/*    */   
/*    */   public void toData(DataOutput out)
/*    */     throws IOException
/*    */   {
/* 27 */     DataSerializerExt.toData(getClass(), this, out);
/*    */   }
/*    */   
/*    */   public void fromData(DataInput in)
/*    */     throws IOException, ClassNotFoundException
/*    */   {
/* 33 */     DataSerializerExt.fromData(getClass(), this, in);
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\master\domain\sync\AbstractPermission.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */