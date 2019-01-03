package org.blue.helper.StringHelper.utils;

import org.blue.helper.HelperApplication;
import org.springframework.context.ApplicationContext;

/**
 * 以Spring的方式获取bean  避免new方式获取的bean中由Spring注入的属性为空
 */
public class SpringUtil {

    private static ApplicationContext applicationContext = HelperApplication.ctx;

    static {
        if (SpringUtil.applicationContext == null) {
            SpringUtil.applicationContext = HelperApplication.ctx;
        }
    }

    //获取applicationContext

    public static ApplicationContext getApplicationContext() {

        return applicationContext;

    }


    //通过name获取 Bean.

    public static Object getBean(String name) {

        return getApplicationContext().getBean(name);

    }


    //通过class获取Bean.

    public static <T> T getBean(Class<T> clazz) {

        return getApplicationContext().getBean(clazz);

    }


    //通过name,以及Clazz返回指定的Bean

    public static <T> T getBean(String name, Class<T> clazz) {

        return getApplicationContext().getBean(name, clazz);

    }


}

