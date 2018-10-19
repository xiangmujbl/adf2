/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.data;
/*     */ 
/*     */ import java.io.Serializable;
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
/*     */ public class MetaKey
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 7191758628212266055L;
/*     */   private String path;
/*     */   private String property;
/*     */   private Object propertyId;
/*     */   
/*     */   public MetaKey() {}
/*     */   
/*     */   public MetaKey(String path, MetaTypes type)
/*     */   {
/*  43 */     this.path = path;
/*  44 */     this.property = type.name();
/*     */   }
/*     */   
/*     */   public String getPath()
/*     */   {
/*  49 */     return this.path;
/*     */   }
/*     */   
/*     */   public void setPath(String path)
/*     */   {
/*  54 */     this.path = path;
/*     */   }
/*     */   
/*     */   public String getProperty()
/*     */   {
/*  59 */     return this.property;
/*     */   }
/*     */   
/*     */   public void setProperty(String property)
/*     */   {
/*  64 */     this.property = property;
/*     */   }
/*     */   
/*     */   public Object getPropertyId()
/*     */   {
/*  69 */     return this.propertyId;
/*     */   }
/*     */   
/*     */   public void setPropertyId(Object propertyId)
/*     */   {
/*  74 */     this.propertyId = propertyId;
/*     */   }
/*     */   
/*     */ 
/*     */   public int hashCode()
/*     */   {
/*  80 */     int prime = 31;
/*  81 */     int result = 1;
/*  82 */     result = 31 * result + (this.path == null ? 0 : this.path.hashCode());
/*  83 */     result = 31 * result + (this.property == null ? 0 : this.property.hashCode());
/*  84 */     result = 31 * result + (this.propertyId == null ? 0 : this.propertyId.hashCode());
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
/*  97 */     MetaKey other = (MetaKey)obj;
/*  98 */     if (this.path == null)
/*     */     {
/* 100 */       if (other.path != null) {
/* 101 */         return false;
/*     */       }
/* 103 */     } else if (!this.path.equals(other.path))
/* 104 */       return false;
/* 105 */     if (this.property == null)
/*     */     {
/* 107 */       if (other.property != null) {
/* 108 */         return false;
/*     */       }
/* 110 */     } else if (!this.property.equals(other.property))
/* 111 */       return false;
/* 112 */     if (this.propertyId == null)
/*     */     {
/* 114 */       if (other.propertyId != null) {
/* 115 */         return false;
/*     */       }
/* 117 */     } else if (!this.propertyId.equals(other.propertyId))
/* 118 */       return false;
/* 119 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\data\MetaKey.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */