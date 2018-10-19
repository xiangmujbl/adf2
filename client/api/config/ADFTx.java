package com.jnj.adf.dataservice.adfcoreignite.client.api.config;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.Transactional;

@Target({java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Transactional("adf-tx")
@Order(-100)
public @interface ADFTx {}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\client\api\config\ADFTx.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */