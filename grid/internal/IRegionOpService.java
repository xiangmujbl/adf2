package com.jnj.adf.dataservice.adfcoreignite.grid.internal;

import com.jnj.adf.config.annotations.Filters;
import com.jnj.adf.config.annotations.Path;
import com.jnj.adf.config.annotations.RemoteMethod;
import com.jnj.adf.config.annotations.RemoteMethod.InvokeTypes;
import com.jnj.adf.config.annotations.RemoteServiceApi;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RemoteServiceApi("RegionOpService")
public abstract interface IRegionOpService
{
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVER)
  public abstract <T> T get(@Path String paramString1, Class<T> paramClass, String paramString2);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_REGION)
  public abstract <T> List<T> getAll(@Path String paramString, Class<T> paramClass);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_REGION)
  public abstract void put(@Path String paramString1, @Filters String paramString2, Object paramObject);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_REGION)
  public abstract void putAll(@Path String paramString, @Filters Set paramSet, Map paramMap);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_REGION)
  public abstract void remove(@Path String paramString1, @Filters String paramString2);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVERS)
  public abstract <T> List<T> queryForList(@Path String paramString1, Class<T> paramClass, String paramString2);
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\internal\IRegionOpService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */