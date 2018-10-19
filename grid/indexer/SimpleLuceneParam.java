/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.indexer;
/*     */ 
/*     */ import com.gemstone.gemfire.DataSerializable;
/*     */ import com.jnj.adf.grid.support.gemfire.DataSerializerExt;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
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
/*     */ public class SimpleLuceneParam
/*     */   implements DataSerializable, Cloneable
/*     */ {
/*     */   private static final long serialVersionUID = -7830708278322511679L;
/*     */   private String query;
/*     */   private String filter;
/*     */   private String orderBys;
/*     */   private int limit;
/*     */   private boolean isSpatialOp;
/*     */   private double latitude;
/*     */   private double longitude;
/*     */   private double distance;
/*     */   private int sortOrder;
/*     */   private boolean isTemporalOp;
/*     */   private long validAt;
/*     */   private long asOf;
/*     */   private long validAtStart;
/*     */   private long validAtEnd;
/*     */   private long asOfStart;
/*     */   private long asOfEnd;
/*     */   private boolean validAtPointType;
/*     */   private int pageSize;
/*     */   private int pageNo;
/*     */   private boolean withoutCache;
/*     */   private String path;
/*     */   private boolean withJoin;
/*     */   private SimpleLuceneParam joinParts;
/*     */   private String joinCondition;
/*     */   
/*     */   public SimpleLuceneParam() {}
/*     */   
/*     */   public SimpleLuceneParam(String query, String filter, String orderBys)
/*     */   {
/*  54 */     this.query = query;
/*  55 */     this.filter = filter;
/*  56 */     this.orderBys = orderBys;
/*     */   }
/*     */   
/*     */   public void fromData(DataInput in)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/*  62 */     DataSerializerExt.fromData(getClass(), this, in);
/*     */   }
/*     */   
/*     */   public void toData(DataOutput out)
/*     */     throws IOException
/*     */   {
/*  68 */     DataSerializerExt.toData(getClass(), this, out);
/*     */   }
/*     */   
/*     */   public double getLatitude()
/*     */   {
/*  73 */     return this.latitude;
/*     */   }
/*     */   
/*     */   public void setLatitude(double latitude)
/*     */   {
/*  78 */     this.latitude = latitude;
/*     */   }
/*     */   
/*     */   public double getLongitude()
/*     */   {
/*  83 */     return this.longitude;
/*     */   }
/*     */   
/*     */   public void setLongitude(double longitude)
/*     */   {
/*  88 */     this.longitude = longitude;
/*     */   }
/*     */   
/*     */   public double getDistance()
/*     */   {
/*  93 */     return this.distance;
/*     */   }
/*     */   
/*     */   public void setDistance(double distance)
/*     */   {
/*  98 */     this.distance = distance;
/*     */   }
/*     */   
/*     */   public String getQuery()
/*     */   {
/* 103 */     return this.query;
/*     */   }
/*     */   
/*     */   public void setQuery(String query)
/*     */   {
/* 108 */     this.query = query;
/*     */   }
/*     */   
/*     */   public String getFilter()
/*     */   {
/* 113 */     return this.filter;
/*     */   }
/*     */   
/*     */   public void setFilter(String filter)
/*     */   {
/* 118 */     this.filter = filter;
/*     */   }
/*     */   
/*     */   public String getOrderBys()
/*     */   {
/* 123 */     return this.orderBys;
/*     */   }
/*     */   
/*     */   public void setOrderBys(String orderBys)
/*     */   {
/* 128 */     this.orderBys = orderBys;
/*     */   }
/*     */   
/*     */   public int getLimit()
/*     */   {
/* 133 */     return this.limit;
/*     */   }
/*     */   
/*     */   public void setLimit(int limit)
/*     */   {
/* 138 */     this.limit = limit;
/*     */   }
/*     */   
/*     */   public boolean isSpatialOp()
/*     */   {
/* 143 */     return this.isSpatialOp;
/*     */   }
/*     */   
/*     */   public void setSpatialOp(boolean isSpatialOp)
/*     */   {
/* 148 */     this.isSpatialOp = isSpatialOp;
/*     */   }
/*     */   
/*     */   public boolean isTemporalOp()
/*     */   {
/* 153 */     return this.isTemporalOp;
/*     */   }
/*     */   
/*     */   public void setTemporalOp(boolean isTemporalOp)
/*     */   {
/* 158 */     this.isTemporalOp = isTemporalOp;
/*     */   }
/*     */   
/*     */   public long getValidAt()
/*     */   {
/* 163 */     return this.validAt;
/*     */   }
/*     */   
/*     */   public void setValidAt(long validAt)
/*     */   {
/* 168 */     this.validAt = validAt;
/*     */   }
/*     */   
/*     */   public long getAsOf()
/*     */   {
/* 173 */     return this.asOf;
/*     */   }
/*     */   
/*     */   public void setAsOf(long asOf)
/*     */   {
/* 178 */     this.asOf = asOf;
/*     */   }
/*     */   
/*     */   public long getValidAtStart()
/*     */   {
/* 183 */     return this.validAtStart;
/*     */   }
/*     */   
/*     */   public void setValidAtStart(long validAtStart)
/*     */   {
/* 188 */     this.validAtStart = validAtStart;
/*     */   }
/*     */   
/*     */   public long getValidAtEnd()
/*     */   {
/* 193 */     return this.validAtEnd;
/*     */   }
/*     */   
/*     */   public void setValidAtEnd(long validAtEnd)
/*     */   {
/* 198 */     this.validAtEnd = validAtEnd;
/*     */   }
/*     */   
/*     */   public long getAsOfStart()
/*     */   {
/* 203 */     return this.asOfStart;
/*     */   }
/*     */   
/*     */   public void setAsOfStart(long asOfStart)
/*     */   {
/* 208 */     this.asOfStart = asOfStart;
/*     */   }
/*     */   
/*     */   public long getAsOfEnd()
/*     */   {
/* 213 */     return this.asOfEnd;
/*     */   }
/*     */   
/*     */   public void setAsOfEnd(long asOfEnd)
/*     */   {
/* 218 */     this.asOfEnd = asOfEnd;
/*     */   }
/*     */   
/*     */   public boolean isValidAtPointType()
/*     */   {
/* 223 */     return this.validAtPointType;
/*     */   }
/*     */   
/*     */   public void setValidAtPointType(boolean validAtPointType)
/*     */   {
/* 228 */     this.validAtPointType = validAtPointType;
/*     */   }
/*     */   
/*     */   public int getSortOrder() {
/* 232 */     return this.sortOrder;
/*     */   }
/*     */   
/*     */   public void setSortOrder(int sortOrder) {
/* 236 */     this.sortOrder = sortOrder;
/*     */   }
/*     */   
/*     */   public int getPageSize()
/*     */   {
/* 241 */     return this.pageSize;
/*     */   }
/*     */   
/*     */   public void setPageSize(int pageSize)
/*     */   {
/* 246 */     this.pageSize = pageSize;
/*     */   }
/*     */   
/*     */   public int getPageNo()
/*     */   {
/* 251 */     return this.pageNo;
/*     */   }
/*     */   
/*     */   public void setPageNo(int pageNo)
/*     */   {
/* 256 */     this.pageNo = pageNo;
/*     */   }
/*     */   
/*     */   public boolean isWithoutCache()
/*     */   {
/* 261 */     return this.withoutCache;
/*     */   }
/*     */   
/*     */   public void setWithoutCache(boolean withoutCache)
/*     */   {
/* 266 */     this.withoutCache = withoutCache;
/*     */   }
/*     */   
/*     */   public String getPath()
/*     */   {
/* 271 */     return this.path;
/*     */   }
/*     */   
/*     */   public void setPath(String path)
/*     */   {
/* 276 */     this.path = path;
/*     */   }
/*     */   
/*     */   public boolean isWithJoin() {
/* 280 */     return this.withJoin;
/*     */   }
/*     */   
/*     */   public void setWithJoin(boolean withJoin) {
/* 284 */     this.withJoin = withJoin;
/*     */   }
/*     */   
/*     */   public SimpleLuceneParam getJoinParts() {
/* 288 */     return this.joinParts;
/*     */   }
/*     */   
/*     */   public void setJoinParts(SimpleLuceneParam joinParts) {
/* 292 */     this.joinParts = joinParts;
/*     */   }
/*     */   
/*     */   public String getJoinCondition() {
/* 296 */     return this.joinCondition;
/*     */   }
/*     */   
/*     */   public void setJoinCondition(String joinCondition) {
/* 300 */     this.joinCondition = joinCondition;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 304 */     SimpleLuceneParam obj = new SimpleLuceneParam();
/* 305 */     obj.asOf = this.asOf;
/* 306 */     obj.asOfEnd = this.asOfEnd;
/* 307 */     obj.asOfStart = this.asOfStart;
/* 308 */     obj.distance = this.distance;
/* 309 */     obj.filter = this.filter;
/* 310 */     obj.isSpatialOp = this.isSpatialOp;
/* 311 */     obj.isTemporalOp = this.isTemporalOp;
/* 312 */     obj.joinCondition = this.joinCondition;
/* 313 */     obj.joinParts = (this.joinParts == null ? null : (SimpleLuceneParam)this.joinParts.clone());
/* 314 */     obj.latitude = this.latitude;
/* 315 */     obj.limit = this.limit;
/* 316 */     obj.longitude = this.longitude;
/* 317 */     obj.orderBys = this.orderBys;
/* 318 */     obj.pageNo = this.pageNo;
/* 319 */     obj.pageSize = this.pageSize;
/* 320 */     obj.path = this.path;
/* 321 */     obj.query = this.query;
/* 322 */     obj.sortOrder = this.sortOrder;
/* 323 */     obj.validAt = this.validAt;
/* 324 */     obj.validAtEnd = this.validAtEnd;
/* 325 */     obj.validAtPointType = this.validAtPointType;
/* 326 */     obj.validAtStart = this.validAtStart;
/* 327 */     obj.withJoin = this.withJoin;
/* 328 */     obj.withoutCache = this.withoutCache;
/* 329 */     return obj;
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\indexer\SimpleLuceneParam.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */