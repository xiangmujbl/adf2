package com.jnj.adf.dataservice.adfcoreignite.grid.client.api.impl;

import com.jnj.adf.grid.query.criteria.Criteria;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;

public class LocationParam
{
  public double latitude;
  public double longitude;
  public Point location;
  public Distance distance;
  public Integer spatialSortOrder;
  public Criteria locationQry;
  public String distanceSort;
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\client\api\impl\LocationParam.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */