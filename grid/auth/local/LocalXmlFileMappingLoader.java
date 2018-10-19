/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.auth.local;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import com.google.common.collect.Sets;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.net.URL;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import org.dom4j.Document;
/*    */ import org.dom4j.DocumentException;
/*    */ import org.dom4j.Element;
/*    */ import org.dom4j.io.SAXReader;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LocalXmlFileMappingLoader
/*    */ {
/* 23 */   private static String[] localRoleMappingXmlFileNames = { "role-mapping.xml", "role-mapping-default.xml" };
/*    */   
/* 25 */   private static Document roleMappingDocument = null;
/*    */   
/* 27 */   private static Map<String, Set<String>> groupMapping = Maps.newConcurrentMap();
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public static Set<String> getLocalRolesByUserGroup(String groupname)
/*    */   {
/* 34 */     if (groupMapping.containsKey(groupname)) {
/* 35 */       return (Set)groupMapping.get(groupname);
/*    */     }
/* 37 */     Set<String> resultRoles = Sets.newHashSet();
/* 38 */     readLocalMappingDocument();
/* 39 */     Element rootElement = roleMappingDocument.getRootElement();
/* 40 */     List<Element> groupNodes = rootElement.selectNodes("//config-mapping/roles/role/group[@name='" + groupname + "']");
/* 41 */     if ((groupNodes != null) && (!groupNodes.isEmpty())) {
/* 42 */       for (Element groupNode : groupNodes) {
/* 43 */         Element parElement = groupNode.getParent();
/* 44 */         if ("role".equalsIgnoreCase(parElement.getName())) {
/* 45 */           resultRoles.add(parElement.attributeValue("name"));
/*    */         }
/*    */       }
/*    */     }
/*    */     
/* 50 */     groupMapping.put(groupname, resultRoles);
/*    */     
/* 52 */     return resultRoles;
/*    */   }
/*    */   
/*    */   private static void readLocalMappingDocument()
/*    */   {
/* 57 */     SAXReader reader = new SAXReader();
/* 58 */     URL localMappingFileUrl = null;
/* 59 */     for (String mappingXmlFileName : localRoleMappingXmlFileNames) {
/* 60 */       URL fileUrl = LocalXmlFileMappingLoader.class.getClassLoader().getResource(mappingXmlFileName);
/* 61 */       if (fileUrl != null) {
/* 62 */         localMappingFileUrl = fileUrl;
/* 63 */         break;
/*    */       }
/*    */     }
/* 66 */     if (localMappingFileUrl != null) {
/* 67 */       InputStream is = null;
/*    */       try {
/* 69 */         is = localMappingFileUrl.openStream();
/* 70 */         roleMappingDocument = reader.read(is);
/*    */       }
/*    */       catch (DocumentException localDocumentException1) {}catch (IOException localIOException2) {}finally
/*    */       {
/* 74 */         if (is != null) {
/*    */           try {
/* 76 */             is.close();
/*    */           } catch (IOException e) {
/* 78 */             e.printStackTrace();
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\auth\local\LocalXmlFileMappingLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */