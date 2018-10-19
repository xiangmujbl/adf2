/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.client.api.impl;
/*    */ 
/*    */ import com.jnj.adf.client.api.ADFEvent;
/*    */ import com.jnj.adf.client.api.async.ADFAsyncListener;
/*    */ import com.jnj.adf.grid.client.api.support.ADFServiceContext;
/*    */ import com.jnj.adf.grid.client.api.support.ADFServiceContext.Keys;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.concurrent.ConcurrentHashMap;
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
/*    */ 
/*    */ public class AsyncListenerManager
/*    */ {
/* 36 */   private static final Map<String, ADFAsyncListener> taskMap = new ConcurrentHashMap();
/*    */   
/*    */   public static final void register(ADFAsyncListener task) {
/* 39 */     taskMap.put(task.path(), task);
/*    */   }
/*    */   
/*    */   public static final void execute(String path, List<ADFEvent> list)
/*    */   {
/* 44 */     ADFAsyncListener task = (ADFAsyncListener)taskMap.get(path);
/* 45 */     ADFServiceContext.setValue(ADFServiceContext.Keys.PATH, path);
/* 46 */     task.execute(list);
/* 47 */     ADFServiceContext.resetSession();
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\client\api\impl\AsyncListenerManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */