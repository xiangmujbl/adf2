/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.support.hybrid;
/*     */ 
/*     */ import com.gemstone.gemfire.cache.client.Pool;
/*     */ import com.gemstone.gemfire.cache.client.PoolManager;
/*     */ import com.gemstone.gemfire.cache.client.internal.PoolImpl;
/*     */ import com.gemstone.gemfire.internal.cache.PoolFactoryImpl;
/*     */ import com.gemstone.gemfire.internal.cache.PoolFactoryImpl.PoolAttributes;
/*     */ import com.gemstone.gemfire.internal.cache.PoolManagerImpl;
/*     */ import com.jnj.adf.grid.common.ADFException;
/*     */ import com.jnj.adf.grid.connect.attri.ADFPoolAttributes;
/*     */ import com.jnj.adf.grid.connect.domain.GridInfo;
/*     */ import com.jnj.adf.grid.connect.domain.IPAddress;
/*     */ import com.jnj.adf.grid.connect.impl.AbstractGridConnection;
/*     */ import com.jnj.adf.grid.support.context.ADFRuntime;
/*     */ import com.jnj.adf.grid.support.context.MetaServiceHelper;
/*     */ import com.jnj.adf.grid.support.system.ADFConfigHelper;
/*     */ import com.jnj.adf.grid.support.system.ADFConfigHelper.ITEMS;
/*     */ import com.jnj.adf.grid.utils.LogUtil;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.springframework.util.Assert;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ADFHybridCacheConnection
/*     */   extends AbstractGridConnection
/*     */ {
/*  31 */   private GridInfo gridInfo = null;
/*  32 */   private AtomicBoolean connectStatus = new AtomicBoolean(false);
/*     */   private Pool pool;
/*  34 */   private AtomicBoolean fetched = new AtomicBoolean(false);
/*  35 */   private ADFPoolAttributes attributes = ADFPoolAttributes.getDefaultMasterAttributes();
/*     */   
/*     */ 
/*     */   public ADFHybridCacheConnection()
/*     */   {
/*  40 */     LogUtil.getCoreLog().debug("using ADFHybridCacheConnection to connect.");
/*     */   }
/*     */   
/*     */ 
/*     */   protected Pool createPool(GridInfo gridInfo, String poolName, ADFPoolAttributes attributes)
/*     */   {
/*  46 */     List<IPAddress> ipAddresses = gridInfo.locatorAddress();
/*  47 */     PoolFactoryImpl pf = buildPoolFactory(ipAddresses, attributes);
/*  48 */     Pool pool = PoolManager.find(poolName);
/*  49 */     if (pool == null)
/*     */     {
/*  51 */       LogUtil.getCoreLog().debug("not found pool in Poolmanager. start to createLocalRegion pool use PoolFactory. attributes:{}", new Object[] { attributes });
/*     */       
/*  53 */       PoolFactoryImpl.PoolAttributes poolAttributes = pf.getPoolAttributes();
/*  54 */       LogUtil.getCoreLog().debug("createLocalRegion pool user Poolfactory, gateWay:{}", new Object[] { Boolean.valueOf(attributes.isGateway()) });
/*  55 */       poolAttributes.setGateway(attributes.isGateway());
/*  56 */       pool = PoolImpl.create(PoolManagerImpl.getPMI(), poolName, poolAttributes);
/*     */     }
/*  58 */     LogUtil.getCoreLog().debug("find Pool in Poolmanager. poolName:{}, Pool:{}", new Object[] { pool.getName(), pool });
/*  59 */     return pool;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean connect()
/*     */   {
/*  65 */     if (!this.gridInfo.valid())
/*     */     {
/*  67 */       throw new ADFException("Cann't connect to grid gridInfo:" + this.gridInfo);
/*     */     }
/*     */     
/*  70 */     if (this.connectStatus.get())
/*     */     {
/*  72 */       return true;
/*     */     }
/*     */     try
/*     */     {
/*  76 */       String poolName = generatePoolName(this.gridInfo.getGridName());
/*  77 */       if (validateGemfireCache())
/*     */       {
/*  79 */         LogUtil.getCoreLog().debug("find gemfire cache, createLocalRegion default pool, gridInfo:{}, poolAttributes:{}", new Object[] { this.gridInfo, this.attributes });
/*     */         
/*  81 */         this.pool = createPool(this.gridInfo, poolName, this.attributes);
/*     */       }
/*     */       else
/*     */       {
/*  85 */         LogUtil.getCoreLog().debug("gemfire cache is not found, createLocalRegion cache with default pool, gridInfo:{}, poolAttributes:{}", new Object[] { this.gridInfo, this.attributes });
/*     */         
/*     */ 
/*  88 */         this.pool = initCacheWithPool(this.gridInfo, poolName, this.attributes);
/*     */       }
/*  90 */       LogUtil.getCoreLog().debug("createLocalRegion pool successful, gridInfo:{}", new Object[] { this.gridInfo });
/*  91 */       this.connectStatus.set(true);
/*     */       
/*  93 */       GridInfo fetchedGridInfo = null;
/*  94 */       if (!ADFRuntime.getRuntime().isMaster())
/*     */       {
/*  96 */         fetchedGridInfo = MetaServiceHelper.fetchGridInfo(this.pool);
/*     */       }
/*     */       else
/*     */       {
/* 100 */         this.gridInfo.setDefaultGroup(ADFConfigHelper.getConfig(ITEMS.SYSTEM_NAME));
/* 101 */         this.gridInfo.setGridName(ADFConfigHelper.getConfig(ITEMS.SYSTEM_GRIDNAME));
/* 102 */         this.gridInfo.setLocators(ADFConfigHelper.getConfig(ITEMS.LOCATORS));
/* 103 */         this.gridInfo.setMaster(ADFRuntime.getRuntime().isMaster());
/*     */       }
/*     */       
/* 106 */       if (fetchedGridInfo != null)
/*     */       {
/* 108 */         this.gridInfo.setDefaultGroup(fetchedGridInfo.getDefaultGroup());
/* 109 */         this.gridInfo.setGridName(fetchedGridInfo.getGridName());
/* 110 */         this.gridInfo.setLocators(fetchedGridInfo.getLocators());
/* 111 */         this.gridInfo.setJmxAddress(fetchedGridInfo.getJmxAddress());
/* 112 */         this.gridInfo.setJmxManagerPort(fetchedGridInfo.getJmxManagerPort());
/* 113 */         this.gridInfo.setMaster(fetchedGridInfo.isMaster());
/*     */       }
/* 115 */       this.gridInfo.setConnected(true);
/*     */       
/* 117 */       return true;
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 121 */       LogUtil.getCoreLog().error("Connct to grid error, gridId={} hosts={}", new Object[] { this.gridInfo.getGridName(), this.gridInfo
/* 122 */         .locatorAddress(), e }); }
/* 123 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isConnected()
/*     */   {
/* 130 */     return this.connectStatus.get();
/*     */   }
/*     */   
/*     */ 
/*     */   public String getPoolName()
/*     */   {
/* 136 */     Assert.notNull(this.pool);
/* 137 */     return this.pool.getName();
/*     */   }
/*     */   
/*     */ 
/*     */   public void setGridInfo(GridInfo gridInfo)
/*     */   {
/* 143 */     this.gridInfo = gridInfo;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setPoolAttributes(ADFPoolAttributes attributes)
/*     */   {
/* 149 */     this.attributes = attributes;
/*     */   }
/*     */   
/*     */ 
/*     */   public GridInfo fetchGridInfo()
/*     */   {
/* 155 */     return this.gridInfo;
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
/*     */   public void close()
/*     */   {
/* 190 */     if (this.gridInfo.isMaster())
/*     */     {
/* 192 */       throw new ADFException("you can not close master grid connection.");
/*     */     }
/* 194 */     if (this.pool != null)
/*     */     {
/* 196 */       this.pool.destroy();
/*     */     }
/*     */   }
/*     */   
/*     */   public Pool getPool()
/*     */   {
/* 202 */     return this.pool;
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\support\hybrid\ADFHybridCacheConnection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */