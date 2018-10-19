/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.manager;
/*     */ 
/*     */ import com.gemstone.gemfire.cache.RegionService;
/*     */ import com.gemstone.gemfire.cache.client.Pool;
/*     */ import com.gemstone.gemfire.cache.client.internal.ProxyCache;
/*     */ import com.gemstone.gemfire.internal.cache.GemFireCacheImpl;
/*     */ import com.jnj.adf.grid.auth.impl.AuthSessionContextHelper;
/*     */ import com.jnj.adf.grid.utils.LogUtil;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.locks.ReentrantLock;
/*     */ import org.apache.commons.lang3.StringUtils;
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
/*     */ public class MultipleUserSupporter
/*     */ {
/*     */   public static final RegionService getRegionService(String gridId)
/*     */   {
/*  36 */     Pool pool = null;
/*  37 */     if (StringUtils.isEmpty(gridId))
/*     */     {
/*  39 */       pool = GridManager.getInstance().getGemFirePool();
/*     */     }
/*     */     else
/*     */     {
/*  43 */       pool = GridManager.getInstance().getGemFirePool(gridId);
/*     */     }
/*  45 */     if ((pool != null) && (pool.getMultiuserAuthentication()))
/*     */     {
/*  47 */       return getRegionServiceForMultiUser(pool);
/*     */     }
/*  49 */     return GemFireCacheImpl.getInstance();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  56 */   static final Map<String, WeakReference<ProxyCache>> pcCache = new ConcurrentHashMap();
/*     */   
/*     */   static String getKey(Pool pool, Properties userC)
/*     */   {
/*  60 */     StringBuilder buid = new StringBuilder();
/*  61 */     buid.append(userC.getProperty("token"));
/*  62 */     buid.append("//");
/*  63 */     buid.append(pool.getName());
/*  64 */     return buid.toString();
/*     */   }
/*     */   
/*  67 */   static final ReentrantLock lock = new ReentrantLock();
/*     */   
/*     */   public static final void closeProxCacheByUser(String userName, String gridId)
/*     */   {
/*  71 */     Pool pool = GridManager.getInstance().getGemFirePool(gridId);
/*  72 */     if ((pool != null) && (!StringUtils.isEmpty(userName)))
/*     */     {
/*  74 */       closeProxCacheByUser(userName, pool);
/*     */     }
/*     */   }
/*     */   
/*     */   public static final void closeProxCacheByUser(String userName, Pool pool)
/*     */   {
/*  80 */     if ((pool != null) && (!StringUtils.isEmpty(userName)))
/*     */     {
/*  82 */       StringBuilder buid = new StringBuilder();
/*  83 */       buid.append(userName);
/*  84 */       buid.append("//");
/*  85 */       buid.append(pool.getName());
/*  86 */       String key = buid.toString();
/*  87 */       WeakReference<ProxyCache> ref = (WeakReference)pcCache.get(key);
/*  88 */       LogUtil.getCoreLog().info("Found proxyCache:{} for key:{}", new Object[] { ref, key });
/*  89 */       ProxyCache pc = null;
/*  90 */       if (ref != null)
/*     */       {
/*  92 */         pc = (ProxyCache)ref.get();
/*  93 */         if ((pc != null) && (!pc.isClosed()))
/*     */         {
/*  95 */           pc.close();
/*  96 */           ref.clear();
/*  97 */           pcCache.remove(key);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static RegionService getRegionServiceForMultiUser(Pool pool)
/*     */   {
/* 106 */     lock.lock();
/*     */     try {
/*     */       GemFireCacheImpl cache;
/* 109 */       if (isMultiUserAuthentication(pool))
/*     */       {
/* 111 */         cache = GemFireCacheImpl.getInstance();
/* 112 */         Properties userC = AuthSessionContextHelper.getMultiUserCredentials();
/* 113 */         String key = getKey(pool, userC);
/* 114 */         WeakReference<ProxyCache> ref = (WeakReference)pcCache.get(key);
/* 115 */         ProxyCache pc = null;
/* 116 */         Object roles; if (ref != null)
/*     */         {
/* 118 */           pc = (ProxyCache)ref.get();
/* 119 */           if ((pc != null) && (!pc.isClosed()))
/*     */           {
/* 121 */             roles = pc.getProperties().get("roles");
/* 122 */             Object userRoles = userC.getProperty("roles");
/* 123 */             if (roles.equals(userRoles)) {
/* 124 */               return pc;
/*     */             }
/*     */             
/* 127 */             pc.close();
/* 128 */             ref.clear();
/* 129 */             pcCache.remove(key);
/*     */           }
/*     */         }
/*     */         
/* 133 */         pc = (ProxyCache)cache.createAuthenticatedCacheView(pool, userC);
/* 134 */         ref = new WeakReference(pc);
/* 135 */         pcCache.put(key, ref);
/* 136 */         return pc;
/*     */       }
/* 138 */       return null;
/*     */     }
/*     */     finally
/*     */     {
/* 142 */       lock.unlock();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean isMultiUserAuthentication(Pool pool)
/*     */   {
/* 155 */     if (pool != null)
/*     */     {
/* 157 */       return pool.getMultiuserAuthentication();
/*     */     }
/* 159 */     return false;
/*     */   }
/*     */   
/*     */   public List<String> getCachedKeys()
/*     */   {
/* 164 */     List<String> list = new ArrayList(pcCache.keySet());
/* 165 */     return list;
/*     */   }
/*     */   
/*     */   public void clearByUser() {}
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\manager\MultipleUserSupporter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */