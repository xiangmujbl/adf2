/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.client.api.impl;
/*    */ 
/*    */ import com.jnj.adf.client.api.PageResults;
/*    */ import java.util.List;
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
/*    */ public class PageResultsImpl<T>
/*    */   implements PageResults<T>
/*    */ {
/*    */   private long totalCount;
/*    */   private List<T> results;
/*    */   private List<Map.Entry<String, T>> entries;
/*    */   private boolean lastPage;
/*    */   
/*    */   public PageResultsImpl(long totalCount, List<T> results, List<Map.Entry<String, T>> entries)
/*    */   {
/* 33 */     this.totalCount = totalCount;
/* 34 */     this.results = results;
/* 35 */     this.entries = entries;
/*    */   }
/*    */   
/* 38 */   public PageResultsImpl(long totalCount, List<T> results, List<Map.Entry<String, T>> entries, boolean lastPage) { this(totalCount, results, entries);
/* 39 */     this.lastPage = lastPage;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public long getTotalCount()
/*    */   {
/* 49 */     return this.totalCount;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public List<T> getResults()
/*    */   {
/* 57 */     return this.results;
/*    */   }
/*    */   
/*    */   public List<Map.Entry<String, T>> getEntries()
/*    */   {
/* 62 */     return this.entries;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean lastPage()
/*    */   {
/* 68 */     return this.lastPage;
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\client\api\impl\PageResultsImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */