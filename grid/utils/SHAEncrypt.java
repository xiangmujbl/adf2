/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.utils;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.security.MessageDigest;
/*    */ 
/*    */ public class SHAEncrypt
/*    */ {
/*    */   public static final String KEY_SHA = "SHA";
/*    */   public static final String KEY_SHA1 = "SHA-1";
/*    */   public static final String KEY_SHA2 = "SHA-256";
/*    */   private static final String SALT = "B6b884d57";
/*    */   
/*    */   public static String encryptSHA(String data, String shaName) throws Exception
/*    */   {
/* 15 */     MessageDigest sha = MessageDigest.getInstance(shaName);
/* 16 */     sha.update("B6b884d57".getBytes());
/* 17 */     byte[] bytes = sha.digest(data.getBytes());
/* 18 */     return org.springframework.util.Base64Utils.encodeToString(bytes);
/*    */   }
/*    */   
/*    */   public static String encryptSHA(String data) {
/*    */     try {
/* 23 */       return encryptSHA(data, "SHA-1");
/*    */     } catch (Exception e) {
/* 25 */       e.printStackTrace();
/*    */     }
/* 27 */     return null;
/*    */   }
/*    */   
/*    */   public static void main(String[] args) throws Exception
/*    */   {
/* 32 */     String key = "123456";
/* 33 */     System.out.println(key);
/* 34 */     System.out.println(encryptSHA(key, "SHA-1"));
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\utils\SHAEncrypt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */