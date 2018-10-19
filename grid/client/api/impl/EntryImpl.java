/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.client.api.impl;
/*    */ 
/*    */ import com.gemstone.gemfire.DataSerializable;
/*    */ import com.gemstone.gemfire.DataSerializer;
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ import java.util.Map.Entry;
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
/*    */ 
/*    */ public class EntryImpl<K, V>
/*    */   implements Map.Entry<K, V>, DataSerializable
/*    */ {
/*    */   private static final long serialVersionUID = 7462965516153427070L;
/*    */   private K key;
/*    */   private V value;
/*    */   
/*    */   public EntryImpl() {}
/*    */   
/*    */   public EntryImpl(K key, V value)
/*    */   {
/* 43 */     this.key = key;
/* 44 */     this.value = value;
/*    */   }
/*    */   
/*    */ 
/*    */   public K getKey()
/*    */   {
/* 50 */     return (K)this.key;
/*    */   }
/*    */   
/*    */   public void setKey(K key) {
/* 54 */     this.key = key;
/*    */   }
/*    */   
/*    */   public V getValue() {
/* 58 */     return (V)this.value;
/*    */   }
/*    */   
/*    */   public V setValue(V value)
/*    */   {
/* 63 */     this.value = value;
/* 64 */     return (V)this.value;
/*    */   }
/*    */   
/*    */   public void toData(DataOutput out) throws IOException
/*    */   {
/* 69 */     DataSerializer.writeObject(this.key, out);
/* 70 */     DataSerializer.writeObject(this.value, out);
/*    */   }
/*    */   
/*    */   public void fromData(DataInput in) throws IOException, ClassNotFoundException
/*    */   {
/* 75 */     this.key = DataSerializer.readObject(in);
/* 76 */     this.value = DataSerializer.readObject(in);
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\client\api\impl\EntryImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */