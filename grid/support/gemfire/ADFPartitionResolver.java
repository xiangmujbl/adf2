/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.support.gemfire;
/*     */ 
/*     */ import com.gemstone.gemfire.cache.EntryOperation;
/*     */ import com.gemstone.gemfire.cache.PartitionResolver;
/*     */ import com.jnj.adf.grid.data.RegionDefine_v3;
/*     */ import com.jnj.adf.grid.manager.RegionHelper;
/*     */ import com.jnj.adf.grid.utils.JsonUtils;
/*     */ import com.jnj.adf.grid.utils.LogUtil;
/*     */ import java.util.Map;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ADFPartitionResolver
/*     */   implements PartitionResolver
/*     */ {
/*     */   protected String[] resolverColumns;
/*     */   
/*     */   public void close() {}
/*     */   
/*     */   public Object getRoutingObject(EntryOperation entry)
/*     */   {
/*  46 */     long l0 = System.nanoTime();
/*  47 */     Object result = entry.getKey();
/*     */     try
/*     */     {
/*  50 */       String[] columns = this.resolverColumns;
/*  51 */       if (!hasResolverColumns(columns))
/*     */       {
/*  53 */         RegionDefine_v3 def = RegionHelper.getRegionDefine(entry.getRegion());
/*  54 */         if (def.getResolverColumns() != null)
/*     */         {
/*  56 */           columns = def.getResolverColumns();
/*     */         }
/*     */       }
/*  59 */       if ((hasResolverColumns(columns)) && ((entry.getKey() instanceof String)))
/*     */       {
/*  61 */         String key = (String)entry.getKey();
/*  62 */         String resolverKey = genResolverKey(columns, key);
/*  63 */         if (resolverKey != null)
/*     */         {
/*  65 */           result = resolverKey;
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*  71 */       LogUtil.getCoreLog().error(e);
/*     */     }
/*  73 */     long l1 = System.nanoTime();
/*  74 */     LogUtil.getCoreLog().trace("Cost:{},routingObject:{}", new Object[] { Long.valueOf(l1 - l0), result });
/*  75 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */   protected boolean hasResolverColumns(String[] columns)
/*     */   {
/*  81 */     return (columns != null) && (columns.length > 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String genResolverKey(String[] columns, String key)
/*     */   {
/*  93 */     if (hasResolverColumns(columns))
/*     */     {
/*  95 */       Map m = (Map)JsonUtils.jsonToObject(key, Map.class);
/*  96 */       if (columns.length == 1)
/*     */       {
/*  98 */         Object vv = m.get(columns[0]);
/*  99 */         if (vv != null) {
/* 100 */           return vv.toString();
/*     */         }
/*     */       }
/*     */       else {
/* 104 */         StringBuilder builder = new StringBuilder();
/* 105 */         for (String col : columns)
/*     */         {
/* 107 */           Object vv = m.get(col);
/* 108 */           if (vv != null) {
/* 109 */             builder.append(vv.toString()).append(".");
/*     */           }
/*     */         }
/* 112 */         return builder.toString();
/*     */       }
/*     */     }
/* 115 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public String getName()
/*     */   {
/* 121 */     return null;
/*     */   }
/*     */   
/*     */   public String[] getResolverColumns()
/*     */   {
/* 126 */     return this.resolverColumns;
/*     */   }
/*     */   
/*     */   public void setResolverColumns(String[] resolverColumns)
/*     */   {
/* 131 */     this.resolverColumns = resolverColumns;
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\support\gemfire\ADFPartitionResolver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */