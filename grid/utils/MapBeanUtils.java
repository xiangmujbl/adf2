/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.utils;
/*    */ 
/*    */ import com.gemstone.gemfire.pdx.PdxInstance;
/*    */ import com.gemstone.gemfire.pdx.internal.PdxInstanceEnum;
/*    */ import com.jnj.adf.grid.common.PdxUtils;
/*    */ import java.beans.BeanInfo;
/*    */ import java.beans.Introspector;
/*    */ import java.beans.PropertyDescriptor;
/*    */ import java.io.PrintStream;
/*    */ import java.lang.reflect.Method;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MapBeanUtils
/*    */ {
/*    */   public static Map<String, Object> convert2Map(Object obj)
/*    */   {
/* 20 */     Map<String, Object> result = new HashMap();
/*    */     try {
/* 22 */       BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
/* 23 */       PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
/* 24 */       for (PropertyDescriptor property : propertyDescriptors) {
/* 25 */         String key = property.getName();
/* 26 */         if (!key.equals("class")) {
/* 27 */           Method getter = property.getReadMethod();
/* 28 */           Object value = getter.invoke(obj, new Object[0]);
/* 29 */           result.put(key, value);
/*    */         }
/*    */       }
/*    */     } catch (Exception e) {
/* 33 */       System.out.println("convert2Map Error " + e);
/*    */     }
/* 35 */     return result;
/*    */   }
/*    */   
/*    */ 
/*    */   public static void convert2Bean(Map<String, Object> map, Object obj)
/*    */   {
/*    */     try
/*    */     {
/* 43 */       BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
/* 44 */       PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
/* 45 */       for (PropertyDescriptor property : propertyDescriptors) {
/* 46 */         String key = property.getName();
/* 47 */         if ((!key.equals("class")) && (map.containsKey(key))) {
/* 48 */           Object value = map.get(key);
/* 49 */           Method setter = property.getWriteMethod();
/* 50 */           if ((property.getPropertyType().isEnum()) && (!value.getClass().isEnum()))
/*    */           {
/*    */ 
/* 53 */             Class c1 = property.getPropertyType();
/* 54 */             String enumName = null;
/* 55 */             if ((value instanceof PdxInstanceEnum)) {
/* 56 */               enumName = ((PdxInstanceEnum)value).getName();
/* 57 */             } else if ((value instanceof String)) {
/* 58 */               enumName = (String)value;
/*    */             }
/* 60 */             if (enumName != null) {
/* 61 */               setter.invoke(obj, new Object[] { Enum.valueOf(c1, enumName) });
/*    */             }
/*    */           } else {
/* 64 */             setter.invoke(obj, new Object[] { value });
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/*    */     catch (Exception e) {
/* 70 */       System.out.println("convert2Bean Error " + e);
/*    */     }
/*    */   }
/*    */   
/*    */   public static PdxInstance convert2Pdx(Object value) {
/* 75 */     byte[] bytes = JsonUtils.objectToBytes(value);
/* 76 */     PdxInstance pdx = PdxUtils.fromJson(bytes);
/* 77 */     return pdx;
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\utils\MapBeanUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */