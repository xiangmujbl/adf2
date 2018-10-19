/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.data.temporal;
/*     */ 
/*     */ import com.gemstone.gemfire.DataSerializable;
/*     */ import com.gemstone.gemfire.DataSerializer;
/*     */ import com.jnj.adf.grid.data.temporal.utils.TemporalUtils;
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
/*     */ public class TemporalKey
/*     */   implements ITemporalKey<String>, DataSerializable
/*     */ {
/*     */   private static final long serialVersionUID = 8299573104957848911L;
/*     */   private String uuid;
/*     */   private long writtenTime;
/*     */   private long writtenTimeEnd;
/*     */   private long validFrom;
/*     */   private long validTo;
/*     */   
/*     */   public TemporalKey()
/*     */   {
/*  51 */     this.writtenTimeEnd = TemporalUtils.max();
/*     */   }
/*     */   
/*     */   public long getValidFrom()
/*     */   {
/*  56 */     return this.validFrom;
/*     */   }
/*     */   
/*     */   public void setValidFrom(long validFrom)
/*     */   {
/*  61 */     this.validFrom = validFrom;
/*     */   }
/*     */   
/*     */   public String getValidFromStr()
/*     */   {
/*  66 */     return TemporalUtils.format(getValidFrom());
/*     */   }
/*     */   
/*     */   public long getValidTo()
/*     */   {
/*  71 */     return this.validTo;
/*     */   }
/*     */   
/*     */   public void setValidTo(long validTo)
/*     */   {
/*  76 */     this.validTo = validTo;
/*     */   }
/*     */   
/*     */   public String getValidToStr()
/*     */   {
/*  81 */     return TemporalUtils.format(getValidTo());
/*     */   }
/*     */   
/*     */   public void setWrittenTime(long writtenTime)
/*     */   {
/*  86 */     this.writtenTime = writtenTime;
/*     */   }
/*     */   
/*     */   public String getWrittenTimeStr()
/*     */   {
/*  91 */     return TemporalUtils.formatTime(getWrittenTime());
/*     */   }
/*     */   
/*     */   public long getWrittenTime()
/*     */   {
/*  96 */     return this.writtenTime;
/*     */   }
/*     */   
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 102 */     int result = this.uuid == null ? 0 : this.uuid.hashCode();
/* 103 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 109 */     if (this == obj)
/* 110 */       return true;
/* 111 */     if (obj == null)
/* 112 */       return false;
/* 113 */     if (getClass() != obj.getClass())
/* 114 */       return false;
/* 115 */     TemporalKey other = (TemporalKey)obj;
/* 116 */     if (this.uuid == null)
/*     */     {
/* 118 */       if (other.uuid != null) {
/* 119 */         return false;
/*     */       }
/* 121 */     } else if (!this.uuid.equals(other.uuid))
/* 122 */       return false;
/* 123 */     if (this.validFrom != other.validFrom)
/* 124 */       return false;
/* 125 */     if (this.validTo != other.validTo)
/* 126 */       return false;
/* 127 */     if (this.writtenTime != other.writtenTime)
/* 128 */       return false;
/* 129 */     return true;
/*     */   }
/*     */   
/*     */   public String getWrittenTimeEndStr()
/*     */   {
/* 134 */     return TemporalUtils.formatTime(getWrittenTimeEnd());
/*     */   }
/*     */   
/*     */   public long getWrittenTimeEnd()
/*     */   {
/* 139 */     return this.writtenTimeEnd;
/*     */   }
/*     */   
/*     */   public void setWrittenTimeEnd(long writtenTimeEnd)
/*     */   {
/* 144 */     this.writtenTimeEnd = writtenTimeEnd;
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 150 */     return 
/*     */     
/* 152 */       "TemporalKey [uuid=" + this.uuid + ", validFrom=" + getValidFromStr() + ", validTo=" + getValidToStr() + ", writtenTime=" + TemporalUtils.formatTime(this.writtenTime) + ", writtenTimeEnd=" + TemporalUtils.formatTime(this.writtenTimeEnd) + "]";
/*     */   }
/*     */   
/*     */   public String getUuid()
/*     */   {
/* 157 */     return this.uuid;
/*     */   }
/*     */   
/*     */   public void setUuid(String uuid)
/*     */   {
/* 162 */     this.uuid = uuid;
/*     */   }
/*     */   
/*     */   public void toData(DataOutput out)
/*     */     throws IOException
/*     */   {
/* 168 */     String version = VersionUtils.getVersion();
/* 169 */     if (VersionUtils.below_v_0_3_2(version))
/*     */     {
/* 171 */       DataSerializer.writeString(this.uuid, out);
/* 172 */       DataSerializer.writePrimitiveLong(this.validFrom, out);
/* 173 */       DataSerializer.writePrimitiveLong(this.validTo, out);
/* 174 */       DataSerializer.writePrimitiveLong(this.writtenTime, out);
/* 175 */       DataSerializer.writePrimitiveLong(this.writtenTimeEnd, out);
/*     */     }
/*     */     else {
/* 178 */       DataSerializerExt.toData(getClass(), this, out);
/*     */     }
/*     */   }
/*     */   
/*     */   public void fromData(DataInput in) throws IOException, ClassNotFoundException
/*     */   {
/* 184 */     String version = VersionUtils.getVersion();
/* 185 */     if (VersionUtils.below_v_0_3_2(version))
/*     */     {
/* 187 */       this.uuid = DataSerializer.readString(in);
/* 188 */       this.validFrom = DataSerializer.readPrimitiveLong(in);
/* 189 */       this.validTo = DataSerializer.readPrimitiveLong(in);
/* 190 */       this.writtenTime = DataSerializer.readPrimitiveLong(in);
/* 191 */       this.writtenTimeEnd = DataSerializer.readPrimitiveLong(in);
/*     */     }
/*     */     else {
/* 194 */       DataSerializerExt.fromData(getClass(), this, in);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\data\temporal\TemporalKey.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */