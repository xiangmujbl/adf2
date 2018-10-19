package com.jnj.adf.dataservice.adfcoreignite.grid.manager.federal;

import com.jnj.adf.grid.connect.domain.GridInfo;
import com.jnj.adf.grid.connect.domain.GridStatus;
import java.util.List;

public abstract interface FederalGridOperation
{
  public abstract boolean updateGrid(String paramString, GridInfo paramGridInfo);
  
  public abstract List<String> listGridName();
  
  public abstract List<String> listGridName(String paramString);
  
  public abstract void gridOnline(GridInfo paramGridInfo);
  
  public abstract GridInfo getGridData(String paramString);
  
  public abstract void setGridStatus(String paramString, GridStatus paramGridStatus);
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\manager\federal\FederalGridOperation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */