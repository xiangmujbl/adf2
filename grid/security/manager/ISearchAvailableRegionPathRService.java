package com.jnj.adf.dataservice.adfcoreignite.grid.security.manager;

import com.jnj.adf.config.annotations.RemoteMethod;
import com.jnj.adf.config.annotations.RemoteServiceApi;
import java.util.Map;
import java.util.Set;

@RemoteServiceApi("SearchAvailableRegionPathRService")
public abstract interface ISearchAvailableRegionPathRService
{
  public static final String RemoteServiceName = "SearchAvailableRegionPathRService";
  
  @RemoteMethod
  public abstract Map<String, String> availableRegion(Set<String> paramSet1, Set<String> paramSet2);
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\security\manager\ISearchAvailableRegionPathRService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */