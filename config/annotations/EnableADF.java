package com.jnj.adf.dataservice.adfcoreignite.config.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.data.gemfire.remoteService.EnableRemoteServiceExecutions;

@Target({java.lang.annotation.ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Configuration
@Import({ADFBeanDefinitionRegister.class})
@EnableRemoteServiceExecutions
@EnableAspectJAutoProxy
public @interface EnableADF {}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\config\annotations\EnableADF.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */