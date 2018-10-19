/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.support.listener.filter;
/*    */ 
/*    */ import com.gemstone.gemfire.cache.asyncqueue.AsyncEvent;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class FilterErrorCollector
/*    */ {
/* 31 */   private static final ThreadLocal<Collection<AsyncEvent>> tl_errors = new ThreadLocal();
/*    */   
/*    */   public static final void recordOne(AsyncEvent e)
/*    */   {
/* 35 */     ((Collection)tl_errors.get()).add(e);
/*    */   }
/*    */   
/*    */   public static final void recordAll(Collection<AsyncEvent> all)
/*    */   {
/* 40 */     ((Collection)tl_errors.get()).addAll(all);
/*    */   }
/*    */   
/*    */   public static final Collection<AsyncEvent> getErrors()
/*    */   {
/* 45 */     return (Collection)tl_errors.get();
/*    */   }
/*    */   
/*    */   public static final void start()
/*    */   {
/* 50 */     tl_errors.set(new ArrayList());
/*    */   }
/*    */   
/*    */   public static final void stop()
/*    */   {
/* 55 */     tl_errors.remove();
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\support\listener\filter\FilterErrorCollector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */