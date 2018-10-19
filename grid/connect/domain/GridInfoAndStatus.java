/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.connect.domain;
/*     */ 
/*     */ import com.gemstone.gemfire.DataSerializable;
/*     */ import com.gemstone.gemfire.DataSerializer;
/*     */ import com.jnj.adf.grid.support.gemfire.DataSerializerExt;
/*     */ import com.jnj.adf.grid.utils.VersionUtils;
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
/*     */ public class GridInfoAndStatus
/*     */   implements DataSerializable
/*     */ {
/*     */   private static final long serialVersionUID = -2736386377937764215L;
/*     */   private GridInfo info;
/*     */   private GridStatus status;
/*     */   
/*     */   public GridStatus getStatus()
/*     */   {
/*  27 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setStatus(GridStatus status)
/*     */   {
/*  32 */     this.status = status;
/*     */   }
/*     */   
/*     */   public GridInfo getInfo()
/*     */   {
/*  37 */     return this.info;
/*     */   }
/*     */   
/*     */   public void setInfo(GridInfo info)
/*     */   {
/*  42 */     this.info = info;
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/*  48 */     return "GridInfoAndStatus [info=" + this.info + ", status=" + this.status + "]";
/*     */   }
/*     */   
/*     */   public void toData(DataOutput out)
/*     */     throws IOException
/*     */   {
/*  54 */     String version = VersionUtils.getVersion();
/*  55 */     if (VersionUtils.below_v_0_3_2(version))
/*     */     {
/*  57 */       DataSerializer.writeObject(this.info, out);
/*  58 */       DataSerializer.writeEnum(this.status, out);
/*     */     }
/*     */     else {
/*  61 */       DataSerializerExt.toData(getClass(), this, out);
/*     */     }
/*     */   }
/*     */   
/*     */   public void fromData(DataInput in)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/*  68 */     String version = VersionUtils.getVersion();
/*  69 */     if (VersionUtils.below_v_0_3_2(version))
/*     */     {
/*  71 */       this.info = ((GridInfo)DataSerializer.readObject(in));
/*  72 */       this.status = ((GridStatus)DataSerializer.readEnum(GridStatus.class, in));
/*     */     }
/*     */     else {
/*  75 */       DataSerializerExt.fromData(getClass(), this, in);
/*     */     }
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/*  81 */     int prime = 31;
/*  82 */     int result = 1;
/*  83 */     result = 31 * result + (this.info == null ? 0 : this.info.hashCode());
/*  84 */     result = 31 * result + (this.status == null ? 0 : this.status.hashCode());
/*  85 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/*  91 */     if (this == obj)
/*  92 */       return true;
/*  93 */     if (obj == null)
/*  94 */       return false;
/*  95 */     if (getClass() != obj.getClass())
/*  96 */       return false;
/*  97 */     GridInfoAndStatus other = (GridInfoAndStatus)obj;
/*  98 */     if (this.info == null)
/*     */     {
/* 100 */       if (other.info != null) {
/* 101 */         return false;
/*     */       }
/* 103 */     } else if (!this.info.equals(other.info))
/* 104 */       return false;
/* 105 */     if (this.status != other.status)
/* 106 */       return false;
/* 107 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\connect\domain\GridInfoAndStatus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */