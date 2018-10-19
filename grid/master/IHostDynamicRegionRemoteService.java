package com.jnj.adf.dataservice.adfcoreignite.grid.master;

import com.jnj.adf.config.annotations.RemoteMethod;
import com.jnj.adf.config.annotations.RemoteMethod.InvokeTypes;
import com.jnj.adf.config.annotations.RemoteServiceApi;
import java.util.List;
import java.util.Map;

@RemoteServiceApi("DynamicRegionService")
public abstract interface IHostDynamicRegionRemoteService
{
  public static final String RemoteServiceName = "DynamicRegionService";
  
  @RemoteMethod
  public abstract List<Boolean> addRegion(String paramString1, String paramString2, String paramString3, String paramString4);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVERS)
  public abstract List<Map<String, String>> getDeployRegionInfo(String paramString);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVERS)
  public abstract List<Boolean> copyBaseConf4Test(String paramString);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVERS)
  public abstract List<Boolean> deployRegion(String paramString, byte[] paramArrayOfByte);
  
  @RemoteMethod
  public abstract List<Boolean> destroyDiskStore(String paramString);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVER)
  public abstract Boolean destroyRegion(String paramString1, String paramString2);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVERS)
  public abstract List<Boolean> checkDeployXml(String paramString1, String paramString2);
  
  @RemoteMethod
  public abstract List<Boolean> destroyAqOnMembers(String paramString1, String paramString2);
  
  @RemoteMethod
  public abstract List<Boolean> removeElement(String paramString1, String paramString2);
  
  @RemoteMethod
  public abstract List<Boolean> removeDiskStoreElement(String paramString);
  
  @RemoteMethod
  public abstract List<Boolean> deployServerXml(String paramString, byte[] paramArrayOfByte);
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\master\IHostDynamicRegionRemoteService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */