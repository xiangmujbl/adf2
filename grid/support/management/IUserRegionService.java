package com.jnj.adf.dataservice.adfcoreignite.grid.support.management;

import com.gemstone.gemfire.cache.client.Pool;
import com.jnj.adf.config.annotations.Path;
import com.jnj.adf.config.annotations.RemoteMethod;
import com.jnj.adf.config.annotations.RemoteMethod.InvokeTypes;
import com.jnj.adf.config.annotations.RemoteServiceApi;
import com.jnj.adf.grid.support.domain.RemoteResult;

@RemoteServiceApi("_UserRegionService")
public abstract interface IUserRegionService
{
  public static final String _SERVICE_NAME = "_UserRegionService";
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVERS)
  public abstract RemoteResult createPath(@Path(true) Pool paramPool, PathRequest paramPathRequest);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVERS)
  public abstract RemoteResult createDisk(@Path(true) Pool paramPool, DiskRequest paramDiskRequest);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVER)
  public abstract RemoteResult destroyPath(@Path(true) Pool paramPool, String paramString, boolean paramBoolean);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVER)
  public abstract RemoteResult destroyDiskStore(@Path(true) Pool paramPool, String paramString);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVERS)
  public abstract RemoteResult checkPathRelatedResources(@Path(true) Pool paramPool, String paramString);
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\support\management\IUserRegionService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */