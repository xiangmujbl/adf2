/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.utils;
/*     */ 
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.Arrays;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.springframework.context.ApplicationContext;
/*     */ import org.springframework.context.annotation.AnnotationConfigApplicationContext;
/*     */ import org.springframework.context.support.ClassPathXmlApplicationContext;
/*     */ import org.springframework.util.ReflectionUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SpringBeanUtils
/*     */ {
/*     */   public static final AnnotationConfigApplicationContext initContext(Class cls)
/*     */   {
/*  19 */     AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(new Class[] { cls });
/*  20 */     return ctx;
/*     */   }
/*     */   
/*     */   public static final ClassPathXmlApplicationContext initContext(boolean refresh, String... resources)
/*     */   {
/*  25 */     ClassPathXmlApplicationContext mainContext = new ClassPathXmlApplicationContext(resources, false);
/*  26 */     mainContext.setValidating(true);
/*  27 */     if (refresh)
/*     */     {
/*  29 */       mainContext.refresh();
/*     */     }
/*  31 */     LogUtil.getCoreLog().info("Spring initContext finished:{}" + Arrays.asList(resources));
/*  32 */     return mainContext;
/*     */   }
/*     */   
/*     */   public static final ClassPathXmlApplicationContext initContext(String... resources)
/*     */   {
/*  37 */     return initContext(true, resources);
/*     */   }
/*     */   
/*     */ 
/*     */   public static final ClassPathXmlApplicationContext childContext(boolean refresh, ApplicationContext context, String... resources)
/*     */   {
/*  43 */     if (!(context instanceof ClassPathXmlApplicationContext))
/*     */     {
/*  45 */       LogUtil.getAppLog().error("parameter context must be instanceof ClassPathXmlApplicationContext");
/*  46 */       return null;
/*     */     }
/*  48 */     ClassPathXmlApplicationContext mainContext = new ClassPathXmlApplicationContext(resources, false, context);
/*  49 */     mainContext.setValidating(true);
/*  50 */     if (refresh)
/*     */     {
/*  52 */       mainContext.refresh();
/*     */     }
/*  54 */     LogUtil.getCoreLog().info("Spring childContext finished:{}" + Arrays.asList(resources));
/*     */     
/*  56 */     return mainContext;
/*     */   }
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
/*     */   public static final ClassPathXmlApplicationContext childContext(ApplicationContext context, String... resources)
/*     */   {
/*  92 */     return childContext(true, context, resources);
/*     */   }
/*     */   
/*     */ 
/*     */   public static final ClassPathXmlApplicationContext addContext(boolean refresh, ApplicationContext context, String... resources)
/*     */   {
/*  98 */     if (!(context instanceof ClassPathXmlApplicationContext))
/*     */     {
/* 100 */       LogUtil.getAppLog().error("parameter context must be instanceof ClassPathXmlApplicationContext");
/* 101 */       return null;
/*     */     }
/* 103 */     ClassPathXmlApplicationContext applicationContext = (ClassPathXmlApplicationContext)context;
/*     */     
/*     */     try
/*     */     {
/* 107 */       Method m = ReflectionUtils.findMethod(ClassPathXmlApplicationContext.class, "getConfigLocations", new Class[0]);
/* 108 */       m.setAccessible(true);
/* 109 */       Object result = m.invoke(applicationContext, new Object[0]);
/* 110 */       if (result == null)
/*     */       {
/* 112 */         applicationContext.setConfigLocations(resources);
/*     */       }
/* 114 */       if ((result instanceof String[]))
/*     */       {
/* 116 */         String[] activeLocations = (String[])result;
/* 117 */         String[] newActiveLocations = new String[activeLocations.length + resources.length];
/* 118 */         System.arraycopy(activeLocations, 0, newActiveLocations, 0, activeLocations.length);
/* 119 */         System.arraycopy(resources, 0, newActiveLocations, activeLocations.length, resources.length);
/* 120 */         applicationContext.setConfigLocations(newActiveLocations);
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 125 */       LogUtil.getAppLog().error("applicationContext getConfigLocations", e);
/*     */     }
/*     */     
/* 128 */     if (refresh)
/*     */     {
/* 130 */       applicationContext.refresh();
/*     */     }
/* 132 */     LogUtil.getCoreLog().info("Spring childContext finished:{}" + Arrays.asList(resources));
/*     */     
/* 134 */     return applicationContext;
/*     */   }
/*     */   
/*     */   public static final ClassPathXmlApplicationContext addContext(ApplicationContext context, String... resources)
/*     */   {
/* 139 */     return childContext(true, context, resources);
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\utils\SpringBeanUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */