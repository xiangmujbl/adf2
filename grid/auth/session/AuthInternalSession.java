package com.jnj.adf.dataservice.adfcoreignite.grid.auth.session;

import com.jnj.adf.grid.auth.impl.AuthUserPrivilege;
import com.jnj.adf.grid.auth.user.AuthUserInfo;
import java.io.Serializable;
import java.util.Collection;

public abstract interface AuthInternalSession
  extends Serializable
{
  public abstract String getToken();
  
  public abstract long getLastAccessedTime();
  
  public abstract long getCreateTime();
  
  public abstract String getUserId();
  
  public abstract void setAttribute(String paramString, Object paramObject);
  
  public abstract Object getAttribute(String paramString);
  
  public abstract Collection<String> getAttributeNames();
  
  public abstract void removeAttribute(String paramString);
  
  public abstract void setUserId(String paramString);
  
  public abstract AuthUserPrivilege getUserPrivilege();
  
  public abstract AuthUserInfo getUserInfo();
  
  public abstract void clear();
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\auth\session\AuthInternalSession.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */