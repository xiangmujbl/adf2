package com.jnj.adf.dataservice.adfcoreignite.client.api.remote;

public abstract interface IRawDataEventFilter
{
  public abstract boolean afterCreate(RawData paramRawData);
  
  public abstract boolean afterUpdate(RawData paramRawData1, RawData paramRawData2);
  
  public abstract boolean afterDelete(RawData paramRawData);
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\client\api\remote\IRawDataEventFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */