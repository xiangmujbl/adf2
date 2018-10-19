/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.auth.util;
/*    */ 
/*    */ import com.jnj.adf.grid.utils.LogUtil;
/*    */ import java.io.PrintStream;
/*    */ import java.security.SecureRandom;
/*    */ import java.util.UUID;
/*    */ import javax.crypto.Cipher;
/*    */ import javax.crypto.SecretKey;
/*    */ import javax.crypto.SecretKeyFactory;
/*    */ import javax.crypto.spec.DESKeySpec;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Crypto
/*    */ {
/*    */   private static final String SALT = "7018028820109108";
/*    */   
/*    */   public static String sign(String rowdata)
/*    */   {
/* 21 */     byte[] encryptBytes = encrypt(rowdata.getBytes(), "7018028820109108");
/* 22 */     return parseByte2HexStr(encryptBytes);
/*    */   }
/*    */   
/*    */   public static String signToken(String data, String salt) {
/* 26 */     byte[] encryptBytes = encrypt(data.getBytes(), salt);
/* 27 */     return parseByte2HexStr(encryptBytes);
/*    */   }
/*    */   
/*    */   public static String sign(byte[] buf) {
/* 31 */     byte[] encryptBytes = encrypt(buf, "7018028820109108");
/* 32 */     return parseByte2HexStr(encryptBytes);
/*    */   }
/*    */   
/*    */   private static String parseByte2HexStr(byte[] buf) {
/* 36 */     StringBuffer sb = new StringBuffer();
/* 37 */     for (int i = 0; i < buf.length; i++) {
/* 38 */       String hex = Integer.toHexString(buf[i] & 0xFF);
/* 39 */       if (hex.length() == 1) {
/* 40 */         hex = '0' + hex;
/*    */       }
/* 42 */       sb.append(hex.toUpperCase());
/*    */     }
/* 44 */     return sb.toString();
/*    */   }
/*    */   
/*    */   private static byte[] encrypt(byte[] data, String password)
/*    */   {
/*    */     try {
/* 50 */       SecureRandom random = new SecureRandom();
/* 51 */       DESKeySpec desKey = new DESKeySpec(password.getBytes());
/* 52 */       SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
/* 53 */       SecretKey securekey = keyFactory.generateSecret(desKey);
/* 54 */       Cipher cipher = Cipher.getInstance("DES");
/* 55 */       cipher.init(1, securekey, random);
/* 56 */       return cipher.doFinal(data);
/*    */     } catch (Throwable e) {
/* 58 */       LogUtil.getCoreLog().error("", e);
/*    */     }
/* 60 */     return null;
/*    */   }
/*    */   
/*    */   public static String UUID() {
/* 64 */     return UUID.randomUUID().toString();
/*    */   }
/*    */   
/*    */   public static void main(String[] args) {
/* 68 */     System.out.println(UUID());
/* 69 */     System.out.println(sign(UUID()));
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\auth\util\Crypto.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */