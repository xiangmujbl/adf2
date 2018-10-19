/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.client.api.impl;
/*     */ 
/*     */ import com.gemstone.gemfire.cache.Region;
/*     */ import com.jnj.adf.client.api.ADFOperations;
/*     */ import com.jnj.adf.client.api.ADFService;
/*     */ import com.jnj.adf.client.api.IBiz;
/*     */ import com.jnj.adf.client.api.IRemoteService;
/*     */ import com.jnj.adf.grid.auth.AuthService;
/*     */ import com.jnj.adf.grid.auth.impl.AuthSessionContextHelper;
/*     */ import com.jnj.adf.grid.auth.session.AuthInternalSession;
/*     */ import com.jnj.adf.grid.auth.session.AuthInternalSessionImpl;
/*     */ import com.jnj.adf.grid.client.api.support.ADFServiceContext;
/*     */ import com.jnj.adf.grid.client.api.support.ADFServiceContext.Keys;
/*     */ import com.jnj.adf.grid.client.api.support.ADFServiceContext.QueryTypes;
/*     */ import com.jnj.adf.grid.connect.ConnectService;
/*     */ import com.jnj.adf.grid.data.RegionDefine_v3;
/*     */ import com.jnj.adf.grid.manager.RegionHelper;
/*     */ import com.jnj.adf.grid.manager.RemoteServiceManager;
/*     */ import com.jnj.adf.grid.support.gemfire.DataSerializerExt;
/*     */ import com.jnj.adf.grid.utils.LogUtil;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.IOException;
/*     */ import javax.annotation.Resource;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.springframework.stereotype.Component;
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
/*     */ @Component
/*     */ public class ADFServiceImpl
/*     */   extends GridAccessor
/*     */   implements ADFService
/*     */ {
/*     */   @Resource
/*     */   public ADFOperations adfOp;
/*     */   @Resource
/*     */   public ConnectService connectService;
/*     */   @Resource
/*     */   private AuthService authService;
/*     */   
/*     */   public boolean isConnect(String group)
/*     */   {
/*  62 */     return this.connectService.isConnect(group);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean connect(String enviroment, String group)
/*     */   {
/*  68 */     return this.connectService.connect(enviroment, group);
/*     */   }
/*     */   
/*     */   public String login(String userName, String password)
/*     */   {
/*  73 */     String token = this.authService.login(userName, password).getToken();
/*  74 */     return token;
/*     */   }
/*     */   
/*     */ 
/*     */   public void loginOut()
/*     */   {
/*  80 */     this.authService.logout();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean verifyToken(String token)
/*     */   {
/*     */     try
/*     */     {
/*  89 */       if (StringUtils.isEmpty(token))
/*     */       {
/*  91 */         LogUtil.getCoreLog().info("input token is empty!");
/*  92 */         return false;
/*     */       }
/*  94 */       Region sessionRegion = RegionHelper.getSessionRegion();
/*  95 */       byte[] sss = (byte[])sessionRegion.get(token);
/*  96 */       if (sss != null)
/*     */       {
/*  98 */         ByteArrayInputStream bi = new ByteArrayInputStream(sss);
/*  99 */         DataInput di = new DataInputStream(bi);
/* 100 */         AuthInternalSessionImpl internalSession = new AuthInternalSessionImpl();
/* 101 */         DataSerializerExt.fromData(AuthInternalSessionImpl.class, internalSession, di);
/* 102 */         AuthSessionContextHelper.setTlSession(internalSession);
/* 103 */         return true;
/*     */       }
/*     */     }
/*     */     catch (IOException e)
/*     */     {
/* 108 */       LogUtil.getCoreLog().error("verifyToken error:{}", e);
/* 109 */       e.printStackTrace();
/*     */     }
/* 111 */     return false;
/*     */   }
/*     */   
/*     */   public String getCurrentUser()
/*     */   {
/* 116 */     return AuthSessionContextHelper.getUserId();
/*     */   }
/*     */   
/*     */   public ADFOperations onPath(String path)
/*     */   {
/* 121 */     ADFServiceContext.setValue(ADFServiceContext.Keys.PATH, path);
/* 122 */     ADFServiceContext.setValue(ADFServiceContext.Keys.QUERY_TYPE, ADFServiceContext.QueryTypes.OnPath);
/* 123 */     RegionDefine_v3 def = RegionHelper.getRegionDefine(path);
/* 124 */     if (def.isEnabledTemporal())
/*     */     {
/* 126 */       ADFServiceContext.setValue(ADFServiceContext.Keys.TEMPORAL, new TemporalParam());
/*     */     }
/* 128 */     return this.adfOp;
/*     */   }
/*     */   
/*     */   public ADFOperations onGroups(String path, String... groups)
/*     */   {
/* 133 */     ADFServiceContext.setValue(ADFServiceContext.Keys.PATH, path);
/* 134 */     ADFServiceContext.setValue(ADFServiceContext.Keys.GROUPS, groups);
/* 135 */     ADFServiceContext.setValue(ADFServiceContext.Keys.QUERY_TYPE, ADFServiceContext.QueryTypes.OnGroup);
/* 136 */     return this.adfOp;
/*     */   }
/*     */   
/*     */   public String connect(String userName, String password, String enviroment, String group)
/*     */   {
/* 141 */     boolean connectStatus = this.connectService.connect(enviroment, group);
/* 142 */     if (connectStatus)
/*     */     {
/* 144 */       LogUtil.getCoreLog().info("Connect to {}:{} success.", new Object[] { enviroment, group });
/* 145 */       String token = this.authService.login(userName, password).getToken();
/* 146 */       LogUtil.getCoreLog().info("Login with userName={} success, now login... The token is *********.", new Object[] { userName });
/* 147 */       return token;
/*     */     }
/* 149 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public <T> IRemoteService<T> remote(Class<T> beanClass)
/*     */   {
/* 156 */     return IBiz.remote(beanClass);
/*     */   }
/*     */   
/*     */ 
/*     */   public <T> IRemoteService<T> remote(String bizId)
/*     */   {
/* 162 */     return RemoteServiceManager.getInstance().getRemoteService(bizId);
/*     */   }
/*     */   
/*     */ 
/*     */   public <T> IRemoteService<T> remote(T bean)
/*     */   {
/* 168 */     return RemoteServiceManager.getInstance().getRemoteService(bean);
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\client\api\impl\ADFServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */