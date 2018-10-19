/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.query.criteria;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ 
/*     */ public class DefaultQueryParser
/*     */ {
/*     */   protected static final String CRITERIA_VALUE_SEPERATOR = " ";
/*     */   protected static final String DELIMINATOR = ":";
/*     */   protected static final String NOT = "*:* -";
/*     */   protected static final String BOOST = "^";
/*     */   private final PredicateProcessor defaultProcessor;
/*     */   private final List<PredicateProcessor> critieraEntryProcessors;
/*     */   
/*     */   public DefaultQueryParser()
/*     */   {
/*  19 */     this.defaultProcessor = new DefaultProcessor();
/*  20 */     this.critieraEntryProcessors = new java.util.ArrayList();
/*     */     
/*  22 */     this.critieraEntryProcessors.add(new ExpressionProcessor());
/*  23 */     this.critieraEntryProcessors.add(new BetweenProcessor());
/*  24 */     this.critieraEntryProcessors.add(new FuzzyProcessor());
/*  25 */     this.critieraEntryProcessors.add(new SloppyProcessor());
/*  26 */     this.critieraEntryProcessors.add(new WildcardProcessor());
/*     */   }
/*     */   
/*     */   public String createQueryStringFromNode(Node node) {
/*  30 */     return createQueryStringFromNode(node, 0);
/*     */   }
/*     */   
/*     */   public String createQueryStringFromNode(Node node, int position)
/*     */   {
/*  35 */     StringBuilder query = new StringBuilder();
/*  36 */     if (position > 0) {
/*  37 */       query.append(node.isOr() ? " OR " : " AND ");
/*     */     }
/*     */     
/*  40 */     if (node.hasSiblings()) {
/*  41 */       if (node.isNegating()) {
/*  42 */         query.append("*:* -");
/*     */       }
/*  44 */       if ((!node.isRoot()) || ((node.isRoot()) && (node.isNegating()))) {
/*  45 */         query.append('(');
/*     */       }
/*     */       
/*  48 */       int i = 0;
/*  49 */       for (Node nested : node.getSiblings()) {
/*  50 */         query.append(createQueryStringFromNode(nested, i++));
/*     */       }
/*     */       
/*  53 */       if ((!node.isRoot()) || ((node.isRoot()) && (node.isNegating()))) {
/*  54 */         query.append(')');
/*     */       }
/*     */     } else {
/*  57 */       query.append(createQueryFragmentForCriteria((Criteria)node));
/*     */     }
/*  59 */     return query.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String createQueryFragmentForCriteria(Criteria part)
/*     */   {
/*  69 */     Criteria criteria = part;
/*  70 */     StringBuilder queryFragment = new StringBuilder();
/*  71 */     boolean singeEntryCriteria = criteria.getPredicates().size() == 1;
/*     */     
/*  73 */     String fieldName = getNullsafeFieldName(criteria.getField());
/*  74 */     if (criteria.isNegating()) {
/*  75 */       fieldName = "(*:* -" + fieldName;
/*     */     }
/*  77 */     if ((!StringUtils.isEmpty(fieldName)) && (!containsFunctionCriteria(criteria.getPredicates()))) {
/*  78 */       queryFragment.append(fieldName);
/*  79 */       queryFragment.append(":");
/*     */     }
/*     */     
/*     */ 
/*  83 */     if (criteria.getPredicates().isEmpty()) {
/*  84 */       queryFragment.append("[* TO *]");
/*  85 */       return queryFragment.toString();
/*     */     }
/*     */     
/*  88 */     if (!singeEntryCriteria) {
/*  89 */       queryFragment.append("(");
/*     */     }
/*     */     
/*  92 */     CriteriaQueryStringValueProvider valueProvider = new CriteriaQueryStringValueProvider(criteria);
/*  93 */     while (valueProvider.hasNext()) {
/*  94 */       queryFragment.append(valueProvider.next());
/*  95 */       if (valueProvider.hasNext()) {
/*  96 */         queryFragment.append(" ");
/*     */       }
/*     */     }
/*     */     
/* 100 */     if (!singeEntryCriteria) {
/* 101 */       queryFragment.append(")");
/*     */     }
/* 103 */     if (!Float.isNaN(criteria.getBoost())) {
/* 104 */       queryFragment.append("^" + criteria.getBoost());
/*     */     }
/*     */     
/* 107 */     if (criteria.isNegating()) {
/* 108 */       queryFragment.append(")");
/*     */     }
/*     */     
/* 111 */     return queryFragment.toString();
/*     */   }
/*     */   
/*     */   private String getNullsafeFieldName(Field field) {
/* 115 */     if ((field == null) || (field.getName() == null)) {
/* 116 */       return "";
/*     */     }
/* 118 */     return field.getName();
/*     */   }
/*     */   
/*     */   private boolean containsFunctionCriteria(Set<Criteria.Predicate> chainedCriterias) {
/* 122 */     for (Criteria.Predicate entry : chainedCriterias) {
/* 123 */       if (StringUtils.equals(Criteria.OperationKey.WITHIN.getKey(), entry.getKey()))
/* 124 */         return true;
/* 125 */       if (StringUtils.equals(Criteria.OperationKey.NEAR.getKey(), entry.getKey())) {
/* 126 */         return true;
/*     */       }
/*     */     }
/* 129 */     return false;
/*     */   }
/*     */   
/*     */   private PredicateProcessor findMatchingProcessor(Criteria.Predicate predicate) {
/* 133 */     for (PredicateProcessor processor : this.critieraEntryProcessors) {
/* 134 */       if (processor.canProcess(predicate)) {
/* 135 */         return processor;
/*     */       }
/*     */     }
/*     */     
/* 139 */     return this.defaultProcessor;
/*     */   }
/*     */   
/*     */ 
/*     */   class CriteriaQueryStringValueProvider
/*     */     implements Iterator<String>
/*     */   {
/*     */     private final Criteria criteria;
/*     */     private Iterator<Criteria.Predicate> delegate;
/*     */     
/*     */     CriteriaQueryStringValueProvider(Criteria criteria)
/*     */     {
/* 151 */       org.springframework.util.Assert.notNull(criteria, "Unable to provide values for 'null' criteria");
/*     */       
/* 153 */       this.criteria = criteria;
/* 154 */       this.delegate = criteria.getPredicates().iterator();
/*     */     }
/*     */     
/*     */     private <T> T getPredicateValue(Criteria.Predicate predicate)
/*     */     {
/* 159 */       PredicateProcessor processor = DefaultQueryParser.this.findMatchingProcessor(predicate);
/* 160 */       return (T)processor.process(predicate, this.criteria.getField());
/*     */     }
/*     */     
/*     */     public boolean hasNext()
/*     */     {
/* 165 */       return this.delegate.hasNext();
/*     */     }
/*     */     
/*     */     public String next()
/*     */     {
/* 170 */       Object o = getPredicateValue((Criteria.Predicate)this.delegate.next());
/* 171 */       String s = o != null ? o.toString() : null;
/* 172 */       return s;
/*     */     }
/*     */     
/*     */     public void remove()
/*     */     {
/* 177 */       this.delegate.remove();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   abstract class BasePredicateProcessor
/*     */     implements PredicateProcessor
/*     */   {
/*     */     protected static final String DOUBLEQUOTE = "\"";
/*     */     
/*     */ 
/*     */     private static final String ESCAPE_WHITE_SPACE = "$$$$";
/*     */     
/*     */ 
/* 193 */     protected final String[] RESERVED_CHARS = { "\"", "+", "-", "&&", "||", "!", "(", ")", "{", "}", "[", "]", "^", "~", "*", "?", ":", "\\", "/", "$$$$" };
/*     */     
/* 195 */     protected String[] RESERVED_CHARS_REPLACEMENT = { "\\\"", "\\+", "\\-", "\\&\\&", "\\|\\|", "\\!", "\\(", "\\)", "\\{", "\\}", "\\[", "\\]", "\\^", "\\~", "\\*", "\\?", "\\:", "\\\\", "\\/", "\\ " };
/*     */     
/*     */     BasePredicateProcessor() {}
/*     */     
/*     */     public Object process(Criteria.Predicate predicate, Field field) {
/* 200 */       if ((predicate == null) || (predicate.getValue() == null)) {
/* 201 */         return null;
/*     */       }
/* 203 */       return doProcess(predicate, field);
/*     */     }
/*     */     
/*     */     protected Object filterCriteriaValue(Object criteriaValue) {
/* 207 */       if (!(criteriaValue instanceof String)) {
/* 208 */         return criteriaValue;
/*     */       }
/* 210 */       String value = escapeCriteriaValue((String)criteriaValue);
/* 211 */       return value;
/*     */     }
/*     */     
/*     */     private String escapeCriteriaValue(String criteriaValue) {
/* 215 */       return StringUtils.replaceEach(criteriaValue, this.RESERVED_CHARS, this.RESERVED_CHARS_REPLACEMENT);
/*     */     }
/*     */     
/*     */ 
/*     */     protected abstract Object doProcess(Criteria.Predicate paramPredicate, Field paramField);
/*     */   }
/*     */   
/*     */   class DefaultProcessor
/*     */     extends BasePredicateProcessor
/*     */   {
/*     */     DefaultProcessor()
/*     */     {
/* 227 */       super();
/*     */     }
/*     */     
/*     */     public boolean canProcess(Criteria.Predicate predicate) {
/* 231 */       return true;
/*     */     }
/*     */     
/*     */     public Object doProcess(Criteria.Predicate predicate, Field field)
/*     */     {
/* 236 */       return filterCriteriaValue(predicate.getValue());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   class ExpressionProcessor
/*     */     extends BasePredicateProcessor
/*     */   {
/*     */     ExpressionProcessor()
/*     */     {
/* 246 */       super();
/*     */     }
/*     */     
/*     */     public boolean canProcess(Criteria.Predicate predicate) {
/* 250 */       return Criteria.OperationKey.EXPRESSION.getKey().equals(predicate.getKey());
/*     */     }
/*     */     
/*     */     public Object doProcess(Criteria.Predicate predicate, Field field)
/*     */     {
/* 255 */       return predicate.getValue().toString();
/*     */     }
/*     */   }
/*     */   
/*     */   class BetweenProcessor extends BasePredicateProcessor
/*     */   {
/*     */     private static final String RANGE_OPERATOR = " TO ";
/*     */     
/*     */     BetweenProcessor()
/*     */     {
/* 265 */       super();
/*     */     }
/*     */     
/*     */ 
/*     */     public boolean canProcess(Criteria.Predicate predicate)
/*     */     {
/* 271 */       return Criteria.OperationKey.BETWEEN.getKey().equals(predicate.getKey());
/*     */     }
/*     */     
/*     */     public Object doProcess(Criteria.Predicate predicate, Field field)
/*     */     {
/* 276 */       Object[] args = (Object[])predicate.getValue();
/* 277 */       String rangeFragment = ((Boolean)args[2]).booleanValue() ? "[" : "{";
/* 278 */       rangeFragment = rangeFragment + createRangeFragment(args[0], args[1]);
/* 279 */       rangeFragment = rangeFragment + (((Boolean)args[3]).booleanValue() ? "]" : "}");
/* 280 */       return rangeFragment;
/*     */     }
/*     */     
/*     */     protected String createRangeFragment(Object rangeStart, Object rangeEnd) {
/* 284 */       String rangeFragment = "";
/* 285 */       rangeFragment = rangeFragment + (rangeStart != null ? filterCriteriaValue(rangeStart) : "*");
/* 286 */       rangeFragment = rangeFragment + " TO ";
/* 287 */       rangeFragment = rangeFragment + (rangeEnd != null ? filterCriteriaValue(rangeEnd) : "*");
/* 288 */       return rangeFragment;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   class FuzzyProcessor
/*     */     extends BasePredicateProcessor
/*     */   {
/*     */     FuzzyProcessor()
/*     */     {
/* 298 */       super();
/*     */     }
/*     */     
/*     */     public boolean canProcess(Criteria.Predicate predicate) {
/* 302 */       return Criteria.OperationKey.FUZZY.getKey().equals(predicate.getKey());
/*     */     }
/*     */     
/*     */     protected Object doProcess(Criteria.Predicate predicate, Field field)
/*     */     {
/* 307 */       Object[] args = (Object[])predicate.getValue();
/* 308 */       Float distance = (Float)args[1];
/* 309 */       return filterCriteriaValue(args[0]) + "~" + (distance.isNaN() ? "" : distance);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   class SloppyProcessor
/*     */     extends BasePredicateProcessor
/*     */   {
/*     */     SloppyProcessor()
/*     */     {
/* 319 */       super();
/*     */     }
/*     */     
/*     */     public boolean canProcess(Criteria.Predicate predicate) {
/* 323 */       return Criteria.OperationKey.SLOPPY.getKey().equals(predicate.getKey());
/*     */     }
/*     */     
/*     */     protected Object doProcess(Criteria.Predicate predicate, Field field)
/*     */     {
/* 328 */       Object[] args = (Object[])predicate.getValue();
/* 329 */       Integer distance = (Integer)args[1];
/* 330 */       return filterCriteriaValue(args[0]) + "~" + distance;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   class WildcardProcessor
/*     */     extends BasePredicateProcessor
/*     */   {
/*     */     WildcardProcessor()
/*     */     {
/* 341 */       super();
/*     */     }
/*     */     
/*     */     public boolean canProcess(Criteria.Predicate predicate) {
/* 345 */       return (Criteria.OperationKey.CONTAINS.getKey().equals(predicate.getKey())) || 
/* 346 */         (Criteria.OperationKey.STARTS_WITH.getKey().equals(predicate.getKey())) || 
/* 347 */         (Criteria.OperationKey.ENDS_WITH.getKey().equals(predicate.getKey()));
/*     */     }
/*     */     
/*     */     protected Object doProcess(Criteria.Predicate predicate, Field field)
/*     */     {
/* 352 */       Object filteredValue = filterCriteriaValue(predicate.getValue());
/* 353 */       if (Criteria.OperationKey.CONTAINS.getKey().equals(predicate.getKey()))
/* 354 */         return "*" + filteredValue + "*";
/* 355 */       if (Criteria.OperationKey.STARTS_WITH.getKey().equals(predicate.getKey()))
/* 356 */         return filteredValue + "*";
/* 357 */       if (Criteria.OperationKey.ENDS_WITH.getKey().equals(predicate.getKey())) {
/* 358 */         return "*" + filteredValue;
/*     */       }
/* 360 */       return filteredValue;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\query\criteria\DefaultQueryParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */