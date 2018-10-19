package com.jnj.adf.dataservice.adfcoreignite.grid.manager.logickey;

import com.gemstone.gemfire.pdx.PdxInstance;
import com.jnj.adf.config.annotations.Filters;
import com.jnj.adf.config.annotations.Path;
import com.jnj.adf.config.annotations.RemoteMethod;
import com.jnj.adf.config.annotations.RemoteMethod.InvokeTypes;
import com.jnj.adf.config.annotations.RemoteServiceApi;
import java.util.Map;
import java.util.Set;

@RemoteServiceApi("_logic_key_service")
public abstract interface ILogicKeyService
{
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_REGION)
  public abstract void putAll(@Path String paramString, @Filters Set<String> paramSet, Map<String, PdxInstance> paramMap, Map<String, Object> paramMap1);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_REGION)
  public abstract void put(@Path String paramString1, @Filters String paramString2, PdxInstance paramPdxInstance, Map<String, Object> paramMap);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_REGION)
  public abstract Object get(@Path String paramString1, @Filters String paramString2);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_REGION)
  public abstract Map<String, Object> getAll(@Path String paramString, @Filters Set<String> paramSet);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_REGION)
  public abstract void remove(@Path String paramString1, @Filters String paramString2);
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\manager\logickey\ILogicKeyService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */