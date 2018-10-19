package com.jnj.adf.dataservice.adfcoreignite.grid.view.api;

import java.util.List;

public abstract interface IViewManager
{
  public abstract String deploy(String paramString);
  
  public abstract void fullCreate(String paramString);
  
  public abstract String authenticateForRowData(String paramString);
  
  public abstract <T> List<T> authenticateForColumnData(String paramString, List<T> paramList);
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\view\api\IViewManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */