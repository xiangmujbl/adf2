/*    */ package com.jnj.adf.dataservice.adfcoreignite.config.xmls;
/*    */ 
/*    */ import com.jnj.adf.config.context.ADFClassPathBeanDefinitionScanner;
/*    */ import org.springframework.beans.factory.xml.BeanDefinitionParser;
/*    */ import org.springframework.beans.factory.xml.XmlReaderContext;
/*    */ import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
/*    */ import org.springframework.context.annotation.ComponentScanBeanDefinitionParser;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ADFComponentScanBeanDefinitionParser
/*    */   extends ComponentScanBeanDefinitionParser
/*    */   implements BeanDefinitionParser
/*    */ {
/*    */   protected ClassPathBeanDefinitionScanner createScanner(XmlReaderContext readerContext, boolean useDefaultFilters)
/*    */   {
/* 19 */     return new ADFClassPathBeanDefinitionScanner(readerContext.getRegistry(), useDefaultFilters);
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\config\xmls\ADFComponentScanBeanDefinitionParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */