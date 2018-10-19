/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.query.impl;
/*     */ 
/*     */ import com.jnj.adf.client.api.PageResults;
/*     */ import com.jnj.adf.grid.client.api.impl.EntryImpl;
/*     */ import com.jnj.adf.grid.client.api.impl.PageResultsImpl;
/*     */ import com.jnj.adf.grid.client.api.impl.TemporalParam;
/*     */ import com.jnj.adf.grid.client.api.impl.TemporalParam.TimeType;
/*     */ import com.jnj.adf.grid.data.temporal.TemporalRKey;
/*     */ import com.jnj.adf.grid.data.temporal.utils.TemporalKeyUtils;
/*     */ import com.jnj.adf.grid.indexer.SimpleLuceneParam;
/*     */ import com.jnj.adf.grid.query.ServerPageResults;
/*     */ import com.jnj.adf.grid.utils.JsonUtils;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import org.joda.time.Interval;
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
/*     */ @Component("_adf_query_temporal_lucene")
/*     */ public class TemporalLuceneQueryService
/*     */   extends LuceneQueryService
/*     */ {
/*     */   public <T> List<T> findAll(Class<T> classType)
/*     */   {
/*  38 */     return super.findAll("", classType);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public <T> List<Entry<String, T>> findAllEntries(Class<T> classType)
/*     */   {
/*  48 */     return super.findAllEntries("", classType);
/*     */   }
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
/*     */ 
/*     */ 
/*     */   protected SimpleLuceneParam makeParam(String queryString, String orderBys)
/*     */   {
/*  75 */     SimpleLuceneParam param = super.makeParam(queryString, orderBys);
/*  76 */     param.setTemporalOp(true);
/*  77 */     TemporalParam.correctValidAtAndAsof();
/*  78 */     param.setValidAt(TemporalParam.getParam().validAt);
/*  79 */     param.setAsOf(TemporalParam.getParam().asOf);
/*     */     
/*  81 */     if (TemporalParam.getParam().validAtInterval != null)
/*     */     {
/*  83 */       param.setValidAtStart(TemporalParam.getParam().validAtInterval.getStartMillis());
/*  84 */       param.setValidAtEnd(TemporalParam.getParam().validAtInterval.getEndMillis());
/*     */     }
/*  86 */     if (TemporalParam.getParam().asOfInterval != null)
/*     */     {
/*  88 */       param.setAsOfStart(TemporalParam.getParam().asOfInterval.getStartMillis());
/*  89 */       param.setAsOfEnd(TemporalParam.getParam().asOfInterval.getEndMillis());
/*     */     }
/*  91 */     if (TemporalParam.getParam().validAtTimeType != null)
/*     */     {
/*  93 */       if (TemporalParam.getParam().validAtTimeType == TemporalParam.TimeType.point) {
/*  94 */         param.setValidAtPointType(true);
/*     */       } else {
/*  96 */         param.setValidAtPointType(false);
/*     */       }
/*     */     } else
/*  99 */       param.setValidAtPointType(true);
/* 100 */     return param;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected <T> Map<String, T> getAll(List keys, Class<T> classType)
/*     */   {
/* 112 */     List<TemporalRKey> regionKeys = new ArrayList();
/* 113 */     if (keys != null)
/*     */     {
/* 115 */       for (Object key : keys)
/*     */       {
/* 117 */         TemporalRKey rkey = TemporalKeyUtils.luceneKeyToRkey(key);
/* 118 */         if (rkey != null)
/*     */         {
/* 120 */           regionKeys.add(rkey);
/*     */         }
/*     */       }
/*     */     }
/* 124 */     return super.getAll(regionKeys, classType);
/*     */   }
/*     */   
/*     */ 
/*     */   protected <T> PageResults<T> toPageResults(ServerPageResults spr, boolean lastPage, Class<T> classType, long count)
/*     */   {
/* 130 */     if ((spr == null) || (spr.getTotalCount() == 0L))
/*     */     {
/* 132 */       PageResults<T> pr = new PageResultsImpl(0L, new ArrayList(), new ArrayList(), true);
/* 133 */       return pr;
/*     */     }
/*     */     
/* 136 */     Map<String, T> resultMap = getAll(spr.getKeys(), classType);
/* 137 */     List<T> values = new ArrayList();
/* 138 */     List<Entry<String, T>> entries = new ArrayList();
/* 139 */     for (Object objKey : spr.getKeys())
/*     */     {
/* 141 */       String key = (String)objKey;
/* 142 */       TemporalRKey rkey = TemporalKeyUtils.luceneKeyToRkey(key);
/* 143 */       T value = resultMap.get(JsonUtils.objectToJson(rkey));
/*     */       
/* 145 */       if (value == null)
/*     */       {
/* 147 */         count -= 1L;
/*     */       }
/*     */       else {
/* 150 */         values.add(value);
/* 151 */         entries.add(new EntryImpl(key, value));
/*     */       }
/*     */     }
/* 154 */     Object pr = new PageResultsImpl(count, values, entries, lastPage);
/* 155 */     return (PageResults<T>)pr;
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\query\impl\TemporalLuceneQueryService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */