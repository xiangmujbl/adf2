package com.jnj.adf.dataservice.adfcoreignite.client.api;

import com.jnj.adf.client.api.query.SortOrder;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;

public abstract interface GridLocationQueryOperations
  extends GridQueryOperations
{
  public abstract GridLocationOperations within(Point paramPoint, Distance paramDistance);
  
  public abstract GridLocationOperations setSortOrder(SortOrder paramSortOrder);
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\client\api\GridLocationQueryOperations.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */