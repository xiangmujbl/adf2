/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.auth.impl;
/*     */ 
/*     */ import com.gemstone.gemfire.cache.Region;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.jnj.adf.grid.auth.exception.AuthException;
/*     */ import com.jnj.adf.grid.auth.provider.IAuthProvider;
/*     */ import com.jnj.adf.grid.auth.provider.impl.RemoteAuthProvider;
/*     */ import com.jnj.adf.grid.auth.session.AuthInternalSession;
/*     */ import com.jnj.adf.grid.auth.session.AuthInternalSessionImpl;
/*     */ import com.jnj.adf.grid.auth.user.AuthUserInfo;
/*     */ import com.jnj.adf.grid.manager.RegionHelper;
/*     */ import com.jnj.adf.grid.support.context.ADFRuntime;
/*     */ import com.jnj.adf.grid.support.gemfire.DataSerializerExt;
/*     */ import com.jnj.adf.grid.utils.LogUtil;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.DataOutput;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.springframework.beans.BeansException;
/*     */ import org.springframework.beans.factory.InitializingBean;
/*     */ import org.springframework.context.ApplicationContext;
/*     */ import org.springframework.context.ApplicationContextAware;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Component
/*     */ public class AuthServiceImpl
/*     */   extends AbstractAuthService
/*     */   implements InitializingBean, ApplicationContextAware
/*     */ {
/*  63 */   private List<IAuthProvider> privoders = Lists.newArrayList();
/*     */   
/*     */   private ApplicationContext applicationContext;
/*     */   
/*     */   protected String doInternalVerify(String username, String pwd)
/*     */     throws AuthException
/*     */   {
/*  70 */     if (!this.privoders.isEmpty())
/*     */     {
/*  72 */       for (IAuthProvider privoder : this.privoders)
/*     */       {
/*  74 */         if ((ADFRuntime.getRuntime().isMaster()) && ((privoder instanceof RemoteAuthProvider)))
/*     */         {
/*  76 */           return null;
/*     */         }
/*  78 */         String userId = privoder.verify(username, pwd);
/*  79 */         LogUtil.getCoreLog().info("Verify userName:{} using provider:{} and passed:{}, tid:{}", new Object[] { username, privoder
/*  80 */           .getClass().getSimpleName(), Boolean.valueOf(StringUtils.isNotEmpty(userId)), Thread.currentThread().getName() });
/*  81 */         if (StringUtils.isNotEmpty(userId))
/*     */         {
/*  83 */           return userId;
/*     */         }
/*     */       }
/*     */     }
/*  87 */     return null;
/*     */   }
/*     */   
/*     */   protected AuthUserInfo doInternalFindUser(String userId)
/*     */     throws AuthException
/*     */   {
/*  93 */     if (!this.privoders.isEmpty())
/*     */     {
/*  95 */       for (IAuthProvider privoder : this.privoders)
/*     */       {
/*  97 */         AuthUserInfo userInfo = privoder.findUser(userId);
/*  98 */         if (userInfo != null)
/*     */         {
/* 100 */           return userInfo;
/*     */         }
/*     */       }
/*     */     }
/* 104 */     return null;
/*     */   }
/*     */   
/*     */   protected AuthUserPrivilege doInternalFindPrivilege(String userId)
/*     */     throws AuthException
/*     */   {
/* 110 */     if (!this.privoders.isEmpty())
/*     */     {
/* 112 */       for (IAuthProvider privoder : this.privoders)
/*     */       {
/* 114 */         if ((ADFRuntime.getRuntime().isMaster()) && ((privoder instanceof RemoteAuthProvider)))
/*     */         {
/* 116 */           return null;
/*     */         }
/* 118 */         AuthUserPrivilege userPrivilege = privoder.findUserPrivilege(userId);
/* 119 */         if (userPrivilege != null)
/*     */         {
/* 121 */           return userPrivilege;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 126 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void doSaveSession(AuthInternalSession session)
/*     */   {
/*     */     try
/*     */     {
/* 138 */       ByteArrayOutputStream bo = new ByteArrayOutputStream();
/* 139 */       DataOutput out = new DataOutputStream(bo);
/* 140 */       DataSerializerExt.toData(AuthInternalSessionImpl.class, session, out);
/*     */       
/* 142 */       Region sessionRegion = RegionHelper.getSessionRegion();
/* 143 */       byte[] sss = bo.toByteArray();
/* 144 */       sessionRegion.put(session.getToken(), sss);
/*     */       
/* 146 */       Object to = sessionRegion.get(session.getToken());
/* 147 */       bo.close();
/*     */     }
/*     */     catch (IOException e)
/*     */     {
/* 151 */       LogUtil.getCoreLog().error("save session,userId:{} error:{}", new Object[] { session.getUserId(), e });
/* 152 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void afterPropertiesSet()
/*     */     throws Exception
/*     */   {
/* 159 */     Map<String, IAuthProvider> beanMaps = this.applicationContext.getBeansOfType(IAuthProvider.class);
/* 160 */     if ((beanMaps == null) || (beanMaps.isEmpty()))
/*     */     {
/* 162 */       return;
/*     */     }
/* 164 */     Collection<IAuthProvider> beans = beanMaps.values();
/* 165 */     for (IAuthProvider bean : beans)
/*     */     {
/* 167 */       if (bean.enabled())
/*     */       {
/* 169 */         this.privoders.add(bean);
/*     */       }
/*     */     }
/*     */     
/* 173 */     Collections.sort(this.privoders, new Comparator()
/*     */     {
/*     */ 
/*     */       public int compare(IAuthProvider thisObj, IAuthProvider otherObj)
/*     */       {
/* 178 */         return thisObj.priority() - otherObj.priority();
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */ 
/*     */   public void setApplicationContext(ApplicationContext applicationContext)
/*     */     throws BeansException
/*     */   {
/* 187 */     this.applicationContext = applicationContext;
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\auth\impl\AuthServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */