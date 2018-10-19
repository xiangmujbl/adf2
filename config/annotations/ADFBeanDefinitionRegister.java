/*    */ package com.jnj.adf.dataservice.adfcoreignite.config.annotations;
/*    */ 
/*    */ import com.jnj.adf.config.context.ADFBeanDefinitionRegistryRegister;
/*    */ import com.jnj.adf.config.context.ADFClassPathBeanDefinitionScanner;
/*    */ import com.jnj.adf.config.context.ApplicationContextBeansAware;
/*    */ import com.jnj.adf.config.context.ApplicationStartedListener;
/*    */ import com.jnj.adf.config.context.InternalBeanInstantiationAwareBeanPostProcessor;
/*    */ import com.jnj.adf.config.context.InternalBeanPosterProcessor;
/*    */ import com.jnj.adf.config.support.BeanAdditionalPropertyGenerator;
/*    */ import com.jnj.adf.grid.support.context.ADFRuntime;
/*    */ import com.jnj.adf.grid.support.system.ADFConfigHelper;
/*    */ import org.apache.commons.lang3.StringUtils;
/*    */ import org.springframework.beans.factory.support.BeanDefinitionRegistry;
/*    */ import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
/*    */ import org.springframework.core.type.AnnotationMetadata;
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
/*    */ public class ADFBeanDefinitionRegister
/*    */   implements ImportBeanDefinitionRegistrar
/*    */ {
/* 39 */   final String ADF_DEFAULT_BASE_PACKAGE = "com.jnj.adf.grid,com.jnj.adf.client.api";
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry)
/*    */   {
/* 48 */     if (!ADFRuntime.getRuntime().isClientSide())
/*    */     {
/* 50 */       return;
/*    */     }
/*    */     
/* 53 */     ADFConfigHelper.initConfig();
/* 54 */     ADFConfigHelper.initLog4j();
/* 55 */     ADFBeanDefinitionRegistryRegister scanner = new ADFBeanDefinitionRegistryRegister(registry);
/* 56 */     Class[] typeClasses = { InternalBeanInstantiationAwareBeanPostProcessor.class, InternalBeanPosterProcessor.class, ApplicationStartedListener.class, ApplicationContextBeansAware.class };
/*    */     
/* 58 */     scanner.register(typeClasses);
/*    */     
/*    */ 
/* 61 */     scanner.register(new Class[] { BeanAdditionalPropertyGenerator.class });
/*    */     
/*    */ 
/* 64 */     ADFClassPathBeanDefinitionScanner classPathBeanDefinitionScanner = new ADFClassPathBeanDefinitionScanner(registry, true);
/*    */     
/* 66 */     if (StringUtils.isNotEmpty("com.jnj.adf.grid,com.jnj.adf.client.api"))
/*    */     {
/* 68 */       String[] basePackages = "com.jnj.adf.grid,com.jnj.adf.client.api".split(",");
/* 69 */       classPathBeanDefinitionScanner.scan(basePackages);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\config\annotations\ADFBeanDefinitionRegister.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */