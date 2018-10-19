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
/*     */ public class ViewExecutionData
/*     */   implements DataSerializable
/*     */ {
/*     */   private static final long serialVersionUID = 2078551402862839217L;
/*     */   Object key;
/*     */   Object value;
/*     */   Object oldValue;
/*     */   String mode;
/*     */   
/*     */   public ViewExecutionData() {}
/*     */   
/*     */   public ViewExecutionData(Object key, Object value)
/*     */   {
/*  40 */     this.key = key;
/*  41 */     this.value = value;
/*     */   }
/*     */   
/*     */   public ViewExecutionData(Object key, Object value, Object oldValue) {
/*  45 */     this.key = key;
/*  46 */     this.value = value;
/*  47 */     this.oldValue = oldValue;
/*     */   }
/*     */   
/*     */   public Object getKey()
/*     */   {
/*  52 */     return this.key;
/*     */   }
/*     */   
/*     */   public void setKey(Object key)
/*     */   {
/*  57 */     this.key = key;
/*     */   }
/*     */   
/*     */   public Object getValue()
/*     */   {
/*  62 */     return this.value;
/*     */   }
/*     */   
/*     */   public void setValue(Object value)
/*     */   {
/*  67 */     this.value = value;
/*     */   }
/*     */   
/*     */   public Object getOldValue()
/*     */   {
/*  72 */     return this.oldValue;
/*     */   }
/*     */   
/*     */   public void setOldValue(Object oldValue)
/*     */   {
/*  77 */     this.oldValue = oldValue;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void fromData(DataInput in)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/*  86 */     DataSerializerExt.fromData(getClass(), this, in);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void toData(DataOutput out)
/*     */     throws IOException
/*     */   {
/*  96 */     DataSerializerExt.toData(getClass(), this, out);
/*     */   }
/*     */   
/*     */ 
/*     */   public String getMode()
/*     */   {
/* 102 */     return this.mode;
/*     */   }
/*     */   
/*     */   public void setMode(String mode)
/*     */   {
/* 107 */     this.mode = mode;
/*     */   }
/*     */   
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 113 */     int prime = 31;
/* 114 */     int result = 1;
/* 115 */     result = 31 * result + (this.key == null ? 0 : this.key.hashCode());
/* 116 */     result = 31 * result + (this.mode == null ? 0 : this.mode.hashCode());
/* 117 */     result = 31 * result + (this.oldValue == null ? 0 : this.oldValue.hashCode());
/* 118 */     result = 31 * result + (this.value == null ? 0 : this.value.hashCode());
/* 119 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 125 */     if (this == obj)
/* 126 */       return true;
/* 127 */     if (obj == null)
/* 128 */       return false;
/* 129 */     if (getClass() != obj.getClass())
/* 130 */       return false;
/* 131 */     ViewExecutionData other = (ViewExecutionData)obj;
/* 132 */     if (this.key == null)
/*     */     {
/* 134 */       if (other.key != null) {
/* 135 */         return false;
/*     */       }
/* 137 */     } else if (!this.key.equals(other.key))
/* 138 */       return false;
/* 139 */     if (this.mode == null)
/*     */     {
/* 141 */       if (other.mode != null) {
/* 142 */         return false;
/*     */       }
/* 144 */     } else if (!this.mode.equals(other.mode))
/* 145 */       return false;
/* 146 */     if (this.oldValue == null)
/*     */     {
/* 148 */       if (other.oldValue != null) {
/* 149 */         return false;
/*     */       }
/* 151 */     } else if (!this.oldValue.equals(other.oldValue))
/* 152 */       return false;
/* 153 */     if (this.value == null)
/*     */     {
/* 155 */       if (other.value != null) {
/* 156 */         return false;
/*     */       }
/* 158 */     } else if (!this.value.equals(other.value))
/* 159 */       return false;
/* 160 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 166 */     return "ViewExecutionData [key=" + this.key + ", value=" + this.value + ", oldValue=" + this.oldValue + ", mode=" + this.mode + "]";
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\view\domain\ViewExecutionData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */