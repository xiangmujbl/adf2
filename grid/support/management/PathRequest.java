/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.support.management;
/*     */ 
/*     */ import com.gemstone.gemfire.DataSerializable;
/*     */ import com.jnj.adf.grid.support.gemfire.DataSerializerExt;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ 
/*     */ public class PathRequest
/*     */   implements DataSerializable
/*     */ {
/*     */   private static final long serialVersionUID = -3889481409882893846L;
/*     */   private String fullPath;
/*     */   private boolean enabledTemporal;
/*     */   private String resolverColumns;
/*     */   private String colocatedWith;
/*     */   private boolean enabledLucene;
/*     */   private boolean enableLogicKey;
/*     */   private String diskStoreName;
/*     */   private boolean partition;
/*     */   private boolean persistence;
/*     */   private String compressorClassName;
/*     */   
/*     */   public PathRequest()
/*     */   {
/*  26 */     this.enabledTemporal = false;
/*  27 */     this.enabledLucene = true;
/*  28 */     this.enableLogicKey = false;
/*  29 */     this.partition = true;
/*  30 */     this.persistence = true;
/*     */   }
/*     */   
/*     */   public PathRequest(String fullPath, String diskStoreName)
/*     */   {
/*  35 */     this();
/*  36 */     this.fullPath = fullPath;
/*  37 */     this.diskStoreName = diskStoreName;
/*     */   }
/*     */   
/*     */   public PathRequest(PathRequest req)
/*     */   {
/*  42 */     this();
/*  43 */     if (req != null)
/*     */     {
/*  45 */       this.fullPath = req.fullPath;
/*  46 */       this.enabledTemporal = req.enabledTemporal;
/*  47 */       this.resolverColumns = req.resolverColumns;
/*  48 */       this.colocatedWith = req.colocatedWith;
/*  49 */       this.enabledLucene = req.enabledLucene;
/*  50 */       this.enableLogicKey = req.enableLogicKey;
/*  51 */       this.diskStoreName = req.diskStoreName;
/*  52 */       this.partition = req.partition;
/*  53 */       this.persistence = req.persistence;
/*  54 */       this.compressorClassName = req.compressorClassName;
/*     */     }
/*     */   }
/*     */   
/*     */   public String getFullPath()
/*     */   {
/*  60 */     return this.fullPath;
/*     */   }
/*     */   
/*     */   public void setFullPath(String fullPath)
/*     */   {
/*  65 */     this.fullPath = fullPath;
/*     */   }
/*     */   
/*     */   public boolean isEnabledTemporal()
/*     */   {
/*  70 */     return this.enabledTemporal;
/*     */   }
/*     */   
/*     */   public void setEnabledTemporal(boolean enabledTemporal)
/*     */   {
/*  75 */     this.enabledTemporal = enabledTemporal;
/*     */   }
/*     */   
/*     */   public String getResolverColumns()
/*     */   {
/*  80 */     return this.resolverColumns;
/*     */   }
/*     */   
/*     */   public void setResolverColumns(String resolverColumns)
/*     */   {
/*  85 */     this.resolverColumns = resolverColumns;
/*     */   }
/*     */   
/*     */   public boolean isEnabledLucene()
/*     */   {
/*  90 */     return this.enabledLucene;
/*     */   }
/*     */   
/*     */   public void setEnabledLucene(boolean enabledLucene)
/*     */   {
/*  95 */     this.enabledLucene = enabledLucene;
/*     */   }
/*     */   
/*     */   public boolean isEnableLogicKey()
/*     */   {
/* 100 */     return this.enableLogicKey;
/*     */   }
/*     */   
/*     */   public void setEnableLogicKey(boolean enableLogicKey)
/*     */   {
/* 105 */     this.enableLogicKey = enableLogicKey;
/*     */   }
/*     */   
/*     */   public String getDiskStoreName()
/*     */   {
/* 110 */     return this.diskStoreName;
/*     */   }
/*     */   
/*     */   public void setDiskStoreName(String diskStoreName)
/*     */   {
/* 115 */     this.diskStoreName = diskStoreName;
/*     */   }
/*     */   
/*     */   public void toData(DataOutput out)
/*     */     throws IOException
/*     */   {
/* 121 */     DataSerializerExt.toData(getClass(), this, out);
/*     */   }
/*     */   
/*     */   public void fromData(DataInput in)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/* 127 */     DataSerializerExt.fromData(getClass(), this, in);
/*     */   }
/*     */   
/*     */   public boolean isPartition()
/*     */   {
/* 132 */     return this.partition;
/*     */   }
/*     */   
/*     */   public void setPartition(boolean partition)
/*     */   {
/* 137 */     this.partition = partition;
/*     */   }
/*     */   
/*     */   public boolean isPersistence()
/*     */   {
/* 142 */     return this.persistence;
/*     */   }
/*     */   
/*     */   public void setPersistence(boolean persistence)
/*     */   {
/* 147 */     this.persistence = persistence;
/*     */   }
/*     */   
/*     */   public String getCompressorClassName()
/*     */   {
/* 152 */     return this.compressorClassName;
/*     */   }
/*     */   
/*     */   public void setCompressorClassName(String compressorClassName)
/*     */   {
/* 157 */     this.compressorClassName = compressorClassName;
/*     */   }
/*     */   
/*     */   public String getColocatedWith()
/*     */   {
/* 162 */     return this.colocatedWith;
/*     */   }
/*     */   
/*     */   public void setColocatedWith(String colocatedWith)
/*     */   {
/* 167 */     this.colocatedWith = colocatedWith;
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\support\management\PathRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */