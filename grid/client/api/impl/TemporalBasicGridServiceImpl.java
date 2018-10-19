/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.client.api.impl;
/*     */ 
/*     */ import com.gemstone.gemfire.cache.Region;
/*     */ import com.gemstone.gemfire.pdx.PdxInstance;
/*     */ import com.jnj.adf.client.api.IBiz;
/*     */ import com.jnj.adf.grid.client.api.ITemporalRegionService;
/*     */ import com.jnj.adf.grid.client.api.support.ADFServiceContext;
/*     */ import com.jnj.adf.grid.data.ReservedFields;
/*     */ import com.jnj.adf.grid.data.temporal.TemporalRKey;
/*     */ import com.jnj.adf.grid.data.temporal.utils.TemporalKeyUtils;
/*     */ import com.jnj.adf.grid.data.temporal.utils.TemporalUtils;
/*     */ import com.jnj.adf.grid.utils.JsonUtils;
/*     */ import com.jnj.adf.grid.utils.LogUtil;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.apache.logging.log4j.Logger;
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
/*     */ @Component("_adf_temporal_basic")
/*     */ public class TemporalBasicGridServiceImpl
/*     */   extends BasicGridServiceImpl
/*     */ {
/*     */   ITemporalRegionService temporalRegionService()
/*     */   {
/*  34 */     return (ITemporalRegionService)IBiz.lookup(ITemporalRegionService.class);
/*     */   }
/*     */   
/*     */   private PdxInstance getRegionValue(String key, Region<?, ?> region)
/*     */   {
/*  39 */     String path = ADFServiceContext.getPath();
/*  40 */     TemporalParam.correctValidAtAndAsof();
/*  41 */     LogUtil.getCoreLog().debug("validAt:{} asOf:{} ", new Object[] { TemporalUtils.format(TemporalParam.getParam().validAt), 
/*  42 */       TemporalUtils.formatTime(TemporalParam.getParam().asOf) });
/*     */     
/*  44 */     PdxInstance pdxInstance = temporalRegionService().getAsof(path, key, TemporalParam.getParam().validAt, 
/*  45 */       TemporalParam.getParam().asOf);
/*     */     
/*     */ 
/*     */ 
/*  49 */     return pdxInstance;
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
/*     */   public Entry<String, PdxInstance> getEntry(String adfKey, Region<?, ?> region)
/*     */   {
/* 201 */     LogUtil.startTimer();
/*     */     
/*     */ 
/* 204 */     Region<TemporalRKey, PdxInstance> r = region;
/* 205 */     Map<String, Object> map = (Map)JsonUtils.jsonToObject(adfKey, Map.class);
/* 206 */     String uuid = (String)map.get(ReservedFields.TEMPORAL_UUID.fieldName());
/*     */     PdxInstance pdxInstance;
/*     */     final String identityKey;
/*     */     final PdxInstance pdxInstance;
/* 210 */     if (StringUtils.isNotEmpty(uuid))
/*     */     {
/* 212 */       String identityKey = (String)map.get("identityKey");
/* 213 */       TemporalRKey rkey = TemporalKeyUtils.createTemporalRKey(uuid, identityKey);
/*     */       
/*     */ 
/* 216 */       pdxInstance = (PdxInstance)r.get(rkey);
/*     */     }
/*     */     else
/*     */     {
/* 220 */       identityKey = adfKey;
/* 221 */       pdxInstance = getRegionValue(adfKey, r);
/*     */     }
/* 223 */     LogUtil.getCoreLog().debug("Get temporal entry of key:{} for region:{} cost:{}", new Object[] { adfKey, region == null ? null : region
/* 224 */       .getFullPath(), Long.valueOf(LogUtil.cost()) });
/* 225 */     new Entry()
/*     */     {
/*     */ 
/*     */       public String getKey()
/*     */       {
/*     */ 
/* 231 */         return identityKey;
/*     */       }
/*     */       
/*     */ 
/*     */       public PdxInstance getValue()
/*     */       {
/* 237 */         return pdxInstance;
/*     */       }
/*     */       
/*     */ 
/*     */       public PdxInstance setValue(PdxInstance value)
/*     */       {
/* 243 */         return value;
/*     */       }
/*     */     };
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\client\api\impl\TemporalBasicGridServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */