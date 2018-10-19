/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.data.temporal.utils;
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
/*     */ public class IntervalUtils
/*     */ {
/*     */   public static final boolean during(long thisStart, long thisEnd, long compareStart, long compareEnd)
/*     */   {
/*  22 */     return (thisStart < compareStart) && (compareEnd < thisEnd);
/*     */   }
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
/*     */   public static final boolean start(long thisStart, long thisEnd, long compareStart, long compareEnd)
/*     */   {
/*  36 */     return (thisStart == compareStart) && (compareStart < compareEnd) && (compareEnd < thisEnd);
/*     */   }
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
/*     */   public static final boolean finish(long thisStart, long thisEnd, long compareStart, long compareEnd)
/*     */   {
/*  50 */     return (thisStart < compareStart) && (compareStart < compareEnd) && (compareEnd == thisEnd);
/*     */   }
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
/*     */   public static final boolean frontOverlap(long thisStart, long thisEnd, long compareStart, long compareEnd)
/*     */   {
/*  64 */     return (thisStart < compareStart) && (compareStart < thisEnd) && (thisEnd < compareEnd);
/*     */   }
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
/*     */   public static final boolean latterOverlap(long thisStart, long thisEnd, long compareStart, long compareEnd)
/*     */   {
/*  78 */     return (compareStart < thisStart) && (thisStart < compareEnd) && (compareEnd < thisEnd);
/*     */   }
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
/*     */   public static final boolean equal(long thisStart, long thisEnd, long compareStart, long compareEnd)
/*     */   {
/*  92 */     return (compareStart == thisStart) && (compareStart < compareEnd) && (compareEnd == thisEnd);
/*     */   }
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
/*     */   public static final boolean fullContains(long thisStart, long thisEnd, long compareStart, long compareEnd)
/*     */   {
/* 106 */     return (compareStart < thisStart) && (thisStart < thisEnd) && (thisEnd < compareEnd);
/*     */   }
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
/*     */   public static final boolean startContains(long thisStart, long thisEnd, long compareStart, long compareEnd)
/*     */   {
/* 120 */     return (compareStart == thisStart) && (thisStart < thisEnd) && (thisEnd < compareEnd);
/*     */   }
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
/*     */   public static final boolean finishContains(long thisStart, long thisEnd, long compareStart, long compareEnd)
/*     */   {
/* 134 */     return (compareStart < thisStart) && (thisStart < thisEnd) && (thisEnd == compareEnd);
/*     */   }
/*     */   
/*     */   private static boolean overlaps(long thisFrom, long thisTo, long otherFrom, long otherTo, boolean needTruncate)
/*     */   {
/* 139 */     if (otherFrom <= 0L)
/* 140 */       otherFrom = TemporalUtils.correctNow(otherFrom, needTruncate);
/* 141 */     if (otherTo <= 0L)
/* 142 */       otherTo = otherFrom;
/* 143 */     return (thisFrom < otherTo) && (otherFrom < thisTo);
/*     */   }
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
/*     */   public static boolean overlapsValidTime(long validFrom, long validTo, long otherFrom, long otherTo)
/*     */   {
/* 157 */     return overlaps(validFrom, validTo, otherFrom, otherTo, true);
/*     */   }
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
/*     */   public static boolean overlapsTxnTime(long txnTime, long txnTimeEnd, long otherTxnTime, long otherTxnTimeEnd)
/*     */   {
/* 171 */     return overlaps(txnTime, txnTimeEnd, otherTxnTime, otherTxnTimeEnd, false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean contains(long thisFrom, long thisTo, long millisInstant)
/*     */   {
/* 184 */     return (millisInstant >= thisFrom) && (millisInstant < thisTo);
/*     */   }
/*     */   
/*     */   public static boolean contansNotEqual(long thisFrom, long thisTo, long millisInstant)
/*     */   {
/* 189 */     return (millisInstant > thisFrom) && (millisInstant < thisTo);
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\data\temporal\utils\IntervalUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */