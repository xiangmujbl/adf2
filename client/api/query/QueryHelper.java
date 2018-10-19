/*    */ package com.jnj.adf.dataservice.adfcoreignite.client.api.query;
/*    */ 
/*    */ import com.jnj.adf.grid.query.criteria.ADFCriteriaImpl;
/*    */ 
/*    */ public class QueryHelper
/*    */ {
/*    */   public static final com.jnj.adf.client.api.ADFCriteria buildCriteria(String fieldName)
/*    */   {
/*  9 */     return new ADFCriteriaImpl(fieldName);
/*    */   }
/*    */   
/*    */   public static final com.jnj.adf.client.api.ADFCriteria buildCriteria() {
/* 13 */     return new ADFCriteriaImpl();
/*    */   }
/*    */   
/*    */   public static void main(String[] args) {}
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\client\api\query\QueryHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */