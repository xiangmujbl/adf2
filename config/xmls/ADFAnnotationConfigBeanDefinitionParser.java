/*     */ package com.jnj.adf.dataservice.adfcoreignite.config.xmls;
/*     */ 
/*     */ import com.jnj.adf.config.context.ADFBeanDefinitionRegistryRegister;
/*     */ import com.jnj.adf.config.context.ADFClassPathBeanDefinitionScanner;
/*     */ import com.jnj.adf.config.context.ApplicationContextBeansAware;
/*     */ import com.jnj.adf.config.context.ApplicationStartedListener;
/*     */ import com.jnj.adf.config.context.InternalBeanPosterProcessor;
/*     */ import com.jnj.adf.config.support.BeanAdditionalPropertyGenerator;
/*     */ import com.jnj.adf.grid.support.context.ADFRuntime;
/*     */ import com.jnj.adf.grid.support.system.ADFConfigHelper;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.springframework.aop.config.AopConfigUtils;
/*     */ import org.springframework.beans.factory.config.BeanDefinition;
/*     */ import org.springframework.beans.factory.config.ConstructorArgumentValues;
/*     */ import org.springframework.beans.factory.support.BeanDefinitionRegistry;
/*     */ import org.springframework.beans.factory.support.RootBeanDefinition;
/*     */ import org.springframework.beans.factory.xml.BeanDefinitionParser;
/*     */ import org.springframework.beans.factory.xml.ParserContext;
/*     */ import org.springframework.data.gemfire.remoteService.RemoteServiceBeanDefinitionRegistrar;
/*     */ import org.w3c.dom.Element;
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
/*     */ public class ADFAnnotationConfigBeanDefinitionParser
/*     */   implements BeanDefinitionParser
/*     */ {
/*  46 */   final String ADF_DEFAULT_BASE_PACKAGE = "com.jnj.adf.grid,com.jnj.adf.client.api";
/*     */   
/*     */ 
/*     */   public BeanDefinition parse(Element element, ParserContext parserContext)
/*     */   {
/*  51 */     if ((!ADFRuntime.getRuntime().isClientSide()) && (!ADFRuntime.getRuntime().isLocatorSide()))
/*     */     {
/*  53 */       return null;
/*     */     }
/*  55 */     ADFConfigHelper.initConfig();
/*  56 */     ADFConfigHelper.initLog4j();
/*  57 */     BeanDefinitionRegistry registry = parserContext.getRegistry();
/*  58 */     String propertyGeneratorName = element.getAttribute("property-generator");
/*     */     
/*  60 */     registerAopNamespace(parserContext, false);
/*     */     
/*  62 */     ADFBeanDefinitionRegistryRegister register = new ADFBeanDefinitionRegistryRegister(registry);
/*  63 */     Class[] typeClasses = { InternalBeanPosterProcessor.class, ApplicationStartedListener.class, ApplicationContextBeansAware.class };
/*     */     
/*  65 */     register.register(typeClasses);
/*     */     
/*     */ 
/*  68 */     RootBeanDefinition beanDefinition = new RootBeanDefinition();
/*  69 */     beanDefinition.setBeanClass(BeanAdditionalPropertyGenerator.class);
/*  70 */     ConstructorArgumentValues argumentValues = new ConstructorArgumentValues();
/*  71 */     argumentValues.addIndexedArgumentValue(0, propertyGeneratorName);
/*  72 */     beanDefinition.setConstructorArgumentValues(argumentValues);
/*  73 */     if (!registry.containsBeanDefinition(beanDefinition.getBeanClassName()))
/*     */     {
/*  75 */       registry.registerBeanDefinition(beanDefinition.getBeanClassName(), beanDefinition);
/*     */     }
/*     */     
/*  78 */     beanDefinition = new RootBeanDefinition();
/*  79 */     beanDefinition.setBeanClass(RemoteServiceBeanDefinitionRegistrar.class);
/*  80 */     if (!registry.containsBeanDefinition(beanDefinition.getBeanClassName())) {
/*  81 */       registry.registerBeanDefinition(beanDefinition.getBeanClassName(), beanDefinition);
/*     */     }
/*     */     
/*     */ 
/*  85 */     ADFClassPathBeanDefinitionScanner classPathBeanDefinitionScanner = new ADFClassPathBeanDefinitionScanner(registry, true);
/*     */     
/*  87 */     if (StringUtils.isNotEmpty("com.jnj.adf.grid,com.jnj.adf.client.api"))
/*     */     {
/*  89 */       String[] basePackages = "com.jnj.adf.grid,com.jnj.adf.client.api".split(",");
/*  90 */       classPathBeanDefinitionScanner.scan(basePackages);
/*     */     }
/*  92 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   private void registerAopNamespace(ParserContext parserContext, boolean proxyTargetClass)
/*     */   {
/*  98 */     BeanDefinitionRegistry registry = parserContext.getRegistry();
/*  99 */     AopConfigUtils.registerAspectJAnnotationAutoProxyCreatorIfNecessary(registry);
/* 100 */     if (proxyTargetClass)
/*     */     {
/* 102 */       AopConfigUtils.forceAutoProxyCreatorToUseClassProxying(registry);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\config\xmls\ADFAnnotationConfigBeanDefinitionParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */