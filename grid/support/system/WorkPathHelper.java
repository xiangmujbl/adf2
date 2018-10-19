/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.support.system;
/*     */ 
/*     */ import com.jnj.adf.grid.support.context.ADFRuntime;
/*     */ import com.jnj.adf.grid.utils.LogUtil;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.nio.channels.Channels;
/*     */ import java.nio.channels.FileChannel;
/*     */ import java.nio.channels.FileLock;
/*     */ import java.nio.channels.OverlappingFileLockException;
/*     */ import org.apache.commons.io.FileUtils;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.apache.logging.log4j.Logger;
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
/*     */ public class WorkPathHelper
/*     */ {
/*     */   private static FileLock lock;
/*     */   
/*     */   public void unlock(FileLock lock)
/*     */   {
/*  38 */     if (lock != null)
/*     */     {
/*     */       try
/*     */       {
/*  42 */         lock.release();
/*     */       }
/*     */       catch (IOException x)
/*     */       {
/*  46 */         throw new RuntimeException(x);
/*     */       }
/*  48 */       lock = null;
/*     */     }
/*     */   }
/*     */   
/*     */   public static File verifyWorkPath(String path)
/*     */   {
/*  54 */     String home = ADFConfigHelper.getConfig(ADFConfigHelper.ITEMS.GS_WORK);
/*  55 */     return verifyPath(home, path, false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static File verifyPath(String path)
/*     */   {
/*  66 */     return verifyPath(path, false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static File verifyPath(String path, boolean clean)
/*     */   {
/*  78 */     String home = ADFConfigHelper.getConfig(ADFConfigHelper.ITEMS.GS_HOME);
/*  79 */     return verifyPath(home, path, clean);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static File verifyPath(String home, String path, boolean clean)
/*     */   {
/*  90 */     StringBuilder builder = new StringBuilder();
/*  91 */     builder.append(home).append("/");
/*  92 */     String parent = builder.toString();
/*  93 */     File dir = new File(parent, path);
/*  94 */     if ((clean) && (dir.exists()))
/*     */     {
/*  96 */       FileUtils.deleteQuietly(dir);
/*     */     }
/*  98 */     if (!dir.exists())
/*     */     {
/* 100 */       boolean bl = dir.mkdirs();
/* 101 */       if (!bl)
/*     */       {
/* 103 */         System.err.println("Directory create failure:" + dir.getAbsolutePath());
/* 104 */         return null;
/*     */       }
/*     */     }
/* 107 */     return dir;
/*     */   }
/*     */   
/*     */   public static String verifyServerName(ADFConfigHelper.ITEMS item, String type, String name)
/*     */   {
/* 112 */     String home = ADFConfigHelper.getConfig(item);
/* 113 */     StringBuilder builder = new StringBuilder();
/* 114 */     builder.append(home).append("/").append(type);
/* 115 */     String parent = builder.toString();
/* 116 */     if (StringUtils.isBlank(name)) {
/* 117 */       return randomServerName(parent);
/*     */     }
/*     */     
/* 120 */     File f = findDirectory(parent, name);
/* 121 */     if (f != null)
/*     */     {
/* 123 */       if (lock(f))
/*     */       {
/* 125 */         System.out.println("Check directory ok:" + f);
/* 126 */         return name;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 131 */     throw new RuntimeException("Error create work path:" + parent + "/" + name);
/*     */   }
/*     */   
/*     */   public static String randomServerName(String parent)
/*     */   {
/* 136 */     for (int i = 0; 
/* 137 */         i < 100; i++)
/*     */     {
/* 139 */       File f = findDirectory(parent, "node" + i);
/* 140 */       if (f != null)
/*     */       {
/* 142 */         if (lock(f))
/*     */         {
/* 144 */           return "node" + i;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 150 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static synchronized File findDirectory(String parent, String serverName)
/*     */   {
/* 159 */     if (serverName != null)
/*     */     {
/* 161 */       File dir = new File(parent, serverName);
/* 162 */       if (dir.exists())
/*     */       {
/* 164 */         if (!ADFConfigHelper.getBoolean(ADFConfigHelper.ITEMS.ADF_NODE_RESET))
/* 165 */           return dir;
/* 166 */         boolean bl = FileUtils.deleteQuietly(dir);
/* 167 */         LogUtil.getCoreLog().info("Work path {} exists. Clean all:{}.", new Object[] { dir, Boolean.valueOf(bl) });
/*     */       }
/* 169 */       boolean bl = dir.mkdirs();
/* 170 */       LogUtil.getCoreLog().info("Work path {} created.", new Object[] { dir });
/* 171 */       if (!bl)
/*     */       {
/* 173 */         System.err.println("Directory create failure:" + dir.getAbsolutePath());
/* 174 */         return null;
/*     */       }
/*     */       
/* 177 */       return dir;
/*     */     }
/* 179 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private static boolean lock(File directory)
/*     */   {
/* 186 */     String name = "node.lock";
/* 187 */     File lockfile = new File(directory, name);
/* 188 */     lockfile.deleteOnExit();
/*     */     try
/*     */     {
/* 191 */       FileChannel fc = (FileChannel)Channels.newChannel(new FileOutputStream(lockfile));
/* 192 */       lock = fc.tryLock();
/* 193 */       if (lock != null)
/*     */       {
/* 195 */         return true;
/*     */       }
/*     */     }
/*     */     catch (IOException x)
/*     */     {
/* 200 */       System.err.println(x.toString());
/*     */     }
/*     */     catch (OverlappingFileLockException e)
/*     */     {
/* 204 */       System.err.println(e.toString());
/*     */     }
/* 206 */     return false;
/*     */   }
/*     */   
/*     */   public static String diskStore(String diskStoreName)
/*     */   {
/* 211 */     return diskStore(null, diskStoreName);
/*     */   }
/*     */   
/*     */   public static String diskStore(String category, String diskStoreName) {
/* 215 */     String parent = ADFConfigHelper.getConfig(ADFConfigHelper.ITEMS.GS_WORK);
/* 216 */     String serverName = ADFConfigHelper.getProperty(ADFConfigHelper.ITEMS.NODE_NAME);
/* 217 */     String nodeTypePath = "server";
/* 218 */     if (ADFRuntime.getRuntime().getNodeType() == ADFConfigHelper.TYPES.LOCATOR)
/*     */     {
/* 220 */       nodeTypePath = "locator";
/*     */     }
/* 222 */     StringBuilder bui = new StringBuilder();
/* 223 */     bui.append(parent).append("/").append(nodeTypePath).append("/").append(serverName);
/* 224 */     if (!StringUtils.isEmpty(category))
/* 225 */       bui.append("/").append(category);
/* 226 */     File diskStore = findDirectory(bui.toString(), diskStoreName);
/* 227 */     return diskStore.getAbsolutePath();
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\support\system\WorkPathHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */