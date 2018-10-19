/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.kit;
/*    */ 
/*    */ import com.gemstone.gemfire.DataSerializable;
/*    */ import com.jnj.adf.grid.common.GridPathNameUtil;
/*    */ import com.jnj.adf.grid.master.domain.ActiveStatus;
/*    */ import com.jnj.adf.grid.master.domain.ValidStatus;
/*    */ import com.jnj.adf.grid.support.gemfire.DataSerializerExt;
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RegionInfo
/*    */   implements DataSerializable
/*    */ {
/*    */   private static final long serialVersionUID = -2985114065233051313L;
/*    */   private String gridId;
/*    */   private String regionPath;
/*    */   private ActiveStatus status;
/* 24 */   private ValidStatus valid = ValidStatus.VALID;
/*    */   
/*    */   private String sources;
/*    */   
/*    */   public RegionInfo() {}
/*    */   
/*    */   public RegionInfo(String gridId, String regionPath)
/*    */   {
/* 32 */     this.gridId = gridId;
/* 33 */     this.regionPath = GridPathNameUtil.escapePath(regionPath);
/*    */   }
/*    */   
/*    */   public String getRegionPath() {
/* 37 */     return this.regionPath;
/*    */   }
/*    */   
/*    */   public void setRegionPath(String regionPath) {
/* 41 */     this.regionPath = GridPathNameUtil.escapePath(regionPath);
/*    */   }
/*    */   
/*    */   public String getGridId() {
/* 45 */     return this.gridId;
/*    */   }
/*    */   
/*    */   public void setGridId(String gridId) {
/* 49 */     this.gridId = gridId;
/*    */   }
/*    */   
/*    */   public ActiveStatus getStatus() {
/* 53 */     return this.status;
/*    */   }
/*    */   
/*    */   public void setStatus(ActiveStatus status) {
/* 57 */     this.status = status;
/*    */   }
/*    */   
/*    */   public ValidStatus getValid() {
/* 61 */     return this.valid;
/*    */   }
/*    */   
/*    */   public void setValid(ValidStatus valid) {
/* 65 */     this.valid = valid;
/*    */   }
/*    */   
/*    */   public String getSources() {
/* 69 */     return this.sources;
/*    */   }
/*    */   
/*    */   public void setSources(String sources) {
/* 73 */     this.sources = sources;
/*    */   }
/*    */   
/*    */   public void toData(DataOutput out)
/*    */     throws IOException
/*    */   {
/* 79 */     DataSerializerExt.toData(getClass(), this, out);
/*    */   }
/*    */   
/*    */   public void fromData(DataInput in)
/*    */     throws IOException, ClassNotFoundException
/*    */   {
/* 85 */     DataSerializerExt.fromData(getClass(), this, in);
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\kit\RegionInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */