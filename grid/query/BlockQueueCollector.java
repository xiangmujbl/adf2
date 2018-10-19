/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.query;
/*     */ 
/*     */ import com.gemstone.gemfire.cache.execute.FunctionException;
/*     */ import com.gemstone.gemfire.cache.execute.ResultCollector;
/*     */ import com.gemstone.gemfire.distributed.DistributedMember;
/*     */ import com.gemstone.gemfire.pdx.PdxInstance;
/*     */ import com.jnj.adf.grid.client.api.impl.EntryImpl;
/*     */ import com.jnj.adf.grid.common.ADFException;
/*     */ import com.jnj.adf.grid.common.PdxUtils;
/*     */ import com.jnj.adf.grid.data.MetaService;
/*     */ import com.jnj.adf.grid.data.MetaServiceFactory;
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.BlockingQueue;
/*     */ import java.util.concurrent.LinkedBlockingQueue;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ 
/*     */ public class BlockQueueCollector<T>
/*     */   implements ResultCollector<Object, BlockingQueue<SendResult>>
/*     */ {
/*     */   private final BlockingQueue entryQueue;
/*     */   private final BlockingQueue valueQueue;
/*     */   private final Class<T> classType;
/*     */   private final AtomicBoolean valueOnly;
/*     */   protected MetaService meta;
/*     */   private String path;
/*     */   
/*     */   public BlockQueueCollector(String path, Class<T> classType, boolean valueOnly)
/*     */   {
/*  34 */     this.entryQueue = new LinkedBlockingQueue();
/*  35 */     this.valueQueue = new LinkedBlockingQueue();
/*  36 */     this.classType = classType;
/*  37 */     this.valueOnly = new AtomicBoolean(valueOnly);
/*  38 */     this.meta = MetaServiceFactory.createMetaService();
/*  39 */     this.path = path;
/*     */   }
/*     */   
/*     */   public BlockingQueue<SendResult> getResult() throws FunctionException
/*     */   {
/*  44 */     if (this.valueOnly.get()) {
/*  45 */       return this.valueQueue;
/*     */     }
/*  47 */     return this.entryQueue;
/*     */   }
/*     */   
/*     */   public BlockingQueue<SendResult> getResult(long paramLong, TimeUnit paramTimeUnit) throws FunctionException, InterruptedException
/*     */   {
/*  52 */     return getResult();
/*     */   }
/*     */   
/*     */   public void addResult(DistributedMember memberID, Object result)
/*     */   {
/*  57 */     if ((result instanceof Throwable))
/*  58 */       throw new ADFException((Throwable)result);
/*     */     Map<Serializable, PdxInstance> entryMap;
/*  60 */     Iterator<Serializable> it; if (result != null)
/*     */     {
/*  62 */       SendResult sr = (SendResult)result;
/*  63 */       entryMap = (Map)sr.getData();
/*  64 */       for (it = entryMap.keySet().iterator(); it.hasNext();)
/*     */       {
/*  66 */         Serializable key = (Serializable)it.next();
/*  67 */         PdxInstance value = (PdxInstance)entryMap.get(key);
/*  68 */         addEntry(key, value);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void endResults()
/*     */   {
/*  76 */     SendResult sr = new SendResult();
/*  77 */     sr.setEnd(true);
/*     */     try {
/*  79 */       this.entryQueue.put(sr);
/*  80 */       this.valueQueue.put(sr);
/*     */     } catch (InterruptedException e) {
/*  82 */       Thread.currentThread().interrupt();
/*  83 */       throw new ADFException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   public void clearResults()
/*     */   {
/*  89 */     this.entryQueue.clear();
/*  90 */     this.valueQueue.clear();
/*     */   }
/*     */   
/*     */   private void addEntry(Serializable key, PdxInstance pdxInstance)
/*     */   {
/*  95 */     Object value = null;
/*  96 */     if (pdxInstance != null)
/*     */     {
/*  98 */       if (String.class.equals(this.classType)) {
/*  99 */         value = PdxUtils.toJson(pdxInstance);
/*     */       } else {
/* 101 */         value = this.meta.applyReadSchema(this.path, pdxInstance, this.classType);
/*     */       }
/*     */     }
/* 104 */     if (value != null)
/*     */     {
/* 106 */       Entry adfEntry = new EntryImpl(key, value);
/*     */       try {
/* 108 */         this.entryQueue.put(adfEntry);
/* 109 */         this.valueQueue.put(value);
/*     */       } catch (InterruptedException e) {
/* 111 */         Thread.currentThread().interrupt();
/* 112 */         throw new ADFException(e);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\query\BlockQueueCollector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */