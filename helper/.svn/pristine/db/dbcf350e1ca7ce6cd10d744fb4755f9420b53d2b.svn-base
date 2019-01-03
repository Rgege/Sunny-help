package org.blue.helper.StringHelper.utils;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

public class BeanConverUtil {

    /**
     * 将任意类型转换成字符串
     *
     * @param value
     * @param <T>
     * @return
     */
    public static <T> String beanToString(T value) {
        Class<?> clazz = value.getClass();
        if (clazz == int.class || clazz == Integer.class) {
            return value + "";
        } else if (clazz == String.class) {
            return (String) value;
        } else if (clazz == long.class || clazz == Long.class) {
            return value + "";
        } else {
            return JSON.toJSONString(value);
        }
    }

    /**
     * 把一个字符串转换成bean对象
     *
     * @param str
     * @param <T>
     * @return
     */
    public static <T> T stringToBean(String str, Class<T> clazz) {
        if (str == null || str.length() <= 0 || clazz == null) {
            return null;
        }
        if (clazz == int.class || clazz == Integer.class) {
            return (T) Integer.valueOf(str);
        } else if (clazz == String.class) {
            return (T) str;
        } else if (clazz == long.class || clazz == Long.class) {
            return (T) Long.valueOf(str);
        } else {
            return JSON.toJavaObject(JSON.parseObject(str), clazz);
        }
    }


    public static <T> List<T> listConver(List<String> list, Class<T> clazz){
        List<T> objList=new ArrayList<T>();
        if(list ==null || list.isEmpty())
            return objList;
        else

        for (String strObj : list) {
            T t=stringToBean(strObj,clazz);
            objList.add(t);
        }

        return objList;
    }
    public static <T> List<String> toStringList(List<T> list){
        List<String> strList=new ArrayList<String>();
        if(list ==null || list.isEmpty())
            return strList;
        else

            for (T t : list) {
                String str=beanToString(t);
                strList.add(str);
            }

        return strList;
    }
}
