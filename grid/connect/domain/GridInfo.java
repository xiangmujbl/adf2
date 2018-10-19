/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.connect.domain;
/*     */ 
/*     */ import com.gemstone.gemfire.DataSerializable;
/*     */ import com.jnj.adf.grid.support.gemfire.DataSerializerExt;
/*     */ import com.jnj.adf.grid.support.system.ADFConfigHelper;
/*     */ import com.jnj.adf.grid.support.system.ADFConfigHelper.ITEMS;
/*     */ import com.jnj.adf.grid.utils.JsonUtils;
/*     */ import com.jnj.adf.grid.utils.LogUtil;
/*     */ import com.jnj.adf.grid.utils.RegexParse;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import java.util.List;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.apache.logging.log4j.Logger;
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
/*     */ public class GridInfo
/*     */   implements DataSerializable
/*     */ {
/*     */   private static final long serialVersionUID = 6197362632617444862L;
/*     */   private String gridName;
/*     */   private String wanArea;
/*     */   private String defaultGroup;
/*     */   private String locators;
/*     */   private boolean connected;
/*     */   private String jmxAddress;
/*     */   private int jmxPort;
/*     */   private int jmxManagerPort;
/*     */   private boolean isMaster;
/*     */   
/*     */   public GridInfo() {}
/*     */   
/*     */   public GridInfo(String gridName)
/*     */   {
/*  48 */     this.gridName = gridName;
/*     */   }
/*     */   
/*     */ 
/*     */   public GridInfo(String gridName, String locators)
/*     */   {
/*  54 */     this.gridName = gridName;
/*  55 */     this.locators = locators;
/*     */   }
/*     */   
/*     */ 
/*     */   public GridInfo(String gridName, String defaultGroup, String locators)
/*     */   {
/*  61 */     this.gridName = gridName;
/*  62 */     this.defaultGroup = defaultGroup;
/*  63 */     this.locators = locators;
/*     */   }
/*     */   
/*     */   public byte[] toBytes()
/*     */   {
/*  68 */     return JsonUtils.objectToBytes(this);
/*     */   }
/*     */   
/*     */   public boolean valid()
/*     */   {
/*  73 */     return true;
/*     */   }
/*     */   
/*     */   public String getGridName()
/*     */   {
/*  78 */     return this.gridName;
/*     */   }
/*     */   
/*     */   public void setGridName(String gridName)
/*     */   {
/*  83 */     this.gridName = gridName;
/*     */   }
/*     */   
/*     */   public String getLocators()
/*     */   {
/*  88 */     return this.locators;
/*     */   }
/*     */   
/*     */   public void setLocators(String locators)
/*     */   {
/*  93 */     this.locators = locators;
/*     */   }
/*     */   
/*     */   public int getJmxManagerPort()
/*     */   {
/*  98 */     return this.jmxManagerPort;
/*     */   }
/*     */   
/*     */   public void setJmxManagerPort(int jmxManagerPort)
/*     */   {
/* 103 */     this.jmxManagerPort = jmxManagerPort;
/*     */   }
/*     */   
/*     */   public String getWanArea()
/*     */   {
/* 108 */     return this.wanArea;
/*     */   }
/*     */   
/*     */   public void setWanArea(String wanArea)
/*     */   {
/* 113 */     this.wanArea = wanArea;
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 119 */     return "[gridName=" + this.gridName + ", defaultGroup=" + this.defaultGroup + ", locators=" + this.locators + ", connected=" + this.connected + ", jmxAddress=" + this.jmxAddress + ", jmxManagerPort=" + this.jmxManagerPort + ", isMaster=" + this.isMaster + ", wanArea=" + this.wanArea + "]";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isConnected()
/*     */   {
/* 126 */     return this.connected;
/*     */   }
/*     */   
/*     */   public void setConnected(boolean connected)
/*     */   {
/* 131 */     this.connected = connected;
/*     */   }
/*     */   
/*     */   public String getDefaultGroup()
/*     */   {
/* 136 */     return this.defaultGroup;
/*     */   }
/*     */   
/*     */   public void setDefaultGroup(String defaultGroup)
/*     */   {
/* 141 */     this.defaultGroup = defaultGroup;
/*     */   }
/*     */   
/*     */   public String getJmxAddress()
/*     */   {
/* 146 */     return this.jmxAddress;
/*     */   }
/*     */   
/*     */   public void setJmxAddress(String jmxAddress)
/*     */   {
/* 151 */     this.jmxAddress = jmxAddress;
/*     */   }
/*     */   
/*     */   public List<IPAddress> locatorAddress()
/*     */   {
/* 156 */     return RegexParse.parseAddress(this.locators);
/*     */   }
/*     */   
/*     */   public boolean isMaster()
/*     */   {
/* 161 */     return this.isMaster;
/*     */   }
/*     */   
/*     */   public void setMaster(boolean isMaster)
/*     */   {
/* 166 */     this.isMaster = isMaster;
/*     */   }
/*     */   
/*     */   public boolean isLogicMasterGrid()
/*     */   {
/* 171 */     LogUtil.getCoreLog().debug("naming.server={}", new Object[] { ADFConfigHelper.getProperty(ITEMS.NAMING_SERVER) });
/* 172 */     LogUtil.getCoreLog().debug("master.grid.name={}", new Object[] {
/* 173 */       ADFConfigHelper.getProperty(ITEMS.MASTER_GRID_NAME) });
/* 174 */     return (this.isMaster) || 
/* 175 */       ((StringUtils.isNotEmpty(this.gridName)) && 
/* 176 */       (this.gridName.equals(ADFConfigHelper.getProperty(ITEMS.MASTER_GRID_NAME)))) || (
/* 177 */       (StringUtils.isNotEmpty(this.locators)) && 
/* 178 */       (this.locators.equals(ADFConfigHelper.getProperty(ITEMS.NAMING_SERVER))));
/*     */   }
/*     */   
/*     */   public int getJmxPort()
/*     */   {
/* 183 */     return this.jmxPort;
/*     */   }
/*     */   
/*     */   public void setJmxPort(int jmxPort)
/*     */   {
/* 188 */     this.jmxPort = jmxPort;
/*     */   }
/*     */   
/*     */   public void toData(DataOutput out)
/*     */     throws IOException
/*     */   {
/* 194 */     DataSerializerExt.toData(getClass(), this, out);
/*     */   }
/*     */   
/*     */   public void fromData(DataInput in)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/* 200 */     DataSerializerExt.fromData(getClass(), this, in);
/*     */   }
/*     */   
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 206 */     int prime = 31;
/* 207 */     int result = 1;
/* 208 */     result = 31 * result + (this.connected ? 1231 : 1237);
/* 209 */     result = 31 * result + (this.defaultGroup == null ? 0 : this.defaultGroup.hashCode());
/* 210 */     result = 31 * result + (this.gridName == null ? 0 : this.gridName.hashCode());
/* 211 */     result = 31 * result + (this.isMaster ? 1231 : 1237);
/* 212 */     result = 31 * result + (this.jmxAddress == null ? 0 : this.jmxAddress.hashCode());
/* 213 */     result = 31 * result + this.jmxManagerPort;
/* 214 */     result = 31 * result + this.jmxPort;
/* 215 */     result = 31 * result + (this.locators == null ? 0 : this.locators.hashCode());
/* 216 */     result = 31 * result + (this.wanArea == null ? 0 : this.wanArea.hashCode());
/* 217 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 223 */     if (this == obj)
/* 224 */       return true;
/* 225 */     if (obj == null)
/* 226 */       return false;
/* 227 */     if (getClass() != obj.getClass())
/* 228 */       return false;
/* 229 */     GridInfo other = (GridInfo)obj;
/* 230 */     if (this.connected != other.connected)
/* 231 */       return false;
/* 232 */     if (this.defaultGroup == null)
/*     */     {
/* 234 */       if (other.defaultGroup != null) {
/* 235 */         return false;
/*     */       }
/* 237 */     } else if (!this.defaultGroup.equals(other.defaultGroup))
/* 238 */       return false;
/* 239 */     if (this.gridName == null)
/*     */     {
/* 241 */       if (other.gridName != null) {
/* 242 */         return false;
/*     */       }
/* 244 */     } else if (!this.gridName.equals(other.gridName))
/* 245 */       return false;
/* 246 */     if (this.isMaster != other.isMaster)
/* 247 */       return false;
/* 248 */     if (this.jmxAddress == null)
/*     */     {
/* 250 */       if (other.jmxAddress != null) {
/* 251 */         return false;
/*     */       }
/* 253 */     } else if (!this.jmxAddress.equals(other.jmxAddress))
/* 254 */       return false;
/* 255 */     if (this.jmxManagerPort != other.jmxManagerPort)
/* 256 */       return false;
/* 257 */     if (this.jmxPort != other.jmxPort)
/* 258 */       return false;
/* 259 */     if (this.locators == null)
/*     */     {
/* 261 */       if (other.locators != null) {
/* 262 */         return false;
/*     */       }
/* 264 */     } else if (!this.locators.equals(other.locators)) {
/* 265 */       return false;
/*     */     }
/* 267 */     if (this.wanArea == null)
/*     */     {
/* 269 */       if (other.wanArea != null) {
/* 270 */         return false;
/*     */       }
/* 272 */     } else if (!this.wanArea.equals(other.wanArea)) {
/* 273 */       return false;
/*     */     }
/* 275 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\connect\domain\GridInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */