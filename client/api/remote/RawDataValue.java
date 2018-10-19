package com.jnj.adf.dataservice.adfcoreignite.client.api.remote;

import com.gemstone.gemfire.pdx.PdxInstance;
import java.util.List;
import java.util.Map;

public abstract interface RawDataValue
{
  public abstract boolean hasField(String paramString);
  
  public abstract List<String> getFieldNames();
  
  public abstract <V> V getField(String paramString);
  
  public abstract void setField(String paramString, Object paramObject);
  
  public abstract PdxInstance toPdx();
  
  public abstract Map toMap();
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\client\api\remote\RawDataValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */