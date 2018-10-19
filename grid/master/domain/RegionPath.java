/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.master.domain;
/*     */ 
/*     */ import com.gemstone.gemfire.DataSerializable;
/*     */ import com.jnj.adf.client.api.JsonObject;
/*     */ import com.jnj.adf.grid.support.gemfire.DataSerializerExt;
/*     */ import com.jnj.adf.grid.utils.DateUtil;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import java.util.Date;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RegionPath
/*     */   implements DataSerializable
/*     */ {
/*     */   private static final long serialVersionUID = 7187615376144030500L;
/*     */   private String gridId;
/*     */   private String regionPath;
/*  21 */   private Long createTime = Long.valueOf(System.currentTimeMillis());
/*     */   
/*  23 */   private ActiveStatus status = ActiveStatus.ACTIVE;
/*  24 */   private ValidStatus valid = ValidStatus.VALID;
/*     */   
/*     */   private String description;
/*     */   
/*     */   private String sources;
/*     */   
/*     */   public String getGridId()
/*     */   {
/*  32 */     return this.gridId;
/*     */   }
/*     */   
/*     */   public void setGridId(String gridId)
/*     */   {
/*  37 */     this.gridId = gridId;
/*     */   }
/*     */   
/*     */   public String getRegionPath()
/*     */   {
/*  42 */     return this.regionPath;
/*     */   }
/*     */   
/*     */   public void setRegionPath(String regionPath)
/*     */   {
/*  47 */     this.regionPath = regionPath;
/*     */   }
/*     */   
/*     */   public ActiveStatus getStatus()
/*     */   {
/*  52 */     return this.status;
/*     */   }
/*     */   
/*     */   public String getActiveStatusName()
/*     */   {
/*  57 */     return this.status != null ? this.status.name() : "";
/*     */   }
/*     */   
/*     */   public void setStatus(ActiveStatus status)
/*     */   {
/*  62 */     this.status = status;
/*     */   }
/*     */   
/*     */   public String getDescription()
/*     */   {
/*  67 */     return this.description;
/*     */   }
/*     */   
/*     */   public void setDescription(String description)
/*     */   {
/*  72 */     this.description = description;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ValidStatus getValid()
/*     */   {
/*  81 */     return this.valid;
/*     */   }
/*     */   
/*     */   public String getValidName()
/*     */   {
/*  86 */     return this.valid != null ? this.valid.name() : "";
/*     */   }
/*     */   
/*     */   public void setValid(ValidStatus valid)
/*     */   {
/*  91 */     this.valid = valid;
/*     */   }
/*     */   
/*     */   public Long getCreateTime()
/*     */   {
/*  96 */     return this.createTime;
/*     */   }
/*     */   
/*     */   public String getCreateTimeStr()
/*     */   {
/* 101 */     return DateUtil.format(new Date(this.createTime.longValue()), "yyyy/MM/dd HH:mm:ss.SSS");
/*     */   }
/*     */   
/*     */   public void setCreateTime(Long createTime)
/*     */   {
/* 106 */     this.createTime = createTime;
/*     */   }
/*     */   
/*     */   public String getSources() {
/* 110 */     return this.sources;
/*     */   }
/*     */   
/*     */   public void setSources(String sources) {
/* 114 */     this.sources = sources;
/*     */   }
/*     */   
/*     */   public String genarateKey()
/*     */   {
/* 119 */     if ((this.gridId != null) && (this.regionPath != null))
/*     */     {
/* 121 */       JsonObject jkey = JsonObject.create();
/* 122 */       jkey.append("gridId", this.gridId);
/* 123 */       jkey.append("regionPath", this.regionPath);
/*     */       
/* 125 */       return jkey.toJson();
/*     */     }
/*     */     
/* 128 */     return null;
/*     */   }
/*     */   
/*     */   public void toData(DataOutput out)
/*     */     throws IOException
/*     */   {
/* 134 */     DataSerializerExt.toData(getClass(), this, out);
/*     */   }
/*     */   
/*     */   public void fromData(DataInput in)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/* 140 */     DataSerializerExt.fromData(getClass(), this, in);
/*     */   }
/*     */   
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 146 */     int prime = 31;
/* 147 */     int result = 1;
/* 148 */     result = 31 * result + (this.createTime == null ? 0 : this.createTime.hashCode());
/* 149 */     result = 31 * result + (this.description == null ? 0 : this.description.hashCode());
/* 150 */     result = 31 * result + (this.gridId == null ? 0 : this.gridId.hashCode());
/* 151 */     result = 31 * result + (this.regionPath == null ? 0 : this.regionPath.hashCode());
/* 152 */     result = 31 * result + (this.status == null ? 0 : this.status.hashCode());
/* 153 */     result = 31 * result + (this.valid == null ? 0 : this.valid.hashCode());
/* 154 */     result = 31 * result + (this.sources == null ? 0 : this.sources.hashCode());
/* 155 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 161 */     if (this == obj)
/* 162 */       return true;
/* 163 */     if (obj == null)
/* 164 */       return false;
/* 165 */     if (getClass() != obj.getClass())
/* 166 */       return false;
/* 167 */     RegionPath other = (RegionPath)obj;
/* 168 */     if (this.createTime == null)
/*     */     {
/* 170 */       if (other.createTime != null) {
/* 171 */         return false;
/*     */       }
/* 173 */     } else if (!this.createTime.equals(other.createTime))
/* 174 */       return false;
/* 175 */     if (this.description == null)
/*     */     {
/* 177 */       if (other.description != null) {
/* 178 */         return false;
/*     */       }
/* 180 */     } else if (!this.description.equals(other.description))
/* 181 */       return false;
/* 182 */     if (this.gridId == null)
/*     */     {
/* 184 */       if (other.gridId != null) {
/* 185 */         return false;
/*     */       }
/* 187 */     } else if (!this.gridId.equals(other.gridId))
/* 188 */       return false;
/* 189 */     if (this.regionPath == null)
/*     */     {
/* 191 */       if (other.regionPath != null) {
/* 192 */         return false;
/*     */       }
/* 194 */     } else if (!this.regionPath.equals(other.regionPath))
/* 195 */       return false;
/* 196 */     if (this.status != other.status)
/* 197 */       return false;
/* 198 */     if (this.valid != other.valid) {
/* 199 */       return false;
/*     */     }
/* 201 */     if (this.sources == null)
/*     */     {
/* 203 */       if (other.sources != null) {
/* 204 */         return false;
/*     */       }
/* 206 */     } else if (!this.sources.equals(other.sources)) {
/* 207 */       return false;
/*     */     }
/* 209 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\master\domain\RegionPath.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */