/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.query;
/*     */ 
/*     */ import com.gemstone.gemfire.cache.execute.FunctionException;
/*     */ import com.gemstone.gemfire.cache.execute.ResultCollector;
/*     */ import com.gemstone.gemfire.distributed.DistributedMember;
/*     */ import com.gemstone.gemfire.pdx.PdxInstance;
/*     */ import com.jnj.adf.grid.client.api.impl.EntryImpl;
/*     */ import com.jnj.adf.grid.common.PdxUtils;
/*     */ import com.jnj.adf.grid.data.MetaService;
/*     */ import com.jnj.adf.grid.data.MetaServiceFactory;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ 
/*     */ public class QueryResultCollector<T>
/*     */   implements ResultCollector
/*     */ {
/*  24 */   private final List entryList = new ArrayList();
/*  25 */   private final List valueList = new ArrayList();
/*     */   private final Class<T> classType;
/*     */   private final AtomicBoolean valueOnly;
/*     */   protected MetaService meta;
/*     */   private String path;
/*     */   
/*     */   public QueryResultCollector(Class<T> classType, boolean valueOnly) {
/*  32 */     this.classType = classType;
/*  33 */     this.valueOnly = new AtomicBoolean(valueOnly);
/*  34 */     this.meta = MetaServiceFactory.createMetaService();
/*     */   }
/*     */   
/*     */   public List getResult() throws FunctionException
/*     */   {
/*  39 */     if (this.valueOnly.get()) {
/*  40 */       return this.valueList;
/*     */     }
/*  42 */     return this.entryList;
/*     */   }
/*     */   
/*     */   public List getResult(long paramLong, TimeUnit paramTimeUnit) throws FunctionException, InterruptedException
/*     */   {
/*  47 */     if (this.valueOnly.get()) {
/*  48 */       return this.valueList;
/*     */     }
/*  50 */     return this.entryList;
/*     */   }
/*     */   
/*     */ 
/*     */   public void addResult(DistributedMember paramDistributedMember, Object resultOfSingleExecution)
/*     */   {
/*  56 */     if (resultOfSingleExecution != null) { Map<Serializable, PdxInstance> regionEntry;
/*     */       Iterator<Serializable> it;
/*  58 */       Serializable key; PdxInstance pdxInstance; if ((resultOfSingleExecution instanceof Map))
/*     */       {
/*  60 */         regionEntry = (Map)resultOfSingleExecution;
/*  61 */         for (it = regionEntry.keySet().iterator(); it.hasNext();)
/*     */         {
/*  63 */           key = (Serializable)it.next();
/*  64 */           pdxInstance = (PdxInstance)regionEntry.get(key);
/*  65 */           addEntry(key, pdxInstance);
/*     */         }
/*     */       }
/*  68 */       else if ((resultOfSingleExecution instanceof Object[]))
/*     */       {
/*  70 */         Object[] entries = (Object[])resultOfSingleExecution;
/*  71 */         it = entries;key = it.length; for (pdxInstance = 0; pdxInstance < key; pdxInstance++) { Object obj = it[pdxInstance];
/*     */           
/*  73 */           Entry<Serializable, PdxInstance> entry = (Entry)obj;
/*  74 */           Serializable key = (Serializable)entry.getKey();
/*  75 */           PdxInstance pdxInstance = (PdxInstance)entry.getValue();
/*  76 */           addEntry(key, pdxInstance);
/*     */         }
/*     */       }
/*     */       else {
/*  80 */         throw new IllegalStateException("Results: " + resultOfSingleExecution.toString() + " received from member " + paramDistributedMember.getName() + " is not valid.");
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void endResults() {}
/*     */   
/*     */ 
/*     */   public void clearResults()
/*     */   {
/*  91 */     this.entryList.clear();
/*  92 */     this.valueList.clear();
/*     */   }
/*     */   
/*     */ 
/*     */   private void addEntry(Serializable key, PdxInstance pdxInstance)
/*     */   {
/*  98 */     Object value = null;
/*  99 */     if (pdxInstance != null)
/*     */     {
/* 101 */       if (String.class.equals(this.classType)) {
/* 102 */         value = PdxUtils.toJson(pdxInstance);
/*     */       }
/*     */       else {
/* 105 */         value = this.meta.applyReadSchema(this.path, pdxInstance, this.classType);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 110 */     if (value != null)
/*     */     {
/* 112 */       Entry adfEntry = new EntryImpl(key, value);
/* 113 */       this.entryList.add(adfEntry);
/* 114 */       this.valueList.add(adfEntry.getValue());
/*     */     }
/*     */   }
/*     */   
/*     */   public String getPath()
/*     */   {
/* 120 */     return this.path;
/*     */   }
/*     */   
/*     */   public void setPath(String path)
/*     */   {
/* 125 */     this.path = path;
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\query\QueryResultCollector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */