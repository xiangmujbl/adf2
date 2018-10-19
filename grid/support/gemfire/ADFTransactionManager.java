/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.support.gemfire;
/*     */ 
/*     */ import com.gemstone.gemfire.cache.Cache;
/*     */ import com.gemstone.gemfire.cache.CacheTransactionManager;
/*     */ import com.gemstone.gemfire.cache.GemFireCache;
/*     */ import com.gemstone.gemfire.cache.Region;
/*     */ import com.gemstone.gemfire.internal.cache.GemFireCacheImpl;
/*     */ import com.jnj.adf.client.api.exceptions.ADFTransactionCommitException;
/*     */ import com.jnj.adf.grid.utils.LogUtil;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.springframework.beans.factory.InitializingBean;
/*     */ import org.springframework.transaction.CannotCreateTransactionException;
/*     */ import org.springframework.transaction.NoTransactionException;
/*     */ import org.springframework.transaction.TransactionDefinition;
/*     */ import org.springframework.transaction.support.AbstractPlatformTransactionManager;
/*     */ import org.springframework.transaction.support.DefaultTransactionStatus;
/*     */ import org.springframework.transaction.support.ResourceTransactionManager;
/*     */ import org.springframework.transaction.support.TransactionSynchronizationManager;
/*     */ import org.springframework.util.Assert;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ADFTransactionManager
/*     */   extends AbstractPlatformTransactionManager
/*     */   implements InitializingBean, ResourceTransactionManager
/*     */ {
/*     */   private GemFireCache cache;
/*  77 */   private boolean copyOnRead = true;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ADFTransactionManager() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ADFTransactionManager(Cache cache)
/*     */   {
/*  95 */     this.cache = cache;
/*  96 */     afterPropertiesSet();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void afterPropertiesSet() {}
/*     */   
/*     */ 
/*     */   protected Object doGetTransaction()
/*     */     throws org.springframework.transaction.TransactionException
/*     */   {
/* 107 */     CacheTransactionObject txObject = new CacheTransactionObject(null);
/* 108 */     CacheHolder cacheHolder = (CacheHolder)TransactionSynchronizationManager.getResource(getCache());
/* 109 */     txObject.setHolder(cacheHolder);
/* 110 */     return txObject;
/*     */   }
/*     */   
/*     */   protected boolean isExistingTransaction(Object transaction)
/*     */     throws org.springframework.transaction.TransactionException
/*     */   {
/* 116 */     CacheTransactionObject txObject = (CacheTransactionObject)transaction;
/*     */     
/* 118 */     return txObject.getHolder() != null;
/*     */   }
/*     */   
/*     */   protected void doBegin(Object transaction, TransactionDefinition definition)
/*     */     throws org.springframework.transaction.TransactionException
/*     */   {
/* 124 */     CacheTransactionObject txObject = (CacheTransactionObject)transaction;
/*     */     
/* 126 */     GemFireCache cache = null;
/*     */     
/*     */     try
/*     */     {
/* 130 */       cache = getCache();
/* 131 */       if (LogUtil.getCoreLog().isDebugEnabled())
/*     */       {
/* 133 */         LogUtil.getCoreLog().debug("Acquired Cache [" + cache + "] for local Cache transaction");
/*     */       }
/*     */       
/* 136 */       txObject.setHolder(new CacheHolder(null));
/* 137 */       cache.getCacheTransactionManager().begin();
/* 138 */       TransactionSynchronizationManager.bindResource(cache, txObject.getHolder());
/*     */ 
/*     */     }
/*     */     catch (IllegalStateException ex)
/*     */     {
/* 143 */       throw new CannotCreateTransactionException("An ongoing transaction already is already associated with the current thread; are there multiple transaction managers ?", ex);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void doCommit(DefaultTransactionStatus status)
/*     */     throws org.springframework.transaction.TransactionException
/*     */   {
/* 154 */     LogUtil.getCoreLog().debug("Committing Gemfire local transaction on Cache [" + this.cache + "]");
/*     */     try
/*     */     {
/* 157 */       this.cache.getCacheTransactionManager().commit();
/*     */     }
/*     */     catch (IllegalStateException ex)
/*     */     {
/* 161 */       throw new NoTransactionException("No transaction associated with the current thread; are there multiple transaction managers ?", ex);
/*     */ 
/*     */     }
/*     */     catch (com.gemstone.gemfire.cache.TransactionException ex)
/*     */     {
/*     */ 
/* 167 */       throw new ADFTransactionCommitException("Unexpected failure on commit of Cache local transaction", ex);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void doRollback(DefaultTransactionStatus status)
/*     */     throws org.springframework.transaction.TransactionException
/*     */   {
/* 174 */     LogUtil.getCoreLog().debug("Rolling back Cache local transaction for [" + this.cache + "]");
/*     */     try
/*     */     {
/* 177 */       this.cache.getCacheTransactionManager().rollback();
/*     */     }
/*     */     catch (IllegalStateException ex)
/*     */     {
/* 181 */       throw new NoTransactionException("No transaction associated with the current thread; are there multiple transaction managers ?", ex);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void doSetRollbackOnly(DefaultTransactionStatus status)
/*     */   {
/* 189 */     CacheTransactionObject txObject = (CacheTransactionObject)status.getTransaction();
/* 190 */     if (status.isDebug())
/*     */     {
/* 192 */       LogUtil.getCoreLog().debug("Setting Gemfire local transaction [" + txObject.getHolder() + "] rollback-only");
/*     */     }
/* 194 */     txObject.getHolder().setRollbackOnly();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void doCleanupAfterCompletion(Object transaction)
/*     */   {
/* 201 */     TransactionSynchronizationManager.unbindResource(this.cache);
/*     */   }
/*     */   
/*     */ 
/*     */   protected final boolean useSavepointForNestedTransaction()
/*     */   {
/* 207 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public GemFireCache getCache()
/*     */   {
/* 217 */     if (this.cache == null)
/* 218 */       this.cache = GemFireCacheImpl.getInstance();
/* 219 */     return this.cache;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setCache(GemFireCache cache)
/*     */   {
/* 230 */     this.cache = cache;
/*     */   }
/*     */   
/*     */ 
/*     */   public Object getResourceFactory()
/*     */   {
/* 236 */     return getCache();
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
/*     */   public <K, V> void setRegion(Region<K, V> region)
/*     */   {
/* 253 */     Assert.notNull(region, "non-null arguments are required");
/* 254 */     this.cache = ((Cache)region.getRegionService());
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
/*     */   public void setCopyOnRead(boolean copyOnRead)
/*     */   {
/* 275 */     this.copyOnRead = copyOnRead;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isCopyOnRead()
/*     */   {
/* 286 */     return this.copyOnRead;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private static class CacheTransactionObject
/*     */   {
/*     */     private CacheHolder cacheHolder;
/*     */     
/*     */ 
/*     */ 
/*     */     public CacheHolder getHolder()
/*     */     {
/* 300 */       return this.cacheHolder;
/*     */     }
/*     */     
/*     */     public void setHolder(CacheHolder cacheHolder)
/*     */     {
/* 305 */       this.cacheHolder = cacheHolder;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private static class CacheHolder
/*     */   {
/* 312 */     private boolean rollbackOnly = false;
/*     */     
/*     */ 
/*     */     public boolean isRollbackOnly()
/*     */     {
/* 317 */       return this.rollbackOnly;
/*     */     }
/*     */     
/*     */     public void setRollbackOnly()
/*     */     {
/* 322 */       this.rollbackOnly = true;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\support\gemfire\ADFTransactionManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */