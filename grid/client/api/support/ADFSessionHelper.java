/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.client.api.support;
/*     */ 
/*     */ import com.jnj.adf.client.api.GridBasicOperations;
/*     */ import com.jnj.adf.client.api.GridQueryOperations;
/*     */ import com.jnj.adf.grid.support.context.ADFSession;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import org.springframework.util.ReflectionUtils;
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
/*     */ public class ADFSessionHelper
/*     */ {
/*     */   static class ADFSessionMatcher
/*     */   {
/*  38 */     private static Map<MethodId, Method> methodIds = new HashMap();
/*     */     
/*  40 */     private static Map<Method, Boolean> cachedSessionFlag = new ConcurrentHashMap();
/*     */     
/*     */     public static final ADFSessionMatcher preProcess(Class<?> cls)
/*     */     {
/*  44 */       ADFSessionMatcher mc = new ADFSessionMatcher();
/*  45 */       mc.init(cls);
/*  46 */       return mc;
/*     */     }
/*     */     
/*     */     private final void init(Class<?> cls)
/*     */     {
/*  51 */       Method[] list = ReflectionUtils.getAllDeclaredMethods(cls);
/*  52 */       for (Method m : list)
/*     */       {
/*  54 */         if ((!ReflectionUtils.isObjectMethod(m)) && (!ReflectionUtils.isEqualsMethod(m)) && 
/*  55 */           (!ReflectionUtils.isHashCodeMethod(m)) && (!ReflectionUtils.isToStringMethod(m)))
/*     */         {
/*     */ 
/*     */ 
/*  59 */           methodIds.put(new MethodId(m), m);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     public static boolean withSession(Method m) {
/*  65 */       MethodId id = new MethodId(m);
/*  66 */       Method superM = (Method)methodIds.get(id);
/*  67 */       Boolean flag = (Boolean)cachedSessionFlag.get(m);
/*  68 */       if ((flag == null) || (!flag.booleanValue()))
/*     */       {
/*  70 */         if (superM != null)
/*  71 */           flag = Boolean.valueOf(superM.getAnnotation(ADFSession.class) != null);
/*  72 */         if ((flag == null) || (!flag.booleanValue()))
/*  73 */           flag = Boolean.valueOf(m.getAnnotation(ADFSession.class) != null);
/*  74 */         cachedSessionFlag.put(m, flag);
/*     */       }
/*  76 */       return flag.booleanValue();
/*     */     }
/*     */   }
/*     */   
/*     */   static class MethodId
/*     */   {
/*     */     private String name;
/*     */     private Class<?>[] parameterTypes;
/*     */     
/*     */     public MethodId(Method mhd)
/*     */     {
/*  87 */       this.name = mhd.getName();
/*  88 */       this.parameterTypes = mhd.getParameterTypes();
/*     */     }
/*     */     
/*     */     public String getName()
/*     */     {
/*  93 */       return this.name;
/*     */     }
/*     */     
/*     */     public void setName(String name)
/*     */     {
/*  98 */       this.name = name;
/*     */     }
/*     */     
/*     */     public Class<?>[] getParameterTypes()
/*     */     {
/* 103 */       return this.parameterTypes;
/*     */     }
/*     */     
/*     */     public void setParameterTypes(Class<?>[] parameterTypes)
/*     */     {
/* 108 */       this.parameterTypes = parameterTypes;
/*     */     }
/*     */     
/*     */ 
/*     */     public int hashCode()
/*     */     {
/* 114 */       int prime = 31;
/* 115 */       int result = 1;
/* 116 */       result = 31 * result + (this.name == null ? 0 : this.name.hashCode());
/* 117 */       result = 31 * result + Arrays.hashCode(this.parameterTypes);
/* 118 */       return result;
/*     */     }
/*     */     
/*     */ 
/*     */     public boolean equals(Object obj)
/*     */     {
/* 124 */       if (this == obj)
/* 125 */         return true;
/* 126 */       if (obj == null)
/* 127 */         return false;
/* 128 */       if (getClass() != obj.getClass())
/* 129 */         return false;
/* 130 */       MethodId other = (MethodId)obj;
/* 131 */       if (this.name == null)
/*     */       {
/* 133 */         if (other.name != null) {
/* 134 */           return false;
/*     */         }
/* 136 */       } else if (!this.name.equals(other.name))
/* 137 */         return false;
/* 138 */       if (!Arrays.equals(this.parameterTypes, other.parameterTypes))
/* 139 */         return false;
/* 140 */       return true;
/*     */     }
/*     */   }
/*     */   
/*     */   static
/*     */   {
/* 146 */     ADFSessionMatcher.preProcess(GridBasicOperations.class);
/* 147 */     ADFSessionMatcher.preProcess(GridQueryOperations.class);
/*     */   }
/*     */   
/*     */ 
/*     */   public static boolean withSession(Method m)
/*     */   {
/* 153 */     return ADFSessionMatcher.withSession(m);
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\client\api\support\ADFSessionHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */