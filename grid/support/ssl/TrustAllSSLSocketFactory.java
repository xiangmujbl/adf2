/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.support.ssl;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.net.InetAddress;
/*    */ import java.net.Socket;
/*    */ import java.net.UnknownHostException;
/*    */ import java.security.SecureRandom;
/*    */ import java.util.concurrent.atomic.AtomicBoolean;
/*    */ import javax.net.ssl.SSLContext;
/*    */ import javax.net.ssl.SSLSocketFactory;
/*    */ import javax.net.ssl.TrustManager;
/*    */ 
/*    */ public class TrustAllSSLSocketFactory extends SSLSocketFactory
/*    */ {
/* 16 */   private SSLSocketFactory factory = null;
/* 17 */   private static TrustAllSSLSocketFactory INST = null;
/* 18 */   private static AtomicBoolean initialed = new AtomicBoolean(false);
/*    */   
/*    */   public static TrustAllSSLSocketFactory getDefault() {
/* 21 */     if ((!initialed.get()) && (INST == null)) {
/* 22 */       INST = new TrustAllSSLSocketFactory();
/* 23 */       initialed.set(true);
/*    */     }
/* 25 */     return INST;
/*    */   }
/*    */   
/*    */ 
/*    */   public TrustAllSSLSocketFactory()
/*    */   {
/*    */     try
/*    */     {
/* 33 */       SSLContext sslcontext = SSLContext.getInstance("TLS");
/* 34 */       TrustManager[] trustAllCerts = new TrustManager[1];
/* 35 */       trustAllCerts[0] = new SSLAllX509TrustManager();
/* 36 */       sslcontext.init(null, trustAllCerts, new SecureRandom());
/* 37 */       this.factory = sslcontext.getSocketFactory();
/*    */     } catch (Exception ex) {
/* 39 */       ex.printStackTrace();
/*    */     }
/*    */   }
/*    */   
/*    */   public Socket createSocket(Socket s, InputStream consumed, boolean autoClose) throws IOException
/*    */   {
/* 45 */     return this.factory.createSocket(s, consumed, autoClose);
/*    */   }
/*    */   
/*    */   public Socket createSocket(Socket s, String host, int port, boolean autoClose) throws IOException
/*    */   {
/* 50 */     return this.factory.createSocket(s, host, port, autoClose);
/*    */   }
/*    */   
/*    */   public String[] getDefaultCipherSuites()
/*    */   {
/* 55 */     return this.factory.getDefaultCipherSuites();
/*    */   }
/*    */   
/*    */   public String[] getSupportedCipherSuites()
/*    */   {
/* 60 */     return this.factory.getSupportedCipherSuites();
/*    */   }
/*    */   
/*    */   public Socket createSocket(InetAddress address, int port, InetAddress localAddress, int localPort) throws IOException
/*    */   {
/* 65 */     return this.factory.createSocket(address, port, localAddress, localPort);
/*    */   }
/*    */   
/*    */   public Socket createSocket(InetAddress host, int port) throws IOException
/*    */   {
/* 70 */     return this.factory.createSocket(host, port);
/*    */   }
/*    */   
/*    */   public Socket createSocket(String host, int port, InetAddress localHost, int localPort)
/*    */     throws IOException, UnknownHostException
/*    */   {
/* 76 */     return this.factory.createSocket(host, port, localHost, localPort);
/*    */   }
/*    */   
/*    */   public Socket createSocket(String host, int port)
/*    */     throws IOException, UnknownHostException
/*    */   {
/* 82 */     return this.factory.createSocket(host, port);
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\support\ssl\TrustAllSSLSocketFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */