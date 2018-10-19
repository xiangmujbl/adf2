/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.query.indexer;
/*    */ 
/*    */ import com.jnj.adf.grid.common.ADFException;
/*    */ import com.jnj.adf.grid.query.indexer.pk.IPkIndexer;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GridIndexerFactory
/*    */ {
/*    */   public static enum IndexPrivoder
/*    */   {
/* 16 */     SOLR,  PK,  LUCENE;
/*    */     
/*    */     private IndexPrivoder() {} }
/* 19 */   private static Map<IndexPrivoder, IGridIndexer> gridIndexerContext = new HashMap();
/*    */   
/*    */   public static IPkIndexer getPkIndexer(String param)
/*    */   {
/* 23 */     IGridIndexer indexer = getIndexerByPrivoder(IndexPrivoder.PK);
/* 24 */     if (indexer == null)
/*    */     {
/* 26 */       throw new ADFException("Indexer of primary key not found.");
/*    */     }
/* 28 */     IPkIndexer pkIndexer = (IPkIndexer)indexer;
/* 29 */     return pkIndexer;
/*    */   }
/*    */   
/*    */   public static void registerIndexerPrivoder(IGridIndexer gridIndexer)
/*    */   {
/* 34 */     gridIndexerContext.put(gridIndexer.getIndexPrivoder(), gridIndexer);
/*    */   }
/*    */   
/*    */   private static IGridIndexer getIndexerByPrivoder(IndexPrivoder privoder)
/*    */   {
/* 39 */     return (IGridIndexer)gridIndexerContext.get(privoder);
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\query\indexer\GridIndexerFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */