package com.jnj.adf.dataservice.adfcoreignite.grid.data.version;

import com.jnj.adf.config.annotations.Path;
import com.jnj.adf.config.annotations.RemoteMethod;
import com.jnj.adf.config.annotations.RemoteMethod.InvokeTypes;
import com.jnj.adf.config.annotations.RemoteServiceApi;
import com.jnj.adf.grid.indexer.AdfField;
import java.util.List;

@RemoteServiceApi("com.jnj.adf.grid.data.version.TypeRedefineRemoteService")
public abstract interface ITypeRedefineRemoteService
{
  public static final String srvId = "com.jnj.adf.grid.data.version.TypeRedefineRemoteService";
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVER)
  public abstract List<AdfField[]> getFieldDefs(@Path String paramString);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVER)
  public abstract List<AdfField[]> getFieldDefs(@Path(true) String paramString1, String paramString2);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVER)
  public abstract List<String> setFieldDefs(@Path String paramString, AdfField[] paramArrayOfAdfField);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVER)
  public abstract List<String> setFieldDefs(@Path(true) String paramString1, String paramString2, AdfField[] paramArrayOfAdfField);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVER)
  public abstract List<List<String>> getKeyAndColNames(@Path String paramString);
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\data\version\ITypeRedefineRemoteService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */