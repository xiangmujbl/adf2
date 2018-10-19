package com.jnj.adf.dataservice.adfcoreignite.grid.master;

import com.jnj.adf.config.annotations.RemoteMethod;
import com.jnj.adf.config.annotations.RemoteServiceApi;
import com.jnj.adf.grid.connect.domain.GridInfo;
import com.jnj.adf.grid.connect.domain.GridStatus;
import com.jnj.adf.grid.master.domain.RegionGrid;
import java.util.List;

@RemoteServiceApi("HostBizRemoteService")
public abstract interface IHostBizRemoteService
{
  public static final String RemoteServiceName = "HostBizRemoteService";
  
  @RemoteMethod
  public abstract boolean registerRegionGrid(RegionGrid paramRegionGrid);
  
  @RemoteMethod
  public abstract RegionGrid getRegionGrid(String paramString);
  
  @RemoteMethod
  public abstract boolean registerRegionPath(String paramString1, String paramString2);
  
  @RemoteMethod
  public abstract boolean inactiveRegionPath(String paramString1, String paramString2);
  
  @RemoteMethod
  public abstract boolean registerRegionPaths(String paramString, List<String> paramList);
  
  @RemoteMethod
  public abstract List<String> listRegionPathByGridId(String paramString);
  
  @RemoteMethod
  public abstract boolean registerRemoteService(String paramString1, String paramString2);
  
  @RemoteMethod
  public abstract boolean registerRemoteServices(String paramString, List<String> paramList);
  
  @RemoteMethod
  public abstract List<String> listRemoteServicesByGridId(String paramString);
  
  @RemoteMethod
  public abstract boolean gridOnline(GridInfo paramGridInfo);
  
  @RemoteMethod
  public abstract boolean updateGrid(String paramString, GridInfo paramGridInfo);
  
  @RemoteMethod
  public abstract void groupOnline(GridInfo paramGridInfo);
  
  @RemoteMethod
  public abstract boolean addGroup(String paramString);
  
  @RemoteMethod
  public abstract boolean removeGroup(String paramString);
  
  @RemoteMethod
  public abstract boolean updateGroup(String paramString, GridInfo paramGridInfo);
  
  @RemoteMethod
  public abstract void setGridStatus(String paramString, GridStatus paramGridStatus);
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\master\IHostBizRemoteService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */