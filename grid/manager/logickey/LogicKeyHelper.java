/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.manager.logickey;
/*    */ 
/*    */ import com.gemstone.gemfire.cache.Region;
/*    */ import com.jnj.adf.grid.data.RegionDefine_v3;
/*    */ import com.jnj.adf.grid.manager.RegionHelper;
/*    */ import com.jnj.adf.grid.support.context.ADFRuntime;
/*    */ import java.util.UUID;
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
/*    */ public final class LogicKeyHelper
/*    */ {
/*    */   public static boolean enableLogicKey(String path)
/*    */   {
/* 27 */     if (ADFRuntime.getRuntime().isClientSide())
/*    */     {
/* 29 */       RegionDefine_v3 rd = RegionHelper.getRegionDefine(path);
/* 30 */       return (rd != null) && (rd.isEnableLogicKey());
/*    */     }
/*    */     
/* 33 */     Region<?, ?> region = RegionHelper.getRegion(path);
/* 34 */     if (region != null) {
/* 35 */       Object o = region.getUserAttribute();
/* 36 */       if (o != null) {
/* 37 */         RegionDefine_v3 rd = (RegionDefine_v3)o;
/* 38 */         return rd.isEnableLogicKey();
/*    */       }
/*    */     }
/* 41 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static LogicKey buildLogicKey(String rawKey)
/*    */   {
/* 52 */     LogicKey lk = new LogicKey();
/* 53 */     lk.setOriginalKey(rawKey);
/* 54 */     lk.setIdentityKey(UUID.randomUUID().toString());
/* 55 */     return lk;
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\manager\logickey\LogicKeyHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */