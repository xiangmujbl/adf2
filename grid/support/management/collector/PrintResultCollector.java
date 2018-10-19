/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.support.management.collector;
/*    */ 
/*    */ import com.gemstone.gemfire.cache.execute.FunctionException;
/*    */ import com.gemstone.gemfire.cache.execute.ResultCollector;
/*    */ import com.gemstone.gemfire.distributed.DistributedMember;
/*    */ import java.io.PrintStream;
/*    */ import java.util.concurrent.TimeUnit;
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
/*    */ public class PrintResultCollector
/*    */   implements ResultCollector
/*    */ {
/*    */   public void addResult(DistributedMember arg0, Object arg1)
/*    */   {
/* 30 */     System.out.println(arg1);
/*    */   }
/*    */   
/*    */   public Object getResult()
/*    */     throws FunctionException
/*    */   {
/* 36 */     return null;
/*    */   }
/*    */   
/*    */   public Object getResult(long l, TimeUnit timeunit)
/*    */     throws FunctionException, InterruptedException
/*    */   {
/* 42 */     return null;
/*    */   }
/*    */   
/*    */ 
/*    */   public void endResults()
/*    */   {
/* 48 */     System.out.println("finish!");
/*    */   }
/*    */   
/*    */   public void clearResults() {}
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\support\management\collector\PrintResultCollector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */