/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.utils;
/*    */ 
/*    */ import com.esotericsoftware.kryo.Kryo;
/*    */ import com.esotericsoftware.kryo.io.ByteBufferInput;
/*    */ import com.esotericsoftware.kryo.io.ByteBufferOutput;
/*    */ import com.esotericsoftware.kryo.io.Input;
/*    */ import com.esotericsoftware.kryo.io.Output;
/*    */ import com.esotericsoftware.kryo.serializers.CompatibleFieldSerializer;
/*    */ import com.gemstone.gemfire.internal.ClassPathLoader;
/*    */ import org.apache.commons.beanutils.PropertyUtils;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class KryoTools
/*    */ {
/*    */   public static final int defaultPoolSize = 16;
/*    */   public static final int buffSize = 4096;
/*    */   public static final int maxBuffSize = 65537;
/* 21 */   private static final ThreadLocal<Kryo> tl_kryo = new ThreadLocal()
/*    */   {
/*    */     protected Kryo initialValue() {
/* 24 */       Kryo kryo = new Kryo();
/*    */       
/* 26 */       kryo.setClassLoader(ClassPathLoader.getLatestAsClassLoader());
/* 27 */       kryo.setDefaultSerializer(CompatibleFieldSerializer.class);
/* 28 */       return kryo;
/*    */     }
/*    */   };
/*    */   
/*    */ 
/*    */   private static final Kryo findKryo()
/*    */   {
/* 35 */     Kryo kryo = (Kryo)tl_kryo.get();
/* 36 */     return kryo;
/*    */   }
/*    */   
/*    */   public static final <T> byte[] toBytes(T value) {
/* 40 */     Kryo kryo = findKryo();
/* 41 */     Output out = new ByteBufferOutput(4096, 1048576);
/*    */     try {
/* 43 */       kryo.writeObject(out, value);
/* 44 */       out.flush();
/* 45 */       byte[] bytes = out.toBytes();
/* 46 */       return bytes;
/*    */     } catch (Exception e) {
/* 48 */       LogUtil.getCoreLog().error("KryoTools toBytes error:{}", e);
/* 49 */       throw new RuntimeException(e);
/*    */     } finally {
/* 51 */       if (out != null) {
/* 52 */         out.close();
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static final <T> T toObject(byte[] bytes, Class<T> tClass)
/*    */   {
/* 63 */     Kryo kryo = findKryo();
/*    */     try {
/* 65 */       Input input = new ByteBufferInput(bytes);
/* 66 */       return (T)kryo.readObject(input, tClass);
/*    */     }
/*    */     catch (Exception e) {
/* 69 */       throw new RuntimeException(e);
/*    */     }
/*    */   }
/*    */   
/*    */   public static final <T> T toObject(byte[] bytes, T dest)
/*    */   {
/* 75 */     T from = toObject(bytes, dest.getClass());
/*    */     try
/*    */     {
/* 78 */       PropertyUtils.copyProperties(dest, from);
/*    */     }
/*    */     catch (Exception e)
/*    */     {
/* 82 */       e.printStackTrace();
/*    */     }
/* 84 */     return dest;
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\utils\KryoTools.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */