/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.indexer;
/*     */ 
/*     */ import com.gemstone.gemfire.DataSerializable;
/*     */ import com.gemstone.gemfire.DataSerializer;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
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
/*     */ public class AdfField
/*     */   implements DataSerializable, Serializable, Cloneable
/*     */ {
/*     */   private static final long serialVersionUID = -4985687954526813309L;
/*     */   private String name;
/*  38 */   private boolean indexed = true;
/*  39 */   private boolean stored = true;
/*     */   
/*     */   private AdfFieldType fieldType;
/*     */   
/*     */ 
/*     */   public AdfField() {}
/*     */   
/*     */ 
/*     */   public AdfField(String name)
/*     */   {
/*  49 */     this(name, AdfFieldType.TEXT_STRINGS);
/*     */   }
/*     */   
/*     */   public AdfField(String name, AdfFieldType fieldType)
/*     */   {
/*  54 */     this(name, fieldType, true, false);
/*     */   }
/*     */   
/*     */   public AdfField(String name, AdfFieldType fieldType, boolean indexed, boolean stored)
/*     */   {
/*  59 */     this.name = name;
/*  60 */     this.fieldType = fieldType;
/*  61 */     this.indexed = indexed;
/*  62 */     this.stored = stored;
/*     */   }
/*     */   
/*     */   public AdfFieldType getFieldType()
/*     */   {
/*  67 */     return this.fieldType;
/*     */   }
/*     */   
/*     */   public void setFieldType(AdfFieldType fieldType)
/*     */   {
/*  72 */     this.fieldType = fieldType;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getName()
/*     */   {
/*  80 */     return this.name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/*  89 */     this.name = name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isIndexed()
/*     */   {
/*  97 */     return this.indexed;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setIndexed(boolean indexed)
/*     */   {
/* 106 */     this.indexed = indexed;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void toData(DataOutput out)
/*     */     throws IOException
/*     */   {
/* 117 */     DataSerializer.writeString(this.name, out);
/* 118 */     DataSerializer.writeEnum(this.fieldType, out);
/* 119 */     DataSerializer.writeBoolean(Boolean.valueOf(this.indexed), out);
/* 120 */     DataSerializer.writeBoolean(Boolean.valueOf(this.stored), out);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void fromData(DataInput in)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/* 131 */     this.name = DataSerializer.readString(in);
/* 132 */     this.fieldType = ((AdfFieldType)DataSerializer.readEnum(AdfFieldType.class, in));
/* 133 */     this.indexed = DataSerializer.readBoolean(in).booleanValue();
/* 134 */     this.stored = DataSerializer.readBoolean(in).booleanValue();
/*     */   }
/*     */   
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 140 */     int prime = 31;
/* 141 */     int result = 1;
/* 142 */     result = 31 * result + (this.fieldType == null ? 0 : this.fieldType.hashCode());
/* 143 */     result = 31 * result + (this.indexed ? 1231 : 1237);
/* 144 */     result = 31 * result + (this.name == null ? 0 : this.name.hashCode());
/* 145 */     result = 31 * result + (this.stored ? 1231 : 1237);
/* 146 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 152 */     if (this == obj)
/* 153 */       return true;
/* 154 */     if (obj == null)
/* 155 */       return false;
/* 156 */     if (getClass() != obj.getClass())
/* 157 */       return false;
/* 158 */     AdfField other = (AdfField)obj;
/* 159 */     if (this.fieldType != other.fieldType)
/* 160 */       return false;
/* 161 */     if (this.indexed != other.indexed)
/* 162 */       return false;
/* 163 */     if (this.name == null)
/*     */     {
/* 165 */       if (other.name != null) {
/* 166 */         return false;
/*     */       }
/* 168 */     } else if (!this.name.equals(other.name))
/* 169 */       return false;
/* 170 */     if (this.stored != other.stored)
/* 171 */       return false;
/* 172 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isStored()
/*     */   {
/* 180 */     return this.stored;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setStored(boolean stored)
/*     */   {
/* 189 */     this.stored = stored;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 200 */     StringBuffer sb = new StringBuffer(64);
/* 201 */     sb.append("ADF FIELD {").append("name:'").append(this.name).append("',").append("type:'").append(this.fieldType.name())
/* 202 */       .append("',").append("indexed:").append(this.indexed).append(",").append("stored:").append(this.stored).append(",")
/* 203 */       .append("}");
/* 204 */     return sb.toString();
/*     */   }
/*     */   
/*     */   public AdfField clone()
/*     */     throws CloneNotSupportedException
/*     */   {
/* 210 */     return (AdfField)super.clone();
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\indexer\AdfField.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */