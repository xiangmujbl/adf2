package com.jnj.adf.dataservice.adfcoreignite.grid.query;

import com.jnj.adf.config.annotations.Path;
import com.jnj.adf.config.annotations.RemoteMethod;
import com.jnj.adf.config.annotations.RemoteMethod.CollectorTypes;
import com.jnj.adf.config.annotations.RemoteMethod.InvokeTypes;
import com.jnj.adf.config.annotations.RemoteServiceApi;
import com.jnj.adf.grid.indexer.SimpleLuceneParam;

@RemoteServiceApi("_lucene_index_matrix_service")
public abstract interface ILuceneIndexMatrixService
{
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVER)
  public abstract ServerPageResults queryPageOnServer(@Path(true) String paramString, SimpleLuceneParam paramSimpleLuceneParam);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_REGION, collectorType=RemoteMethod.CollectorTypes.SUM)
  public abstract Long count(@Path(true) String paramString, SimpleLuceneParam paramSimpleLuceneParam);
  
  @RemoteMethod(type=RemoteMethod.InvokeTypes.ON_SERVER)
  public abstract void clearCacheOnServer(@Path(true) String paramString, SimpleLuceneParam paramSimpleLuceneParam);
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\query\ILuceneIndexMatrixService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */