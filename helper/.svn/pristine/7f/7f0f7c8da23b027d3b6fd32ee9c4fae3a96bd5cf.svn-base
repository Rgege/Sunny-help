package org.blue.helper.StringHelper.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.StringReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ArrayUtils {

    public static final int INDEX_NOT_FOUND = -1;

    /**
     * 获取下标位置
     * @param array
     * @param valuesToFind
     * @param startIndex
     * @return
     */
    public static int indexOf(final byte[]array,final byte[]valuesToFind,int startIndex){
        if (array == null || valuesToFind==null ) {
            return INDEX_NOT_FOUND;
        }
        int lenth1=array.length;
        int lenth2=valuesToFind.length;

        Byte [] bytes1={};
        Byte [] bytes2={};
        for (int i=0;i<lenth1;i++) {
            bytes1[i]=array[i];
        }
        for (int i=0;i<lenth2;i++) {
            bytes2[i]=valuesToFind[i];
        }
        Byte [] intersect={};
        intersect(bytes1,bytes2,intersect);
        if(bytes2==intersect){
            return indexOf(array,valuesToFind[0],startIndex);
        }
        return INDEX_NOT_FOUND;
    }

    /**
     *
     * @param original
     * @param from
     * @param to
     * @return
     */
    public static byte[] copyOfRange(byte[] original, int from, int to) {
        if(null==original){
            return new byte[]{};
        }
        int newLength = to - from;
        if (newLength < 0)
            throw new IllegalArgumentException(from + " > " + to);
        byte[] copy = new byte[newLength];
        System.arraycopy(original, from, copy, 0,
                Math.min(original.length - from, newLength));
        return copy;
    }

    public static LineReturn getLine(byte[]buf, int start){
        String bf=new String(buf);
        StringReader sr=new StringReader(bf.substring(start));
        LineNumberReader lr = new LineNumberReader(sr);
        String line ="";
        try {
            while (lr.readLine() !=null){
                line = lr.readLine();
                break;
            }
            return new LineReturn(line,line.length()+start);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(lr !=null){
                try {
                    lr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new LineReturn();
    }

    public static int indexOf(final byte[] array, final byte valueToFind, int startIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        for (int i = startIndex; i < array.length; i++) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    /**
     * 求并集  利用set唯一性
     * @param arr1
     * @param arr2
     * @return
     */
    public static<T> T[] union(T[] arr1, T[] arr2 ,T[] arrUnion){
        HashSet<T> hs = new HashSet<T>();
        for(T t:arr1){
            hs.add(t);
        }
        for(T t:arr2){
            hs.add(t);
        }
        return hs.toArray(arrUnion);
    }

    /**
     * 交集(注意结果集中若使用LinkedList添加，则需要判断是否包含该元素，否则其中会包含重复的元素)
     * @param arr1
     * @param arr2
     * @return
     */
    public static <T> T[] intersect(T[] arr1, T[] arr2 ,T[] arrIntersect){
        List<T> l = new LinkedList<T>();
        Set<T> common = new HashSet<T>();
        for(T t:arr1){
            if(!l.contains(t)){
                l.add(t);
            }
        }
        for(T t:arr2){
            if(l.contains(t)){
                common.add(t);
            }
        }
        return common.toArray(arrIntersect);
    }

    /**
     *  差集
     * @param arr1
     * @param arr2
     * @return
     */
    public static <T> T[] substract(T[] arr1, T[] arr2 ,T[] arrSubstract) {
        LinkedList<T> list = new LinkedList<T>();
        for (T t : arr1) {
            if(!list.contains(t)) {
                list.add(t);
            }
        }
        for (T t : arr2) {
            if (list.contains(t)) {
                list.remove(t);
            }
        }
        return list.toArray(arrSubstract);
    }


    public static class LineReturn{
        public String line="";
        public int endIndex;

        public LineReturn() {}
        public LineReturn(String line,int endIndex) {
            this.line = line;
            this.endIndex=endIndex;
        }
    }
}

