package com.jnj.adf.dataservice.adfcoreignite.client.api.async;

import com.jnj.adf.client.api.ADFEvent;
import java.util.List;

public abstract interface ADFAsyncListener
{
  public abstract void execute(List<ADFEvent> paramList);
  
  public abstract String path();
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\client\api\async\ADFAsyncListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */