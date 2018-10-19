/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.query;
/*    */ 
/*    */ import com.gemstone.gemfire.DataSerializable;
/*    */ import com.gemstone.gemfire.DataSerializer;
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ 
/*    */ public final class SendResult implements DataSerializable
/*    */ {
/*    */   private static final long serialVersionUID = 4115852311775176502L;
/*    */   private Object data;
/*    */   
/*    */   public SendResult() {}
/*    */   
/*    */   public SendResult(Object data)
/*    */   {
/* 18 */     this.data = data;
/*    */   }
/*    */   
/*    */ 
/* 22 */   private boolean end = false;
/*    */   
/* 24 */   public Object getData() { return this.data; }
/*    */   
/*    */   public void setData(Object data) {
/* 27 */     this.data = data;
/*    */   }
/*    */   
/* 30 */   public boolean isEnd() { return this.end; }
/*    */   
/*    */   public void setEnd(boolean end) {
/* 33 */     this.end = end;
/*    */   }
/*    */   
/*    */   public void toData(DataOutput out) throws IOException
/*    */   {
/* 38 */     DataSerializer.writeObject(this.data, out);
/* 39 */     DataSerializer.writePrimitiveBoolean(this.end, out);
/*    */   }
/*    */   
/*    */   public void fromData(DataInput in) throws IOException, ClassNotFoundException
/*    */   {
/* 44 */     this.data = DataSerializer.readObject(in);
/* 45 */     this.end = DataSerializer.readPrimitiveBoolean(in);
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\query\SendResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */