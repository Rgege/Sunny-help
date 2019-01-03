package org.blue.helper.StringHelper.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NeedLogin {
    /**
     * 是否登录校验
     * @return
     */
    boolean checkLogin() default true;
}
