package com.jnj.adf.dataservice.adfcoreignite.grid.query;

import com.gemstone.gemfire.cache.execute.ResultCollector;
import com.jnj.adf.config.annotations.Collector;
import com.jnj.adf.config.annotations.Path;
import com.jnj.adf.config.annotations.RemoteMethod;
import com.jnj.adf.config.annotations.RemoteMethod.InvokeTypes;
import com.jnj.adf.config.annotations.RemoteServiceApi;
import com.jnj.adf.grid.indexer.SimpleLuceneParam;
import java.util.List;

@RemoteServiceApi("_lucene_direct_query_onserver_service")
public abstract interface ILuceneDirectQueryOnServerService
{
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVER)
  public abstract <T> List<T> findAll(@Path String paramString, @Collector ResultCollector<?, ?> paramResultCollector);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVER)
  public abstract <T> List<T> queryForList(@Path String paramString, @Collector ResultCollector<?, ?> paramResultCollector, SimpleLuceneParam paramSimpleLuceneParam, boolean paramBoolean);
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\query\ILuceneDirectQueryOnServerService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */