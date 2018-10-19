/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.utils;
/*     */ 
/*     */ import com.fasterxml.jackson.databind.DeserializationFeature;
/*     */ import com.fasterxml.jackson.databind.ObjectMapper;
/*     */ import com.fasterxml.jackson.databind.SerializationFeature;
/*     */ import com.jnj.adf.grid.data.temporal.TemporalRKey;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.apache.logging.log4j.Logger;
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
/*     */ 
/*     */ 
/*     */ public final class JsonUtils
/*     */ {
/*  37 */   private static final ObjectMapper mapper = new ObjectMapper();
/*  38 */   static { mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
/*  39 */     mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
/*     */   }
/*     */   
/*     */   public static final <T> T jsonToObject(byte[] bytes, Class<T> classType)
/*     */   {
/*  44 */     mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
/*  45 */     T value = null;
/*     */     try
/*     */     {
/*  48 */       value = mapper.readValue(bytes, classType);
/*     */     }
/*     */     catch (IOException e)
/*     */     {
/*  52 */       LogUtil.getCoreLog().warn("Json bytes convert to class {} failed", new Object[] { classType.getName(), e });
/*     */     }
/*  54 */     return value;
/*     */   }
/*     */   
/*     */ 
/*     */   public static final <T> T jsonToObject(String jsonString, Class<T> classType)
/*     */   {
/*  60 */     T value = null;
/*     */     try
/*     */     {
/*  63 */       if (!StringUtils.isEmpty(jsonString)) {
/*  64 */         value = mapper.readValue(jsonString, classType);
/*     */       }
/*     */     }
/*     */     catch (IOException e) {
/*  68 */       LogUtil.getCoreLog().warn("Json string {} convert to class {} failed", new Object[] { jsonString, classType.getName(), e });
/*     */     }
/*  70 */     return value;
/*     */   }
/*     */   
/*     */ 
/*     */   public static final String objectToJson(Object value)
/*     */   {
/*  76 */     if ((value instanceof String))
/*  77 */       return (String)value;
/*  78 */     String str = null;
/*     */     try
/*     */     {
/*  81 */       str = mapper.writeValueAsString(value);
/*     */     }
/*     */     catch (IOException e)
/*     */     {
/*  85 */       LogUtil.getCoreLog().warn("Object convert to string failed", e);
/*     */     }
/*  87 */     return str;
/*     */   }
/*     */   
/*     */   public static final byte[] objectToBytes(Object value)
/*     */   {
/*  92 */     byte[] dataBytes = null;
/*     */     try
/*     */     {
/*  95 */       dataBytes = mapper.writeValueAsBytes(value);
/*     */     }
/*     */     catch (IOException e)
/*     */     {
/*  99 */       LogUtil.getCoreLog().warn("Object convert to byte[] failed", e);
/*     */     }
/* 101 */     return dataBytes;
/*     */   }
/*     */   
/*     */   public static final <T> T convertValue(Object value, Class<T> toClass)
/*     */   {
/*     */     try
/*     */     {
/* 108 */       T resutl = null;
/* 109 */       if (value != null) {}
/* 110 */       return (T)mapper.convertValue(value, toClass);
/*     */ 
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 115 */       LogUtil.getCoreLog().warn("Object convert to {} failed", new Object[] { toClass.getSimpleName(), e });
/*     */     }
/* 117 */     return null;
/*     */   }
/*     */   
/*     */   public static void main(String[] args)
/*     */   {
/* 122 */     String address = "192.168.120.82[12244]";
/* 123 */     byte[] aaa = objectToBytes(address);
/* 124 */     System.out.println((String)jsonToObject(aaa, String.class));
/* 125 */     System.out.println(jsonToObject("{\"uuid\":\"9ad6a506-fa62-42af-96c2-9e0826f0f95f\",\"identityKey\":\"k1\",\"name\":\"com.jnj.adf.grid.data.temporal.TemporalRKey\"}", TemporalRKey.class));
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\utils\JsonUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */