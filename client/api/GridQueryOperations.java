/*     */ package com.jnj.adf.dataservice.adfcoreignite.client.api;
/*     */ 
/*     */ import com.jnj.adf.grid.client.api.support.ADFServiceContext;
/*     */ import com.jnj.adf.grid.client.api.support.ADFServiceContext.Keys;
/*     */ import com.jnj.adf.grid.support.context.ADFSession;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map.Entry;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract interface GridQueryOperations
/*     */ {
/*     */   public GridQueryOperations useLimit(int limit)
/*     */   {
/*  47 */     ADFServiceContext.setValue(ADFServiceContext.Keys.LIMIT, Integer.valueOf(limit));
/*  48 */     return this;
/*     */   }
/*     */   
/*     */   public GridQueryOperations useLimit(boolean flag)
/*     */   {
/*  53 */     if (!flag) {
/*  54 */       ADFServiceContext.setValue(ADFServiceContext.Keys.LIMIT, Integer.valueOf(Integer.MAX_VALUE));
/*     */     } else
/*  56 */       ADFServiceContext.setValue(ADFServiceContext.Keys.LIMIT, Integer.valueOf(1000));
/*  57 */     return this;
/*     */   }
/*     */   
/*     */   public GridQueryOperations useBatch(int size)
/*     */   {
/*  62 */     ADFServiceContext.setValue(ADFServiceContext.Keys.BATCH_SIZE, Integer.valueOf(size));
/*  63 */     return this;
/*     */   }
/*     */   
/*     */   public GridQueryOperations withoutCache(boolean bl)
/*     */   {
/*  68 */     ADFServiceContext.setValue(ADFServiceContext.Keys.QUERY_WITHOUT_CACHE, Boolean.valueOf(bl));
/*  69 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @ADFSession
/*     */   public abstract long count();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @ADFSession
/*     */   public abstract long count(String paramString1, String paramString2);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   @ADFSession
/*     */   public List<String> findAll()
/*     */   {
/*  92 */     return findAll(String.class);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @ADFSession
/*     */   public List<String> findAll(String orderBys)
/*     */   {
/* 103 */     return findAll(orderBys, String.class);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @ADFSession
/*     */   public abstract <T> List<T> findAll(Class<T> paramClass);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @ADFSession
/*     */   public abstract <T> List<T> findAll(String paramString, Class<T> paramClass);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @ADFSession
/*     */   public List<Map.Entry<String, String>> findAllEntries()
/*     */   {
/* 136 */     return findAllEntries(String.class);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @ADFSession
/*     */   public List<Map.Entry<String, String>> findAllEntries(String orderBys)
/*     */   {
/* 147 */     return findAllEntries(orderBys, String.class);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @ADFSession
/*     */   public abstract <T> List<Map.Entry<String, T>> findAllEntries(Class<T> paramClass);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @ADFSession
/*     */   public abstract <T> List<Map.Entry<String, T>> findAllEntries(String paramString, Class<T> paramClass);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @ADFSession
/*     */   public List<String> queryForList(String queryString)
/*     */   {
/* 180 */     return queryForList(queryString, String.class);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @ADFSession
/*     */   public <T> List<T> queryForList(String queryString, Class<T> classType)
/*     */   {
/* 192 */     return queryForList(queryString, null, classType);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @ADFSession
/*     */   public List<String> queryForList(String queryString, String orderBys)
/*     */   {
/* 204 */     return queryForList(queryString, orderBys, String.class);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @ADFSession
/*     */   public abstract <T> List<T> queryForList(String paramString1, String paramString2, Class<T> paramClass);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @ADFSession
/*     */   public List<Map.Entry<String, String>> queryForEntries(String queryString)
/*     */   {
/* 224 */     return queryForEntries(queryString, String.class);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @ADFSession
/*     */   public <T> List<Map.Entry<String, T>> queryForEntries(String queryString, Class<T> classType)
/*     */   {
/* 236 */     return queryForEntries(queryString, null, classType);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @ADFSession
/*     */   public List<Map.Entry<String, String>> queryForEntries(String queryString, String orderBys)
/*     */   {
/* 247 */     return queryForEntries(queryString, orderBys, String.class);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @ADFSession
/*     */   public abstract <T> List<Map.Entry<String, T>> queryForEntries(String paramString1, String paramString2, Class<T> paramClass);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @ADFSession
/*     */   public String queryForObject(String queryString)
/*     */   {
/* 268 */     return (String)queryForObject(queryString, String.class);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @ADFSession
/*     */   public abstract <T> T queryForObject(String paramString, Class<T> paramClass);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @ADFSession
/*     */   public Map.Entry<String, String> queryForEntry(String queryString)
/*     */   {
/* 291 */     return queryForEntry(queryString, String.class);
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
/*     */   @ADFSession
/*     */   public abstract <T> Map.Entry<String, T> queryForEntry(String paramString, Class<T> paramClass);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @ADFSession
/*     */   public PageResults<String> queryPage(String query)
/*     */   {
/* 315 */     return queryPage(query, 1, 50);
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
/*     */   @ADFSession
/*     */   public PageResults<String> queryPage(String query, int pageNo)
/*     */   {
/* 329 */     return queryPage(query, pageNo, 50);
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
/*     */   @ADFSession
/*     */   public PageResults<String> queryPage(String query, int pageNo, int pageSize)
/*     */   {
/* 343 */     return queryPage(query, pageNo, pageSize, String.class);
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
/*     */   @ADFSession
/*     */   public <T> PageResults<T> queryPage(String query, int pageNo, int pageSize, Class<T> classType)
/*     */   {
/* 358 */     return queryPage(query, null, pageNo, pageSize, classType);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @ADFSession
/*     */   public PageResults<String> queryPage(String query, String orderBys, int pageNo, int pageSize)
/*     */   {
/* 369 */     return queryPage(query, orderBys, pageNo, pageSize, String.class);
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
/*     */   @ADFSession
/*     */   public PageResults<String> queryPage(String query, String orderBy, boolean orderAscending, int pageNo, int pageSize)
/*     */   {
/* 386 */     return queryPage(query, orderBy, orderAscending, pageNo, pageSize, String.class);
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
/*     */   @ADFSession
/*     */   public <T> PageResults<T> queryPage(String query, String orderBy, boolean orderAscending, int pageNo, int pageSize, Class<T> classType)
/*     */   {
/* 403 */     String orderBys = null;
/* 404 */     if (StringUtils.isNotEmpty(orderBy))
/* 405 */       orderBys = orderBy + " desc";
/* 406 */     return queryPage(query, orderBys, pageNo, pageSize, classType);
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
/*     */   @ADFSession
/*     */   public abstract <T> PageResults<T> queryPage(String paramString1, String paramString2, int paramInt1, int paramInt2, Class<T> paramClass);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @ADFSession
/*     */   public abstract <T> PageResults<T> queryPageWithJoin(List<String[]> paramList, List<String> paramList1, int paramInt1, int paramInt2, Class<T>... paramVarArgs);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @ADFSession
/*     */   public Iterator<String> valueIterator()
/*     */   {
/* 440 */     return valueIterator(String.class);
/*     */   }
/*     */   
/*     */   @ADFSession
/*     */   public <T> Iterator<T> valueIterator(Class<T> classType)
/*     */   {
/* 446 */     return valueIterator("*:*", classType);
/*     */   }
/*     */   
/*     */   @ADFSession
/*     */   public <T> Iterator<T> valueIterator(String queryString, Class<T> classType)
/*     */   {
/* 452 */     return valueIterator(queryString, null, classType);
/*     */   }
/*     */   
/*     */   @ADFSession
/*     */   public Iterator<String> valueIterator(String queryString, String orderBys)
/*     */   {
/* 458 */     return valueIterator(queryString, orderBys, String.class);
/*     */   }
/*     */   
/*     */   @ADFSession
/*     */   public Iterator<String> valueIterator(String queryString)
/*     */   {
/* 464 */     return valueIterator(queryString, null, String.class);
/*     */   }
/*     */   
/*     */ 
/*     */   @ADFSession
/*     */   public abstract <T> Iterator<T> valueIterator(String paramString1, String paramString2, Class<T> paramClass);
/*     */   
/*     */   @ADFSession
/*     */   public Iterator<Map.Entry<String, String>> entryIterator()
/*     */   {
/* 474 */     return entryIterator(String.class);
/*     */   }
/*     */   
/*     */   @ADFSession
/*     */   public <T> Iterator<Map.Entry<String, T>> entryIterator(Class<T> classType)
/*     */   {
/* 480 */     return entryIterator("*:*", classType);
/*     */   }
/*     */   
/*     */   @ADFSession
/*     */   public Iterator<Map.Entry<String, String>> entryIterator(String queryString, String orderBys)
/*     */   {
/* 486 */     return entryIterator(queryString, orderBys, String.class);
/*     */   }
/*     */   
/*     */   @ADFSession
/*     */   public <T> Iterator<Map.Entry<String, T>> entryIterator(String queryString, Class<T> classType)
/*     */   {
/* 492 */     return entryIterator(queryString, null, classType);
/*     */   }
/*     */   
/*     */   @ADFSession
/*     */   public Iterator<Map.Entry<String, String>> entryIterator(String queryString)
/*     */   {
/* 498 */     return entryIterator(queryString, null, String.class);
/*     */   }
/*     */   
/*     */   @ADFSession
/*     */   public abstract <T> Iterator<Map.Entry<String, T>> entryIterator(String paramString1, String paramString2, Class<T> paramClass);
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\client\api\GridQueryOperations.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */