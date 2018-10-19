package com.jnj.adf.dataservice.adfcoreignite.grid.events;

import org.springframework.context.ApplicationEvent;

public abstract interface IEventDispatcher
{
  public abstract void sendEvent(ApplicationEvent paramApplicationEvent);
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\events\IEventDispatcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */