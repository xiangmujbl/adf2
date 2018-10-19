/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.master.exception;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class InvalidDataException
/*    */   extends RuntimeException
/*    */ {
/*    */   public static final int REGIONPATH_INVALID_ERROR = 3001;
/*    */   public static final String INVALID_REGIONPATH_LIST_KEY = "INVALID_REGIONPATH_LIST";
/* 16 */   int error_code = 0;
/* 17 */   private Map<String, Object> params = Maps.newHashMap();
/*    */   
/*    */ 
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */ 
/*    */   public InvalidDataException(int code)
/*    */   {
/* 25 */     this.error_code = code;
/*    */   }
/*    */   
/*    */   public InvalidDataException(int code, String message) {
/* 29 */     super(message);
/* 30 */     this.error_code = code;
/*    */   }
/*    */   
/*    */   public InvalidDataException(String message)
/*    */   {
/* 35 */     super(message);
/*    */   }
/*    */   
/*    */   public int getErrorCode() {
/* 39 */     return this.error_code;
/*    */   }
/*    */   
/*    */   public Object getParam(String key) {
/* 43 */     return this.params.get(key);
/*    */   }
/*    */   
/*    */   public void putParam(String key, Object value) {
/* 47 */     this.params.put(key, value);
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\master\exception\InvalidDataException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */