package org.blue.helper.test.util;

import org.blue.helper.StringHelper.persistence.entity.model.BillRecord;
import org.blue.helper.StringHelper.persistence.entity.model.BillRecordDay;
import org.blue.helper.StringHelper.persistence.entity.model.BillRecordMonth;

import java.lang.reflect.Method;

public class Util {

    public static void set(String prefix,Class clazz){
        Method[] methods=clazz.getMethods();
        int i=0;
        for (Method method:methods) {
            if(method.getName().startsWith("set") || method.getName().startsWith("is")){
                System.out.println(prefix+"."+method.getName()+"();");
                i++;
            }
        }
        System.out.println(i);
    }

    public static void main(String[] args) {
        set("billRecordDay",BillRecordDay.class);
    }
}
