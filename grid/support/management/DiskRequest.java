/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.support.management;
/*     */ 
/*     */ import com.jnj.adf.grid.support.gemfire.DataSerializerAdapter;
/*     */ 
/*     */ public class DiskRequest
/*     */   extends DataSerializerAdapter
/*     */ {
/*     */   private static final long serialVersionUID = -2057945679155269213L;
/*     */   private boolean allowForceCompaction;
/*     */   private boolean autoCompact;
/*     */   private int compactionThreshold;
/*     */   private int maxOplogSize;
/*     */   private int queueSize;
/*     */   private int timeInterval;
/*     */   private int writeBufferSize;
/*     */   private String dir;
/*     */   private String name;
/*     */   
/*     */   public DiskRequest()
/*     */   {
/*  21 */     this.allowForceCompaction = false;
/*  22 */     this.autoCompact = true;
/*  23 */     this.compactionThreshold = 50;
/*  24 */     this.maxOplogSize = 100;
/*  25 */     this.queueSize = 10000;
/*  26 */     this.timeInterval = 1000;
/*  27 */     this.writeBufferSize = 32768;
/*     */   }
/*     */   
/*     */   public DiskRequest(String diskStoreName, int maxOplogSize)
/*     */   {
/*  32 */     this();
/*  33 */     this.name = diskStoreName;
/*  34 */     this.maxOplogSize = maxOplogSize;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isAllowForceCompaction()
/*     */   {
/*  41 */     return this.allowForceCompaction;
/*     */   }
/*     */   
/*     */   public void setAllowForceCompaction(boolean allowForceCompaction)
/*     */   {
/*  46 */     this.allowForceCompaction = allowForceCompaction;
/*     */   }
/*     */   
/*     */   public boolean isAutoCompact()
/*     */   {
/*  51 */     return this.autoCompact;
/*     */   }
/*     */   
/*     */   public void setAutoCompact(boolean autoCompact)
/*     */   {
/*  56 */     this.autoCompact = autoCompact;
/*     */   }
/*     */   
/*     */   public int getCompactionThreshold()
/*     */   {
/*  61 */     return this.compactionThreshold;
/*     */   }
/*     */   
/*     */   public void setCompactionThreshold(int compactionThreshold)
/*     */   {
/*  66 */     this.compactionThreshold = compactionThreshold;
/*     */   }
/*     */   
/*     */   public int getMaxOplogSize()
/*     */   {
/*  71 */     return this.maxOplogSize;
/*     */   }
/*     */   
/*     */   public void setMaxOplogSize(int maxOplogSize)
/*     */   {
/*  76 */     this.maxOplogSize = maxOplogSize;
/*     */   }
/*     */   
/*     */   public int getQueueSize()
/*     */   {
/*  81 */     return this.queueSize;
/*     */   }
/*     */   
/*     */   public void setQueueSize(int queueSize)
/*     */   {
/*  86 */     this.queueSize = queueSize;
/*     */   }
/*     */   
/*     */   public int getTimeInterval()
/*     */   {
/*  91 */     return this.timeInterval;
/*     */   }
/*     */   
/*     */   public void setTimeInterval(int timeInterval)
/*     */   {
/*  96 */     this.timeInterval = timeInterval;
/*     */   }
/*     */   
/*     */   public int getWriteBufferSize()
/*     */   {
/* 101 */     return this.writeBufferSize;
/*     */   }
/*     */   
/*     */   public void setWriteBufferSize(int writeBufferSize)
/*     */   {
/* 106 */     this.writeBufferSize = writeBufferSize;
/*     */   }
/*     */   
/*     */   public String getDir()
/*     */   {
/* 111 */     return this.dir;
/*     */   }
/*     */   
/*     */   public void setDir(String dir)
/*     */   {
/* 116 */     this.dir = dir;
/*     */   }
/*     */   
/*     */   public String getName()
/*     */   {
/* 121 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name)
/*     */   {
/* 126 */     this.name = name;
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\support\management\DiskRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */