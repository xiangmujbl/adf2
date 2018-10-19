package com.jnj.adf.dataservice.adfcoreignite.client.api.remote;

public abstract interface RawData
{
  public abstract Object getKey();
  
  public abstract RawDataValue getValue();
  
  public abstract String getPath();
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\client\api\remote\RawData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */