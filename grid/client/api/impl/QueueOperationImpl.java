/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.client.api.impl;
/*     */ 
/*     */ import com.gemstone.gemfire.cache.Region;
/*     */ import com.gemstone.gemfire.cache.client.ClientCache;
/*     */ import com.gemstone.gemfire.cache.client.ClientCacheFactory;
/*     */ import com.gemstone.gemfire.cache.client.ClientRegionFactory;
/*     */ import com.gemstone.gemfire.cache.client.ClientRegionShortcut;
/*     */ import com.jnj.adf.client.api.ADFEvent;
/*     */ import com.jnj.adf.client.api.QueueOperations;
/*     */ import com.jnj.adf.grid.client.api.support.ADFServiceContext;
/*     */ import com.jnj.adf.grid.client.api.support.ADFServiceContext.Keys;
/*     */ import com.jnj.adf.grid.data.MetaService;
/*     */ import com.jnj.adf.grid.data.MetaServiceFactory;
/*     */ import com.jnj.adf.grid.utils.LogUtil;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.ExecutorService;
/*     */ import java.util.concurrent.Executors;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.springframework.stereotype.Component;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Component
/*     */ public class QueueOperationImpl
/*     */   implements QueueOperations
/*     */ {
/*     */   class QueueTask
/*     */     extends Thread
/*     */   {
/*     */     private String path;
/*     */     private List<ADFEvent> list;
/*     */     
/*     */     public QueueTask(List<ADFEvent> path)
/*     */     {
/*  54 */       this.path = path;
/*  55 */       this.list = list;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public void run()
/*     */     {
/*     */       try
/*     */       {
/*  70 */         ADFServiceContext.setValue(ADFServiceContext.Keys.PATH, this.path);
/*  71 */         LogUtil.startTimer();
/*  72 */         AsyncListenerManager.execute(this.path, this.list);
/*  73 */         LogUtil.getCoreLog().info("AsyncListener on path:{} invoked success. cost:{}ms", new Object[] { this.path, Long.valueOf(LogUtil.cost()) });
/*  74 */         for (ADFEvent e : this.list)
/*     */         {
/*  76 */           QueueOperationImpl.this.removeKey(e.getKey());
/*     */         }
/*     */       }
/*     */       catch (Exception e)
/*     */       {
/*  81 */         LogUtil.getCoreLog().error("execute task on path:{} failed.", new Object[] { this.path, e });
/*     */       } finally {
/*  83 */         ADFServiceContext.resetSession();
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */     public String getPath()
/*     */     {
/*  90 */       return this.path;
/*     */     }
/*     */     
/*     */     public void setPath(String path)
/*     */     {
/*  95 */       this.path = path;
/*     */     }
/*     */     
/*     */     public List<ADFEvent> getList()
/*     */     {
/* 100 */       return this.list;
/*     */     }
/*     */     
/*     */     public void setList(List<ADFEvent> list)
/*     */     {
/* 105 */       this.list = list;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 112 */   ExecutorService service = Executors.newFixedThreadPool(16);
/*     */   
/*     */ 
/*     */   private static Region msgRegion;
/*     */   
/*     */   private MetaService meta;
/*     */   
/*     */ 
/*     */   public QueueOperationImpl()
/*     */   {
/* 122 */     this.meta = MetaServiceFactory.createMetaService();
/*     */   }
/*     */   
/*     */   private void removeKey(String key)
/*     */   {
/* 127 */     if (getMsgRegion() != null) {
/* 128 */       getMsgRegion().remove(key);
/*     */     }
/*     */   }
/*     */   
/*     */   private static final Region getMsgRegion() {
/* 133 */     if (msgRegion != null) {
/* 134 */       return msgRegion;
/*     */     }
/*     */     try {
/* 137 */       ClientCache clientCache = ClientCacheFactory.getAnyInstance();
/* 138 */       ClientRegionFactory crf = clientCache.createClientRegionFactory(ClientRegionShortcut.LOCAL);
/*     */       
/*     */ 
/* 141 */       msgRegion = crf.create("_async_task_local");
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 145 */       LogUtil.getCoreLog().warn("No client cahce");
/* 146 */       msgRegion = null;
/*     */     }
/* 148 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean offer(String key, String jsongValue)
/*     */   {
/* 160 */     String path = ADFServiceContext.getPath();
/* 161 */     if (StringUtils.isEmpty(path))
/*     */     {
/* 163 */       LogUtil.getCoreLog().error("onPaht should be used first.");
/* 164 */       return false;
/*     */     }
/* 166 */     if (getMsgRegion() != null)
/*     */     {
/* 168 */       getMsgRegion().put(key, jsongValue);
/*     */     }
/* 170 */     List<ADFEvent> list = new ArrayList();
/* 171 */     ADFEventImpl e = new ADFEventImpl(key, jsongValue);
/* 172 */     list.add(e);
/* 173 */     QueueTask task = new QueueTask(path, list);
/* 174 */     this.service.execute(task);
/* 175 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public <V> boolean offer(String key, V value)
/*     */   {
/* 187 */     String valueString = "";
/* 188 */     if ((value instanceof String)) {
/* 189 */       valueString = (String)value;
/*     */     } else {
/* 191 */       valueString = this.meta.applyWriteSchema(ADFServiceContext.getPath(), value);
/*     */     }
/* 193 */     return offer(key, valueString);
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\client\api\impl\QueueOperationImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */