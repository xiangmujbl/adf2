/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.view.domain;
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
/*     */ public class DomainDef
/*     */   implements DataSerializable
/*     */ {
/*     */   private static final long serialVersionUID = -5638493783790645218L;
/*     */   private String id;
/*     */   private String parentId;
/*     */   private String name;
/*     */   private String description;
/*     */   private String dispOrder;
/*     */   private String path;
/*     */   
/*     */   public void toData(DataOutput out)
/*     */     throws IOException
/*     */   {
/*  63 */     DataSerializerExt.toData(getClass(), this, out);
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
/*     */ 
/*     */ 
/*     */   public void fromData(DataInput in)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/*  80 */     DataSerializerExt.fromData(getClass(), this, in);
/*     */   }
/*     */   
/*     */   public String getId()
/*     */   {
/*  85 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(String id)
/*     */   {
/*  90 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getParentId()
/*     */   {
/*  95 */     return this.parentId;
/*     */   }
/*     */   
/*     */   public void setParentId(String parentId)
/*     */   {
/* 100 */     this.parentId = parentId;
/*     */   }
/*     */   
/*     */   public String getName()
/*     */   {
/* 105 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name)
/*     */   {
/* 110 */     this.name = name;
/*     */   }
/*     */   
/*     */   public String getDescription()
/*     */   {
/* 115 */     return this.description;
/*     */   }
/*     */   
/*     */   public void setDescription(String description)
/*     */   {
/* 120 */     this.description = description;
/*     */   }
/*     */   
/*     */   public String getDispOrder()
/*     */   {
/* 125 */     return this.dispOrder;
/*     */   }
/*     */   
/*     */   public void setDispOrder(String dispOrder)
/*     */   {
/* 130 */     this.dispOrder = dispOrder;
/*     */   }
/*     */   
/*     */   public String getPath()
/*     */   {
/* 135 */     return this.path;
/*     */   }
/*     */   
/*     */   public void setPath(String path)
/*     */   {
/* 140 */     this.path = path;
/*     */   }
/*     */   
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 146 */     int prime = 31;
/* 147 */     int result = 1;
/* 148 */     result = 31 * result + (this.description == null ? 0 : this.description.hashCode());
/* 149 */     result = 31 * result + (this.dispOrder == null ? 0 : this.dispOrder.hashCode());
/* 150 */     result = 31 * result + (this.id == null ? 0 : this.id.hashCode());
/* 151 */     result = 31 * result + (this.name == null ? 0 : this.name.hashCode());
/* 152 */     result = 31 * result + (this.parentId == null ? 0 : this.parentId.hashCode());
/* 153 */     result = 31 * result + (this.path == null ? 0 : this.path.hashCode());
/* 154 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 160 */     if (this == obj)
/* 161 */       return true;
/* 162 */     if (obj == null)
/* 163 */       return false;
/* 164 */     if (getClass() != obj.getClass())
/* 165 */       return false;
/* 166 */     DomainDef other = (DomainDef)obj;
/* 167 */     if (this.description == null)
/*     */     {
/* 169 */       if (other.description != null) {
/* 170 */         return false;
/*     */       }
/* 172 */     } else if (!this.description.equals(other.description))
/* 173 */       return false;
/* 174 */     if (this.dispOrder == null)
/*     */     {
/* 176 */       if (other.dispOrder != null) {
/* 177 */         return false;
/*     */       }
/* 179 */     } else if (!this.dispOrder.equals(other.dispOrder))
/* 180 */       return false;
/* 181 */     if (this.id == null)
/*     */     {
/* 183 */       if (other.id != null) {
/* 184 */         return false;
/*     */       }
/* 186 */     } else if (!this.id.equals(other.id))
/* 187 */       return false;
/* 188 */     if (this.name == null)
/*     */     {
/* 190 */       if (other.name != null) {
/* 191 */         return false;
/*     */       }
/* 193 */     } else if (!this.name.equals(other.name))
/* 194 */       return false;
/* 195 */     if (this.parentId == null)
/*     */     {
/* 197 */       if (other.parentId != null) {
/* 198 */         return false;
/*     */       }
/* 200 */     } else if (!this.parentId.equals(other.parentId))
/* 201 */       return false;
/* 202 */     if (this.path == null)
/*     */     {
/* 204 */       if (other.path != null) {
/* 205 */         return false;
/*     */       }
/* 207 */     } else if (!this.path.equals(other.path))
/* 208 */       return false;
/* 209 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 215 */     return "DomainDef [id=" + this.id + ", parentId=" + this.parentId + ", name=" + this.name + ", description=" + this.description + ", dispOrder=" + this.dispOrder + ", path=" + this.path + "]";
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\view\domain\DomainDef.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */