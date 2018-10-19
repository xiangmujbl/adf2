/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.support.hybrid;
/*     */ 
/*     */ import com.gemstone.gemfire.cache.client.PoolManager;
/*     */ import com.gemstone.gemfire.internal.cache.GemFireCacheImpl;
/*     */ import com.gemstone.gemfire.pdx.internal.PeerTypeRegistration;
/*     */ import com.gemstone.gemfire.pdx.internal.TypeRegistration;
/*     */ import com.gemstone.gemfire.pdx.internal.TypeRegistry;
/*     */ import com.jnj.adf.grid.common.ADFException;
/*     */ import com.jnj.adf.grid.connect.GridConnection;
/*     */ import com.jnj.adf.grid.connect.GridConnectionManager;
/*     */ import com.jnj.adf.grid.connect.domain.GridInfo;
/*     */ import com.jnj.adf.grid.manager.ADFConnectFactory;
/*     */ import com.jnj.adf.grid.manager.ADFConnectFactory.ConnTypes;
/*     */ import com.jnj.adf.grid.manager.RegionBuilder;
/*     */ import com.jnj.adf.grid.support.context.ADFRuntime;
/*     */ import com.jnj.adf.grid.support.system.ADFConfigHelper;
/*     */ import com.jnj.adf.grid.support.system.ADFConfigHelper.ITEMS;
/*     */ import com.jnj.adf.grid.utils.LogUtil;
/*     */ import com.jnj.adf.grid.utils.Util;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import java.util.concurrent.locks.Lock;
/*     */ import java.util.concurrent.locks.ReentrantLock;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ADFHybridCacheManager
/*     */ {
/*  32 */   private static String DEFAULT_MASTER_GRID_NAME = "grid_master";
/*  33 */   private static final ADFHybridCacheManager INST = new ADFHybridCacheManager();
/*  34 */   private static final AtomicBoolean initial = new AtomicBoolean(false);
/*     */   
/*     */ 
/*     */   public static ADFHybridCacheManager getInstance()
/*     */   {
/*  39 */     return INST;
/*     */   }
/*     */   
/*     */   static
/*     */   {
/*  44 */     ADFConnectFactory.register(ADFConnectFactory.ConnTypes.Hybrid.name(), ADFHybridCacheConnection.class);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void initDefaultConnection()
/*     */   {
/*  54 */     if (!initial.get())
/*     */     {
/*     */ 
/*  57 */       verifyTypeRegistryConfiguration();
/*     */       
/*     */ 
/*  60 */       GridConnection conn = findMaserConnection();
/*  61 */       ADFRuntime.getRuntime().setMasterGridName(conn.fetchGridInfo().getGridName());
/*  62 */       LogUtil.getCoreLog().info("Master grid connected. {}/{}", new Object[] { conn.fetchGridInfo().getGridName(), conn
/*  63 */         .fetchGridInfo().getLocators() });
/*     */       
/*     */ 
/*  66 */       initial.set(true);
/*  67 */       LogUtil.getCoreLog().debug("PoolManager get all pools, all pool names:{}", new Object[] { PoolManager.getAll().keySet() });
/*     */     }
/*     */   }
/*     */   
/*     */   private void verifyTypeRegistryConfiguration()
/*     */   {
/*  73 */     GemFireCacheImpl cache = GemFireCacheImpl.getInstance();
/*  74 */     if (cache != null)
/*     */     {
/*  76 */       TypeRegistration typeRegistry = cache.getPdxRegistry().getTypeRegistration();
/*  77 */       if ((typeRegistry instanceof PeerTypeRegistration))
/*     */       {
/*  79 */         PeerTypeRegistration peerTypeRegistration = (PeerTypeRegistration)typeRegistry;
/*  80 */         Util.unsafeSet(peerTypeRegistration, "typeRegistryInUse", Boolean.valueOf(true));
/*  81 */         peerTypeRegistration.getLastAllocatedTypeId();
/*  82 */         LogUtil.getCoreLog().info("PeerTypeRegistration ? ");
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*  88 */   private static final Lock connectionLock = new ReentrantLock();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public GridConnection findConnection(String gridName, String locators, boolean create)
/*     */   {
/* 100 */     if (GridConnectionManager.exist(gridName))
/*     */     {
/* 102 */       return GridConnectionManager.find(gridName);
/*     */     }
/*     */     
/*     */     try
/*     */     {
/* 107 */       connectionLock.lock();
/* 108 */       GridConnection conn; if (create)
/*     */       {
/* 110 */         conn = createConnection(gridName, locators);
/* 111 */         return conn;
/*     */       }
/* 113 */       return null;
/*     */     }
/*     */     finally
/*     */     {
/* 117 */       connectionLock.unlock();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public GridConnection findMaserConnection()
/*     */   {
/* 127 */     String masterLocators = findMasterAddress();
/* 128 */     if (StringUtils.isNotEmpty(masterLocators))
/*     */     {
/* 130 */       GridConnection masterConnection = findConnection(DEFAULT_MASTER_GRID_NAME, masterLocators, true);
/* 131 */       if (masterConnection != null)
/*     */       {
/* 133 */         GridInfo masterGridInfo = masterConnection.fetchGridInfo();
/* 134 */         DEFAULT_MASTER_GRID_NAME = masterGridInfo.getGridName();
/* 135 */         ADFConfigHelper.setProperty(ITEMS.MASTER_GRID_NAME, masterGridInfo.getGridName());
/*     */       }
/*     */       else {
/* 138 */         LogUtil.getCoreLog().error("No master grid availiable. locators:{}", new Object[] { masterLocators }); }
/* 139 */       return masterConnection;
/*     */     }
/* 141 */     return null;
/*     */   }
/*     */   
/*     */   public boolean isMasterConnected()
/*     */   {
/* 146 */     GridConnection masterConnection = findConnection(DEFAULT_MASTER_GRID_NAME, null, false);
/* 147 */     if ((masterConnection != null) && (masterConnection.getPool() != null))
/*     */     {
/* 149 */       return true;
/*     */     }
/* 151 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private GridConnection createConnection(String gridName, String locators)
/*     */   {
/* 161 */     GridInfo gridInfo = new GridInfo(gridName);
/* 162 */     locators = ADFConfigHelper.processLocalhost(locators);
/* 163 */     gridInfo.setLocators(locators);
/* 164 */     GridConnection connection = ADFConnectFactory.createConnection(gridInfo, ADFConnectFactory.ConnTypes.Hybrid);
/* 165 */     if (!connection.isConnected())
/*     */     {
/* 167 */       boolean bl = connection.connect();
/* 168 */       if (!bl) {
/* 169 */         throw new ADFException("Can't connect to grid:" + gridInfo + " failure");
/*     */       }
/*     */     }
/* 172 */     GridInfo ginfo = connection.fetchGridInfo();
/* 173 */     GridConnectionManager.register(ginfo.getGridName(), connection);
/* 174 */     if (ADFRuntime.getRuntime().isClientSide())
/*     */     {
/*     */ 
/* 177 */       List<String> regionNames = RegionBuilder.newBuilder().setGridName(ginfo.getGridName()).setPool(connection.getPool()).create();
/* 178 */       LogUtil.getCoreLog().info("Create {} proxy regions on connection:{}/{},{}.", new Object[] { Integer.valueOf(regionNames.size()), connection
/* 179 */         .fetchGridInfo().getGridName(), connection.fetchGridInfo().getLocators(), connection });
/*     */     }
/* 181 */     return connection;
/*     */   }
/*     */   
/*     */   private String findMasterAddress()
/*     */   {
/* 186 */     String masterLocators = ADFConfigHelper.getConfig(ITEMS.NAMING_SERVER);
/* 187 */     return masterLocators;
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\support\hybrid\ADFHybridCacheManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */