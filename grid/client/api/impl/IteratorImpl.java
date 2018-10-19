/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.client.api.impl;
/*    */ 
/*    */ import com.jnj.adf.grid.query.SendResult;
/*    */ import com.jnj.adf.grid.utils.LogUtil;
/*    */ import java.util.Iterator;
/*    */ import java.util.concurrent.BlockingQueue;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ public class IteratorImpl<T> implements Iterator<T>
/*    */ {
/*    */   private BlockingQueue<T> queue;
/*    */   
/*    */   public IteratorImpl(BlockingQueue<T> queue)
/*    */   {
/* 15 */     this.queue = queue;
/*    */   }
/*    */   
/*    */   public boolean hasNext()
/*    */   {
/* 20 */     T head = this.queue.peek();
/* 21 */     if ((head instanceof SendResult))
/* 22 */       return !((SendResult)head).isEnd();
/* 23 */     return true;
/*    */   }
/*    */   
/*    */   public T next()
/*    */   {
/*    */     try
/*    */     {
/* 30 */       return (T)this.queue.take();
/*    */     } catch (InterruptedException e) {
/* 32 */       LogUtil.getCoreLog().error("", e); }
/* 33 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\client\api\impl\IteratorImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */