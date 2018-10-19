/*    */ package com.jnj.adf.dataservice.adfcoreignite.config.annotations;
/*    */ 
/*    */ import java.lang.annotation.Annotation;
/*    */ import java.lang.annotation.Inherited;
/*    */ import java.lang.annotation.Retention;
/*    */ import java.lang.annotation.RetentionPolicy;
/*    */ import java.lang.annotation.Target;
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
/*    */ @Target({java.lang.annotation.ElementType.METHOD})
/*    */ @Retention(RetentionPolicy.RUNTIME)
/*    */ @Inherited
/*    */ public @interface RemoteMethod
/*    */ {
/*    */   InvokeTypes type() default InvokeTypes.ON_SERVERS;
/*    */   
/*    */   String path() default "";
/*    */   
/*    */   boolean extractResult() default true;
/*    */   
/*    */   CollectorTypes collectorType() default CollectorTypes.NULL;
/*    */   
/*    */   public static enum InvokeTypes
/*    */   {
/* 37 */     ON_SERVER,  ON_REGION,  ON_SERVERS;
/*    */     
/*    */     private InvokeTypes() {} }
/*    */   
/* 41 */   public static enum CollectorTypes { NULL,  SUM;
/*    */     
/*    */     private CollectorTypes() {}
/*    */   }
/*    */ }


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\config\annotations\RemoteMethod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */