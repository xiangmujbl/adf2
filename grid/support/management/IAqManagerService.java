package com.jnj.adf.dataservice.adfcoreignite.grid.support.management;

import com.jnj.adf.config.annotations.Path;
import com.jnj.adf.config.annotations.RemoteMethod;
import com.jnj.adf.config.annotations.RemoteMethod.InvokeTypes;
import com.jnj.adf.config.annotations.RemoteServiceApi;
import com.jnj.adf.grid.support.listener.filter.AsyncEventFilterType;

@RemoteServiceApi("AqManagerService")
public abstract interface IAqManagerService
{
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVERS)
  public abstract void enableAq(@Path String paramString);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVERS)
  public abstract void enableAq(@Path String paramString, AsyncEventFilterType paramAsyncEventFilterType);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVERS)
  public abstract void enableFilter(@Path String paramString, AsyncEventFilterType paramAsyncEventFilterType);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVERS)
  public abstract void disableAq(@Path String paramString);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVERS)
  public abstract void disableAq(@Path String paramString, AsyncEventFilterType paramAsyncEventFilterType);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVERS)
  public abstract void disableFilter(@Path String paramString, AsyncEventFilterType paramAsyncEventFilterType);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVER)
  public abstract boolean aqEnabled(@Path String paramString);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVER)
  public abstract boolean aqEnabled(@Path String paramString, AsyncEventFilterType paramAsyncEventFilterType);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVER)
  public abstract boolean filterEnabled(@Path String paramString, AsyncEventFilterType paramAsyncEventFilterType);
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\support\management\IAqManagerService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */