/*    */ package com.jnj.adf.dataservice.adfcoreignite.client.api;
/*    */ 
/*    */ import com.jnj.adf.grid.data.temporal.utils.TemporalUtils;
/*    */ import java.time.LocalDateTime;
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
/*    */ public abstract interface GridTemporalQueryOperations
/*    */   extends GridLocationQueryOperations
/*    */ {
/*    */   public GridTemporalOperations validAt(String validAtTimeString)
/*    */   {
/* 28 */     LocalDateTime d1 = TemporalUtils.parse(validAtTimeString);
/* 29 */     return validAt(d1);
/*    */   }
/*    */   
/*    */   public GridTemporalOperations validAt(String validAtTimeString, String asOfTimeString) {
/* 33 */     LocalDateTime d1 = TemporalUtils.parse(validAtTimeString);
/* 34 */     LocalDateTime d2 = TemporalUtils.parse(asOfTimeString);
/* 35 */     return validAt(d1, d2);
/*    */   }
/*    */   
/*    */   public GridTemporalOperations validAt(LocalDateTime validAtTime) {
/* 39 */     return validAt(validAtTime, null);
/*    */   }
/*    */   
/*    */   public abstract GridTemporalOperations validAt(LocalDateTime paramLocalDateTime1, LocalDateTime paramLocalDateTime2);
/*    */   
/*    */   public GridTemporalOperations validAt(String validAtStartString, String validAtEndString, String asOfTimeString)
/*    */   {
/* 46 */     return validAt(validAtStartString, validAtEndString, asOfTimeString, null);
/*    */   }
/*    */   
/*    */   public GridTemporalOperations validAt(LocalDateTime validAtStartTime, LocalDateTime validAtEndTime, LocalDateTime asOfTime)
/*    */   {
/* 51 */     return validAt(validAtStartTime, validAtEndTime, asOfTime, null);
/*    */   }
/*    */   
/*    */   public GridTemporalOperations validAt(String validAtStartString, String validAtEndString, String asOfStartString, String asOfEndString)
/*    */   {
/* 56 */     LocalDateTime d1 = TemporalUtils.parse(validAtStartString);
/* 57 */     LocalDateTime d2 = TemporalUtils.parse(validAtEndString);
/* 58 */     LocalDateTime d3 = TemporalUtils.parse(asOfStartString);
/* 59 */     LocalDateTime d4 = TemporalUtils.parse(asOfEndString);
/* 60 */     return validAt(d1, d2, d3, d4);
/*    */   }
/*    */   
/*    */   public abstract GridTemporalOperations validAt(LocalDateTime paramLocalDateTime1, LocalDateTime paramLocalDateTime2, LocalDateTime paramLocalDateTime3, LocalDateTime paramLocalDateTime4);
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\client\api\GridTemporalQueryOperations.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */