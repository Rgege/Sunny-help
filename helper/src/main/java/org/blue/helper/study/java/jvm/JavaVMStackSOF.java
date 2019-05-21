package org.blue.helper.study.java.jvm;

public class JavaVMStackSOF {
    private int stackLength=1;

    public void stackLeak(){
        this.stackLength ++;
        this.stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackSOF sof=new JavaVMStackSOF();
        try {
            sof.stackLeak();
        }catch (StackOverflowError e){
            System.out.println("stack length："+sof.stackLength);
            e.printStackTrace();
        }catch (OutOfMemoryError e){
            System.out.println("stack length："+sof.stackLength);
            e.printStackTrace();
        }
    }
}
