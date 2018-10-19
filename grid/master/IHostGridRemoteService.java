package com.jnj.adf.dataservice.adfcoreignite.grid.master;

import com.jnj.adf.config.annotations.RemoteMethod;
import com.jnj.adf.config.annotations.RemoteMethod.InvokeTypes;
import com.jnj.adf.config.annotations.RemoteServiceApi;
import com.jnj.adf.grid.connect.domain.GridInfo;
import com.jnj.adf.grid.connect.domain.GridInfoAndStatus;
import java.util.List;

@RemoteServiceApi("HostGridRemoteService")
public abstract interface IHostGridRemoteService
{
  public static final String RemoteServiceName = "HostGridRemoteService";
  
  @RemoteMethod
  public abstract List<String> listGridName();
  
  @RemoteMethod
  public abstract List<String> listGridName(String paramString);
  
  @RemoteMethod
  public abstract GridInfo getGridData(String paramString);
  
  @RemoteMethod
  public abstract List<String> listGroupName();
  
  @RemoteMethod
  public abstract List<GridInfo> listGridData(String paramString);
  
  @RemoteMethod
  public abstract GridInfoAndStatus getGridAndStatus(String paramString);
  
  @RemoteMethod
  public abstract List<GridInfoAndStatus> listGridAndStatus(String paramString);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVER)
  public abstract boolean validateGridId(String paramString1, String paramString2);
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\master\IHostGridRemoteService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */