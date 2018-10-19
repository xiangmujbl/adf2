/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.namespace;
/*     */ 
/*     */ import com.jnj.adf.client.api.JsonObject;
/*     */ import com.jnj.adf.grid.common.GridPathNameUtil;
/*     */ import com.jnj.adf.grid.support.context.ADFRuntime;
/*     */ import com.jnj.adf.grid.support.system.ADFConfigHelper;
/*     */ import com.jnj.adf.grid.support.system.ADFConfigHelper.ITEMS;
/*     */ import com.jnj.adf.grid.utils.LogUtil;
/*     */ import java.io.PrintStream;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public final class ExecutionContextHelper
/*     */ {
/*     */   public static final String QA = "QA";
/*     */   public static final String PRODUCTION = "PRD";
/*     */   public static final String DEV = "DEV";
/*     */   public static final String SEP = "/";
/*  19 */   private static final ThreadLocal<ExecutionContextImpl> tl_ecc = new InheritableThreadLocal()
/*     */   {
/*     */     protected ExecutionContextImpl initialValue()
/*     */     {
/*  23 */       ExecutionContextImpl ecc = new ExecutionContextImpl();
/*  24 */       return ecc;
/*     */     }
/*     */   };
/*     */   
/*     */   private static final ExecutionContextImpl changValue(String namespace, String version)
/*     */   {
/*  30 */     ExecutionContextImpl ecc = new ExecutionContextImpl(namespace, version);
/*  31 */     return ecc;
/*     */   }
/*     */   
/*     */   public static final ExecutionContext setNamespace(String namespace) {
/*  35 */     ExecutionContextImpl ecc = (ExecutionContextImpl)tl_ecc.get();
/*  36 */     ExecutionContextImpl newEcc = changValue(namespace, ecc.getVersion());
/*  37 */     tl_ecc.set(newEcc);
/*  38 */     return ecc;
/*     */   }
/*     */   
/*     */   public static final ExecutionContext setVersion(String version)
/*     */   {
/*  43 */     ExecutionContextImpl ecc = (ExecutionContextImpl)tl_ecc.get();
/*  44 */     ExecutionContextImpl newEcc = changValue(ecc.getNamespace(), version);
/*  45 */     tl_ecc.set(newEcc);
/*  46 */     return newEcc;
/*     */   }
/*     */   
/*     */   public static final void clearNamespace()
/*     */   {
/*  51 */     tl_ecc.remove();
/*     */   }
/*     */   
/*     */   public static final String currentVersion()
/*     */   {
/*  56 */     ExecutionContext ecc = currentExecutionContext();
/*  57 */     return ecc.getVersion();
/*     */   }
/*     */   
/*     */   public static final String currentNamespace()
/*     */   {
/*  62 */     return currentNamespace(null);
/*     */   }
/*     */   
/*     */   public static final String currentNamespace(String path)
/*     */   {
/*  67 */     ExecutionContext ecc = currentExecutionContext(path);
/*  68 */     return ecc.getNamespace();
/*     */   }
/*     */   
/*     */   public static final ExecutionContext currentExecutionContext()
/*     */   {
/*  73 */     return currentExecutionContext(null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static final ExecutionContext currentExecutionContext(String inputPath)
/*     */   {
/*  84 */     ExecutionContextImpl ecc = (ExecutionContextImpl)tl_ecc.get();
/*  85 */     if (ecc.getNamespace() == null)
/*     */     {
/*  87 */       if (ADFRuntime.getRuntime().isClientSide())
/*     */       {
/*  89 */         String nm = ADFConfigHelper.getProperty(ITEMS.PATH_NAMESPACE);
/*  90 */         ecc.setNamespace(nm);
/*     */ 
/*     */ 
/*     */       }
/*  94 */       else if (!StringUtils.isEmpty(inputPath))
/*     */       {
/*  96 */         String nm = NamespaceService.getInstance().findNamespace(inputPath);
/*  97 */         if (nm != null)
/*     */         {
/*  99 */           ecc.setNamespace(nm);
/*     */         }
/*     */       }
/*     */     }
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
/*     */ 
/*     */ 
/*     */ 
/* 134 */     return ecc;
/*     */   }
/*     */   
/*     */   public static final JsonObject genNamespaceParam(String path)
/*     */   {
/* 139 */     JsonObject jo = JsonObject.create();
/* 140 */     ExecutionContext ecc = currentExecutionContext(path);
/* 141 */     if (ecc != null)
/*     */     {
/* 143 */       jo.append("_NAMESPACE", ecc.getNamespace());
/*     */     }
/* 145 */     return jo;
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
/*     */   public static final String processNamespace(String inputPath)
/*     */   {
/* 162 */     String path = inputPath;
/* 163 */     ExecutionContext ecc = currentExecutionContext(inputPath);
/* 164 */     if ((ecc != null) && (!StringUtils.isEmpty(ecc.getNamespace())))
/*     */     {
/* 166 */       if (!path.startsWith("/" + ecc.getNamespace()))
/*     */       {
/* 168 */         StringBuilder bd = new StringBuilder("/");
/* 169 */         path = GridPathNameUtil.escapePath(path);
/* 170 */         if (!"PRD".equals(ecc.getNamespace()))
/*     */         {
/* 172 */           bd.append(ecc.getNamespace()).append(path);
/* 173 */           path = bd.toString();
/*     */         }
/*     */         else {
/* 176 */           path = inputPath;
/*     */         } }
/* 178 */       LogUtil.getCoreLog().debug("Found namespace:{}, inputPath:{} to {}", new Object[] { ecc.getNamespace(), inputPath, path });
/*     */     }
/* 180 */     return path;
/*     */   }
/*     */   
/*     */   public static void main(String[] args)
/*     */   {
/* 185 */     String s = checkNamespace("/QA/prod1");
/* 186 */     System.out.println(s);
/* 187 */     s = checkNamespace("/INT/prod1");
/* 188 */     System.out.println(s);
/* 189 */     s = checkNamespace("/uto/prod1");
/* 190 */     System.out.println(s);
/* 191 */     s = checkNamespace("/utoprod1");
/* 192 */     System.out.println(s);
/*     */   }
/*     */   
/*     */ 
/*     */   public static final String checkNamespace(String fullpath)
/*     */   {
/* 198 */     if (!StringUtils.isEmpty(fullpath))
/*     */     {
/* 200 */       fullpath = GridPathNameUtil.escapePath(fullpath);
/* 201 */       String[] names = fullpath.split("/");
/* 202 */       if ("QA".equals(names[1]))
/* 203 */         return "QA";
/* 204 */       if ("INT".equals(names[1]))
/* 205 */         return "INT";
/*     */     }
/* 207 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\namespace\ExecutionContextHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */