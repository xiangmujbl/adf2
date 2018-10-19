/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.client.api.impl;
/*    */ 
/*    */ import com.gemstone.gemfire.DataSerializable;
/*    */ import com.gemstone.gemfire.DataSerializer;
/*    */ import com.gemstone.gemfire.distributed.DistributedMember;
/*    */ import com.jnj.adf.client.api.IRemoteResults;
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RemoteResultsImpl<T>
/*    */   implements IRemoteResults<T>, DataSerializable
/*    */ {
/*    */   private static final long serialVersionUID = 3621863289716980967L;
/* 39 */   private ArrayList<T> list = new ArrayList();
/* 40 */   private Map<String, Exception> exceptions = new HashMap();
/*    */   
/*    */ 
/*    */   public void addRemoteResults(IRemoteResults<T> other)
/*    */   {
/* 45 */     this.list.addAll(other.getResults());
/*    */   }
/*    */   
/*    */   public void addResult(T item)
/*    */   {
/* 50 */     this.list.add(item);
/*    */   }
/*    */   
/*    */ 
/*    */   public T singleResult()
/*    */   {
/* 56 */     return (T)(this.list.size() > 0 ? this.list.get(0) : null);
/*    */   }
/*    */   
/*    */ 
/*    */   public List<T> getResults()
/*    */   {
/* 62 */     return this.list;
/*    */   }
/*    */   
/*    */   public void toData(DataOutput out)
/*    */     throws IOException
/*    */   {
/* 68 */     DataSerializer.writeArrayList(this.list, out);
/*    */   }
/*    */   
/*    */   public void fromData(DataInput in)
/*    */     throws IOException, ClassNotFoundException
/*    */   {
/* 74 */     this.list = DataSerializer.readArrayList(in);
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 79 */     return "RemoteResultsImpl [list=" + this.list + "]";
/*    */   }
/*    */   
/*    */   public Iterator<T> iterator()
/*    */   {
/* 84 */     return this.list.iterator();
/*    */   }
/*    */   
/*    */   public void addException(DistributedMember dm, Exception ex)
/*    */   {
/* 89 */     this.exceptions.put(dm.getId(), ex);
/*    */   }
/*    */   
/*    */   public Map<String, Exception> getExceptions()
/*    */   {
/* 94 */     return this.exceptions;
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\client\api\impl\RemoteResultsImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */