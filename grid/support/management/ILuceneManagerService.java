package com.jnj.adf.dataservice.adfcoreignite.grid.support.management;

import com.jnj.adf.config.annotations.Path;
import com.jnj.adf.config.annotations.RemoteMethod;
import com.jnj.adf.config.annotations.RemoteMethod.CollectorTypes;
import com.jnj.adf.config.annotations.RemoteMethod.InvokeTypes;
import com.jnj.adf.config.annotations.RemoteServiceApi;
import com.jnj.adf.grid.indexer.AdfField;
import java.util.List;

@RemoteServiceApi("LuceneManagerService")
public abstract interface ILuceneManagerService
{
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVERS)
  public abstract List<String> viewIndex(@Path String paramString);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVER)
  public abstract List<AdfField> viewFields(@Path String paramString);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVERS, collectorType=RemoteMethod.CollectorTypes.SUM)
  public abstract long getIndexCount(@Path String paramString);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_REGION)
  public abstract List<String> refreshRepo(@Path String paramString);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVERS)
  public abstract void clearIndexRegion(@Path String paramString);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVERS)
  public abstract void optimizeIndex(@Path String paramString);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVERS)
  public abstract void reopenWriter(@Path String paramString);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVERS)
  public abstract void rebuildIndex(@Path String paramString);
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\support\management\ILuceneManagerService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */