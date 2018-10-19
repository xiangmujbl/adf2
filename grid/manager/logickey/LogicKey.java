/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.manager.logickey;
/*    */ 
/*    */ import com.gemstone.gemfire.DataSerializable;
/*    */ import com.gemstone.gemfire.DataSerializer;
/*    */ import com.jnj.adf.grid.utils.JsonUtils;
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
/*    */ 
/*    */ public class LogicKey
/*    */   implements DataSerializable
/*    */ {
/*    */   private static final long serialVersionUID = 9030168896501378084L;
/*    */   private String originalKey;
/*    */   private String identityKey;
/*    */   
/*    */   public String getOriginalKey()
/*    */   {
/* 27 */     return this.originalKey;
/*    */   }
/*    */   
/*    */   public void setOriginalKey(String originalKey) {
/* 31 */     this.originalKey = originalKey;
/*    */   }
/*    */   
/*    */   public String getIdentityKey() {
/* 35 */     return this.identityKey;
/*    */   }
/*    */   
/*    */   public void setIdentityKey(String identityKey) {
/* 39 */     this.identityKey = identityKey;
/*    */   }
/*    */   
/*    */   public void toData(DataOutput out) throws IOException
/*    */   {
/* 44 */     DataSerializer.writeString(this.originalKey, out);
/* 45 */     DataSerializer.writeString(this.identityKey, out);
/*    */   }
/*    */   
/*    */   public void fromData(DataInput in)
/*    */     throws IOException, ClassNotFoundException
/*    */   {
/* 51 */     this.originalKey = DataSerializer.readString(in);
/* 52 */     this.identityKey = DataSerializer.readString(in);
/*    */   }
/*    */   
/*    */   public int hashCode()
/*    */   {
/* 57 */     int result = 1;
/* 58 */     result = this.originalKey == null ? 0 : this.originalKey.hashCode();
/* 59 */     return result;
/*    */   }
/*    */   
/*    */   public boolean equals(Object obj)
/*    */   {
/* 64 */     if (this == obj)
/* 65 */       return true;
/* 66 */     if (obj == null)
/* 67 */       return false;
/* 68 */     if (getClass() != obj.getClass())
/* 69 */       return false;
/* 70 */     LogicKey other = (LogicKey)obj;
/* 71 */     if (this.originalKey == null) {
/* 72 */       if (other.originalKey != null)
/* 73 */         return false;
/* 74 */     } else if (!this.originalKey.equals(other.originalKey))
/* 75 */       return false;
/* 76 */     if (this.identityKey == null) {
/* 77 */       if (other.identityKey != null)
/* 78 */         return false;
/* 79 */     } else if (!this.identityKey.equals(other.identityKey))
/* 80 */       return false;
/* 81 */     return true;
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 86 */     return JsonUtils.objectToJson(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\manager\logickey\LogicKey.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */