package com.jnj.adf.dataservice.adfcoreignite.grid.security.column;

import com.jnj.adf.config.annotations.Path;
import com.jnj.adf.config.annotations.RemoteMethod;
import com.jnj.adf.config.annotations.RemoteMethod.InvokeTypes;
import com.jnj.adf.config.annotations.RemoteServiceApi;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RemoteServiceApi("IColumnPrivilegeService")
public abstract interface IColumnPrivilegeService
{
  public static final String RemoteServiceName = "IColumnPrivilegeService";
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVER)
  public abstract Map<String, Map<String, String>> getRegionPermissionMap(@Path String paramString, Set<String> paramSet);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVER)
  public abstract Set<String> filterViewRawPaths(String paramString1, Set<String> paramSet1, Set<String> paramSet2, String paramString2);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVER)
  public abstract HashMap<String, Map<String, String>> filterViewColumns(String paramString1, Set<String> paramSet, HashMap<String, Set<String>> paramHashMap, String paramString2);
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\security\column\IColumnPrivilegeService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */