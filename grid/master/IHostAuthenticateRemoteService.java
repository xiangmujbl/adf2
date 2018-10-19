package com.jnj.adf.dataservice.adfcoreignite.grid.master;

import com.jnj.adf.config.annotations.RemoteMethod;
import com.jnj.adf.config.annotations.RemoteServiceApi;
import com.jnj.adf.grid.auth.exception.AuthException;
import com.jnj.adf.grid.master.domain.RegionPath;
import java.util.Set;

@RemoteServiceApi("HostAuthenticateRemoteService")
public abstract interface IHostAuthenticateRemoteService
{
  public static final String RemoteServiceName = "HostAuthenticateRemoteService";
  
  @RemoteMethod
  public abstract boolean authenticate(String paramString1, String paramString2)
    throws AuthException;
  
  @RemoteMethod
  public abstract Set<String> getUserRolesByUsername(String paramString)
    throws AuthException;
  
  @RemoteMethod
  public abstract Set<String> getUserGroupsByUsername(String paramString)
    throws AuthException;
  
  @RemoteMethod
  public abstract String getGridIdByRegionPath(RegionPath paramRegionPath);
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\master\IHostAuthenticateRemoteService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */