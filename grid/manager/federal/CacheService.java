package com.jnj.adf.dataservice.adfcoreignite.grid.manager.federal;

import com.jnj.adf.grid.connect.domain.GridInfo;
import com.jnj.adf.grid.connect.domain.GridInfoAndStatus;
import java.util.List;

public abstract interface CacheService
{
  public abstract void cacheGrid(GridInfo paramGridInfo);
  
  public abstract GridInfo getGridInfo();
  
  public abstract GridInfo getGridInfo(String paramString);
  
  public abstract void updateCache(GridInfo paramGridInfo);
  
  public abstract void removeCache(GridInfo paramGridInfo);
  
  public abstract List<String> cacheGroup(String paramString);
  
  public abstract List<GridInfo> listGridData(String paramString);
  
  public abstract List<String> listGridName(String paramString);
  
  public abstract GridInfoAndStatus getGridAndStatus(String paramString);
  
  public abstract List<GridInfoAndStatus> listGridAndStatus(String paramString);
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\manager\federal\CacheService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */