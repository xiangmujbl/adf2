package com.jnj.adf.dataservice.adfcoreignite.client.api.config;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({java.lang.annotation.ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ADFIgnore
{
  boolean ignore() default true;
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\client\api\config\ADFIgnore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */