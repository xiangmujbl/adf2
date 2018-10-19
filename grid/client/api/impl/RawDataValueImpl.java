/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.client.api.impl;
/*    */ 
/*    */ import com.gemstone.gemfire.pdx.PdxInstance;
/*    */ import com.gemstone.gemfire.pdx.WritablePdxInstance;
/*    */ import com.jnj.adf.client.api.remote.RawDataValue;
/*    */ import com.jnj.adf.grid.common.PdxUtils;
/*    */ import com.jnj.adf.grid.utils.JsonUtils;
/*    */ import java.util.List;
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
/*    */ public class RawDataValueImpl
/*    */   implements RawDataValue
/*    */ {
/*    */   private PdxInstance pdx;
/*    */   
/*    */   public RawDataValueImpl(PdxInstance pdx)
/*    */   {
/* 35 */     this.pdx = pdx;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean hasField(String fieldName)
/*    */   {
/* 41 */     return this.pdx.hasField(fieldName);
/*    */   }
/*    */   
/*    */ 
/*    */   public List<String> getFieldNames()
/*    */   {
/* 47 */     return this.pdx.getFieldNames();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public <V> V getField(String fieldName)
/*    */   {
/* 54 */     return (V)this.pdx.getField(fieldName);
/*    */   }
/*    */   
/*    */   public void setField(String fieldName, Object value)
/*    */   {
/* 59 */     this.pdx.createWriter().setField(fieldName, value);
/*    */   }
/*    */   
/*    */ 
/*    */   public PdxInstance toPdx()
/*    */   {
/* 65 */     return this.pdx;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public Map toMap()
/*    */   {
/* 72 */     String json = PdxUtils.toJson(this.pdx);
/* 73 */     Map mm = (Map)JsonUtils.jsonToObject(json, Map.class);
/* 74 */     return mm;
/*    */   }
/*    */   
/*    */ 
/*    */   public String toString()
/*    */   {
/* 80 */     return "RawDataValueImpl [pdx=" + this.pdx + "]";
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\client\api\impl\RawDataValueImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */