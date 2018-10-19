package com.jnj.adf.dataservice.adfcoreignite.client.api;

import java.util.List;
import java.util.Map.Entry;

public abstract interface PageResults<T>
{
  public abstract List<T> getResults();
  
  public abstract List<Map.Entry<String, T>> getEntries();
  
  public abstract long getTotalCount();
  
  public abstract boolean lastPage();
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\client\api\PageResults.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */