/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.security.utils;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import org.apache.commons.lang3.StringUtils;
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
/*     */ public class SecurityPolicyUtils
/*     */ {
/*     */   public static String stringMask(String text, String maskPattern)
/*     */   {
/* 206 */     if (StringUtils.isEmpty(maskPattern))
/* 207 */       return text;
/* 208 */     if (StringUtils.isEmpty(text)) {
/* 209 */       return text;
/*     */     }
/* 211 */     char action = maskPattern.charAt(0);
/* 212 */     if ("LRC".indexOf(action) == -1) {
/* 213 */       return text;
/*     */     }
/* 215 */     int len = 0;
/*     */     try {
/* 217 */       len = Integer.parseInt(maskPattern.substring(1));
/*     */     } catch (NumberFormatException npe) {
/* 219 */       return text;
/*     */     }
/*     */     
/* 222 */     int textLen = text.length();
/* 223 */     if (action == 'L') {
/* 224 */       if (len < 0) {
/* 225 */         len = 0 - len;
/* 226 */         if (textLen >= len) {
/* 227 */           return StringUtils.substring(text, 0, len) + StringUtils.repeat('*', textLen - len);
/*     */         }
/* 229 */         return text;
/*     */       }
/*     */       
/* 232 */       if (textLen >= len) {
/* 233 */         return StringUtils.repeat('*', len) + StringUtils.substring(text, len);
/*     */       }
/* 235 */       return StringUtils.repeat('*', textLen);
/*     */     }
/*     */     
/* 238 */     if (action == 'R') {
/* 239 */       if (len < 0) {
/* 240 */         len = 0 - len;
/* 241 */         if (textLen >= len) {
/* 242 */           return StringUtils.repeat('*', textLen - len) + StringUtils.substring(text, textLen - len);
/*     */         }
/* 244 */         return text;
/*     */       }
/*     */       
/* 247 */       if (textLen >= len) {
/* 248 */         return StringUtils.substring(text, 0, textLen - len) + StringUtils.repeat('*', len);
/*     */       }
/* 250 */       return StringUtils.repeat('*', textLen);
/*     */     }
/*     */     
/* 253 */     if (action == 'C') {
/* 254 */       if (len < 0) {
/* 255 */         len = 0 - len;
/* 256 */         if (textLen >= len) {
/* 257 */           int start = textLen - len >>> 1;
/* 258 */           int end = start + len;
/*     */           
/* 260 */           return StringUtils.repeat('*', start) + StringUtils.substring(text, start, end) + StringUtils.repeat('*', textLen - end);
/*     */         }
/* 262 */         return text;
/*     */       }
/*     */       
/* 265 */       if (textLen >= len) {
/* 266 */         int start = textLen - len >>> 1;
/* 267 */         int end = start + len;
/*     */         
/* 269 */         return StringUtils.substring(text, 0, start) + StringUtils.repeat('*', len) + StringUtils.substring(text, end);
/*     */       }
/* 271 */       return StringUtils.repeat('*', textLen);
/*     */     }
/*     */     
/*     */ 
/* 275 */     return text;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 284 */     System.out.println(stringMask("1234567", "L-5"));
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\security\utils\SecurityPolicyUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */