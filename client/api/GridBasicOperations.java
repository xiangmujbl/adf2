package com.jnj.adf.dataservice.adfcoreignite.client.api;

import com.jnj.adf.grid.support.context.ADFSession;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface GridBasicOperations
{
  @ADFSession
  public abstract String get(String paramString);
  
  @ADFSession
  public abstract Map.Entry<String, String> getEntry(String paramString);
  
  @ADFSession
  public abstract <V> V get(String paramString, Class<V> paramClass);
  
  @ADFSession
  public abstract <V> Map.Entry<String, V> getEntry(String paramString, Class<V> paramClass);
  
  @ADFSession
  public abstract Map<String, String> getAll(Collection<String> paramCollection);
  
  @ADFSession
  public abstract <V> Map<String, V> getAll(Collection<String> paramCollection, Class<V> paramClass);
  
  @ADFSession
  public abstract <V> V put(String paramString, V paramV);
  
  @ADFSession
  public abstract <V> V put(String paramString, V paramV, Map<String, Object> paramMap);
  
  @ADFSession
  public abstract <V> void putAll(Map<String, ? extends V> paramMap);
  
  @ADFSession
  public abstract <V> void putAll(Map<String, ? extends V> paramMap, Map<String, Object> paramMap1);
  
  @ADFSession
  public abstract boolean remove(String paramString);
  
  @ADFSession
  public abstract void removeAll();
  
  @ADFSession
  public abstract boolean softRemove(String paramString);
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\client\api\GridBasicOperations.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */