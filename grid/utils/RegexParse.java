/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.utils;
/*    */ 
/*    */ import com.jnj.adf.grid.connect.domain.IPAddress;
/*    */ import java.io.PrintStream;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.regex.Matcher;
/*    */ import java.util.regex.Pattern;
/*    */ import org.apache.commons.lang3.StringUtils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RegexParse
/*    */ {
/*    */   private static final String CONN_TYPE_CM = "cm";
/*    */   private static final String CONN_TYPE_GRID = "grid";
/* 37 */   private static final Pattern REGEX_URL = Pattern.compile("^(\\w+?):(\\w+?)://(.*)$");
/* 38 */   private static final Pattern REGEX_HOST = Pattern.compile("(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}):(\\d+)");
/* 39 */   private static final Pattern REGEX_LOCATORS = Pattern.compile("([^\\[\\]:]+?)(?:\\[|:)(\\d+)\\]?");
/*    */   
/* 41 */   private static final Pattern REGEX_GRID = Pattern.compile("(?:/\\w+?)+(?=(?:\\?|\\Z))");
/* 42 */   private static final Pattern REGEX_PARAM = Pattern.compile("(\\w+?)=(\\w+?)(?=(?:&|\\Z))");
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static final List<IPAddress> parseAddress(String str)
/*    */   {
/* 51 */     Matcher match = REGEX_LOCATORS.matcher(str);
/* 52 */     List<IPAddress> urls = new ArrayList();
/* 53 */     while (match.find()) {
/* 54 */       String hostIpAddress = match.group(1);
/* 55 */       if (hostIpAddress != null) {
/* 56 */         hostIpAddress = StringUtils.remove(hostIpAddress, ',');
/*    */       }
/* 58 */       urls.add(new IPAddress(hostIpAddress, Integer.valueOf(match.group(2)).intValue()));
/*    */     }
/*    */     
/* 61 */     return urls;
/*    */   }
/*    */   
/*    */ 
/*    */   public static void main(String[] args)
/*    */   {
/* 67 */     String locators = "localhost[12346],192.168.120.151[12347],192.168.120.152[12348]";
/* 68 */     List<IPAddress> ipAddresses = parseAddress(locators);
/* 69 */     for (IPAddress ipAddress : ipAddresses) {
/* 70 */       System.out.println(ipAddress);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\utils\RegexParse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */