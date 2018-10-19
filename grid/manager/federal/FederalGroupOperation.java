package com.jnj.adf.dataservice.adfcoreignite.grid.manager.federal;

import com.jnj.adf.grid.connect.domain.GridInfo;
import java.util.List;

public abstract interface FederalGroupOperation
{
  public abstract boolean addGroup(String paramString);
  
  public abstract boolean removeGroup(String paramString);
  
  public abstract List<String> listGroupName();
  
  public abstract boolean updateGroup(String paramString, GridInfo paramGridInfo);
  
  public abstract void groupOnline(GridInfo paramGridInfo);
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\manager\federal\FederalGroupOperation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */