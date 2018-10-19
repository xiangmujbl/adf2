package com.jnj.adf.dataservice.adfcoreignite.config.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.stereotype.Component;

@Target({java.lang.annotation.ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
@Inherited
public @interface RemoteService
{
  String value() default "";
  
  boolean securityCheckFlag() default true;
  
  boolean optimizeForWrite() default true;
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\config\annotations\RemoteService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */