/*    */ package com.jnj.adf.dataservice.adfcoreignite.config.support;
/*    */ 
/*    */ import com.jnj.adf.grid.support.system.ADFConfigHelper;
/*    */ import java.util.Properties;
/*    */ import org.apache.commons.lang3.StringUtils;
/*    */ import org.springframework.beans.BeansException;
/*    */ import org.springframework.context.ApplicationContext;
/*    */ import org.springframework.context.ApplicationContextAware;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BeanAdditionalPropertyGenerator
/*    */   implements ApplicationContextAware
/*    */ {
/*    */   private ApplicationContext context;
/* 17 */   private String generatorBeanName = null;
/*    */   
/*    */ 
/*    */   public BeanAdditionalPropertyGenerator() {}
/*    */   
/*    */   public BeanAdditionalPropertyGenerator(String customGeneratorName)
/*    */   {
/* 24 */     this.generatorBeanName = customGeneratorName;
/*    */   }
/*    */   
/*    */   public void init() {
/* 28 */     if ((StringUtils.isNotEmpty(this.generatorBeanName)) && 
/* 29 */       (this.context.containsBean(this.generatorBeanName))) {
/* 30 */       IPropertyGenerator generator = (IPropertyGenerator)this.context.getBean(this.generatorBeanName, IPropertyGenerator.class);
/* 31 */       Properties properties = generator.generator();
/* 32 */       ADFConfigHelper.putAll(properties);
/*    */     }
/*    */   }
/*    */   
/*    */   public void setApplicationContext(ApplicationContext applicationContext)
/*    */     throws BeansException
/*    */   {
/* 39 */     this.context = applicationContext;
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\config\support\BeanAdditionalPropertyGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */