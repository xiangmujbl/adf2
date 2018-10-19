package com.jnj.adf.dataservice.adfcoreignite.client.api;

import com.jnj.adf.grid.support.context.ADFSession;

public abstract interface QueueOperations
{
  @ADFSession
  public abstract <V> boolean offer(String paramString, V paramV);
  
  @ADFSession
  public abstract boolean offer(String paramString1, String paramString2);
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\client\api\QueueOperations.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */