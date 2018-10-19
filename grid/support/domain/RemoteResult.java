/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.support.domain;
/*     */ 
/*     */ import com.gemstone.gemfire.distributed.DistributedMember;
/*     */ import com.jnj.adf.client.api.IRemoteResults;
/*     */ import com.jnj.adf.grid.support.context.ADFRuntime;
/*     */ import com.jnj.adf.grid.support.gemfire.DataSerializerAdapter;
/*     */ import com.jnj.adf.grid.support.gemfire.DataSerializerExt;
/*     */ import com.jnj.adf.grid.support.system.ADFConfigHelper;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ 
/*     */ public class RemoteResult
/*     */   extends DataSerializerAdapter implements IRemoteResults<RemoteResult>
/*     */ {
/*     */   private static final long serialVersionUID = -3273288357545651721L;
/*     */   private boolean success;
/*     */   private String ownerName;
/*     */   private String message;
/*     */   private String memberName;
/*     */   private Object serialableObject;
/*     */   private List<RemoteResult> subResults;
/*     */   
/*     */   public static void main(String[] args)
/*     */   {
/*  29 */     RemoteResult rr = new RemoteResult();
/*  30 */     DataSerializerExt.test(RemoteResult.class, rr);
/*     */   }
/*     */   
/*     */ 
/*  34 */   private Map<String, Exception> exceptions = new HashMap();
/*     */   
/*     */   public RemoteResult()
/*     */   {
/*  38 */     if ((StringUtils.isEmpty(this.memberName)) && (ADFRuntime.getRuntime().isServerSide()))
/*  39 */       this.memberName = ADFConfigHelper.getMemberName();
/*  40 */     this.subResults = new ArrayList();
/*  41 */     this.success = true;
/*  42 */     this.ownerName = "";
/*     */   }
/*     */   
/*     */ 
/*     */   public RemoteResult(Class clazz)
/*     */   {
/*  48 */     this();
/*  49 */     this.ownerName = clazz.getSimpleName();
/*     */   }
/*     */   
/*     */   public void addResult(RemoteResult rr)
/*     */   {
/*  54 */     if (!rr.isSuccess())
/*  55 */       setSuccess(false);
/*  56 */     this.subResults.add(rr);
/*  57 */     setMemberName(rr.getMemberName());
/*  58 */     setSerialableObject(rr.getSerialableObject());
/*     */   }
/*     */   
/*     */   public boolean isSuccess()
/*     */   {
/*  63 */     return this.success;
/*     */   }
/*     */   
/*     */   public void setSuccess(boolean success)
/*     */   {
/*  68 */     this.success = success;
/*     */   }
/*     */   
/*     */   public String getMessage()
/*     */   {
/*  73 */     if (StringUtils.isEmpty(this.message))
/*     */     {
/*  75 */       this.message = dump();
/*  76 */       return this.message;
/*     */     }
/*  78 */     int size = this.subResults != null ? this.subResults.size() : 0;
/*  79 */     return this.message + " subSize:" + size;
/*     */   }
/*     */   
/*     */   public void setMessage(String message)
/*     */   {
/*  84 */     this.message = message;
/*     */   }
/*     */   
/*     */   public String getMemberName()
/*     */   {
/*  89 */     return this.memberName;
/*     */   }
/*     */   
/*     */   public void setMemberName(String memberName)
/*     */   {
/*  94 */     this.memberName = memberName;
/*     */   }
/*     */   
/*     */   public List<RemoteResult> getSubResults()
/*     */   {
/*  99 */     return this.subResults;
/*     */   }
/*     */   
/*     */   public void setSubResults(List<RemoteResult> subResults)
/*     */   {
/* 104 */     this.subResults = subResults;
/*     */   }
/*     */   
/*     */ 
/*     */   public Iterator<RemoteResult> iterator()
/*     */   {
/* 110 */     return this.subResults.iterator();
/*     */   }
/*     */   
/*     */ 
/*     */   public RemoteResult singleResult()
/*     */   {
/* 116 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */   public List<RemoteResult> getResults()
/*     */   {
/* 122 */     return this.subResults;
/*     */   }
/*     */   
/*     */ 
/*     */   public void addRemoteResults(IRemoteResults<RemoteResult> results)
/*     */   {
/* 128 */     addResult((RemoteResult)results);
/*     */   }
/*     */   
/*     */ 
/*     */   public void addException(DistributedMember dm, Exception ex)
/*     */   {
/* 134 */     this.exceptions.put(dm.getId(), ex);
/*     */   }
/*     */   
/*     */ 
/*     */   public Map<String, Exception> getExceptions()
/*     */   {
/* 140 */     return this.exceptions;
/*     */   }
/*     */   
/*     */   public Object getSerialableObject()
/*     */   {
/* 145 */     return this.serialableObject;
/*     */   }
/*     */   
/*     */   public void setSerialableObject(Object serialableObject)
/*     */   {
/* 150 */     this.serialableObject = serialableObject;
/*     */   }
/*     */   
/*     */   public String dump()
/*     */   {
/* 155 */     StringBuilder builder = new StringBuilder("\nMember \t Owner \t Success \t Message\n");
/* 156 */     dump(this, 1, builder);
/* 157 */     return builder.toString();
/*     */   }
/*     */   
/*     */   protected void dump(RemoteResult r, int level, StringBuilder bu)
/*     */   {
/* 162 */     if ((r.getSubResults() == null) || (r.getSubResults().size() == 0))
/* 163 */       return;
/* 164 */     for (RemoteResult item : r.subResults)
/*     */     {
/*     */ 
/* 167 */       bu.append(item.memberName).append("\t").append(item.ownerName).append("\t").append(item.success).append("\t").append(item.message).append("\n");
/* 168 */       dump(item, level++, bu);
/*     */     }
/*     */   }
/*     */   
/*     */   public String getOwnerName()
/*     */   {
/* 174 */     return this.ownerName;
/*     */   }
/*     */   
/*     */   public void setOwnerName(String ownerName)
/*     */   {
/* 179 */     this.ownerName = ownerName;
/*     */   }
/*     */   
/*     */   public String toSimpleString()
/*     */   {
/* 184 */     return 
/*     */     
/* 186 */       "RemoteResult [success=" + this.success + ", ownerName=" + this.ownerName + ", message=" + this.message + ", memberName=" + this.memberName + ", serialableObject=" + this.serialableObject + ", subResults=" + (this.subResults != null ? this.subResults.size() : 0) + ", exceptions=" + (this.exceptions != null ? this.exceptions.size() : 0) + "]";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 193 */     return "RemoteResult [success=" + this.success + ", ownerName=" + this.ownerName + ", message=" + this.message + ", memberName=" + this.memberName + "]";
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\support\domain\RemoteResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */