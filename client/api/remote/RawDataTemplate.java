package com.jnj.adf.dataservice.adfcoreignite.client.api.remote;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface RawDataTemplate
{
  public abstract <T> void save(T paramT);
  
  public abstract <T> void put(Object paramObject, T paramT);
  
  public abstract <T> void putAll(Map<Object, Object> paramMap);
  
  public abstract <T> void putAll(Map<Object, Object> paramMap, Object paramObject);
  
  public abstract Object delete(Object paramObject);
  
  public abstract boolean containsKey(Object paramObject);
  
  public abstract RawData get(Object paramObject);
  
  public abstract Object get(Object paramObject, boolean paramBoolean);
  
  public abstract Iterator<Object> localKeyIterator();
  
  public abstract Iterator<Entry<Object, Object>> localEntryIterator();
  
  public abstract String getFullPath();
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\client\api\remote\RawDataTemplate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */