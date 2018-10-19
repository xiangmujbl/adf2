/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.data.temporal.utils;
/*     */ 
/*     */ import com.jnj.adf.grid.data.ReservedFields;
/*     */ import com.jnj.adf.grid.data.temporal.TemporalKey;
/*     */ import com.jnj.adf.grid.data.temporal.TemporalRKey;
/*     */ import com.jnj.adf.grid.data.temporal.TemporalValue;
/*     */ import com.jnj.adf.grid.utils.JsonUtils;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import java.util.concurrent.ConcurrentSkipListSet;
/*     */ 
/*     */ public class TemporalKeyUtils
/*     */ {
/*     */   public static final TemporalKey createTemporalKey(long validFrom, long validTo, long writtenTime)
/*     */   {
/*  20 */     TemporalKey tkey = new TemporalKey();
/*  21 */     tkey.setUuid(UUID.randomUUID().toString());
/*  22 */     tkey.setValidFrom(validFrom);
/*  23 */     tkey.setValidTo(validTo);
/*  24 */     tkey.setWrittenTime(writtenTime);
/*  25 */     return tkey;
/*     */   }
/*     */   
/*     */   public static final TemporalRKey createTemporalRKey(String uuid, String identityKey) {
/*  29 */     TemporalRKey rkey = new TemporalRKey();
/*  30 */     rkey.setUuid(uuid);
/*  31 */     rkey.setIdentityKey(identityKey);
/*  32 */     return rkey;
/*     */   }
/*     */   
/*     */   public static final TemporalKey convertToKey(String uuid, TemporalValue value) {
/*  36 */     if (value == null)
/*  37 */       return null;
/*  38 */     TemporalKey key = new TemporalKey();
/*  39 */     key.setUuid(uuid);
/*  40 */     key.setValidFrom(value.getValidFrom());
/*  41 */     key.setValidTo(value.getValidTo());
/*  42 */     key.setWrittenTime(value.getWrittenTime());
/*  43 */     return key;
/*     */   }
/*     */   
/*     */   public static final ConcurrentSkipListSet<TemporalKey> createConcurrentSetWithComparator() {
/*  47 */     new ConcurrentSkipListSet(new Comparator() {
/*     */       public int compare(TemporalKey o1, TemporalKey o2) {
/*  49 */         long n = o1.getWrittenTime() - o2.getWrittenTime();
/*  50 */         if (n == 0L) {
/*  51 */           n = o2.getWrittenTimeEnd() - o1.getWrittenTimeEnd();
/*  52 */           if (n == 0L) {
/*  53 */             n = o1.getValidFrom() - o2.getValidFrom();
/*  54 */             if (n == 0L) {
/*  55 */               n = o1.getValidTo() - o2.getValidTo();
/*  56 */               if (n == 0L) {
/*  57 */                 n = o1.getUuid().compareTo(o2.getUuid());
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*  62 */         return (int)n;
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */   public static final Set<TemporalKey> copySet(Set<TemporalKey> set) {
/*  68 */     Set<TemporalKey> newSet = createConcurrentSetWithComparator();
/*  69 */     Iterator<TemporalKey> it; if ((set != null) && (set.size() > 0)) {
/*  70 */       for (it = set.iterator(); it.hasNext();) {
/*  71 */         TemporalKey original = (TemporalKey)it.next();
/*  72 */         newSet.add(copyTemporalKey(original));
/*     */       }
/*     */     }
/*  75 */     return newSet;
/*     */   }
/*     */   
/*     */   public static final TemporalKey copyTemporalKey(TemporalKey original, long validFrom, long validTo, long newWrittenTime)
/*     */   {
/*  80 */     TemporalKey newKey = new TemporalKey();
/*  81 */     newKey.setUuid(original.getUuid());
/*  82 */     newKey.setValidFrom(validFrom);
/*  83 */     newKey.setValidTo(validTo);
/*  84 */     newKey.setWrittenTime(newWrittenTime);
/*  85 */     return newKey;
/*     */   }
/*     */   
/*     */   public static final TemporalKey copyTemporalKey(TemporalKey original, long validFrom, long validTo, long newWrittenTime, long newWrittenTimeEnd)
/*     */   {
/*  90 */     TemporalKey newKey = new TemporalKey();
/*  91 */     newKey.setUuid(original.getUuid());
/*  92 */     newKey.setValidFrom(validFrom);
/*  93 */     newKey.setValidTo(validTo);
/*  94 */     newKey.setWrittenTime(newWrittenTime);
/*  95 */     newKey.setWrittenTimeEnd(newWrittenTimeEnd);
/*  96 */     return newKey;
/*     */   }
/*     */   
/*     */   public static final TemporalKey copyTemporalKey(TemporalKey original) {
/* 100 */     TemporalKey newKey = new TemporalKey();
/* 101 */     newKey.setUuid(original.getUuid());
/* 102 */     newKey.setValidFrom(original.getValidFrom());
/* 103 */     newKey.setValidTo(original.getValidTo());
/* 104 */     newKey.setWrittenTime(original.getWrittenTime());
/* 105 */     newKey.setWrittenTimeEnd(original.getWrittenTimeEnd());
/* 106 */     return newKey;
/*     */   }
/*     */   
/*     */   public static final Map<String, Object> keyToMap(TemporalKey key) {
/* 110 */     Map<String, Object> map = new HashMap();
/* 111 */     map.put(ReservedFields.TEMPORAL_UUID.fieldName(), key.getUuid());
/* 112 */     map.put(ReservedFields.TEMPORAL_VALID_FROM.fieldName(), Long.valueOf(key.getValidFrom()));
/* 113 */     map.put(ReservedFields.TEMPORAL_VALID_TO.fieldName(), Long.valueOf(key.getValidTo()));
/* 114 */     map.put(ReservedFields.TEMPORAL_WRITTEN_TIME.fieldName(), Long.valueOf(key.getWrittenTime()));
/* 115 */     map.put(ReservedFields.TEMPORAL_WRITTEN_TIME_END.fieldName(), Long.valueOf(key.getWrittenTimeEnd()));
/* 116 */     return map;
/*     */   }
/*     */   
/*     */   public static final TemporalRKey luceneKeyToRkey(Object luceneKey)
/*     */   {
/* 121 */     String lks = JsonUtils.objectToJson(luceneKey);
/* 122 */     Map<String, Object> map = (Map)JsonUtils.jsonToObject(lks, Map.class);
/* 123 */     if ((map != null) && (map.size() > 2)) {
/* 124 */       String identityKey = (String)map.get("identityKey");
/* 125 */       String uuid = (String)map.get(ReservedFields.TEMPORAL_UUID.fieldName());
/* 126 */       TemporalRKey rkey = createTemporalRKey(uuid, identityKey);
/* 127 */       return rkey;
/*     */     }
/* 129 */     return null;
/*     */   }
/*     */   
/*     */   public static final TemporalKey luceneKeyToTemporalkey(Object luceneKey) {
/* 133 */     String lks = JsonUtils.objectToJson(luceneKey);
/* 134 */     Map<String, Object> map = (Map)JsonUtils.jsonToObject(lks, Map.class);
/* 135 */     if ((map != null) && (map.size() > 2)) {
/* 136 */       String uuid = (String)map.get(ReservedFields.TEMPORAL_UUID.fieldName());
/* 137 */       TemporalKey key = new TemporalKey();
/* 138 */       key.setUuid(uuid);
/* 139 */       key.setValidFrom(Long.valueOf(map.get(ReservedFields.TEMPORAL_VALID_FROM.fieldName()).toString()).longValue());
/* 140 */       key.setValidTo(Long.valueOf(map.get(ReservedFields.TEMPORAL_VALID_TO.fieldName()).toString()).longValue());
/*     */       
/* 142 */       key.setWrittenTime(Long.valueOf(map.get(ReservedFields.TEMPORAL_WRITTEN_TIME.fieldName()).toString()).longValue());
/* 143 */       key.setWrittenTimeEnd(
/* 144 */         Long.valueOf(map.get(ReservedFields.TEMPORAL_WRITTEN_TIME_END.fieldName()).toString()).longValue());
/* 145 */       return key;
/*     */     }
/* 147 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static final TemporalRKey convertToRKey(String rkeyStr)
/*     */   {
/* 156 */     TemporalRKey rkey = (TemporalRKey)JsonUtils.jsonToObject(rkeyStr, TemporalRKey.class);
/* 157 */     return rkey;
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\data\temporal\utils\TemporalKeyUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */