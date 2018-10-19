package com.jnj.adf.dataservice.adfcoreignite.grid.support.management;

import com.jnj.adf.config.annotations.Path;
import com.jnj.adf.config.annotations.RemoteMethod;
import com.jnj.adf.config.annotations.RemoteMethod.InvokeTypes;
import com.jnj.adf.config.annotations.RemoteServiceApi;
import java.util.List;

@RemoteServiceApi("RegionCleanService")
public abstract interface IManagementService
{
  public static final String CleanServiceName = "RegionCleanService";
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVERS)
  public abstract List<String> clean(@Path String paramString);
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\support\management\IManagementService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */