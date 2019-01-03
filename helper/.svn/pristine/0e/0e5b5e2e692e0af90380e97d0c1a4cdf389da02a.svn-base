package org.blue.helper.StringHelper.config;

import org.blue.helper.StringHelper.aop.annotation.StartInit;
import org.blue.helper.StringHelper.service.PackageScanner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

/**
 * springboot 提供的一种实现 主要是处理项目启动后执行的功能  用法是实现CommandLineRunner类 并实例化run方法
 * 在run方法中编写需要项目启动后就执行的逻辑
 * 如有多个类实现了CommandLineRunner  当需要保证顺序时用@Order注解 此注解提供了value参数 执行顺序为按value值
 * 由小到大执行
 *
 *
 * @ClassName : CommandLineRunnerConfig
 * @Author : XR33
 * @Date : 2018/9/5 10:49
 */
@Configuration
public class CommandLineRunnerConfig {
    private static final String scannerPackge="org.blue.helper.StringHelper.service";

//    @Bean
//    CommandLineRunner init(final StorageService storageService) {
//        return new CommandLineRunner() {
//            @Override
//            public void run(String... args) {
//                try {
//                    storageService.deleteAll();
//                }catch (Exception e){
//
//                }finally {
//                    storageService.init();
//                }
//            }
//        };
//    }
//    /**
//     *
//     * @Decription : 启动时扫描带有@StartInit注解的方法 并执行
//     *               由于执行此方法的对象是反射手动生成的 所以方法中所有使用spring注解注入的值以及属性全部不能用
//     *               目前只能将这些方法直接放在这里调用   正在寻找解决办法
//     * @Param :
//     * @Return :
//     * @Modified :
//     * @Throws:
//     * @Date : 2018/9/5 17:58
//     */
//    @Bean
//    CommandLineRunner commandLineRunner(final PackageScanner packageScanner){
//        return new CommandLineRunner() {
//            @Override
//            public void run(String... args) throws Exception {
//                List<Class<?>> list = packageScanner.getAllClassUderPackage(scannerPackge);
//                List<InitMethod> methodList=new ArrayList<InitMethod>();
//                for (Class c:list) {
//                    Method[] methods=c.getMethods();
//                    for (Method method:methods) {
//                        if(methodIsAnnotated(method,StartInit.class)){
//                            InitMethod initMethod=new InitMethod();
//                            initMethod.setMethod(method);
//                            initMethod.setMethodClass(c);
//                            initMethod.setOrder(method.getAnnotation(StartInit.class).order());
//                            methodList.add(initMethod);
//                        }
//                    }
//                }
//                //根据order升序排序确定初始化顺序
//                Collections.sort(methodList,new Comparator<InitMethod>() {
//                    @Override
//                    public int compare(InitMethod o1, InitMethod o2) {
//                        return o1.getOrder().compareTo(o2.getOrder());
//                    }
//                });
//                for (InitMethod initMethod:methodList) {
//                    Method m=initMethod.getMethod();
//                    Class c=initMethod.getMethodClass();
//                    Object obj=c.newInstance();
//                    m.invoke(obj,new Object[]{});
//                }
//            }
//        };
//    }

    private boolean methodIsAnnotated(Method method,Class<? extends Annotation> annotationClass){
        boolean b=false;
        if (method ==null || annotationClass == null) return b;
        if (null != method.getAnnotation(annotationClass)) {
            b=true;
        }
        return b;
    }

    class InitMethod {

        private Class methodClass;
        private Method method;
        private Integer order;

        public Class getMethodClass() {
            return methodClass;
        }

        public void setMethodClass(Class methodClass) {
            this.methodClass = methodClass;
        }

        public Method getMethod() {
            return method;
        }

        public void setMethod(Method method) {
            this.method = method;
        }

        public Integer getOrder() {
            return order;
        }

        public void setOrder(Integer order) {
            this.order = order;
        }
    }
}
