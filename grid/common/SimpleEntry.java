/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.common;
/*    */ 
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
/*    */ public class SimpleEntry<K, V>
/*    */   implements Map.Entry<K, V>
/*    */ {
/*    */   K k;
/*    */   V v;
/*    */   
/*    */   public SimpleEntry(K k, V v)
/*    */   {
/* 30 */     this.k = k;
/* 31 */     this.v = v;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public SimpleEntry() {}
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public K getKey()
/*    */   {
/* 47 */     return (K)this.k;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public V getValue()
/*    */   {
/* 56 */     return (V)this.v;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public V setValue(V value)
/*    */   {
/* 65 */     this.v = value;
/* 66 */     return (V)this.v;
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\common\SimpleEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */