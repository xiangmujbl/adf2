/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.utils;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.util.Arrays;
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
/*    */ public final class ImportUtils
/*    */ {
/*    */   private static final String UNDER_LINE = "_";
/* 18 */   private static final Pattern PATT_COLUMN = Pattern.compile("[a-zA-Z0-9]");
/*    */   
/*    */   public static String[] parseColumns(String[] columns) {
/* 21 */     if (columns == null)
/* 22 */       return null;
/* 23 */     String[] arr = new String[columns.length];
/* 24 */     int i = 0;
/* 25 */     for (String column : columns) {
/* 26 */       arr[(i++)] = parseColumn(column);
/*    */     }
/* 28 */     return arr;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static String parseColumn(String column)
/*    */   {
/* 39 */     if (StringUtils.isNotBlank(column)) {
/* 40 */       column = replaceSpecialChar(column.toLowerCase());
/* 41 */       char firstChar = column.charAt(0);
/* 42 */       if (Character.isLetter(firstChar)) {
/* 43 */         if (Character.isUpperCase(firstChar))
/* 44 */           column = Character.toLowerCase(firstChar) + column.substring(1);
/*    */       } else {
/* 46 */         column = "_" + column;
/*    */       }
/* 48 */       return column;
/*    */     }
/* 50 */     return "";
/*    */   }
/*    */   
/*    */   private static String replaceSpecialChar(String column) {
/* 54 */     Matcher match = PATT_COLUMN.matcher(column);
/* 55 */     StringBuilder sb = new StringBuilder(16);
/* 56 */     int startIndex = 0;int endIndex = 0;
/* 57 */     String str = null;String tmpstr = null;
/*    */     
/* 59 */     while (match.find()) {
/* 60 */       str = match.group();
/* 61 */       endIndex = match.start();
/* 62 */       if (startIndex != 0) {
/* 63 */         tmpstr = column.substring(startIndex, endIndex);
/* 64 */         if (tmpstr.matches("[\\s_]+")) {
/* 65 */           char firstChar = str.charAt(0);
/* 66 */           if (Character.isLetter(firstChar)) {
/* 67 */             str = Character.toUpperCase(firstChar) + str.substring(1);
/*    */           }
/*    */         }
/*    */       }
/*    */       
/* 72 */       startIndex = match.end();
/* 73 */       sb.append(str);
/*    */     }
/* 75 */     return sb.toString();
/*    */   }
/*    */   
/*    */   public static void main(String[] args) {
/* 79 */     String[] cols = { "1dfd", "23 dsad", "Abc", "aaa bbb", "aaa 123", "aaa_fdd", " +++d,d-----" };
/* 80 */     System.out.println(Arrays.toString(parseColumns(cols)));
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\utils\ImportUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */