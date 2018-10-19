/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.data;
/*     */ 
/*     */ import com.gemstone.gemfire.DataSerializable;
/*     */ import com.jnj.adf.grid.support.gemfire.DataSerializerExt;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.io.RandomAccessFile;
/*     */ import java.util.Arrays;
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
/*     */ public class RegionDefine_v3
/*     */   implements DataSerializable
/*     */ {
/*     */   private static final long serialVersionUID = 6284650697328341029L;
/*     */   private String fullPath;
/*     */   private boolean enabledTemporal;
/*     */   private String temporalVTField;
/*     */   private String temporalVFField;
/*     */   private String[] temporalAttributes;
/*     */   private String[] resolverColumns;
/*     */   private boolean enabledLucene;
/*     */   private boolean enabledSolr;
/*     */   private boolean enableLogicKey;
/*     */   private String namespace;
/*     */   
/*     */   public RegionDefine_v3()
/*     */   {
/*  53 */     this.enabledTemporal = false;
/*  54 */     this.enabledLucene = true;
/*  55 */     this.enabledSolr = false;
/*  56 */     this.enableLogicKey = false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public RegionDefine_v3(boolean enabledTemporal, boolean enabledLucene)
/*     */   {
/*  63 */     this.enabledTemporal = enabledTemporal;
/*  64 */     this.enabledLucene = enabledLucene;
/*     */   }
/*     */   
/*     */   public boolean isEnabledTemporal()
/*     */   {
/*  69 */     return this.enabledTemporal;
/*     */   }
/*     */   
/*     */   public boolean isEnabledLucene()
/*     */   {
/*  74 */     return this.enabledLucene;
/*     */   }
/*     */   
/*     */   public String[] getResolverColumns()
/*     */   {
/*  79 */     return this.resolverColumns;
/*     */   }
/*     */   
/*     */   public void setResolverColumns(String[] resolverColumns)
/*     */   {
/*  84 */     this.resolverColumns = resolverColumns;
/*     */   }
/*     */   
/*     */   public boolean isEnabledSolr()
/*     */   {
/*  89 */     return this.enabledSolr;
/*     */   }
/*     */   
/*     */   public void setEnabledSolr(boolean enabledSolr)
/*     */   {
/*  94 */     this.enabledSolr = enabledSolr;
/*     */   }
/*     */   
/*     */   public String getFullPath()
/*     */   {
/*  99 */     return this.fullPath;
/*     */   }
/*     */   
/*     */   public void setFullPath(String fullPath)
/*     */   {
/* 104 */     this.fullPath = fullPath;
/*     */   }
/*     */   
/*     */   public boolean isEnableLogicKey()
/*     */   {
/* 109 */     return this.enableLogicKey;
/*     */   }
/*     */   
/*     */   public void setEnableLogicKey(boolean enableLogicKey)
/*     */   {
/* 114 */     this.enableLogicKey = enableLogicKey;
/*     */   }
/*     */   
/*     */   public String getTemporalVTField()
/*     */   {
/* 119 */     return this.temporalVTField;
/*     */   }
/*     */   
/*     */   public void setTemporalVTField(String temporalVTField)
/*     */   {
/* 124 */     this.temporalVTField = temporalVTField;
/*     */   }
/*     */   
/*     */   public String getTemporalVFField()
/*     */   {
/* 129 */     return this.temporalVFField;
/*     */   }
/*     */   
/*     */   public void setTemporalVFField(String temporalVFField)
/*     */   {
/* 134 */     this.temporalVFField = temporalVFField;
/*     */   }
/*     */   
/*     */   public String[] getTemporalAttributes()
/*     */   {
/* 139 */     return this.temporalAttributes;
/*     */   }
/*     */   
/*     */   public void setTemporalAttributes(String[] temporalAttributes)
/*     */   {
/* 144 */     this.temporalAttributes = temporalAttributes;
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 150 */     return 
/*     */     
/* 152 */       this.fullPath + ", temporal=" + this.enabledTemporal + ", resolver=" + Arrays.toString(this.resolverColumns) + ", lucene=" + this.enabledLucene + ", logicKey=" + this.enableLogicKey + ", temporalVFField=" + this.temporalVFField + ", temporalVTField=" + this.temporalVTField + ", temporalAttributes=" + Arrays.toString(this.temporalAttributes);
/*     */   }
/*     */   
/*     */   public static void main(String[] args) throws Exception
/*     */   {
/* 157 */     RegionDefine_v3 def = new RegionDefine_v3();
/* 158 */     def.setEnabledSolr(true);
/* 159 */     def.enabledTemporal = true;
/* 160 */     RandomAccessFile ra = new RandomAccessFile("c:/tmp/aa1", "rw");
/* 161 */     def.toData(ra);
/* 162 */     ra.close();
/*     */     
/* 164 */     ra = new RandomAccessFile("c:/tmp/aa1", "rw");
/* 165 */     RegionDefine_v3 def2 = new RegionDefine_v3();
/* 166 */     def2.fromData(ra);
/* 167 */     System.out.println(def2);
/*     */   }
/*     */   
/*     */   public void toData(DataOutput out)
/*     */     throws IOException
/*     */   {
/* 173 */     DataSerializerExt.toData(getClass(), this, out);
/*     */   }
/*     */   
/*     */   public void fromData(DataInput in)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/* 179 */     DataSerializerExt.fromData(getClass(), this, in);
/*     */   }
/*     */   
/*     */   public void setEnabledTemporal(boolean enabledTemporal)
/*     */   {
/* 184 */     this.enabledTemporal = enabledTemporal;
/*     */   }
/*     */   
/*     */   public void setEnabledLucene(boolean enabledLucene)
/*     */   {
/* 189 */     this.enabledLucene = enabledLucene;
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\data\RegionDefine_v3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */