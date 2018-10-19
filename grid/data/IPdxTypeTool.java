package com.jnj.adf.dataservice.adfcoreignite.grid.data;

import com.gemstone.gemfire.pdx.internal.PdxType;
import com.jnj.adf.config.annotations.Path;
import com.jnj.adf.config.annotations.RemoteMethod;
import com.jnj.adf.config.annotations.RemoteMethod.InvokeTypes;
import com.jnj.adf.config.annotations.RemoteServiceApi;
import java.util.List;
import java.util.Map;

@RemoteServiceApi("com.jnj.adf.grid.data.PdxTypeTool")
public abstract interface IPdxTypeTool
{
  public static final String srvID = "com.jnj.adf.grid.data.PdxTypeTool";
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVER)
  public abstract Integer removeAllRegionDataOfPdxTypeId(@Path String paramString, Integer paramInteger);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVER)
  public abstract List<PdxType> getPdxTypesForRegion(@Path String paramString);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVER)
  public abstract Map<PdxType, Long> getDataCountOfPdxTypeForRegion(@Path String paramString);
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\data\IPdxTypeTool.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */