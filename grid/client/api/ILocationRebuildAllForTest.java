package com.jnj.adf.dataservice.adfcoreignite.grid.client.api;

import com.jnj.adf.config.annotations.Path;
import com.jnj.adf.config.annotations.RemoteMethod;
import com.jnj.adf.config.annotations.RemoteMethod.InvokeTypes;
import com.jnj.adf.config.annotations.RemoteServiceApi;
import java.util.Set;

@RemoteServiceApi("LocationRebuildAllForTest")
public abstract interface ILocationRebuildAllForTest
{
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVERS)
  public abstract Set<Boolean> buildLocationAttributes(@Path(true) String paramString1, String paramString2, String paramString3);
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\client\api\ILocationRebuildAllForTest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */