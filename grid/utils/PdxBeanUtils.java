/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.utils;
/*    */ 
/*    */ import com.gemstone.gemfire.pdx.PdxInstance;
/*    */ import com.gemstone.gemfire.pdx.internal.PdxInstanceEnum;
/*    */ import com.google.common.collect.Sets;
/*    */ import com.jnj.adf.grid.common.PdxUtils;
/*    */ import java.beans.BeanInfo;
/*    */ import java.beans.Introspector;
/*    */ import java.beans.PropertyDescriptor;
/*    */ import java.io.PrintStream;
/*    */ import java.lang.reflect.Method;
/*    */ import java.util.Collection;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ public class PdxBeanUtils
/*    */ {
/*    */   public static Map<String, Object> convert2Map(Object obj)
/*    */   {
/* 22 */     Map<String, Object> result = new HashMap();
/*    */     try {
/* 24 */       BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
/* 25 */       PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
/* 26 */       for (PropertyDescriptor property : propertyDescriptors) {
/* 27 */         String key = property.getName();
/* 28 */         if (!key.equals("class")) {
/* 29 */           Method getter = property.getReadMethod();
/* 30 */           Object value = getter.invoke(obj, new Object[0]);
/* 31 */           result.put(key, value);
/*    */         }
/*    */       }
/*    */     } catch (Exception e) {
/* 35 */       System.out.println("convert2Map Error " + e);
/*    */     }
/* 37 */     return result;
/*    */   }
/*    */   
/*    */ 
/*    */   public static void convert2Bean(Map<String, Object> map, Object obj)
/*    */   {
/*    */     try
/*    */     {
/* 45 */       BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
/* 46 */       PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
/* 47 */       for (PropertyDescriptor property : propertyDescriptors) {
/* 48 */         String key = property.getName();
/* 49 */         if ((!key.equals("class")) && (map.containsKey(key))) {
/* 50 */           Object value = map.get(key);
/* 51 */           Method setter = property.getWriteMethod();
/* 52 */           if ((property.getPropertyType().isEnum()) && (!value.getClass().isEnum()))
/*    */           {
/*    */ 
/* 55 */             Class c1 = property.getPropertyType();
/* 56 */             String enumName = null;
/* 57 */             if ((value instanceof PdxInstanceEnum)) {
/* 58 */               enumName = ((PdxInstanceEnum)value).getName();
/* 59 */             } else if ((value instanceof String)) {
/* 60 */               enumName = (String)value;
/*    */             }
/* 62 */             if (enumName != null) {
/* 63 */               setter.invoke(obj, new Object[] { Enum.valueOf(c1, enumName) });
/*    */             }
/* 65 */           } else if (property.getPropertyType().isAssignableFrom(Set.class))
/*    */           {
/* 67 */             if ((value instanceof Set)) {
/* 68 */               setter.invoke(obj, new Object[] { value });
/* 69 */             } else if ((value instanceof Collection)) {
/* 70 */               Set v = Sets.newHashSet(((Collection)value).iterator());
/* 71 */               setter.invoke(obj, new Object[] { v });
/*    */             }
/*    */           } else {
/* 74 */             setter.invoke(obj, new Object[] { value });
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/*    */     catch (Exception e) {
/* 80 */       System.out.println("convert2Bean Error " + e);
/*    */     }
/*    */   }
/*    */   
/*    */   public static PdxInstance convert2Pdx(Object value) {
/* 85 */     byte[] bytes = JsonUtils.objectToBytes(value);
/* 86 */     PdxInstance pdx = PdxUtils.fromJson(bytes);
/* 87 */     return pdx;
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\utils\PdxBeanUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */