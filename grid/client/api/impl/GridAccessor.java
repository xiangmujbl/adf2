/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.client.api.impl;
/*    */ 
/*    */ import com.gemstone.gemfire.GemFireCheckedException;
/*    */ import com.gemstone.gemfire.GemFireException;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ import org.springframework.beans.factory.InitializingBean;
/*    */ import org.springframework.dao.DataAccessException;
/*    */ import org.springframework.data.gemfire.GemfireCacheUtils;
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
/*    */ public class GridAccessor
/*    */   implements InitializingBean
/*    */ {
/* 32 */   protected final Log log = LogFactory.getLog(getClass());
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void afterPropertiesSet() {}
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public DataAccessException convertGemFireAccessException(GemFireCheckedException ex)
/*    */   {
/* 46 */     return GemfireCacheUtils.convertGemfireAccessException(ex);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public DataAccessException convertGemFireAccessException(GemFireException ex)
/*    */   {
/* 57 */     return GemfireCacheUtils.convertGemfireAccessException(ex);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public DataAccessException convertGemFireQueryException(RuntimeException ex)
/*    */   {
/* 70 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\client\api\impl\GridAccessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */