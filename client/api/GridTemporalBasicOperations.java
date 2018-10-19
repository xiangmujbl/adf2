package com.jnj.adf.dataservice.adfcoreignite.client.api;

import com.jnj.adf.grid.data.temporal.TemporalKey;
import com.jnj.adf.grid.support.context.ADFSession;
import java.util.Map;

public abstract interface GridTemporalBasicOperations
  extends GridLocationBasicOperations
{
  @ADFSession
  public abstract Map<TemporalKey, String> getHistoryDump(String paramString);
  
  @ADFSession
  public abstract <V> Map<TemporalKey, V> getHistoryDump(String paramString, Class<V> paramClass);
  
  @ADFSession
  public abstract boolean rebuildData(String paramString);
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\client\api\GridTemporalBasicOperations.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */