package org.blue.helper.StringHelper.common.databaseStructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.*;

/**
 * 循环链表简单实现
 */
public class CircularLinkedList<T> implements Serializable {

    private static final Logger LOGGER=LoggerFactory.getLogger(CircularLinkedList.class);
    private static final long serialVersionUID = -1869523773769794788L;

    private int size;
    private transient Map<Integer,Element> elementMap;

    public CircularLinkedList() {
        this.elementMap = new HashMap<Integer,Element>();
    }

    public CircularLinkedList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementMap = new HashMap<Integer,Element>(initialCapacity);
        } else if (initialCapacity == 0) {
            this.elementMap = new HashMap<Integer,Element>();
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "+
                    initialCapacity);
        }
    }
    private class Element {
        public int index;
        public T value = null;
        private Element next = null;
    }


    /**
     * header element
     */
    private Element header = null;


    /**
     * 插入链表
     */
    public void add(T t) {
        Element e = new Element();
        e.value = t;
        e.index=this.size;
        if (this.elementMap.isEmpty()){//第一次插入
            e.next = e;
            this.header=e;
        } else{
            //temp引用在栈中，temp和header引用都指向堆中的initList()中new的Element对象
            Element temp = this.elementMap.get(0);
            while (temp.next != header){//获取最后一个元素
                temp = temp.next;
            }
            temp.next = e;
            e.next = header;//新插入的最后一个节点指向头结点

        }
        elementMap.put(e.index,e);
        this.size ++;
    }
    /**
     * 链表长度
     */
    public int size() {
        return this.size;
    }

    /**
     * 判断链表中是否存在某元素
     */
    public Boolean isContain(T t) {
        if (t==null) throw new IllegalArgumentException("Param can not be null");
        for (int i = 0; i <this.size ; i++) {
            if (t.equals(this.elementMap.get(i).value)){
                return true;
            }
        }
        return false;
    }

    public T get(int index){
        if(index >= size()) throw  new IndexOutOfBoundsException(outOfBoundsMsg(index));
        if(index <0 ) throw  new IllegalArgumentException();

        return this.elementMap.get(index).value;
    }

    public T getNext(int index){
        if(index >= size()) throw  new IndexOutOfBoundsException(outOfBoundsMsg(index));
        if(index <0 ) throw  new IllegalArgumentException();
        return this.elementMap.get(index).next.value;
    }

    public T getBefor(int index){
        if(index >= size()) throw  new IndexOutOfBoundsException(outOfBoundsMsg(index));
        if(index <0 ) throw  new IllegalArgumentException();
        Element element=null;
        for (int i = 0; i <this.size ; i++) {
            element=this.elementMap.get(i);
            if (element.next.index==index){
                break;
            }
        }
        return element==null ? null : element.value;
    }

    public List<T> rangeList(int startIndex,int length){
        List<T> list=new ArrayList<T>();
        if(length>= size() ||startIndex<0 ||length<=0) throw  new IllegalArgumentException();
        if (startIndex>= size()){
            startIndex=0;
        }
        Element e=this.elementMap.get(startIndex);
        list.add(e.value);
        if (length==1) {
            return list;
        }else {
            int z=length-1;
            for (int i = 0; i <z ; i++) {
                e=e.next;
                list.add(e.value);
            }
        }
        return list;
    }

    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }

    @Override
    public String toString() {
        StringBuffer sb=new StringBuffer("CircularLinkedList{");
        for (int i = 0; i <size ; i++) {
            sb.append("[").append("\"index\":").append(this.elementMap.get(i).index).append(";");
            sb.append("\"value\":").append(this.elementMap.get(i).value).append(";");
            sb.append("\"next\":").append(this.elementMap.get(i).next.index).append(";");
            if (i==this.size-1){
                sb.append("]");
                break;
            }else {
                sb.append("],");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    public static void main(String[] args) {
        List<String> list=new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("f");
        CircularLinkedList<String> billList = new CircularLinkedList<String>(list.size());
        for (String s : list) {
            billList.add(s);
        }
        for (int i = 0; i <billList.size() ; i++) {
            System.out.println(billList.rangeList(i,3));
        }
    }
}














