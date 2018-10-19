/*    */ package com.jnj.adf.dataservice.adfcoreignite.config.context;
/*    */ 
/*    */ import org.springframework.beans.factory.support.BeanDefinitionRegistry;
/*    */ import org.springframework.beans.factory.support.RootBeanDefinition;
/*    */ import org.springframework.context.annotation.AnnotationConfigUtils;
/*    */ 
/*    */ public class ADFBeanDefinitionRegistryRegister
/*    */ {
/*    */   private BeanDefinitionRegistry registry;
/*    */   
/*    */   public ADFBeanDefinitionRegistryRegister(BeanDefinitionRegistry registry)
/*    */   {
/* 13 */     this.registry = registry;
/* 14 */     AnnotationConfigUtils.registerAnnotationConfigProcessors(this.registry);
/*    */   }
/*    */   
/*    */   public void register(Class... typeClasses) {
/* 18 */     for (Class typeClass : typeClasses) {
/* 19 */       RootBeanDefinition beanDefinition = new RootBeanDefinition();
/* 20 */       beanDefinition.setBeanClass(typeClass);
/* 21 */       if (!this.registry.containsBeanDefinition(beanDefinition.getBeanClassName())) {
/* 22 */         this.registry.registerBeanDefinition(beanDefinition.getBeanClassName(), beanDefinition);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\config\context\ADFBeanDefinitionRegistryRegister.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */