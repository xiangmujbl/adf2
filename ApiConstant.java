package com.jnj.adf.dataservice.adfcoreignite;

import com.jnj.adf.dataservice.adfcoreignite.grid.utils.LogUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract interface ApiConstant
{
  public static final String REMOTE_SERVICE_FUNCTION = "REMOTE_SERVICE_FUNCTION";
  public static final String REMOTE_ADMIN_FUNCTION = "REMOTE_ADMIN_FUNCTION";
  public static final String PARAM_MODULE_NAME = "moduleName";
  public static final String PARAM_BEAN_NAME = "beanName";
  public static final String PARAM_URSER_ARGS = "userArgs";
  public static final String REGIONPATH = "REGIONPATH";

  /*     */
  /*     */
  /*     */  class VersionUtils
  /*     */ {
  /*  18 */   public static String ADF_V_0302_VIEW = "0.3.02_VIEW";
  /*  19 */   public static String ADF_V_0302 = "0.3.02";
  /*  20 */   public static String ADF_V02 = "0.2";
  /*     */
  /*  22 */   private static String localVersion = null;
  /*  23 */   private static AtomicBoolean localVersionSearched = new AtomicBoolean(false);
  /*     */
  /*     */   public static final void findLocalVersion()
  /*     */   {
  /*  27 */     localVersionSearched.set(true);
  /*  28 */     localVersion = findVersion(VersionUtils.class.getClassLoader());
  /*     */   }
  /*     */
  /*     */   public static final String localVersion() {
  /*  32 */     if (localVersion != null)
  /*  33 */       return localVersion;
  /*  34 */     if (localVersionSearched.get())
  /*  35 */       return null;
  /*  36 */     findLocalVersion();
  /*  37 */     return localVersion;
  /*     */   }
  /*     */
  /*     */   public static final boolean is_v_0302_VIEW(String version) {
  /*  41 */     return ADF_V_0302_VIEW.equals(version);
  /*     */   }
  /*     */
  /*     */
  /*     */
  /*     */
  /*     */   public static final boolean below_v_0_3_2(String version)
  /*     */   {
  /*  49 */     if ((StringUtils.isEmpty(version)) || (version.startsWith(ADF_V02)))
  /*  50 */       return true;
  /*  51 */     if (ADF_V_0302.equals(version))
  /*  52 */       return true;
  /*  53 */     return false;
  /*     */   }
  /*     */
  /*     */
  /*     */   public static final String getVersion()
  /*     */   {
  /*  59 */     String version = ExecutionContextHelper.currentVersion();
  /*  60 */     if (version != null)
  /*  61 */       return version;
  /*  62 */     return localVersion;
  /*     */   }
  /*     */
  /*     */   public static final String findVersion(ClassLoader loader)
  /*     */   {
  /*  67 */     String versionNo = ADFConfigHelper.getConfig(ITEMS.ADF_VERSION_NO);
  /*  68 */     if (!StringUtils.isEmpty(versionNo))
  /*     */     {
  /*  70 */       ADFConfigHelper.setProperty(ITEMS.ADF_VERSION_NO, versionNo);
  /*  71 */       return versionNo;
  /*     */     }
  /*  73 */     if (ADFRuntime.getRuntime().isClientSide())
  /*     */     {
  /*  75 */       versionNo = findVersion("adf-core-version.properties", loader);
  /*     */     }
  /*     */     else
  /*     */     {
  /*  79 */       versionNo = findVersion("adf-server-version.properties", loader);
  /*     */     }
  /*  81 */     return versionNo;
  /*     */   }
  /*     */
  /*     */   private static final String findVersion(String propertyFile, ClassLoader loader)
  /*     */   {
  /*  86 */     String version = null;
  /*  87 */     URL url = loader.getResource(propertyFile);
  /*  88 */     if (url != null)
  /*     */     {
  /*  90 */       com.jnj.adf.dataservice.adfcoreignite.grid.utils.LogUtil.getCoreLog().info("Found version define file:{}", new Object[] { url.toString() });
  /*     */       try
  /*     */       {
  /*  93 */         InputStream in = url.openStream();
  /*  94 */         Properties prop = new Properties();
  /*  95 */         prop.load(in);
  /*  96 */         version = prop.getProperty("version");
  /*  97 */         com.jnj.adf.dataservice.adfcoreignite.grid.utils.LogUtil.getCoreLog().info("Found version:{}", new Object[] { version });
  /*  98 */         ADFConfigHelper.setProperty(ITEMS.ADF_VERSION_NO, version);
  /*     */       }
  /*     */       catch (IOException e)
  /*     */       {
  /* 102 */         LogUtil.getCoreLog().error(e);
  /*     */       }
  /*     */     }
  /* 105 */     return version;
  /*     */   }
  /*     */ }
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\ApiConstant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */