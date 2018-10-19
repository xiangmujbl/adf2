package com.jnj.adf.dataservice.adfcoreignite.grid.support;

import com.gemstone.gemfire.cache.client.Pool;
import com.jnj.adf.config.annotations.Filters;
import com.jnj.adf.config.annotations.Path;
import com.jnj.adf.config.annotations.RemoteMethod;
import com.jnj.adf.config.annotations.RemoteMethod.CollectorTypes;
import com.jnj.adf.config.annotations.RemoteMethod.InvokeTypes;
import com.jnj.adf.config.annotations.RemoteServiceApi;
import com.jnj.adf.grid.support.domain.RemoteResult;
import java.util.List;

@RemoteServiceApi("_GRID_SERV_")
public abstract interface IGridService
{
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVER)
  public abstract int getServerCount(@Path(true) String paramString);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVER)
  public abstract int getServerCount(@Path(true) Pool paramPool);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVER)
  public abstract <T> T invokeProxy(String paramString1, String paramString2, @Path String paramString3, String[] paramArrayOfString1, String[] paramArrayOfString2, String paramString4, @Filters String[] paramArrayOfString3, String paramString5, boolean paramBoolean);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVER)
  public abstract <T> T invokeProxy(String paramString1, String paramString2, @Path(true) Pool paramPool, String[] paramArrayOfString1, String[] paramArrayOfString2, String paramString3, @Filters String[] paramArrayOfString3, String paramString4, boolean paramBoolean);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_REGION, collectorType=RemoteMethod.CollectorTypes.SUM)
  public abstract int getQueueSize(@Path String paramString);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVERS)
  public abstract List<String> listHotdeplableItems(@Path(true) Pool paramPool, String paramString);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVERS)
  public abstract List<String> listLoaders(@Path(true) Pool paramPool);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVERS)
  public abstract RemoteResult getPerStat(@Path(true) Pool paramPool);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVERS)
  public abstract RemoteResult listGatewaySenders(@Path(true) Pool paramPool);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_REGION)
  public abstract RemoteResult getQueueStat(@Path String paramString);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVER)
  public abstract List<String> getHostRegions(@Path(true) Pool paramPool);
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\support\IGridService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */