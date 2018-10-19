package com.jnj.adf.dataservice.adfcoreignite.grid.client.api.support;

import com.gemstone.gemfire.cache.Region;
import com.jnj.adf.client.api.GridBasicOperations;

public abstract interface PathService
  extends GridBasicOperations
{
  public abstract String getFullPath(String paramString1, String paramString2);
  
  public abstract String getUserPath(String paramString);
  
  public abstract boolean createPath(String paramString1, String paramString2);
  
  public abstract Region<?, ?> getRegion(String paramString1, String paramString2);
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\client\api\support\PathService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */