/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.support.management.collector;
/*    */ 
/*    */ import com.gemstone.gemfire.cache.execute.FunctionException;
/*    */ import com.gemstone.gemfire.cache.execute.ResultCollector;
/*    */ import com.gemstone.gemfire.distributed.DistributedMember;
/*    */ import java.util.concurrent.BlockingQueue;
/*    */ import java.util.concurrent.LinkedBlockingQueue;
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
/*    */ public class MsgCollector
/*    */   implements ResultCollector
/*    */ {
/*    */   private final BlockingQueue<String> entries;
/*    */   private final String end;
/*    */   
/*    */   public MsgCollector(String last)
/*    */   {
/* 34 */     this.entries = new LinkedBlockingQueue();
/* 35 */     this.end = last;
/*    */   }
/*    */   
/*    */   public BlockingQueue<String> getResult() throws FunctionException
/*    */   {
/* 40 */     return this.entries;
/*    */   }
/*    */   
/*    */   public BlockingQueue<String> getResult(long timeout, TimeUnit unit)
/*    */     throws FunctionException, InterruptedException
/*    */   {
/* 46 */     return getResult();
/*    */   }
/*    */   
/*    */   public void addResult(DistributedMember memberID, Object result)
/*    */   {
/*    */     try
/*    */     {
/* 53 */       if ((result instanceof Throwable))
/*    */       {
/* 55 */         endResults();
/*    */       }
/*    */       else
/*    */       {
/* 59 */         this.entries.put(result.toString());
/*    */       }
/*    */     }
/*    */     catch (InterruptedException e)
/*    */     {
/* 64 */       e.printStackTrace();
/* 65 */       endResults();
/* 66 */       Thread.currentThread().interrupt();
/*    */     }
/*    */   }
/*    */   
/*    */   public void endResults()
/*    */   {
/* 72 */     this.entries.add(this.end);
/*    */   }
/*    */   
/*    */   public void clearResults()
/*    */   {
/* 77 */     this.entries.clear();
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\support\management\collector\MsgCollector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */