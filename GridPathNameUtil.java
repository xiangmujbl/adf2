/*     */ package com.jnj.adf.dataservice.adfcoreignite;
/*     */ 
/*     */ import org.apache.commons.lang3.StringUtils;

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
/*     */ public class GridPathNameUtil
/*     */ {
/*     */   public static final String PATH_SEPARATOR = "/";
/*     */   
/*     */   public static String formatPathWithoutPrefix(String path)
/*     */   {
/*  34 */     if ((!StringUtils.isEmpty(path)) && (path.startsWith("/")))
/*     */     {
/*  36 */       path = path.substring(1);
/*     */     }
/*  38 */     return path;
/*     */   }
/*     */   
/*     */   public static String formatPathWithPrefix(String path)
/*     */   {
/*  43 */     if ((!StringUtils.isEmpty(path)) && (!path.startsWith("/")))
/*     */     {
/*  45 */       return "/" + path;
/*     */     }
/*     */     
/*  48 */     return path;
/*     */   }
/*     */   
/*     */   public static String formatRemoteNameWithPrefix(String gridId, String serviceName)
/*     */   {
/*  53 */     if ((!StringUtils.isEmpty(serviceName)) && (!StringUtils.isEmpty(gridId)))
/*     */     {
/*  55 */       return gridId + "/" + serviceName;
/*     */     }
/*     */     
/*  58 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public static String getFullPath(String gridId, String regionPath)
/*     */   {
/*  64 */     StringBuilder path = new StringBuilder();
/*  65 */     joinPath(path, gridId, regionPath);
/*  66 */     return path.toString();
/*     */   }
/*     */   
/*     */   public static String join(String... pathNames)
/*     */   {
/*  71 */     StringBuilder path = new StringBuilder();
/*  72 */     if (pathNames != null) {
/*  73 */       for (String pathName : pathNames) {
/*  74 */         path.append("/");
/*  75 */         path.append(pathName);
/*     */       }
/*     */     }
/*  78 */     return path.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private static void joinPath(StringBuilder path, String parent, String child)
/*     */   {
/*  86 */     if ((parent != null) && (parent.length() > 0))
/*     */     {
/*  88 */       if (!parent.startsWith("/"))
/*     */       {
/*  90 */         path.append("/");
/*     */       }
/*  92 */       if (parent.endsWith("/"))
/*     */       {
/*  94 */         path.append(parent.substring(0, parent.length() - 1));
/*     */       }
/*     */       else
/*     */       {
/*  98 */         path.append(parent);
/*     */       }
/*     */     }
/*     */     
/* 102 */     if ((child == null) || (child.length() == 0) || (child.equals("/")))
/*     */     {
/*     */ 
/* 105 */       if (path.length() == 0)
/*     */       {
/* 107 */         path.append("/");
/*     */       }
/* 109 */       return;
/*     */     }
/*     */     
/*     */ 
/* 113 */     path.append("/");
/*     */     
/* 115 */     if (child.startsWith("/"))
/*     */     {
/* 117 */       child = child.substring(1);
/*     */     }
/*     */     
/* 120 */     if (child.endsWith("/"))
/*     */     {
/* 122 */       child = child.substring(0, child.length() - 1);
/*     */     }
/*     */     
/* 125 */     path.append(child);
/*     */   }
/*     */   
/*     */   public static String getUserPath(String regionPath)
/*     */   {
/* 130 */     if (StringUtils.startsWith(regionPath, "/"))
/*     */     {
/* 132 */       return regionPath;
/*     */     }
/* 134 */     return "/" + regionPath;
/*     */   }
/*     */   
/*     */   public static String getRootName(String regionPath)
/*     */   {
/* 139 */     if (StringUtils.endsWith(regionPath, "/"))
/*     */     {
/* 141 */       regionPath = regionPath.substring(0, regionPath.length() - 1);
/*     */     }
/* 143 */     if (StringUtils.indexOf(regionPath, "/") >= 0)
/*     */     {
/* 145 */       return StringUtils.substringAfterLast(regionPath, "/");
/*     */     }
/* 147 */     return regionPath;
/*     */   }
/*     */   
/*     */   public static String getRootRegionName(String fullPath)
/*     */   {
/* 152 */     if (StringUtils.startsWith(fullPath, "/"))
/*     */     {
/* 154 */       fullPath = fullPath.substring(1);
/*     */     }
/* 156 */     if (StringUtils.indexOf(fullPath, "/") >= 0)
/*     */     {
/* 158 */       return StringUtils.substringBefore(fullPath, "/");
/*     */     }
/* 160 */     return fullPath;
/*     */   }
/*     */   
/*     */   public static String getParentRegionName(String fullPath)
/*     */   {
/* 165 */     fullPath = escapePath(fullPath);
/* 166 */     if (StringUtils.indexOf(fullPath, "/") >= 0)
/*     */     {
/* 168 */       return StringUtils.substringBeforeLast(fullPath, "/");
/*     */     }
/* 170 */     return fullPath;
/*     */   }
/*     */   
/*     */ 
/*     */   public static String getGridId(String fullPath)
/*     */   {
/* 176 */     if (StringUtils.startsWith(fullPath, "/"))
/*     */     {
/* 178 */       fullPath = fullPath.substring(0, fullPath.length() - 1);
/*     */     }
/* 180 */     if (StringUtils.indexOf(fullPath, "/") >= 0)
/*     */     {
/* 182 */       return StringUtils.substringBefore(fullPath, "/");
/*     */     }
/* 184 */     return fullPath;
/*     */   }
/*     */   
/*     */   public static String escapePath(String path)
/*     */   {
/* 189 */     if (StringUtils.isEmpty(path)) {
/* 190 */       return path;
/*     */     }
/* 192 */     if (!path.startsWith("/"))
/*     */     {
/* 194 */       StringBuilder bd = new StringBuilder("/");
/* 195 */       return path;
/*     */     }
/* 197 */     return path;
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\common\GridPathNameUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */