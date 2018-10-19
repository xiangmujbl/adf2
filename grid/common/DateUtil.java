/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.common;
/*     */ 
/*     */ import com.jnj.adf.grid.utils.LogUtil;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.StringTokenizer;
/*     */ import org.apache.commons.lang3.time.DateFormatUtils;
/*     */ import org.apache.commons.lang3.time.DateUtils;
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
/*     */ public class DateUtil
/*     */ {
/*     */   public static final String DATE_FORMAT_1 = "yyyy-MM-dd";
/*     */   public static final String F_yyyyMMdd = "yyyyMMdd";
/*     */   public static final String F_yyyyMMddHHmmss = "yyyyMMddHHmmss";
/*     */   public static final String yyyy_MM_dd_HHmmss_SSS = "yyyy/MM/dd HH:mm:ss.SSS";
/*     */   public static final String yyyyMMdd_HHmmssSSS = "yyyyMMdd HH:mm:ss:SSS";
/*     */   public static final String yyyy_MM_dd_HHmmssSSS = "yyyy-MM-dd HH:mm:ss:SSS";
/*     */   public static final String US_MMM_dd_yyyy_hhmmssSSSaa = "MMM dd yyyy hh:mm:ss:SSSaa";
/*     */   public static final String US_EEE_MMM_dd_hhmmsszyyyy = "EEE MMM dd HH:mm:ss z yyyy";
/*     */   public static final String F_yyyy_MM_dd_HHmmssSSS = "yyyy-MM-dd-HHmmssSSS";
/*     */   
/*     */   public static String dateToString(Date dt, String format)
/*     */   {
/*  48 */     SimpleDateFormat sdf = new SimpleDateFormat(format);
/*  49 */     return sdf.format(dt);
/*     */   }
/*     */   
/*  52 */   public static String[] supportPatterns = { "yyyyMMdd", "yyyy-MM-dd HH:mm:ss" };
/*     */   
/*     */   static {
/*  55 */     String currentSetting = System.getProperty("gs.date.patterns");
/*  56 */     if (currentSetting != null)
/*     */     {
/*  58 */       StringTokenizer stk = new StringTokenizer(currentSetting, ",");
/*  59 */       supportPatterns = new String[stk.countTokens()];
/*  60 */       int i = 0;
/*  61 */       while (stk.hasMoreTokens())
/*     */       {
/*  63 */         String pattern = stk.nextToken();
/*  64 */         supportPatterns[(i++)] = pattern;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static final Date parse(String strDate)
/*     */   {
/*  72 */     Date dt = null;
/*     */     try
/*     */     {
/*  75 */       dt = DateUtils.parseDate(strDate, supportPatterns);
/*     */     }
/*     */     catch (ParseException e)
/*     */     {
/*  79 */       LogUtil.getCoreLog().error("Date:" + strDate, e);
/*     */     }
/*  81 */     return dt;
/*     */   }
/*     */   
/*     */   public static final Date parse(String strDate, String pattern)
/*     */   {
/*  86 */     Date dt = null;
/*     */     try
/*     */     {
/*  89 */       dt = DateUtils.parseDate(strDate, new String[] { pattern });
/*     */     }
/*     */     catch (ParseException e)
/*     */     {
/*  93 */       LogUtil.getCoreLog().error("Date:" + strDate, e);
/*     */     }
/*  95 */     return dt;
/*     */   }
/*     */   
/*     */   public static String format(Date dt, String fmtStr)
/*     */   {
/* 100 */     if (dt == null)
/* 101 */       return "";
/* 102 */     String str = DateFormatUtils.format(dt, fmtStr);
/* 103 */     return str;
/*     */   }
/*     */   
/*     */   public static String format(Date dt)
/*     */   {
/* 108 */     if (dt == null)
/* 109 */       return "";
/* 110 */     String str = DateFormatUtils.format(dt, "yyyyMMdd");
/* 111 */     return str;
/*     */   }
/*     */   
/*     */ 
/*     */   public static Date stringToDate(String dateStr)
/*     */   {
/* 117 */     return stringToDate(dateStr, "yyyy-MM-dd");
/*     */   }
/*     */   
/*     */   public static Date stringToDate(String dateStr, String format)
/*     */   {
/* 122 */     return stringToDate(dateStr, format, Locale.getDefault());
/*     */   }
/*     */   
/*     */   public static Date stringToDate(String dateStr, String format, Locale locale)
/*     */   {
/* 127 */     SimpleDateFormat sdf = new SimpleDateFormat(format, locale);
/* 128 */     Date s_date = null;
/*     */     try
/*     */     {
/* 131 */       s_date = sdf.parse(dateStr);
/*     */     }
/*     */     catch (ParseException e)
/*     */     {
/* 135 */       LogUtil.getAppLog().error("DateUtil.stringToDate errr.DateStr:" + dateStr + " fmt:" + format);
/* 136 */       s_date = correctDate(dateStr, format);
/*     */     }
/* 138 */     return s_date;
/*     */   }
/*     */   
/*     */   private static Date correctDate(String dateStr, String format)
/*     */   {
/* 143 */     Map<String, Locale> set = new HashMap();
/* 144 */     set.put("MMM dd yyyy hh:mm:ss:SSSaa", Locale.US);
/* 145 */     set.put("yyyyMMdd HH:mm:ss:SSS", Locale.US);
/* 146 */     set.put("EEE MMM dd HH:mm:ss z yyyy", Locale.US);
/* 147 */     set.put("yyyyMMdd HH:mm:ss:SSS", Locale.getDefault());
/* 148 */     set.put("yyyy/MM/dd HH:mm:ss.SSS", Locale.getDefault());
/* 149 */     set.remove(format);
/* 150 */     for (Entry<String, Locale> es : set.entrySet())
/*     */     {
/* 152 */       SimpleDateFormat sdf = new SimpleDateFormat((String)es.getKey(), (Locale)es.getValue());
/*     */       
/*     */       try
/*     */       {
/* 156 */         Date s_date = sdf.parse(dateStr);
/* 157 */         LogUtil.getAppLog().error("DateUtil.correctDate work.DateStr:" + dateStr + " fmt:" + (String)es.getKey());
/* 158 */         return s_date;
/*     */       }
/*     */       catch (ParseException e)
/*     */       {
/* 162 */         LogUtil.getAppLog().error("DateUtil.correctDate errr.DateStr:" + dateStr + " fmt:" + (String)es.getKey());
/*     */       }
/*     */     }
/* 165 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static long today()
/*     */   {
/* 174 */     Calendar c = Calendar.getInstance();
/* 175 */     c.setTime(new Date());
/* 176 */     c.set(13, 0);
/* 177 */     c.set(14, 0);
/* 178 */     c.set(12, 0);
/* 179 */     c.set(11, 0);
/* 180 */     return c.getTimeInMillis();
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
/*     */   public static long addDay(long date, int difference)
/*     */   {
/* 193 */     long lTime = 0L;
/*     */     try
/*     */     {
/* 196 */       lTime = difference * 24 * 60 * 60 * 1000L;
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 200 */       LogUtil.getCoreLog().error("DateFormatUtil.addDay: {}", e);
/*     */     }
/* 202 */     return date + lTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static long toLong(String dateStr)
/*     */   {
/*     */     try
/*     */     {
/* 214 */       SimpleDateFormat YYYY_MM_DD = new SimpleDateFormat("yyyyMMdd");
/* 215 */       Date dt = YYYY_MM_DD.parse(dateStr);
/* 216 */       return dt.getTime();
/*     */     }
/*     */     catch (ParseException e)
/*     */     {
/* 220 */       LogUtil.getLogger().error("DateFormatUtil.toLong parser error:{}", new Object[] { dateStr });
/*     */     }
/* 222 */     return 0L;
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\common\DateUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */