/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.support.gemfire;
/*    */ 
/*    */ import com.gemstone.gemfire.DataSerializable;
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ 
/*    */ public abstract class DataSerializerAdapter
/*    */   implements DataSerializable
/*    */ {
/*    */   private static final long serialVersionUID = -2519028911331180143L;
/*    */   
/*    */   public void toData(DataOutput out)
/*    */     throws IOException
/*    */   {
/* 16 */     DataSerializerExt.toData(getClass(), this, out);
/*    */   }
/*    */   
/*    */   public void fromData(DataInput in)
/*    */     throws IOException, ClassNotFoundException
/*    */   {
/* 22 */     DataSerializerExt.fromData(getClass(), this, in);
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\support\gemfire\DataSerializerAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */