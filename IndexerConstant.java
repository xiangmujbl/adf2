/*    */ package com.jnj.adf.dataservice.adfcoreignite;
/*    */ 
/*    */ import com.jnj.adf.grid.support.system.ADFConfigHelper;
/*    */ import com.jnj.adf.grid.support.system.ADFConfigHelper.ITEMS;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract interface IndexerConstant
/*    */ {
/*    */   public static final String REGION_PATH = "_region_path_";
/*    */   public static final String GRID_ID = "_grid_id_";
/*    */   public static final String SOLR_KEY = "_id_";
/*    */   public static final String DELETED_FLAG = "_deleted_";
/*    */   public static final String WRITTEN_TIME = "writtenTime";
/*    */   public static final String IDENTITY_KEY = "identityKey";
/*    */   public static final String REGION_KEY = "regionKey";
/*    */   
/*    */   public static class Lucene
/*    */   {
/*    */     public static final String FILE_REGION_SUFF = ".files";
/*    */     public static final String CHUNK_REGION_SUFF = ".chunks";
/* 45 */     public static final String R_INDEX = "/" + "_RTM".concat("/Lucene/Index");
/* 46 */     public static final String R_RESULT = "/" + "_RTM".concat("/Lucene/Result");
/*    */     
/*    */     public static final int FIRST_PAGE = 1;
/*    */     
/*    */     public static final int DEFAULT_PAGE_NO = 1;
/*    */     
/*    */     public static final int DEFAULT_PAGE_SIZE = 50;
/*    */     
/*    */     public static final String QUERY_ALL = "*:*";
/*    */     
/*    */     public static final int DEFAULT_LIMIT = 1000;
/*    */     public static final int DEFAULT_BATCH_SIZE = 500;
/*    */     public static final int DEFAULT_SAMPLE_BLOCK = 100;
/*    */     public static final int TERM_MAX_LENGTH = 10000;
/*    */     public static final String PRIMARY_KEY = "_id_";
/*    */     public static final String REGION_PATH = "_region_path_";
/*    */     public static final String FULLTEXT_SEARCH_FIELD = "_text_";
/*    */     public static final String FIELD_LOCATION = "_location_";
/*    */     public static final String FIELD_DISTANCE = "_distance_";
/*    */     public static final String FIELD_TEMPORAL = "_temporal_";
/*    */     public static final String DYNAMIC_FIELD_DEFAULT = "*";
/* 67 */     public static int PREFETCH_COUNT = ADFConfigHelper.getInteger(ITEMS.LUCENE_PREFETCH_COUNT).intValue();
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\IndexerConstant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */