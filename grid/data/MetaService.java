package com.jnj.adf.dataservice.adfcoreignite.grid.data;

import com.gemstone.gemfire.pdx.PdxInstance;
import com.jnj.adf.grid.data.schema.ProcessorTypes;
import java.util.Map;

public abstract interface MetaService
{
  public abstract void enableTemporal(String paramString, boolean paramBoolean);
  
  public abstract boolean temporalEnabled(String paramString);
  
  public abstract void registerReadSchema(String paramString, Map<String, String> paramMap1, Map<String, String> paramMap2);
  
  public abstract Map<String, String> getScheam(String paramString);
  
  public abstract <V> V applyReadSchema(String paramString, PdxInstance paramPdxInstance, Class<V> paramClass);
  
  public abstract <V> String applyWriteSchema(String paramString, V paramV);
  
  public abstract <V> String applyWriteSchema(String paramString, V paramV, double paramDouble1, double paramDouble2);
  
  public abstract <V> Map<String, Object> applyWriteSchemaAsMap(String paramString, V paramV);
  
  public abstract void afterDataStoreStarted();
  
  public abstract void enableAQProcessor(ProcessorTypes paramProcessorTypes, boolean paramBoolean);
  
  public abstract boolean aqProcessorEnabled(ProcessorTypes paramProcessorTypes);
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\data\MetaService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */