/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.data.temporal.utils;
/*     */ 
/*     */ import com.jnj.adf.grid.support.system.ADFConfigHelper;
/*     */ import com.jnj.adf.grid.support.system.ADFConfigHelper.ITEMS;
/*     */ import com.jnj.adf.grid.utils.LogUtil;
/*     */ import java.io.PrintStream;
/*     */ import java.time.LocalDate;
/*     */ import java.time.LocalDateTime;
/*     */ import java.time.ZoneOffset;
/*     */ import java.time.format.DateTimeFormatter;
/*     */ import java.time.format.DateTimeParseException;
/*     */ import java.time.temporal.ChronoUnit;
/*     */ import java.time.temporal.TemporalUnit;
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
/*     */ 
/*     */ public class TemporalUtils
/*     */ {
/*  40 */   public static final DateTimeFormatter F_DAY = DateTimeFormatter.ofPattern("MM/dd/yyyy");
/*  41 */   public static final DateTimeFormatter F_DAYTIME = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
/*  42 */   public static final DateTimeFormatter F_DAY_yyyy_MM_dd = DateTimeFormatter.ofPattern("yyyy-MM-dd");
/*  43 */   public static final DateTimeFormatter F_DAY_ddMMyyyy_space = DateTimeFormatter.ofPattern("dd MM yyyy");
/*  44 */   public static final DateTimeFormatter[] DAY_FORMATTERS = { F_DAY, F_DAY_yyyy_MM_dd, F_DAY_ddMMyyyy_space };
/*     */   
/*     */   static {
/*  47 */     String temporalUnit = ADFConfigHelper.getProperty(ITEMS.TEMPORAL_UNIT, ChronoUnit.DAYS.name());
/*  48 */     TEMPORAL_UNIT = ChronoUnit.valueOf(temporalUnit); }
/*  49 */   public static long MIN_TIME = truncatedTime(LocalDateTime.MIN).toEpochSecond(ZoneOffset.UTC);
/*  50 */   public static long MAX_TIME = truncatedTime(LocalDateTime.MAX).toEpochSecond(ZoneOffset.UTC);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static TemporalUnit TEMPORAL_UNIT;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/*  65 */     System.out.println(correctDate(Integer.valueOf(1491004800), 0L));
/*     */   }
/*     */   
/*     */   private static final LocalDateTime truncatedTime(LocalDateTime time)
/*     */   {
/*  70 */     if (time != null) {
/*  71 */       return time.truncatedTo(TEMPORAL_UNIT);
/*     */     }
/*  73 */     return null;
/*     */   }
/*     */   
/*     */   public static final LocalDateTime parse(String dateString) {
/*  77 */     return parse(dateString, F_DAY);
/*     */   }
/*     */   
/*     */   public static final LocalDateTime parse(String dateString, DateTimeFormatter df) {
/*  81 */     if (StringUtils.isEmpty(dateString))
/*  82 */       return null;
/*  83 */     LocalDateTime time = null;
/*     */     try {
/*  85 */       LocalDate dt = LocalDate.parse(dateString, df);
/*  86 */       time = LocalDateTime.of(dt.getYear(), dt.getMonth(), dt.getDayOfMonth(), 0, 0);
/*  87 */       time = truncatedTime(time);
/*     */     } catch (DateTimeParseException e) {
/*  89 */       LogUtil.getCoreLog().warn("The date string:{} can't parse to formatter:{}", new Object[] { dateString, df, e });
/*     */     }
/*  91 */     return time;
/*     */   }
/*     */   
/*     */   public static final boolean isMinTime(long time) {
/*  95 */     return time <= MIN_TIME;
/*     */   }
/*     */   
/*     */   public static final boolean isMaxTime(long time) {
/*  99 */     return time >= MAX_TIME;
/*     */   }
/*     */   
/*     */   public static final long toMinLong(LocalDateTime time, boolean needTruncate) {
/* 103 */     return toLong(time, MIN_TIME, needTruncate);
/*     */   }
/*     */   
/*     */   public static final long toMaxLong(LocalDateTime time, boolean needTruncate) {
/* 107 */     return toLong(time, MAX_TIME, needTruncate);
/*     */   }
/*     */   
/*     */   public static final long toLong(LocalDateTime time, boolean needTruncate) {
/* 111 */     return toLong(time, MIN_TIME, needTruncate);
/*     */   }
/*     */   
/*     */   public static final long toLong(LocalDateTime time, long defaultTime, boolean needTruncate) {
/* 115 */     if (time != null) {
/* 116 */       if (needTruncate)
/* 117 */         time = truncatedTime(time);
/* 118 */       return time.toEpochSecond(ZoneOffset.UTC);
/*     */     }
/* 120 */     return defaultTime;
/*     */   }
/*     */   
/*     */   public static final long max() {
/* 124 */     return MAX_TIME;
/*     */   }
/*     */   
/*     */   public static final long min() {
/* 128 */     return MIN_TIME;
/*     */   }
/*     */   
/*     */   public static final String format(long timestamp) {
/* 132 */     return format(timestamp, F_DAY);
/*     */   }
/*     */   
/*     */   public static final String formatTime(long timestamp) {
/* 136 */     return format(timestamp, F_DAYTIME);
/*     */   }
/*     */   
/*     */   public static final String format(long timestamp, DateTimeFormatter df) {
/* 140 */     LocalDateTime time = of(timestamp);
/* 141 */     if (time != null)
/* 142 */       return time.format(df);
/* 143 */     return "";
/*     */   }
/*     */   
/*     */   public static final LocalDateTime of(long timestamp) {
/* 147 */     LocalDateTime dt = LocalDateTime.ofEpochSecond(timestamp, 0, ZoneOffset.UTC);
/* 148 */     return dt;
/*     */   }
/*     */   
/*     */   public static final long now() {
/* 152 */     return now(false);
/*     */   }
/*     */   
/*     */   public static final long now(boolean needTruncate) {
/* 156 */     LocalDateTime now = LocalDateTime.now();
/* 157 */     return toLong(now, -1L, needTruncate);
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
/*     */   public static final long correctNow(long thisTime, boolean needTruncate)
/*     */   {
/* 190 */     if (thisTime < 0L)
/* 191 */       thisTime = now(needTruncate);
/* 192 */     return thisTime;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static final long correctDate(Object value, long defaultTime)
/*     */   {
/* 232 */     LocalDateTime dt = null;
/* 233 */     if (value != null)
/* 234 */       dt = convert(value);
/* 235 */     return toLong(dt, defaultTime, false);
/*     */   }
/*     */   
/*     */   private static final LocalDateTime convert(Object value) { LocalDateTime l;
/*     */     LocalDateTime l;
/* 240 */     if ((value instanceof LocalDateTime)) {
/* 241 */       l = (LocalDateTime)value; } else { LocalDateTime l;
/* 242 */       if (((value instanceof Integer)) || ((value instanceof Long))) {
/* 243 */         l = LocalDateTime.ofEpochSecond(Long.parseLong(value.toString()), 0, ZoneOffset.UTC);
/*     */       } else
/* 245 */         l = parseAnyFormat(StringUtils.trim((String)value));
/*     */     }
/* 247 */     return l;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final LocalDateTime parseAnyFormat(String dateString)
/*     */   {
/* 257 */     for (DateTimeFormatter df : DAY_FORMATTERS) {
/* 258 */       LocalDateTime ldt = parse(dateString, df);
/* 259 */       if (ldt != null) {
/* 260 */         return ldt;
/*     */       }
/*     */     }
/* 263 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static final long correctDateTime(Object value, long defaultTime)
/*     */   {
/* 274 */     LocalDateTime dt = null;
/* 275 */     if (value != null) {
/* 276 */       if ((value instanceof LocalDateTime)) {
/* 277 */         dt = (LocalDateTime)value;
/* 278 */       } else if (((value instanceof Integer)) || ((value instanceof Long))) {
/* 279 */         dt = LocalDateTime.ofEpochSecond(Long.parseLong(value.toString()), 0, ZoneOffset.UTC);
/*     */       } else {
/* 281 */         dt = parseDateTime(StringUtils.trim((String)value), F_DAYTIME);
/*     */       }
/*     */     }
/* 284 */     return toLong(dt, defaultTime, false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static final LocalDateTime parseDateTime(String dateString, DateTimeFormatter df)
/*     */   {
/* 295 */     if (StringUtils.isEmpty(dateString))
/* 296 */       return null;
/* 297 */     LocalDateTime time = null;
/*     */     try {
/* 299 */       time = LocalDateTime.parse(dateString, df);
/*     */     } catch (DateTimeParseException e) {
/* 301 */       LogUtil.getCoreLog().warn("The date string:{} can't parse to formatter:{}", new Object[] { dateString, df, e });
/*     */     }
/* 303 */     return time;
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\data\temporal\utils\TemporalUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */