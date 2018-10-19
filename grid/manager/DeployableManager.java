package com.jnj.adf.dataservice.adfcoreignite.grid.manager;

import java.util.Map;

public abstract interface DeployableManager
{
  public abstract <S> void register(Map<String, S> paramMap);
  
  public abstract void unregister(String paramString);
  
  public abstract <S> void register(String paramString, S paramS);
  
  public abstract void clean(String[] paramArrayOfString);
  
  public abstract <S> S getService(String paramString);
  
  public abstract <S> Map<String, S> getRegistry();
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\manager\DeployableManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */