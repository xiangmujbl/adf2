package com.jnj.adf.dataservice.adfcoreignite.grid.query.criteria;

public abstract interface Function
{
  public abstract String getOperation();
  
  public abstract Iterable<?> getArguments();
  
  public abstract boolean hasArguments();
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\query\criteria\Function.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */