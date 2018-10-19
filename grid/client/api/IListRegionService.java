package com.jnj.adf.dataservice.adfcoreignite.grid.client.api;

import com.gemstone.gemfire.cache.client.Pool;
import com.jnj.adf.config.annotations.Path;
import com.jnj.adf.config.annotations.RemoteMethod;
import com.jnj.adf.config.annotations.RemoteMethod.InvokeTypes;
import com.jnj.adf.config.annotations.RemoteServiceApi;
import java.util.List;

@RemoteServiceApi("adf.system.listRegion")
public abstract interface IListRegionService
{
  public static final String ID = "adf.system.listRegion";
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVER)
  public abstract List<?> listNormalRegions(@Path(true) Pool paramPool);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVER)
  public abstract List<?> listHostRegions(@Path(true) Pool paramPool);
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\client\api\IListRegionService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */