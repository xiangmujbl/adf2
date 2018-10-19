package com.jnj.adf.dataservice.adfcoreignite.grid.view.manager;

import com.jnj.adf.config.annotations.Path;
import com.jnj.adf.config.annotations.RemoteMethod;
import com.jnj.adf.config.annotations.RemoteMethod.InvokeTypes;
import com.jnj.adf.config.annotations.RemoteServiceApi;
import com.jnj.adf.grid.view.domain.ViewColumn;
import com.jnj.adf.grid.view.domain.ViewColumnMapping;
import com.jnj.adf.grid.view.domain.ViewGroup;
import com.jnj.adf.grid.view.domain.ViewMeta;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RemoteServiceApi("ViewManagerService")
public abstract interface IViewManagerService
{
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVER)
  public abstract boolean isView(String paramString);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVER)
  public abstract boolean deleteView(String paramString);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVER)
  public abstract List<ViewColumn> listColumns(@Path String paramString);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVER)
  public abstract List<ViewGroup> listUnionGroups(@Path String paramString);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVER)
  public abstract void deleteUnion(String paramString1, String paramString2);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVER)
  public abstract List<ViewColumnMapping> listColumnMappings(String paramString1, String paramString2);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVER)
  public abstract List<ViewMeta> listViewMetas(@Path String paramString);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVER)
  public abstract Map<String, List<ViewMeta>> listAllViewMetas();
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVER)
  public abstract List<ViewMeta> getViewMeta(@Path String paramString);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVER)
  public abstract String deploy(@Path String paramString);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVERS)
  public abstract void pushRule(String paramString1, String paramString2, String paramString3, ViewMeta paramViewMeta);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVERS)
  public abstract void pushRules(String paramString, Map<String, String> paramMap, List<ViewMeta> paramList);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVER)
  public abstract void fullCreate(@Path String paramString);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVERS)
  public abstract void fullCreate(@Path String paramString, ViewMeta paramViewMeta);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVER)
  public abstract Map<String, String> listRules();
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVERS)
  public abstract void deleteRule(String paramString);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVERS)
  public abstract void deleteRules(Set<String> paramSet);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVERS)
  public abstract void invalidCache(String paramString, Set<String> paramSet);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVER)
  public abstract Set<String> listRawMainPaths(String paramString);
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\view\manager\IViewManagerService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */