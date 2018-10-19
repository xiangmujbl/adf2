/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.security.utils;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ColumnMaskUtils
/*     */ {
/*     */   public static final String MASK_ID = "MASK_ID";
/*     */   
/*     */ 
/*     */   public static final String MASK_NAME = "MASK_NAME";
/*     */   
/*     */   public static final String MASK_TYPE = "MASK_TYPE";
/*     */   
/*     */   public static final String MASK_LENGTH = "MASK_LENGTH";
/*     */   
/*     */   public static final String STAR_LENGTH = "STAR_LENGHT";
/*     */   
/*     */   public static final String LEFT = "left";
/*     */   
/*     */   public static final String RIGHT = "right";
/*     */   
/*     */   public static final String BETWEEN = "between";
/*     */   
/*     */   public static final String ALL = "all";
/*     */   
/*     */ 
/*     */   public static String getStarString(String content, String maskLength, String type, String starLength)
/*     */   {
/*  29 */     int nMaskLength = Integer.parseInt(maskLength);
/*  30 */     int nStarLength = Integer.parseInt(starLength);
/*     */     
/*  32 */     if (content == null) {
/*  33 */       return content;
/*     */     }
/*     */     
/*  36 */     content = formatContent(content, nMaskLength);
/*     */     
/*     */ 
/*  39 */     content = maskContentByType(content, type, nStarLength);
/*     */     
/*  41 */     return content;
/*     */   }
/*     */   
/*     */   public static String stringMask(String maskName, String content, String maskLength, String type, String starLength)
/*     */   {
/*  46 */     if ("all".equals(type))
/*     */     {
/*  48 */       return getStarString(content, maskLength, type, starLength);
/*     */     }
/*     */     
/*     */ 
/*  52 */     if (isNewMaskType(maskName))
/*     */     {
/*  54 */       return SecurityPolicyUtils.stringMask(content, maskName);
/*     */     }
/*     */     
/*     */ 
/*  58 */     return getStarString(content, maskLength, type, starLength);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private static boolean isNewMaskType(String maskName)
/*     */   {
/*  65 */     char action = maskName.charAt(0);
/*  66 */     if ("LRC".indexOf(action) == -1) {
/*  67 */       return false;
/*     */     }
/*     */     try {
/*  70 */       Integer.parseInt(maskName.substring(1));
/*  71 */       return true;
/*     */     } catch (NumberFormatException npe) {}
/*  73 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private static String formatContent(String content, int masklength)
/*     */   {
/*  80 */     if (content.length() > masklength) {
/*  81 */       return content.substring(0, masklength);
/*     */     }
/*  83 */     if (content.length() == masklength) {
/*  84 */       return content;
/*     */     }
/*  86 */     if (content.length() < masklength)
/*     */     {
/*  88 */       for (int i = content.length(); i < masklength; i++) {
/*  89 */         content = content + " ";
/*     */       }
/*  91 */       return content;
/*     */     }
/*  93 */     return "";
/*     */   }
/*     */   
/*     */   private static String maskContentByType(String content, String type, int starLength)
/*     */   {
/*  98 */     if ("left".equals(type))
/*     */     {
/* 100 */       return genStarString(content, 0, starLength);
/*     */     }
/* 102 */     if ("right".equals(type))
/*     */     {
/* 104 */       return genStarString(content, content.length() - starLength, content.length());
/*     */     }
/* 106 */     if ("between".equals(type))
/*     */     {
/* 108 */       int start = Math.floorDiv(content.length() - starLength, 2);
/* 109 */       return genStarString(content, start, start + starLength);
/*     */     }
/*     */     
/* 112 */     if ("all".equals(type))
/*     */     {
/* 114 */       return genStarString(content, 0, content.length());
/*     */     }
/*     */     
/* 117 */     return "";
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
/*     */   private static String genStarString(String content, int begin, int end)
/*     */   {
/* 131 */     String starStr = "";
/* 132 */     for (int i = begin; i < end; i++) {
/* 133 */       starStr = starStr + "*";
/*     */     }
/* 135 */     return content.substring(0, begin) + starStr + content.substring(end, content.length());
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {}
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\security\utils\ColumnMaskUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */