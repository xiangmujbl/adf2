/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.client.api.impl;
/*    */ 
/*    */ import com.jnj.adf.client.api.remote.RawData;
/*    */ import com.jnj.adf.client.api.remote.RawDataValue;
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
/*    */ public class RawDataImpl
/*    */   implements RawData
/*    */ {
/*    */   private Object key;
/*    */   private RawDataValue value;
/*    */   private String path;
/*    */   
/*    */   public RawDataImpl(Object key, RawDataValue value, String path)
/*    */   {
/* 32 */     this.key = key;
/* 33 */     this.value = value;
/* 34 */     this.path = path;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public RawDataImpl() {}
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void setKey(String key)
/*    */   {
/* 50 */     this.key = key;
/*    */   }
/*    */   
/*    */   public void setValue(RawDataValue value)
/*    */   {
/* 55 */     this.value = value;
/*    */   }
/*    */   
/*    */   public void setPath(String path)
/*    */   {
/* 60 */     this.path = path;
/*    */   }
/*    */   
/*    */   public Object getKey()
/*    */   {
/* 65 */     return this.key;
/*    */   }
/*    */   
/*    */   public RawDataValue getValue()
/*    */   {
/* 70 */     return this.value;
/*    */   }
/*    */   
/*    */   public String getPath()
/*    */   {
/* 75 */     return this.path;
/*    */   }
/*    */   
/*    */ 
/*    */   public String toString()
/*    */   {
/* 81 */     return "RawDataImpl [key=" + this.key + ", value=" + this.value + ", path=" + this.path + "]";
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\client\api\impl\RawDataImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */