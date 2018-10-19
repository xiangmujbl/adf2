/*    */ package com.jnj.adf.dataservice.adfcoreignite.config.context;
/*    */ 
/*    */ import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
/*    */ import org.springframework.beans.factory.config.BeanDefinitionHolder;
/*    */ import org.springframework.beans.factory.config.ConstructorArgumentValues;
/*    */ import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
/*    */ import org.springframework.beans.factory.support.BeanDefinitionRegistry;
/*    */ import org.springframework.beans.factory.support.RootBeanDefinition;
/*    */ import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
/*    */ import org.springframework.core.type.AnnotationMetadata;
/*    */ 
/*    */ 
/*    */ public class ADFClassPathBeanDefinitionScanner
/*    */   extends ClassPathBeanDefinitionScanner
/*    */ {
/*    */   public ADFClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry)
/*    */   {
/* 18 */     super(registry);
/*    */   }
/*    */   
/*    */   public ADFClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters) {
/* 22 */     super(registry, useDefaultFilters);
/*    */   }
/*    */   
/*    */   protected void registerBeanDefinition(BeanDefinitionHolder definitionHolder, BeanDefinitionRegistry registry)
/*    */   {
/* 27 */     AnnotatedBeanDefinition beanDefinition = (AnnotatedBeanDefinition)definitionHolder.getBeanDefinition();
/*    */     
/*    */ 
/*    */ 
/* 31 */     BeanDefinitionReaderUtils.registerBeanDefinition(definitionHolder, registry);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition)
/*    */   {
/* 39 */     boolean isIndependent = beanDefinition.getMetadata().isIndependent();
/* 40 */     if (!isIndependent) {
/* 41 */       return false;
/*    */     }
/*    */     
/*    */ 
/*    */ 
/* 46 */     return beanDefinition.getMetadata().isConcrete();
/*    */   }
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
/*    */   private void registerProxyChainFactoryBean(AnnotatedBeanDefinition beanDefinition, BeanDefinitionRegistry registry)
/*    */   {
/*    */     try
/*    */     {
/* 62 */       registerApplicationAwareBeanIfAbsent(registry);
/* 63 */       Class c = Class.forName(beanDefinition.getMetadata().getClassName());
/* 64 */       RootBeanDefinition factoryBeanDefinition = new RootBeanDefinition();
/*    */       
/* 66 */       ConstructorArgumentValues constructorArgumentValues = new ConstructorArgumentValues();
/* 67 */       constructorArgumentValues.addIndexedArgumentValue(0, c);
/* 68 */       factoryBeanDefinition.setConstructorArgumentValues(constructorArgumentValues);
/* 69 */       String beanName = c.getName() + ".Proxy$1";
/* 70 */       registry.registerBeanDefinition(beanName, factoryBeanDefinition);
/*    */     } catch (ClassNotFoundException e) {
/* 72 */       e.printStackTrace();
/*    */     } catch (Exception e) {
/* 74 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   private void registerApplicationAwareBeanIfAbsent(BeanDefinitionRegistry registry)
/*    */   {
/* 81 */     RootBeanDefinition beanDefinition = new RootBeanDefinition();
/* 82 */     beanDefinition.setBeanClass(ApplicationContextBeansAware.class);
/* 83 */     if (!registry.containsBeanDefinition(beanDefinition.getBeanClassName())) {
/* 84 */       registry.registerBeanDefinition(beanDefinition.getBeanClassName(), beanDefinition);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\config\context\ADFClassPathBeanDefinitionScanner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */