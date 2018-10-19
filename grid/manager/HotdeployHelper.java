/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.manager;
/*    */ 
/*    */ import com.jnj.adf.grid.utils.LogUtil;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.TreeMap;
/*    */ import org.apache.logging.log4j.Logger;
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
/*    */ public final class HotdeployHelper
/*    */ {
/* 31 */   protected static final Map<String, DeployableManager> managers = new HashMap();
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static final <T extends DeployableManager> T getInstance(ManagerTypes type)
/*    */   {
/* 39 */     return (DeployableManager)getInstance(type.name());
/*    */   }
/*    */   
/*    */   public static final void register(ManagerTypes type, DeployableManager mgr)
/*    */   {
/* 44 */     managers.put(type.name(), mgr);
/*    */   }
/*    */   
/*    */   public static final <T> T getInstance(String type)
/*    */   {
/* 49 */     DeployableManager mgm = (DeployableManager)managers.get(type);
/* 50 */     if (mgm == null)
/*    */     {
/* 52 */       managers.put(type, mgm);
/*    */     }
/* 54 */     return mgm;
/*    */   }
/*    */   
/*    */   public static final void dump()
/*    */   {
/* 59 */     for (String type : managers.keySet())
/*    */     {
/* 61 */       DeployableManager mgr = (DeployableManager)getInstance(type);
/* 62 */       TreeMap<String, Object> trs = new TreeMap(mgr.getRegistry());
/* 63 */       LogUtil.getCoreLog().info("Loaded {}:{}", new Object[] { type, trs });
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\manager\HotdeployHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */