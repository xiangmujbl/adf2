/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.data.temporal;
/*     */ 
/*     */ import com.gemstone.gemfire.cache.Operation;
/*     */ import com.gemstone.gemfire.cache.Region;
/*     */ import com.gemstone.gemfire.pdx.PdxInstance;
/*     */ import com.google.common.base.Predicate;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Sets;
/*     */ import com.jnj.adf.grid.common.PdxUtils;
/*     */ import com.jnj.adf.grid.data.temporal.utils.IntervalUtils;
/*     */ import com.jnj.adf.grid.data.temporal.utils.TemporalKeyUtils;
/*     */ import com.jnj.adf.grid.data.temporal.utils.TemporalUtils;
/*     */ import com.jnj.adf.grid.data.temporal.utils.TemporalValueUtils;
/*     */ import com.jnj.adf.grid.manager.RegionHelper;
/*     */ import com.jnj.adf.grid.utils.JsonUtils;
/*     */ import com.jnj.adf.grid.utils.LogUtil;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentSkipListSet;
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
/*     */ public class TemporalProcessor
/*     */ {
/*     */   protected String identityKey;
/*     */   private String path;
/*     */   protected ConcurrentSkipListSet<TemporalKey> sortedByValid;
/*     */   
/*     */   public TemporalProcessor(String identityKey)
/*     */   {
/*  63 */     this.identityKey = identityKey;
/*  64 */     this.sortedByValid = TemporalKeyUtils.createConcurrentSetWithComparator();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean contains(TemporalKey tkey)
/*     */   {
/*  75 */     return this.sortedByValid.contains(tkey);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int size()
/*     */   {
/*  84 */     return this.sortedByValid.size();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TemporalEntry getNowRelativeEntry()
/*     */   {
/*  93 */     long validAt = TemporalUtils.now(true);
/*  94 */     long asOf = TemporalUtils.now(false);
/*  95 */     return getAsOf(validAt, asOf);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TemporalEntry getValidAt(long validAtTime)
/*     */   {
/* 104 */     long asOf = TemporalUtils.now(false);
/* 105 */     return getAsOf(validAtTime, asOf);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TemporalEntry getAsOf(long validAtTime, long asOfTime)
/*     */   {
/* 115 */     if ((validAtTime == -1L) && (asOfTime == -1L)) {
/* 116 */       return getNowRelativeEntry();
/*     */     }
/*     */     
/* 119 */     TemporalKey foundKey = getOne(validAtTime, asOfTime);
/* 120 */     if (foundKey == null)
/* 121 */       return null;
/* 122 */     TemporalRKey rkey = TemporalKeyUtils.createTemporalRKey(foundKey.getUuid(), this.identityKey);
/* 123 */     TemporalValue value = getRegionValue(rkey);
/* 124 */     if (value == null)
/* 125 */       return null;
/* 126 */     PdxInstance originValue = value.getOriginValue();
/* 127 */     if (originValue == null) {
/* 128 */       LogUtil.getCoreLog().debug("Found key:{} , it's a delete operation's record, value is null!", new Object[] { foundKey });
/*     */     } else {
/* 130 */       LogUtil.getCoreLog().debug("Found key:{} ,value:{}", new Object[] { foundKey, PdxUtils.toJson(originValue) });
/*     */     }
/* 132 */     TemporalEntry entry = new TemporalEntry(rkey, value);
/* 133 */     return entry;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private TemporalKey getOne(long validAtTime, long asOfTime)
/*     */   {
/* 144 */     Collection<TemporalKey> coll = get(validAtTime, asOfTime);
/* 145 */     if (coll.isEmpty()) {
/* 146 */       return null;
/*     */     }
/*     */     
/* 149 */     return (TemporalKey)coll.iterator().next();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private Set<TemporalKey> get(final long validAtTime, long asOfTime)
/*     */   {
/* 157 */     Set<TemporalKey> filterCollection = Sets.filter(this.sortedByValid, new Predicate()
/*     */     {
/*     */       public boolean apply(TemporalKey input)
/*     */       {
/* 161 */         TemporalKey keyObject = input;
/* 162 */         return (IntervalUtils.contains(keyObject.getValidFrom(), keyObject.getValidTo(), validAtTime)) && 
/* 163 */           (IntervalUtils.contains(keyObject.getWrittenTime(), keyObject.getWrittenTimeEnd(), this.val$asOfTime));
/*     */       }
/* 165 */     });
/* 166 */     return filterCollection;
/*     */   }
/*     */   
/*     */   private TemporalValue getRegionValue(TemporalRKey rkey) {
/* 170 */     Region<TemporalRKey, PdxInstance> r = RegionHelper.getRegion(this.path);
/* 171 */     if (r == null)
/* 172 */       return null;
/* 173 */     PdxInstance regionValue = (PdxInstance)r.get(rkey);
/* 174 */     return TemporalValueUtils.pdxToTValue(regionValue);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void dump()
/*     */   {
/* 183 */     synchronized (this) {
/* 184 */       LogUtil.getCoreLog().debug("");
/* 185 */       LogUtil.getCoreLog().debug("=====================================================");
/* 186 */       LogUtil.getCoreLog().debug("   IdentityKey = " + this.identityKey);
/*     */       
/*     */ 
/* 189 */       if (this.sortedByValid == null) {
/* 190 */         LogUtil.getCoreLog().debug("   TemporalProcessor empty");
/*     */       }
/*     */       else
/*     */       {
/* 194 */         LogUtil.getCoreLog().debug("   UUID          StartValid     EndValidTime     WrittenTime     Value");
/* 195 */         LogUtil.getCoreLog().debug("   -------       ----------     ------------     -----------     -----");
/* 196 */         Iterator<TemporalKey> it = this.sortedByValid.iterator();
/* 197 */         while (it.hasNext()) {
/* 198 */           TemporalKey curElem = (TemporalKey)it.next();
/* 199 */           String sv; String sv; if (curElem.getValidFrom() >= TemporalUtils.MAX_TIME) {
/* 200 */             sv = "&";
/*     */           } else
/* 202 */             sv = curElem.getValidFrom() + "";
/*     */           String ev;
/* 204 */           String ev; if (curElem.getValidTo() >= TemporalUtils.MAX_TIME) {
/* 205 */             ev = "&";
/*     */           } else {
/* 207 */             ev = curElem.getValidTo() + "";
/*     */           }
/* 209 */           LogUtil.getCoreLog().debug("   " + curElem.getUuid() + "  " + sv + "  " + ev + "  " + curElem
/* 210 */             .getWrittenTime() + "  " + curElem);
/*     */         }
/*     */       }
/* 213 */       LogUtil.getCoreLog().debug("=====================================================");
/*     */     }
/*     */   }
/*     */   
/*     */   public Map<TemporalKey, String> getHistoryDump() {
/* 218 */     if (this.sortedByValid == null)
/* 219 */       return null;
/* 220 */     Iterator<TemporalKey> it = this.sortedByValid.iterator();
/* 221 */     Map<TemporalKey, String> historyMap = Maps.newHashMap();
/* 222 */     while (it.hasNext()) {
/* 223 */       TemporalKey itKey = (TemporalKey)it.next();
/* 224 */       TemporalRKey rkey = TemporalKeyUtils.createTemporalRKey(itKey.getUuid(), this.identityKey);
/* 225 */       TemporalValue value = getRegionValue(rkey);
/* 226 */       if ((value != null) && (value.getOriginValue() != null))
/*     */       {
/* 228 */         PdxInstance pdx = value.getOriginValue();
/* 229 */         LogUtil.getCoreLog().debug("Found key:{} ,value:{}", new Object[] { itKey, PdxUtils.toJson(value.getOriginValue()) });
/* 230 */         String pdxString = PdxUtils.toJson(pdx);
/* 231 */         historyMap.put(itKey, pdxString);
/*     */       } }
/* 233 */     return historyMap;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Map<String, Map> getDetailedHistory()
/*     */   {
/* 245 */     if (this.sortedByValid == null)
/* 246 */       return null;
/* 247 */     Iterator<TemporalKey> it = this.sortedByValid.iterator();
/* 248 */     Map<String, Map> historyMap = Maps.newHashMap();
/* 249 */     while (it.hasNext()) {
/* 250 */       TemporalKey itKey = (TemporalKey)it.next();
/* 251 */       TemporalRKey rkey = TemporalKeyUtils.createTemporalRKey(itKey.getUuid(), this.identityKey);
/* 252 */       TemporalValue value = getRegionValue(rkey);
/* 253 */       if ((value != null) && (value.getOriginValue() != null))
/*     */       {
/* 255 */         LogUtil.getCoreLog().debug("Found key:{} ,value:{}", new Object[] { itKey, PdxUtils.toJson(value.getOriginValue()) });
/* 256 */         Map<String, Object> map = PdxUtils.temporalToMapWithTrim(itKey, value);
/*     */         
/* 258 */         Map<String, Object> keyMap = TemporalKeyUtils.keyToMap(itKey);
/* 259 */         keyMap.put("identityKey", this.identityKey);
/* 260 */         String jsonKey = JsonUtils.objectToJson(keyMap);
/* 261 */         historyMap.put(jsonKey, map);
/*     */       } }
/* 263 */     return historyMap;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isRemoved(long asOfTime)
/*     */   {
/* 273 */     long validAtTime = TemporalUtils.now(true);
/* 274 */     TemporalEntry entry = getAsOf(validAtTime, asOfTime);
/* 275 */     if (entry == null)
/* 276 */       return true;
/* 277 */     TemporalValue value = entry.getValue();
/* 278 */     if (value == null)
/* 279 */       return true;
/* 280 */     if (value.getOriginValue() == null)
/* 281 */       return true;
/* 282 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isRemoved()
/*     */   {
/* 291 */     long asOf = TemporalUtils.now(false);
/* 292 */     return isRemoved(asOf);
/*     */   }
/*     */   
/*     */   public String getPath() {
/* 296 */     return this.path;
/*     */   }
/*     */   
/*     */   public void setPath(String path) {
/* 300 */     this.path = path;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean checkExist(TemporalValue value)
/*     */   {
/* 311 */     TemporalKey key = TemporalKeyUtils.convertToKey(null, value);
/* 312 */     dump();
/*     */     
/*     */ 
/*     */ 
/* 316 */     Collection<TemporalKey> equalTimeList = getEquals(key);
/*     */     
/*     */ 
/* 319 */     if ((equalTimeList == null) || (equalTimeList.size() == 0)) {
/* 320 */       return false;
/*     */     }
/*     */     
/* 323 */     boolean isExist = false;
/* 324 */     PdxInstance originValue = value.getOriginValue();
/* 325 */     for (TemporalKey equalTimeofOldValue : equalTimeList) {
/* 326 */       TemporalRKey rkey = TemporalKeyUtils.createTemporalRKey(equalTimeofOldValue.getUuid(), this.identityKey);
/* 327 */       TemporalValue regionTemporalValue = getRegionValue(rkey);
/* 328 */       if (regionTemporalValue != null)
/*     */       {
/* 330 */         PdxInstance regionPdxValue = regionTemporalValue.getOriginValue();
/* 331 */         if ((regionPdxValue == null) && (originValue == null)) {
/* 332 */           LogUtil.getCoreLog().debug("Found key:{} ,value:{}. Exist when remove temporal data", new Object[] { equalTimeofOldValue, regionPdxValue });
/*     */           
/* 334 */           isExist = true;
/* 335 */           break;
/*     */         }
/* 337 */         if ((regionPdxValue != null) && (TemporalValueUtils.checkValueEquals(this.path, originValue, regionPdxValue))) {
/* 338 */           LogUtil.getCoreLog().debug("Found key:{} ,value:{}", new Object[] { equalTimeofOldValue, 
/* 339 */             PdxUtils.toJson(regionPdxValue) });
/* 340 */           isExist = true;
/* 341 */           break;
/*     */         }
/*     */       } }
/* 344 */     return isExist;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void add(TemporalKey ikey)
/*     */   {
/* 352 */     add(ikey, false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public TemporalIndexCollection add(TemporalKey ikey, boolean needCreateIndex)
/*     */   {
/* 360 */     if (ikey == null) {
/* 361 */       LogUtil.getCoreLog().error("The new temporal key is null, do nothing!");
/* 362 */       return null;
/*     */     }
/* 364 */     Set<TemporalKey> deleteList = new HashSet();
/* 365 */     Set<TemporalKey> addList = new HashSet();
/*     */     
/* 367 */     processAddKey(ikey, needCreateIndex, deleteList, addList);
/*     */     
/* 369 */     if (needCreateIndex) {
/* 370 */       LogUtil.getCoreLog().debug("Need to create index, create index collection.");
/* 371 */       TemporalIndexCollection coll = new TemporalIndexCollection();
/* 372 */       processIndex(coll, deleteList, Operation.REMOVE);
/* 373 */       processIndex(coll, addList, Operation.CREATE);
/* 374 */       return coll;
/*     */     }
/* 376 */     LogUtil.getCoreLog().debug("Don't need to create index, return null.");
/* 377 */     return null;
/*     */   }
/*     */   
/*     */   private void processIndex(TemporalIndexCollection coll, Set<TemporalKey> list, Operation op) { boolean isDelete;
/* 381 */     if (list != null) {
/* 382 */       isDelete = op.isDestroy();
/* 383 */       for (TemporalKey temporalKey : list) {
/* 384 */         Map<String, Object> keyMap = TemporalKeyUtils.keyToMap(temporalKey);
/* 385 */         keyMap.put("identityKey", this.identityKey);
/* 386 */         String jsonKey = JsonUtils.objectToJson(keyMap);
/* 387 */         if (isDelete) {
/* 388 */           coll.addToDeleteKey(jsonKey);
/*     */         }
/*     */         else {
/* 391 */           TemporalRKey rkey = TemporalKeyUtils.createTemporalRKey(temporalKey.getUuid(), this.identityKey);
/* 392 */           TemporalValue regionValue = getRegionValue(rkey);
/* 393 */           if ((regionValue != null) && (regionValue.getOriginValue() != null))
/*     */           {
/* 395 */             Map<String, Object> map = PdxUtils.temporalToMapWithTrim(temporalKey, regionValue);
/* 396 */             coll.addToAddEntry(jsonKey, map);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private Set<TemporalKey> getOverlaps(TemporalKey newKey, Set<TemporalKey> set)
/*     */   {
/* 407 */     final long otherFrom = newKey.getValidFrom();
/* 408 */     long otherTo = newKey.getValidTo();
/* 409 */     final long otherTxnTime = newKey.getWrittenTime();
/* 410 */     long otherTxnTimeEnd = newKey.getWrittenTimeEnd();
/* 411 */     Set<TemporalKey> filterCollection = Sets.filter(set, new Predicate()
/*     */     {
/*     */       public boolean apply(TemporalKey input) {
/* 414 */         TemporalKey keyObject = input;
/* 415 */         if (IntervalUtils.overlapsValidTime(keyObject.getValidFrom(), keyObject.getValidTo(), otherFrom, otherTxnTime)) {} return 
/*     */         
/* 417 */           IntervalUtils.overlapsTxnTime(keyObject.getWrittenTime(), keyObject.getWrittenTimeEnd(), this.val$otherTxnTime, this.val$otherTxnTimeEnd);
/*     */       }
/*     */       
/* 420 */     });
/* 421 */     return filterCollection;
/*     */   }
/*     */   
/*     */   private Set<TemporalKey> getOverlaps(TemporalKey newKey) {
/* 425 */     return getOverlaps(newKey, this.sortedByValid);
/*     */   }
/*     */   
/*     */   private void addOneToSet(TemporalKey originalValue, long validFrom, long validTo, long writtenTime, long writtenTimeEnd, Set<TemporalKey> toAdd)
/*     */   {
/* 430 */     if ((validFrom != validTo) && (writtenTime < writtenTimeEnd)) {
/* 431 */       toAdd.add(TemporalKeyUtils.copyTemporalKey(originalValue, validFrom, validTo, writtenTime, writtenTimeEnd));
/*     */     }
/*     */   }
/*     */   
/*     */   private Set<TemporalKey> getEquals(final TemporalKey newValue) {
/* 436 */     Set<TemporalKey> filterCollection = Sets.filter(this.sortedByValid, new Predicate()
/*     */     {
/*     */       public boolean apply(TemporalKey input)
/*     */       {
/* 440 */         TemporalKey keyObject = input;
/* 441 */         if (IntervalUtils.equal(keyObject.getValidFrom(), keyObject.getValidTo(), newValue.getValidFrom(), newValue
/* 442 */           .getValidTo())) {}
/* 441 */         return 
/*     */         
/* 443 */           IntervalUtils.overlapsTxnTime(keyObject.getWrittenTime(), keyObject.getWrittenTimeEnd(), newValue
/* 444 */           .getWrittenTime(), newValue.getWrittenTimeEnd());
/*     */       }
/*     */       
/* 447 */     });
/* 448 */     return filterCollection;
/*     */   }
/*     */   
/*     */ 
/*     */   private void addOne_VStart_VEnd_TxtStart_otherTxtStart(TemporalKey originalValue, TemporalKey otherValue, Set<TemporalKey> toAdd)
/*     */   {
/* 454 */     long thisVStart = originalValue.getValidFrom();
/* 455 */     long thisVEnd = originalValue.getValidTo();
/* 456 */     long thisTxtStart = originalValue.getWrittenTime();
/* 457 */     long otherTxtStart = otherValue.getWrittenTime();
/* 458 */     addOneToSet(originalValue, thisVStart, thisVEnd, thisTxtStart, otherTxtStart, toAdd);
/*     */   }
/*     */   
/*     */ 
/*     */   private void addOne_VStart_VEnd_otherTxtEnd_TxtEnd(TemporalKey originalValue, TemporalKey otherValue, Set<TemporalKey> toAdd)
/*     */   {
/* 464 */     long thisVStart = originalValue.getValidFrom();
/* 465 */     long thisVEnd = originalValue.getValidTo();
/* 466 */     long thisTxtEnd = originalValue.getWrittenTimeEnd();
/* 467 */     long otherTxtEnd = otherValue.getWrittenTimeEnd();
/* 468 */     addOneToSet(originalValue, thisVStart, thisVEnd, otherTxtEnd, thisTxtEnd, toAdd);
/*     */   }
/*     */   
/*     */ 
/*     */   private void addOne_VStart_otherVStart_otherTxtStart_TxtEnd(TemporalKey originalValue, TemporalKey otherValue, Set<TemporalKey> toAdd)
/*     */   {
/* 474 */     long thisVStart = originalValue.getValidFrom();
/* 475 */     long thisTxtEnd = originalValue.getWrittenTimeEnd();
/* 476 */     long otherVStart = otherValue.getValidFrom();
/* 477 */     long otherTxtStart = otherValue.getWrittenTime();
/* 478 */     addOneToSet(originalValue, thisVStart, otherVStart, otherTxtStart, thisTxtEnd, toAdd);
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
/*     */   private void addOne_VStart_otherVStart_TxtStart_otherTxtEnd(TemporalKey originalValue, TemporalKey otherValue, Set<TemporalKey> toAdd)
/*     */   {
/* 497 */     long thisVStart = originalValue.getValidFrom();
/* 498 */     long thisTxtStart = originalValue.getWrittenTime();
/* 499 */     long otherVStart = otherValue.getValidFrom();
/* 500 */     long otherTxtEnd = otherValue.getWrittenTimeEnd();
/* 501 */     addOneToSet(originalValue, thisVStart, otherVStart, thisTxtStart, otherTxtEnd, toAdd);
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
/*     */   private void addOne_otherVEnd_VEnd_TxtStart_otherTxtEnd(TemporalKey originalValue, TemporalKey otherValue, Set<TemporalKey> toAdd)
/*     */   {
/* 520 */     long thisVEnd = originalValue.getValidTo();
/* 521 */     long thisTxtStart = originalValue.getWrittenTime();
/* 522 */     long otherVEnd = otherValue.getValidTo();
/* 523 */     long otherTxtEnd = otherValue.getWrittenTimeEnd();
/* 524 */     addOneToSet(originalValue, otherVEnd, thisVEnd, thisTxtStart, otherTxtEnd, toAdd);
/*     */   }
/*     */   
/*     */ 
/*     */   private void addOne_otherVStart_otherVEnd_otherTxtEnd_TxtEnd(TemporalKey originalValue, TemporalKey otherValue, Set<TemporalKey> toAdd)
/*     */   {
/* 530 */     long thisTxtEnd = originalValue.getWrittenTimeEnd();
/* 531 */     long otherVStart = otherValue.getValidFrom();
/* 532 */     long otherVEnd = otherValue.getValidTo();
/* 533 */     long otherTxtEnd = otherValue.getWrittenTimeEnd();
/* 534 */     addOneToSet(originalValue, otherVStart, otherVEnd, otherTxtEnd, thisTxtEnd, toAdd);
/*     */   }
/*     */   
/*     */ 
/*     */   private void addOne_otherVEnd_VEnd_otherTxtStart_TxtEnd(TemporalKey originalValue, TemporalKey otherValue, Set<TemporalKey> toAdd)
/*     */   {
/* 540 */     long thisVEnd = originalValue.getValidTo();
/* 541 */     long thisTxtEnd = originalValue.getWrittenTimeEnd();
/* 542 */     long otherVEnd = otherValue.getValidTo();
/* 543 */     long otherTxtStart = otherValue.getWrittenTime();
/* 544 */     addOneToSet(originalValue, otherVEnd, thisVEnd, otherTxtStart, thisTxtEnd, toAdd);
/*     */   }
/*     */   
/*     */ 
/*     */   private void addOne_otherVEnd_VEnd_otherTxtStart_otherTxtEnd(TemporalKey originalValue, TemporalKey otherValue, Set<TemporalKey> toAdd)
/*     */   {
/* 550 */     long thisVEnd = originalValue.getValidTo();
/* 551 */     long otherVEnd = otherValue.getValidTo();
/* 552 */     long otherTxtStart = otherValue.getWrittenTime();
/* 553 */     long otherTxtEnd = otherValue.getWrittenTimeEnd();
/* 554 */     addOneToSet(originalValue, otherVEnd, thisVEnd, otherTxtStart, otherTxtEnd, toAdd);
/*     */   }
/*     */   
/*     */   private void processAddKey(TemporalKey newKey, boolean needCreateIndex, Set<TemporalKey> deleteSet, Set<TemporalKey> addSet)
/*     */   {
/* 559 */     synchronized (this.sortedByValid) {
/* 560 */       Set<TemporalKey> overlapsSet = getOverlaps(newKey);
/* 561 */       LogUtil.getCoreLog().debug("Temporal process add key,get overlaps temporal key size:{}, overlaps key:{},newKey:{}", new Object[] {
/*     */       
/* 563 */         Integer.valueOf(overlapsSet.size()), overlapsSet, newKey });
/* 564 */       Set<TemporalKey> needDeleteSet = TemporalKeyUtils.copySet(overlapsSet);
/* 565 */       LogUtil.getCoreLog().debug("Temporal process need to delete temporal key size:{}", new Object[] { Integer.valueOf(needDeleteSet.size()) });
/* 566 */       Set<TemporalKey> toAdd = new HashSet();
/* 567 */       toAdd.add(newKey);
/* 568 */       processAddKey(newKey, overlapsSet, toAdd);
/* 569 */       LogUtil.getCoreLog().debug("Temporal process need to delete key set size:{}, to add key set size:{}, delete set:{}, add set:{}", new Object[] {
/*     */       
/* 571 */         Integer.valueOf(needDeleteSet.size()), Integer.valueOf(toAdd.size()), needDeleteSet, toAdd });
/* 572 */       this.sortedByValid.removeAll(needDeleteSet);
/* 573 */       this.sortedByValid.addAll(toAdd);
/* 574 */       if (needCreateIndex) {
/* 575 */         deleteSet.addAll(needDeleteSet);
/* 576 */         addSet.addAll(toAdd);
/*     */       }
/* 578 */       dump();
/*     */     }
/*     */   }
/*     */   
/*     */   private void processAddKey(TemporalKey newKey, Set<TemporalKey> overlapsSet, Set<TemporalKey> toAdd) {
/* 583 */     Set<TemporalKey> copyedOverlaps = TemporalKeyUtils.copySet(overlapsSet);
/* 584 */     copyedOverlaps.add(newKey);
/* 585 */     for (Iterator<TemporalKey> it = copyedOverlaps.iterator(); it.hasNext();) {
/* 586 */       TemporalKey itKey = (TemporalKey)it.next();
/* 587 */       LogUtil.getCoreLog().debug("Start processing one in loop, originalValue:{}, newValue:{}, current toAdd set:{}", new Object[] { itKey, newKey, toAdd });
/*     */       
/*     */ 
/*     */ 
/* 591 */       if (IntervalUtils.contansNotEqual(newKey.getWrittenTime(), newKey.getWrittenTimeEnd(), itKey
/* 592 */         .getWrittenTime())) {
/* 593 */         LogUtil.getCoreLog().debug("---NewValue is early than orignalValue, process out of time logic---");
/* 594 */         processOneOutOfOrder(itKey, newKey, toAdd);
/* 595 */         LogUtil.getCoreLog().debug("---Finish to process one out of time logic---");
/*     */       } else {
/* 597 */         LogUtil.getCoreLog().debug("---NewValue is later than orignalValue, process in order logic---");
/* 598 */         processOne(itKey, newKey, toAdd);
/* 599 */         LogUtil.getCoreLog().debug("---Finish to process one in order logic---");
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void processOneOutOfOrder(TemporalKey laterKey, TemporalKey earlyKey, Set<TemporalKey> toAdd)
/*     */   {
/* 606 */     Set<TemporalKey> newOverlapsSet = getOverlaps(laterKey, toAdd);
/* 607 */     Set<TemporalKey> copyedOverlaps = TemporalKeyUtils.copySet(newOverlapsSet);
/* 608 */     LogUtil.getCoreLog().debug("Use laterKey:{} to get overlaps set:{}, early key:{}, current toAdd set:{}.", new Object[] { laterKey, earlyKey, toAdd });
/*     */     
/* 610 */     for (Iterator<TemporalKey> it = copyedOverlaps.iterator(); it.hasNext();) {
/* 611 */       TemporalKey itKey = (TemporalKey)it.next();
/* 612 */       LogUtil.getCoreLog().debug("Start processing one out of order in loop, orignalValue:{}, laterValue:{}.", new Object[] { itKey, laterKey, toAdd });
/*     */       
/*     */ 
/* 615 */       if ((itKey.getValidFrom() == laterKey.getValidFrom()) && (itKey.getValidTo() == laterKey.getValidTo())) {
/* 616 */         LogUtil.getCoreLog().debug("ValidFrom and validTo is all equals, do nothing, continue loop...");
/* 617 */         return;
/*     */       }
/* 619 */       LogUtil.getCoreLog().debug("Remove one:{}.", new Object[] { itKey });
/* 620 */       toAdd.remove(itKey);
/* 621 */       processOne(itKey, laterKey, toAdd);
/*     */     }
/* 623 */     LogUtil.getCoreLog().debug("Add laterKey{} to toAdd set.", new Object[] { laterKey });
/* 624 */     toAdd.add(laterKey);
/*     */   }
/*     */   
/*     */   private void processOne(TemporalKey originalValue, TemporalKey newValue, Set<TemporalKey> toAdd) {
/* 628 */     long thisTxt = originalValue.getWrittenTime();
/* 629 */     long thisTxtEnd = originalValue.getWrittenTimeEnd();
/* 630 */     long newTxtStart = newValue.getWrittenTime();
/* 631 */     long newTxtEnd = newValue.getWrittenTimeEnd();
/*     */     
/* 633 */     boolean during = IntervalUtils.during(thisTxt, thisTxtEnd, newTxtStart, newTxtEnd);
/* 634 */     boolean start = IntervalUtils.start(thisTxt, thisTxtEnd, newTxtStart, newTxtEnd);
/* 635 */     boolean frontOverlap = IntervalUtils.frontOverlap(thisTxt, thisTxtEnd, newTxtStart, newTxtEnd);
/*     */     
/*     */ 
/*     */ 
/* 639 */     boolean finish = IntervalUtils.finish(thisTxt, thisTxtEnd, newTxtStart, newTxtEnd);
/* 640 */     boolean equal = IntervalUtils.equal(thisTxt, thisTxtEnd, newTxtStart, newTxtEnd);
/*     */     
/*     */ 
/*     */ 
/* 644 */     boolean startContains = IntervalUtils.startContains(thisTxt, thisTxtEnd, newTxtStart, newTxtEnd);
/*     */     
/*     */ 
/* 647 */     if (during) {
/* 648 */       processOneTxtDuring(originalValue, newValue, toAdd);
/* 649 */     } else if (start) {
/* 650 */       processOneTxtStart(originalValue, newValue, toAdd);
/* 651 */     } else if ((frontOverlap) || (finish)) {
/* 652 */       processOneTxtFrontOverlapOrFinish(originalValue, newValue, toAdd);
/* 653 */     } else if ((equal) || (startContains)) {
/* 654 */       processOneTxtEqualOrStartContains(originalValue, newValue, toAdd);
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
/*     */ 
/*     */ 
/*     */   private void processOneTxtDuring(TemporalKey originalValue, TemporalKey newValue, Set<TemporalKey> toAdd)
/*     */   {
/* 669 */     ValidIntervalTypes intervalType = getValidIntervalType(originalValue, newValue);
/* 670 */     if (ValidIntervalTypes.DURING == intervalType) {
/* 671 */       LogUtil.getCoreLog().debug("WrittenTime and valid interval is both during the original interval.");
/* 672 */       addOne_VStart_VEnd_TxtStart_otherTxtStart(originalValue, newValue, toAdd);
/* 673 */       addOne_VStart_otherVStart_otherTxtStart_TxtEnd(originalValue, newValue, toAdd);
/* 674 */       addOne_otherVEnd_VEnd_otherTxtStart_TxtEnd(originalValue, newValue, toAdd);
/* 675 */       addOne_otherVStart_otherVEnd_otherTxtEnd_TxtEnd(originalValue, newValue, toAdd);
/* 676 */     } else if ((ValidIntervalTypes.FINISH == intervalType) || (ValidIntervalTypes.FRONT_OVERLAP == intervalType)) {
/* 677 */       LogUtil.getCoreLog().debug("WrittenTime interval is during the original interval. Valid interval is finish or front overlaps the orignal interval.");
/*     */       
/* 679 */       addOne_VStart_VEnd_TxtStart_otherTxtStart(originalValue, newValue, toAdd);
/* 680 */       addOne_VStart_otherVStart_otherTxtStart_TxtEnd(originalValue, newValue, toAdd);
/* 681 */       addOne_otherVStart_otherVEnd_otherTxtEnd_TxtEnd(originalValue, newValue, toAdd);
/* 682 */     } else if ((ValidIntervalTypes.START == intervalType) || (ValidIntervalTypes.LATTER_OVERLAP == intervalType)) {
/* 683 */       LogUtil.getCoreLog().debug("WrittenTime interval is during the original interval. Valid interval is start or latter overlap the orignal interval.");
/*     */       
/* 685 */       addOne_VStart_VEnd_TxtStart_otherTxtStart(originalValue, newValue, toAdd);
/* 686 */       addOne_VStart_VEnd_otherTxtEnd_TxtEnd(originalValue, newValue, toAdd);
/* 687 */       addOne_otherVEnd_VEnd_otherTxtStart_otherTxtEnd(originalValue, newValue, toAdd);
/* 688 */     } else if ((ValidIntervalTypes.EQUAL == intervalType) || (ValidIntervalTypes.START_CONTAINS == intervalType) || (ValidIntervalTypes.FINISH_CONTAINS == intervalType))
/*     */     {
/* 690 */       LogUtil.getCoreLog().debug("WrittenTime interval is during the original interval. Valid interval is equal or start contains or finish contains the orignal interval.");
/*     */       
/* 692 */       addOne_VStart_VEnd_TxtStart_otherTxtStart(originalValue, newValue, toAdd);
/* 693 */       addOne_VStart_VEnd_otherTxtEnd_TxtEnd(originalValue, newValue, toAdd);
/*     */     }
/*     */   }
/*     */   
/*     */   private void processOneTxtStart(TemporalKey originalValue, TemporalKey newValue, Set<TemporalKey> toAdd) {
/* 698 */     addOne_VStart_VEnd_otherTxtEnd_TxtEnd(originalValue, newValue, toAdd);
/* 699 */     ValidIntervalTypes intervalType = getValidIntervalType(originalValue, newValue);
/* 700 */     if (ValidIntervalTypes.DURING == intervalType) {
/* 701 */       LogUtil.getCoreLog().debug("WrittenTime interval is start the orignal interval. Valid interval is during the original interval.");
/*     */       
/* 703 */       addOne_VStart_VEnd_otherTxtEnd_TxtEnd(originalValue, newValue, toAdd);
/* 704 */       addOne_VStart_otherVStart_TxtStart_otherTxtEnd(originalValue, newValue, toAdd);
/* 705 */       addOne_otherVEnd_VEnd_TxtStart_otherTxtEnd(originalValue, newValue, toAdd);
/* 706 */     } else if ((ValidIntervalTypes.FINISH == intervalType) || (ValidIntervalTypes.FRONT_OVERLAP == intervalType)) {
/* 707 */       LogUtil.getCoreLog().debug("WrittenTime interval is start the original interval. Valid interval is finish or front overlaps the orignal interval.");
/*     */       
/* 709 */       addOne_VStart_VEnd_otherTxtEnd_TxtEnd(originalValue, newValue, toAdd);
/* 710 */       addOne_VStart_otherVStart_TxtStart_otherTxtEnd(originalValue, newValue, toAdd);
/* 711 */     } else if ((ValidIntervalTypes.START == intervalType) || (ValidIntervalTypes.LATTER_OVERLAP == intervalType)) {
/* 712 */       LogUtil.getCoreLog().debug("WrittenTime interval is start the original interval. Valid interval is start or latter overlap the orignal interval.");
/*     */       
/* 714 */       addOne_VStart_VEnd_otherTxtEnd_TxtEnd(originalValue, newValue, toAdd);
/* 715 */       addOne_otherVEnd_VEnd_TxtStart_otherTxtEnd(originalValue, newValue, toAdd);
/* 716 */     } else if ((ValidIntervalTypes.EQUAL == intervalType) || (ValidIntervalTypes.START_CONTAINS == intervalType) || (ValidIntervalTypes.FINISH_CONTAINS == intervalType))
/*     */     {
/* 718 */       LogUtil.getCoreLog().debug("WrittenTime interval is start the original interval. Valid interval is equal or start/finish contains the orignal interval.");
/*     */       
/* 720 */       addOne_VStart_VEnd_otherTxtEnd_TxtEnd(originalValue, newValue, toAdd);
/*     */     }
/*     */   }
/*     */   
/*     */   private void processOneTxtFrontOverlapOrFinish(TemporalKey originalValue, TemporalKey newValue, Set<TemporalKey> toAdd)
/*     */   {
/* 726 */     ValidIntervalTypes intervalType = getValidIntervalType(originalValue, newValue);
/* 727 */     if (ValidIntervalTypes.DURING == intervalType) {
/* 728 */       LogUtil.getCoreLog().debug("WrittenTime interval is front overlap or finish the orignal interval. Valid interval is during the original interval.");
/*     */       
/* 730 */       addOne_VStart_VEnd_TxtStart_otherTxtStart(originalValue, newValue, toAdd);
/* 731 */       addOne_VStart_otherVStart_otherTxtStart_TxtEnd(originalValue, newValue, toAdd);
/* 732 */       addOne_otherVEnd_VEnd_otherTxtStart_TxtEnd(originalValue, newValue, toAdd);
/* 733 */     } else if ((ValidIntervalTypes.FINISH == intervalType) || (ValidIntervalTypes.FRONT_OVERLAP == intervalType)) {
/* 734 */       LogUtil.getCoreLog().debug("WrittenTime interval is front overlap or finsh the original interval. Valid interval is finish or front overlaps the orignal interval.");
/*     */       
/* 736 */       addOne_VStart_VEnd_TxtStart_otherTxtStart(originalValue, newValue, toAdd);
/* 737 */       addOne_VStart_otherVStart_otherTxtStart_TxtEnd(originalValue, newValue, toAdd);
/* 738 */     } else if ((ValidIntervalTypes.START == intervalType) || (ValidIntervalTypes.LATTER_OVERLAP == intervalType)) {
/* 739 */       LogUtil.getCoreLog().debug("WrittenTime interval is front overlap or finish the original interval,Valid interval is start or latter overlap the orignal interval.");
/*     */       
/* 741 */       addOne_VStart_VEnd_TxtStart_otherTxtStart(originalValue, newValue, toAdd);
/* 742 */       addOne_otherVEnd_VEnd_otherTxtStart_TxtEnd(originalValue, newValue, toAdd);
/* 743 */     } else if ((ValidIntervalTypes.EQUAL == intervalType) || (ValidIntervalTypes.START_CONTAINS == intervalType) || (ValidIntervalTypes.FINISH_CONTAINS == intervalType))
/*     */     {
/* 745 */       LogUtil.getCoreLog().debug("WrittenTime interval is front overlap or finish the original interval,Valid interval is equal or start/finish contains the orignal interval.");
/*     */       
/* 747 */       addOne_VStart_VEnd_TxtStart_otherTxtStart(originalValue, newValue, toAdd);
/*     */     }
/*     */   }
/*     */   
/*     */   private void processOneTxtEqualOrStartContains(TemporalKey originalValue, TemporalKey newValue, Set<TemporalKey> toAdd)
/*     */   {
/* 753 */     ValidIntervalTypes intervalType = getValidIntervalType(originalValue, newValue);
/* 754 */     if (ValidIntervalTypes.DURING == intervalType) {
/* 755 */       LogUtil.getCoreLog().debug("WrittenTime interval is equal or start contains the orignal interval. Valid interval is during the original interval.");
/*     */       
/* 757 */       addOne_VStart_otherVStart_otherTxtStart_TxtEnd(originalValue, newValue, toAdd);
/* 758 */       addOne_otherVEnd_VEnd_otherTxtStart_TxtEnd(originalValue, newValue, toAdd);
/* 759 */     } else if ((ValidIntervalTypes.FINISH == intervalType) || (ValidIntervalTypes.FRONT_OVERLAP == intervalType)) {
/* 760 */       LogUtil.getCoreLog().debug("WrittenTime interval is equal or start contains the original interval. Valid interval is finish or front overlaps the orignal interval.");
/*     */       
/* 762 */       addOne_VStart_otherVStart_otherTxtStart_TxtEnd(originalValue, newValue, toAdd);
/* 763 */     } else if (ValidIntervalTypes.START == intervalType) {
/* 764 */       LogUtil.getCoreLog().debug("WrittenTime interva is equal or start contains the original interval,Valid interval is start the orignal interval.");
/*     */       
/* 766 */       addOne_otherVEnd_VEnd_otherTxtStart_TxtEnd(originalValue, newValue, toAdd);
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
/*     */   private ValidIntervalTypes getValidIntervalType(TemporalKey originalValue, TemporalKey newValue)
/*     */   {
/* 858 */     long thisVStart = originalValue.getValidFrom();
/* 859 */     long thisVEnd = originalValue.getValidTo();
/* 860 */     long newVStart = newValue.getValidFrom();
/* 861 */     long newVEnd = newValue.getValidTo();
/* 862 */     boolean during = IntervalUtils.during(thisVStart, thisVEnd, newVStart, newVEnd);
/* 863 */     boolean start = IntervalUtils.start(thisVStart, thisVEnd, newVStart, newVEnd);
/* 864 */     boolean frontOverlap = IntervalUtils.frontOverlap(thisVStart, thisVEnd, newVStart, newVEnd);
/* 865 */     boolean latterOverlap = IntervalUtils.latterOverlap(thisVStart, thisVEnd, newVStart, newVEnd);
/* 866 */     boolean finish = IntervalUtils.finish(thisVStart, thisVEnd, newVStart, newVEnd);
/* 867 */     boolean equal = IntervalUtils.equal(thisVStart, thisVEnd, newVStart, newVEnd);
/* 868 */     boolean startContains = IntervalUtils.startContains(thisVStart, thisVEnd, newVStart, newVEnd);
/* 869 */     boolean finishContains = IntervalUtils.finishContains(thisVStart, thisVEnd, newVStart, newVEnd);
/* 870 */     boolean fullContains = IntervalUtils.fullContains(thisVStart, thisVEnd, newVStart, newVEnd);
/* 871 */     if (during)
/* 872 */       return ValidIntervalTypes.DURING;
/* 873 */     if (latterOverlap)
/* 874 */       return ValidIntervalTypes.LATTER_OVERLAP;
/* 875 */     if (frontOverlap)
/* 876 */       return ValidIntervalTypes.FRONT_OVERLAP;
/* 877 */     if (start)
/* 878 */       return ValidIntervalTypes.START;
/* 879 */     if (finish)
/* 880 */       return ValidIntervalTypes.FINISH;
/* 881 */     if (equal)
/* 882 */       return ValidIntervalTypes.EQUAL;
/* 883 */     if (startContains)
/* 884 */       return ValidIntervalTypes.START_CONTAINS;
/* 885 */     if (finishContains)
/* 886 */       return ValidIntervalTypes.FINISH_CONTAINS;
/* 887 */     if (fullContains) {
/* 888 */       return ValidIntervalTypes.FINISH_CONTAINS;
/*     */     }
/* 890 */     return null;
/*     */   }
/*     */   
/*     */   private static enum ValidIntervalTypes {
/* 894 */     DURING,  START,  FINISH,  EQUAL,  START_CONTAINS,  FINISH_CONTAINS,  FRONT_OVERLAP,  LATTER_OVERLAP;
/*     */     
/*     */     private ValidIntervalTypes() {} }
/*     */   
/* 898 */   public void clear() { this.sortedByValid.clear(); }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\data\temporal\TemporalProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */