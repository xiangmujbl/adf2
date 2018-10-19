package com.jnj.adf.dataservice.adfcoreignite.grid.query.criteria;

public abstract interface PredicateProcessor
{
  public abstract boolean canProcess(Criteria.Predicate paramPredicate);
  
  public abstract Object process(Criteria.Predicate paramPredicate, Field paramField);
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\query\criteria\PredicateProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */