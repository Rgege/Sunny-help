package org.blue.helper.StringHelper.aop.annotation;

import org.blue.helper.StringHelper.aop.support.CheckType;
import org.blue.helper.StringHelper.aop.support.ParamsEntity;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;
import java.util.Map;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface ParamsCheck {
    String param();
    Class paramClass() default String.class;
}
