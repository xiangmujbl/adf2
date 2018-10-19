/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.kit;
/*    */ 
/*    */ import com.jnj.adf.grid.common.GridPathNameUtil;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RemoteServiceInfo
/*    */   implements Serializable
/*    */ {
/*    */   private String gridId;
/*    */   private String serviceName;
/*    */   
/*    */   public RemoteServiceInfo() {}
/*    */   
/*    */   public RemoteServiceInfo(String gridId, String serviceName)
/*    */   {
/* 19 */     this.gridId = gridId;
/* 20 */     this.serviceName = GridPathNameUtil.formatPathWithPrefix(serviceName);
/*    */   }
/*    */   
/*    */   public String getServiceName() {
/* 24 */     return this.serviceName;
/*    */   }
/*    */   
/*    */   public void setServiceName(String serviceName) {
/* 28 */     this.serviceName = serviceName;
/*    */   }
/*    */   
/*    */   public String getGridId() {
/* 32 */     return this.gridId;
/*    */   }
/*    */   
/*    */   public void setGridId(String gridId) {
/* 36 */     this.gridId = gridId;
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\kit\RemoteServiceInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */