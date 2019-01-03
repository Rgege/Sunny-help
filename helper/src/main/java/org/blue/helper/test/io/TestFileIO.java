package org.blue.helper.test.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestFileIO {
    public static void testDriver() throws IOException {
        int maxlineNum = 100000001;//写入文件的最大行数
        int startlineNum = 1;//写入文件的行数
        int Multiplying = 2;//行数增长倍率
        long begin = 0L;
        long end = 0L; //将时间统计写入文件Result.txt中
        FileWriter fileWriter = new FileWriter("./Result.txt", true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        System.out.println("Test FileOutputStream begin.");
        for (int lineNum = startlineNum; lineNum < maxlineNum; lineNum *= Multiplying) {
            begin = System.currentTimeMillis();
            testFileOutputStream(lineNum);
            end = System.currentTimeMillis();
            long timeElapse_FileOutputStream = end - begin;
            bufferedWriter.write(String.valueOf(timeElapse_FileOutputStream) + " ");
        }
        System.out.println("Test FileOutputStream end. ");
        System.out.println("Test BufferedOutputStream begin.");
        bufferedWriter.write(" ");
        for (int lineNum = startlineNum; lineNum < maxlineNum; lineNum *= Multiplying) {
            begin = System.currentTimeMillis();
            testBufferedOutputStream(lineNum);
            end = System.currentTimeMillis();
            long timeElapse_BufferedOutputStream = end - begin;
            bufferedWriter.write(String.valueOf(timeElapse_BufferedOutputStream) + " ");
        }
        System.out.println("Test BufferedOutputStream end. ");
        System.out.println("Test FileWriter begin.");
        bufferedWriter.write(" ");
        for (int lineNum = startlineNum; lineNum < maxlineNum; lineNum *= Multiplying) {
            begin = System.currentTimeMillis();
            testFileWriter(lineNum);
            end = System.currentTimeMillis();
            long timeElapse_FileWriter = end - begin;
            bufferedWriter.write(String.valueOf(timeElapse_FileWriter) + " ");
        }
        System.out.println("Test FileWriter end. ");
        System.out.println("Test BufferedWriter begin.");
        bufferedWriter.write(" ");
        for (int lineNum = startlineNum; lineNum < maxlineNum; lineNum *= Multiplying) {
            begin = System.currentTimeMillis();
            testBufferedWriter(lineNum);
            end = System.currentTimeMillis();
            long timeElapse_BufferedWriter = end - begin;
            bufferedWriter.write(String.valueOf(timeElapse_BufferedWriter) + " ");
        }
        System.out.println("Test BufferedWriter end. ");
        System.out.println("Test NewOutputStream begin.");
        bufferedWriter.write(" ");
        for (int lineNum = startlineNum; lineNum < maxlineNum; lineNum *= Multiplying) {
            begin = System.currentTimeMillis();
            testNewOutputStream(lineNum);
            end = System.currentTimeMillis();
            long timeElapse_NewOutputStream = end - begin;
            bufferedWriter.write(String.valueOf(timeElapse_NewOutputStream) + " ");
        }
        System.out.println("Test NewOutputStream end. ");
        System.out.println("Test NewBufferedWriter begin.");
        bufferedWriter.write(" ");
        for (int lineNum = startlineNum; lineNum < maxlineNum; lineNum *= Multiplying) {
            begin = System.currentTimeMillis();
            testNewBufferedWriter(lineNum);
            end = System.currentTimeMillis();
            long timeElapse_NewBufferedWriter = end - begin;
            bufferedWriter.write(String.valueOf(timeElapse_NewBufferedWriter) + " ");
        }
        System.out.println("Test NewOutputStream end. ");
        bufferedWriter.close();
    }

    /************************** I/O *****************************/
    //面向字节
    public static void testFileOutputStream(int lineNum) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(new File("./testFileOutputStream.txt"));
        while (--lineNum > 0) {
            fileOutputStream.write("写入文件Data ".getBytes());
        }
        fileOutputStream.close();
    }

    public static void testBufferedOutputStream(int lineNum) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(new File("./testBufferedOutputStream.txt"));
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        while (--lineNum > 0) {
            bufferedOutputStream.write("写入文件Data ".getBytes());
        }
        bufferedOutputStream.close();
    }

    //面向字符
    public static void testFileWriter(int lineNum) throws IOException {
        FileWriter fileWriter = new FileWriter("./testFileWriter.txt");
        while (--lineNum > 0) {
            fileWriter.write("写入文件Data ");
        }
        fileWriter.close();
    }

    public static void testBufferedWriter(int lineNum) throws IOException {
        FileWriter fileWriter = new FileWriter("./testBufferedWriter.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        while (--lineNum > 0) {
            bufferedWriter.write("写入文件Data ");
        }
        bufferedWriter.close();
    }

    /************************** NIO ****************************/
    public static void testNewOutputStream(int lineNum) throws IOException {
        OutputStream outputStream = Files.newOutputStream(Paths.get("./testNewOutputStream.txt"));
        while (--lineNum > 0) {
            outputStream.write("写入文件Data ".getBytes());
        }
        outputStream.close();
    }

    public static void testNewBufferedWriter(int lineNum) throws IOException {
        BufferedWriter newBufferedReader = Files.newBufferedWriter(Paths.get("./testNewBufferedWriter.txt"));
        while (--lineNum > 0) {
            newBufferedReader.write("写入文件Data ");
        }
        newBufferedReader.close();
    }

    public static void main(String[] args) throws IOException {
        //多次测试时可清空result.txt文件
        FileWriter fileWriter = new FileWriter("./Result.txt");
        testDriver();
    }
}
