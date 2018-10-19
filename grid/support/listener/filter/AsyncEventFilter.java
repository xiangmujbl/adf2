package com.jnj.adf.dataservice.adfcoreignite.grid.support.listener.filter;

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
public @interface AsyncEventFilter {}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\support\listener\filter\AsyncEventFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */