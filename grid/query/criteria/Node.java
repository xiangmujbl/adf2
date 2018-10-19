/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.query.criteria;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
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
/*     */ 
/*     */ 
/*     */ public abstract class Node
/*     */ {
/*     */   private Node parent;
/*  33 */   private boolean isOr = false;
/*  34 */   private boolean negating = false;
/*     */   
/*     */ 
/*     */ 
/*     */   protected void setParent(Node parent)
/*     */   {
/*  40 */     this.parent = parent;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPartIsOr(boolean isOr)
/*     */   {
/*  49 */     this.isOr = isOr;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isRoot()
/*     */   {
/*  56 */     return this.parent == null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isOr()
/*     */   {
/*  63 */     return this.isOr;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Node getParent()
/*     */   {
/*  72 */     return this.parent;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean hasSiblings()
/*     */   {
/*  79 */     return !getSiblings().isEmpty();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Collection<Criteria> getSiblings()
/*     */   {
/*  86 */     return Collections.emptyList();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isNegating()
/*     */   {
/*  94 */     return this.negating;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void setNegating(boolean negating)
/*     */   {
/* 102 */     this.negating = negating;
/*     */   }
/*     */   
/*     */   public abstract <T extends Node> T and(Node paramNode);
/*     */   
/*     */   public abstract <T extends Node> T and(String paramString);
/*     */   
/*     */   public abstract <T extends Node> T or(Node paramNode);
/*     */   
/*     */   public abstract <T extends Node> T or(String paramString);
/*     */   
/*     */   public abstract Node is(Object paramObject);
/*     */   
/*     */   public abstract Node is(Object... paramVarArgs);
/*     */   
/*     */   public abstract Node is(Iterable<?> paramIterable);
/*     */   
/*     */   public abstract Node isNull();
/*     */   
/*     */   public abstract Node isNotNull();
/*     */   
/*     */   public abstract Node contains(String paramString);
/*     */   
/*     */   public abstract Node contains(String... paramVarArgs);
/*     */   
/*     */   public abstract Node contains(Iterable<String> paramIterable);
/*     */   
/*     */   public abstract Node startsWith(String paramString);
/*     */   
/*     */   public abstract Node startsWith(String... paramVarArgs);
/*     */   
/*     */   public abstract Node startsWith(Iterable<String> paramIterable);
/*     */   
/*     */   public abstract Node endsWith(String paramString);
/*     */   
/*     */   public abstract Node endsWith(String... paramVarArgs);
/*     */   
/*     */   public abstract Node endsWith(Iterable<String> paramIterable);
/*     */   
/*     */   public abstract Node not();
/*     */   
/*     */   public abstract Node fuzzy(String paramString);
/*     */   
/*     */   public abstract Node fuzzy(String paramString, float paramFloat);
/*     */   
/*     */   public abstract Node sloppy(String paramString, int paramInt);
/*     */   
/*     */   public abstract Node expression(String paramString);
/*     */   
/*     */   public abstract Node boost(float paramFloat);
/*     */   
/*     */   public abstract Node between(Object paramObject1, Object paramObject2);
/*     */   
/*     */   public abstract Node between(Object paramObject1, Object paramObject2, boolean paramBoolean1, boolean paramBoolean2);
/*     */   
/*     */   public abstract Node lessThan(Object paramObject);
/*     */   
/*     */   public abstract Node lessThanEqual(Object paramObject);
/*     */   
/*     */   public abstract Node greaterThan(Object paramObject);
/*     */   
/*     */   public abstract Node greaterThanEqual(Object paramObject);
/*     */   
/*     */   public abstract Node in(Object... paramVarArgs);
/*     */   
/*     */   public abstract Node in(Iterable<?> paramIterable);
/*     */   
/*     */   public abstract Node within(Point paramPoint, Distance paramDistance);
/*     */   
/*     */   public abstract Node near(Box paramBox);
/*     */   
/*     */   public abstract Node near(Point paramPoint, Distance paramDistance);
/*     */   
/*     */   public abstract Node function(Function paramFunction);
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\query\criteria\Node.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */