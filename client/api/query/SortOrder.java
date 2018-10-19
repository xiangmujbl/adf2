/*    */ package com.jnj.adf.dataservice.adfcoreignite.client.api.query;
/*    */ 
/*    */ public class SortOrder
/*    */ {
/*  5 */   private Integer order = null;
/*    */   
/*  7 */   public static final SortOrder SORT_FIRST = new SortOrder(Integer.valueOf(0));
/*  8 */   public static final SortOrder SORT_LAST = new SortOrder(Integer.valueOf(Integer.MAX_VALUE));
/*    */   
/*    */   public SortOrder(Integer order)
/*    */   {
/* 12 */     this.order = order;
/*    */   }
/*    */   
/*    */   public Integer getOrder() {
/* 16 */     return this.order;
/*    */   }
/*    */   
/*    */   public void setOrder(Integer order) {
/* 20 */     this.order = order;
/*    */   }
/*    */   
/*    */   public int hashCode()
/*    */   {
/* 25 */     int prime = 31;
/* 26 */     int result = 1;
/* 27 */     result = 31 * result + (this.order == null ? 0 : this.order.hashCode());
/* 28 */     return result;
/*    */   }
/*    */   
/*    */   public boolean equals(Object obj)
/*    */   {
/* 33 */     if (this == obj)
/* 34 */       return true;
/* 35 */     if (obj == null)
/* 36 */       return false;
/* 37 */     if (getClass() != obj.getClass())
/* 38 */       return false;
/* 39 */     SortOrder other = (SortOrder)obj;
/* 40 */     if (this.order == null) {
/* 41 */       if (other.order != null)
/* 42 */         return false;
/* 43 */     } else if (!this.order.equals(other.order))
/* 44 */       return false;
/* 45 */     return true;
/*    */   }
/*    */   
/*    */   public static void checkOrder(SortOrder order)
/*    */   {
/* 50 */     if ((order != SORT_FIRST) && (order != SORT_LAST))
/*    */     {
/* 52 */       if ((order.getOrder() == null) || (order.getOrder().intValue() < 0)) {
/* 53 */         throw new IllegalArgumentException("Sort Order must be SORT_FIRST, SORT_LAST, NO_SORT or other positive integers");
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\client\api\query\SortOrder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */