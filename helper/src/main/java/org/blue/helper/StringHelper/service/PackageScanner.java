package org.blue.helper.StringHelper.service;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;

public interface PackageScanner {
    /**
     * 获取全部对象名称
     * @param packageName
     * @return
     * @throws IOException
     */
    public List<String> getFullyQualifiedClassNameList(String packageName) throws IOException;

    public List<Class<?>> getAllClassUderPackage(String packageName) throws IOException;

    /**
     * 获取有特定注解的对象
     * @param packageName
     * @param annotationClass
     * @return
     * @throws IOException
     */
    public Map<String,Object> getAnnotatedBean(String packageName,Class<? extends Annotation> annotationClass)throws IOException;
}