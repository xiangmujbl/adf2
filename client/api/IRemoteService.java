package com.jnj.adf.dataservice.adfcoreignite.client.api;

import com.gemstone.gemfire.cache.client.Pool;
import com.gemstone.gemfire.cache.execute.ResultCollector;
import com.gemstone.gemfire.distributed.DistributedMember;
import java.util.Set;

public abstract interface IRemoteService<T>
{
  public static final String onServer = "onServer";
  public static final String onRegion = "onRegion";
  
  public abstract T onServer(String paramString);
  
  public abstract T onServer(Pool paramPool);
  
  public abstract T onServers(Pool paramPool);
  
  public abstract T onServers(String paramString);
  
  public abstract T onRegion(String paramString, Object... paramVarArgs);
  
  public abstract T onRegion(String paramString, Set<?> paramSet);
  
  public abstract T onMembers(String... paramVarArgs);
  
  public abstract T onMember(String... paramVarArgs);
  
  public abstract T onMember(DistributedMember paramDistributedMember);
  
  public abstract IRemoteService<T> setHasResults(boolean paramBoolean);
  
  public abstract IRemoteService<T> setExtractResults(boolean paramBoolean);
  
  public abstract IRemoteService<T> withResultCollector(ResultCollector paramResultCollector);
  
  public abstract <C> C getOriginBean();
  
  @Deprecated
  public abstract <V> Iterable<V> iterableResults();
  
  @Deprecated
  public abstract ResultCollector<?, ?> getResultColector();
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\client\api\IRemoteService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */