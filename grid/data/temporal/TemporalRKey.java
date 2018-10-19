/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.data.temporal;
/*    */ 
/*    */ import com.gemstone.gemfire.DataSerializable;
/*    */ import com.gemstone.gemfire.DataSerializer;
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TemporalRKey
/*    */   implements DataSerializable
/*    */ {
/*    */   private static final long serialVersionUID = 514429547219598014L;
/*    */   private String uuid;
/*    */   private String identityKey;
/*    */   
/*    */   public void toData(DataOutput out)
/*    */     throws IOException
/*    */   {
/* 26 */     DataSerializer.writeString(this.uuid, out);
/* 27 */     DataSerializer.writeString(this.identityKey, out);
/*    */   }
/*    */   
/*    */   public void fromData(DataInput in)
/*    */     throws IOException, ClassNotFoundException
/*    */   {
/* 33 */     this.uuid = DataSerializer.readString(in);
/* 34 */     this.identityKey = DataSerializer.readString(in);
/*    */   }
/*    */   
/*    */   public String getUuid()
/*    */   {
/* 39 */     return this.uuid;
/*    */   }
/*    */   
/*    */   public void setUuid(String uuid)
/*    */   {
/* 44 */     this.uuid = uuid;
/*    */   }
/*    */   
/*    */   public String getIdentityKey()
/*    */   {
/* 49 */     return this.identityKey;
/*    */   }
/*    */   
/*    */   public void setIdentityKey(String identityKey)
/*    */   {
/* 54 */     this.identityKey = identityKey;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public int hashCode()
/*    */   {
/* 61 */     int result = 1;
/* 62 */     result = this.identityKey == null ? 0 : this.identityKey.hashCode();
/*    */     
/* 64 */     return result;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean equals(Object obj)
/*    */   {
/* 70 */     if (this == obj)
/* 71 */       return true;
/* 72 */     if (obj == null)
/* 73 */       return false;
/* 74 */     if (getClass() != obj.getClass())
/* 75 */       return false;
/* 76 */     TemporalRKey other = (TemporalRKey)obj;
/* 77 */     if (this.identityKey == null)
/*    */     {
/* 79 */       if (other.identityKey != null) {
/* 80 */         return false;
/*    */       }
/* 82 */     } else if (!this.identityKey.equals(other.identityKey))
/* 83 */       return false;
/* 84 */     if (this.uuid == null)
/*    */     {
/* 86 */       if (other.uuid != null) {
/* 87 */         return false;
/*    */       }
/* 89 */     } else if (!this.uuid.equals(other.uuid))
/* 90 */       return false;
/* 91 */     return true;
/*    */   }
/*    */   
/*    */ 
/*    */   public String toString()
/*    */   {
/* 97 */     return "TemporalRKey [uuid=" + this.uuid + ", identityKey=" + this.identityKey + "]";
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\data\temporal\TemporalRKey.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */