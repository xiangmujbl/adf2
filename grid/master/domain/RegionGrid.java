/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.master.domain;
/*     */ 
/*     */ import com.gemstone.gemfire.DataSerializable;
/*     */ import com.jnj.adf.grid.connect.domain.GridStatus;
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
/*     */ public class RegionGrid
/*     */   implements DataSerializable
/*     */ {
/*     */   private static final long serialVersionUID = -8024238696839317334L;
/*     */   private String gridId;
/*     */   private String defaultGroup;
/*     */   private String locators;
/*     */   private String jmxAddress;
/*     */   private int jmxPort;
/*     */   private int jmxManagerPort;
/*     */   private boolean isMaster;
/*  26 */   private Long createTime = Long.valueOf(System.currentTimeMillis());
/*     */   
/*     */   private String wanArea;
/*     */   private GridStatus status;
/*     */   private String description;
/*     */   
/*     */   public boolean isMaster()
/*     */   {
/*  34 */     return this.isMaster;
/*     */   }
/*     */   
/*     */   public void setMaster(boolean isMaster)
/*     */   {
/*  39 */     this.isMaster = isMaster;
/*     */   }
/*     */   
/*     */   public GridStatus getStatus()
/*     */   {
/*  44 */     return this.status;
/*     */   }
/*     */   
/*     */   public String getStatusName()
/*     */   {
/*  49 */     return this.status != null ? this.status.name() : "";
/*     */   }
/*     */   
/*     */   public void setStatus(GridStatus status)
/*     */   {
/*  54 */     this.status = status;
/*     */   }
/*     */   
/*     */   public String getDescription()
/*     */   {
/*  59 */     return this.description;
/*     */   }
/*     */   
/*     */   public void setDescription(String description)
/*     */   {
/*  64 */     this.description = description;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getGridId()
/*     */   {
/*  73 */     return this.gridId;
/*     */   }
/*     */   
/*     */   public void setGridId(String gridId)
/*     */   {
/*  78 */     this.gridId = gridId;
/*     */   }
/*     */   
/*     */   public Long getCreateTime()
/*     */   {
/*  83 */     return this.createTime;
/*     */   }
/*     */   
/*     */   public String getCreateTimeStr() {
/*  87 */     return DateUtil.format(new Date(this.createTime.longValue()), "yyyy/MM/dd HH:mm:ss.SSS");
/*     */   }
/*     */   
/*     */   public void setCreateTime(Long createTime)
/*     */   {
/*  92 */     this.createTime = createTime;
/*     */   }
/*     */   
/*     */   public String getDefaultGroup()
/*     */   {
/*  97 */     return this.defaultGroup;
/*     */   }
/*     */   
/*     */   public void setDefaultGroup(String defaultGroup)
/*     */   {
/* 102 */     this.defaultGroup = defaultGroup;
/*     */   }
/*     */   
/*     */   public String getLocators()
/*     */   {
/* 107 */     return this.locators;
/*     */   }
/*     */   
/*     */   public void setLocators(String locators)
/*     */   {
/* 112 */     this.locators = locators;
/*     */   }
/*     */   
/*     */   public String getJmxAddress()
/*     */   {
/* 117 */     return this.jmxAddress;
/*     */   }
/*     */   
/*     */   public void setJmxAddress(String jmxAddress)
/*     */   {
/* 122 */     this.jmxAddress = jmxAddress;
/*     */   }
/*     */   
/*     */   public int getJmxPort()
/*     */   {
/* 127 */     return this.jmxPort;
/*     */   }
/*     */   
/*     */   public void setJmxPort(int jmxPort)
/*     */   {
/* 132 */     this.jmxPort = jmxPort;
/*     */   }
/*     */   
/*     */   public int getJmxManagerPort()
/*     */   {
/* 137 */     return this.jmxManagerPort;
/*     */   }
/*     */   
/*     */   public void setJmxManagerPort(int jmxManagerPort)
/*     */   {
/* 142 */     this.jmxManagerPort = jmxManagerPort;
/*     */   }
/*     */   
/*     */   public String genarateKey()
/*     */   {
/* 147 */     return this.gridId;
/*     */   }
/*     */   
/*     */   public String getWanArea() {
/* 151 */     return this.wanArea;
/*     */   }
/*     */   
/*     */   public void setWanArea(String wanArea) {
/* 155 */     this.wanArea = wanArea;
/*     */   }
/*     */   
/*     */   public void toData(DataOutput out)
/*     */     throws IOException
/*     */   {
/* 161 */     DataSerializerExt.toData(getClass(), this, out);
/*     */   }
/*     */   
/*     */   public void fromData(DataInput in)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/* 167 */     DataSerializerExt.fromData(getClass(), this, in);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 174 */     int prime = 31;
/* 175 */     int result = 1;
/* 176 */     result = 31 * result + (this.createTime == null ? 0 : this.createTime.hashCode());
/* 177 */     result = 31 * result + (this.defaultGroup == null ? 0 : this.defaultGroup.hashCode());
/* 178 */     result = 31 * result + (this.description == null ? 0 : this.description.hashCode());
/* 179 */     result = 31 * result + (this.gridId == null ? 0 : this.gridId.hashCode());
/* 180 */     result = 31 * result + (this.jmxAddress == null ? 0 : this.jmxAddress.hashCode());
/* 181 */     result = 31 * result + this.jmxManagerPort;
/* 182 */     result = 31 * result + this.jmxPort;
/* 183 */     result = 31 * result + (this.locators == null ? 0 : this.locators.hashCode());
/* 184 */     result = 31 * result + (this.status == null ? 0 : this.status.hashCode());
/* 185 */     result = 31 * result + (this.wanArea == null ? 0 : this.wanArea.hashCode());
/* 186 */     result = 31 * result + String.valueOf(this.isMaster).hashCode();
/* 187 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 193 */     if (this == obj)
/* 194 */       return true;
/* 195 */     if (obj == null)
/* 196 */       return false;
/* 197 */     if (getClass() != obj.getClass())
/* 198 */       return false;
/* 199 */     RegionGrid other = (RegionGrid)obj;
/* 200 */     if (this.createTime == null)
/*     */     {
/* 202 */       if (other.createTime != null) {
/* 203 */         return false;
/*     */       }
/* 205 */     } else if (!this.createTime.equals(other.createTime))
/* 206 */       return false;
/* 207 */     if (this.defaultGroup == null)
/*     */     {
/* 209 */       if (other.defaultGroup != null) {
/* 210 */         return false;
/*     */       }
/* 212 */     } else if (!this.defaultGroup.equals(other.defaultGroup))
/* 213 */       return false;
/* 214 */     if (this.description == null)
/*     */     {
/* 216 */       if (other.description != null) {
/* 217 */         return false;
/*     */       }
/* 219 */     } else if (!this.description.equals(other.description))
/* 220 */       return false;
/* 221 */     if (this.gridId == null)
/*     */     {
/* 223 */       if (other.gridId != null) {
/* 224 */         return false;
/*     */       }
/* 226 */     } else if (!this.gridId.equals(other.gridId))
/* 227 */       return false;
/* 228 */     if (this.jmxAddress == null)
/*     */     {
/* 230 */       if (other.jmxAddress != null) {
/* 231 */         return false;
/*     */       }
/* 233 */     } else if (!this.jmxAddress.equals(other.jmxAddress))
/* 234 */       return false;
/* 235 */     if (this.jmxManagerPort != other.jmxManagerPort)
/* 236 */       return false;
/* 237 */     if (this.jmxPort != other.jmxPort)
/* 238 */       return false;
/* 239 */     if (this.locators == null)
/*     */     {
/* 241 */       if (other.locators != null) {
/* 242 */         return false;
/*     */       }
/* 244 */     } else if (!this.locators.equals(other.locators))
/* 245 */       return false;
/* 246 */     if (this.status != other.status)
/* 247 */       return false;
/* 248 */     if (this.isMaster != other.isMaster) {
/* 249 */       return false;
/*     */     }
/*     */     
/* 252 */     if (this.wanArea == null)
/*     */     {
/* 254 */       if (other.wanArea != null) {
/* 255 */         return false;
/*     */       }
/* 257 */     } else if (!this.wanArea.equals(other.wanArea)) {
/* 258 */       return false;
/*     */     }
/* 260 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 266 */     StringBuilder builder = new StringBuilder();
/* 267 */     builder.append("RegionGrid [gridId=");
/* 268 */     builder.append(this.gridId);
/* 269 */     builder.append(", defaultGroup=");
/* 270 */     builder.append(this.defaultGroup);
/* 271 */     builder.append(", locators=");
/* 272 */     builder.append(this.locators);
/* 273 */     builder.append(", jmxAddress=");
/* 274 */     builder.append(this.jmxAddress);
/* 275 */     builder.append(", jmxPort=");
/* 276 */     builder.append(this.jmxPort);
/* 277 */     builder.append(", jmxManagerPort=");
/* 278 */     builder.append(this.jmxManagerPort);
/* 279 */     builder.append(", createTime=");
/* 280 */     builder.append(this.createTime);
/* 281 */     builder.append(", status=");
/* 282 */     builder.append(this.status);
/* 283 */     builder.append(", isMaster=");
/* 284 */     builder.append(this.isMaster);
/* 285 */     builder.append(", description=");
/* 286 */     builder.append(this.description);
/* 287 */     builder.append(", wanArea=");
/* 288 */     builder.append(this.wanArea);
/* 289 */     builder.append("]");
/* 290 */     return builder.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\master\domain\RegionGrid.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */