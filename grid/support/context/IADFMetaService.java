package com.jnj.adf.dataservice.adfcoreignite.grid.support.context;

import com.jnj.adf.client.api.IRemoteResults;
import com.jnj.adf.config.annotations.Path;
import com.jnj.adf.config.annotations.RemoteMethod;
import com.jnj.adf.config.annotations.RemoteMethod.InvokeTypes;
import com.jnj.adf.config.annotations.RemoteServiceApi;
import com.jnj.adf.grid.connect.domain.GridInfo;
import com.jnj.adf.grid.data.MetaTypes;
import com.jnj.adf.grid.data.RegionDefine_v3;
import com.jnj.adf.grid.data.schema.ProcessorTypes;
import com.jnj.adf.grid.support.domain.Log;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RemoteServiceApi("adf.meta.service")
public abstract interface IADFMetaService
{
  public static final String MetaRemoteService = "adf.meta.service";
  
  @RemoteMethod
  public abstract <V> String setValue(String paramString, V paramV);
  
  @RemoteMethod
  public abstract GridInfo getGridName();
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVERS)
  public abstract Set<String> getVersionColumns(@Path String paramString);
  
  @RemoteMethod
  public abstract String resetVersion(String paramString);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVERS)
  public abstract IRemoteResults<String> changeLogLevel(String paramString);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVERS)
  public abstract String changeLogLevel(String paramString1, String paramString2);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVER)
  public abstract List<Log> getAllLevel();
  
  @RemoteMethod
  public abstract Object getMetaValue(String paramString, MetaTypes paramMetaTypes);
  
  @RemoteMethod
  public abstract RegionDefine_v3 getUserAttribute(String paramString);
  
  @RemoteMethod
  public abstract RegionDefine_v3 registerUserAttribute(String paramString1, String paramString2, Object paramObject);
  
  @RemoteMethod
  public abstract Object getAllMetaValues();
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVER)
  public abstract RegionDefine_v3 getRegionDefine(@Path String paramString);
  
  @RemoteMethod
  public abstract Map<String, String> listRemoteServices();
  
  @RemoteMethod
  public abstract String setAQProcessorEnabled(ProcessorTypes paramProcessorTypes, boolean paramBoolean);
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\support\context\IADFMetaService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */