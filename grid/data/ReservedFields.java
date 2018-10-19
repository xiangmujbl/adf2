/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.data;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
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
/*    */ public enum ReservedFields
/*    */ {
/* 27 */   TEMPORAL_VALID_FROM("_TVF_"),  TEMPORAL_VALID_TO("_TVT_"),  TEMPORAL_WRITTEN_TIME("_TXNF_"), 
/* 28 */   TEMPORAL_WRITTEN_TIME_END("_TXNT_"),  INSERT_USER_NAME("_INSUN_"),  UPDATE_USER_NAME("_UPUN_"),  DELETED_FLAG("_DELETED_"), 
/* 29 */   LOCATION_LATITUDE("_LOCATION_LATITUDE_"),  LOCATION_LONGITUDE("_LOCATION_LONGITUDE_"), 
/* 30 */   UPDATE_TIME("_UPT_"),  INSERT_TIME("_INST_"),  TEMPORAL_UUID("_TUUID_"),  TEMPORAL_ORIGIN_VALUE("_ORIGIN_VALUE_");
/*    */   
/*    */   private final String fieldName;
/*    */   private static final Set<String> names;
/*    */   
/*    */   static {
/* 36 */     ReservedFields[] values = values();
/* 37 */     names = new HashSet();
/* 38 */     for (ReservedFields val : values)
/*    */     {
/* 40 */       names.add(val.fieldName);
/*    */     }
/*    */   }
/*    */   
/*    */   public static Set<String> getNames()
/*    */   {
/* 46 */     return names;
/*    */   }
/*    */   
/*    */   public static void main(String[] args)
/*    */   {
/* 51 */     System.out.println(names);
/*    */   }
/*    */   
/*    */   public static final boolean hasField(String name)
/*    */   {
/* 56 */     return names.contains(name);
/*    */   }
/*    */   
/*    */   private ReservedFields(String name)
/*    */   {
/* 61 */     this.fieldName = name;
/*    */   }
/*    */   
/*    */   public String fieldName()
/*    */   {
/* 66 */     return this.fieldName;
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\data\ReservedFields.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */