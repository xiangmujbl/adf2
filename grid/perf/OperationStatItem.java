/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.perf;
/*     */ 
/*     */ import com.jnj.adf.grid.data.temporal.utils.TemporalUtils;
/*     */ import com.jnj.adf.grid.support.gemfire.DataSerializerAdapter;
/*     */ import java.io.PrintStream;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ import java.util.concurrent.atomic.AtomicLong;
/*     */ 
/*     */ 
/*     */ public class OperationStatItem
/*     */   extends DataSerializerAdapter
/*     */   implements Comparable<OperationStatItem>
/*     */ {
/*     */   private static final long serialVersionUID = 5262184494635696820L;
/*     */   private String operation;
/*  16 */   private AtomicInteger count = new AtomicInteger(0);
/*  17 */   private AtomicLong totalCost = new AtomicLong();
/*     */   
/*     */   private long avgCost;
/*     */   private long maxCost;
/*     */   private long minCost;
/*     */   private long recentCost;
/*     */   private long lastUpdate;
/*     */   
/*     */   public OperationStatItem() {}
/*     */   
/*     */   public OperationStatItem(String operation)
/*     */   {
/*  29 */     this.operation = operation;
/*     */   }
/*     */   
/*     */   public void record(long cost)
/*     */   {
/*  34 */     int n = this.count.incrementAndGet();
/*  35 */     long total = this.totalCost.addAndGet(cost);
/*  36 */     this.recentCost = cost;
/*  37 */     this.avgCost = (total / n);
/*  38 */     if (cost > this.maxCost) {
/*  39 */       this.maxCost = cost;
/*  40 */     } else if ((cost < this.minCost) || (this.minCost == 0L))
/*  41 */       this.minCost = cost;
/*  42 */     this.lastUpdate = TemporalUtils.now();
/*     */   }
/*     */   
/*     */   public String getOperation()
/*     */   {
/*  47 */     return this.operation;
/*     */   }
/*     */   
/*     */   public long getCount()
/*     */   {
/*  52 */     return this.count.get();
/*     */   }
/*     */   
/*     */   public long getTotalCost()
/*     */   {
/*  57 */     return this.totalCost.get();
/*     */   }
/*     */   
/*     */   public long getAvgCost()
/*     */   {
/*  62 */     return this.avgCost;
/*     */   }
/*     */   
/*     */   public long getMaxCost()
/*     */   {
/*  67 */     return this.maxCost;
/*     */   }
/*     */   
/*     */   public long getMinCost()
/*     */   {
/*  72 */     return this.minCost;
/*     */   }
/*     */   
/*     */   public long getRecentCost()
/*     */   {
/*  77 */     return this.recentCost;
/*     */   }
/*     */   
/*     */   public long getLastUpdate()
/*     */   {
/*  82 */     return this.lastUpdate;
/*     */   }
/*     */   
/*     */ 
/*     */   public int compareTo(OperationStatItem o)
/*     */   {
/*  88 */     if ((o == null) || (o.operation == null))
/*  89 */       return -1;
/*  90 */     return this.operation.compareTo(o.operation);
/*     */   }
/*     */   
/*     */   public static String header()
/*     */   {
/*  95 */     return "Operation \t Count \t TotalCost \t avg \t max \t min \t recent \t lastUpdate";
/*     */   }
/*     */   
/*     */   public String formatString()
/*     */   {
/* 100 */     return String.format("%s \t %d \t %d \t %d \t %d \t %d \t %d \t %s", new Object[] { this.operation, Integer.valueOf(this.count.get()), Long.valueOf(this.totalCost.get()), Long.valueOf(this.avgCost), 
/* 101 */       Long.valueOf(this.maxCost), Long.valueOf(this.minCost), Long.valueOf(this.recentCost), TemporalUtils.formatTime(this.lastUpdate) });
/*     */   }
/*     */   
/*     */   public static void main(String[] args)
/*     */   {
/* 106 */     OperationStatItem sss = new OperationStatItem("putall");
/* 107 */     sss.record(System.currentTimeMillis() - 10000L);
/* 108 */     sss.record(System.currentTimeMillis() - 10000L + 900L);
/* 109 */     sss.record(System.currentTimeMillis() - 10000L + 700L);
/* 110 */     System.out.println(header());
/* 111 */     System.out.println(sss.formatString());
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 116 */     return "OperationStatItem [operation=" + this.operation + ", count=" + this.count + ", totalCost=" + this.totalCost + ", avgCost=" + this.avgCost + ", maxCost=" + this.maxCost + ", minCost=" + this.minCost + ", recentCost=" + this.recentCost + ", lastUpdate=" + this.lastUpdate + "]";
/*     */   }
/*     */   
/*     */ 
/*     */   public void setOperation(String operation)
/*     */   {
/* 122 */     this.operation = operation;
/*     */   }
/*     */   
/*     */   public void setCount(AtomicInteger count) {
/* 126 */     this.count = count;
/*     */   }
/*     */   
/*     */   public void setTotalCost(AtomicLong totalCost) {
/* 130 */     this.totalCost = totalCost;
/*     */   }
/*     */   
/*     */   public void setAvgCost(long avgCost) {
/* 134 */     this.avgCost = avgCost;
/*     */   }
/*     */   
/*     */   public void setMaxCost(long maxCost) {
/* 138 */     this.maxCost = maxCost;
/*     */   }
/*     */   
/*     */   public void setMinCost(long minCost) {
/* 142 */     this.minCost = minCost;
/*     */   }
/*     */   
/*     */   public void setRecentCost(long recentCost) {
/* 146 */     this.recentCost = recentCost;
/*     */   }
/*     */   
/*     */   public void setLastUpdate(long lastUpdate) {
/* 150 */     this.lastUpdate = lastUpdate;
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\perf\OperationStatItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */