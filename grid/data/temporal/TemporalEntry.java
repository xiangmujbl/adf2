/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.data.temporal;
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
/*    */ public class TemporalEntry
/*    */   implements Map.Entry<TemporalRKey, TemporalValue>
/*    */ {
/*    */   private TemporalRKey key;
/*    */   private TemporalValue value;
/*    */   
/*    */   public TemporalEntry(TemporalRKey key, TemporalValue value)
/*    */   {
/* 30 */     this.key = key;
/* 31 */     this.value = value;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public TemporalEntry() {}
/*    */   
/*    */ 
/*    */ 
/*    */   public TemporalRKey getKey()
/*    */   {
/* 43 */     return this.key;
/*    */   }
/*    */   
/*    */   public void setKey(TemporalRKey key) {
/* 47 */     this.key = key;
/*    */   }
/*    */   
/*    */   public TemporalValue getValue() {
/* 51 */     return this.value;
/*    */   }
/*    */   
/*    */   public TemporalValue setValue(TemporalValue value) {
/* 55 */     this.value = value;
/* 56 */     return value;
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\data\temporal\TemporalEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */