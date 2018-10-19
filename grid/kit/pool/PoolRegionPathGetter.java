/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.kit.pool;
/*    */ 
/*    */ import com.jnj.adf.grid.common.ADFException;
/*    */ import com.jnj.adf.grid.kit.IRegionPathGetter;
/*    */ import com.jnj.adf.grid.kit.RegionInfo;
/*    */ import com.jnj.adf.grid.master.manager.MasterRegionPathManager;
/*    */ 
/*    */ public class PoolRegionPathGetter
/*    */   extends AbstractRegister
/*    */   implements IRegionPathGetter
/*    */ {
/*    */   public RegionInfo getRegionPathInfo(String fullPath) throws ADFException
/*    */   {
/* 14 */     String gridId = MasterRegionPathManager.getInstance().getGridIdByRegionPath(fullPath);
/* 15 */     RegionInfo regionInfo = new RegionInfo();
/* 16 */     regionInfo.setGridId(gridId);
/* 17 */     regionInfo.setRegionPath(fullPath);
/* 18 */     return regionInfo;
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\kit\pool\PoolRegionPathGetter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */