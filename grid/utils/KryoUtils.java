/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.utils;
/*     */ 
/*     */ import com.esotericsoftware.kryo.Kryo;
/*     */ import com.esotericsoftware.kryo.io.ByteBufferInput;
/*     */ import com.esotericsoftware.kryo.io.ByteBufferOutput;
/*     */ import com.esotericsoftware.kryo.io.Input;
/*     */ import com.esotericsoftware.kryo.io.Output;
/*     */ import com.gemstone.gemfire.internal.ClassPathLoader;
/*     */ import com.jnj.adf.grid.common.ADFException;
/*     */ import com.jnj.adf.grid.connect.domain.GridInfo;
/*     */ import java.io.PrintStream;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import org.apache.commons.beanutils.PropertyUtils;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class KryoUtils
/*     */ {
/*     */   public static final int defaultPoolSize = 16;
/*     */   public static final int buffSize = 4096;
/*     */   public static final int maxBuffSize = 65537;
/*  25 */   private static final ThreadLocal<Kryo> tl_kryo = new ThreadLocal()
/*     */   {
/*     */     protected Kryo initialValue() {
/*  28 */       Kryo kryo = new Kryo();
/*  29 */       kryo.setClassLoader(ClassPathLoader.getLatestAsClassLoader());
/*  30 */       return kryo;
/*     */     }
/*     */   };
/*     */   
/*     */   public static void main(String[] args)
/*     */   {
/*  36 */     GridInfo gi = new GridInfo();
/*  37 */     gi.setGridName("madf");
/*  38 */     byte[] bytes = toBytes(gi);
/*  39 */     for (int i = 0; i < 10; i++)
/*     */     {
/*  41 */       GridInfo gi2 = (GridInfo)toObject(bytes);
/*  42 */       System.out.println(gi2);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final Kryo findKryo()
/*     */   {
/*  55 */     Kryo kryo = (Kryo)tl_kryo.get();
/*  56 */     return kryo;
/*     */   }
/*     */   
/*     */   public static final <T> byte[] toBytes(T value)
/*     */   {
/*  61 */     Kryo kryo = findKryo();
/*  62 */     Output out = new ByteBufferOutput(4096, 1048576);
/*     */     try
/*     */     {
/*  65 */       kryo.writeClassAndObject(out, value);
/*  66 */       out.flush();
/*  67 */       byte[] bytes = out.toBytes();
/*  68 */       return bytes;
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*  72 */       LogUtil.getCoreLog().error(e);
/*  73 */       throw new ADFException(e);
/*     */     }
/*     */     finally
/*     */     {
/*  77 */       if (out != null) {
/*  78 */         out.close();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static final <T> T toObject(byte[] bytes, T dest)
/*     */   {
/*  85 */     T from = toObject(bytes);
/*     */     try
/*     */     {
/*  88 */       PropertyUtils.copyProperties(dest, from);
/*     */     }
/*     */     catch (IllegalAccessException|InvocationTargetException|NoSuchMethodException e)
/*     */     {
/*  92 */       e.printStackTrace();
/*     */     }
/*  94 */     return dest;
/*     */   }
/*     */   
/*     */ 
/*     */   public static final <T> T toObject(byte[] bytes)
/*     */   {
/* 100 */     Kryo kryo = findKryo();
/*     */     try
/*     */     {
/* 103 */       Input input = new ByteBufferInput(bytes);
/* 104 */       return (T)kryo.readClassAndObject(input);
/*     */ 
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 109 */       LogUtil.getCoreLog().error(e);
/* 110 */       throw new ADFException(e);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\utils\KryoUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */