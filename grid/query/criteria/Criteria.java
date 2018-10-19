/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.query.criteria;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.springframework.dao.InvalidDataAccessApiUsageException;
/*     */ import org.springframework.data.geo.Box;
/*     */ import org.springframework.data.geo.Circle;
/*     */ import org.springframework.data.geo.Distance;
/*     */ import org.springframework.data.geo.Point;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Criteria
/*     */   extends Node
/*     */ {
/*     */   public static final String WILDCARD = "*";
/*     */   public static final String CRITERIA_VALUE_SEPERATOR = " ";
/*     */   private Field field;
/*  46 */   private float boost = NaN.0F;
/*     */   
/*  48 */   private Set<Predicate> predicates = new LinkedHashSet();
/*     */   
/*     */ 
/*     */ 
/*     */   public Criteria() {}
/*     */   
/*     */ 
/*     */   public Criteria(Function function)
/*     */   {
/*  57 */     Assert.notNull(function, "Cannot create Critiera for 'null' function.");
/*  58 */     function(function);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Criteria(String fieldname)
/*     */   {
/*  67 */     this(new SimpleField(fieldname));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Criteria(Field field)
/*     */   {
/*  76 */     Assert.notNull(field, "Field for criteria must not be null");
/*  77 */     Assert.hasText(field.getName(), "Field.name for criteria must not be null/empty");
/*     */     
/*  79 */     this.field = field;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Criteria where(String fieldname)
/*     */   {
/*  89 */     return where(new SimpleField(fieldname));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Criteria where(Function function)
/*     */   {
/* 100 */     return new Criteria(function);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Criteria where(Field field)
/*     */   {
/* 110 */     return new Criteria(field);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Criteria is(Object o)
/*     */   {
/* 121 */     if (o == null) {
/* 122 */       return isNull();
/*     */     }
/* 124 */     this.predicates.add(new Predicate(OperationKey.EQUALS, o));
/* 125 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Criteria is(Object... values)
/*     */   {
/* 135 */     return in(values);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Criteria is(Iterable<?> values)
/*     */   {
/* 145 */     return in(values);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Criteria isNull()
/*     */   {
/* 154 */     return between(null, null).not();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Criteria isNotNull()
/*     */   {
/* 163 */     return between(null, null);
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
/*     */   public Criteria contains(String s)
/*     */   {
/* 176 */     assertNoBlankInWildcardedQuery(s, true, true);
/* 177 */     this.predicates.add(new Predicate(OperationKey.CONTAINS, s));
/* 178 */     return this;
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
/*     */   public Criteria contains(String... values)
/*     */   {
/* 191 */     assertValuesPresent((Object[])values);
/* 192 */     return contains(Arrays.asList(values));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Criteria contains(Iterable<String> values)
/*     */   {
/* 204 */     Assert.notNull(values, "Collection must not be null");
/* 205 */     for (String value : values) {
/* 206 */       contains(value);
/*     */     }
/* 208 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Criteria startsWith(String s)
/*     */   {
/* 220 */     assertNoBlankInWildcardedQuery(s, false, true);
/* 221 */     this.predicates.add(new Predicate(OperationKey.STARTS_WITH, s));
/* 222 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Criteria startsWith(String... values)
/*     */   {
/* 234 */     assertValuesPresent((Object[])values);
/* 235 */     return startsWith(Arrays.asList(values));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Criteria startsWith(Iterable<String> values)
/*     */   {
/* 246 */     Assert.notNull(values, "Collection must not be null");
/* 247 */     for (String value : values) {
/* 248 */       startsWith(value);
/*     */     }
/* 250 */     return this;
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
/*     */   public Criteria endsWith(String s)
/*     */   {
/* 263 */     assertNoBlankInWildcardedQuery(s, true, false);
/* 264 */     this.predicates.add(new Predicate(OperationKey.ENDS_WITH, s));
/* 265 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Criteria endsWith(String... values)
/*     */   {
/* 277 */     assertValuesPresent((Object[])values);
/* 278 */     return endsWith(Arrays.asList(values));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Criteria endsWith(Iterable<String> values)
/*     */   {
/* 290 */     Assert.notNull(values, "Collection must not be null");
/* 291 */     for (String value : values) {
/* 292 */       endsWith(value);
/*     */     }
/* 294 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Criteria not()
/*     */   {
/* 304 */     setNegating(true);
/* 305 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Criteria notOperator()
/*     */   {
/* 316 */     Crotch c = new Crotch();
/* 317 */     c.setNegating(true);
/* 318 */     c.add(this);
/*     */     
/* 320 */     return c;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Criteria fuzzy(String s)
/*     */   {
/* 330 */     return fuzzy(s, NaN.0F);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Criteria fuzzy(String s, float levenshteinDistance)
/*     */   {
/* 341 */     if ((!Float.isNaN(levenshteinDistance)) && ((levenshteinDistance < 0.0F) || (levenshteinDistance > 1.0F))) {
/* 342 */       throw new InvalidDataAccessApiUsageException("Levenshtein Distance has to be within its bounds (0.0 - 1.0).");
/*     */     }
/* 344 */     this.predicates.add(new Predicate(OperationKey.FUZZY, new Object[] { s, Float.valueOf(levenshteinDistance) }));
/* 345 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Criteria sloppy(String phrase, int distance)
/*     */   {
/* 356 */     if (distance <= 0) {
/* 357 */       throw new InvalidDataAccessApiUsageException("Slop distance has to be greater than 0.");
/*     */     }
/*     */     
/* 360 */     if (!StringUtils.contains(phrase, " ")) {
/* 361 */       throw new InvalidDataAccessApiUsageException("Phrase must consist of multiple terms, separated with spaces.");
/*     */     }
/*     */     
/* 364 */     this.predicates.add(new Predicate(OperationKey.SLOPPY, new Object[] { phrase, Integer.valueOf(distance) }));
/* 365 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Criteria expression(String s)
/*     */   {
/* 375 */     this.predicates.add(new Predicate(OperationKey.EXPRESSION, s));
/* 376 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Criteria boost(float boost)
/*     */   {
/* 386 */     if (boost < 0.0F) {
/* 387 */       throw new InvalidDataAccessApiUsageException("Boost must not be negative.");
/*     */     }
/* 389 */     this.boost = boost;
/* 390 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Criteria between(Object lowerBound, Object upperBound)
/*     */   {
/* 401 */     return between(lowerBound, upperBound, true, true);
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
/*     */   public Criteria between(Object lowerBound, Object upperBound, boolean includeLowerBound, boolean includeUppderBound)
/*     */   {
/* 414 */     this.predicates.add(new Predicate(OperationKey.BETWEEN, new Object[] { lowerBound, upperBound, Boolean.valueOf(includeLowerBound), 
/* 415 */       Boolean.valueOf(includeUppderBound) }));
/* 416 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Criteria lessThan(Object upperBound)
/*     */   {
/* 426 */     between(null, upperBound, true, false);
/* 427 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Criteria lessThanEqual(Object upperBound)
/*     */   {
/* 437 */     between(null, upperBound);
/* 438 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Criteria greaterThan(Object lowerBound)
/*     */   {
/* 448 */     between(lowerBound, null, false, true);
/* 449 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Criteria greaterThanEqual(Object lowerBound)
/*     */   {
/* 459 */     between(lowerBound, null);
/* 460 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Criteria in(Object... values)
/*     */   {
/* 470 */     assertValuesPresent(values);
/* 471 */     return in(Arrays.asList(values));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Criteria in(Iterable<?> values)
/*     */   {
/* 481 */     Assert.notNull(values, "Collection of 'in' values must not be null");
/* 482 */     for (Object value : values) {
/* 483 */       if ((value instanceof Collection)) {
/* 484 */         in((Collection)value);
/*     */       } else {
/* 486 */         is(value);
/*     */       }
/*     */     }
/* 489 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Criteria within(Point location, Distance distance)
/*     */   {
/* 500 */     Assert.notNull(location);
/* 501 */     assertPositiveDistanceValue(distance);
/* 502 */     this.predicates.add(new Predicate(OperationKey.WITHIN, new Object[] { location, distance != null ? distance : new Distance(0.0D) }));
/*     */     
/* 504 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Criteria within(Circle circle)
/*     */   {
/* 516 */     Assert.notNull(circle, "Circle for 'within' must not be 'null'.");
/* 517 */     return within(circle.getCenter(), circle.getRadius());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Criteria near(Box box)
/*     */   {
/* 527 */     this.predicates.add(new Predicate(OperationKey.NEAR, new Object[] { box }));
/* 528 */     return this;
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
/*     */   public Criteria near(Point location, Distance distance)
/*     */   {
/* 542 */     Assert.notNull(location, "Location must not be 'null' for near criteria.");
/* 543 */     assertPositiveDistanceValue(distance);
/*     */     
/* 545 */     this.predicates.add(new Predicate(OperationKey.NEAR, new Object[] { location, distance != null ? distance : new Distance(0.0D) }));
/*     */     
/* 547 */     return this;
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
/*     */   public Criteria near(Circle circle)
/*     */   {
/* 560 */     Assert.notNull(circle, "Circle for 'near' must not be 'null'.");
/* 561 */     return near(circle.getCenter(), circle.getRadius());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Criteria function(Function function)
/*     */   {
/* 573 */     Assert.notNull(function, "Cannot add 'null' function to criteria.");
/* 574 */     this.predicates.add(new Predicate(OperationKey.FUNCTION, function));
/* 575 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Field getField()
/*     */   {
/* 584 */     return this.field;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isNegating()
/*     */   {
/* 591 */     return super.isNegating();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public float getBoost()
/*     */   {
/* 600 */     return this.boost;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Set<Predicate> getPredicates()
/*     */   {
/* 607 */     return Collections.unmodifiableSet(this.predicates);
/*     */   }
/*     */   
/*     */   private void assertPositiveDistanceValue(Distance distance) {
/* 611 */     if ((distance != null) && (distance.getValue() < 0.0D)) {
/* 612 */       throw new InvalidDataAccessApiUsageException("distance must not be negative.");
/*     */     }
/*     */   }
/*     */   
/*     */   private void assertNoBlankInWildcardedQuery(String searchString, boolean leadingWildcard, boolean trailingWildcard) {
/* 617 */     if (StringUtils.contains(searchString, " ")) {
/* 618 */       throw new InvalidDataAccessApiUsageException("Cannot constructQuery '" + (leadingWildcard ? "*" : "") + "\"" + searchString + "\"" + (trailingWildcard ? "*" : "") + "'. Use epxression or mulitple clauses instead.");
/*     */     }
/*     */   }
/*     */   
/*     */   private void assertValuesPresent(Object... values)
/*     */   {
/* 624 */     if ((values.length == 0) || ((values.length > 1) && ((values[1] instanceof Collection))))
/*     */     {
/* 626 */       throw new InvalidDataAccessApiUsageException("At least one element " + (values.length > 0 ? "of argument of type " + values[1].getClass().getName() : "") + " has to be present.");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 634 */     StringBuilder sb = new StringBuilder(isOr() ? "OR " : "AND ");
/* 635 */     sb.append(isNegating() ? "!" : "");
/* 636 */     sb.append(this.field != null ? this.field.getName() : "");
/*     */     
/* 638 */     if (this.predicates.size() > 1) {
/* 639 */       sb.append('(');
/*     */     }
/* 641 */     for (Predicate ce : this.predicates) {
/* 642 */       sb.append(ce.toString());
/*     */     }
/* 644 */     if (this.predicates.size() > 1) {
/* 645 */       sb.append(')');
/*     */     }
/* 647 */     sb.append(' ');
/* 648 */     return sb.toString();
/*     */   }
/*     */   
/*     */ 
/*     */   public static enum OperationKey
/*     */   {
/* 654 */     EQUALS("$equals"),  CONTAINS("$contains"),  STARTS_WITH("$startsWith"),  ENDS_WITH("$endsWith"),  EXPRESSION("$expression"), 
/* 655 */     BETWEEN("$between"),  NEAR("$near"),  WITHIN("$within"),  FUZZY("$fuzzy"),  SLOPPY("$sloppy"),  FUNCTION("$function");
/*     */     
/*     */     private final String key;
/*     */     
/*     */     private OperationKey(String key)
/*     */     {
/* 661 */       this.key = key;
/*     */     }
/*     */     
/*     */     public String getKey() {
/* 665 */       return this.key;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static class Predicate
/*     */   {
/*     */     private String key;
/*     */     
/*     */ 
/*     */     private Object value;
/*     */     
/*     */ 
/*     */ 
/*     */     public Predicate(OperationKey key, Object value)
/*     */     {
/* 682 */       this(key.getKey(), value);
/*     */     }
/*     */     
/*     */     public Predicate(String key, Object value) {
/* 686 */       this.key = key;
/* 687 */       this.value = value;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public String getKey()
/*     */     {
/* 694 */       return this.key;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public void setKey(String key)
/*     */     {
/* 703 */       this.key = key;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public Object getValue()
/*     */     {
/* 710 */       return this.value;
/*     */     }
/*     */     
/*     */     public void setValue(Object value) {
/* 714 */       this.value = value;
/*     */     }
/*     */     
/*     */     public String toString()
/*     */     {
/* 719 */       return this.key + ":" + this.value;
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
/*     */   public Criteria connect()
/*     */   {
/* 733 */     Crotch c = new Crotch();
/* 734 */     c.add(this);
/* 735 */     return c;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Crotch and(Node node)
/*     */   {
/* 742 */     if (!(node instanceof Criteria)) {
/* 743 */       throw new IllegalArgumentException("Can only add instances of Criteria");
/*     */     }
/*     */     
/* 746 */     Crotch crotch = new Crotch();
/* 747 */     crotch.setParent(getParent());
/* 748 */     crotch.add(this);
/* 749 */     crotch.add((Criteria)node);
/* 750 */     return crotch;
/*     */   }
/*     */   
/*     */ 
/*     */   public Crotch and(String fieldname)
/*     */   {
/* 756 */     Criteria node = new Criteria(fieldname);
/* 757 */     return and(node);
/*     */   }
/*     */   
/*     */ 
/*     */   public Crotch or(Node node)
/*     */   {
/* 763 */     if (!(node instanceof Criteria)) {
/* 764 */       throw new IllegalArgumentException("Can only add instances of Criteria");
/*     */     }
/*     */     
/* 767 */     node.setPartIsOr(true);
/*     */     
/* 769 */     Crotch crotch = new Crotch();
/* 770 */     crotch.setParent(getParent());
/* 771 */     crotch.add(this);
/* 772 */     crotch.add((Criteria)node);
/* 773 */     return crotch;
/*     */   }
/*     */   
/*     */   public Crotch or(String fieldname)
/*     */   {
/* 778 */     Criteria node = new Criteria(fieldname);
/* 779 */     node.setPartIsOr(true);
/* 780 */     return or(node);
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\query\criteria\Criteria.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */