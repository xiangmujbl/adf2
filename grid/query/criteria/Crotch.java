/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.query.criteria;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.springframework.data.geo.Box;
/*     */ import org.springframework.data.geo.Distance;
/*     */ import org.springframework.data.geo.Point;
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
/*     */ public class Crotch
/*     */   extends Criteria
/*     */ {
/*  33 */   private List<Criteria> siblings = new ArrayList();
/*  34 */   private Node mostRecentSibling = null;
/*     */   
/*     */ 
/*     */ 
/*     */   public Field getField()
/*     */   {
/*  40 */     if ((this.mostRecentSibling instanceof Criteria)) {
/*  41 */       return ((Criteria)this.mostRecentSibling).getField();
/*     */     }
/*  43 */     return null;
/*     */   }
/*     */   
/*     */   public Crotch is(Object o)
/*     */   {
/*  48 */     this.mostRecentSibling.is(o);
/*  49 */     return this;
/*     */   }
/*     */   
/*     */   public Crotch boost(float boost)
/*     */   {
/*  54 */     this.mostRecentSibling.boost(boost);
/*  55 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */   public Crotch not()
/*     */   {
/*  61 */     this.mostRecentSibling.not();
/*  62 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */   public Crotch notOperator()
/*     */   {
/*  68 */     if (isRoot()) {
/*  69 */       setNegating(true);
/*     */     } else {
/*  71 */       super.notOperator();
/*     */     }
/*  73 */     return this;
/*     */   }
/*     */   
/*     */   public Crotch endsWith(String postfix)
/*     */   {
/*  78 */     this.mostRecentSibling.endsWith(postfix);
/*  79 */     return this;
/*     */   }
/*     */   
/*     */   public Crotch startsWith(String prefix)
/*     */   {
/*  84 */     this.mostRecentSibling.startsWith(prefix);
/*  85 */     return this;
/*     */   }
/*     */   
/*     */   public Crotch contains(String value)
/*     */   {
/*  90 */     this.mostRecentSibling.contains(value);
/*  91 */     return this;
/*     */   }
/*     */   
/*     */   public Crotch is(Object... values)
/*     */   {
/*  96 */     this.mostRecentSibling.is(values);
/*  97 */     return this;
/*     */   }
/*     */   
/*     */   public Crotch is(Iterable<?> values)
/*     */   {
/* 102 */     this.mostRecentSibling.is(values);
/* 103 */     return this;
/*     */   }
/*     */   
/*     */   public Crotch isNull()
/*     */   {
/* 108 */     this.mostRecentSibling.isNull();
/* 109 */     return this;
/*     */   }
/*     */   
/*     */   public Crotch isNotNull()
/*     */   {
/* 114 */     this.mostRecentSibling.isNotNull();
/* 115 */     return this;
/*     */   }
/*     */   
/*     */   public Crotch contains(String... values)
/*     */   {
/* 120 */     this.mostRecentSibling.contains(values);
/* 121 */     return this;
/*     */   }
/*     */   
/*     */   public Crotch contains(Iterable<String> values)
/*     */   {
/* 126 */     this.mostRecentSibling.contains(values);
/* 127 */     return this;
/*     */   }
/*     */   
/*     */   public Crotch startsWith(String... values)
/*     */   {
/* 132 */     this.mostRecentSibling.startsWith(values);
/* 133 */     return this;
/*     */   }
/*     */   
/*     */   public Crotch startsWith(Iterable<String> values)
/*     */   {
/* 138 */     this.mostRecentSibling.startsWith(values);
/* 139 */     return this;
/*     */   }
/*     */   
/*     */   public Crotch endsWith(String... values)
/*     */   {
/* 144 */     this.mostRecentSibling.endsWith(values);
/* 145 */     return this;
/*     */   }
/*     */   
/*     */   public Crotch endsWith(Iterable<String> values)
/*     */   {
/* 150 */     this.mostRecentSibling.endsWith(values);
/* 151 */     return this;
/*     */   }
/*     */   
/*     */   public Crotch fuzzy(String value)
/*     */   {
/* 156 */     this.mostRecentSibling.fuzzy(value);
/* 157 */     return this;
/*     */   }
/*     */   
/*     */   public Crotch fuzzy(String values, float levenshteinDistance)
/*     */   {
/* 162 */     this.mostRecentSibling.fuzzy(values, levenshteinDistance);
/* 163 */     return this;
/*     */   }
/*     */   
/*     */   public Crotch sloppy(String phrase, int distance)
/*     */   {
/* 168 */     this.mostRecentSibling.sloppy(phrase, distance);
/* 169 */     return this;
/*     */   }
/*     */   
/*     */   public Crotch expression(String nativeSolrQueryExpression)
/*     */   {
/* 174 */     this.mostRecentSibling.expression(nativeSolrQueryExpression);
/* 175 */     return this;
/*     */   }
/*     */   
/*     */   public Crotch between(Object lowerBound, Object upperBound)
/*     */   {
/* 180 */     this.mostRecentSibling.between(lowerBound, upperBound);
/* 181 */     return this;
/*     */   }
/*     */   
/*     */   public Crotch between(Object lowerBound, Object upperBound, boolean includeLowerBound, boolean includeUpperBound)
/*     */   {
/* 186 */     this.mostRecentSibling.between(lowerBound, upperBound, includeLowerBound, includeUpperBound);
/* 187 */     return this;
/*     */   }
/*     */   
/*     */   public Crotch lessThan(Object upperBound)
/*     */   {
/* 192 */     this.mostRecentSibling.lessThan(upperBound);
/* 193 */     return this;
/*     */   }
/*     */   
/*     */   public Crotch lessThanEqual(Object upperBound)
/*     */   {
/* 198 */     this.mostRecentSibling.lessThanEqual(upperBound);
/* 199 */     return this;
/*     */   }
/*     */   
/*     */   public Crotch greaterThan(Object lowerBound)
/*     */   {
/* 204 */     this.mostRecentSibling.greaterThan(lowerBound);
/* 205 */     return this;
/*     */   }
/*     */   
/*     */   public Crotch greaterThanEqual(Object lowerBound)
/*     */   {
/* 210 */     this.mostRecentSibling.greaterThanEqual(lowerBound);
/* 211 */     return this;
/*     */   }
/*     */   
/*     */   public Crotch in(Object... values)
/*     */   {
/* 216 */     this.mostRecentSibling.in(values);
/* 217 */     return this;
/*     */   }
/*     */   
/*     */   public Crotch in(Iterable<?> values)
/*     */   {
/* 222 */     this.mostRecentSibling.in(values);
/* 223 */     return this;
/*     */   }
/*     */   
/*     */   public Crotch within(Point location, Distance distance)
/*     */   {
/* 228 */     this.mostRecentSibling.within(location, distance);
/* 229 */     return this;
/*     */   }
/*     */   
/*     */   public Crotch near(Box box)
/*     */   {
/* 234 */     this.mostRecentSibling.near(box);
/* 235 */     return this;
/*     */   }
/*     */   
/*     */   public Crotch near(Point location, Distance distance)
/*     */   {
/* 240 */     this.mostRecentSibling.near(location, distance);
/* 241 */     return this;
/*     */   }
/*     */   
/*     */   public Crotch function(Function function)
/*     */   {
/* 246 */     this.mostRecentSibling.function(function);
/* 247 */     return this;
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 252 */     StringBuilder sb = new StringBuilder(isOr() ? " OR " : " AND ");
/* 253 */     sb.append('(');
/* 254 */     boolean first = true;
/* 255 */     for (Node node : this.siblings) {
/* 256 */       String s = node.toString();
/* 257 */       if (first) {
/* 258 */         s = s.replaceFirst("OR", "").replaceFirst("AND", "");
/* 259 */         first = false;
/*     */       }
/* 261 */       sb.append(s);
/*     */     }
/* 263 */     sb.append(')');
/* 264 */     return sb.toString();
/*     */   }
/*     */   
/*     */ 
/*     */   void add(Node node)
/*     */   {
/* 270 */     if (!(node instanceof Criteria)) {
/* 271 */       throw new IllegalArgumentException("Can only add instances of Criteria");
/*     */     }
/*     */     
/* 274 */     node.setParent(this);
/* 275 */     this.siblings.add((Criteria)node);
/* 276 */     this.mostRecentSibling = node;
/*     */   }
/*     */   
/*     */   public Collection<Criteria> getSiblings()
/*     */   {
/* 281 */     return Collections.unmodifiableCollection(this.siblings);
/*     */   }
/*     */   
/*     */   public Crotch and(Node part)
/*     */   {
/* 286 */     add(part);
/* 287 */     return this;
/*     */   }
/*     */   
/*     */   public Crotch or(Node part)
/*     */   {
/* 292 */     part.setPartIsOr(true);
/* 293 */     add(part);
/* 294 */     return this;
/*     */   }
/*     */   
/*     */   public Crotch and(String fieldname)
/*     */   {
/* 299 */     if ((this.mostRecentSibling instanceof Crotch)) {
/* 300 */       ((Crotch)this.mostRecentSibling).add(new Criteria(fieldname));
/*     */     } else {
/* 302 */       and(new Criteria(fieldname));
/*     */     }
/* 304 */     return this;
/*     */   }
/*     */   
/*     */   public Crotch or(String fieldname)
/*     */   {
/* 309 */     Criteria criteria = new Criteria(fieldname);
/* 310 */     criteria.setPartIsOr(true);
/*     */     
/* 312 */     if ((this.mostRecentSibling instanceof Crotch)) {
/* 313 */       ((Crotch)this.mostRecentSibling).add(criteria);
/*     */     } else {
/* 315 */       or(new Criteria(fieldname));
/*     */     }
/* 317 */     return this;
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\query\criteria\Crotch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */