/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.connect.impl;
/*     */ 
/*     */ import com.gemstone.gemfire.cache.client.Pool;
/*     */ import com.gemstone.gemfire.cache.client.PoolManager;
/*     */ import com.gemstone.gemfire.internal.cache.PoolFactoryImpl;
/*     */ import com.jnj.adf.grid.common.ADFException;
/*     */ import com.jnj.adf.grid.connect.GridConnectionManager;
/*     */ import com.jnj.adf.grid.connect.attri.ADFPoolAttributes;
/*     */ import com.jnj.adf.grid.connect.domain.GridInfo;
/*     */ import com.jnj.adf.grid.connect.domain.IPAddress;
/*     */ import com.jnj.adf.grid.manager.RegionBuilder;
/*     */ import com.jnj.adf.grid.support.context.MetaServiceHelper;
/*     */ import com.jnj.adf.grid.utils.LogUtil;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.apache.logging.log4j.Logger;
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
/*     */ public final class GridConnectionImpl
/*     */   extends AbstractGridConnection
/*     */ {
/*     */   private GridInfo gridInfo;
/*  39 */   private AtomicBoolean connectStatus = new AtomicBoolean(false);
/*     */   private String usingPoolName;
/*  41 */   private ADFPoolAttributes attributes = ADFPoolAttributes.getDefaultAttributes();
/*     */   
/*     */ 
/*     */ 
/*     */   public GridConnectionImpl()
/*     */   {
/*  47 */     LogUtil.getCoreLog().debug("using GridConnectionImpl to connect.");
/*     */   }
/*     */   
/*     */   public boolean connect()
/*     */   {
/*  52 */     if (!this.gridInfo.valid()) {
/*  53 */       throw new ADFException("Cann't connect to grid gridInfo:" + this.gridInfo);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  59 */     LogUtil.getCoreLog().info("Connecting to grid {}/{} ....", new Object[] { this.gridInfo.getGridName(), this.gridInfo.getLocators() });
/*     */     try
/*     */     {
/*  62 */       Pool pool = null;
/*     */       
/*  64 */       if (this.gridInfo.isLogicMasterGrid()) {
/*  65 */         String masterPoolName = GridConnectionManager.getMasterPoolName();
/*  66 */         if (StringUtils.isNotEmpty(masterPoolName)) {
/*  67 */           pool = PoolManager.find(masterPoolName);
/*     */         }
/*     */       }
/*     */       
/*  71 */       if (pool == null) {
/*  72 */         LogUtil.getCoreLog().debug("gridInfo is not master grid. gridInfo:{}", new Object[] { this.gridInfo });
/*  73 */         String poolName = generatePoolName(this.gridInfo.getGridName());
/*  74 */         if (validateGemfireCache()) {
/*  75 */           pool = createPool(this.gridInfo, poolName, this.attributes);
/*     */         } else {
/*  77 */           LogUtil.getCoreLog().debug("gemfire cache is null, create default pool, gridInfo:{}", new Object[] { this.gridInfo });
/*  78 */           pool = initCacheWithPool(this.gridInfo, poolName, this.attributes);
/*     */         }
/*     */       }
/*  81 */       this.usingPoolName = pool.getName();
/*  82 */       this.connectStatus.set(true);
/*     */       
/*  84 */       if (StringUtils.isEmpty(this.gridInfo.getGridName()))
/*     */       {
/*  86 */         GridInfo fetchedGridInfo = MetaServiceHelper.fetchGridInfo(pool);
/*  87 */         this.gridInfo.setDefaultGroup(fetchedGridInfo.getDefaultGroup());
/*  88 */         this.gridInfo.setGridName(fetchedGridInfo.getGridName());
/*  89 */         this.gridInfo.setLocators(fetchedGridInfo.getLocators());
/*  90 */         this.gridInfo.setJmxAddress(fetchedGridInfo.getJmxAddress());
/*  91 */         this.gridInfo.setJmxManagerPort(fetchedGridInfo.getJmxManagerPort());
/*     */       }
/*     */       
/*  94 */       this.gridInfo.setConnected(true);
/*  95 */       LogUtil.getCoreLog().info("Connect to grid successful. GridInfo:{}", new Object[] { this.gridInfo });
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*  99 */       LogUtil.getCoreLog().error("Connct to grid error, gridId={} hosts={}", new Object[] { this.gridInfo.getGridName(), this.gridInfo
/* 100 */         .locatorAddress(), e });
/* 101 */       return false;
/*     */     }
/*     */     
/* 104 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isConnected()
/*     */   {
/* 115 */     return this.connectStatus.get();
/*     */   }
/*     */   
/*     */ 
/*     */   public void close()
/*     */   {
/* 121 */     if (this.gridInfo.isMaster()) {
/* 122 */       throw new ADFException("you can not close master grid connection.");
/*     */     }
/*     */     
/* 125 */     RegionBuilder.newBuilder().setGridName(this.gridInfo.getGridName()).destroy();
/*     */     
/* 127 */     LogUtil.getCoreLog().info("Close grid connection ");
/* 128 */     if (getPool() != null)
/*     */     {
/* 130 */       getPool().destroy();
/* 131 */       PoolManager.getAll().remove(this.usingPoolName);
/*     */     }
/*     */   }
/*     */   
/*     */   public GridInfo fetchGridInfo()
/*     */   {
/* 137 */     return this.gridInfo;
/*     */   }
/*     */   
/*     */   public void setPoolAttributes(ADFPoolAttributes attributes) {
/* 141 */     Assert.notNull(attributes);
/* 142 */     this.attributes = attributes;
/*     */   }
/*     */   
/*     */ 
/*     */   public String getPoolName()
/*     */   {
/* 148 */     return this.usingPoolName;
/*     */   }
/*     */   
/*     */ 
/*     */   public Pool getPool()
/*     */   {
/* 154 */     return PoolManager.find(this.usingPoolName);
/*     */   }
/*     */   
/*     */ 
/*     */   public void setGridInfo(GridInfo gridInfo)
/*     */   {
/* 160 */     Assert.notNull(gridInfo);
/* 161 */     this.gridInfo = gridInfo;
/*     */   }
/*     */   
/*     */   protected Pool createPool(GridInfo gridInfo, String poolName, ADFPoolAttributes attributes)
/*     */   {
/* 166 */     Pool pool = PoolManager.find(poolName);
/* 167 */     if (pool == null) {
/* 168 */       LogUtil.getCoreLog().debug("not found pool in Poolmanager. start to create pool use PoolFactory. attributes:{}", new Object[] { attributes });
/* 169 */       List<IPAddress> addressList = gridInfo.locatorAddress();
/* 170 */       PoolFactoryImpl pf = buildPoolFactory(addressList, attributes);
/* 171 */       pool = pf.create(poolName);
/* 172 */       PoolManager.getAll().put(poolName, pool);
/*     */     }
/* 174 */     LogUtil.getCoreLog().debug("find Pool in Poolmanager. poolName:{}, Pool:{}", new Object[] { pool.getName(), pool });
/* 175 */     return pool;
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\connect\impl\GridConnectionImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */