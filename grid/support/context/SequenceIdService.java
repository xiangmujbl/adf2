/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.support.context;
/*     */ 
/*     */ import com.gemstone.gemfire.cache.Cache;
/*     */ import com.gemstone.gemfire.cache.CacheFactory;
/*     */ import com.gemstone.gemfire.cache.Region;
/*     */ import com.gemstone.gemfire.cache.client.ClientCache;
/*     */ import com.gemstone.gemfire.cache.client.ClientCacheFactory;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ import java.util.concurrent.locks.Lock;
/*     */ import org.springframework.data.gemfire.function.annotation.GemfireFunction;
/*     */ import org.springframework.data.gemfire.function.execution.GemfireOnServerFunctionTemplate;
/*     */ import org.springframework.stereotype.Component;
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
/*     */ @Component
/*     */ public class SequenceIdService
/*     */ {
/*     */   public static final String F_getSequenceId = "adf.meta.getSequenceId";
/*     */   public static final String F_getSequenceIds = "adf.meta.getSequenceIds";
/*  39 */   public final AtomicInteger sequenceId = new AtomicInteger();
/*     */   
/*     */ 
/*     */   private static final String sequencePath = "_sequence";
/*     */   
/*     */ 
/*     */   @GemfireFunction(id="adf.meta.getSequenceId", HA=false, hasResult=true)
/*     */   public String genSequenceId(String sequence_name)
/*     */   {
/*  48 */     return getId(sequence_name);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static final String getSequenceId(String sequence_name)
/*     */   {
/*  59 */     GemfireOnServerFunctionTemplate t = new GemfireOnServerFunctionTemplate(ClientCacheFactory.getAnyInstance().getDefaultPool());
/*  60 */     String id = (String)t.executeAndExtract("adf.meta.getSequenceId", new Object[] { sequence_name });
/*  61 */     return id;
/*     */   }
/*     */   
/*     */   @GemfireFunction(id="adf.meta.getSequenceIds", HA=false, hasResult=true)
/*     */   public String[] genSequenceIds(String sequence_name, int count)
/*     */   {
/*  67 */     return getIds(sequence_name, count);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static final String[] getSequenceIds(String sequence_name, int count)
/*     */   {
/*  79 */     GemfireOnServerFunctionTemplate t = new GemfireOnServerFunctionTemplate(ClientCacheFactory.getAnyInstance().getDefaultPool());
/*  80 */     String[] ids = (String[])t.executeAndExtract("adf.meta.getSequenceId", new Object[] { sequence_name, Integer.valueOf(count) });
/*  81 */     return ids;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private String getId(String sequence_name)
/*     */   {
/*  91 */     synchronized (this.sequenceId)
/*     */     {
/*  93 */       Region sequenceRegion = CacheFactory.getAnyInstance().getRegion("_sequence");
/*  94 */       if (sequenceRegion == null)
/*     */       {
/*  96 */         return null;
/*     */       }
/*  98 */       Lock lock = sequenceRegion.getDistributedLock(sequence_name);
/*     */       
/* 100 */       lock.lock();
/*     */       try
/*     */       {
/* 103 */         Integer startId = (Integer)sequenceRegion.get(sequence_name);
/* 104 */         if (startId == null)
/*     */         {
/* 106 */           startId = Integer.valueOf(1);
/*     */         }
/* 108 */         this.sequenceId.set(startId.intValue());
/* 109 */         int nextStartId = this.sequenceId.getAndAdd(1);
/* 110 */         sequenceRegion.put(sequence_name, Integer.valueOf(nextStartId));
/*     */       }
/*     */       finally
/*     */       {
/* 114 */         lock.unlock(); }
/*     */       Integer startId;
/* 116 */       return Integer.toString(startId.intValue());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private String[] getIds(String sequence_name, int count)
/*     */   {
/* 128 */     String[] newIds = new String[count];
/* 129 */     synchronized (this.sequenceId)
/*     */     {
/* 131 */       Region sequenceRegion = CacheFactory.getAnyInstance().getRegion("_sequence");
/* 132 */       if (sequenceRegion == null)
/*     */       {
/* 134 */         return null;
/*     */       }
/* 136 */       Lock lock = sequenceRegion.getDistributedLock(sequence_name);
/*     */       
/* 138 */       lock.lock();
/*     */       try
/*     */       {
/* 141 */         Integer startId = (Integer)sequenceRegion.get(sequence_name);
/* 142 */         if (startId == null)
/*     */         {
/* 144 */           startId = Integer.valueOf(1);
/*     */         }
/* 146 */         this.sequenceId.set(startId.intValue());
/* 147 */         for (int i = 0; i < count; i++)
/*     */         {
/* 149 */           newIds[i] = Integer.toString(startId.intValue());
/* 150 */           startId = Integer.valueOf(this.sequenceId.getAndAdd(1));
/*     */         }
/* 152 */         sequenceRegion.put(sequence_name, startId);
/*     */       }
/*     */       finally
/*     */       {
/* 156 */         lock.unlock(); }
/*     */       Integer startId;
/* 158 */       return newIds;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\support\context\SequenceIdService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */