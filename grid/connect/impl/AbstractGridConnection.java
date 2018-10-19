/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.connect.impl;
/*     */ 
/*     */ import com.gemstone.gemfire.cache.client.ClientCache;
/*     */ import com.gemstone.gemfire.cache.client.ClientCacheFactory;
/*     */ import com.gemstone.gemfire.cache.client.Pool;
/*     */ import com.gemstone.gemfire.cache.client.PoolFactory;
/*     */ import com.gemstone.gemfire.cache.client.PoolManager;
/*     */ import com.gemstone.gemfire.cache.client.internal.SingleHopClientThreadPoolExecutor;
/*     */ import com.gemstone.gemfire.internal.cache.GemFireCacheImpl;
/*     */ import com.gemstone.gemfire.internal.cache.PoolFactoryImpl;
/*     */ import com.jnj.adf.grid.common.ADFException;
/*     */ import com.jnj.adf.grid.connect.GridConnection;
/*     */ import com.jnj.adf.grid.connect.attri.ADFPoolAttributes;
/*     */ import com.jnj.adf.grid.connect.domain.GridInfo;
/*     */ import com.jnj.adf.grid.connect.domain.IPAddress;
/*     */ import com.jnj.adf.grid.support.gemfire.TypeRegistryExt;
/*     */ import com.jnj.adf.grid.utils.LogUtil;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractGridConnection
/*     */   implements GridConnection
/*     */ {
/*  29 */   private static AtomicInteger defaultPoolId = new AtomicInteger(0);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Pool initCacheWithPool(GridInfo gridInfo, String poolName, ADFPoolAttributes attributes)
/*     */   {
/*  38 */     LogUtil.getCoreLog().debug("start to create client cache with pool, gridInfo:{}, attributes:{}", new Object[] { gridInfo, attributes });
/*  39 */     ClientCacheFactory cf = new ClientCacheFactory();
/*  40 */     List<IPAddress> ipAddresses = gridInfo.locatorAddress();
/*  41 */     for (IPAddress ip : ipAddresses)
/*     */     {
/*  43 */       cf.addPoolLocator(ip.getHost(), ip.getPort());
/*     */     }
/*  45 */     cf.setPoolPRSingleHopEnabled(attributes.isPrSingleHopEnabled());
/*  46 */     cf.setPoolMultiuserAuthentication(attributes.isMultiuserAuthentication());
/*  47 */     cf.setPoolReadTimeout(attributes.getReadTimeout());
/*  48 */     cf.setPoolRetryAttempts(attributes.getRetryAttempts());
/*  49 */     cf.setPoolMaxConnections(attributes.getMaxConnections());
/*  50 */     cf.setPoolMinConnections(attributes.getMinConnections());
/*  51 */     cf.setPoolFreeConnectionTimeout(attributes.getFreeConnectionTimeout());
/*  52 */     cf.setPoolSubscriptionEnabled(attributes.isSubscriptionEnabled());
/*  53 */     cf.setPdxReadSerialized(attributes.isPdxReadSerialized());
/*     */     
/*     */ 
/*     */ 
/*  57 */     ClientCache c = cf.create();
/*  58 */     Pool pool = c.getDefaultPool();
/*  59 */     TypeRegistryExt.upgradeClientTypeRegistration();
/*  60 */     SingleHopClientThreadPoolExecutor.upgradeSingleHopClientThreadPoolExecutor();
/*     */     
/*  62 */     return pool;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected abstract Pool createPool(GridInfo paramGridInfo, String paramString, ADFPoolAttributes paramADFPoolAttributes);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected PoolFactoryImpl buildPoolFactory(List<IPAddress> ipAddresses, ADFPoolAttributes attributes)
/*     */   {
/*  83 */     LogUtil.getCoreLog().debug("start to buildPoolFactory ,ipAddresses:{}, attributes:{}", new Object[] { ipAddresses, attributes });
/*  84 */     PoolFactory pf = PoolManager.createFactory();
/*  85 */     pf.setReadTimeout(attributes.getReadTimeout());
/*  86 */     pf.setRetryAttempts(attributes.getRetryAttempts());
/*  87 */     pf.setMaxConnections(attributes.getMaxConnections());
/*  88 */     pf.setMinConnections(attributes.getMinConnections());
/*  89 */     pf.setFreeConnectionTimeout(attributes.getFreeConnectionTimeout());
/*  90 */     pf.setSubscriptionEnabled(attributes.isSubscriptionEnabled());
/*  91 */     pf.setMultiuserAuthentication(attributes.isMultiuserAuthentication());
/*  92 */     for (IPAddress locator : ipAddresses) {
/*  93 */       pf.addLocator(locator.getHost(), locator.getPort());
/*     */     }
/*  95 */     if ((pf instanceof PoolFactoryImpl)) {
/*  96 */       return (PoolFactoryImpl)pf;
/*     */     }
/*  98 */     throw new ADFException("poolFactory is not instance of PoolFactoryImpl");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean validateGemfireCache()
/*     */   {
/* 106 */     GemFireCacheImpl cache = GemFireCacheImpl.getInstance();
/* 107 */     return cache != null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String generatePoolName(String gridName)
/*     */   {
/* 117 */     if (StringUtils.isNotEmpty(gridName)) {
/* 118 */       return gridName + "/pool";
/*     */     }
/* 120 */     String defaultPoolNamePrefix = "default";
/*     */     
/*     */ 
/* 123 */     int j = defaultPoolId.getAndIncrement();
/* 124 */     defaultPoolNamePrefix = defaultPoolNamePrefix + StringUtils.leftPad(new StringBuilder().append(j).append("").toString(), 4, "0");
/*     */     
/* 126 */     return defaultPoolNamePrefix + "/pool";
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\connect\impl\AbstractGridConnection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */