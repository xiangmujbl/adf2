/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.auth.credential;
/*    */ 
/*    */ import com.gemstone.gemfire.LogWriter;
/*    */ import com.gemstone.gemfire.cache.CacheFactory;
/*    */ import com.gemstone.gemfire.distributed.DistributedMember;
/*    */ import com.gemstone.gemfire.distributed.internal.DM;
/*    */ import com.gemstone.gemfire.distributed.internal.DistributionConfig;
/*    */ import com.gemstone.gemfire.internal.cache.GemFireCacheImpl;
/*    */ import com.gemstone.gemfire.security.AuthInitialize;
/*    */ import com.gemstone.gemfire.security.AuthenticationFailedException;
/*    */ import java.util.Properties;
/*    */ import java.util.Set;
/*    */ import java.util.concurrent.atomic.AtomicBoolean;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ADFJMXCredentialInit
/*    */   implements AuthInitialize
/*    */ {
/* 21 */   private static ADFJMXCredentialInit INST = null;
/* 22 */   private static AtomicBoolean initialed = new AtomicBoolean(false);
/*    */   
/*    */   public void init(LogWriter logWriter1, LogWriter logWriter2)
/*    */     throws AuthenticationFailedException
/*    */   {}
/*    */   
/*    */   public Properties getCredentials(Properties properties, DistributedMember distributedMember, boolean isPeer)
/*    */     throws AuthenticationFailedException
/*    */   {
/* 31 */     Properties prop = new Properties();
/* 32 */     Set<String> propertyNames = properties.stringPropertyNames();
/* 33 */     for (String proName : propertyNames) {
/* 34 */       prop.put(proName, properties.getProperty(proName));
/*    */     }
/*    */     
/* 37 */     DistributionConfig config = ((GemFireCacheImpl)CacheFactory.getAnyInstance()).getDistributionManager().getConfig();
/* 38 */     prop.put("security-service-account-username", config.getAttribute("security-service-account-username"));
/* 39 */     prop.put("security-service-account-password", config.getAttribute("security-service-account-password"));
/*    */     
/* 41 */     return prop;
/*    */   }
/*    */   
/*    */   public static AuthInitialize create() {
/* 45 */     if (!initialed.get()) {
/* 46 */       INST = new ADFJMXCredentialInit();
/*    */     }
/* 48 */     initialed.set(true);
/* 49 */     return INST;
/*    */   }
/*    */   
/*    */   public void close() {}
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\auth\credential\ADFJMXCredentialInit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */