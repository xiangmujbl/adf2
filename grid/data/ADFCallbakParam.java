/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.data;
/*     */ 
/*     */ import com.gemstone.gemfire.cache.EntryEvent;
/*     */ import com.gemstone.gemfire.cache.Operation;
/*     */ import com.gemstone.gemfire.internal.cache.EntryEventImpl;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ADFCallbakParam
/*     */ {
/*     */   static enum CallbackNames
/*     */   {
/*  33 */     batching("adf.import.batching"),  audit("adf.import.audit"),  userId("adf.import.userid"), 
/*  34 */     withDomain("adf.import.withDomain"),  oldValue("adf.entry.oldValue");
/*     */     
/*     */ 
/*  37 */     private CallbackNames(String name) { this.fieldName = name; }
/*     */     
/*  39 */     String fieldName = null;
/*     */   }
/*     */   
/*     */ 
/*  43 */   Map<String, Object> callbackArgs = new HashMap();
/*     */   
/*     */   public Map<String, Object> getCallbackArgs()
/*     */   {
/*  47 */     return this.callbackArgs;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static final ADFCallbakParam getADFCallbakParam(Object callbackArgs)
/*     */   {
/*  56 */     ADFCallbakParam param = new ADFCallbakParam();
/*  57 */     if (callbackArgs != null) {
/*  58 */       param.callbackArgs = ((Map)callbackArgs);
/*     */     }
/*  60 */     return param;
/*     */   }
/*     */   
/*     */   public static final ADFCallbakParam getADFCallbakParam(EntryEvent event)
/*     */   {
/*  65 */     ADFCallbakParam param = new ADFCallbakParam();
/*  66 */     Object arg = event.getCallbackArgument();
/*  67 */     if (arg != null)
/*     */     {
/*  69 */       if ((arg instanceof Map))
/*     */       {
/*  71 */         Map<String, Object> theMap = (Map)arg;
/*  72 */         if (event.getOperation().equals(Operation.PUTALL_UPDATE))
/*     */         {
/*  74 */           Map<String, Object> one = theMap;
/*  75 */           theMap = new HashMap(one);
/*     */         }
/*  77 */         param.callbackArgs = theMap;
/*     */       }
/*     */     }
/*  80 */     ((EntryEventImpl)event).setRawCallbackArgument(param.callbackArgs);
/*  81 */     return param;
/*     */   }
/*     */   
/*     */   public static final ADFCallbakParam create() {
/*  85 */     ADFCallbakParam param = new ADFCallbakParam();
/*  86 */     param.callbackArgs = new HashMap();
/*  87 */     return param;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setAudit(Boolean audit)
/*     */   {
/*  93 */     this.callbackArgs.put(CallbackNames.audit.fieldName, audit);
/*     */   }
/*     */   
/*     */   public void setAsyncIndex(Boolean flag)
/*     */   {
/*  98 */     this.callbackArgs.put(CallbackNames.batching.fieldName, flag);
/*     */   }
/*     */   
/*     */   public void setUserId(String userId)
/*     */   {
/* 103 */     this.callbackArgs.put(CallbackNames.userId.fieldName, userId);
/*     */   }
/*     */   
/*     */   public void setWithDomain(boolean bl) {
/* 107 */     this.callbackArgs.put(CallbackNames.withDomain.fieldName, Boolean.valueOf(bl));
/*     */   }
/*     */   
/*     */   public boolean temporalEnabled()
/*     */   {
/* 112 */     Boolean audit = (Boolean)this.callbackArgs.get(CallbackNames.audit.fieldName);
/* 113 */     return (audit != null) && (audit.booleanValue());
/*     */   }
/*     */   
/*     */   public boolean isAsyncIndex()
/*     */   {
/* 118 */     Boolean batching = (Boolean)this.callbackArgs.get(CallbackNames.batching.fieldName);
/* 119 */     return (batching != null) && (batching.booleanValue());
/*     */   }
/*     */   
/*     */   public String getUserId()
/*     */   {
/* 124 */     String userId = (String)this.callbackArgs.get(CallbackNames.userId.fieldName);
/* 125 */     return userId;
/*     */   }
/*     */   
/*     */   public boolean withDomain()
/*     */   {
/* 130 */     Boolean batching = (Boolean)this.callbackArgs.get(CallbackNames.withDomain.fieldName);
/* 131 */     return (batching != null) && (batching.booleanValue());
/*     */   }
/*     */   
/*     */   public void setSerializedOldValue(byte[] oldValue)
/*     */   {
/* 136 */     this.callbackArgs.put(CallbackNames.oldValue.fieldName, oldValue);
/*     */   }
/*     */   
/*     */ 
/*     */   public <V> V getOldValue()
/*     */   {
/* 142 */     byte[] bytes = (byte[])this.callbackArgs.get(CallbackNames.oldValue.fieldName);
/* 143 */     if (bytes != null)
/*     */     {
/* 145 */       V oldValue = EntryEventImpl.deserialize(bytes);
/* 146 */       return oldValue;
/*     */     }
/* 148 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\data\ADFCallbakParam.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */