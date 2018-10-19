/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.support.gemfire;
/*     */ 
/*     */ import com.gemstone.gemfire.cache.CacheClosedException;
/*     */ import com.gemstone.gemfire.cache.client.Pool;
/*     */ import com.gemstone.gemfire.cache.client.ServerConnectivityException;
/*     */ import com.gemstone.gemfire.cache.client.internal.ExecutablePool;
/*     */ import com.gemstone.gemfire.cache.client.internal.GetPDXIdForTypeOp;
/*     */ import com.gemstone.gemfire.cache.client.internal.GetPDXTypeByIdOp;
/*     */ import com.gemstone.gemfire.internal.Assert;
/*     */ import com.gemstone.gemfire.internal.cache.GemFireCacheImpl;
/*     */ import com.gemstone.gemfire.pdx.internal.PdxType;
/*     */ import com.gemstone.gemfire.pdx.internal.TypeRegistry;
/*     */ import com.jnj.adf.grid.client.api.support.ADFServiceContext;
/*     */ import com.jnj.adf.grid.common.ADFException;
/*     */ import com.jnj.adf.grid.manager.GridManager;
/*     */ import com.jnj.adf.grid.manager.RegionHelper;
/*     */ import com.jnj.adf.grid.utils.LogUtil;
/*     */ import com.jnj.adf.grid.utils.Util;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TypeRegistryExt
/*     */   extends TypeRegistry
/*     */ {
/*     */   private TypeRegistry reg;
/*     */   private GemFireCacheImpl cache;
/*  48 */   private static final Map<String, Map<Integer, PdxType>> gridIdToType = new ConcurrentHashMap();
/*  49 */   private static final Map<String, Map<PdxType, Integer>> gridTypeToId = new ConcurrentHashMap();
/*     */   
/*     */ 
/*     */ 
/*     */   public TypeRegistryExt(GemFireCacheImpl cache, boolean disableTypeRegistry, TypeRegistry reg)
/*     */   {
/*  55 */     super(cache, disableTypeRegistry);
/*  56 */     this.reg = reg;
/*     */   }
/*     */   
/*     */   public static final void upgradeClientTypeRegistration()
/*     */   {
/*     */     try
/*     */     {
/*  63 */       GemFireCacheImpl gfc = GemFireCacheImpl.getInstance();
/*  64 */       TypeRegistry tr = gfc.getPdxRegistry();
/*  65 */       TypeRegistryExt ext = new TypeRegistryExt(gfc, false, tr);
/*  66 */       Util.unsafeSet(gfc, "pdxRegistry", ext);
/*  67 */       LogUtil.getCoreLog().info("Extend default TypeRegistry to adapt muti-grid pdx.");
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*  71 */       LogUtil.getCoreLog().error(e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void clear()
/*     */   {
/*  78 */     super.clear();
/*  79 */     String gridId = getTypeRegistryGridId();
/*  80 */     if (StringUtils.isEmpty(gridId)) {
/*  81 */       this.reg.clear();
/*  82 */       gridIdToType.clear();
/*  83 */       gridTypeToId.clear();
/*     */     }
/*     */     else {
/*  86 */       Map<Integer, PdxType> idToType = pdxRegistryIdToType();
/*  87 */       Map<PdxType, Integer> typeToId = pdxRegistryTypeToId();
/*  88 */       idToType.clear();
/*  89 */       typeToId.clear();
/*     */     }
/*     */   }
/*     */   
/*     */   public PdxType getType(int typeId)
/*     */   {
/*  95 */     String gridId = getTypeRegistryGridId();
/*  96 */     if (StringUtils.isEmpty(gridId)) {
/*  97 */       return this.reg.getType(typeId);
/*     */     }
/*  99 */     Map<Integer, PdxType> idToType = pdxRegistryIdToType();
/* 100 */     Map<PdxType, Integer> typeToId = pdxRegistryTypeToId();
/* 101 */     PdxType type = (PdxType)idToType.get(Integer.valueOf(typeId));
/* 102 */     if (type != null)
/*     */     {
/* 104 */       LogUtil.getCoreLog().trace("Use cached PdxType, grid:{},pdxType:{}", new Object[] { gridId, type });
/* 105 */       return type;
/*     */     }
/* 107 */     Pool pool = null;
/*     */     try
/*     */     {
/* 110 */       pool = GridManager.getInstance().getGemFirePool(gridId);
/* 111 */       type = GetPDXTypeByIdOp.execute((ExecutablePool)pool, typeId);
/* 112 */       if (type != null)
/*     */       {
/* 114 */         idToType.put(Integer.valueOf(typeId), type);
/* 115 */         typeToId.put(type, Integer.valueOf(typeId));
/* 116 */         LogUtil.getCoreLog().debug("Send GetPDXTypeByIdOp fetch pdxType, grid:{},path:{},pdxType:{}", new Object[] { gridId, type });
/* 117 */         return type;
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 122 */       LogUtil.getCoreLog().warn("Received an exception getting pdx type from pool {}, {}", new Object[] { pool, e.getMessage(), e });
/*     */     }
/* 124 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public int defineType(PdxType newType)
/*     */   {
/* 130 */     String gridId = getTypeRegistryGridId();
/* 131 */     if (StringUtils.isEmpty(gridId)) {
/* 132 */       return this.reg.defineType(newType);
/*     */     }
/*     */     
/* 135 */     Map<Integer, PdxType> idToType = pdxRegistryIdToType();
/* 136 */     Map<PdxType, Integer> typeToId = pdxRegistryTypeToId();
/*     */     
/* 138 */     Integer existingId = (Integer)typeToId.get(newType);
/* 139 */     if (existingId != null) {
/* 140 */       int eid = existingId.intValue();
/* 141 */       newType.setTypeId(eid);
/* 142 */       return eid;
/*     */     }
/*     */     
/* 145 */     int id = defineDistributeType(newType);
/* 146 */     newType.setTypeId(id);
/* 147 */     PdxType oldType = (PdxType)idToType.get(Integer.valueOf(id));
/* 148 */     if (oldType == null) {
/* 149 */       idToType.put(Integer.valueOf(id), newType);
/* 150 */       typeToId.put(newType, Integer.valueOf(id));
/*     */     }
/* 152 */     else if (!oldType.equals(newType)) {
/* 153 */       Assert.fail("Old type does not equal new type for the same id. oldType=" + oldType + " new type=" + newType);
/*     */     }
/* 155 */     return id;
/*     */   }
/*     */   
/*     */   public int defineDistributeType(PdxType newType)
/*     */   {
/* 160 */     String gridId = ADFServiceContext.getGrid();
/* 161 */     String path = ADFServiceContext.getPath();
/* 162 */     if (StringUtils.isEmpty(gridId))
/*     */     {
/* 164 */       gridId = RegionHelper.getGridIdByRegionPath(path);
/*     */     }
/* 166 */     if (!StringUtils.isEmpty(gridId))
/*     */     {
/* 168 */       Pool pool = GridManager.getInstance().getGemFirePool(gridId);
/* 169 */       ServerConnectivityException lastException = null;
/*     */       try {
/* 171 */         int result = GetPDXIdForTypeOp.execute((ExecutablePool)pool, newType);
/* 172 */         newType.setTypeId(result);
/* 173 */         return result;
/*     */       }
/*     */       catch (ServerConnectivityException e) {
/* 176 */         lastException = e;
/*     */         
/* 178 */         if (lastException != null) {
/* 179 */           throw lastException;
/*     */         }
/* 181 */         if (this.cache.isClosed()) {
/* 182 */           throw new CacheClosedException("PDX detected cache was closed");
/*     */         }
/* 184 */         throw new CacheClosedException("Client pools have been closed so the PDX type registry can not define a type.");
/*     */       } }
/* 186 */     return this.reg.defineType(newType);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private Map<PdxType, Integer> pdxRegistryTypeToId()
/*     */   {
/* 195 */     String gridId = getTypeRegistryGridId();
/* 196 */     if (StringUtils.isEmpty(gridId)) {
/* 197 */       throw new ADFException("Grid id not found. you should assign a grid id before gemfire opration. ");
/*     */     }
/*     */     
/* 200 */     if (!gridTypeToId.containsKey(gridId)) {
/* 201 */       gridTypeToId.put(gridId, new ConcurrentHashMap());
/*     */     }
/* 203 */     return (Map)gridTypeToId.get(gridId);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private Map<Integer, PdxType> pdxRegistryIdToType()
/*     */   {
/* 211 */     String gridId = getTypeRegistryGridId();
/* 212 */     if (StringUtils.isEmpty(gridId)) {
/* 213 */       throw new ADFException("Grid id not found. you should assign a grid id before gemfire opration. ");
/*     */     }
/* 215 */     if (!gridIdToType.containsKey(gridId)) {
/* 216 */       gridIdToType.put(gridId, new ConcurrentHashMap());
/*     */     }
/* 218 */     return (Map)gridIdToType.get(gridId);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private String getTypeRegistryGridId()
/*     */   {
/* 225 */     String gridId = ADFServiceContext.getGrid();
/* 226 */     String path = ADFServiceContext.getPath();
/* 227 */     if (StringUtils.isEmpty(gridId))
/*     */     {
/* 229 */       gridId = RegionHelper.getGridIdByRegionPath(path);
/*     */     }
/* 231 */     return gridId;
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\support\gemfire\TypeRegistryExt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */