/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.manager;
/*    */ 
/*    */ import com.jnj.adf.grid.common.ADFException;
/*    */ import com.jnj.adf.grid.connect.GridConnection;
/*    */ import com.jnj.adf.grid.connect.domain.GridInfo;
/*    */ import com.jnj.adf.grid.connect.impl.GridConnectionImpl;
/*    */ import java.util.HashMap;
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
/*    */ public class ADFConnectFactory
/*    */ {
/*    */   public static enum ConnTypes
/*    */   {
/* 33 */     Client,  Server,  Hybrid;
/*    */     
/*    */     private ConnTypes() {} }
/* 36 */   private static final Map<String, Class<? extends GridConnection>> gridConnCreatorMap = new HashMap();
/*    */   
/*    */   static
/*    */   {
/* 40 */     gridConnCreatorMap.put(ConnTypes.Client.name(), GridConnectionImpl.class);
/*    */   }
/*    */   
/*    */   public static final void register(String name, Class<? extends GridConnection> clazz) {
/* 44 */     gridConnCreatorMap.put(name, clazz);
/*    */   }
/*    */   
/*    */   public static final GridConnection createConnection(GridInfo gi, ConnTypes type)
/*    */   {
/* 49 */     Class<? extends GridConnection> claz = (Class)gridConnCreatorMap.get(type.name());
/*    */     
/*    */     try
/*    */     {
/* 53 */       GridConnection connection = (GridConnection)claz.newInstance();
/* 54 */       connection.setGridInfo(gi);
/* 55 */       return connection;
/*    */     }
/*    */     catch (InstantiationException|IllegalAccessException e)
/*    */     {
/* 59 */       throw new ADFException(gi.toString(), e);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\manager\ADFConnectFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */