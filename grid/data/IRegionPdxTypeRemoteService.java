package com.jnj.adf.dataservice.adfcoreignite.grid.data;

import com.jnj.adf.config.annotations.Path;
import com.jnj.adf.config.annotations.RemoteMethod;
import com.jnj.adf.config.annotations.RemoteMethod.InvokeTypes;
import com.jnj.adf.config.annotations.RemoteServiceApi;
import com.jnj.adf.grid.data.verhist.RegionPdxTypeValue;
import java.util.List;
import java.util.Set;

@RemoteServiceApi("com.jnj.adf.grid.data.verhist.RegionPdxTypeRemoteService")
public abstract interface IRegionPdxTypeRemoteService
{
  public static final String SRV_ID = "com.jnj.adf.grid.data.verhist.RegionPdxTypeRemoteService";
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVER)
  public abstract List<RegionPdxTypeValue> getAllPdxTypeByRegion(@Path String paramString);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_REGION)
  public abstract Set[] fetchFieldInfoForRegion(@Path String paramString);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVER)
  public abstract List<String> rebuildRegionPdxType(@Path String paramString);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVER)
  public abstract List<String> removeAllRegionPdxType(@Path String paramString);
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\data\IRegionPdxTypeRemoteService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */