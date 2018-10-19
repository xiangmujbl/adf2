package com.jnj.adf.dataservice.adfcoreignite.grid.kit;

import com.jnj.adf.grid.common.ADFException;

public abstract interface IRegionPathGetter
{
  public abstract RegionInfo getRegionPathInfo(String paramString)
    throws ADFException;
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\kit\IRegionPathGetter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */