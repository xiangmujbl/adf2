/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.query.criteria;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.jnj.adf.client.api.ADFCriteria;
/*     */ import com.jnj.adf.client.api.query.QueryHelper;
/*     */ import com.jnj.adf.grid.utils.Util;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Collection;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.springframework.dao.InvalidDataAccessApiUsageException;
/*     */ import org.springframework.util.Assert;
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
/*     */ public class ADFCriteriaImpl
/*     */   implements ADFCriteria
/*     */ {
/*     */   private static final String CRITERIA_VALUE_SEPERATOR = " ";
/*     */   private static final String ESCAPE_WHITE_SPACE = "$$$$";
/*  37 */   private Criteria criteria = null;
/*  38 */   private Crotch crotch = null;
/*  39 */   private DefaultQueryParser queryParser = null;
/*     */   
/*     */   public ADFCriteriaImpl() {
/*  42 */     this.criteria = new Criteria();
/*  43 */     this.queryParser = new DefaultQueryParser();
/*     */   }
/*     */   
/*     */   public ADFCriteriaImpl(String fieldName) {
/*  47 */     this.criteria = new Criteria(fieldName);
/*  48 */     this.queryParser = new DefaultQueryParser();
/*     */   }
/*     */   
/*     */   public Criteria getCriteria()
/*     */   {
/*  53 */     return this.crotch != null ? this.crotch : this.criteria;
/*     */   }
/*     */   
/*     */   public ADFCriteria is(Object o) {
/*  57 */     Assert.isTrue(isNotEmpty(o), "value in 'is' must not be null");
/*     */     
/*  59 */     if ((o instanceof String)) {
/*  60 */       getCriteria().is(escapeBlanks(String.valueOf(o)));
/*     */     } else
/*  62 */       getCriteria().is(o);
/*  63 */     return this;
/*     */   }
/*     */   
/*     */   public ADFCriteria in(Object... objs) {
/*  67 */     assertValuesPresent(objs);
/*  68 */     Assert.notNull(objs, "Collection of 'in' values must not be null");
/*  69 */     for (Object value : objs) {
/*  70 */       if ((value instanceof Collection)) {
/*  71 */         in((Collection)value);
/*     */       } else {
/*  73 */         is(value);
/*     */       }
/*     */     }
/*  76 */     return this;
/*     */   }
/*     */   
/*     */   private ADFCriteria in(Collection<?> objs) {
/*  80 */     Assert.notNull(objs, "Collection of 'in' values must not be null");
/*  81 */     for (Object value : objs) {
/*  82 */       is(value);
/*     */     }
/*  84 */     return this;
/*     */   }
/*     */   
/*     */   public String toQueryString() {
/*  88 */     return this.queryParser.createQueryStringFromNode(getCriteria());
/*     */   }
/*     */   
/*     */   public ADFCriteria and(String fieldName) {
/*  92 */     if (this.crotch != null) {
/*  93 */       this.crotch.and(fieldName);
/*     */     } else
/*  95 */       this.crotch = this.criteria.and(fieldName);
/*  96 */     return this;
/*     */   }
/*     */   
/*     */   public ADFCriteria and(ADFCriteria adfCriteria) {
/* 100 */     if ((adfCriteria instanceof ADFCriteriaImpl)) {
/* 101 */       ADFCriteriaImpl adfCriteriaImpl = (ADFCriteriaImpl)adfCriteria;
/* 102 */       Criteria innerCriteria = adfCriteriaImpl.getCriteria();
/* 103 */       if (this.crotch != null) {
/* 104 */         Crotch c = new Crotch();
/* 105 */         c.add(this.crotch);
/* 106 */         this.crotch = c.and(innerCriteria);
/*     */       } else {
/* 108 */         this.crotch = this.criteria.and(innerCriteria);
/*     */       }
/*     */     }
/* 111 */     return this;
/*     */   }
/*     */   
/*     */   public ADFCriteria or(ADFCriteria adfCriteria) {
/* 115 */     if ((adfCriteria instanceof ADFCriteriaImpl)) {
/* 116 */       ADFCriteriaImpl adfCriteriaImpl = (ADFCriteriaImpl)adfCriteria;
/* 117 */       Criteria innerCriteria = adfCriteriaImpl.getCriteria();
/* 118 */       if (this.crotch != null) {
/* 119 */         Crotch c = new Crotch();
/* 120 */         c.add(this.crotch);
/* 121 */         this.crotch = c.or(innerCriteria);
/*     */       } else {
/* 123 */         this.crotch = this.criteria.or(innerCriteria);
/*     */       }
/*     */     }
/* 126 */     return this;
/*     */   }
/*     */   
/*     */   public ADFCriteria isNull() {
/* 130 */     String fieldName = getCriteria().getField().getName();
/* 131 */     Criteria cr = new Criteria(fieldName).isNull();
/* 132 */     Crotch cro = cr.or(new Criteria().expression(fieldName + ":/\\s*/"));
/*     */     
/* 134 */     if (this.crotch == null) {
/* 135 */       this.crotch = cro;
/*     */     } else {
/* 137 */       resetField(this.crotch);
/* 138 */       cro.setParent(this.crotch);
/* 139 */       Util.unsafeSet(this.crotch, "mostRecentSibling", cro);
/*     */       
/* 141 */       Collection<Criteria> siblings = this.crotch.getSiblings();
/* 142 */       Collection<Criteria> tmpSiblings = Lists.newArrayList();
/* 143 */       if ((siblings != null) && (!siblings.isEmpty())) {
/* 144 */         Criteria[] arr = new Criteria[siblings.size()];
/* 145 */         siblings.toArray(arr);
/*     */         
/* 147 */         int i = 0; for (int len = arr.length - 1; i < len; i++) {
/* 148 */           tmpSiblings.add(arr[i]);
/*     */         }
/* 150 */         tmpSiblings.add(cro);
/* 151 */         Util.unsafeSet(this.crotch, "siblings", tmpSiblings);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 156 */     return this;
/*     */   }
/*     */   
/*     */   public ADFCriteria missingFiled()
/*     */   {
/* 161 */     getCriteria().isNull();
/* 162 */     return this;
/*     */   }
/*     */   
/*     */   public ADFCriteria isNotNull() {
/* 166 */     String fieldName = getCriteria().getField().getName();
/* 167 */     keywords(fieldName + ":/(\\s+[^\\s]+.*)|([^\\s]+.*)/");
/* 168 */     return this;
/*     */   }
/*     */   
/*     */   public ADFCriteria startsWith(String s) {
/* 172 */     Assert.isTrue(isNotEmpty(s), "value in 'startsWith' must not be null");
/* 173 */     getCriteria().startsWith(escapeBlanks(s));
/* 174 */     return this;
/*     */   }
/*     */   
/*     */   public ADFCriteria startsWith(String... values) {
/* 178 */     assertValuesPresent((Object[])values);
/* 179 */     Assert.notNull(values, "Collection must not be null");
/* 180 */     for (String value : values) {
/* 181 */       startsWith(value);
/*     */     }
/* 183 */     return this;
/*     */   }
/*     */   
/*     */   public ADFCriteria endsWith(String s) {
/* 187 */     Assert.isTrue(isNotEmpty(s), "value in 'endsWith' must not be null");
/* 188 */     getCriteria().endsWith(escapeBlanks(s));
/* 189 */     return this;
/*     */   }
/*     */   
/*     */   public ADFCriteria endsWith(String... values) {
/* 193 */     assertValuesPresent((Object[])values);
/* 194 */     Assert.notNull(values, "Collection must not be null");
/* 195 */     for (String value : values) {
/* 196 */       endsWith(value);
/*     */     }
/* 198 */     return this;
/*     */   }
/*     */   
/*     */   public ADFCriteria contains(String s) {
/* 202 */     Assert.isTrue(isNotEmpty(s), "value in 'contains' must not be null");
/* 203 */     getCriteria().contains(escapeBlanks(s));
/* 204 */     return this;
/*     */   }
/*     */   
/*     */   public ADFCriteria contains(String... values) {
/* 208 */     assertValuesPresent((Object[])values);
/* 209 */     Assert.notNull(values, "Collection must not be null");
/* 210 */     for (String value : values) {
/* 211 */       contains(value);
/*     */     }
/* 213 */     return this;
/*     */   }
/*     */   
/*     */   public ADFCriteria fuzzy(String s) {
/* 217 */     Assert.isTrue(isNotEmpty(s), "value in 'fuzzy' must not be null");
/* 218 */     getCriteria().fuzzy(s);
/* 219 */     return this;
/*     */   }
/*     */   
/*     */   public ADFCriteria fuzzy(String s, float levenshteinDistance) {
/* 223 */     Assert.isTrue(isNotEmpty(s), "value in 'fuzzy' must not be null");
/* 224 */     getCriteria().fuzzy(s, levenshteinDistance);
/* 225 */     return this;
/*     */   }
/*     */   
/*     */   public ADFCriteria lessThan(Object upperBound) {
/* 229 */     Assert.isTrue(isNotEmpty(upperBound), "upperBound in 'lessThan' must not be null");
/* 230 */     getCriteria().lessThan(upperBound);
/* 231 */     return this;
/*     */   }
/*     */   
/*     */   public ADFCriteria lessThanEqual(Object upperBound)
/*     */   {
/* 236 */     Assert.isTrue(isNotEmpty(upperBound), "upperBound in 'lessThanEqual' must not be null");
/* 237 */     getCriteria().lessThanEqual(upperBound);
/* 238 */     return this;
/*     */   }
/*     */   
/*     */   public ADFCriteria greaterThan(Object lowerBound) {
/* 242 */     Assert.isTrue(isNotEmpty(lowerBound), "lowerBound in 'greaterThan' must not be null");
/* 243 */     getCriteria().greaterThan(lowerBound);
/* 244 */     return this;
/*     */   }
/*     */   
/*     */   public ADFCriteria greaterThanEqual(Object lowerBound) {
/* 248 */     Assert.isTrue(isNotEmpty(lowerBound), "lowerBound in 'greaterThanEqual' must not be null");
/* 249 */     getCriteria().greaterThanEqual(lowerBound);
/* 250 */     return this;
/*     */   }
/*     */   
/*     */   public ADFCriteria between(Object lowerBound, Object upperBound) {
/* 254 */     Assert.isTrue(isNotEmpty(lowerBound), "lowerBound in 'between' must not be null");
/* 255 */     Assert.isTrue(isNotEmpty(upperBound), "upperBound in 'between' must not be null");
/* 256 */     getCriteria().between(lowerBound, upperBound);
/* 257 */     return this;
/*     */   }
/*     */   
/*     */   public ADFCriteria keywords(String keyword)
/*     */   {
/* 262 */     if (getCriteria().getField() == null) {
/* 263 */       getCriteria().expression(keyword);
/*     */     }
/*     */     else
/*     */     {
/* 267 */       resetField(getCriteria());
/* 268 */       getCriteria().expression(keyword);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 273 */     return this;
/*     */   }
/*     */   
/*     */   private String escapeBlanks(String value)
/*     */   {
/* 278 */     if (StringUtils.contains(value, " ")) {
/* 279 */       value = StringUtils.replace(value, " ", "$$$$");
/*     */     }
/* 281 */     return value;
/*     */   }
/*     */   
/*     */   private void assertValuesPresent(Object... values) {
/* 285 */     if ((values.length == 0) || ((values.length > 1) && ((values[1] instanceof Collection)))) {
/* 286 */       throw new InvalidDataAccessApiUsageException("At least one element " + (values.length > 0 ? "of argument of type " + values[1].getClass().getName() : "") + " has to be present.");
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean isNotEmpty(Object o)
/*     */   {
/* 292 */     boolean flag = true;
/* 293 */     if (o == null) {
/* 294 */       flag = false;
/*     */ 
/*     */     }
/* 297 */     else if ((o instanceof String))
/*     */     {
/* 299 */       String s = (String)o;
/* 300 */       if (StringUtils.isEmpty(s)) {
/* 301 */         flag = false;
/*     */       }
/*     */     }
/*     */     
/* 305 */     return flag;
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
/*     */   public ADFCriteria not()
/*     */   {
/* 328 */     getCriteria().not();
/* 329 */     return this;
/*     */   }
/*     */   
/*     */   private void resetField(Criteria cr) {
/* 333 */     if ((cr instanceof Crotch)) {
/* 334 */       Criteria tmpCr = null;
/* 335 */       Collection<Criteria> siblings = this.crotch.getSiblings();
/* 336 */       if ((siblings != null) && (!siblings.isEmpty())) {
/* 337 */         Criteria[] arr = new Criteria[siblings.size()];
/* 338 */         siblings.toArray(arr);
/* 339 */         tmpCr = arr[(arr.length - 1)];
/* 340 */         resetField(tmpCr);
/*     */       }
/*     */     } else {
/* 343 */       Util.unsafeSet(cr, "field", null);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {
/* 348 */     System.out.println(QueryHelper.buildCriteria("f1").isNull().toQueryString());
/* 349 */     System.out.println(QueryHelper.buildCriteria("f1").isNotNull().toQueryString());
/*     */     
/* 351 */     System.out.println("=======================================");
/* 352 */     ADFCriteria c1 = QueryHelper.buildCriteria("f2").isNotNull();
/* 353 */     System.out.println(QueryHelper.buildCriteria("f1").is("liu feng").and(c1).and("f3").is("aaa").toQueryString());
/* 354 */     System.out.println(QueryHelper.buildCriteria("f1").is("liu feng").and("f2").isNotNull().and("f3").is("aaa").toQueryString());
/* 355 */     System.out.println("=======================================");
/* 356 */     c1 = QueryHelper.buildCriteria("f2").isNull();
/* 357 */     System.out.println(QueryHelper.buildCriteria("f1").is("liu feng").and(c1).and("f3").is("aaa").toQueryString());
/* 358 */     System.out.println(QueryHelper.buildCriteria("f1").is("liu feng").and("f2").isNull().and("f3").is("aaa").toQueryString());
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\query\criteria\ADFCriteriaImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */