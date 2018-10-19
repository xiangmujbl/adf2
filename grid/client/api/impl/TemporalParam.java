/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.client.api.impl;
/*    */ 
/*    */ import com.jnj.adf.grid.client.api.support.ADFServiceContext;
/*    */ import com.jnj.adf.grid.client.api.support.ADFServiceContext.Keys;
/*    */ import com.jnj.adf.grid.data.temporal.utils.TemporalUtils;
/*    */ import java.time.LocalDateTime;
/*    */ import org.joda.time.Interval;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TemporalParam
/*    */ {
/*    */   public LocalDateTime validFrom;
/*    */   public LocalDateTime validTo;
/*    */   public LocalDateTime writtenTime;
/*    */   public long validAt;
/*    */   public long asOf;
/*    */   public Interval validAtInterval;
/*    */   public Interval asOfInterval;
/*    */   public TimeType validAtTimeType;
/*    */   
/*    */   public static enum TimeType
/*    */   {
/* 26 */     interval,  point;
/*    */     
/*    */     private TimeType() {}
/*    */   }
/*    */   
/* 31 */   public static TemporalParam getParam() { TemporalParam param = (TemporalParam)ADFServiceContext.getValue(ADFServiceContext.Keys.TEMPORAL);
/* 32 */     if (param == null)
/*    */     {
/* 34 */       param = new TemporalParam();
/* 35 */       ADFServiceContext.setValue(ADFServiceContext.Keys.TEMPORAL, param);
/*    */     }
/* 37 */     return param;
/*    */   }
/*    */   
/*    */   public static void correctValidAtAndAsof()
/*    */   {
/* 42 */     if (TimeType.interval == getParam().validAtTimeType)
/*    */     {
/* 44 */       correctTimeInterval();
/*    */     }
/*    */     else
/*    */     {
/* 48 */       correctTimePoint();
/*    */     }
/*    */   }
/*    */   
/*    */   private static void correctTimeInterval()
/*    */   {
/* 54 */     long validAtStart = getParam().validAtInterval.getStartMillis();
/* 55 */     long validAtEnd = getParam().validAtInterval.getEndMillis();
/* 56 */     long asOfStart = getParam().asOfInterval.getStartMillis();
/* 57 */     long asOfEnd = getParam().asOfInterval.getEndMillis();
/* 58 */     if ((validAtStart == 0L) && (asOfStart == 0L))
/*    */     {
/* 60 */       validAtStart = TemporalUtils.now(true);
/* 61 */       asOfStart = TemporalUtils.now(false);
/*    */     }
/* 63 */     else if (asOfStart == 0L)
/*    */     {
/* 65 */       asOfStart = validAtStart;
/*    */     }
/* 67 */     else if (validAtStart == 0L)
/*    */     {
/* 69 */       validAtStart = asOfStart;
/*    */     }
/* 71 */     if ((validAtEnd == 0L) && (asOfEnd == 0L))
/*    */     {
/* 73 */       validAtEnd = TemporalUtils.max();
/* 74 */       asOfEnd = TemporalUtils.max();
/*    */     }
/* 76 */     else if (asOfEnd == 0L)
/*    */     {
/* 78 */       asOfEnd = TemporalUtils.max();
/*    */     }
/* 80 */     else if (validAtEnd == 0L)
/*    */     {
/* 82 */       validAtEnd = asOfEnd;
/*    */     }
/* 84 */     getParam().validAtInterval = new Interval(validAtStart, validAtEnd);
/* 85 */     getParam().asOfInterval = new Interval(asOfStart, asOfEnd);
/*    */   }
/*    */   
/*    */   private static void correctTimePoint()
/*    */   {
/* 90 */     if ((getParam().validAt == 0L) && (getParam().asOf == 0L))
/*    */     {
/* 92 */       getParam().validAt = TemporalUtils.now(true);
/* 93 */       getParam().asOf = TemporalUtils.now(false);
/*    */     }
/* 95 */     else if (getParam().asOf == 0L) {
/* 96 */       getParam().asOf = getParam().validAt;
/* 97 */     } else if (getParam().validAt == 0L) {
/* 98 */       getParam().validAt = getParam().asOf;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\client\api\impl\TemporalParam.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */