/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.events;
/*     */ 
/*     */ import com.gemstone.gemfire.cache.EntryEvent;
/*     */ import com.gemstone.gemfire.cache.Operation;
/*     */ import com.gemstone.gemfire.cache.Region;
/*     */ import com.gemstone.gemfire.cache.SerializedCacheValue;
/*     */ import com.gemstone.gemfire.cache.TransactionId;
/*     */ import com.gemstone.gemfire.distributed.DistributedMember;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SimpleEntryEvent<K, V>
/*     */   implements EntryEvent<K, V>
/*     */ {
/*     */   private Region<K, V> r;
/*     */   private Operation op;
/*     */   private Object arg;
/*     */   private K key;
/*     */   private V oldValue;
/*     */   private SerializedCacheValue<V> soldValue;
/*     */   private V newValue;
/*     */   private SerializedCacheValue<V> snewValue;
/*     */   
/*     */   public SimpleEntryEvent(EntryEvent<K, V> src)
/*     */   {
/*  31 */     this.r = src.getRegion();
/*  32 */     this.op = src.getOperation();
/*  33 */     this.arg = src.getCallbackArgument();
/*  34 */     this.key = src.getKey();
/*  35 */     this.oldValue = src.getOldValue();
/*  36 */     this.soldValue = src.getSerializedOldValue();
/*  37 */     this.newValue = src.getNewValue();
/*  38 */     this.snewValue = src.getSerializedNewValue();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SimpleEntryEvent(Region<K, V> r, Operation op, Object arg, K key, V oldValue, SerializedCacheValue<V> soldValue, V newValue, SerializedCacheValue<V> snewValue)
/*     */   {
/*  47 */     this.r = r;
/*  48 */     this.op = op;
/*  49 */     this.arg = arg;
/*  50 */     this.key = key;
/*  51 */     this.oldValue = oldValue;
/*  52 */     this.soldValue = soldValue;
/*  53 */     this.newValue = newValue;
/*  54 */     this.snewValue = snewValue;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Region<K, V> getRegion()
/*     */   {
/*  62 */     return this.r;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Operation getOperation()
/*     */   {
/*  69 */     return this.op;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object getCallbackArgument()
/*     */   {
/*  77 */     return this.arg;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isCallbackArgumentAvailable()
/*     */   {
/*  83 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isOriginRemote()
/*     */   {
/*  89 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public DistributedMember getDistributedMember()
/*     */   {
/*  95 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isExpiration()
/*     */   {
/* 101 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isDistributed()
/*     */   {
/* 107 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public K getKey()
/*     */   {
/* 114 */     return (K)this.key;
/*     */   }
/*     */   
/*     */   public void setKey(K k)
/*     */   {
/* 119 */     this.key = k;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public V getOldValue()
/*     */   {
/* 126 */     return (V)this.oldValue;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public SerializedCacheValue<V> getSerializedOldValue()
/*     */   {
/* 133 */     return this.soldValue;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public V getNewValue()
/*     */   {
/* 140 */     return (V)this.newValue;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public SerializedCacheValue<V> getSerializedNewValue()
/*     */   {
/* 148 */     return this.snewValue;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isLocalLoad()
/*     */   {
/* 154 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isNetLoad()
/*     */   {
/* 160 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isLoad()
/*     */   {
/* 166 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isNetSearch()
/*     */   {
/* 172 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public TransactionId getTransactionId()
/*     */   {
/* 178 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isBridgeEvent()
/*     */   {
/* 184 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean hasClientOrigin()
/*     */   {
/* 190 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isOldValueAvailable()
/*     */   {
/* 196 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 204 */     return "AsyncEvent [r=" + this.r + ", op=" + this.op + ", arg=" + this.arg + ", key=" + this.key + ", oldValue=" + this.oldValue + ", soldValue=" + this.soldValue + ", newValue=" + this.newValue + ", snewValue=" + this.snewValue + "]";
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\events\SimpleEntryEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */