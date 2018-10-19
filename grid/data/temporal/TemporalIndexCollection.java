/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.data.temporal;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import java.util.concurrent.ConcurrentHashMap;
/*    */ import java.util.concurrent.ConcurrentSkipListSet;
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
/*    */ public class TemporalIndexCollection
/*    */ {
/* 21 */   private ConcurrentHashMap<String, Object> toAddEntries = new ConcurrentHashMap();
/* 22 */   private Set<String> toDeleteKeys = new ConcurrentSkipListSet();
/*    */   
/*    */   public void addToAddEntry(String key, Object value)
/*    */   {
/* 26 */     this.toAddEntries.put(key, value);
/*    */   }
/*    */   
/*    */   public void addToDeleteKey(String key)
/*    */   {
/* 31 */     this.toDeleteKeys.add(key);
/*    */   }
/*    */   
/*    */   public void addAllToAddEntry(Map<String, Object> entries)
/*    */   {
/* 36 */     this.toAddEntries.putAll(entries);
/*    */   }
/*    */   
/*    */   public void addAllToDeleteKey(List<String> keys)
/*    */   {
/* 41 */     this.toDeleteKeys.addAll(keys);
/*    */   }
/*    */   
/*    */   public Set<String> getToDeleteKeys()
/*    */   {
/* 46 */     return this.toDeleteKeys;
/*    */   }
/*    */   
/*    */   public ConcurrentHashMap<String, Object> getToAddEntries()
/*    */   {
/* 51 */     return this.toAddEntries;
/*    */   }
/*    */   
/*    */ 
/*    */   public Collection gettoAddValues()
/*    */   {
/* 57 */     return this.toAddEntries.values();
/*    */   }
/*    */   
/*    */   public void clear()
/*    */   {
/* 62 */     this.toAddEntries.clear();
/* 63 */     this.toDeleteKeys.clear();
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\data\temporal\TemporalIndexCollection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */