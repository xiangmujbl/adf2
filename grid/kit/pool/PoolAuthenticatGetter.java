/*    */ package com.jnj.adf.dataservice.adfcoreignite.grid.kit.pool;
/*    */ 
/*    */ import com.jnj.adf.grid.auth.exception.AuthException;
/*    */ import com.jnj.adf.grid.kit.IAuthenticateGetter;
/*    */ import com.jnj.adf.grid.master.manager.MasterAuthenticateManager;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ public class PoolAuthenticatGetter
/*    */   extends AbstractRegister
/*    */   implements IAuthenticateGetter
/*    */ {
/*    */   public boolean authenticate(String username, String password)
/*    */     throws AuthException
/*    */   {
/* 16 */     return MasterAuthenticateManager.getInstance().authenticate(username, password);
/*    */   }
/*    */   
/*    */   public Set<String> getUserGroups(String username) throws AuthException
/*    */   {
/* 21 */     return MasterAuthenticateManager.getInstance().getUserGroupsByUsername(username);
/*    */   }
/*    */   
/*    */   public Set<String> getUserPrivilege(String username) throws AuthException
/*    */   {
/* 26 */     return MasterAuthenticateManager.getInstance().getUserRolesByUsername(username);
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\kit\pool\PoolAuthenticatGetter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */