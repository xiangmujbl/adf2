/*     */ package com.jnj.adf.dataservice.adfcoreignite.config.context;
/*     */ 
/*     */ import com.jnj.adf.client.api.async.ADFAsyncListener;
/*     */ import com.jnj.adf.client.api.config.ADFDomain;
/*     */ import com.jnj.adf.client.api.config.ADFField;
/*     */ import com.jnj.adf.client.api.config.ADFIgnore;
/*     */ import com.jnj.adf.grid.auth.AuthService;
/*     */ import com.jnj.adf.grid.auth.AuthTokenManager;
/*     */ import com.jnj.adf.grid.client.api.impl.AsyncListenerManager;
/*     */ import com.jnj.adf.grid.data.MetaService;
/*     */ import com.jnj.adf.grid.data.MetaServiceFactory;
/*     */ import com.jnj.adf.grid.query.indexer.GridIndexerFactory;
/*     */ import com.jnj.adf.grid.query.indexer.IGridIndexer;
/*     */ import com.jnj.adf.grid.utils.LogUtil;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.springframework.beans.BeansException;
/*     */ import org.springframework.beans.factory.config.BeanPostProcessor;
/*     */ import org.springframework.context.ApplicationContext;
/*     */ import org.springframework.context.ApplicationListener;
/*     */ import org.springframework.context.event.ContextRefreshedEvent;
/*     */ import org.springframework.core.annotation.AnnotationUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class InternalBeanPosterProcessor
/*     */   implements BeanPostProcessor, ApplicationListener<ContextRefreshedEvent>
/*     */ {
/*     */   public Object postProcessBeforeInitialization(Object bean, String beanName)
/*     */     throws BeansException
/*     */   {
/*  35 */     return bean;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object postProcessAfterInitialization(Object bean, String beanName)
/*     */     throws BeansException
/*     */   {
/*  47 */     registerADFDomainBean(bean);
/*     */     
/*  49 */     bean = registerAsyncListener(bean, beanName);
/*     */     
/*  51 */     registerAuthenticationService(bean);
/*     */     
/*     */ 
/*     */ 
/*  55 */     registerDataIndexers(bean);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  62 */     return bean;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void registerADFDomainBean(Object bean)
/*     */   {
/*  69 */     ADFDomain dm = (ADFDomain)AnnotationUtils.getAnnotation(bean.getClass(), ADFDomain.class);
/*  70 */     if (dm == null)
/*  71 */       return;
/*  72 */     String path = dm.value();
/*  73 */     Map<String, String> externalKeyMap = new HashMap();
/*  74 */     Map<String, String> internalKeyMap = new HashMap();
/*  75 */     Field[] fields = bean.getClass().getDeclaredFields();
/*  76 */     for (Field field : fields)
/*     */     {
/*  78 */       ADFIgnore ignoreField = (ADFIgnore)field.getAnnotation(ADFIgnore.class);
/*  79 */       if ((ignoreField == null) || (!ignoreField.ignore()))
/*     */       {
/*  81 */         ADFField f = (ADFField)field.getAnnotation(ADFField.class);
/*  82 */         if (f != null)
/*     */         {
/*  84 */           externalKeyMap.put(field.getName(), f.value());
/*  85 */           internalKeyMap.put(f.value(), field.getName());
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  94 */     MetaService meta = MetaServiceFactory.createMetaService();
/*  95 */     meta.registerReadSchema(path, internalKeyMap, externalKeyMap);
/*  96 */     LogUtil.getCoreLog().debug("Register ADFDomain:{} into meta with columns:{}", new Object[] { bean.getClass().getSimpleName(), internalKeyMap });
/*     */   }
/*     */   
/*     */ 
/*     */   private void registerDataIndexers(Object bean)
/*     */   {
/* 102 */     if ((bean instanceof IGridIndexer))
/*     */     {
/*     */ 
/* 105 */       IGridIndexer indexer = (IGridIndexer)bean;
/* 106 */       GridIndexerFactory.registerIndexerPrivoder(indexer);
/*     */     }
/*     */   }
/*     */   
/*     */   private void registerAuthenticationService(Object bean)
/*     */   {
/* 112 */     if ((bean instanceof AuthService))
/*     */     {
/* 114 */       AuthService service = (AuthService)bean;
/* 115 */       AuthTokenManager.setAuthService(service);
/*     */     }
/*     */   }
/*     */   
/*     */   private Object registerAsyncListener(Object bean, String beanName)
/*     */   {
/* 121 */     if ((bean instanceof ADFAsyncListener))
/*     */     {
/* 123 */       ADFAsyncListener task = (ADFAsyncListener)bean;
/* 124 */       LogUtil.getCoreLog().debug("Regiser AsyncListener [{}] {}", new Object[] { beanName, bean });
/* 125 */       AsyncListenerManager.register(task);
/*     */     }
/* 127 */     return bean;
/*     */   }
/*     */   
/*     */ 
/*     */   public void onApplicationEvent(ContextRefreshedEvent event)
/*     */   {
/* 133 */     if (event.getApplicationContext().getParent() == null)
/*     */     {
/*     */ 
/* 136 */       LogUtil.getCoreLog().info("application context initial finished.");
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\config\context\InternalBeanPosterProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */