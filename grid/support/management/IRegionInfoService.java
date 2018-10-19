package com.jnj.adf.dataservice.adfcoreignite.grid.support.management;

import com.jnj.adf.config.annotations.Path;
import com.jnj.adf.config.annotations.RemoteMethod;
import com.jnj.adf.config.annotations.RemoteMethod.InvokeTypes;
import com.jnj.adf.config.annotations.RemoteServiceApi;
import java.util.HashMap;

@RemoteServiceApi("RegionInfoService")
public abstract interface IRegionInfoService
{
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVER)
  public abstract boolean enableLogicKey(@Path String paramString);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVER)
  public abstract HashMap<String, Object> findAllRegion();
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\support\management\IRegionInfoService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */