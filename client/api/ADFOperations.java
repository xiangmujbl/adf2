package com.jnj.adf.dataservice.adfcoreignite.client.api;

import com.jnj.adf.grid.support.context.ADFSession;
import java.util.List;

public abstract interface ADFOperations
  extends GridBasicOperations, GridQueryOperations, QueueOperations
{
  public abstract GridTemporalOperations biTemporal();
  
  public abstract GridLocationOperations spatialOp();
  
  public abstract List<Object> queryOql(String paramString);
  
  @ADFSession
  public abstract void registerSchema(String paramString);
  
  @ADFSession
  public abstract String getSchema();
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\client\api\ADFOperations.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */