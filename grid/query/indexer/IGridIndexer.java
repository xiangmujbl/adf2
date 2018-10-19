package com.jnj.adf.dataservice.adfcoreignite.grid.query.indexer;

import java.io.Serializable;

public abstract interface IGridIndexer<T, ID extends Serializable>
{
  public abstract GridIndexerFactory.IndexPrivoder getIndexPrivoder();
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\query\indexer\IGridIndexer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */