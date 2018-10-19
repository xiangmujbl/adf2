package com.jnj.adf.dataservice.adfcoreignite.grid.data.temporal;

import java.io.Serializable;

public abstract interface ITemporalKey<K>
  extends Serializable
{
  public abstract String getUuid();
  
  public abstract long getWrittenTime();
  
  public abstract long getWrittenTimeEnd();
  
  public abstract long getValidFrom();
  
  public abstract long getValidTo();
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\data\temporal\ITemporalKey.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */