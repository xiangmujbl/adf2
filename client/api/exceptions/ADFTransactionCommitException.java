/*    */ package com.jnj.adf.dataservice.adfcoreignite.client.api.exceptions;
/*    */ 
/*    */ import org.springframework.transaction.TransactionException;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ADFTransactionCommitException
/*    */   extends TransactionException
/*    */ {
/*    */   private static final long serialVersionUID = 7779409977713306153L;
/*    */   
/*    */   public ADFTransactionCommitException(String msg)
/*    */   {
/* 37 */     super(msg);
/*    */   }
/*    */   
/*    */   public ADFTransactionCommitException(String message, Throwable cause) {
/* 41 */     super(message, cause);
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\client\api\exceptions\ADFTransactionCommitException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */