/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.query;
/*    */ 
/*    */ import com.jnj.adf.client.api.GridQueryOperations;
/*    */ import com.jnj.adf.client.api.IBiz;
/*    */ import com.jnj.adf.grid.client.api.support.ADFServiceContext;
/*    */ import com.jnj.adf.grid.client.api.support.ADFServiceContext.Keys;
/*    */ import com.jnj.adf.grid.client.api.support.BasicGridService;
/*    */ import com.jnj.adf.grid.support.context.ADFContext;
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
/*    */ public abstract interface QueryService
/*    */   extends GridQueryOperations
/*    */ {
/*    */   public abstract QueryProviderType getProviderType();
/*    */   
/*    */   public String getGridRegionPath()
/*    */   {
/* 29 */     return (String)ADFServiceContext.getValue(ADFServiceContext.Keys.PATH);
/*    */   }
/*    */   
/*    */   public BasicGridService getBasicGridService() {
/* 33 */     return (BasicGridService)ADFContext.getBean("_adf_basic");
/*    */   }
/*    */   
/*    */   public ILuceneDirectQueryOnServerService getLuceneDirectQueryService() {
/* 37 */     return (ILuceneDirectQueryOnServerService)IBiz.lookup(ILuceneDirectQueryOnServerService.class);
/*    */   }
/*    */   
/*    */   public ILuceneIndexMatrixService getLuceneIndexMatrixService() {
/* 41 */     return (ILuceneIndexMatrixService)IBiz.lookup(ILuceneIndexMatrixService.class);
/*    */   }
/*    */   
/*    */   public ILucene2StepService getLucene2StepService() {
/* 45 */     return (ILucene2StepService)IBiz.lookup(ILucene2StepService.class);
/*    */   }
/*    */   
/*    */   public ILuceneStreamingQueryOnServerService getLuceneStreamingQueryService() {
/* 49 */     return (ILuceneStreamingQueryOnServerService)IBiz.lookup(ILuceneStreamingQueryOnServerService.class);
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\query\QueryService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */