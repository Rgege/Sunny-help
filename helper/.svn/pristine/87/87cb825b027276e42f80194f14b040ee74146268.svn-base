package org.blue.helper.StringHelper.utils;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;


public class BillCodeUtil {

    /**
     *
     * @param billType
     * @param userId
     * @return
     */
    public final static  String createBillCode(String userId) {
//        Long threadId=Thread.currentThread().getId();
        String billCode="";
        StringBuffer sb=new StringBuffer();
        sb.append(userId).append("_");

        String UUID=UUidUtil.uuidStr();
        String time=DateUtil.getPureCurrentTs();
        String subfix=MD5Util.sign(time+UUID,userId);
        sb.append(subfix);
        billCode=sb.toString();
        return billCode;
    }


    public static void main(String[] args) {
        List<String> list=new ArrayList<String>();
        for (int i = 0; i <100000 ; i++) {
            String s=createBillCode("01");
            System.out.println(s);
            list.add(s);
        }
        String temp="";
        for (int i = 0; i < list.size() - 1; i++)
        {
            temp = list.get(i);
            for (int j = i + 1; j < list.size(); j++)
            {
                if (temp.equals(list.get(j)))
                {
                    System.out.println("第" + (i + 1) + "个跟第" + (j + 1) + "个重复，值是：" + temp);
                }
            }
        }
    }

}

