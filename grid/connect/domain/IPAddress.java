/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.connect.domain;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class IPAddress
/*    */ {
/*    */   private String host;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   private int port;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public IPAddress(String host, int port)
/*    */   {
/* 24 */     this.host = host;
/* 25 */     this.port = port;
/*    */   }
/*    */   
/*    */   public String getHost() {
/* 29 */     return this.host;
/*    */   }
/*    */   
/* 32 */   public void setHost(String host) { this.host = host; }
/*    */   
/*    */   public int getPort() {
/* 35 */     return this.port;
/*    */   }
/*    */   
/* 38 */   public void setPort(int port) { this.port = port; }
/*    */   
/*    */ 
/*    */   public boolean equals(Object obj)
/*    */   {
/* 43 */     return toString().equals(obj.toString());
/*    */   }
/*    */   
/*    */   public int hashCode()
/*    */   {
/* 48 */     return toString().hashCode();
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 53 */     return this.host + ":" + this.port;
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\connect\domain\IPAddress.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */