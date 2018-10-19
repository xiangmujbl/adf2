/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.client.api.impl;
/*    */ 
/*    */ import com.gemstone.gemfire.cache.execute.FunctionContext;
/*    */ import com.jnj.adf.grid.utils.KryoTools;
/*    */ import com.jnj.adf.grid.utils.KryoUtils;
/*    */ import java.util.Set;
/*    */ import org.springframework.data.gemfire.function.annotation.Filter;
/*    */ import org.springframework.data.gemfire.function.annotation.GemfireFunction;
/*    */ import org.springframework.data.gemfire.function.execution.RemoteParamter;
/*    */ import org.springframework.data.gemfire.remoteService.RemoteServiceHelper;
/*    */ import org.springframework.stereotype.Component;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public class RemoteServiceFunction
/*    */ {
/*    */   public static final String ON_SERVER_SERVICE = "adf.remoteServiceExecutor.onSever";
/*    */   public static final String ON_REGION_SERVICE = "adf.remoteServiceExecutor.onRegion";
/*    */   public static final String ON_SERVER_SERVICE_V2 = "adf.remoteServiceExecutor.onSever.v2";
/*    */   public static final String ON_REGION_SERVICE_V2 = "adf.remoteServiceExecutor.onRegion.v2";
/*    */   
/*    */   @GemfireFunction(id="adf.remoteServiceExecutor.onSever", hasResult=true, optimizeForWrite=true, HA=false)
/*    */   public Object invokeMethodOnServer(String path, String srvId, String methodName, Class[] parameterTypes, Object[] args, int filterPos, FunctionContext context)
/*    */   {
/* 49 */     RemoteParamter param = new RemoteParamter(path, srvId, methodName, args, parameterTypes, filterPos);
/* 50 */     return RemoteServiceHelper.invokeMethodLocally(param, null, context);
/*    */   }
/*    */   
/*    */   @GemfireFunction(id="adf.remoteServiceExecutor.onSever.v2", hasResult=true, optimizeForWrite=true, HA=false)
/*    */   public Object invokeMethodOnServer_V2(byte[] bytes, String srvId, Object[] args, Class[] paramTypes, FunctionContext context)
/*    */   {
/* 56 */     RemoteParamter param = null;
/*    */     try {
/* 58 */       param = (RemoteParamter)KryoTools.toObject(bytes, RemoteParamter.class);
/*    */     } catch (Exception e) {
/* 60 */       param = (RemoteParamter)KryoUtils.toObject(bytes);
/*    */     }
/* 62 */     param.setArgs(args);
/* 63 */     param.setParamTypes(paramTypes);
/* 64 */     return RemoteServiceHelper.invokeMethodLocally(param, null, context);
/*    */   }
/*    */   
/*    */ 
/*    */   @GemfireFunction(id="adf.remoteServiceExecutor.onRegion", hasResult=true, optimizeForWrite=true, HA=false)
/*    */   public Object invokeMethodOnRegionOptimizeForWrite(String path, String srvId, String methodName, Class[] parameterTypes, Object[] args, int filterPos, @Filter Set<?> filters, FunctionContext context)
/*    */   {
/* 71 */     RemoteParamter param = new RemoteParamter(path, srvId, methodName, args, parameterTypes, filterPos);
/*    */     
/* 73 */     return RemoteServiceHelper.invokeMethodLocally(param, filters, context);
/*    */   }
/*    */   
/*    */ 
/*    */   @GemfireFunction(id="adf.remoteServiceExecutor.onRegion.v2", hasResult=true, optimizeForWrite=true, HA=false)
/*    */   public Object invokeMethodOnRegionOptimizeForWrite_V2(byte[] bytes, String srvId, Object[] args, Class[] paramTypes, @Filter Set<?> filters, FunctionContext context)
/*    */   {
/* 80 */     RemoteParamter param = null;
/*    */     try {
/* 82 */       param = (RemoteParamter)KryoTools.toObject(bytes, RemoteParamter.class);
/*    */     } catch (Exception e) {
/* 84 */       param = (RemoteParamter)KryoUtils.toObject(bytes);
/*    */     }
/* 86 */     param.setArgs(args);
/* 87 */     param.setParamTypes(paramTypes);
/* 88 */     return RemoteServiceHelper.invokeMethodLocally(param, filters, context);
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\client\api\impl\RemoteServiceFunction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */