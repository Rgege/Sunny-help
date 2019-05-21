package org.blue.helper.study.java.jvm;

import java.util.ArrayList;
import java.util.List;

public class HeapOOM {
    static class OOMObject{
    }

    public static void main(String[] args) {
        List<OOMObject> list=new ArrayList<OOMObject>();
        while (true){
            try {
                list.add(new OOMObject());
            }catch (StackOverflowError e){
                System.out.println(list.size());
                e.printStackTrace();
                break;
            }catch (OutOfMemoryError e){
                System.out.println(list.size());
                e.printStackTrace();
                break;
            }

        }
    }
}
