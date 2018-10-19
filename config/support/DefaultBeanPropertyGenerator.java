/*    */ package com.jnj.adf.dataservice.adfcoreignite.config.support;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import java.util.Map.Entry;
/*    */ import java.util.Properties;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ public class DefaultBeanPropertyGenerator
/*    */   implements IPropertyGenerator
/*    */ {
/* 14 */   private Map<String, String> values = Maps.newHashMap();
/*    */   
/*    */   public Properties generator() {
/* 17 */     Properties pro = new Properties();
/* 18 */     Iterator<Entry<String, String>> it = this.values.entrySet().iterator();
/* 19 */     while (it.hasNext()) {
/* 20 */       Entry<String, String> entry = (Entry)it.next();
/* 21 */       pro.put(entry.getKey(), entry.getValue());
/*    */     }
/* 23 */     return pro;
/*    */   }
/*    */   
/*    */   public void setValues(Map<String, String> values) {
/* 27 */     this.values = values;
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\config\support\DefaultBeanPropertyGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */