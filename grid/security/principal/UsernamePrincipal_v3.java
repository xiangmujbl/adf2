/*     */ package com.jnj.adf.dataservice.adfcoreignite.grid.security.principal;
/*     */ 
/*     */ import com.gemstone.gemfire.DataSerializable;
/*     */ import com.gemstone.gemfire.DataSerializer;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Sets;
/*     */ import com.jnj.adf.grid.auth.AuthKeyConstants.GridPermission;
/*     */ import com.jnj.adf.grid.support.gemfire.DataSerializerExt;
/*     */ import com.jnj.adf.grid.utils.KryoUtils;
/*     */ import com.jnj.adf.grid.utils.VersionUtils;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import java.security.Principal;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class UsernamePrincipal_v3
/*     */   implements Principal, DataSerializable
/*     */ {
/*     */   private static final long serialVersionUID = -5326836425852138873L;
/*     */   private String userName;
/*     */   private Set<String> roles;
/*  31 */   private Map<AuthKeyConstants.GridPermission, Set<String>> gridPermissionStringMap = Maps.newConcurrentMap();
/*     */   
/*     */   public UsernamePrincipal_v3(String userName)
/*     */   {
/*  35 */     this.userName = userName;
/*     */   }
/*     */   
/*     */   public boolean containsPermission(AuthKeyConstants.GridPermission permission)
/*     */   {
/*  40 */     return this.gridPermissionStringMap.containsKey(permission);
/*     */   }
/*     */   
/*     */   public String getName()
/*     */   {
/*  45 */     return this.userName;
/*     */   }
/*     */   
/*     */   public String getUserName()
/*     */   {
/*  50 */     return this.userName;
/*     */   }
/*     */   
/*     */   public Set<String> getRoles()
/*     */   {
/*  55 */     return this.roles;
/*     */   }
/*     */   
/*     */   public void setRoles(Set<String> roles)
/*     */   {
/*  60 */     this.roles = roles;
/*     */   }
/*     */   
/*     */   public Set<String> getOperations(AuthKeyConstants.GridPermission permission)
/*     */   {
/*  65 */     return (Set)this.gridPermissionStringMap.get(permission);
/*     */   }
/*     */   
/*     */   public void addOperations(AuthKeyConstants.GridPermission permission, Set<String> operations)
/*     */   {
/*  70 */     Set<String> oldOperations = (Set)this.gridPermissionStringMap.get(permission);
/*  71 */     if (oldOperations == null)
/*     */     {
/*  73 */       this.gridPermissionStringMap.put(permission, Sets.newHashSet(operations));
/*     */     }
/*     */     else
/*     */     {
/*  77 */       oldOperations.addAll(operations);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/*  84 */     return this.userName;
/*     */   }
/*     */   
/*     */   public void toData(DataOutput out)
/*     */     throws IOException
/*     */   {
/*  90 */     String version = VersionUtils.getVersion();
/*  91 */     if (VersionUtils.below_v_0_3_2(version))
/*     */     {
/*  93 */       DataSerializerExt.toData(getClass(), this, out);
/*     */     }
/*     */     else
/*     */     {
/*  97 */       byte[] bts = KryoUtils.toBytes(this);
/*  98 */       DataSerializer.writeByteArray(bts, out);
/*     */     }
/*     */   }
/*     */   
/*     */   public void fromData(DataInput in)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/* 105 */     String version = VersionUtils.getVersion();
/* 106 */     if (VersionUtils.below_v_0_3_2(version))
/*     */     {
/* 108 */       DataSerializerExt.fromData(getClass(), this, in);
/*     */     }
/*     */     else
/*     */     {
/* 112 */       byte[] bts = DataSerializer.readByteArray(in);
/* 113 */       KryoUtils.toObject(bts, this);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\security\principal\UsernamePrincipal_v3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */