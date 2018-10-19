/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.common;
/*     */ 
/*     */ import com.fasterxml.jackson.core.JsonParser.Feature;
/*     */ import com.fasterxml.jackson.databind.JsonNode;
/*     */ import com.fasterxml.jackson.databind.ObjectMapper;
/*     */ import com.gemstone.gemfire.pdx.JSONFormatterException;
/*     */ import com.gemstone.gemfire.pdx.PdxInstance;
/*     */ import com.gemstone.gemfire.pdx.internal.ExtraFieldItem;
/*     */ import com.gemstone.gemfire.pdx.internal.PdxWriterImplExt;
/*     */ import com.gemstone.gemfire.pdx.internal.json.PdxToJSON;
/*     */ import com.jnj.adf.grid.utils.LogUtil;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
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
/*     */ public class PdxFormatter
/*     */ {
/*     */   public static PdxInstance fromJSON(String jsonString)
/*     */   {
/*     */     try
/*     */     {
/*  60 */       ObjectMapper mapper = new ObjectMapper();
/*  61 */       mapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
/*  62 */       mapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
/*  63 */       JsonNode node = mapper.readTree(jsonString);
/*  64 */       PdxWriterImplExt writer = PdxWriterImplExt.create();
/*  65 */       return new PdxFormatter().processObject(writer, node);
/*     */ 
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*  70 */       LogUtil.getCoreLog().error(e);
/*  71 */       throw new ADFException("Could not parse JSON document", e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static PdxInstance fromObject(Object value)
/*     */   {
/*     */     try
/*     */     {
/*  86 */       return PdxWriterImplExt.objectToPdx(value);
/*     */ 
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*  91 */       LogUtil.getCoreLog().error(e);
/*  92 */       throw new ADFException("Could not convert " + value + " to pdx", e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static PdxInstance fromJSON(byte[] jsonByteArray)
/*     */   {
/*     */     try
/*     */     {
/* 107 */       ObjectMapper mapper = new ObjectMapper();
/* 108 */       mapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
/* 109 */       mapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
/* 110 */       JsonNode node = mapper.readTree(jsonByteArray);
/* 111 */       PdxWriterImplExt writer = PdxWriterImplExt.create();
/* 112 */       return new PdxFormatter().processObject(writer, node);
/*     */ 
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 117 */       LogUtil.getCoreLog().error(e);
/* 118 */       throw new ADFException("Could not parse JSON document", e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String toJSON(PdxInstance pdxInstance)
/*     */   {
/*     */     try
/*     */     {
/* 133 */       PdxToJSON pj = new PdxToJSON(pdxInstance);
/* 134 */       return pj.getJSON();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 138 */       throw new JSONFormatterException("Could not create JSON document from PdxInstance ", e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static byte[] toJSONByteArray(PdxInstance pdxInstance)
/*     */   {
/*     */     try
/*     */     {
/* 153 */       PdxToJSON pj = new PdxToJSON(pdxInstance);
/* 154 */       return pj.getJSONByteArray();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 158 */       throw new JSONFormatterException("Could not create JSON document from PdxInstance ", e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private void processPrimitive(PdxWriterImplExt writer, String fieldName, JsonNode node)
/*     */   {
/* 165 */     ExtraFieldItem item = getPrimitiveValue(node);
/* 166 */     writer.addField(fieldName, item.getFieldValue(), item.getFieldType());
/*     */   }
/*     */   
/*     */   private <CT, VT extends CT> ExtraFieldItem<CT, VT> getPrimitiveValue(JsonNode node)
/*     */   {
/* 171 */     ExtraFieldItem<CT, VT> item = null;
/* 172 */     if (node.isBigDecimal()) {
/* 173 */       item = new ExtraFieldItem(node.decimalValue(), BigDecimal.class);
/* 174 */     } else if (node.isInt()) {
/* 175 */       item = new ExtraFieldItem(Integer.valueOf(node.intValue()), Integer.TYPE);
/* 176 */     } else if (node.isShort()) {
/* 177 */       item = new ExtraFieldItem(Short.valueOf(node.shortValue()), Short.TYPE);
/* 178 */     } else if (node.isLong()) {
/* 179 */       item = new ExtraFieldItem(Long.valueOf(node.longValue()), Long.TYPE);
/* 180 */     } else if (node.isFloat()) {
/* 181 */       item = new ExtraFieldItem(Float.valueOf(node.floatValue()), Float.TYPE);
/* 182 */     } else if (node.isDouble()) {
/* 183 */       item = new ExtraFieldItem(Double.valueOf(node.doubleValue()), Double.TYPE);
/* 184 */     } else if (node.isBoolean()) {
/* 185 */       item = new ExtraFieldItem(Boolean.valueOf(node.booleanValue()), Boolean.TYPE);
/* 186 */     } else if (node.isTextual()) {
/* 187 */       item = new ExtraFieldItem(node.textValue(), String.class);
/* 188 */     } else if (node.isNull())
/*     */     {
/* 190 */       item = new ExtraFieldItem(null, Object.class);
/*     */     }
/*     */     else
/*     */     {
/* 194 */       LogUtil.getCoreLog().error("Parse json error,type:{},json:{}", new Object[] { node.getNodeType(), node.toString() });
/* 195 */       item = new ExtraFieldItem(node.textValue(), String.class);
/*     */     }
/* 197 */     return item;
/*     */   }
/*     */   
/*     */   private PdxInstance processObject(PdxWriterImplExt writer, JsonNode node)
/*     */   {
/* 202 */     Map<String, JsonNode> sortedNodeFields = sortNodeFields(node);
/* 203 */     Iterator<Entry<String, JsonNode>> it = sortedNodeFields.entrySet().iterator();
/* 204 */     while (it.hasNext())
/*     */     {
/* 206 */       Entry<String, JsonNode> e = (Entry)it.next();
/* 207 */       String fieldName = (String)e.getKey();
/* 208 */       JsonNode fieldValue = (JsonNode)e.getValue();
/* 209 */       if (fieldValue.isArray())
/*     */       {
/* 211 */         List list = processArray(writer, fieldValue);
/* 212 */         writer.addField(fieldName, list, List.class);
/*     */       }
/* 214 */       else if (fieldValue.isObject())
/*     */       {
/* 216 */         PdxFormatter sub = new PdxFormatter();
/* 217 */         PdxInstance pdx = sub.processObject(PdxWriterImplExt.create(), fieldValue);
/* 218 */         writer.addField(fieldName, pdx, PdxInstance.class);
/*     */       }
/*     */       else
/*     */       {
/* 222 */         processPrimitive(writer, fieldName, fieldValue);
/*     */       }
/*     */     }
/* 225 */     return writer.makePdxInstance();
/*     */   }
/*     */   
/*     */   private List processArray(PdxWriterImplExt writer, JsonNode node)
/*     */   {
/* 230 */     List list = new ArrayList();
/* 231 */     Iterator<JsonNode> it = node.elements();
/* 232 */     while (it.hasNext())
/*     */     {
/* 234 */       JsonNode child = (JsonNode)it.next();
/* 235 */       if (child.isArray())
/*     */       {
/* 237 */         List subList = processArray(writer, child);
/* 238 */         list.add(subList);
/*     */       }
/* 240 */       else if (child.isObject())
/*     */       {
/* 242 */         PdxFormatter sub = new PdxFormatter();
/* 243 */         PdxInstance pdx = sub.processObject(PdxWriterImplExt.create(), child);
/* 244 */         list.add(pdx);
/*     */       }
/*     */       else
/*     */       {
/* 248 */         ExtraFieldItem item = getPrimitiveValue(child);
/* 249 */         list.add(item.getFieldValue());
/*     */       }
/*     */     }
/*     */     
/* 253 */     return list;
/*     */   }
/*     */   
/*     */   private Map<String, JsonNode> sortNodeFields(JsonNode node)
/*     */   {
/* 258 */     Iterator<Entry<String, JsonNode>> it = node.fields();
/* 259 */     Map<String, JsonNode> sortedNodes = new TreeMap();
/* 260 */     while (it.hasNext())
/*     */     {
/* 262 */       Entry<String, JsonNode> e = (Entry)it.next();
/* 263 */       sortedNodes.put(e.getKey(), e.getValue());
/*     */     }
/* 265 */     return sortedNodes;
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\common\PdxFormatter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */