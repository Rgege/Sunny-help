package org.blue.helper.study.java.jvm;

/**
 * @Description <P>运行时常量池</P>
 * @Author v-Rui.Xiong@bl.com
 * @Date 2018/12/24
 * @Version 1.0.0
 **/
public class RunTimeConstantPool {
    public static void main(String[] args) {
        String str="AAA";
        String in=str.intern();
        System.out.println(in);
    }
}
