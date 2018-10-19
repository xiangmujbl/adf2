package com.jnj.adf.dataservice.adfcoreignite.grid.auth;

import com.jnj.adf.grid.auth.exception.AuthException;
import com.jnj.adf.grid.auth.impl.AuthUserPrivilege;
import com.jnj.adf.grid.auth.session.AuthInternalSession;
import com.jnj.adf.grid.auth.user.AuthUserInfo;

public abstract interface AuthService
{
  public abstract AuthInternalSession login(String paramString1, String paramString2)
    throws AuthException;
  
  public abstract String verify(String paramString1, String paramString2)
    throws AuthException;
  
  public abstract String createToken(String paramString)
    throws AuthException;
  
  public abstract void logout()
    throws AuthException;
  
  public abstract AuthInternalSession getAuthSession(AuthInternalSession paramAuthInternalSession)
    throws AuthException;
  
  public abstract AuthUserInfo getAuthUserInfo(String paramString)
    throws AuthException;
  
  public abstract AuthUserPrivilege getAuthUserPrivilege(String paramString)
    throws AuthException;
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\auth\AuthService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */