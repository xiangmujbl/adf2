package com.jnj.adf.dataservice.adfcoreignite.grid.manager.region;

import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.client.ClientRegionFactory;
import com.gemstone.gemfire.cache.client.ClientRegionShortcut;
import com.gemstone.gemfire.cache.client.Pool;
import java.util.List;

public abstract interface ClientRegionCreator
{
  public abstract List<String> createAllClientRegions(String paramString, Pool paramPool, ClientRegionShortcut paramClientRegionShortcut);
  
  public abstract List<String> createAllClientRegions(String paramString, Pool paramPool, ClientRegionFactory<?, ?> paramClientRegionFactory);
  
  public abstract Region createClientRegion(String paramString1, String paramString2, Pool paramPool, ClientRegionShortcut paramClientRegionShortcut);
  
  public abstract Region createClientRegion(String paramString1, String paramString2, Pool paramPool, ClientRegionFactory<?, ?> paramClientRegionFactory);
  
  public abstract void destroyClientRegions(List<String> paramList);
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\manager\region\ClientRegionCreator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */