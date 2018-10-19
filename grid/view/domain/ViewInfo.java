/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.view.domain;
/*     */ 
/*     */ import com.gemstone.gemfire.DataSerializable;
/*     */ import com.jnj.adf.grid.support.gemfire.DataSerializerExt;
/*     */ import com.jnj.adf.grid.utils.JsonUtils;
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
/*     */ public final class ViewInfo
/*     */   implements DataSerializable
/*     */ {
/*     */   private static final long serialVersionUID = 7190502173485650605L;
/*     */   private String viewPath;
/*     */   private String desc;
/*     */   private String status;
/*     */   private String type;
/*     */   private String level;
/*     */   private String securityMode;
/*  38 */   private boolean loadData = true;
/*     */   
/*     */   public String getViewPath() {
/*  41 */     return this.viewPath;
/*     */   }
/*     */   
/*     */   public void setViewPath(String viewPath) {
/*  45 */     this.viewPath = viewPath;
/*     */   }
/*     */   
/*     */   public String getDesc() {
/*  49 */     return this.desc;
/*     */   }
/*     */   
/*     */   public void setDesc(String desc) {
/*  53 */     this.desc = desc;
/*     */   }
/*     */   
/*     */   public String getStatus() {
/*  57 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setStatus(String status) {
/*  61 */     this.status = status;
/*     */   }
/*     */   
/*     */   public boolean isLoadData() {
/*  65 */     return this.loadData;
/*     */   }
/*     */   
/*     */   public void setLoadData(boolean loadData) {
/*  69 */     this.loadData = loadData;
/*     */   }
/*     */   
/*     */   public void toData(DataOutput out) throws IOException
/*     */   {
/*  74 */     DataSerializerExt.toData(getClass(), this, out);
/*     */   }
/*     */   
/*     */   public void fromData(DataInput in) throws IOException
/*     */   {
/*  79 */     DataSerializerExt.fromData(getClass(), this, in);
/*     */   }
/*     */   
/*     */ 
/*     */   public int hashCode()
/*     */   {
/*  85 */     int prime = 31;
/*  86 */     int result = 1;
/*  87 */     result = 31 * result + (this.viewPath == null ? 0 : this.viewPath.hashCode());
/*  88 */     result = 31 * result + (this.desc == null ? 0 : this.desc.hashCode());
/*  89 */     result = 31 * result + (this.status == null ? 0 : this.status.hashCode());
/*  90 */     result = 31 * result + (this.loadData ? 1231 : 1237);
/*  91 */     result = 31 * result + (this.type == null ? 0 : this.type.hashCode());
/*  92 */     result = 31 * result + (this.level == null ? 0 : this.level.hashCode());
/*  93 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/*  99 */     if (this == obj)
/* 100 */       return true;
/* 101 */     if (obj == null)
/* 102 */       return false;
/* 103 */     if (getClass() != obj.getClass())
/* 104 */       return false;
/* 105 */     ViewInfo other = (ViewInfo)obj;
/* 106 */     if (this.viewPath == null)
/*     */     {
/* 108 */       if (other.viewPath != null) {
/* 109 */         return false;
/*     */       }
/* 111 */     } else if (!this.viewPath.equals(other.viewPath)) {
/* 112 */       return false;
/*     */     }
/* 114 */     if (this.desc == null)
/*     */     {
/* 116 */       if (other.desc != null) {
/* 117 */         return false;
/*     */       }
/* 119 */     } else if (!this.desc.equals(other.desc)) {
/* 120 */       return false;
/*     */     }
/* 122 */     if (this.status == null)
/*     */     {
/* 124 */       if (other.status != null) {
/* 125 */         return false;
/*     */       }
/* 127 */     } else if (!this.status.equals(other.status)) {
/* 128 */       return false;
/*     */     }
/* 130 */     if (this.loadData != other.loadData) {
/* 131 */       return false;
/*     */     }
/* 133 */     if (this.type == null)
/*     */     {
/* 135 */       if (other.type != null) {
/* 136 */         return false;
/*     */       }
/* 138 */     } else if (!this.type.equals(other.type)) {
/* 139 */       return false;
/*     */     }
/* 141 */     if (this.level == null)
/*     */     {
/* 143 */       if (other.level != null) {
/* 144 */         return false;
/*     */       }
/* 146 */     } else if (!this.level.equals(other.level))
/* 147 */       return false;
/* 148 */     return true;
/*     */   }
/*     */   
/*     */   public String getType() {
/* 152 */     return this.type;
/*     */   }
/*     */   
/*     */   public void setType(String type) {
/* 156 */     this.type = type;
/*     */   }
/*     */   
/*     */   public String getLevel() {
/* 160 */     return this.level;
/*     */   }
/*     */   
/*     */   public void setLevel(String level) {
/* 164 */     this.level = level;
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 169 */     return JsonUtils.objectToJson(this);
/*     */   }
/*     */   
/*     */   public String getSecurityMode() {
/* 173 */     return this.securityMode;
/*     */   }
/*     */   
/*     */   public void setSecurityMode(String securityMode) {
/* 177 */     this.securityMode = securityMode;
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\view\domain\ViewInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */