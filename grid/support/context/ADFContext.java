/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.support.context;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import org.springframework.beans.BeansException;
/*     */ import org.springframework.beans.factory.NoSuchBeanDefinitionException;
/*     */ import org.springframework.context.ApplicationContext;
/*     */ import org.springframework.context.ApplicationContextAware;
/*     */ import org.springframework.context.support.AbstractApplicationContext;
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
/*     */ @Component
/*     */ public class ADFContext
/*     */   implements ApplicationContextAware
/*     */ {
/*     */   private static ApplicationContext context;
/*     */   
/*     */   public static final ApplicationContext getParent()
/*     */   {
/*  39 */     return context;
/*     */   }
/*     */   
/*     */   public static final void addChild(AbstractApplicationContext ac)
/*     */   {
/*  44 */     ac.setParent(context);
/*     */   }
/*     */   
/*     */   public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
/*     */   {
/*  49 */     context = applicationContext;
/*     */   }
/*     */   
/*     */   public static final <T> T getBean(Class<T> requiredType)
/*     */   {
/*  54 */     return (T)getBean(requiredType, null);
/*     */   }
/*     */   
/*     */   public static final <T> T getBean(String name, Class<T> requiredType)
/*     */   {
/*  59 */     if (context != null)
/*     */     {
/*  61 */       return (T)context.getBean(name, requiredType);
/*     */     }
/*  63 */     return null;
/*     */   }
/*     */   
/*     */   public static final <T> T getBean(String id)
/*     */   {
/*  68 */     if ((context != null) && (context.containsBean(id)))
/*     */     {
/*  70 */       return (T)context.getBean(id);
/*     */     }
/*  72 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public static final <T> T getBean(Class<T> requiredType, Class<T> targetClass)
/*     */   {
/*  78 */     if (context != null)
/*     */     {
/*     */       try
/*     */       {
/*  82 */         return (T)context.getBean(requiredType);
/*     */       }
/*     */       catch (NoSuchBeanDefinitionException e)
/*     */       {
/*     */         try
/*     */         {
/*  88 */           if (targetClass != null) {
/*  89 */             return (T)targetClass.newInstance();
/*     */           }
/*     */         }
/*     */         catch (InstantiationException|IllegalAccessException localInstantiationException) {}
/*     */       }
/*     */     }
/*     */     
/*  96 */     return null;
/*     */   }
/*     */   
/*     */   public static final <T> Iterator<T> getBeans(Class<T> requiredType)
/*     */   {
/* 101 */     if (context != null) {
/*     */       try {
/* 103 */         Map<String, T> beans = context.getBeansOfType(requiredType);
/* 104 */         if (beans != null) {
/* 105 */           return beans.values().iterator();
/*     */         }
/*     */       }
/*     */       catch (NoSuchBeanDefinitionException localNoSuchBeanDefinitionException) {}
/*     */     }
/*     */     
/* 111 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\support\context\ADFContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */