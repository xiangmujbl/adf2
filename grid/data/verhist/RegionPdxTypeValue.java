/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.data.verhist;
/*     */ 
/*     */ import com.gemstone.gemfire.DataSerializable;
/*     */ import com.gemstone.gemfire.DataSerializer;
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
/*     */ 
/*     */ 
/*     */ public class RegionPdxTypeValue
/*     */   implements DataSerializable
/*     */ {
/*     */   private static final long serialVersionUID = 7712574732516235099L;
/*     */   private String gridPath;
/*     */   private Integer pdxTypeId;
/*     */   
/*     */   public RegionPdxTypeValue() {}
/*     */   
/*     */   public RegionPdxTypeValue(String gridPath, Integer pdxTypeId)
/*     */   {
/*  40 */     this.gridPath = gridPath;
/*  41 */     this.pdxTypeId = pdxTypeId;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void fromData(DataInput in)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/*  51 */     this.gridPath = DataSerializer.readString(in);
/*  52 */     this.pdxTypeId = DataSerializer.readInteger(in);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void toData(DataOutput out)
/*     */     throws IOException
/*     */   {
/*  63 */     DataSerializer.writeString(this.gridPath, out);
/*  64 */     DataSerializer.writeInteger(this.pdxTypeId, out);
/*     */   }
/*     */   
/*     */ 
/*     */   public String getGridPath()
/*     */   {
/*  70 */     return this.gridPath;
/*     */   }
/*     */   
/*     */   public void setGridPath(String gridPath)
/*     */   {
/*  75 */     this.gridPath = gridPath;
/*     */   }
/*     */   
/*     */   public Integer getPdxTypeId()
/*     */   {
/*  80 */     return this.pdxTypeId;
/*     */   }
/*     */   
/*     */   public void setPdxTypeId(Integer pdxTypeId)
/*     */   {
/*  85 */     this.pdxTypeId = pdxTypeId;
/*     */   }
/*     */   
/*     */ 
/*     */   public int hashCode()
/*     */   {
/*  91 */     int prime = 31;
/*  92 */     int result = 1;
/*  93 */     result = 31 * result + (this.gridPath == null ? 0 : this.gridPath.hashCode());
/*  94 */     result = 31 * result + (this.pdxTypeId == null ? 0 : this.pdxTypeId.hashCode());
/*  95 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 101 */     if (this == obj)
/* 102 */       return true;
/* 103 */     if (obj == null)
/* 104 */       return false;
/* 105 */     if (getClass() != obj.getClass())
/* 106 */       return false;
/* 107 */     RegionPdxTypeValue other = (RegionPdxTypeValue)obj;
/* 108 */     if (this.gridPath == null)
/*     */     {
/* 110 */       if (other.gridPath != null) {
/* 111 */         return false;
/*     */       }
/* 113 */     } else if (!this.gridPath.equals(other.gridPath))
/* 114 */       return false;
/* 115 */     if (this.pdxTypeId == null)
/*     */     {
/* 117 */       if (other.pdxTypeId != null) {
/* 118 */         return false;
/*     */       }
/* 120 */     } else if (!this.pdxTypeId.equals(other.pdxTypeId))
/* 121 */       return false;
/* 122 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 128 */     return "RegionPdxTypeValue [gridPath=" + this.gridPath + ", pdxTypeId=" + this.pdxTypeId + "]";
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\data\verhist\RegionPdxTypeValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */