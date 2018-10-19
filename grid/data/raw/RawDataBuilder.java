/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.data.raw;
/*    */ 
/*    */ import com.jnj.adf.client.api.remote.RawDataHelper;
/*    */ import com.jnj.adf.client.api.remote.RawDataValue;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
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
/*    */ 
/*    */ public class RawDataBuilder
/*    */ {
/*    */   Map<String, Object> map;
/*    */   
/*    */   public RawDataBuilder()
/*    */   {
/* 40 */     this.map = new HashMap();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public RawDataBuilder put(String key, Object value)
/*    */   {
/* 50 */     this.map.put(key, value);
/* 51 */     return this;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public RawDataBuilder put(String key, RawDataValue rdvObject, String rdvFieldName)
/*    */   {
/* 64 */     this.map.put(key, rdvObject.getField(rdvFieldName));
/* 65 */     return this;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public Object toRawData()
/*    */   {
/* 73 */     return RawDataHelper.getInstance().createRawData(this.map);
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\data\raw\RawDataBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */