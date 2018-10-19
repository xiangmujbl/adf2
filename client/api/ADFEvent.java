package com.jnj.adf.dataservice.adfcoreignite.client.api;

public abstract interface ADFEvent
{
  public abstract String getKey();
  
  public abstract String getValue();
  
  public abstract <V> V getValue(Class<V> paramClass);
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\client\api\ADFEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */