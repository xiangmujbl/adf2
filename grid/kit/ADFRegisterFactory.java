/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.kit;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import com.jnj.adf.grid.kit.pool.PoolAuthenticatGetter;
/*    */ import com.jnj.adf.grid.kit.pool.PoolRegionPathGetter;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ADFRegisterFactory
/*    */ {
/* 17 */   private static ADFRegisterFactory INST = new ADFRegisterFactory();
/*    */   
/* 19 */   private static Map<Class, Object> defaultRegisterMap = Maps.newConcurrentMap();
/*    */   
/*    */   static {
/* 22 */     defaultRegisterMap.put(IRegionPathGetter.class, new PoolRegionPathGetter());
/* 23 */     defaultRegisterMap.put(IAuthenticateGetter.class, new PoolAuthenticatGetter());
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static ADFRegisterFactory getInstance()
/*    */   {
/* 32 */     return INST;
/*    */   }
/*    */   
/*    */   public <T> void register(Class<T> klass, T inst) {
/* 36 */     defaultRegisterMap.put(klass, inst);
/*    */   }
/*    */   
/*    */   public <T> T getRegister(Class<T> klass) {
/* 40 */     if (defaultRegisterMap.containsKey(klass))
/*    */     {
/* 42 */       return (T)defaultRegisterMap.get(klass);
/*    */     }
/* 44 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\kit\ADFRegisterFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */