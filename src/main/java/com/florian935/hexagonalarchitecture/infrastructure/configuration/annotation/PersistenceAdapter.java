package com.florian935.hexagonalarchitecture.infrastructure.configuration.annotation;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Configuration
public @interface PersistenceAdapter {

    @AliasFor(annotation = Configuration.class)
    String value() default "";
}
