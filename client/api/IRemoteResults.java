package com.jnj.adf.dataservice.adfcoreignite.client.api;

import com.gemstone.gemfire.distributed.DistributedMember;
import java.util.List;
import java.util.Map;

public abstract interface IRemoteResults<T>
  extends Iterable<T>
{
  public abstract void addRemoteResults(IRemoteResults<T> paramIRemoteResults);
  
  public abstract void addResult(T paramT);
  
  public abstract void addException(DistributedMember paramDistributedMember, Exception paramException);
  
  public abstract T singleResult();
  
  public abstract List<T> getResults();
  
  public abstract Map<String, Exception> getExceptions();
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\client\api\IRemoteResults.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */