package com.jnj.adf.dataservice.adfcoreignite.grid.kit;

import com.jnj.adf.grid.auth.exception.AuthException;
import java.util.Set;

public abstract interface IAuthenticateGetter
{
  public abstract boolean authenticate(String paramString1, String paramString2)
    throws AuthException;
  
  public abstract Set<String> getUserGroups(String paramString)
    throws AuthException;
  
  public abstract Set<String> getUserPrivilege(String paramString)
    throws AuthException;
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\kit\IAuthenticateGetter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */