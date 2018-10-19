/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.security.column;
/*     */ 
/*     */ import com.gemstone.gemfire.management.cli.Result;
/*     */ import com.gemstone.gemfire.management.internal.cli.json.GfJsonArray;
/*     */ import com.gemstone.gemfire.management.internal.cli.json.GfJsonObject;
/*     */ import com.gemstone.gemfire.management.internal.cli.result.CommandResult;
/*     */ import com.gemstone.gemfire.pdx.PdxInstance;
/*     */ import com.gemstone.gemfire.pdx.internal.PdxInstanceImpl;
/*     */ import com.jnj.adf.client.api.IBiz;
/*     */ import com.jnj.adf.grid.auth.impl.AuthSessionContextHelper;
/*     */ import com.jnj.adf.grid.auth.impl.AuthUserPrivilege;
/*     */ import com.jnj.adf.grid.auth.session.AuthInternalSession;
/*     */ import com.jnj.adf.grid.client.api.impl.EntryImpl;
/*     */ import com.jnj.adf.grid.common.PdxUtils;
/*     */ import com.jnj.adf.grid.security.utils.ColumnMaskUtils;
/*     */ import com.jnj.adf.grid.utils.JsonUtils;
/*     */ import com.jnj.adf.grid.utils.LogUtil;
/*     */ import com.jnj.adf.grid.utils.PdxBeanUtils;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.springframework.shell.event.ParseResult;
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
/*     */ public class ColumnPrivilegeHelper
/*     */ {
/*     */   public static <V> V checkColumn(String path, V value, Class<V> classtype)
/*     */   {
/*  49 */     Map<String, Map<String, String>> map = getRegionPermissionMap(path);
/*  50 */     if ((map == null) || ((map != null) && (map.size() == 0)))
/*  51 */       return value;
/*  52 */     Map attmap = null;
/*  53 */     if (classtype.equals(String.class)) {
/*  54 */       attmap = (Map)JsonUtils.jsonToObject(value.toString(), Map.class);
/*  55 */     } else if ((classtype.equals(Map.class)) || (classtype.equals(HashMap.class))) {
/*  56 */       attmap = (Map)JsonUtils.convertValue(value, Map.class);
/*     */     }
/*     */     else {
/*  59 */       attmap = (Map)JsonUtils.convertValue(JsonUtils.objectToJson(value), Map.class);
/*     */     }
/*     */     
/*     */ 
/*  63 */     maskColumnValue(attmap, map);
/*     */     
/*  65 */     if (classtype.equals(String.class)) {
/*  66 */       return JsonUtils.objectToJson(attmap);
/*     */     }
/*  68 */     return (V)JsonUtils.convertValue(attmap, classtype);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String checkColumn(String path, String value)
/*     */   {
/*  80 */     Map<String, Map<String, String>> map = getRegionPermissionMap(path);
/*  81 */     if ((map == null) || ((map != null) && (map.size() == 0))) {
/*  82 */       return value;
/*     */     }
/*  84 */     Map attmap = (Map)JsonUtils.jsonToObject(value, Map.class);
/*     */     
/*  86 */     maskColumnValue(attmap, map);
/*     */     
/*  88 */     return JsonUtils.objectToJson(attmap);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <T> List<T> checkColumn(String path, List<T> value)
/*     */   {
/* 113 */     if ((value == null) || ((value != null) && (value.size() == 0))) {
/* 114 */       return value;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 120 */     Map<String, Map<String, String>> map = getRegionPermissionMap(path);
/* 121 */     if ((map == null) || ((map != null) && (map.size() == 0))) {
/* 122 */       return value;
/*     */     }
/* 124 */     List<T> rtnlist = new ArrayList();
/*     */     
/* 126 */     for (T item : value)
/*     */     {
/* 128 */       if ((item instanceof PdxInstance))
/*     */       {
/* 130 */         PdxInstanceImpl pdxitem = (PdxInstanceImpl)item;
/* 131 */         rtnlist.add(maskColumnValue(pdxitem, map));
/*     */       }
/* 133 */       else if ((item instanceof EntryImpl))
/*     */       {
/* 135 */         EntryImpl entryitem = (EntryImpl)item;
/* 136 */         rtnlist.add(maskColumnValue(entryitem, map));
/*     */       }
/*     */       else
/*     */       {
/* 140 */         Map attmap = (Map)JsonUtils.jsonToObject(JsonUtils.objectToJson(item), Map.class);
/* 141 */         maskColumnValue(attmap, map);
/* 142 */         rtnlist.add(JsonUtils.objectToJson(attmap));
/*     */       }
/*     */     }
/* 145 */     return rtnlist;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <T> List<Entry<String, T>> checkEntryRegionColumn(String path, List<Entry<String, T>> value, Class<T> classtype)
/*     */   {
/* 153 */     if ((value == null) || ((value != null) && (value.size() == 0))) {
/* 154 */       return value;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 160 */     Map<String, Map<String, String>> map = getRegionPermissionMap(path);
/* 161 */     if ((map == null) || ((map != null) && (map.size() == 0))) {
/* 162 */       return value;
/*     */     }
/* 164 */     List<Entry<String, T>> rtnlist = new ArrayList();
/*     */     
/* 166 */     for (Entry<String, T> entry : value)
/*     */     {
/* 168 */       T item = entry.getValue();
/* 169 */       if ((item instanceof PdxInstance))
/*     */       {
/* 171 */         PdxInstanceImpl pdxitem = (PdxInstanceImpl)item;
/* 172 */         entry.setValue(maskColumnValue(pdxitem, map));
/*     */       }
/* 174 */       else if ((item instanceof EntryImpl))
/*     */       {
/* 176 */         EntryImpl entryitem = (EntryImpl)item;
/* 177 */         entry.setValue(maskColumnValue(entryitem, map));
/*     */       }
/*     */       else
/*     */       {
/* 181 */         Map attmap = (Map)JsonUtils.jsonToObject(JsonUtils.objectToJson(item), Map.class);
/* 182 */         maskColumnValue(attmap, map);
/* 183 */         entry.setValue(JsonUtils.convertValue(attmap, classtype));
/*     */       }
/* 185 */       rtnlist.add(entry);
/*     */     }
/* 187 */     return rtnlist;
/*     */   }
/*     */   
/*     */ 
/*     */   public static <T> List<T> checkColumn(String path, List<T> value, Class<T> classtype)
/*     */   {
/* 193 */     if ((value == null) || ((value != null) && (value.size() == 0))) {
/* 194 */       return value;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 200 */     Map<String, Map<String, String>> map = getRegionPermissionMap(path);
/* 201 */     if ((map == null) || ((map != null) && (map.size() == 0))) {
/* 202 */       return value;
/*     */     }
/* 204 */     List<T> rtnlist = new ArrayList();
/*     */     
/* 206 */     for (T item : value)
/*     */     {
/* 208 */       if ((item instanceof PdxInstance))
/*     */       {
/* 210 */         PdxInstanceImpl pdxitem = (PdxInstanceImpl)item;
/* 211 */         rtnlist.add(maskColumnValue(pdxitem, map));
/*     */       }
/* 213 */       else if ((item instanceof EntryImpl))
/*     */       {
/* 215 */         EntryImpl entryitem = (EntryImpl)item;
/* 216 */         rtnlist.add(maskColumnValue(entryitem, map));
/*     */       }
/*     */       else
/*     */       {
/* 220 */         Map attmap = (Map)JsonUtils.jsonToObject(JsonUtils.objectToJson(item), Map.class);
/* 221 */         maskColumnValue(attmap, map);
/* 222 */         rtnlist.add(JsonUtils.convertValue(attmap, classtype));
/*     */       }
/*     */     }
/* 225 */     return rtnlist;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void checkGfshQuery(ParseResult parseResult, Result result)
/*     */   {
/* 232 */     String path = getRegionPath(parseResult.getArguments());
/* 233 */     if (StringUtils.isEmpty(path))
/* 234 */       return;
/* 235 */     Map<String, Map<String, String>> map = getRegionPermissionMap(path);
/* 236 */     if ((map == null) || ((map != null) && (map.size() == 0))) {
/* 237 */       return;
/*     */     }
/* 239 */     LogUtil.getCoreLog().info("ColumnPrivileges checkGfshQuery path={}", new Object[] { path });
/*     */     
/* 241 */     CommandResult cr = (CommandResult)result;
/*     */     try {
/* 243 */       if (cr.getContent() == null)
/* 244 */         return;
/* 245 */       GfJsonObject obj_lv1 = cr.getContent().getJSONObject("__sections__-ARG_SECTION");
/* 246 */       if (obj_lv1 == null)
/* 247 */         return;
/* 248 */       GfJsonObject obj_lv2 = obj_lv1.getJSONObject("__tables__-0");
/* 249 */       if (obj_lv2 == null)
/* 250 */         return;
/* 251 */       GfJsonObject obj_lv3 = obj_lv2.getJSONObject("content");
/* 252 */       if (obj_lv3 == null) {
/* 253 */         return;
/*     */       }
/* 255 */       Iterator<String> it = map.keySet().iterator();
/* 256 */       while (it.hasNext()) {
/* 257 */         String columnName = (String)it.next();
/* 258 */         Map<String, String> detialmap = (Map)map.get(columnName);
/* 259 */         String type = (String)detialmap.get("MASK_TYPE");
/* 260 */         String maskLength = (String)detialmap.get("MASK_LENGTH");
/* 261 */         String starLength = (String)detialmap.get("STAR_LENGHT");
/* 262 */         String maskName = (String)detialmap.get("MASK_NAME");
/* 263 */         GfJsonArray jarray = obj_lv3.getJSONArray(columnName);
/* 264 */         if ((jarray != null) && (jarray.size() > 0))
/*     */         {
/* 266 */           for (int i = 0; i < jarray.size(); i++) {
/*     */             try
/*     */             {
/* 269 */               if (jarray.get(i) != null) {
/* 270 */                 jarray.put(i, ColumnMaskUtils.stringMask(maskName, jarray.get(i).toString(), maskLength, type, starLength));
/*     */               }
/*     */             } catch (Exception localException) {}
/*     */           }
/* 274 */           obj_lv3.put(columnName, jarray);
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception localException1) {}
/*     */   }
/*     */   
/*     */ 
/*     */   private static String getRegionPath(Object[] obj)
/*     */   {
/* 284 */     String sourceString = "";
/* 285 */     Object[] arrayOfObject = obj;int i = arrayOfObject.length; Object item; for (Object localObject1 = 0; localObject1 < i; localObject1++) { item = arrayOfObject[localObject1];
/* 286 */       if (item == null)
/* 287 */         return "";
/* 288 */       sourceString = sourceString + " " + item.toString();
/*     */     }
/* 290 */     if (StringUtils.isNotEmpty(sourceString))
/*     */     {
/* 292 */       String[] ss = sourceString.split(" ");
/* 293 */       String[] arrayOfString1 = ss;localObject1 = arrayOfString1.length; for (item = 0; item < localObject1; item++) { String item = arrayOfString1[item];
/*     */         
/* 295 */         if (item.startsWith("/"))
/* 296 */           return item;
/*     */       }
/*     */     }
/* 299 */     return "";
/*     */   }
/*     */   
/*     */   private static Map<String, Map<String, String>> getRegionPermissionMap(String path) {
/* 303 */     if (AuthSessionContextHelper.getTlSession() != null)
/*     */     {
/* 305 */       Set<String> roles = AuthSessionContextHelper.getTlSession().getUserPrivilege().getRoles();
/* 306 */       Map<String, Map<String, String>> map = ((IColumnPrivilegeService)IBiz.lookup(IColumnPrivilegeService.class)).getRegionPermissionMap(path, roles);
/*     */       
/* 308 */       return map;
/*     */     }
/* 310 */     return null;
/*     */   }
/*     */   
/*     */   private static EntryImpl maskColumnValue(EntryImpl entry, Map<String, Map<String, String>> map)
/*     */   {
/* 315 */     if (entry != null)
/*     */     {
/* 317 */       if ((entry.getValue() instanceof PdxInstance))
/*     */       {
/* 319 */         PdxInstanceImpl pdxitem = (PdxInstanceImpl)entry.getValue();
/* 320 */         entry.setValue(maskColumnValue(pdxitem, map));
/*     */       }
/* 322 */       else if ((entry.getValue() instanceof String))
/*     */       {
/* 324 */         String value = entry.getValue().toString();
/* 325 */         Map attmap = (Map)JsonUtils.jsonToObject(value, Map.class);
/* 326 */         maskColumnValue(attmap, map);
/* 327 */         entry.setValue(JsonUtils.objectToJson(attmap));
/*     */       }
/*     */     }
/* 330 */     return entry;
/*     */   }
/*     */   
/*     */   private static PdxInstance maskColumnValue(PdxInstanceImpl pdxitem, Map<String, Map<String, String>> map)
/*     */   {
/* 335 */     Map attmap = PdxUtils.pdxToMap(pdxitem);
/* 336 */     maskColumnValue(attmap, map);
/* 337 */     return PdxBeanUtils.convert2Pdx(attmap);
/*     */   }
/*     */   
/*     */   private static void maskColumnValue(Map attmap, Map<String, Map<String, String>> map)
/*     */   {
/* 342 */     Iterator<String> it = map.keySet().iterator();
/* 343 */     while (it.hasNext()) {
/* 344 */       String columnName = (String)it.next();
/* 345 */       Map<String, String> detialmap = (Map)map.get(columnName);
/* 346 */       String type = (String)detialmap.get("MASK_TYPE");
/* 347 */       String maskLength = (String)detialmap.get("MASK_LENGTH");
/* 348 */       String starLength = (String)detialmap.get("STAR_LENGHT");
/* 349 */       String maskName = (String)detialmap.get("MASK_NAME");
/* 350 */       String content = (String)attmap.get(columnName);
/* 351 */       if ((StringUtils.isNotEmpty(content)) && (StringUtils.isNotEmpty(maskName))) {
/* 352 */         attmap.put(columnName, ColumnMaskUtils.stringMask(maskName, content, maskLength, type, starLength));
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\security\column\ColumnPrivilegeHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */