/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.auth.local;
/*     */ 
/*     */ import com.google.common.collect.Sets;
/*     */ import com.jnj.adf.grid.utils.SHAEncrypt;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.URL;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.dom4j.Document;
/*     */ import org.dom4j.DocumentException;
/*     */ import org.dom4j.Element;
/*     */ import org.dom4j.io.SAXReader;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LocalXmlFileAuthLoader
/*     */ {
/*  20 */   private static String[] localUserXmlFileNames = { "local-users.xml", "local-users-default.xml" };
/*     */   
/*     */ 
/*  23 */   private static Document localUserDocument = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static LocalAccount getLocalAccountByUserName(String username)
/*     */   {
/*  32 */     readLocalUserDocument();
/*  33 */     Element rootElement = localUserDocument.getRootElement();
/*  34 */     Element userNode = (Element)rootElement.selectSingleNode("//local-users/user[@username='" + username + "']");
/*  35 */     if (userNode != null) {
/*  36 */       LocalAccount la = new LocalAccount();
/*  37 */       la.setUsername(userNode.attributeValue("username"));
/*  38 */       la.setPassword(userNode.attributeValue("password"));
/*  39 */       String xmlRoles = userNode.attributeValue("roles");
/*  40 */       if (StringUtils.isNotEmpty(xmlRoles)) {
/*  41 */         String[] roles = StringUtils.split(xmlRoles, ",");
/*  42 */         la.setUserRoles(Sets.newHashSet(roles));
/*     */       }
/*  44 */       String xmlGroups = userNode.attributeValue("groups");
/*  45 */       if (StringUtils.isNotEmpty(xmlGroups)) {
/*  46 */         String[] adGroups = StringUtils.split(xmlGroups, ",");
/*  47 */         la.setAdGroups(Sets.newHashSet(adGroups));
/*     */       }
/*  49 */       return la;
/*     */     }
/*     */     
/*  52 */     return null;
/*     */   }
/*     */   
/*     */   private static void readLocalUserDocument()
/*     */   {
/*  57 */     SAXReader reader = new SAXReader();
/*  58 */     URL localUserFileUrl = null;
/*  59 */     for (String localUserXmlFileName : localUserXmlFileNames) {
/*  60 */       URL fileUrl = LocalXmlFileAuthLoader.class.getClassLoader().getResource(localUserXmlFileName);
/*  61 */       if (fileUrl != null) {
/*  62 */         localUserFileUrl = fileUrl;
/*  63 */         break;
/*     */       }
/*     */     }
/*  66 */     if (localUserFileUrl != null) {
/*  67 */       InputStream is = null;
/*     */       try {
/*  69 */         is = localUserFileUrl.openStream();
/*  70 */         localUserDocument = reader.read(is);
/*     */       }
/*     */       catch (DocumentException localDocumentException1) {}catch (IOException localIOException2) {}finally
/*     */       {
/*  74 */         if (is != null) {
/*     */           try {
/*  76 */             is.close();
/*     */           } catch (IOException e) {
/*  78 */             e.printStackTrace();
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static class LocalAccount
/*     */   {
/*     */     private String username;
/*     */     
/*     */     private String password;
/*     */     
/*     */     private Set<String> userRoles;
/*     */     
/*     */     private Set<String> adGroups;
/*     */     
/*     */     public String getUsername()
/*     */     {
/*  98 */       return this.username;
/*     */     }
/*     */     
/* 101 */     public void setUsername(String username) { this.username = username; }
/*     */     
/*     */     public String getPassword() {
/* 104 */       return this.password;
/*     */     }
/*     */     
/* 107 */     public void setPassword(String password) { this.password = password; }
/*     */     
/*     */     public Set<String> getUserRoles() {
/* 110 */       return this.userRoles;
/*     */     }
/*     */     
/* 113 */     public void setUserRoles(Set<String> userRoles) { this.userRoles = userRoles; }
/*     */     
/*     */     public Set<String> getAdGroups() {
/* 116 */       return this.adGroups;
/*     */     }
/*     */     
/* 119 */     public void setAdGroups(Set<String> adGroups) { this.adGroups = adGroups; }
/*     */     
/*     */     public void addGroups(String group)
/*     */     {
/* 123 */       if (this.adGroups == null) {
/* 124 */         this.adGroups = Sets.newHashSet();
/*     */       }
/* 126 */       this.adGroups.add(group);
/*     */     }
/*     */     
/* 129 */     public void addRoles(String role) { if (this.userRoles == null) {
/* 130 */         this.userRoles = Sets.newHashSet();
/*     */       }
/* 132 */       this.userRoles.add(role);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public boolean validateCreditial(String principle, String creditial)
/*     */     {
/* 142 */       assert (StringUtils.isNotEmpty(principle));
/* 143 */       assert (StringUtils.isNotEmpty(creditial));
/*     */       
/* 145 */       return (principle.equals(this.username)) && 
/* 146 */         (SHAEncrypt.encryptSHA(creditial).equals(this.password));
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\auth\local\LocalXmlFileAuthLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */