/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.manager;
/*     */ 
/*     */ import com.jnj.adf.client.api.IRemoteService;
/*     */ import com.jnj.adf.config.CglibRemoteServiceProxy;
/*     */ import com.jnj.adf.config.annotations.RemoteService;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.springframework.beans.BeansException;
/*     */ import org.springframework.context.ApplicationContext;
/*     */ import org.springframework.context.ApplicationContextAware;
/*     */ import org.springframework.core.annotation.AnnotationUtils;
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
/*     */ public class RemoteServiceManager
/*     */   implements ApplicationContextAware
/*     */ {
/*  36 */   private static final RemoteServiceManager INST = new RemoteServiceManager();
/*     */   private static ApplicationContext applicationContext;
/*     */   
/*     */   public static final RemoteServiceManager getInstance()
/*     */   {
/*  41 */     return INST;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public <T> IRemoteService<T> getRemoteService(String bizId)
/*     */   {
/*  51 */     if (applicationContext != null)
/*     */     {
/*  53 */       T bean = applicationContext.getBean(bizId);
/*  54 */       if (bean != null)
/*  55 */         return getRemoteService(bean);
/*     */     }
/*  57 */     return null;
/*     */   }
/*     */   
/*     */   public <T> IRemoteService<T> getRemoteService(Class<T> requiredType)
/*     */   {
/*  62 */     if (applicationContext != null)
/*     */     {
/*  64 */       T bean = applicationContext.getBean(requiredType);
/*  65 */       if (bean != null) {
/*  66 */         return getRemoteService(bean);
/*     */       }
/*     */     }
/*     */     try {
/*  70 */       T bean = requiredType.newInstance();
/*  71 */       return getRemoteService(bean);
/*     */     }
/*     */     catch (InstantiationException|IllegalAccessException e) {}
/*     */     
/*  75 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public <T> IRemoteService<T> getRemoteService(T bean)
/*     */   {
/*  82 */     if ((bean instanceof IRemoteService))
/*  83 */       return (IRemoteService)bean;
/*  84 */     RemoteService rs = (RemoteService)AnnotationUtils.getAnnotation(bean.getClass(), RemoteService.class);
/*  85 */     String name = rs.value();
/*  86 */     if (StringUtils.isEmpty(name))
/*     */     {
/*  88 */       String[] names = applicationContext.getBeanNamesForType(bean.getClass());
/*  89 */       if ((names != null) && (names.length > 0)) {
/*  90 */         name = names[0];
/*     */       }
/*     */     }
/*  93 */     if (StringUtils.isEmpty(name)) {
/*  94 */       name = StringUtils.uncapitalize(bean.getClass().getSimpleName());
/*     */     }
/*  96 */     CglibRemoteServiceProxy<T> proxy = new CglibRemoteServiceProxy();
/*  97 */     IRemoteService<T> srv = proxy.bind(bean, name);
/*  98 */     return srv;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setApplicationContext(ApplicationContext applicationContext)
/*     */     throws BeansException
/*     */   {
/* 106 */     applicationContext = applicationContext;
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\manager\RemoteServiceManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */