package com.jnj.adf.dataservice.adfcoreignite.client.api;

public abstract interface IRemoteServiceBuilder
{
  public abstract IRemoteServiceBuilder setSrvId(String paramString);
  
  public abstract IRemoteServiceBuilder setMethod(String paramString);
  
  public abstract IRemoteServiceBuilder setPath(String paramString);
  
  public abstract IRemoteServiceBuilder setGridId(String paramString);
  
  public abstract IRemoteServiceBuilder setParameterTypes(String... paramVarArgs);
  
  public abstract IRemoteServiceBuilder setParameterValues(String... paramVarArgs);
  
  public abstract IRemoteServiceBuilder setResultCollector(String paramString);
  
  public abstract IRemoteServiceBuilder setFilters(String... paramVarArgs);
  
  public abstract IRemoteServiceBuilder setFromShell(boolean paramBoolean);
  
  public abstract <T> T execute();
  
  public abstract IRemoteServiceBuilder setInvokeType(String paramString);
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\client\api\IRemoteServiceBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */