/*    */ package com.jnj.adf.dataservice.adfcoreignite.config;
/*    */ 
/*    */ import com.jnj.adf.config.xmls.ADFAnnotationConfigBeanDefinitionParser;
/*    */ import com.jnj.adf.config.xmls.ADFComponentScanBeanDefinitionParser;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.config.BeanDefinition;
/*    */ import org.springframework.beans.factory.xml.NamespaceHandlerSupport;
/*    */ import org.springframework.beans.factory.xml.ParserContext;
/*    */ import org.springframework.beans.factory.xml.XmlReaderContext;
/*    */ import org.springframework.data.gemfire.GemfireUtils;
/*    */ import org.w3c.dom.Element;
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
/*    */ public class ADFNamespaceHandler
/*    */   extends NamespaceHandlerSupport
/*    */ {
/* 35 */   protected static final List<String> GEMFIRE7_ELEMENTS = Arrays.asList(new String[] { "async-event-queue", "gateway-sender", "gateway-receiver" });
/*    */   
/*    */ 
/*    */ 
/*    */   public BeanDefinition parse(Element element, ParserContext parserContext)
/*    */   {
/* 41 */     boolean v7ElementsPresent = GEMFIRE7_ELEMENTS.contains(element.getLocalName());
/*    */     
/* 43 */     if (v7ElementsPresent)
/*    */     {
/* 45 */       if (!GemfireUtils.isGemfireVersion7OrAbove()) {
/* 46 */         String messagePrefix = String.format("Element '%1$s'", new Object[] { element.getLocalName() });
/* 47 */         parserContext.getReaderContext().error(
/* 48 */           String.format("%1$s requires GemFire version 7 or later. The current version is %2$s.", new Object[] { messagePrefix, GemfireUtils.GEMFIRE_VERSION }), null);
/*    */       }
/*    */     }
/*    */     
/*    */ 
/* 53 */     return super.parse(element, parserContext);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void init()
/*    */   {
/* 60 */     registerBeanDefinitionParser("annotation-driven", new ADFAnnotationConfigBeanDefinitionParser());
/* 61 */     registerBeanDefinitionParser("component-scan", new ADFComponentScanBeanDefinitionParser());
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\config\ADFNamespaceHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */