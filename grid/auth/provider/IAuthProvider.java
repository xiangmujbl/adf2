package com.jnj.adf.dataservice.adfcoreignite.grid.auth.provider;

import com.jnj.adf.grid.auth.exception.AuthException;
import com.jnj.adf.grid.auth.impl.AuthUserPrivilege;
import com.jnj.adf.grid.auth.user.AuthUserInfo;

public abstract interface IAuthProvider
{
  public abstract String verify(String paramString1, String paramString2)
    throws AuthException;
  
  public abstract AuthUserInfo findUser(String paramString)
    throws AuthException;
  
  public abstract AuthUserPrivilege findUserPrivilege(String paramString)
    throws AuthException;
  
  public abstract boolean enabled();
  
  public abstract int priority();
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\auth\provider\IAuthProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */