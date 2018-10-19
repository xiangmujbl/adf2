package com.jnj.adf.dataservice.adfcoreignite.grid.query;

import com.jnj.adf.config.annotations.Path;
import com.jnj.adf.config.annotations.RemoteMethod;
import com.jnj.adf.config.annotations.RemoteServiceApi;
import com.jnj.adf.grid.indexer.SimpleLuceneParam;

@RemoteServiceApi("_lucene_2step_service")
public abstract interface ILucene2StepService
{
  @RemoteMethod
  public abstract ServerPageResults remoteQueryPage(@Path(true) String paramString, SimpleLuceneParam paramSimpleLuceneParam);
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\query\ILucene2StepService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */