/*     */ package com.jnj.adf.dataservice.adfcoreignite.client.api;
/*     */ 
/*     */ import com.gemstone.gemfire.distributed.internal.DM;
/*     */ import com.gemstone.gemfire.distributed.internal.DistributionConfig;
/*     */ import com.gemstone.gemfire.internal.ClassLoadUtil;
/*     */ import com.gemstone.gemfire.internal.cache.GemFireCacheImpl;
/*     */ import com.gemstone.gemfire.security.AuthInitialize;
/*     */ import com.jnj.adf.grid.auth.impl.AuthSessionContextHelper;
/*     */ import com.jnj.adf.grid.manager.RegionHelper;
/*     */ import com.jnj.adf.grid.utils.LogUtil;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.Properties;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ADFCredentialsHelper
/*     */ {
/*  22 */   private static DistributionConfig config = null;
/*     */   
/*  24 */   private static Properties getMultiUserCredentials() { if (config == null) {
/*  25 */       initConfig();
/*     */     }
/*  27 */     return AuthSessionContextHelper.getMultiUserCredentials();
/*     */   }
/*     */   
/*     */   public static Properties getJMXMultiUserCredentials() {
/*  31 */     if (config == null) {
/*  32 */       initConfig();
/*     */     }
/*  34 */     String jmxInitialKlass = config.getAttribute("security-jmx-client-auth-init");
/*  35 */     AuthInitialize initialize = getAuthInitializeCallback(jmxInitialKlass);
/*  36 */     initialize.init(null, null);
/*  37 */     Properties multiUserCredential = getMultiUserCredentials();
/*  38 */     Properties credentials = initialize.getCredentials(multiUserCredential, null, false);
/*  39 */     return credentials;
/*     */   }
/*     */   
/*     */   public static Properties getPoolMultiUserCredentials()
/*     */   {
/*  44 */     if (config == null) {
/*  45 */       initConfig();
/*     */     }
/*  47 */     String poolInitialKlass = config.getAttribute("security-client-auth-init");
/*  48 */     AuthInitialize initialize = getAuthInitializeCallback(poolInitialKlass);
/*  49 */     initialize.init(null, null);
/*  50 */     Properties multiUserCredential = getMultiUserCredentials();
/*  51 */     Properties credentials = initialize.getCredentials(multiUserCredential, null, false);
/*  52 */     return credentials;
/*     */   }
/*     */   
/*     */   private static AuthInitialize getAuthInitializeCallback(String initialKlass) {
/*  56 */     Method authInitMethod = null;
/*  57 */     AuthInitialize authInitCallback = null;
/*     */     try {
/*  59 */       authInitMethod = ClassLoadUtil.methodFromName(initialKlass);
/*  60 */       authInitCallback = (AuthInitialize)authInitMethod.invoke(null, new Object[0]);
/*     */     }
/*     */     catch (IllegalAccessException|InvocationTargetException|ClassNotFoundException|NoSuchMethodException localIllegalAccessException) {}
/*  63 */     return authInitCallback;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   @Deprecated
/*     */   private static AuthInitialize getAuthInitialize(String initialKlass)
/*     */   {
/*  72 */     if (StringUtils.isNotEmpty(initialKlass)) {
/*  73 */       String methodName = StringUtils.substringAfterLast(initialKlass, ".");
/*  74 */       String klassName = StringUtils.substringBeforeLast(initialKlass, ".");
/*     */       try {
/*  76 */         Class initialClass = Class.forName(klassName);
/*  77 */         Method method = initialClass.getMethod(methodName, new Class[0]);
/*  78 */         Object result = method.invoke(null, new Object[0]);
/*  79 */         if ((result instanceof AuthInitialize)) {
/*  80 */           return (AuthInitialize)result;
/*     */         }
/*     */       } catch (ClassNotFoundException e) {
/*  83 */         LogUtil.getCoreLog().error(e);
/*     */       } catch (NoSuchMethodException e) {
/*  85 */         LogUtil.getCoreLog().error(e);
/*     */       } catch (IllegalAccessException e) {
/*  87 */         LogUtil.getCoreLog().error(e);
/*     */       } catch (InvocationTargetException e) {
/*  89 */         LogUtil.getCoreLog().error(e);
/*     */       }
/*     */     }
/*     */     
/*  93 */     return null;
/*     */   }
/*     */   
/*     */   private static synchronized void initConfig()
/*     */   {
/*  98 */     if (config == null) {
/*  99 */       DM distributeManager = RegionHelper.getClientcache().getDistributionManager();
/* 100 */       if (distributeManager != null) {
/* 101 */         config = distributeManager.getConfig();
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\client\api\ADFCredentialsHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */