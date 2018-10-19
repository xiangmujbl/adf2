package com.jnj.adf.dataservice.adfcoreignite.grid.support.ldap;

import com.jnj.adf.config.annotations.RemoteMethod;
import com.jnj.adf.config.annotations.RemoteMethod.InvokeTypes;
import com.jnj.adf.config.annotations.RemoteServiceApi;
import java.util.List;

@RemoteServiceApi("HotModifyLdapService")
public abstract interface IHotModifyLdapService
{
  public static final String RemoteServiceName = "HotModifyLdapService";
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVERS)
  public abstract List<Boolean> reLoadLdap();
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\support\ldap\IHotModifyLdapService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */