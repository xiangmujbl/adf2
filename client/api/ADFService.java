package com.jnj.adf.dataservice.adfcoreignite.client.api;

public abstract interface ADFService
{
  public abstract boolean connect(String paramString1, String paramString2);
  
  public abstract boolean isConnect(String paramString);
  
  @Deprecated
  public abstract String connect(String paramString1, String paramString2, String paramString3, String paramString4);
  
  public abstract String login(String paramString1, String paramString2);
  
  public abstract void loginOut();
  
  public abstract boolean verifyToken(String paramString);
  
  public abstract String getCurrentUser();
  
  public abstract ADFOperations onPath(String paramString);
  
  /**
   * @deprecated
   */
  public abstract ADFOperations onGroups(String paramString, String... paramVarArgs);
  
  public abstract <T> IRemoteService<T> remote(T paramT);
  
  public abstract <T> IRemoteService<T> remote(Class<T> paramClass);
  
  public abstract <T> IRemoteService<T> remote(String paramString);
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\client\api\ADFService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */