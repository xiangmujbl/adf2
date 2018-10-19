/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.query;
/*     */ 
/*     */ import com.gemstone.gemfire.DataSerializable;
/*     */ import com.gemstone.gemfire.DataSerializer;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
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
/*     */ public class ServerPageResults
/*     */   implements DataSerializable
/*     */ {
/*     */   private static final long serialVersionUID = -1472516500047811137L;
/*     */   private long totalCount;
/*     */   private ArrayList<String> paths;
/*     */   private ArrayList<Object> keys;
/*     */   private HashMap<Object, Object[]> items;
/*     */   
/*     */   public ServerPageResults()
/*     */   {
/*  48 */     this.paths = new ArrayList();
/*  49 */     this.keys = new ArrayList();
/*  50 */     this.items = new HashMap();
/*     */   }
/*     */   
/*     */   public void addResult(ServerPageResults pr)
/*     */   {
/*  55 */     if (pr != null)
/*     */     {
/*  57 */       if (this.paths.isEmpty()) {
/*  58 */         this.paths.addAll(pr.getPaths());
/*     */       }
/*  60 */       this.keys.addAll(pr.getKeys());
/*  61 */       this.items.putAll(pr.getItems());
/*     */       
/*  63 */       if (this.totalCount == 0L) {
/*  64 */         this.totalCount = pr.totalCount;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void addKey(Object key, Object[] sortValues) {
/*  70 */     this.keys.add(key);
/*  71 */     this.items.put(key, sortValues);
/*     */   }
/*     */   
/*     */   public long getTotalCount()
/*     */   {
/*  76 */     return this.totalCount;
/*     */   }
/*     */   
/*     */   public void setTotalCount(long totalCount)
/*     */   {
/*  81 */     this.totalCount = totalCount;
/*     */   }
/*     */   
/*     */   public List<Object> getKeys()
/*     */   {
/*  86 */     return this.keys;
/*     */   }
/*     */   
/*     */   public void setKeys(ArrayList<Object> keys)
/*     */   {
/*  91 */     this.keys = keys;
/*     */   }
/*     */   
/*     */   public Map<Object, Object[]> getItems() {
/*  95 */     return this.items;
/*     */   }
/*     */   
/*     */   public void setItems(HashMap<Object, Object[]> items) {
/*  99 */     this.items = items;
/*     */   }
/*     */   
/*     */   public void toData(DataOutput out)
/*     */     throws IOException
/*     */   {
/* 105 */     DataSerializer.writeLong(Long.valueOf(this.totalCount), out);
/* 106 */     DataSerializer.writeArrayList(this.keys, out);
/* 107 */     DataSerializer.writeArrayList(this.paths, out);
/* 108 */     DataSerializer.writeHashMap(this.items, out);
/*     */   }
/*     */   
/*     */   public void fromData(DataInput in)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/* 114 */     this.totalCount = DataSerializer.readPrimitiveLong(in);
/* 115 */     this.keys = DataSerializer.readArrayList(in);
/* 116 */     this.paths = DataSerializer.readArrayList(in);
/* 117 */     this.items = DataSerializer.readHashMap(in);
/*     */   }
/*     */   
/*     */   public ArrayList<String> getPaths() {
/* 121 */     return this.paths;
/*     */   }
/*     */   
/*     */   public void setPaths(ArrayList<String> paths) {
/* 125 */     this.paths = paths;
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\query\ServerPageResults.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */