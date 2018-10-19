/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.utils;
/*     */ 
/*     */ import com.jnj.adf.grid.support.system.ADFConfigHelper;
/*     */ import java.net.URISyntaxException;
/*     */ import java.net.URL;
/*     */ import java.util.Map;
/*     */ import org.apache.logging.log4j.Level;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.apache.logging.log4j.core.LoggerContext;
/*     */ import org.apache.logging.log4j.core.config.Configuration;
/*     */ import org.apache.logging.log4j.core.config.LoggerConfig;
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
/*     */ public class LogUtil
/*     */ {
/*  43 */   private static Logger coreLog = LogManager.getLogger("com.jnj.adf.coreLog");
/*  44 */   static { appLog = LogManager.getLogger("com.jnj.adf.appLog");
/*  45 */     checkLog = LogManager.getLogger("com.jnj.adf.coreLog");
/*  46 */     auditLog = LogManager.getLogger("com.jnj.adf.auditLog"); }
/*  47 */   private static Logger mqSyncLog = LogManager.getLogger("com.jnj.adf.coreLog");
/*     */   
/*     */ 
/*     */   public static Logger getCoreLog()
/*     */   {
/*  52 */     return coreLog;
/*     */   }
/*     */   
/*     */   public static Logger getMqSyncLog()
/*     */   {
/*  57 */     return mqSyncLog;
/*     */   }
/*     */   
/*     */   public static Logger getAuditLog()
/*     */   {
/*  62 */     return auditLog;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Logger getAppLog()
/*     */   {
/*  70 */     return appLog;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Logger getLogicLog()
/*     */   {
/*  78 */     return appLog;
/*     */   }
/*     */   
/*     */   public static Logger getLogger()
/*     */   {
/*  83 */     return appLog;
/*     */   }
/*     */   
/*     */   public static Logger getCheckLog()
/*     */   {
/*  88 */     return checkLog;
/*     */   }
/*     */   
/*     */ 
/*     */   public static void modifyLevel(Logger logger, Level level)
/*     */   {
/*  94 */     LoggerContext context = (LoggerContext)LogManager.getContext(false);
/*  95 */     LoggerConfig conf = context.getConfiguration().getLoggerConfig(logger.getName());
/*  96 */     conf.setLevel(level);
/*  97 */     context.updateLoggers();
/*     */   }
/*     */   
/*     */   public static Map<String, LoggerConfig> getAllLevel() throws URISyntaxException
/*     */   {
/* 102 */     LoggerContext context = (LoggerContext)LogManager.getContext(null, true, ADFConfigHelper.class
/* 103 */       .getClassLoader().getResource(ADFConfigHelper.getLoggerFileName()).toURI());
/* 104 */     return context.getConfiguration().getLoggers();
/*     */   }
/*     */   
/*     */   public static void modifyLevel(String name, Level level) throws URISyntaxException
/*     */   {
/* 109 */     LoggerContext context = (LoggerContext)LogManager.getContext(null, true, ADFConfigHelper.class
/* 110 */       .getClassLoader().getResource(ADFConfigHelper.getLoggerFileName()).toURI());
/* 111 */     LoggerConfig conf = context.getConfiguration().getLoggerConfig(name);
/* 112 */     conf.setLevel(level);
/* 113 */     context.updateLoggers();
/*     */   }
/*     */   
/* 116 */   private static final Watch localWatch = new Watch();
/*     */   private static Logger appLog;
/*     */   
/* 119 */   public static final void startTimer() { localWatch.rest(); }
/*     */   
/*     */   private static Logger auditLog;
/*     */   private static Logger checkLog;
/*     */   public static final long cost() {
/* 124 */     return localWatch.cost();
/*     */   }
/*     */   
/*     */   public long longCost()
/*     */   {
/* 129 */     return localWatch.longCost();
/*     */   }
/*     */   
/*     */   public static final Watch newWatch()
/*     */   {
/* 134 */     return new Watch();
/*     */   }
/*     */   
/*     */ 
/*     */   public static class Watch
/*     */   {
/* 140 */     private final ThreadLocal<Long> TL_COSTUSE = new ThreadLocal();
/* 141 */     private final ThreadLocal<Long> TL_START = new ThreadLocal();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public void rest()
/*     */     {
/* 149 */       long l = System.currentTimeMillis();
/* 150 */       this.TL_COSTUSE.set(Long.valueOf(l));
/* 151 */       this.TL_START.set(Long.valueOf(l));
/*     */     }
/*     */     
/*     */     public long cost()
/*     */     {
/* 156 */       Long end = (Long)this.TL_COSTUSE.get();
/* 157 */       long t1 = end != null ? end.longValue() : System.currentTimeMillis();
/* 158 */       long t2 = System.currentTimeMillis();
/* 159 */       long d = t2 - t1;
/* 160 */       LogUtil.startTimer();
/* 161 */       return d;
/*     */     }
/*     */     
/*     */     public long longCost()
/*     */     {
/* 166 */       Long end = (Long)this.TL_START.get();
/* 167 */       long t1 = end != null ? end.longValue() : System.currentTimeMillis();
/* 168 */       long t2 = System.currentTimeMillis();
/* 169 */       long d = t2 - t1;
/* 170 */       LogUtil.startTimer();
/* 171 */       return d;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\utils\LogUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */