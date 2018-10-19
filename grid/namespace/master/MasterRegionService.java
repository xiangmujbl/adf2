/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.namespace.master;
/*    */ 
/*    */ import com.gemstone.gemfire.cache.Cache;
/*    */ import com.gemstone.gemfire.cache.CacheFactory;
/*    */ import com.gemstone.gemfire.cache.Region;
/*    */ import com.gemstone.gemfire.cache.RegionService;
/*    */ import com.gemstone.gemfire.cache.query.FunctionDomainException;
/*    */ import com.gemstone.gemfire.cache.query.Query;
/*    */ import com.gemstone.gemfire.cache.query.QueryException;
/*    */ import com.gemstone.gemfire.cache.query.QueryInvocationTargetException;
/*    */ import com.gemstone.gemfire.cache.query.QueryService;
/*    */ import com.gemstone.gemfire.cache.query.SelectResults;
/*    */ import com.gemstone.gemfire.cache.query.TypeMismatchException;
/*    */ import com.jnj.adf.config.annotations.RemoteMethod;
/*    */ import com.jnj.adf.config.annotations.RemoteService;
/*    */ import com.jnj.adf.grid.utils.LogUtil;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ @RemoteService("adf.master.region.service")
/*    */ public class MasterRegionService
/*    */ {
/*    */   public static final String ID = "adf.master.region.service";
/*    */   
/*    */   @RemoteMethod
/*    */   public <K, V> void put(String name, K k, V v)
/*    */   {
/* 27 */     Region r = CacheFactory.getAnyInstance().getRegion(name);
/* 28 */     r.put(k, v);
/*    */   }
/*    */   
/*    */   @RemoteMethod
/*    */   public <K, V> V get(String name, K k)
/*    */   {
/* 34 */     Region r = CacheFactory.getAnyInstance().getRegion(name);
/* 35 */     return (V)r.get(k);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   @RemoteMethod
/*    */   public <V> java.util.List<V> oql(String name, String oql, Object[] args)
/*    */   {
/* 48 */     Region r = CacheFactory.getAnyInstance().getRegion(name);
/* 49 */     Query query = r.getRegionService().getQueryService().newQuery(oql);
/*    */     try
/*    */     {
/* 52 */       SelectResults<V> sr = (SelectResults)query.execute(args);
/* 53 */       if (sr != null)
/*    */       {
/* 55 */         return sr.asList();
/*    */       }
/*    */       
/*    */     }
/*    */     catch (FunctionDomainException|TypeMismatchException|com.gemstone.gemfire.cache.query.NameResolutionException|QueryInvocationTargetException e)
/*    */     {
/* 61 */       LogUtil.getCoreLog().error("Error when executing oql on server " + oql, e);
/*    */     }
/* 63 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\namespace\master\MasterRegionService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */