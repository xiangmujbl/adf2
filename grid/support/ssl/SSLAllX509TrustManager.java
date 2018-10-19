/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.support.ssl;
/*    */ 
/*    */ import java.security.cert.CertificateException;
/*    */ import java.security.cert.X509Certificate;
/*    */ import javax.net.ssl.TrustManager;
/*    */ import javax.net.ssl.X509TrustManager;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SSLAllX509TrustManager
/*    */   implements TrustManager, X509TrustManager
/*    */ {
/*    */   public void checkClientTrusted(X509Certificate[] chain, String authType)
/*    */     throws CertificateException
/*    */   {}
/*    */   
/*    */   public void checkServerTrusted(X509Certificate[] chain, String authType)
/*    */     throws CertificateException
/*    */   {}
/*    */   
/*    */   public X509Certificate[] getAcceptedIssuers()
/*    */   {
/* 25 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\support\ssl\SSLAllX509TrustManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */