/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.data.temporal;
/*     */ 
/*     */ import com.gemstone.gemfire.pdx.PdxInstance;
/*     */ import com.gemstone.gemfire.pdx.PdxReader;
/*     */ import com.gemstone.gemfire.pdx.PdxSerializable;
/*     */ import com.gemstone.gemfire.pdx.PdxWriter;
/*     */ import com.jnj.adf.grid.data.temporal.utils.TemporalUtils;
/*     */ import java.util.List;
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
/*     */ public class TemporalValue
/*     */   implements PdxSerializable
/*     */ {
/*     */   private PdxInstance originValue;
/*     */   private long writtenTime;
/*     */   private long writtenTimeEnd;
/*     */   private long validFrom;
/*     */   private long validTo;
/*     */   private String userName;
/*     */   
/*     */   public TemporalValue() {}
/*     */   
/*     */   public TemporalValue(TemporalKey tkey, PdxInstance originValue)
/*     */   {
/*  47 */     this.originValue = originValue;
/*  48 */     this.validFrom = tkey.getValidFrom();
/*  49 */     this.validTo = tkey.getValidTo();
/*  50 */     this.writtenTime = tkey.getWrittenTime();
/*  51 */     this.writtenTimeEnd = tkey.getWrittenTimeEnd();
/*     */   }
/*     */   
/*     */   public boolean isValueChanged(TemporalValue value)
/*     */   {
/*  56 */     return value.getOriginValue().equals(this.originValue);
/*     */   }
/*     */   
/*     */   public List<String> getFieldNames()
/*     */   {
/*  61 */     return this.originValue.getFieldNames();
/*     */   }
/*     */   
/*     */   public <V> V getField(String fieldName)
/*     */   {
/*  66 */     return (V)this.originValue.getField(fieldName);
/*     */   }
/*     */   
/*     */   public PdxInstance getOriginValue()
/*     */   {
/*  71 */     return this.originValue;
/*     */   }
/*     */   
/*     */   public void setOriginValue(PdxInstance originValue)
/*     */   {
/*  76 */     this.originValue = originValue;
/*     */   }
/*     */   
/*     */   public long getWrittenTime()
/*     */   {
/*  81 */     return this.writtenTime;
/*     */   }
/*     */   
/*     */   public void setWrittenTime(long writtenTime)
/*     */   {
/*  86 */     this.writtenTime = writtenTime;
/*     */   }
/*     */   
/*     */   public long getWrittenTimeEnd()
/*     */   {
/*  91 */     return this.writtenTimeEnd;
/*     */   }
/*     */   
/*     */   public void setWrittenTimeEnd(long writtenTimeEnd)
/*     */   {
/*  96 */     this.writtenTimeEnd = writtenTimeEnd;
/*     */   }
/*     */   
/*     */   public void setValidFrom(long validFrom)
/*     */   {
/* 101 */     this.validFrom = validFrom;
/*     */   }
/*     */   
/*     */   public void setValidTo(long validTo)
/*     */   {
/* 106 */     this.validTo = validTo;
/*     */   }
/*     */   
/*     */   public long getValidFrom()
/*     */   {
/* 111 */     return this.validFrom;
/*     */   }
/*     */   
/*     */   public long getValidTo()
/*     */   {
/* 116 */     return this.validTo;
/*     */   }
/*     */   
/*     */   public String getValidFromStr()
/*     */   {
/* 121 */     return TemporalUtils.format(this.validFrom);
/*     */   }
/*     */   
/*     */   public String getValidToStr()
/*     */   {
/* 126 */     return TemporalUtils.format(this.validTo);
/*     */   }
/*     */   
/*     */   public String getWrittenTimeStr()
/*     */   {
/* 131 */     return TemporalUtils.formatTime(this.writtenTime);
/*     */   }
/*     */   
/*     */   public String getWrittenTimeEndStr()
/*     */   {
/* 136 */     return TemporalUtils.formatTime(this.writtenTimeEnd);
/*     */   }
/*     */   
/*     */   public String getUserName()
/*     */   {
/* 141 */     return this.userName;
/*     */   }
/*     */   
/*     */   public void setUserName(String userName)
/*     */   {
/* 146 */     this.userName = userName;
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
/*     */   public void toData(PdxWriter writer)
/*     */   {
/* 160 */     writer.writeObject("originValue", this.originValue);
/* 161 */     writer.writeLong("validFrom", this.validFrom);
/* 162 */     writer.writeLong("validTo", this.validTo);
/* 163 */     writer.writeLong("writtenTime", this.writtenTime);
/* 164 */     writer.writeLong("writtenTimeEnd", this.writtenTimeEnd);
/* 165 */     writer.writeString("userName", this.userName);
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
/*     */   public void fromData(PdxReader reader)
/*     */   {
/* 178 */     this.originValue = ((PdxInstance)reader.readObject("originValue"));
/* 179 */     this.validFrom = reader.readLong("validFrom");
/* 180 */     this.validTo = reader.readLong("validTo");
/* 181 */     this.writtenTime = reader.readLong("writtenTime");
/* 182 */     this.writtenTimeEnd = reader.readLong("writtenTimeEnd");
/* 183 */     this.userName = reader.readString("userName");
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\data\temporal\TemporalValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */