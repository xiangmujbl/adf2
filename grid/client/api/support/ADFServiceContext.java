/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.client.api.support;
/*     */ 
/*     */ import com.gemstone.gemfire.cache.execute.FunctionContext;
/*     */ import com.jnj.adf.grid.connect.domain.GridInfo;
/*     */ import com.jnj.adf.grid.support.context.ADFRuntime;
/*     */ import com.jnj.adf.grid.utils.LogUtil;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ADFServiceContext
/*     */ {
/*  34 */   public static final String DEFAULT_GRID_NAME = null;
/*  35 */   private static String defaultGrid = DEFAULT_GRID_NAME;
/*  36 */   private static String defaultGroup = "_default_group";
/*     */   
/*     */   public static void before()
/*     */   {
/*  40 */     int count = ((Integer)((Map)_ADF_SERVICE_QUERY_TL.get()).get(Keys._COUNT)).intValue();
/*  41 */     ((Map)_ADF_SERVICE_QUERY_TL.get()).put(Keys._COUNT, Integer.valueOf(count + 1));
/*  42 */     LogUtil.getCoreLog().debug("Before invoke,session:{}", new Object[] { dump() });
/*     */   }
/*     */   
/*     */   public static void after()
/*     */   {
/*  47 */     int count = ((Integer)((Map)_ADF_SERVICE_QUERY_TL.get()).get(Keys._COUNT)).intValue();
/*  48 */     if (count <= 1)
/*     */     {
/*  50 */       resetSession();
/*  51 */       LogUtil.getCoreLog().debug("After reset,session:{}", new Object[] { dump() });
/*  52 */       return;
/*     */     }
/*  54 */     ((Map)_ADF_SERVICE_QUERY_TL.get()).put(Keys._COUNT, Integer.valueOf(count - 1));
/*  55 */     LogUtil.getCoreLog().debug("After invoke,session:{}", new Object[] { dump() });
/*     */   }
/*     */   
/*     */   public static enum Keys
/*     */   {
/*  60 */     PATH,  GROUPS,  GRID,  Initialized,  VERSION,  QUERY_TYPE,  CLIENT_SESSION,  AUDIT,  BATCHING,  LIMIT,  VAILD_FROM, 
/*  61 */     VAILD_TO,  FILTERS,  _COUNT,  COLLECTION,  ZKHOSTS,  TEMPORAL,  LOCATION,  FUNCTION_CONTEXT,  BATCH_SIZE,  QUERY_2STEP, 
/*  62 */     QUERY_WITHOUT_CACHE,  RW_SCHEMA,  LOCAL_GRID_INFO,  SESSION;
/*     */     
/*     */     private Keys() {}
/*     */   }
/*     */   
/*  67 */   public static enum QueryTypes { OnPath,  OnGroup;
/*     */     
/*     */     private QueryTypes() {} }
/*  70 */   private static ThreadLocal<Map<Keys, Object>> _ADF_SERVICE_QUERY_TL = new InheritableThreadLocal()
/*     */   {
/*     */     protected Map<Keys, Object> initialValue()
/*     */     {
/*  74 */       Map<Keys, Object> map = new HashMap();
/*  75 */       map.put(Keys._COUNT, Integer.valueOf(0));
/*  76 */       return map;
/*     */     }
/*     */     
/*     */     protected void finalize() throws Throwable
/*     */     {
/*  81 */       remove();
/*     */     }
/*     */   };
/*     */   
/*     */   public static final void setValue(Keys key, Object value)
/*     */   {
/*  87 */     if (value != null)
/*     */     {
/*  89 */       Map<Keys, Object> newMap = new HashMap((Map)_ADF_SERVICE_QUERY_TL.get());
/*  90 */       newMap.put(key, value);
/*  91 */       _ADF_SERVICE_QUERY_TL.set(newMap);
/*     */     }
/*     */   }
/*     */   
/*     */   public static final String getVersion()
/*     */   {
/*  97 */     return (String)getValue(Keys.VERSION);
/*     */   }
/*     */   
/*     */   public static final String getPath()
/*     */   {
/* 102 */     return (String)getValue(Keys.PATH);
/*     */   }
/*     */   
/*     */   public static final FunctionContext getFunctionContext()
/*     */   {
/* 107 */     return (FunctionContext)getValue(Keys.FUNCTION_CONTEXT);
/*     */   }
/*     */   
/*     */   public static final String[] getGroups()
/*     */   {
/* 112 */     return (String[])getValue(Keys.GROUPS);
/*     */   }
/*     */   
/*     */   public static final String getGrid()
/*     */   {
/* 117 */     return (String)getValue(Keys.GRID);
/*     */   }
/*     */   
/*     */ 
/*     */   public static final <T> T getValue(Keys key)
/*     */   {
/* 123 */     return (T)((Map)_ADF_SERVICE_QUERY_TL.get()).get(key);
/*     */   }
/*     */   
/*     */   public static final Map<Keys, Object> getThreadLocalCopy()
/*     */   {
/* 128 */     return new HashMap((Map)_ADF_SERVICE_QUERY_TL.get());
/*     */   }
/*     */   
/*     */   public static final void setThreadLocal(Map value)
/*     */   {
/* 133 */     _ADF_SERVICE_QUERY_TL.set(value);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static final void resetSession()
/*     */   {
/* 142 */     LogUtil.getCoreLog().trace("Clear session values");
/* 143 */     ((Map)_ADF_SERVICE_QUERY_TL.get()).clear();
/* 144 */     ((Map)_ADF_SERVICE_QUERY_TL.get()).put(Keys._COUNT, Integer.valueOf(0));
/*     */   }
/*     */   
/*     */   public static final boolean isValid()
/*     */   {
/* 149 */     return true;
/*     */   }
/*     */   
/*     */   public static final String defaultGrid()
/*     */   {
/* 154 */     if (!StringUtils.isEmpty(defaultGrid)) {
/* 155 */       return defaultGrid;
/*     */     }
/* 157 */     return ADFRuntime.getRuntime().getMasterGridName();
/*     */   }
/*     */   
/*     */   public static final String defaultGroup()
/*     */   {
/* 162 */     return defaultGroup;
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
/*     */   public static void recordGridInfo(GridInfo info)
/*     */   {
/* 177 */     if (!info.isMaster())
/*     */     {
/* 179 */       defaultGrid = info.getGridName();
/* 180 */       defaultGroup = info.getDefaultGroup();
/*     */     }
/*     */   }
/*     */   
/*     */   public static String dump()
/*     */   {
/* 186 */     return ((Map)_ADF_SERVICE_QUERY_TL.get()).toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\client\api\support\ADFServiceContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */