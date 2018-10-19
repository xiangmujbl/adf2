/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.connect.domain;
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
/*    */ public enum GridStatus
/*    */ {
/* 23 */   offline("offline"),  locatorReady("locatorReady"),  clusterReady("clusterReady"),  rebalancing("rebalancing"), 
/* 24 */   shutdowning("shutdowning");
/*    */   
/*    */   private final String name;
/*    */   
/*    */   private GridStatus(String name) {
/* 29 */     this.name = name;
/*    */   }
/*    */   
/*    */   public String getStatus() {
/* 33 */     return this.name;
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\connect\domain\GridStatus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */