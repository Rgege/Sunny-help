package org.blue.helper.StringHelper.aop.annotation;

import org.blue.helper.StringHelper.aop.support.CheckType;
import org.blue.helper.StringHelper.aop.support.ParamsEntity;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Require {

    String value() default CheckType.NOTNULL;
}
