/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.query.criteria;
/*    */ 
/*    */ import org.springframework.util.ObjectUtils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SimpleField
/*    */   implements Field
/*    */ {
/*    */   private final String name;
/*    */   
/*    */   public SimpleField(String name)
/*    */   {
/* 31 */     this.name = name;
/*    */   }
/*    */   
/*    */   public String getName()
/*    */   {
/* 36 */     return this.name;
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 41 */     return this.name;
/*    */   }
/*    */   
/*    */   public int hashCode()
/*    */   {
/* 46 */     return ObjectUtils.nullSafeHashCode(this.name);
/*    */   }
/*    */   
/*    */   public boolean equals(Object other)
/*    */   {
/* 51 */     if (this == other) {
/* 52 */       return true;
/*    */     }
/* 54 */     if (!(other instanceof SimpleField)) {
/* 55 */       return false;
/*    */     }
/* 57 */     SimpleField that = (SimpleField)other;
/* 58 */     return ObjectUtils.nullSafeEquals(this.name, that.name);
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\query\criteria\SimpleField.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */