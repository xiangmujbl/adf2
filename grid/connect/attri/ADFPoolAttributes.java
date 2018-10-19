/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.connect.attri;
/*     */ 
/*     */ import com.jnj.adf.grid.support.system.ADFConfigHelper;
/*     */ import com.jnj.adf.grid.support.system.ADFConfigHelper.ITEMS;
/*     */ 
/*     */ 
/*     */ public class ADFPoolAttributes
/*     */ {
/*   9 */   private int freeConnectionTimeout = 10000;
/*  10 */   private int readTimeout = 10000;
/*  11 */   private int minConnections = 1;
/*  12 */   private int maxConnections = -1;
/*  13 */   private int retryAttempts = -1;
/*  14 */   private boolean subscriptionEnabled = false;
/*  15 */   private boolean prSingleHopEnabled = true;
/*  16 */   private boolean gateway = false;
/*  17 */   private boolean multiuserAuthentication = false;
/*  18 */   private boolean pdxReadSerialized = false;
/*  19 */   private boolean gateWay = false;
/*     */   
/*     */   public static ADFPoolAttributes getDefaultAttributes() {
/*  22 */     ADFPoolAttributes attributes = new ADFPoolAttributes();
/*  23 */     boolean isMultipleUser = ADFConfigHelper.getBoolean(ITEMS.CLINET_MULTIPLE_USER);
/*  24 */     attributes.setPrSingleHopEnabled(ADFConfigHelper.getBoolean(ITEMS.CLIENT_POOL_SINGLEHOP));
/*  25 */     attributes.setMultiuserAuthentication(isMultipleUser);
/*  26 */     attributes.setReadTimeout(ADFConfigHelper.getInteger(ITEMS.CLIENT_POOL_TIMEOUT).intValue());
/*  27 */     attributes.setRetryAttempts(ADFConfigHelper.getInteger(ITEMS.CLIENT_POOL_RETRY).intValue());
/*  28 */     attributes.setRetryAttempts(ADFConfigHelper.getInteger(ITEMS.CLIENT_POOL_RETRY).intValue());
/*  29 */     attributes.setMaxConnections(ADFConfigHelper.getInteger(ITEMS.CLIENT_POOL_MAX).intValue());
/*  30 */     attributes.setMinConnections(ADFConfigHelper.getInteger(ITEMS.CLIENT_POOL_MIN).intValue());
/*  31 */     attributes.setFreeConnectionTimeout(ADFConfigHelper.getInteger(ITEMS.CLIENT_FREE_TIMEOUT).intValue());
/*  32 */     attributes.setSubscriptionEnabled(true);
/*  33 */     attributes.setPdxReadSerialized(false);
/*  34 */     return attributes;
/*     */   }
/*     */   
/*     */   public static ADFPoolAttributes getDefaultMasterAttributes() {
/*  38 */     ADFPoolAttributes attributes = new ADFPoolAttributes();
/*  39 */     boolean isMultipleUser = ADFConfigHelper.getBoolean(ITEMS.MASTER_CLINET_MULTIPLE_USER);
/*  40 */     attributes.setMultiuserAuthentication(isMultipleUser);
/*  41 */     attributes.setReadTimeout(ADFConfigHelper.getInteger(ITEMS.MASTER_CLIENT_POOL_TIMEOUT).intValue());
/*  42 */     attributes.setRetryAttempts(ADFConfigHelper.getInteger(ITEMS.MASTER_CLIENT_POOL_RETRY).intValue());
/*  43 */     attributes.setRetryAttempts(ADFConfigHelper.getInteger(ITEMS.MASTER_CLIENT_POOL_RETRY).intValue());
/*  44 */     attributes.setMaxConnections(ADFConfigHelper.getInteger(ITEMS.MASTER_CLIENT_POOL_MAX).intValue());
/*  45 */     attributes.setMinConnections(ADFConfigHelper.getInteger(ITEMS.MASTER_CLIENT_POOL_MIN).intValue());
/*  46 */     attributes.setFreeConnectionTimeout(ADFConfigHelper.getInteger(ITEMS.MASTER_CLIENT_FREE_TIMEOUT).intValue());
/*  47 */     attributes.setSubscriptionEnabled(true);
/*  48 */     attributes.setPdxReadSerialized(false);
/*  49 */     return attributes;
/*     */   }
/*     */   
/*     */   public int getFreeConnectionTimeout() {
/*  53 */     return this.freeConnectionTimeout;
/*     */   }
/*     */   
/*     */   public void setFreeConnectionTimeout(int freeConnectionTimeout) {
/*  57 */     this.freeConnectionTimeout = freeConnectionTimeout;
/*     */   }
/*     */   
/*     */   public int getReadTimeout() {
/*  61 */     return this.readTimeout;
/*     */   }
/*     */   
/*     */   public void setReadTimeout(int readTimeout) {
/*  65 */     this.readTimeout = readTimeout;
/*     */   }
/*     */   
/*     */   public int getMinConnections() {
/*  69 */     return this.minConnections;
/*     */   }
/*     */   
/*     */   public void setMinConnections(int minConnections) {
/*  73 */     this.minConnections = minConnections;
/*     */   }
/*     */   
/*     */   public int getMaxConnections() {
/*  77 */     return this.maxConnections;
/*     */   }
/*     */   
/*     */   public void setMaxConnections(int maxConnections) {
/*  81 */     this.maxConnections = maxConnections;
/*     */   }
/*     */   
/*     */   public int getRetryAttempts() {
/*  85 */     return this.retryAttempts;
/*     */   }
/*     */   
/*     */   public void setRetryAttempts(int retryAttempts) {
/*  89 */     this.retryAttempts = retryAttempts;
/*     */   }
/*     */   
/*     */   public boolean isSubscriptionEnabled() {
/*  93 */     return this.subscriptionEnabled;
/*     */   }
/*     */   
/*     */   public void setSubscriptionEnabled(boolean subscriptionEnabled) {
/*  97 */     this.subscriptionEnabled = subscriptionEnabled;
/*     */   }
/*     */   
/*     */   public boolean isPrSingleHopEnabled() {
/* 101 */     return this.prSingleHopEnabled;
/*     */   }
/*     */   
/*     */   public void setPrSingleHopEnabled(boolean prSingleHopEnabled) {
/* 105 */     this.prSingleHopEnabled = prSingleHopEnabled;
/*     */   }
/*     */   
/*     */   public boolean isGateway() {
/* 109 */     return this.gateway;
/*     */   }
/*     */   
/*     */   public void setGateway(boolean gateway) {
/* 113 */     this.gateway = gateway;
/*     */   }
/*     */   
/*     */   public boolean isMultiuserAuthentication() {
/* 117 */     return this.multiuserAuthentication;
/*     */   }
/*     */   
/*     */   public void setMultiuserAuthentication(boolean multiuserAuthentication) {
/* 121 */     this.multiuserAuthentication = multiuserAuthentication;
/*     */   }
/*     */   
/*     */   public boolean isPdxReadSerialized() {
/* 125 */     return this.pdxReadSerialized;
/*     */   }
/*     */   
/*     */   public void setPdxReadSerialized(boolean pdxReadSerialized) {
/* 129 */     this.pdxReadSerialized = pdxReadSerialized;
/*     */   }
/*     */   
/*     */   public boolean isGateWay() {
/* 133 */     return this.gateWay;
/*     */   }
/*     */   
/*     */   public void setGateWay(boolean gateWay) {
/* 137 */     this.gateWay = gateWay;
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\connect\attri\ADFPoolAttributes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */