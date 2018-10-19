/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.utils;
/*     */ 
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
/*     */   
/*     */   public static String dateToString(Date dt, String format)
/*     */   {
/*  45 */     SimpleDateFormat sdf = new SimpleDateFormat(format);
/*  46 */     return sdf.format(dt);
/*     */   }
/*     */   
/*  49 */   public static String[] supportPatterns = { "yyyyMMdd", "yyyy-MM-dd HH:mm:ss" };
/*     */   
/*     */   static {
/*  52 */     String currentSetting = System.getProperty("gs.date.patterns");
/*  53 */     if (currentSetting != null)
/*     */     {
/*  55 */       StringTokenizer stk = new StringTokenizer(currentSetting, ",");
/*  56 */       supportPatterns = new String[stk.countTokens()];
/*  57 */       int i = 0;
/*  58 */       while (stk.hasMoreTokens())
/*     */       {
/*  60 */         String pattern = stk.nextToken();
/*  61 */         supportPatterns[(i++)] = pattern;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static final Date parse(String strDate)
/*     */   {
/*  69 */     Date dt = null;
/*     */     try
/*     */     {
/*  72 */       dt = DateUtils.parseDate(strDate, supportPatterns);
/*     */     }
/*     */     catch (ParseException e)
/*     */     {
/*  76 */       LogUtil.getCoreLog().error("Date:" + strDate, e);
/*     */     }
/*  78 */     return dt;
/*     */   }
/*     */   
/*     */   public static final Date parse(String strDate, String pattern)
/*     */   {
/*  83 */     Date dt = null;
/*     */     try
/*     */     {
/*  86 */       dt = DateUtils.parseDate(strDate, new String[] { pattern });
/*     */     }
/*     */     catch (ParseException e)
/*     */     {
/*  90 */       LogUtil.getCoreLog().error("Date:" + strDate, e);
/*     */     }
/*  92 */     return dt;
/*     */   }
/*     */   
/*     */   public static String format(Date dt, String fmtStr)
/*     */   {
/*  97 */     if (dt == null)
/*  98 */       return "";
/*  99 */     String str = DateFormatUtils.format(dt, fmtStr);
/* 100 */     return str;
/*     */   }
/*     */   
/*     */   public static String format(Date dt)
/*     */   {
/* 105 */     if (dt == null)
/* 106 */       return "";
/* 107 */     String str = DateFormatUtils.format(dt, "yyyyMMdd");
/* 108 */     return str;
/*     */   }
/*     */   
/*     */ 
/*     */   public static Date stringToDate(String dateStr)
/*     */   {
/* 114 */     return stringToDate(dateStr, "yyyy-MM-dd");
/*     */   }
/*     */   
/*     */   public static Date stringToDate(String dateStr, String format)
/*     */   {
/* 119 */     return stringToDate(dateStr, format, Locale.getDefault());
/*     */   }
/*     */   
/*     */   public static Date stringToDate(String dateStr, String format, Locale locale)
/*     */   {
/* 124 */     SimpleDateFormat sdf = new SimpleDateFormat(format, locale);
/* 125 */     Date s_date = null;
/*     */     try
/*     */     {
/* 128 */       s_date = sdf.parse(dateStr);
/*     */     }
/*     */     catch (ParseException e)
/*     */     {
/* 132 */       LogUtil.getAppLog().error("DateUtil.stringToDate errr.DateStr:" + dateStr + " fmt:" + format);
/* 133 */       s_date = correctDate(dateStr, format);
/*     */     }
/* 135 */     return s_date;
/*     */   }
/*     */   
/*     */   private static Date correctDate(String dateStr, String format)
/*     */   {
/* 140 */     Map<String, Locale> set = new HashMap();
/* 141 */     set.put("MMM dd yyyy hh:mm:ss:SSSaa", Locale.US);
/* 142 */     set.put("yyyyMMdd HH:mm:ss:SSS", Locale.US);
/* 143 */     set.put("EEE MMM dd HH:mm:ss z yyyy", Locale.US);
/* 144 */     set.put("yyyyMMdd HH:mm:ss:SSS", Locale.getDefault());
/* 145 */     set.put("yyyy/MM/dd HH:mm:ss.SSS", Locale.getDefault());
/* 146 */     set.remove(format);
/* 147 */     for (Entry<String, Locale> es : set.entrySet())
/*     */     {
/* 149 */       SimpleDateFormat sdf = new SimpleDateFormat((String)es.getKey(), (Locale)es.getValue());
/*     */       
/*     */       try
/*     */       {
/* 153 */         Date s_date = sdf.parse(dateStr);
/* 154 */         LogUtil.getAppLog().error("DateUtil.correctDate work.DateStr:" + dateStr + " fmt:" + (String)es.getKey());
/* 155 */         return s_date;
/*     */       }
/*     */       catch (ParseException e)
/*     */       {
/* 159 */         LogUtil.getAppLog().error("DateUtil.correctDate errr.DateStr:" + dateStr + " fmt:" + (String)es.getKey());
/*     */       }
/*     */     }
/* 162 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static long today()
/*     */   {
/* 171 */     Calendar c = Calendar.getInstance();
/* 172 */     c.setTime(new Date());
/* 173 */     c.set(13, 0);
/* 174 */     c.set(14, 0);
/* 175 */     c.set(12, 0);
/* 176 */     c.set(11, 0);
/* 177 */     return c.getTimeInMillis();
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
/* 190 */     long lTime = 0L;
/*     */     try
/*     */     {
/* 193 */       lTime = difference * 24 * 60 * 60 * 1000L;
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 197 */       LogUtil.getCoreLog().error("DateFormatUtil.addDay: {}", e);
/*     */     }
/* 199 */     return date + lTime;
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
/* 211 */       SimpleDateFormat YYYY_MM_DD = new SimpleDateFormat("yyyyMMdd");
/* 212 */       Date dt = YYYY_MM_DD.parse(dateStr);
/* 213 */       return dt.getTime();
/*     */     }
/*     */     catch (ParseException e)
/*     */     {
/* 217 */       LogUtil.getLogger().error("DateFormatUtil.toLong parser error:{}", new Object[] { dateStr });
/*     */     }
/* 219 */     return 0L;
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\utils\DateUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */