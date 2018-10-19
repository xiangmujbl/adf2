package com.jnj.adf.dataservice.adfcoreignite.grid.connect;

import com.gemstone.gemfire.cache.client.Pool;
import com.jnj.adf.grid.connect.attri.ADFPoolAttributes;
import com.jnj.adf.grid.connect.domain.GridInfo;

public abstract interface GridConnection
{
  public abstract boolean connect();
  
  public abstract boolean isConnected();
  
  public abstract void close();
  
  public abstract String getPoolName();
  
  public abstract Pool getPool();
  
  public abstract GridInfo fetchGridInfo();
  
  public abstract void setGridInfo(GridInfo paramGridInfo);
  
  public abstract void setPoolAttributes(ADFPoolAttributes paramADFPoolAttributes);
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\connect\GridConnection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */