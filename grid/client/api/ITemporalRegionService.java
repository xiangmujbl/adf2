package com.jnj.adf.dataservice.adfcoreignite.grid.client.api;

import com.gemstone.gemfire.pdx.PdxInstance;
import com.jnj.adf.config.annotations.Filters;
import com.jnj.adf.config.annotations.Path;
import com.jnj.adf.config.annotations.RemoteMethod;
import com.jnj.adf.config.annotations.RemoteMethod.InvokeTypes;
import com.jnj.adf.config.annotations.RemoteServiceApi;
import com.jnj.adf.grid.data.temporal.TemporalKey;
import com.jnj.adf.grid.data.temporal.TemporalValue;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RemoteServiceApi("com.jnj.adf.grid.data.temporal.TemporalRegionService")
public abstract interface ITemporalRegionService
{
  public static final String SRV_ID = "com.jnj.adf.grid.data.temporal.TemporalRegionService";
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_REGION)
  public abstract PdxInstance getAsof(@Path String paramString1, @Filters String paramString2, long paramLong1, long paramLong2);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_REGION)
  public abstract Map<String, PdxInstance> getAll(@Path String paramString, @Filters List<String> paramList, long paramLong1, long paramLong2);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_REGION)
  public abstract <V> void put(@Path String paramString1, @Filters String paramString2, TemporalValue paramTemporalValue, Map<String, Object> paramMap);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_REGION)
  public abstract <V> void putAll(@Path String paramString, @Filters(true) Set<String> paramSet, Map<String, TemporalValue> paramMap, Map<String, Object> paramMap1);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_REGION)
  public abstract boolean remove(@Path String paramString1, @Filters String paramString2, long paramLong1, long paramLong2, Map<String, Object> paramMap);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_REGION)
  public abstract Map<TemporalKey, String> getHistoryDump(@Path(true) String paramString1, @Filters String paramString2);
  
  @RemoteMethod
  public abstract boolean rebuildData();
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\client\api\ITemporalRegionService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */