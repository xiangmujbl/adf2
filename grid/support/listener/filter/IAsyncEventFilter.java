package com.jnj.adf.dataservice.adfcoreignite.grid.support.listener.filter;

import com.gemstone.gemfire.cache.asyncqueue.AsyncEvent;
import java.util.List;

public abstract interface IAsyncEventFilter
{
  public abstract boolean processEvents(List<AsyncEvent> paramList);
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\support\listener\filter\IAsyncEventFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */