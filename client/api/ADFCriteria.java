package com.jnj.adf.dataservice.adfcoreignite.client.api;

public abstract interface ADFCriteria
{
  public abstract ADFCriteria is(Object paramObject);
  
  public abstract ADFCriteria in(Object... paramVarArgs);
  
  public abstract ADFCriteria and(String paramString);
  
  public abstract ADFCriteria and(ADFCriteria paramADFCriteria);
  
  public abstract ADFCriteria or(ADFCriteria paramADFCriteria);
  
  public abstract ADFCriteria not();
  
  public abstract ADFCriteria isNull();
  
  public abstract ADFCriteria missingFiled();
  
  public abstract ADFCriteria isNotNull();
  
  public abstract ADFCriteria startsWith(String paramString);
  
  public abstract ADFCriteria startsWith(String... paramVarArgs);
  
  public abstract ADFCriteria endsWith(String paramString);
  
  public abstract ADFCriteria endsWith(String... paramVarArgs);
  
  public abstract ADFCriteria contains(String paramString);
  
  public abstract ADFCriteria contains(String... paramVarArgs);
  
  public abstract ADFCriteria fuzzy(String paramString);
  
  public abstract ADFCriteria fuzzy(String paramString, float paramFloat);
  
  public abstract ADFCriteria lessThan(Object paramObject);
  
  public abstract ADFCriteria lessThanEqual(Object paramObject);
  
  public abstract ADFCriteria greaterThan(Object paramObject);
  
  public abstract ADFCriteria greaterThanEqual(Object paramObject);
  
  public abstract ADFCriteria between(Object paramObject1, Object paramObject2);
  
  public abstract ADFCriteria keywords(String paramString);
  
  public abstract String toQueryString();
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\client\api\ADFCriteria.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */