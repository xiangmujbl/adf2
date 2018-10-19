/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.common;
/*     */ 
/*     */ import com.gemstone.gemfire.cache.CacheClosedException;
/*     */ import com.gemstone.gemfire.cache.CacheWriterException;
/*     */ import com.gemstone.gemfire.cache.TimeoutException;
/*     */ import com.gemstone.gemfire.cache.TransactionException;
/*     */ import com.gemstone.gemfire.cache.client.NoAvailableLocatorsException;
/*     */ import com.gemstone.gemfire.security.AuthenticationFailedException;
/*     */ import com.gemstone.gemfire.security.NotAuthorizedException;
/*     */ import com.jnj.adf.grid.auth.exception.AuthException;
/*     */ import com.jnj.adf.grid.auth.exception.TokenException;
/*     */ import com.jnj.adf.grid.client.api.support.ADFServiceContext;
/*     */ import com.jnj.adf.grid.connect.ConnectException;
/*     */ import com.jnj.adf.grid.master.exception.InvalidDataException;
/*     */ import com.jnj.adf.grid.utils.LogUtil;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.Arrays;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class AdfExceptionConverter
/*     */ {
/*     */   public static void logError(Throwable e, Method method, Object... args)
/*     */   {
/*  25 */     LogUtil.getCoreLog().error("ADFService invocation error-> Method:{}.{},args:{},session:{}", new Object[] {method
/*  26 */       .getDeclaringClass().getSimpleName(), method.getName(), Arrays.asList(args), ADFServiceContext.dump(), e });
/*     */   }
/*     */   
/*     */   public static Exception convertToAdfException(Throwable e)
/*     */   {
/*  31 */     Exception ex = null;
/*  32 */     if ((e instanceof InvocationTargetException))
/*     */     {
/*  34 */       ex = convertToAdfException(e.getCause());
/*     */ 
/*     */     }
/*  37 */     else if ((e instanceof NullPointerException))
/*     */     {
/*  39 */       ex = new ADFException(ExceptionTypes.unkonw001, e);
/*     */     }
/*  41 */     else if ((e instanceof TimeoutException))
/*     */     {
/*  43 */       ex = new ADFException(ExceptionTypes.timeout, e);
/*     */     }
/*  45 */     else if ((e instanceof CacheClosedException))
/*     */     {
/*  47 */       ex = new ADFException(ExceptionTypes.cacheClosed, e);
/*     */     }
/*  49 */     else if ((e instanceof CacheWriterException))
/*     */     {
/*  51 */       ex = new ADFException(ExceptionTypes.cacheWrite, e);
/*     */     }
/*  53 */     else if ((e instanceof AuthException))
/*     */     {
/*  55 */       ex = new ADFException(ExceptionTypes.login, e);
/*     */     }
/*  57 */     else if ((e instanceof TokenException))
/*     */     {
/*  59 */       ex = new ADFException(ExceptionTypes.login, e);
/*     */     }
/*  61 */     else if ((e instanceof TransactionException))
/*     */     {
/*  63 */       ex = new ADFException(ExceptionTypes.transaction, e);
/*     */     }
/*  65 */     else if ((e instanceof NotAuthorizedException))
/*     */     {
/*  67 */       ex = new ADFException(ExceptionTypes.authenrizationFail, e);
/*     */     }
/*  69 */     else if ((e instanceof AuthenticationFailedException))
/*     */     {
/*  71 */       ex = new ADFException(ExceptionTypes.authenticationFail, e);
/*     */     }
/*  73 */     else if ((e instanceof ConnectException))
/*     */     {
/*  75 */       ex = new ADFException(ExceptionTypes.fetchGridInfo, e);
/*     */     }
/*  77 */     else if ((e instanceof NoAvailableLocatorsException))
/*     */     {
/*  79 */       ex = new ADFException(ExceptionTypes.connectNoLocators, e);
/*     */ 
/*     */     }
/*  82 */     else if ((e instanceof InvalidDataException))
/*     */     {
/*  84 */       ex = new ADFException(ExceptionTypes.invalidDataException, e);
/*     */     }
/*  86 */     else if ((e instanceof ADFException))
/*     */     {
/*  88 */       ADFException exception = (ADFException)e;
/*  89 */       ex = exception;
/*     */     }
/*  91 */     else if ((e instanceof IllegalArgumentException))
/*     */     {
/*  93 */       ex = new ADFException(e.getMessage());
/*     */     }
/*  95 */     else if (e.getCause() != null)
/*     */     {
/*  97 */       ex = convertToAdfException(e.getCause());
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/* 102 */       ex = (Exception)e;
/*     */     }
/* 104 */     return ex;
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\common\AdfExceptionConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */