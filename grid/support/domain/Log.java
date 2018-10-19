/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.support.domain;
/*    */ 
/*    */ import com.gemstone.gemfire.DataSerializable;
/*    */ import com.gemstone.gemfire.DataSerializer;
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ 
/*    */ public class Log
/*    */   implements DataSerializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String name;
/*    */   private String level;
/*    */   
/*    */   public String getName()
/*    */   {
/* 18 */     return this.name;
/*    */   }
/*    */   
/*    */   public void setName(String name) {
/* 22 */     this.name = name;
/*    */   }
/*    */   
/*    */   public String getLevel() {
/* 26 */     return this.level;
/*    */   }
/*    */   
/*    */   public void setLevel(String level) {
/* 30 */     this.level = level;
/*    */   }
/*    */   
/*    */   public void fromData(DataInput in)
/*    */     throws IOException, ClassNotFoundException
/*    */   {
/* 36 */     this.name = DataSerializer.readString(in);
/* 37 */     this.level = DataSerializer.readString(in);
/*    */   }
/*    */   
/*    */   public void toData(DataOutput out)
/*    */     throws IOException
/*    */   {
/* 43 */     DataSerializer.writeString(this.name, out);
/* 44 */     DataSerializer.writeString(this.level, out);
/*    */   }
/*    */   
/*    */ 
/*    */   public int hashCode()
/*    */   {
/* 50 */     int prime = 31;
/* 51 */     int result = 1;
/* 52 */     result = 31 * result + (this.name == null ? 0 : this.name.hashCode());
/* 53 */     result = 31 * result + (this.level == null ? 0 : this.level.hashCode());
/* 54 */     return result;
/*    */   }
/*    */   
/*    */   public boolean equals(Object obj)
/*    */   {
/* 59 */     if (this == obj)
/* 60 */       return true;
/* 61 */     if (obj == null)
/* 62 */       return false;
/* 63 */     if (getClass() != obj.getClass())
/* 64 */       return false;
/* 65 */     Log other = (Log)obj;
/* 66 */     if (this.name == null) {
/* 67 */       if (other.name != null)
/* 68 */         return false;
/* 69 */     } else if (!this.name.equals(other.name))
/* 70 */       return false;
/* 71 */     if (this.level == null) {
/* 72 */       if (other.level != null)
/* 73 */         return false;
/* 74 */     } else if (!this.level.equals(other.level))
/* 75 */       return false;
/* 76 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\support\domain\Log.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */