/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.data.temporal.utils;
/*     */ 
/*     */ import com.gemstone.gemfire.pdx.PdxInstance;
/*     */ import com.jnj.adf.grid.data.RegionDefine_v3;
/*     */ import com.jnj.adf.grid.data.ReservedFields;
/*     */ import com.jnj.adf.grid.manager.RegionHelper;
/*     */ import org.apache.commons.lang3.StringUtils;
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
/*     */ public class TemporalHelper
/*     */ {
/*     */   public static boolean isEnableTemporal(String regionPath)
/*     */   {
/*  40 */     RegionDefine_v3 rd = RegionHelper.getRegionDefine(regionPath);
/*  41 */     return rd.isEnabledTemporal();
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
/*     */   public static long getValidFrom(String regionPath, PdxInstance valuePdx)
/*     */   {
/*  54 */     Object vf = valuePdx.getField(ReservedFields.TEMPORAL_VALID_FROM.fieldName());
/*  55 */     if (vf == null)
/*     */     {
/*  57 */       RegionDefine_v3 rd = RegionHelper.getRegionDefine(regionPath);
/*  58 */       String validFromField = rd.getTemporalVFField();
/*  59 */       if (StringUtils.isNotBlank(validFromField)) {
/*  60 */         vf = valuePdx.getField(validFromField);
/*     */       }
/*     */     }
/*     */     
/*  64 */     long validFrom = TemporalUtils.now(true);
/*  65 */     validFrom = TemporalUtils.correctDate(vf, TemporalUtils.now(true));
/*  66 */     return validFrom;
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
/*     */   public static long getValidTo(String regionPath, PdxInstance valuePdx)
/*     */   {
/*  82 */     Object vt = valuePdx.getField(ReservedFields.TEMPORAL_VALID_TO.fieldName());
/*  83 */     if (vt == null)
/*     */     {
/*  85 */       RegionDefine_v3 rd = RegionHelper.getRegionDefine(regionPath);
/*  86 */       String validToField = rd.getTemporalVTField();
/*  87 */       if (StringUtils.isNotBlank(validToField)) {
/*  88 */         vt = valuePdx.getField(validToField);
/*     */       }
/*     */     }
/*     */     
/*  92 */     long validTo = TemporalUtils.max();
/*  93 */     validTo = TemporalUtils.correctDate(vt, TemporalUtils.max());
/*  94 */     return validTo;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static long getWrittenTime(PdxInstance valuePdx)
/*     */   {
/* 106 */     if (valuePdx == null)
/* 107 */       return TemporalUtils.now();
/* 108 */     Object txnf = valuePdx.getField(ReservedFields.TEMPORAL_WRITTEN_TIME.fieldName());
/* 109 */     long writtenTime = TemporalUtils.correctDateTime(txnf, TemporalUtils.now());
/* 110 */     return writtenTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static long getWrittenTimeEnd(PdxInstance valuePdx)
/*     */   {
/* 122 */     if (valuePdx == null)
/* 123 */       return TemporalUtils.max();
/* 124 */     Object txnt = valuePdx.getField(ReservedFields.TEMPORAL_WRITTEN_TIME_END.fieldName());
/* 125 */     long writtenTimeEnd = TemporalUtils.correctDateTime(txnt, TemporalUtils.max());
/* 126 */     return writtenTimeEnd;
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\data\temporal\utils\TemporalHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */