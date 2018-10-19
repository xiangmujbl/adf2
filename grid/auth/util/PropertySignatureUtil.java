/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.auth.util;
/*    */ 
/*    */ import com.jnj.adf.grid.utils.JsonUtils;
/*    */ import java.util.Collections;
/*    */ import java.util.Comparator;
/*    */ import java.util.Enumeration;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.Properties;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PropertySignatureUtil
/*    */ {
/*    */   public static String signature(Properties p)
/*    */   {
/* 21 */     Map<String, String> dataMap = getMapData(p);
/* 22 */     byte[] buffer = JsonUtils.objectToBytes(dataMap);
/* 23 */     String cryptoString = Crypto.sign(buffer);
/*    */     
/* 25 */     return cryptoString;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   private static Map<String, String> getMapData(Properties pro)
/*    */   {
/* 35 */     Enumeration<String> proNames = pro.propertyNames();
/* 36 */     List<String> proNameList = Collections.list(proNames);
/* 37 */     Collections.sort(proNameList, new Comparator()
/*    */     {
/*    */       public int compare(String o1, String o2) {
/* 40 */         return o1.hashCode() - o2.hashCode();
/*    */       }
/* 42 */     });
/* 43 */     Map<String, String> resultMap = new HashMap();
/* 44 */     for (String proName : proNameList) {
/* 45 */       String proValue = pro.getProperty(proName);
/* 46 */       if (proValue != null) {
/* 47 */         resultMap.put(proName, proValue);
/*    */       }
/*    */     }
/* 50 */     return resultMap;
/*    */   }
/*    */   
/*    */   public static void main(String[] args) {}
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\auth\util\PropertySignatureUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */