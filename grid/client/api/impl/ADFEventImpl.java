/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.client.api.impl;
/*    */ 
/*    */ import com.jnj.adf.client.api.ADFEvent;
/*    */ import com.jnj.adf.grid.data.MetaService;
/*    */ import com.jnj.adf.grid.data.MetaServiceFactory;
/*    */ import com.jnj.adf.grid.utils.JsonUtils;
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
/*    */ public class ADFEventImpl
/*    */   implements ADFEvent
/*    */ {
/*    */   private String key;
/*    */   private String value;
/*    */   private MetaService meta;
/*    */   
/*    */   public ADFEventImpl(String key, String value)
/*    */   {
/* 32 */     this();
/* 33 */     this.key = key;
/* 34 */     this.value = value;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public ADFEventImpl()
/*    */   {
/* 46 */     this.meta = MetaServiceFactory.createMetaService();
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
/*    */   public <V> V getValue(Class<V> classType)
/*    */   {
/* 59 */     if (String.class.equals(classType)) {
/* 60 */       return this.value;
/*    */     }
/*    */     
/* 63 */     V v = JsonUtils.jsonToObject(this.value, classType);
/* 64 */     return v;
/*    */   }
/*    */   
/*    */ 
/*    */   public void setKey(String key)
/*    */   {
/* 70 */     this.key = key;
/*    */   }
/*    */   
/*    */   public void setValue(String value)
/*    */   {
/* 75 */     this.value = value;
/*    */   }
/*    */   
/*    */   public String getKey()
/*    */   {
/* 80 */     return this.key;
/*    */   }
/*    */   
/*    */   public String getValue()
/*    */   {
/* 85 */     return this.value;
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\client\api\impl\ADFEventImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */