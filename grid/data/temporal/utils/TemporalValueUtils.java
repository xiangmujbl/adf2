/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.data.temporal.utils;
/*     */ 
/*     */ import com.gemstone.gemfire.pdx.PdxInstance;
/*     */ import com.gemstone.gemfire.pdx.internal.PdxInstanceImpl;
/*     */ import com.gemstone.gemfire.pdx.internal.PdxWriterImplExt;
/*     */ import com.jnj.adf.grid.data.RegionDefine_v3;
/*     */ import com.jnj.adf.grid.data.ReservedFields;
/*     */ import com.jnj.adf.grid.data.temporal.TemporalValue;
/*     */ import com.jnj.adf.grid.manager.RegionHelper;
/*     */ import com.jnj.adf.grid.utils.LogUtil;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TemporalValueUtils
/*     */ {
/*     */   public static TemporalValue constructTValue(String regionPath, PdxInstance valuePdx)
/*     */   {
/*  29 */     if ((regionPath == null) || (valuePdx == null))
/*  30 */       return null;
/*  31 */     TemporalValue value = new TemporalValue();
/*  32 */     value.setOriginValue(valuePdx);
/*  33 */     value.setValidFrom(TemporalHelper.getValidFrom(regionPath, valuePdx));
/*  34 */     value.setValidTo(TemporalHelper.getValidTo(regionPath, valuePdx));
/*  35 */     value.setWrittenTime(TemporalHelper.getWrittenTime(valuePdx));
/*  36 */     value.setWrittenTimeEnd(TemporalHelper.getWrittenTimeEnd(valuePdx));
/*  37 */     return value;
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
/*     */   public static final TemporalValue pdxToTValue(PdxInstance regionValue)
/*     */   {
/*  56 */     if (regionValue == null)
/*  57 */       return null;
/*  58 */     TemporalValue tvalue = new TemporalValue();
/*  59 */     tvalue.setValidFrom(((Long)regionValue.getField(ReservedFields.TEMPORAL_VALID_FROM.fieldName())).longValue());
/*  60 */     tvalue.setValidTo(((Long)regionValue.getField(ReservedFields.TEMPORAL_VALID_TO.fieldName())).longValue());
/*  61 */     tvalue.setWrittenTime(((Long)regionValue.getField(ReservedFields.TEMPORAL_WRITTEN_TIME.fieldName())).longValue());
/*  62 */     tvalue.setWrittenTimeEnd(((Long)regionValue.getField(ReservedFields.TEMPORAL_WRITTEN_TIME_END.fieldName())).longValue());
/*  63 */     tvalue.setOriginValue(regionValue);
/*  64 */     return tvalue;
/*     */   }
/*     */   
/*     */   public static final PdxInstance tvalueToPdx(String path, TemporalValue tvalue) {
/*  68 */     if (tvalue == null)
/*  69 */       return null;
/*  70 */     PdxInstance originValue = tvalue.getOriginValue();
/*  71 */     PdxWriterImplExt w = null;
/*  72 */     if (originValue == null) {
/*  73 */       w = PdxWriterImplExt.create();
/*  74 */       if (w != null) {
/*  75 */         add4TimeField(w, tvalue);
/*  76 */         return w.makePdxInstance(null, null);
/*     */       }
/*     */     } else {
/*  79 */       w = PdxWriterImplExt.create();
/*  80 */       if (w != null) {
/*  81 */         add4TimeField(w, tvalue);
/*     */         try {
/*  83 */           w.makePdxInstance(null, (PdxInstanceImpl)originValue);
/*     */         } catch (IllegalArgumentException e) {
/*  85 */           LogUtil.getCoreLog().error("TemporalValueUtil.tvalueToPdx method get exception, e:{},data:{},originValue:{},fieldNames:{}", new Object[] { e, tvalue, ((PdxInstanceImpl)originValue)
/*     */           
/*  87 */             .getPdxType(), ((PdxInstanceImpl)originValue)
/*  88 */             .getFieldNames() });
/*     */         }
/*  90 */         return w.getPdxInstance();
/*     */       }
/*     */     }
/*     */     
/*  94 */     return null;
/*     */   }
/*     */   
/*     */   private static void add4TimeField(PdxWriterImplExt w, TemporalValue tvalue)
/*     */   {
/*  99 */     w.addField(ReservedFields.TEMPORAL_VALID_FROM, tvalue.getValidFrom());
/* 100 */     w.addField(ReservedFields.TEMPORAL_VALID_TO, tvalue.getValidTo());
/* 101 */     w.addField(ReservedFields.TEMPORAL_WRITTEN_TIME, tvalue.getWrittenTime());
/* 102 */     w.addField(ReservedFields.TEMPORAL_WRITTEN_TIME_END, tvalue.getWrittenTimeEnd());
/*     */   }
/*     */   
/*     */   public static boolean checkValueEquals(String regionPath, PdxInstance originValue, PdxInstance regionValue) {
/* 106 */     if ((originValue == null) && (regionValue == null))
/* 107 */       return true;
/* 108 */     if (originValue != null) {
/* 109 */       if (regionValue == null)
/* 110 */         return false;
/* 111 */       PdxInstanceImpl ov = (PdxInstanceImpl)originValue;
/* 112 */       PdxInstanceImpl rv = (PdxInstanceImpl)regionValue;
/* 113 */       List<String> fieldNames = decideCompareFields(regionPath, ov);
/* 114 */       for (String fieldName : fieldNames)
/*     */       {
/* 116 */         if ((!StringUtils.equals(ReservedFields.TEMPORAL_WRITTEN_TIME.fieldName(), fieldName)) && 
/* 117 */           (!StringUtils.equals(ReservedFields.TEMPORAL_WRITTEN_TIME_END.fieldName(), fieldName)) && 
/* 118 */           (!StringUtils.equals(ReservedFields.TEMPORAL_VALID_FROM.fieldName(), fieldName)) && 
/* 119 */           (!StringUtils.equals(ReservedFields.TEMPORAL_VALID_TO.fieldName(), fieldName)))
/*     */         {
/* 121 */           boolean isEqual = compareOneField(fieldName, ov, rv);
/* 122 */           if (!isEqual)
/* 123 */             return false;
/*     */         } }
/* 125 */       return true;
/*     */     }
/* 127 */     return false;
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
/*     */   private static List<String> decideCompareFields(String regionPath, PdxInstanceImpl originValue)
/*     */   {
/* 140 */     RegionDefine_v3 rd = RegionHelper.getRegionDefine(regionPath);
/* 141 */     String[] temporalAttributes = rd.getTemporalAttributes();
/* 142 */     if ((temporalAttributes != null) && (temporalAttributes.length > 0)) {
/* 143 */       LogUtil.getCoreLog().debug("Get temporal attributes:{} of region:{} , use them to check value equality.", new Object[] { temporalAttributes, regionPath });
/*     */       
/* 145 */       return Arrays.asList(temporalAttributes);
/*     */     }
/* 147 */     LogUtil.getCoreLog().debug("Can't find temporal attributes of region:{}, use value's all fields to check value equality.", new Object[] { regionPath });
/*     */     
/*     */ 
/* 150 */     return originValue.getFieldNames();
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
/*     */   private static boolean compareOneField(String fieldName, PdxInstanceImpl originValue, PdxInstanceImpl regionValue)
/*     */   {
/* 163 */     Object ofv = originValue.getRawField(fieldName);
/* 164 */     Object cfv = regionValue.getRawField(fieldName);
/* 165 */     if ((ofv == null) && (cfv == null))
/* 166 */       return true;
/* 167 */     if (ofv != null) {
/* 168 */       return ofv.equals(cfv);
/*     */     }
/* 170 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static PdxInstance processNoOriginFiled(PdxInstance pdx)
/*     */   {
/* 181 */     List<String> fieldNames = pdx.getFieldNames();
/* 182 */     for (String fieldName : fieldNames) {
/* 183 */       if (!ReservedFields.getNames().contains(fieldName))
/* 184 */         return pdx;
/*     */     }
/* 186 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean isTemporalValue(PdxInstance pdx)
/*     */   {
/* 195 */     PdxInstanceImpl regionValue = (PdxInstanceImpl)pdx;
/* 196 */     List<String> fields = regionValue.getFieldNames();
/*     */     
/*     */ 
/*     */ 
/* 200 */     boolean containTFields = (fields.contains(ReservedFields.TEMPORAL_VALID_FROM.fieldName())) && (fields.contains(ReservedFields.TEMPORAL_VALID_TO.fieldName())) && (fields.contains(ReservedFields.TEMPORAL_WRITTEN_TIME.fieldName())) && (fields.contains(ReservedFields.TEMPORAL_WRITTEN_TIME_END.fieldName()));
/*     */     
/*     */ 
/*     */ 
/* 204 */     boolean hasValue = (regionValue.getField(ReservedFields.TEMPORAL_VALID_FROM.fieldName()) != null) && (regionValue.getField(ReservedFields.TEMPORAL_VALID_TO.fieldName()) != null) && (regionValue.getField(ReservedFields.TEMPORAL_WRITTEN_TIME.fieldName()) != null) && (regionValue.getField(ReservedFields.TEMPORAL_WRITTEN_TIME_END.fieldName()) != null);
/* 205 */     return (containTFields) && (hasValue);
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\data\temporal\utils\TemporalValueUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */