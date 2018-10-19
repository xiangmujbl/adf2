/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.client.api.impl;
/*     */ 
/*     */ import com.gemstone.gemfire.cache.Region;
/*     */ import com.gemstone.gemfire.cache.query.SelectResults;
/*     */ import com.jnj.adf.client.api.ADFOperations;
/*     */ import com.jnj.adf.client.api.GridLocationOperations;
/*     */ import com.jnj.adf.client.api.GridTemporalOperations;
/*     */ import com.jnj.adf.client.api.QueueOperations;
/*     */ import com.jnj.adf.grid.client.api.support.ADFServiceContext;
/*     */ import com.jnj.adf.grid.client.api.support.ADFServiceContext.Keys;
/*     */ import com.jnj.adf.grid.data.MetaService;
/*     */ import com.jnj.adf.grid.data.MetaServiceFactory;
/*     */ import com.jnj.adf.grid.manager.RegionHelper;
/*     */ import com.jnj.adf.grid.security.column.ColumnPrivilegeHelper;
/*     */ import com.jnj.adf.grid.support.context.ADFContext;
/*     */ import com.jnj.adf.grid.utils.JsonUtils;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.springframework.data.gemfire.GemfireTemplate;
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
/*     */ public abstract class ADFOperationsImpl
/*     */   implements ADFOperations
/*     */ {
/*     */   public GridTemporalOperations biTemporal()
/*     */   {
/*  46 */     ADFServiceContext.setValue(ADFServiceContext.Keys.TEMPORAL, new TemporalParam());
/*  47 */     return (GridTemporalOperations)ADFContext.getBean(GridTemporalOperations.class);
/*     */   }
/*     */   
/*     */   public GridLocationOperations spatialOp()
/*     */   {
/*  52 */     ADFServiceContext.setValue(ADFServiceContext.Keys.LOCATION, new LocationParam());
/*  53 */     return (GridLocationOperations)ADFContext.getBean(GridLocationOperations.class);
/*     */   }
/*     */   
/*     */   public <V> boolean offer(String key, V value)
/*     */   {
/*  58 */     QueueOperations queueoop = (QueueOperations)ADFContext.getBean(QueueOperations.class);
/*  59 */     return queueoop.offer(key, value);
/*     */   }
/*     */   
/*     */   public boolean offer(String key, String jsongValue)
/*     */   {
/*     */     
/*     */     try
/*     */     {
/*  67 */       QueueOperations queueoop = (QueueOperations)ADFContext.getBean(QueueOperations.class);
/*  68 */       return queueoop.offer(key, jsongValue);
/*     */     }
/*     */     finally
/*     */     {
/*  72 */       ADFServiceContext.after();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<Object> queryOql(String query)
/*     */   {
/*  84 */     String path = ADFServiceContext.getPath();
/*  85 */     Region r = RegionHelper.getRegion(path);
/*  86 */     if (r == null)
/*  87 */       return null;
/*  88 */     GemfireTemplate t = new GemfireTemplate(r);
/*  89 */     SelectResults<Object> sr = t.query(query);
/*  90 */     List<Object> list = sr.asList();
/*  91 */     return ColumnPrivilegeHelper.checkColumn(path, list);
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
/*     */   public void registerSchema(String schema)
/*     */   {
/* 104 */     String path = ADFServiceContext.getPath();
/* 105 */     Map<String, String> map = (Map)JsonUtils.jsonToObject(schema, Map.class);
/* 106 */     MetaServiceFactory.createMetaService().registerReadSchema(path, map, map);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getSchema()
/*     */   {
/* 115 */     String path = ADFServiceContext.getPath();
/* 116 */     Map<String, String> sch = MetaServiceFactory.createMetaService().getScheam(path);
/* 117 */     if (sch != null)
/*     */     {
/* 119 */       String str = JsonUtils.objectToJson(sch);
/* 120 */       return str;
/*     */     }
/*     */     
/* 123 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\client\api\impl\ADFOperationsImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */