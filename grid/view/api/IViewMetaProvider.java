package com.jnj.adf.dataservice.adfcoreignite.grid.view.api;

import com.jnj.adf.grid.view.domain.DomainDef;
import com.jnj.adf.grid.view.domain.ViewColumn;
import com.jnj.adf.grid.view.domain.ViewColumnMapping;
import com.jnj.adf.grid.view.domain.ViewGroup;
import com.jnj.adf.grid.view.domain.ViewInfo;
import com.jnj.adf.grid.view.domain.ViewMeta;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract interface IViewMetaProvider
{
  public abstract boolean isView(String paramString);
  
  public abstract void addViewBaseInfo(ViewInfo paramViewInfo);
  
  public abstract void updateViewBaseInfo(ViewInfo paramViewInfo);
  
  public abstract void deleteViewBaseInfo(String paramString);
  
  public abstract ViewInfo getBaseInfo(String paramString);
  
  public abstract List<ViewInfo> listBaseInfos(int paramInt1, int paramInt2);
  
  public abstract List<ViewColumn> listColumns(String paramString);
  
  public abstract void addColumn(ViewColumn paramViewColumn);
  
  public abstract void addColumns(List<ViewColumn> paramList);
  
  public abstract void updateColumn(ViewColumn paramViewColumn);
  
  public abstract void deleteColumn(String paramString1, String paramString2);
  
  public abstract List<ViewGroup> listUnionGroups(String paramString);
  
  public abstract void addUnionGroup(ViewGroup paramViewGroup);
  
  public abstract void addUnionGroups(List<ViewGroup> paramList);
  
  public abstract void updateUnionGroup(ViewGroup paramViewGroup);
  
  public abstract void deleteUnionGroup(String paramString1, String paramString2);
  
  public abstract Set<String> listMaiPaths(String paramString);
  
  public abstract Set<String> listPaths(String paramString1, String paramString2);
  
  public abstract List<ViewColumnMapping> listColumnMappings(String paramString1, String paramString2);
  
  public abstract void addColumnMapping(ViewColumnMapping paramViewColumnMapping);
  
  public abstract void addColumnMappings(List<ViewColumnMapping> paramList);
  
  public abstract void deleteColumnMapping(String paramString1, String paramString2, String paramString3);
  
  public abstract List<DomainDef> listDomainDefs();
  
  public abstract DomainDef getDomainDef(String paramString);
  
  public abstract void addDomainDef(DomainDef paramDomainDef);
  
  public abstract void updateDomainDef(DomainDef paramDomainDef);
  
  public abstract void deleteDomainDef(String paramString);
  
  public abstract List<ViewMeta> getViewMeta(String paramString);
  
  public abstract List<ViewMeta> listViewMetas(String paramString);
  
  public abstract Map<String, List<ViewMeta>> listAllViewMetas();
  
  public abstract String[] getInterestingColumns(String paramString1, String paramString2);
  
  public abstract Map<String, String> listRules();
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\view\api\IViewMetaProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */