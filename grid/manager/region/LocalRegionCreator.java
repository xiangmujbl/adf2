package com.jnj.adf.dataservice.adfcoreignite.grid.manager.region;

import com.gemstone.gemfire.cache.Region;

public abstract interface LocalRegionCreator
{
  public abstract Region createLocalRegions(String paramString);
  
  public abstract void destroyLocalRegions(String paramString);
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\manager\region\LocalRegionCreator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */