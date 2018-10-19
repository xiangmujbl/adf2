/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.common;
/*    */ 
/*    */ import com.gemstone.gemfire.cache.Cache;
/*    */ import com.gemstone.gemfire.cache.CacheFactory;
/*    */ import com.gemstone.gemfire.distributed.DistributedMember;
/*    */ import com.gemstone.gemfire.distributed.DistributedSystem;
/*    */ import com.gemstone.gemfire.internal.HeapDataOutputStream;
/*    */ import com.gemstone.gemfire.internal.Version;
/*    */ import com.jnj.adf.grid.support.context.ADFRuntime;
/*    */ import com.jnj.adf.grid.utils.LogUtil;
/*    */ import java.util.Iterator;
/*    */ import java.util.Set;
/*    */ import org.apache.logging.log4j.Logger;
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
/*    */ public class GemfireUtil
/*    */ {
/*    */   public static final HeapDataOutputStream createHeapDataOutputStream()
/*    */   {
/* 34 */     return new HeapDataOutputStream(Version.CURRENT);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public static DistributedMember getAnyMemberOnServerSide()
/*    */   {
/* 42 */     Cache c = CacheFactory.getAnyInstance();
/* 43 */     if (ADFRuntime.getRuntime().isLocatorSide()) {
/* 44 */       Set<DistributedMember> members = c.getMembers();
/* 45 */       LogUtil.getCoreLog().trace(" Get distributeMembers members:{}", new Object[] { members });
/* 46 */       if ((members != null) && (!members.isEmpty())) {
/* 47 */         DistributedMember member = (DistributedMember)members.iterator().next();
/* 48 */         return member;
/*    */       }
/* 50 */     } else if (ADFRuntime.getRuntime().isDataStoreSide()) {
/* 51 */       DistributedSystem ds = c.getDistributedSystem();
/* 52 */       if (ds != null) {
/* 53 */         LogUtil.getCoreLog().trace("Get distributeMembers member:{}", new Object[] { ds.getDistributedMember() });
/* 54 */         return ds.getDistributedMember();
/*    */       }
/*    */     } else {
/* 57 */       throw new ADFException("only called on server side.");
/*    */     }
/*    */     
/* 60 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\common\GemfireUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */