/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.common;
/*     */ 
/*     */ import com.gemstone.gemfire.pdx.PdxInstance;
/*     */ import com.gemstone.gemfire.pdx.internal.PdxWriterImplExt;
/*     */ import com.jnj.adf.grid.data.ReservedFields;
/*     */ import com.jnj.adf.grid.data.temporal.TemporalKey;
/*     */ import com.jnj.adf.grid.data.temporal.TemporalValue;
/*     */ import com.jnj.adf.grid.utils.LogUtil;
/*     */ import java.beans.PropertyDescriptor;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.beanutils.PropertyUtils;
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
/*     */ public class PdxUtils
/*     */ {
/*     */   public static final Map<String, Object> pdxToMapWithObject(PdxInstance pdx)
/*     */   {
/*  43 */     Map<String, Object> map = new HashMap();
/*  44 */     for (String fieldName : pdx.getFieldNames())
/*     */     {
/*  46 */       Object value = pdx.getField(fieldName);
/*  47 */       if ((value != null) && 
/*     */       
/*  49 */         (!(value instanceof Map)))
/*     */       {
/*  51 */         map.put(fieldName, pdx.getField(fieldName)); }
/*     */     }
/*  53 */     return map;
/*     */   }
/*     */   
/*     */   public static final Map<String, Object> pdxToMapWithTrim(PdxInstance pdx)
/*     */   {
/*  58 */     Map<String, Object> map = new HashMap();
/*  59 */     if (pdx == null)
/*  60 */       return map;
/*  61 */     for (String fieldName : pdx.getFieldNames())
/*     */     {
/*  63 */       Object value = pdx.getField(fieldName);
/*     */       
/*  65 */       if ((value != null) && 
/*     */       
/*  67 */         (!(value instanceof Collection)) && 
/*     */         
/*  69 */         (!(value instanceof Map)))
/*     */       {
/*  71 */         if ((value instanceof String)) {
/*  72 */           map.put(fieldName, ((String)value).trim());
/*     */         } else
/*  74 */           map.put(fieldName, value); }
/*     */     }
/*  76 */     return map;
/*     */   }
/*     */   
/*     */   public static final Map<String, Object> pdxToMap(PdxInstance pdx)
/*     */   {
/*  81 */     Map<String, Object> map = new HashMap();
/*  82 */     if (pdx == null) {
/*  83 */       return map;
/*     */     }
/*     */     
/*  86 */     for (String fieldName : pdx.getFieldNames())
/*     */     {
/*  88 */       Object value = pdx.getField(fieldName);
/*  89 */       if ((value != null) && 
/*     */       
/*  91 */         (!(value instanceof Collection)) && 
/*     */         
/*  93 */         (!(value instanceof Map)))
/*     */       {
/*  95 */         map.put(fieldName, pdx.getField(fieldName)); }
/*     */     }
/*  97 */     return map;
/*     */   }
/*     */   
/*     */   public static final Map<String, Object> temporalToMap(TemporalValue tvalue)
/*     */   {
/* 102 */     Map<String, Object> map = pdxToMap(tvalue.getOriginValue());
/* 103 */     map.put(ReservedFields.TEMPORAL_VALID_FROM.fieldName(), Long.valueOf(tvalue.getValidFrom()));
/* 104 */     map.put(ReservedFields.TEMPORAL_VALID_TO.fieldName(), Long.valueOf(tvalue.getValidTo()));
/* 105 */     map.put(ReservedFields.TEMPORAL_WRITTEN_TIME.fieldName(), Long.valueOf(tvalue.getWrittenTime()));
/* 106 */     map.put(ReservedFields.TEMPORAL_WRITTEN_TIME_END.fieldName(), Long.valueOf(tvalue.getWrittenTimeEnd()));
/* 107 */     return map;
/*     */   }
/*     */   
/*     */   public static final Map<String, Object> temporalToMap(TemporalKey key, TemporalValue tvalue)
/*     */   {
/* 112 */     Map<String, Object> map = pdxToMap(tvalue.getOriginValue());
/* 113 */     map.put(ReservedFields.TEMPORAL_VALID_FROM.fieldName(), Long.valueOf(key.getValidFrom()));
/* 114 */     map.put(ReservedFields.TEMPORAL_VALID_TO.fieldName(), Long.valueOf(key.getValidTo()));
/* 115 */     map.put(ReservedFields.TEMPORAL_WRITTEN_TIME.fieldName(), Long.valueOf(key.getWrittenTime()));
/* 116 */     map.put(ReservedFields.TEMPORAL_WRITTEN_TIME_END.fieldName(), Long.valueOf(key.getWrittenTimeEnd()));
/* 117 */     return map;
/*     */   }
/*     */   
/*     */   public static final Map<String, Object> temporalToMapWithTrim(TemporalValue tvalue)
/*     */   {
/* 122 */     Map<String, Object> map = pdxToMapWithTrim(tvalue.getOriginValue());
/* 123 */     map.put(ReservedFields.TEMPORAL_VALID_FROM.fieldName(), Long.valueOf(tvalue.getValidFrom()));
/* 124 */     map.put(ReservedFields.TEMPORAL_VALID_TO.fieldName(), Long.valueOf(tvalue.getValidTo()));
/* 125 */     map.put(ReservedFields.TEMPORAL_WRITTEN_TIME.fieldName(), Long.valueOf(tvalue.getWrittenTime()));
/* 126 */     map.put(ReservedFields.TEMPORAL_WRITTEN_TIME_END.fieldName(), Long.valueOf(tvalue.getWrittenTimeEnd()));
/* 127 */     return map;
/*     */   }
/*     */   
/*     */   public static final Map<String, Object> temporalToMapWithTrim(TemporalKey key, TemporalValue tvalue)
/*     */   {
/* 132 */     Map<String, Object> map = pdxToMapWithTrim(tvalue.getOriginValue());
/* 133 */     map.put(ReservedFields.TEMPORAL_VALID_FROM.fieldName(), Long.valueOf(key.getValidFrom()));
/* 134 */     map.put(ReservedFields.TEMPORAL_VALID_TO.fieldName(), Long.valueOf(key.getValidTo()));
/* 135 */     map.put(ReservedFields.TEMPORAL_WRITTEN_TIME.fieldName(), Long.valueOf(key.getWrittenTime()));
/* 136 */     map.put(ReservedFields.TEMPORAL_WRITTEN_TIME_END.fieldName(), Long.valueOf(key.getWrittenTimeEnd()));
/* 137 */     return map;
/*     */   }
/*     */   
/*     */   public static final String toJson(Object pdx)
/*     */   {
/* 142 */     if (pdx == null)
/* 143 */       return null;
/* 144 */     if ((pdx instanceof PdxInstance)) {
/* 145 */       return toJson((PdxInstance)pdx);
/*     */     }
/* 147 */     return pdx.toString();
/*     */   }
/*     */   
/*     */   public static final String toJson(PdxInstance pdx)
/*     */   {
/*     */     try
/*     */     {
/* 154 */       return PdxFormatter.toJSON(pdx);
/*     */ 
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 159 */       LogUtil.getCoreLog().error("Convert to Json error ,pdx:{},error{}", new Object[] { pdx, e });
/*     */     }
/* 161 */     return null;
/*     */   }
/*     */   
/*     */   public static final PdxInstance fromJson(byte[] bytes)
/*     */   {
/*     */     try
/*     */     {
/* 168 */       return PdxFormatter.fromJSON(bytes);
/*     */ 
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 173 */       LogUtil.getCoreLog().error("Convert to PdxInstance error.", e);
/*     */     }
/* 175 */     return null;
/*     */   }
/*     */   
/*     */   public static final PdxInstance fromJson(String json)
/*     */   {
/*     */     try
/*     */     {
/* 182 */       return PdxFormatter.fromJSON(json);
/*     */ 
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 187 */       LogUtil.getCoreLog().error("Convert to PdxInstance error.", e);
/*     */     }
/* 189 */     return null;
/*     */   }
/*     */   
/*     */   public static final PdxInstance getPdxValue(Object value)
/*     */   {
/* 194 */     PdxInstance pdx = null;
/* 195 */     TemporalValue tvalue = null;
/* 196 */     if ((value instanceof PdxInstance))
/*     */     {
/* 198 */       pdx = (PdxInstance)value;
/*     */     }
/* 200 */     else if ((value instanceof TemporalValue))
/*     */     {
/* 202 */       tvalue = (TemporalValue)value;
/* 203 */       pdx = tvalue.getOriginValue();
/*     */     }
/* 205 */     return pdx;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static final PdxInstance objectToPdx(Object value)
/*     */   {
/*     */     try
/*     */     {
/* 214 */       PdxWriterImplExt writer = PdxWriterImplExt.create();
/* 215 */       PropertyDescriptor[] props = PropertyUtils.getPropertyDescriptors(value);
/* 216 */       for (PropertyDescriptor prop : props)
/*     */       {
/* 218 */         if ((prop.getReadMethod() != null) && (prop.getReadMethod().isAccessible()))
/*     */         {
/* 220 */           if (prop.getPropertyType().isArray())
/*     */           {
/* 222 */             ArrayList localArrayList = new ArrayList();
/*     */ 
/*     */           }
/* 225 */           else if (prop.getPropertyType().isPrimitive())
/*     */           {
/* 227 */             writer.addField(prop.getName(), prop.getReadMethod().invoke(value, new Object[0]), prop.getPropertyType());
/*     */           }
/* 229 */           else if (!prop.getPropertyType().isAssignableFrom(Map.class)) {}
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 235 */       return writer.makePdxInstance();
/*     */ 
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 240 */       LogUtil.getCoreLog().error("value:{}", new Object[] { value, e });
/* 241 */       throw new ADFException("Value to pdx error.", e);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\common\PdxUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */