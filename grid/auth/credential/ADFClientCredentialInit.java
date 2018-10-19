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
/*    */ import com.google.common.collect.Sets;
/*    */ import com.jnj.adf.grid.support.system.ADFConfigHelper;
/*    */ import com.jnj.adf.grid.support.system.ADFConfigHelper.ITEMS;
/*    */ import com.jnj.adf.grid.utils.JsonUtils;
/*    */ import java.util.Properties;
/*    */ import java.util.Set;
/*    */ import java.util.concurrent.atomic.AtomicBoolean;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ADFClientCredentialInit
/*    */   implements AuthInitialize
/*    */ {
/* 24 */   private static ADFClientCredentialInit INST = null;
/* 25 */   private static AtomicBoolean initialed = new AtomicBoolean(false);
/* 26 */   private static boolean isMultipleUser = ADFConfigHelper.getBoolean(ITEMS.CLINET_MULTIPLE_USER);
/*    */   
/*    */ 
/*    */   public void init(LogWriter logWriter, LogWriter logWriter1)
/*    */     throws AuthenticationFailedException
/*    */   {}
/*    */   
/*    */   public Properties getCredentials(Properties properties, DistributedMember distributedMember, boolean isPeer)
/*    */     throws AuthenticationFailedException
/*    */   {
/* 36 */     if (!isPeer)
/*    */     {
/* 38 */       Properties prop = new Properties();
/* 39 */       Set<String> propertyNames = properties.stringPropertyNames();
/* 40 */       for (String proName : propertyNames) {
/* 41 */         prop.put(proName, properties.getProperty(proName));
/*    */       }
/*    */       
/* 44 */       DistributionConfig config = ((GemFireCacheImpl)CacheFactory.getAnyInstance()).getDistributionManager().getConfig();
/* 45 */       prop.put("security-service-account-username", config.getAttribute("security-service-account-username"));
/* 46 */       prop.put("security-service-account-password", config.getAttribute("security-service-account-password"));
/*    */       
/* 48 */       if (!isMultipleUser) {
/* 49 */         prop.put("userId", "anonymous");
/* 50 */         prop.put("roles", JsonUtils.objectToJson(Sets.newHashSet(new String[] { "_ANONYMOUS_" })));
/*    */       }
/*    */       
/* 53 */       if (!prop.containsKey("userId")) {
/* 54 */         prop.put("userId", "anonymous");
/* 55 */         prop.put("roles", JsonUtils.objectToJson(Sets.newHashSet(new String[] { "_ANONYMOUS_" })));
/*    */       }
/*    */       
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 64 */       prop.put("_credential_signature_", signature(prop));
/*    */       
/*    */ 
/* 67 */       return prop;
/*    */     }
/*    */     
/* 70 */     return null;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void close() {}
/*    */   
/*    */ 
/*    */   private static String signature(Properties p)
/*    */   {
/* 80 */     return "signature";
/*    */   }
/*    */   
/*    */   public static AuthInitialize create() {
/* 84 */     if (!initialed.get()) {
/* 85 */       INST = new ADFClientCredentialInit();
/*    */     }
/* 87 */     initialed.set(true);
/* 88 */     return INST;
/*    */   }
/*    */   
/*    */   public static void main(String[] args) {}
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\auth\credential\ADFClientCredentialInit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */