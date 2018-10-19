/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.client.api.support;
/*    */ 
/*    */ import java.lang.reflect.Method;
/*    */ import java.util.Arrays;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ProxyHelper
/*    */ {
/*    */   public static final String toString = "toString";
/* 34 */   public static final Map<Class, List<Method>> methodCache = new HashMap();
/*    */   
/*    */ 
/*    */   public static final boolean isToString(Method m)
/*    */   {
/* 39 */     return "toString".equals(m.getName());
/*    */   }
/*    */   
/*    */ 
/*    */   public static final boolean definedBy(Method m, Class... interaces)
/*    */   {
/* 45 */     for (Class inf : interaces)
/*    */     {
/* 47 */       List<Method> methods = (List)methodCache.get(inf);
/* 48 */       if (methods == null)
/*    */       {
/* 50 */         methods = Arrays.asList(inf.getDeclaredMethods());
/* 51 */         methodCache.put(inf, methods);
/*    */       }
/* 53 */       if (methods.contains(m)) {
/* 54 */         return true;
/*    */       }
/*    */     }
/* 57 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\client\api\support\ProxyHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */