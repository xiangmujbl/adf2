/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.utils;
/*     */ 
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ 
/*     */ public final class CSVWriter
/*     */ {
/*     */   private static final String SEP = "\t";
/*     */   private static final String WRAP = "\r";
/*     */   private String[] cols;
/*     */   private String fileName;
/*  18 */   private OutputStreamWriter writer = null;
/*     */   
/*     */   public CSVWriter(String file) throws IOException {
/*  21 */     this(file, null);
/*     */   }
/*     */   
/*     */   public CSVWriter(String file, String[] cols) throws IOException {
/*  25 */     this(file, cols, false);
/*     */   }
/*     */   
/*     */   public CSVWriter(String file, String[] cols, boolean buildHeader) throws IOException {
/*  29 */     this.fileName = file;
/*  30 */     FileOutputStream fos = new FileOutputStream(file);
/*  31 */     this.writer = new OutputStreamWriter(fos);
/*  32 */     if (cols != null) {
/*  33 */       this.cols = cols;
/*  34 */       if (buildHeader)
/*  35 */         writeHeaders();
/*     */     }
/*     */   }
/*     */   
/*     */   private void writeHeaders() throws IOException {
/*  40 */     if ((this.cols != null) && (this.cols.length > 0)) {
/*  41 */       StringBuilder sb = new StringBuilder(256);
/*  42 */       int i = 0; for (int len = this.cols.length; i < len - 1; i++) {
/*  43 */         sb.append(this.cols[i]).append("\t");
/*     */       }
/*  45 */       sb.append(this.cols[(this.cols.length - 1)]);
/*  46 */       writeString(sb.toString());
/*  47 */       writeLn();
/*     */     }
/*     */   }
/*     */   
/*     */   public void write(Object data) throws IOException {
/*  52 */     if (data != null) {
/*  53 */       if ((data instanceof Map)) {
/*  54 */         writeMapData((Map)data);
/*     */       } else {
/*  56 */         writeString(String.valueOf(data));
/*     */       }
/*     */     }
/*  59 */     writeLn();
/*     */   }
/*     */   
/*     */   private void writeMapData(Map<?, ?> data) throws IOException {
/*  63 */     StringBuilder output = new StringBuilder(128);
/*  64 */     Object value = null;
/*  65 */     if (this.cols != null) {
/*  66 */       for (String col : this.cols) {
/*  67 */         value = data.get(col);
/*  68 */         if (value != null) {
/*  69 */           output.append(value);
/*     */         }
/*  71 */         output.append("\t");
/*     */       }
/*     */     } else {
/*  74 */       for (??? = data.entrySet().iterator(); ((Iterator)???).hasNext();) { Object entry = (Entry)((Iterator)???).next();
/*  75 */         value = ((Entry)entry).getValue();
/*  76 */         if (value != null) {
/*  77 */           output.append(value);
/*     */         }
/*  79 */         output.append("\t");
/*     */       }
/*     */     }
/*  82 */     writeString(output.substring(0, output.length() - 1).toString());
/*     */   }
/*     */   
/*     */   private void writeString(String data) throws IOException {
/*  86 */     if (StringUtils.isNotBlank(data))
/*  87 */       this.writer.write(data);
/*     */   }
/*     */   
/*     */   private void writeLn() throws IOException {
/*  91 */     this.writer.write("\r");
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/*  95 */     this.writer.close();
/*     */   }
/*     */   
/*     */   public String[] getCols() {
/*  99 */     return this.cols;
/*     */   }
/*     */   
/*     */   public void setCols(String[] cols) {
/* 103 */     this.cols = cols;
/*     */   }
/*     */   
/*     */   public String getFileName() {
/* 107 */     return this.fileName;
/*     */   }
/*     */   
/*     */   public void setFileName(String fileName) {
/* 111 */     this.fileName = fileName;
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\utils\CSVWriter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */