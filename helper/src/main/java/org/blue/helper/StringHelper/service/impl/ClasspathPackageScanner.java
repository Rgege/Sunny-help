package org.blue.helper.StringHelper.service.impl;

import org.blue.helper.StringHelper.aop.annotation.GetBean;
import org.blue.helper.StringHelper.service.PackageScanner;
import org.blue.helper.StringHelper.utils.StringPathUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

@Component
public class ClasspathPackageScanner implements PackageScanner {
    private Logger logger = LoggerFactory.getLogger(ClasspathPackageScanner.class);
    private String basePackage;
    private ClassLoader cl;

    /**
     * 初始化
     *
     * @param basePackage
     */
    public ClasspathPackageScanner() {}
    public ClasspathPackageScanner(String basePackage) {
        this.basePackage = basePackage;
        this.cl = getClass().getClassLoader();
    }

    public ClasspathPackageScanner(String basePackage, ClassLoader cl) {
        this.basePackage = basePackage;
        this.cl = cl;
    }

    /**
     * 获取指定包下的所有字节码文件的全类名
     */
    @Override
    public List<String> getFullyQualifiedClassNameList(String packageName) throws IOException {
        packageName = packageName != null ? packageName : basePackage;
        logger.info("Start scanning all classes under the package:{}", packageName);
        return doScan(packageName, new ArrayList<String>());
    }

    @Override
    public List<Class<?>> getAllClassUderPackage(String packageName) throws IOException {
        List<Class<?>> classList = new ArrayList<Class<?>>();
        List<String> classNames = this.getFullyQualifiedClassNameList(packageName);
        for (String className : classNames) {
            Class c = null;
            try {
                c = Class.forName(className);
                classList.add(c);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                continue;
            }

        }
        return classList;
    }

    @Override
    public Map<String, Object> getAnnotatedBean(String packageName, Class<? extends Annotation> annotationClass) throws IOException {
        Map<String, Object> classMap = new HashMap<String, Object>();
        List<String> classNames = this.getFullyQualifiedClassNameList(packageName);

        for (String className : classNames) {
            try {
                Class c = null;
                c = Class.forName(className);
                if (null != c.getAnnotation(annotationClass)) {
                    classMap.put(c.getSimpleName(), c.newInstance());
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                classMap.put(className.substring(className.lastIndexOf(".")+1), "class:"+className+" not found");
                continue;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                classMap.put(className.substring(className.lastIndexOf(".")+1), "can not creat class:"+className+" cause IllegalAccessException");
                continue;
            } catch (InstantiationException e) {
                e.printStackTrace();
                classMap.put(className.substring(className.lastIndexOf(".")+1), "can not creat class:"+className+" cause InstantiationException");
                continue;
            }
        }


        return classMap;
    }

    /**
     * doScan
     *
     * @param basePackage
     * @param nameList
     * @return
     * @throws IOException
     */
    private List<String> doScan(String basePackage, List<String> nameList) throws IOException {
        String splashPath = StringPathUtil.dotToSplash(basePackage);
//        URL url = cl.getResource(splashPath);   //file:/D:/WorkSpace/java/ScanTest/target/classes/com/scan
        URL url = this.getClass().getClassLoader().getResource(splashPath);
        String filePath = StringPathUtil.getRootPath(url);
        List<String> names = null; // contains the name of the class file. e.g., Apple.class will be stored as "Apple"
        if (isJarFile(filePath)) {// 先判断是否是jar包，如果是jar包，通过JarInputStream产生的JarEntity去递归查询所有类
            if (logger.isDebugEnabled()) {
                logger.debug("{} is a jar", filePath);
            }
            names = readFromJarFile(filePath, splashPath);
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("{} is a directory", filePath);
            }
            names = readFromDirectory(filePath);
        }
        for (String name : names) {
            if (isClassFile(name)) {
                nameList.add(toFullyQualifiedName(name, basePackage));
            } else {
                doScan(basePackage + "." + name, nameList);
            }
        }
        if (logger.isDebugEnabled()) {
            for (String n : nameList) {
                logger.debug("find {}", n);
            }
        }
        return nameList;
    }

    private String toFullyQualifiedName(String shortName, String basePackage) {
        StringBuilder sb = new StringBuilder(basePackage);
        sb.append('.');
        sb.append(StringPathUtil.trimExtension(shortName));
        //打印出结果
        System.out.println(sb.toString());
        return sb.toString();
    }

    private List<String> readFromJarFile(String jarPath, String splashedPackageName) throws IOException {
        if (logger.isDebugEnabled()) {
            logger.debug("Read the class from JAR: {}", jarPath);
        }
        JarInputStream jarIn = new JarInputStream(new FileInputStream(jarPath));
        JarEntry entry = jarIn.getNextJarEntry();
        List<String> nameList = new ArrayList<String>();
        while (null != entry) {
            String name = entry.getName();
            if (name.startsWith(splashedPackageName) && isClassFile(name)) {
                nameList.add(name);
            }

            entry = jarIn.getNextJarEntry();
        }

        return nameList;
    }

    private List<String> readFromDirectory(String path) {
        File file = new File(path);
        String[] names = file.list();

        if (null == names) {
            return null;
        }

        return Arrays.asList(names);
    }

    private boolean isClassFile(String name) {
        return name.endsWith(".class");
    }

    private boolean isJarFile(String name) {
        return name.endsWith(".jar");
    }

    /**
     * For test purpose.
     */
    public static void main(String[] args) throws Exception {

        PackageScanner scan = new ClasspathPackageScanner("");
////        List<String> classList = scan.getFullyQualifiedClassNameList("");
//
//        Map<String,Object> map=scan.getAnnotatedBean("org.blue.helper.StringHelper.service",GetBean.class);
        System.out.println(scan.getAllClassUderPackage("org.blue.helper.StringHelper.service"));
    }
}
