/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.support.system;
/*     */ 
/*     */ import com.gemstone.gemfire.admin.internal.InetAddressUtil;
/*     */ import com.jnj.adf.grid.support.context.ADFRuntime;
/*     */ import com.jnj.adf.grid.utils.LogUtil;
/*     */ import com.jnj.adf.grid.utils.VersionUtils;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.net.InetAddress;
/*     */ import java.net.URISyntaxException;
/*     */ import java.net.URL;
/*     */ import java.net.UnknownHostException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Properties;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import java.util.function.BiConsumer;
/*     */ import org.apache.commons.lang3.BooleanUtils;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.apache.commons.lang3.math.NumberUtils;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.apache.logging.log4j.ThreadContext;
/*     */ import org.apache.logging.log4j.core.LoggerContext;
/*     */ import org.springframework.util.PropertyPlaceholderHelper;
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
/*     */ 
/*     */ public class ADFConfigHelper
/*     */   implements ConfigurationConstants
/*     */ {
/*  65 */   private static final AtomicBoolean initlized = new AtomicBoolean(false);
/*  66 */   private static final AtomicBoolean initlizing = new AtomicBoolean(false);
/*  67 */   private static final AtomicBoolean initlized_log = new AtomicBoolean(false);
/*     */   
/*  69 */   private static int locatorCount = 0;
/*     */   
/*     */ 
/*  72 */   static PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper("${", "}");
/*     */   
/*     */ 
/*     */ 
/*  76 */   static Properties serverConfig = new Properties();
/*  77 */   static Properties defaultConfig = new Properties();
/*     */   private static final String locatorFlag = "-l-";
/*     */   private static final String serverFlag = "-s-";
/*     */   
/*     */   public static void main(String[] args) {
/*  82 */     System.out.println(">>" + getVersion());
/*     */   }
/*     */   
/*     */   public static String getVersion()
/*     */   {
/*  87 */     String version = "";
/*  88 */     InputStream in = ADFConfigHelper.class.getClassLoader().getResourceAsStream("application.properties");
/*  89 */     Properties prop = new Properties();
/*     */     try
/*     */     {
/*  92 */       prop.load(in);
/*  93 */       version = prop.getProperty("version");
/*     */     }
/*     */     catch (IOException localIOException) {}
/*     */     
/*     */ 
/*  98 */     return version;
/*     */   }
/*     */   
/*     */   public static String getConfig(ITEMS item)
/*     */   {
/* 103 */     if ((serverConfig != null) && (serverConfig.containsKey(item.name())))
/*     */     {
/* 105 */       return serverConfig.getProperty(item.getProKey());
/*     */     }
/* 107 */     return System.getProperty(item.getProKey());
/*     */   }
/*     */   
/*     */   private static ITEMS stringToITEMS(String name)
/*     */   {
/* 112 */     ITEMS item = (ITEMS)ITEMS.nameMap.get(name);
/* 113 */     if (item == null)
/* 114 */       item = (ITEMS)ITEMS.proKeyMap.get(name);
/* 115 */     return item;
/*     */   }
/*     */   
/*     */ 
/*     */   public static void setProperty(String name, Object value)
/*     */   {
/* 121 */     if (value == null) {
/* 122 */       return;
/*     */     }
/* 124 */     ITEMS item = stringToITEMS(name);
/* 125 */     if (item != null) {
/* 126 */       setProperty(item, value);
/*     */     }
/*     */     else {
/* 129 */       if (initlized.get())
/*     */       {
/* 131 */         Object old = serverConfig.get(name);
/* 132 */         if (value.equals(old))
/* 133 */           return;
/* 134 */         LogUtil.getCoreLog().info("Set {}={}", new Object[] { name, value });
/*     */       }
/* 136 */       serverConfig.put(name, value);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void setProperty(ITEMS item, Object value)
/*     */   {
/* 144 */     if (initlized.get())
/*     */     {
/* 146 */       Object old = serverConfig.get(item.proKey);
/* 147 */       if (value.equals(old))
/* 148 */         return;
/* 149 */       LogUtil.getCoreLog().info("Set {}={}\t//{}", new Object[] { item.proKey, value, item.name() });
/*     */     }
/*     */     
/* 152 */     serverConfig.put(item.proKey, value);
/* 153 */     serverConfig.put(item.name(), value);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void putAll(Properties pro)
/*     */   {
/* 164 */     if (initlized.get())
/* 165 */       LogUtil.getCoreLog().info("ADF config Helper putAll additinal properties= {}.", new Object[] { pro });
/* 166 */     if (pro != null)
/*     */     {
/* 168 */       Iterator<Entry<Object, Object>> entryIt = pro.entrySet().iterator();
/* 169 */       while (entryIt.hasNext())
/*     */       {
/* 171 */         Entry<Object, Object> entry = (Entry)entryIt.next();
/* 172 */         setProperty((String)entry.getKey(), (String)entry.getValue());
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static void initLog4j()
/*     */   {
/* 179 */     initLog4j(getLoggerFileName());
/*     */   }
/*     */   
/*     */   public static void initLog4j(String configFile)
/*     */   {
/* 184 */     if (initlized_log.get())
/* 185 */       return;
/* 186 */     initlized_log.set(true);
/* 187 */     System.setProperty("Log4jContextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
/* 188 */     if (getProperty(ITEMS.NODE_TYPE.name()) == null)
/* 189 */       setProperty(ITEMS.NODE_TYPE.name(), "");
/* 190 */     if (getProperty(ITEMS.NODE_NAME.name()) == null) {
/* 191 */       setProperty(ITEMS.NODE_NAME.name(), "");
/*     */     }
/*     */     try
/*     */     {
/* 195 */       URL url = ADFConfigHelper.class.getClassLoader().getResource(configFile);
/* 196 */       LoggerContext context = (LoggerContext)LogManager.getContext(false);
/* 197 */       ThreadContext.put(ITEMS.GS_HOME.proKey, getConfig(ITEMS.GS_HOME));
/* 198 */       ThreadContext.put(ITEMS.NODE_TYPE.proKey, getConfig(ITEMS.NODE_TYPE));
/* 199 */       ThreadContext.put(ITEMS.NODE_NAME.proKey, getConfig(ITEMS.NODE_NAME));
/*     */       
/* 201 */       LogUtil.getCoreLog().info("Log4j config file loading from {}.", new Object[] { url });
/* 202 */       context.setConfigLocation(url.toURI());
/*     */     }
/*     */     catch (URISyntaxException e)
/*     */     {
/* 206 */       System.err.print(e.getMessage());
/* 207 */       initlized_log.set(false);
/*     */     }
/* 209 */     initlized_log.set(true);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String getLoggerFileName()
/*     */   {
/* 218 */     return System.getProperty("logger.file.name", "log4j2-server.xml");
/*     */   }
/*     */   
/*     */ 
/*     */   public static void dump()
/*     */   {
/*     */     try
/*     */     {
/* 226 */       StringBuilder bu = new StringBuilder("\n=======================ADF config==============================\n");
/* 227 */       List list = new ArrayList(serverConfig.keySet());
/* 228 */       Collections.sort(list);
/* 229 */       final List priorList = new ArrayList();
/* 230 */       defaultConfig.forEach(new BiConsumer()
/*     */       {
/*     */ 
/*     */         public void accept(Object t, Object u)
/*     */         {
/* 235 */           if (this.val$list.contains(t))
/*     */           {
/* 237 */             Object x = ADFConfigHelper.serverConfig.getProperty(t.toString());
/* 238 */             if ((x != null) && (!x.equals(u)))
/* 239 */               priorList.add(t);
/*     */           }
/*     */         }
/* 242 */       });
/* 243 */       Collections.sort(priorList);
/* 244 */       for (Object key : priorList)
/*     */       {
/* 246 */         if (!ITEMS.nameMap.containsKey(key))
/*     */         {
/* 248 */           ITEMS item = (ITEMS)ITEMS.proKeyMap.get(key);
/* 249 */           Object value = serverConfig.getProperty(key.toString());
/* 250 */           if (item != null) {
/* 251 */             bu.append(key).append("=").append(value).append("\t//").append(item.name()).append("\n");
/*     */           } else
/* 253 */             bu.append(key).append("=").append(value).append("\n");
/*     */         }
/*     */       }
/* 256 */       bu.append("===============================================================\n");
/* 257 */       for (Object key : list)
/*     */       {
/* 259 */         if (!priorList.contains(key))
/*     */         {
/* 261 */           if (!ITEMS.nameMap.containsKey(key))
/*     */           {
/* 263 */             ITEMS item = (ITEMS)ITEMS.proKeyMap.get(key);
/* 264 */             Object value = serverConfig.getProperty(key.toString());
/* 265 */             if (item != null) {
/* 266 */               bu.append(key).append("=").append(value).append("\t//").append(item.name()).append("\n");
/*     */             } else
/* 268 */               bu.append(key).append("=").append(value).append("\n");
/*     */           } }
/*     */       }
/* 271 */       bu.append("===============================================================");
/* 272 */       LogUtil.getCoreLog().info(bu.toString());
/*     */ 
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 277 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static void initConfig()
/*     */   {
/* 284 */     initConfig(getEnvFile());
/* 285 */     VersionUtils.findVersion(ADFConfigHelper.class.getClassLoader());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String getEnvFile()
/*     */   {
/* 293 */     return System.getProperty("env.file.name", "env.properties");
/*     */   }
/*     */   
/*     */   public static void initConfig(String envFile)
/*     */   {
/* 298 */     if (initlized.get()) {
/* 299 */       return;
/*     */     }
/*     */     try {
/* 302 */       Enumeration<URL> urls = ADFConfigHelper.class.getClassLoader().getResources(envFile);
/* 303 */       while (urls.hasMoreElements())
/*     */       {
/* 305 */         System.out.println("Found env url:" + urls.nextElement());
/*     */       }
/* 307 */       URL envFileURL = ADFConfigHelper.class.getClassLoader().getResource(envFile);
/* 308 */       initConfig("config/com/jnj/adf/core/env-default.properties", envFileURL, null);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 312 */       e.printStackTrace();
/* 313 */       System.exit(-10);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static void initConfig(URL envFileURL)
/*     */   {
/* 320 */     initConfig("config/com/jnj/adf/core/env-default.properties", envFileURL, null);
/*     */   }
/*     */   
/*     */   public static void initConfig(Properties envProperties)
/*     */   {
/* 325 */     initConfig("config/com/jnj/adf/core/env-default.properties", null, envProperties);
/*     */   }
/*     */   
/*     */   private static void initConfig(String defaultEnvFile, URL envFileURL, Properties envProperties)
/*     */   {
/* 330 */     if ((initlizing.get()) || (initlized.get()))
/* 331 */       return;
/* 332 */     initlizing.set(true);
/*     */     try
/*     */     {
/* 335 */       beforeConfigInitial();
/* 336 */       initConfig0(defaultEnvFile, envFileURL, envProperties);
/* 337 */       afterConfigInitial();
/*     */     }
/*     */     catch (IOException e)
/*     */     {
/* 341 */       initlized.set(false);
/* 342 */       e.printStackTrace();
/* 343 */       System.exit(-1);
/*     */     }
/* 345 */     ADFRuntime.getRuntime().setHomePath(getHomePath());
/* 346 */     ADFRuntime.getRuntime().setWorkPath(getWorkPath());
/* 347 */     ADFRuntime.getRuntime().setNodeName(getNodeName());
/* 348 */     initlized.set(true);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static void beforeConfigInitial() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static void afterConfigInitial()
/*     */   {
/* 364 */     WorkPathHelper.verifyPath("log");
/*     */   }
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
/*     */   private static synchronized void initConfig0(String defaultEnvFile, URL envFileURL, Properties envProperties)
/*     */     throws IOException
/*     */   {
/* 382 */     URL url = ADFConfigHelper.class.getClassLoader().getResource(defaultEnvFile);
/* 383 */     if (url != null)
/*     */     {
/* 385 */       Properties prop = new Properties();
/* 386 */       prop.load(url.openStream());
/* 387 */       putAll(prop);
/* 388 */       defaultConfig.load(url.openStream());
/*     */     }
/* 390 */     System.out.println("Load default env from:" + url);
/*     */     
/* 392 */     if (envProperties != null)
/*     */     {
/* 394 */       putAll(envProperties);
/* 395 */       System.out.println("Load env from given properties, properties items:" + envProperties.size());
/*     */     }
/* 397 */     else if (envFileURL != null)
/*     */     {
/* 399 */       Properties prop = new Properties();
/* 400 */       prop.load(envFileURL.openStream());
/* 401 */       putAll(prop);
/* 402 */       System.out.println("Load env from:" + envFileURL);
/*     */     }
/*     */     
/*     */ 
/* 406 */     Iterator<Object> it = serverConfig.keySet().iterator();
/* 407 */     while (it.hasNext())
/*     */     {
/* 409 */       String key = it.next().toString();
/* 410 */       processSystemEnv(key);
/*     */     }
/* 412 */     System.setProperty("gemfire.ALLOW_PERSISTENT_TRANSACTIONS", "true");
/* 413 */     String bindip = getConfig(ITEMS.BINDIP);
/* 414 */     processBindIp(bindip);
/* 415 */     String locators = getProperty(ITEMS.LOCATORS);
/* 416 */     processLocators(locators);
/*     */     
/* 418 */     String namingServers = getProperty(ITEMS.NAMING_SERVER);
/* 419 */     namingServers = processLocalhost(namingServers);
/* 420 */     setProperty(ITEMS.NAMING_SERVER, namingServers);
/*     */     
/* 422 */     String remoteLocators = getProperty(ITEMS.CLUSTER_REMOTE_LOCATORS);
/* 423 */     remoteLocators = processLocalhost(remoteLocators);
/* 424 */     setProperty(ITEMS.CLUSTER_REMOTE_LOCATORS, remoteLocators);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 429 */     System.setProperty(ITEMS.GS_ENV.name(), getConfig(ITEMS.GS_ENV));
/* 430 */     System.setProperty(ITEMS.GS_ENV.proKey, getConfig(ITEMS.GS_ENV));
/* 431 */     replacePlaceholders();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private static void replacePlaceholders()
/*     */   {
/* 438 */     Iterator entries = serverConfig.entrySet().iterator();
/* 439 */     while (entries.hasNext())
/*     */     {
/* 441 */       Entry e = (Entry)entries.next();
/* 442 */       Object value = e.getValue();
/* 443 */       String replaced = helper.replacePlaceholders(value.toString(), serverConfig);
/* 444 */       if ((replaced != null) && (!replaced.equals(value)))
/*     */       {
/* 446 */         setProperty(e.getKey().toString(), replaced);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static void processLocators(String locators)
/*     */   {
/* 453 */     if (!StringUtils.isEmpty(locators))
/*     */     {
/* 455 */       String[] grp = locators.split(",");
/* 456 */       locatorCount = grp.length;
/* 457 */       for (int i = 0; i < grp.length; i++)
/*     */       {
/* 459 */         String[] sub = grp[i].split("\\[");
/* 460 */         String ip = sub[0];
/* 461 */         String port = sub[1].substring(0, sub[1].length() - 1);
/* 462 */         String key1 = ITEMS.LOCATOR.name() + (i + 1);
/* 463 */         String key2 = ITEMS.PORT.name() + (i + 1);
/* 464 */         setProperty(key1, ip);
/* 465 */         setProperty(key2, Integer.valueOf(port));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static String processLocalhost(String locators)
/*     */   {
/* 472 */     if ((!StringUtils.isEmpty(locators)) && (locators.indexOf("localhost") != -1))
/*     */     {
/* 474 */       String ip = localhostBindIp(null);
/* 475 */       locators = locators.replaceAll("localhost", ip);
/*     */     }
/* 477 */     return locators;
/*     */   }
/*     */   
/*     */   public static String localhostBindIp(String bindIp)
/*     */   {
/* 482 */     boolean isLocalhost = (bindIp == null) || ("localhost".equalsIgnoreCase(bindIp));
/* 483 */     if (isLocalhost)
/*     */     {
/*     */       try
/*     */       {
/* 487 */         InetAddress addr = InetAddress.getLocalHost();
/* 488 */         bindIp = addr.getHostAddress();
/*     */       }
/*     */       catch (UnknownHostException e)
/*     */       {
/* 492 */         initlized.set(false);
/* 493 */         System.err.println("Unkonw host!");
/* 494 */         return null;
/*     */       }
/*     */     }
/* 497 */     return bindIp;
/*     */   }
/*     */   
/*     */   private static void processBindIp(String bindIp)
/*     */   {
/* 502 */     bindIp = localhostBindIp(bindIp);
/* 503 */     if (!StringUtils.isEmpty(bindIp))
/*     */     {
/* 505 */       setProperty(ITEMS.BINDIP, bindIp);
/* 506 */       String locators = getConfig(ITEMS.LOCATORS);
/* 507 */       locators = locators.replaceAll("localhost", bindIp);
/* 508 */       setProperty(ITEMS.LOCATORS, locators);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static void processSystemEnv(String name)
/*     */   {
/* 519 */     String value = System.getProperty(name);
/* 520 */     if (StringUtils.isEmpty(value))
/*     */     {
/* 522 */       value = System.getenv(name);
/*     */     }
/* 524 */     if (!StringUtils.isEmpty(value))
/*     */     {
/* 526 */       setProperty(name, value);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void setMemberName(String serverName)
/*     */   {
/* 535 */     String hostName = InetAddressUtil.createLocalHost().getHostName();
/* 536 */     setProperty(ITEMS.HOST_NAME.name(), hostName);
/* 537 */     setProperty(ITEMS.NODE_NAME.name(), serverName);
/* 538 */     StringBuilder bui = new StringBuilder();
/* 539 */     if (ADFRuntime.getRuntime().getNodeType() == TYPES.LOCATOR) {
/* 540 */       bui.append(hostName).append("-l-").append(serverName);
/*     */     } else
/* 542 */       bui.append(hostName).append("-s-").append(serverName);
/* 543 */     setProperty(ITEMS.MEMBER_NAME, bui.toString());
/*     */   }
/*     */   
/*     */   public static String getMemberName()
/*     */   {
/* 548 */     return getConfig(ITEMS.MEMBER_NAME);
/*     */   }
/*     */   
/*     */   public static String getMemberId()
/*     */   {
/* 553 */     return getConfig(ITEMS.MEMBER_ID);
/*     */   }
/*     */   
/*     */   public static String getNodeName()
/*     */   {
/* 558 */     return getConfig(ITEMS.NODE_NAME);
/*     */   }
/*     */   
/*     */   public static String getWorkPath()
/*     */   {
/* 563 */     return getConfig(ITEMS.GS_WORK);
/*     */   }
/*     */   
/*     */   public static String getHomePath()
/*     */   {
/* 568 */     return getConfig(ITEMS.GS_HOME);
/*     */   }
/*     */   
/*     */   public static String getProperty(ITEMS item)
/*     */   {
/* 573 */     return getProperty(item, "");
/*     */   }
/*     */   
/*     */   public static String getProperty(ITEMS item, String defaultValue)
/*     */   {
/* 578 */     String value = serverConfig.getProperty(item.name());
/* 579 */     if (value == null)
/* 580 */       value = serverConfig.getProperty(item.proKey);
/* 581 */     if (value == null)
/* 582 */       value = defaultValue;
/* 583 */     return value;
/*     */   }
/*     */   
/*     */   public static String getDefaultLocatorAdddress()
/*     */   {
/* 588 */     if (locatorCount == 0)
/* 589 */       return null;
/* 590 */     return getProperty(ITEMS.LOCATOR.name() + "1");
/*     */   }
/*     */   
/*     */ 
/*     */   public static Integer getDefaultLocatorPort()
/*     */   {
/* 596 */     if (locatorCount == 0)
/* 597 */       return null;
/* 598 */     Object port = getInteger(ITEMS.PORT.name() + "1");
/* 599 */     if ((port instanceof Integer))
/*     */     {
/* 601 */       return (Integer)port;
/*     */     }
/*     */     
/*     */ 
/* 605 */     return Integer.valueOf(NumberUtils.toInt(port.toString()));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static String getProperty(String name)
/*     */   {
/* 612 */     ITEMS item = stringToITEMS(name);
/* 613 */     if (item != null) {
/* 614 */       return getProperty(item);
/*     */     }
/* 616 */     return serverConfig.getProperty(name);
/*     */   }
/*     */   
/*     */   public static String getProperty(String name, String defaultValue)
/*     */   {
/* 621 */     String value = getProperty(name);
/* 622 */     if (value == null)
/* 623 */       return defaultValue;
/* 624 */     return value;
/*     */   }
/*     */   
/*     */   public static Integer getInteger(String name)
/*     */   {
/* 629 */     Object val = getProperty(name);
/* 630 */     if ((val instanceof Integer))
/* 631 */       return (Integer)val;
/* 632 */     if (val != null) {
/* 633 */       return Integer.valueOf(NumberUtils.toInt(val.toString()));
/*     */     }
/* 635 */     return Integer.valueOf(0);
/*     */   }
/*     */   
/*     */   public static Long getLong(String name)
/*     */   {
/* 640 */     Object val = getProperty(name);
/* 641 */     if ((val instanceof Long))
/* 642 */       return (Long)val;
/* 643 */     if (val != null) {
/* 644 */       return Long.valueOf(NumberUtils.toLong(val.toString()));
/*     */     }
/* 646 */     return Long.valueOf(0L);
/*     */   }
/*     */   
/*     */   public static Long getLong(ITEMS item)
/*     */   {
/* 651 */     Object val = getProperty(item);
/* 652 */     if ((val instanceof Long))
/* 653 */       return (Long)val;
/* 654 */     if (val != null) {
/* 655 */       return Long.valueOf(NumberUtils.toLong(val.toString()));
/*     */     }
/* 657 */     return Long.valueOf(0L);
/*     */   }
/*     */   
/*     */   public static Integer getInteger(ITEMS item)
/*     */   {
/* 662 */     Object val = getProperty(item);
/* 663 */     if ((val instanceof Integer))
/* 664 */       return (Integer)val;
/* 665 */     if (val != null) {
/* 666 */       return Integer.valueOf(NumberUtils.toInt(val.toString()));
/*     */     }
/* 668 */     return Integer.valueOf(0);
/*     */   }
/*     */   
/*     */   public static boolean getBoolean(ITEMS item)
/*     */   {
/* 673 */     return getBoolean(item.name());
/*     */   }
/*     */   
/*     */   public static boolean getBoolean(String name)
/*     */   {
/* 678 */     Object v = serverConfig.get(name);
/* 679 */     if ((v != null) && ((v instanceof Boolean)))
/* 680 */       return ((Boolean)v).booleanValue();
/* 681 */     if (v != null)
/* 682 */       return BooleanUtils.toBoolean(v.toString());
/* 683 */     return false;
/*     */   }
/*     */   
/*     */   public static int getLocatorCount()
/*     */   {
/* 688 */     return locatorCount;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean isDev()
/*     */   {
/* 698 */     String env = getConfig(ITEMS.GS_ENV);
/* 699 */     return StringUtils.equalsIgnoreCase("dev", env);
/*     */   }
/*     */   
/*     */ 
/*     */   public static enum TYPES
/*     */   {
/* 705 */     DATASTORE,  LOCATOR,  JMX,  MQ,  CLIENT;
/*     */     
/*     */     private TYPES() {}
/*     */   }
/*     */   
/* 710 */   public static enum ITEMS { GS_ENV("gs.env"),  GS_HOME("gs.home"), 
/* 711 */     GS_WORK("gs.work"),  GS_VERSION("gs.version"), 
/* 712 */     SYSTEM_NAME("system.name"),  SYSTEM_GRIDNAME("system.gridname"), 
/* 713 */     LOCATORS("gs.locators"),  BINDIP("gs.bind.ip"), 
/* 714 */     SYSTEM_WAN_AREA("system.wan.area"),  HTTP_PLUGIN_BIND_ADDRESS("http.plugin.bind.address"), 
/* 715 */     HTTP_PLUGIN_PORT("http.plugin.port"),  SOLR_SERVERS("solr.zk.host"), 
/* 716 */     ZOOKEEPER_SERVERS("zookeeper.servers"), 
/* 717 */     ADF_SESSION_TIMEOUT("adf.session.timeout"),  GS_LOGIC_JAR_DIR("gs.logicJarDir"), 
/* 718 */     DEPLOY_WORKING_DIR("gs.deployWorkingDir"),  NODE_NAME("gs.nodeName"),  NODE_WORK_DIR("gs.node-workDir"), 
/* 719 */     HOST_NAME("gs.hostName"),  MEMBER_NAME("gs.memberName"),  LOCATOR("gs.locator"),  PORT("gs.port"), 
/* 720 */     NODE_TYPE("gs.nodeType"),  MEMBER_ID("gs.memberId"),  DB_TYPE("gs.date.patterns"), 
/* 721 */     DB_NAME("db.default.name"),  DB_URL("db.url"), 
/* 722 */     DB_PORT("db.port"),  DB_DIRVER("db.driver"), 
/* 723 */     DB_USERNAME("db.username"),  DB_PASSWORD("db.password"), 
/* 724 */     DB_DIALECT("db.dialect"),  JMX_HOST("jmx.host"), 
/* 725 */     JMX_PORT("jmx.port"),  HTML_PORT("html.port"), 
/* 726 */     JMX_RMI_PORT("jmx.port"),  JMX_MANAGER_PORT("jmx.manager.port"), 
/* 727 */     NAMING_SERVER("naming.server"),  GRID_OPLOG_META("grid.meta.oplog.max"), 
/* 728 */     GRID_OPLOG_QUEUE("grid.queue.oplog.max"),  GRID_META_VISIBLE("grid.meta.visible"), 
/* 729 */     GRID_STATUS_THREAD_INTERVAL("grid.status-thread.interval"), 
/* 730 */     CACHE_REAL_TIME("grid.cache.realtime"), 
/* 731 */     CLINET_MULTIPLE_USER("client.multiple.user.enabled"), 
/* 732 */     STATISTICS_ENABLED("gs.statistics.enabled"),  TEMPORAL_UNIT("gs.temporal.unit"), 
/* 733 */     GRID_AUTHENTICATION_ENABLED("security.grid.authenticate.enabled"),  AUTH_MODE("security.auth.mode"), 
/* 734 */     CLUSTER_REMOTE_LOCATORS("cluster.remote.locators"),  CLUSTER_DISTRIBUTE_SYSTEM_ID("cluster.distributed.system.id"), 
/* 735 */     ASYNC_QUEUE_BATCH_SIZE("gs.queue.batch-size"),  ASYNC_QUEUE_INTERVAL("gs.queue.time-interval"), 
/* 736 */     ASYNC_QUEUE_BATH_CONFLATION("gs.queue.batch-conflation"), 
/* 737 */     ASYNC_QUEUE_DISP_THREADS("gs.queue.dispatcher-threads"),  MASTER_SERVICE_URL("master.service.url"), 
/* 738 */     ASYNC_BQUEUE_SIZE("gs.block-queue.size"),  ASYNC_THREAD_SIZE("gs.block-thread.size"), 
/* 739 */     SECURITY_SYNC_RATE_HOURS("security.sync.rate.hours"), 
/* 740 */     SECURITY_SYNC_SINGLE_INTERVAL("security.sync.single.interval"),  MASTER_GRID_NAME("master.grid.name"), 
/* 741 */     AS_MASTER_GRID("gs.grid.as.master"),  ADF_REGISTER_TYPE("gs.grid.register.type"), 
/* 742 */     CLIENT_POOL_SINGLEHOP("client.pool.pr-singlehop"),  CLIENT_SENDING_SERVER_MODE("client.sending.server.mode"), 
/* 743 */     CLIENT_POOL_TIMEOUT("client.pool.timeout"),  CLIENT_POOL_RETRY("client.pool.retry-attemps"), 
/* 744 */     CLIENT_POOL_MIN("client.pool.min-connections"),  CLIENT_POOL_MAX("client.pool.max-connections"), 
/* 745 */     CLIENT_FREE_TIMEOUT("client.pool.free-connection-timeout"), 
/* 746 */     SOLR_COMMIT_MAXTIME("solr.commit.max.time"), 
/* 747 */     SOLR_COMMIT_BATCH_SIZE("solr.commit.batch.size"),  INDEXER_SOLR("gs.indexer.solr.enabled"), 
/* 748 */     LUCENE_DIRECTORY("indexer.lucene.directory"),  LUCENE_DISK_STORE("indexer.lucene.disk-store"), 
/* 749 */     LUCENE_AUTO_RECOVER("indexer.lucene.auto-recover"), 
/* 750 */     LUCENE_INTERVAL("indexer.lucene.time-interval"),  LUCENE_BATCHSIZE("indexer.lucene.batch-size"), 
/* 751 */     LUCENE_PAGESIZE("indexer.lucene.page-size"),  LUCENE_PREFETCH_COUNT("indexer.lucene.cache.prefetch-count"), 
/* 752 */     LUCENE_OPLOG("indexer.lucene.oplog-size"), 
/* 753 */     LUCENE_SENDING_WINDOW_SIZE("indexer.lucene.windowedsending.window_size"), 
/* 754 */     LUCENE_SENDING_BUFFER_SIZE("indexer.lucene.windowedsending.buffer_size"), 
/* 755 */     LUCENE_STATISTIC("indexer.lucene.statistic"),  MASTER_CLINET_MULTIPLE_USER("master.client.multiple.user.enabled"), 
/* 756 */     MASTER_CLIENT_POOL_TIMEOUT("master.client.pool.timeout"), 
/* 757 */     MASTER_CLIENT_POOL_RETRY("master.client.pool.retry-attemps"), 
/* 758 */     MASTER_CLIENT_POOL_MIN("master.client.pool.min-connections"), 
/* 759 */     MASTER_CLIENT_POOL_MAX("master.client.pool.max-connections"), 
/* 760 */     MASTER_CLIENT_FREE_TIMEOUT("master.client.pool.free-connection-timeout"),  ADF_NODE_RESET("adf.node.reset"), 
/* 761 */     PATH_NAMESPACE("adf.path-namespace"),  ADF_VERSION_NO("adf.core.version"),  ADF_PARTITION_LISTENER("adf.partition.listener");
/*     */     
/* 763 */     private String proKey = null;
/*     */     
/* 765 */     static { nameMap = new HashMap();
/* 766 */       proKeyMap = new HashMap();
/*     */       
/*     */ 
/*     */ 
/* 770 */       for (ITEMS item : values())
/*     */       {
/* 772 */         nameMap.put(item.name(), item);
/* 773 */         proKeyMap.put(item.proKey, item);
/*     */       }
/*     */     }
/*     */     
/*     */     private static Map<String, ITEMS> nameMap;
/*     */     private static Map<String, ITEMS> proKeyMap;
/*     */     private ITEMS(String name) {
/* 780 */       this.proKey = name;
/*     */     }
/*     */     
/*     */     public String getProKey()
/*     */     {
/* 785 */       return this.proKey;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\support\system\ADFConfigHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */