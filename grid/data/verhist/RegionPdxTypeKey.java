/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.data.verhist;
/*     */ 
/*     */ import com.gemstone.gemfire.DataSerializable;
/*     */ import com.jnj.adf.grid.support.gemfire.DataSerializerExt;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RegionPdxTypeKey
/*     */   implements DataSerializable
/*     */ {
/*     */   private static final long serialVersionUID = -5964675651662423762L;
/*     */   private String gridPath;
/*     */   private Integer pdxTypeId;
/*     */   
/*     */   public RegionPdxTypeKey() {}
/*     */   
/*     */   public RegionPdxTypeKey(String gridPath, Integer pdxTypeId)
/*     */   {
/*  38 */     this.gridPath = gridPath;
/*  39 */     this.pdxTypeId = pdxTypeId;
/*     */   }
/*     */   
/*     */ 
/*     */   public void fromData(DataInput in)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/*  46 */     DataSerializerExt.fromData(getClass(), this, in);
/*     */   }
/*     */   
/*     */ 
/*     */   public void toData(DataOutput out)
/*     */     throws IOException
/*     */   {
/*  53 */     DataSerializerExt.toData(getClass(), this, out);
/*     */   }
/*     */   
/*     */   public String getGridPath()
/*     */   {
/*  58 */     return this.gridPath;
/*     */   }
/*     */   
/*     */   public void setGridPath(String gridPath)
/*     */   {
/*  63 */     this.gridPath = gridPath;
/*     */   }
/*     */   
/*     */   public Integer getPdxTypeId()
/*     */   {
/*  68 */     return this.pdxTypeId;
/*     */   }
/*     */   
/*     */   public void setPdxTypeId(Integer pdxTypeId)
/*     */   {
/*  73 */     this.pdxTypeId = pdxTypeId;
/*     */   }
/*     */   
/*     */ 
/*     */   public int hashCode()
/*     */   {
/*  79 */     int prime = 31;
/*  80 */     int result = 1;
/*  81 */     result = 31 * result + (this.gridPath == null ? 0 : this.gridPath.hashCode());
/*  82 */     result = 31 * result + (this.pdxTypeId == null ? 0 : this.pdxTypeId.hashCode());
/*  83 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/*  89 */     if (this == obj)
/*  90 */       return true;
/*  91 */     if (obj == null)
/*  92 */       return false;
/*  93 */     if (getClass() != obj.getClass())
/*  94 */       return false;
/*  95 */     RegionPdxTypeKey other = (RegionPdxTypeKey)obj;
/*  96 */     if (this.gridPath == null)
/*     */     {
/*  98 */       if (other.gridPath != null) {
/*  99 */         return false;
/*     */       }
/* 101 */     } else if (!this.gridPath.equals(other.gridPath))
/* 102 */       return false;
/* 103 */     if (this.pdxTypeId == null)
/*     */     {
/* 105 */       if (other.pdxTypeId != null) {
/* 106 */         return false;
/*     */       }
/* 108 */     } else if (!this.pdxTypeId.equals(other.pdxTypeId))
/* 109 */       return false;
/* 110 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 116 */     return "RegionPdxTypeKey [gridPath=" + this.gridPath + ", pdxTypeId=" + this.pdxTypeId + "]";
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\data\verhist\RegionPdxTypeKey.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */