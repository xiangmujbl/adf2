package com.jnj.adf.dataservice.adfcoreignite.grid.master;

import com.jnj.adf.config.annotations.RemoteMethod;
import com.jnj.adf.config.annotations.RemoteServiceApi;
import com.jnj.adf.grid.master.domain.sync.GridAllPermission;
import com.jnj.adf.grid.master.domain.sync.GridPermission;
import com.jnj.adf.grid.master.domain.sync.GroupPermission;
import com.jnj.adf.grid.master.domain.sync.RegionPermission;
import com.jnj.adf.grid.master.domain.sync.RemoteServicePermission;
import java.util.Map;

@RemoteServiceApi("HostPrivilegeSyncRemoteService")
public abstract interface IHostPrivilegeSyncRemoteService
{
  public static final String RemoteServiceName = "HostPrivilegeSyncRemoteService";
  
  @RemoteMethod
  public abstract RegionPermission syncRegionPathDataRolePermission(String paramString1, String paramString2);
  
  @RemoteMethod
  public abstract RemoteServicePermission syncRemoteServiceDataRolePermission(String paramString1, String paramString2);
  
  @RemoteMethod
  public abstract GridPermission syncGridDataRolePermission(String paramString);
  
  @RemoteMethod
  public abstract Map<String, GroupPermission> syncGroupDataRolePermission(String paramString1, String paramString2);
  
  @RemoteMethod
  public abstract GridAllPermission syncGridAllDataRolePermission(String paramString);
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\master\IHostPrivilegeSyncRemoteService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */