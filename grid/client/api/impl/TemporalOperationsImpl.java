/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.client.api.impl;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import com.jnj.adf.client.api.GridTemporalOperations;
/*     */ import com.jnj.adf.client.api.IBiz;
/*     */ import com.jnj.adf.client.api.IRemoteService;
/*     */ import com.jnj.adf.grid.client.api.ITemporalRegionService;
/*     */ import com.jnj.adf.grid.client.api.support.ADFServiceContext;
/*     */ import com.jnj.adf.grid.data.temporal.TemporalKey;
/*     */ import com.jnj.adf.grid.data.temporal.utils.TemporalUtils;
/*     */ import com.jnj.adf.grid.utils.JsonUtils;
/*     */ import java.io.PrintStream;
/*     */ import java.time.LocalDateTime;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import org.joda.time.Interval;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class TemporalOperationsImpl
/*     */   implements GridTemporalOperations
/*     */ {
/*     */   public Map<TemporalKey, String> getHistoryDump(String key)
/*     */   {
/*  52 */     String path = ADFServiceContext.getPath();
/*  53 */     Map<TemporalKey, String> map = temporalRegionService().getHistoryDump(path, key);
/*  54 */     printHsitoryDump(key, map);
/*     */     
/*  56 */     return map;
/*     */   }
/*     */   
/*     */   ITemporalRegionService temporalRegionService()
/*     */   {
/*  61 */     return (ITemporalRegionService)IBiz.lookup(ITemporalRegionService.class);
/*     */   }
/*     */   
/*     */ 
/*     */   public <V> Map<TemporalKey, V> getHistoryDump(String key, Class<V> clsType)
/*     */   {
/*  67 */     String path = ADFServiceContext.getPath();
/*  68 */     Map<TemporalKey, String> map = temporalRegionService().getHistoryDump(path, key);
/*  69 */     Map<TemporalKey, V> objectMap = Maps.newHashMap();
/*  70 */     for (Entry<TemporalKey, String> entry : map.entrySet())
/*     */     {
/*  72 */       objectMap.put(entry.getKey(), JsonUtils.jsonToObject((String)entry.getValue(), clsType));
/*     */     }
/*  74 */     printHsitoryDump(key, map);
/*     */     
/*  76 */     return objectMap;
/*     */   }
/*     */   
/*     */   private void printHsitoryDump(String identityKey, Map<TemporalKey, String> map)
/*     */   {
/*  81 */     System.out.println();
/*  82 */     System.out.println("=====================================================");
/*  83 */     System.out.println("   IdentityKey = " + identityKey);
/*     */     
/*     */ 
/*  86 */     if ((map == null) || (map.size() == 0))
/*     */     {
/*  88 */       System.out.println("  getHistoryDump empty");
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/*     */ 
/*  94 */       System.out.println("   UUID                         StartValid     EndValidTime     WrittenTime   WrittenTimeEnd    Value");
/*     */       
/*  96 */       System.out.println("   -------                      ----------     ------------     -----------   -----------       -----");
/*     */       
/*  98 */       Iterator<Entry<TemporalKey, String>> it = map.entrySet().iterator();
/*  99 */       while (it.hasNext())
/*     */       {
/* 101 */         Entry<TemporalKey, String> curElem = (Entry)it.next();
/* 102 */         TemporalKey curKey = (TemporalKey)curElem.getKey();
/* 103 */         String startValidStr; String startValidStr; if (curKey.getValidFrom() >= TemporalUtils.MAX_TIME)
/*     */         {
/* 105 */           startValidStr = "&";
/*     */         }
/*     */         else
/*     */         {
/* 109 */           startValidStr = curKey.getValidFromStr(); }
/*     */         String endValidStr;
/* 111 */         String endValidStr; if (curKey.getValidTo() >= TemporalUtils.MAX_TIME)
/*     */         {
/* 113 */           endValidStr = "&";
/*     */         }
/*     */         else
/*     */         {
/* 117 */           endValidStr = curKey.getValidToStr(); }
/*     */         String writtenTimeEnd;
/* 119 */         String writtenTimeEnd; if (curKey.getWrittenTimeEnd() >= TemporalUtils.MAX_TIME)
/*     */         {
/* 121 */           writtenTimeEnd = "&";
/*     */         }
/*     */         else
/*     */         {
/* 125 */           writtenTimeEnd = curKey.getWrittenTimeEndStr();
/*     */         }
/* 127 */         System.out.println("   " + curKey.getUuid() + "  " + startValidStr + "  " + endValidStr + "  " + curKey
/* 128 */           .getWrittenTimeStr() + "  " + writtenTimeEnd + "  " + (String)curElem.getValue());
/*     */       }
/*     */     }
/* 131 */     System.out.println("=====================================================");
/* 132 */     System.out.println();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public GridTemporalOperations validAt(LocalDateTime validAtTime, LocalDateTime asOfTime)
/*     */   {
/* 144 */     if (validAtTime == null)
/* 145 */       validAtTime = LocalDateTime.now();
/* 146 */     TemporalParam.getParam().validAt = TemporalUtils.toLong(validAtTime, true);
/* 147 */     if (asOfTime == null)
/* 148 */       asOfTime = LocalDateTime.now();
/* 149 */     TemporalParam.getParam().asOf = TemporalUtils.toLong(asOfTime, false);
/* 150 */     TemporalParam.getParam().validAtTimeType = TemporalParam.TimeType.point;
/* 151 */     return this;
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
/*     */   public GridTemporalOperations validAt(LocalDateTime validAtStart, LocalDateTime validAtEnd, LocalDateTime asOfStart, LocalDateTime asOfEnd)
/*     */   {
/* 164 */     if (validAtStart == null)
/* 165 */       validAtStart = LocalDateTime.now();
/* 166 */     long validAtStartL = TemporalUtils.toLong(validAtStart, true);
/*     */     
/* 168 */     long validAtEndL = TemporalUtils.toMaxLong(validAtEnd, true);
/* 169 */     TemporalParam.getParam().validAtInterval = new Interval(validAtStartL, validAtEndL);
/*     */     
/* 171 */     if (asOfStart == null)
/* 172 */       asOfStart = LocalDateTime.now();
/* 173 */     long asOfStartL = TemporalUtils.toLong(asOfStart, false);
/* 174 */     long asOfEndL = TemporalUtils.toMaxLong(asOfEnd, false);
/* 175 */     TemporalParam.getParam().asOfInterval = new Interval(asOfStartL, asOfEndL);
/*     */     
/* 177 */     TemporalParam.getParam().validAtTimeType = TemporalParam.TimeType.interval;
/* 178 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean rebuildData(String path)
/*     */   {
/* 184 */     boolean result = ((ITemporalRegionService)IBiz.remote(ITemporalRegionService.class).onServers(path)).rebuildData();
/* 185 */     return result;
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\client\api\impl\TemporalOperationsImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */