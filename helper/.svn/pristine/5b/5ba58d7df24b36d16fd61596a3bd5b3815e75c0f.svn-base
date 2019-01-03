package org.blue.helper.test.io;

import org.apache.tomcat.util.codec.binary.Base64;
import org.blue.helper.StringHelper.utils.DateUtil;

import java.io.*;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Date;

public class BufferIO {

    private static final String oldPath="D:\\IOTest\\old\\";
    private static final String newPath="D:\\IOTest\\new\\";

    public static void doGetQ(String filePath) throws IOException {
        File newFile = new File("");
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        InputStream file = new FileInputStream(new File(filePath));
        byte[] byteBuf = new byte[3 * 1000];
        byte[] base64ByteBuf;
        int count1; //每次从文件中读取到的有效字节数
        while ((count1 = file.read(byteBuf)) != -1) {
            if (count1 != byteBuf.length) //如果有效字节数不为3*1000，则说明文件已经读到尾了，不够填充满byteBuf了
            {
                byte[] copy = Arrays.copyOf(byteBuf, count1); //从byteBuf中截取包含有效字节数的字节段
                base64ByteBuf = Base64.encodeBase64(copy); //对有效字节段进行编码
            } else {
                base64ByteBuf = Base64.encodeBase64(byteBuf);
            }
            os.write(base64ByteBuf, 0, base64ByteBuf.length);
            os.flush();
        }
        file.close();
        System.out.println(os.toString());
    }

    public static void test() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(new File("C:\\JD_PHONE_LIST_UTF-8.txt")));
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File("C:\\b.txt")));

        byte[] byteBuf = new byte[3 * 1000];
        byte[] base64ByteBuf;
        int count1; //每次从文件中读取到的有效字节数

        String line = "";
        while ((line = br.readLine()) != null) {
            bw.write(line);
            bw.newLine();
            bw.flush();
        }
        br.close();
        bw.close();
    }

    /**
     * @描述：用高效缓冲区的字节流读取复制文件，复制方式：一次复制指定数量的字节
     * @创建时间：
     */
    public static void bufferedInputStreamCopyFile() {
        //创建字节流对象
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            //实例化高效缓冲区字节流的输入、输出对象
            inputStream = new BufferedInputStream(new FileInputStream("E:/WebLogin.log"));
            outputStream = new BufferedOutputStream(new FileOutputStream("E:/new.log"));
            //每次读取的文件字节数量
            int len = -1;
            //每次读取的文件字节数据
            byte[] bs = new byte[1024];
            //循环读取文件，直至读到文件末尾
            while ((len = inputStream.read(bs)) != -1) {
                //写入到文件
                outputStream.write(bs, 0, len);
            }
            //清空缓冲区，将写入到文件中的数据保存
            outputStream.flush();
            //复制成功输出
            System.out.println("使用高效缓冲字节流一次读取指定数量的字节复制文件完毕");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭流
                if (outputStream != null)
                    outputStream.close();
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    /**
     * @描述：使用高效缓冲区的字节流读取复制文件，复制方式：一次复制一个字符
     * @创建时间：
     */
    public static void bufferedInputStreamReader() {
        //创建字节流对象
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            //实例化高效缓冲区字节流的输入、输出对象
            inputStream = new BufferedInputStream(new FileInputStream("E:/WebLogin.log"));
            outputStream = new BufferedOutputStream(new FileOutputStream("E:/login.log"));

            //读取的字节ACSII码
            int val = -1;

            //循环读取文件，直至读到文件末尾
            while ((val = inputStream.read()) != -1) {
                //写入到文件
                outputStream.write(val);
            }
            //清空缓冲区，将写入到文件中的数据保存
            outputStream.flush();
            //复制成功输出
            System.out.println("使用高效缓冲字节流一次读取一个字节复制文件完毕");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭流
                if (outputStream != null)
                    outputStream.close();
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }

    }
    public static void main(String[] args) {
        testCopy("jdk-8u152-windows-x64.zip");

    }

    public static void testCopy(String fileName){
        Date start=new Date();
        String oldFile=oldPath+fileName;
        String newFile=newPath+fileName;
        File ofile = new File(oldFile);
        //文件总长度
        long total=ofile.length();
        long doLenth=0L;

        // 指定要读取文件的缓冲输入字节流
        BufferedInputStream in = null;
        BufferedOutputStream out = null;
        try {
            in = new BufferedInputStream(new FileInputStream(ofile));

            File nfile = new File(newFile);
            if (nfile != null) {
                nfile.createNewFile();
            }
            // 指定要写入文件的缓冲输出字节流
            out = new BufferedOutputStream(new FileOutputStream(nfile));
            byte[] byteBuf = new byte[3 * 1000];
            byte[] base64ByteBuf;
            byte[] copy;
            int count1; //每次从文件中读取到的有效字节数
            while ((count1 = in.read(byteBuf)) != -1) {
                if (count1 != byteBuf.length) //如果有效字节数不为3*1000，则说明文件已经读到尾了，不够填充满byteBuf了
                {
                    copy = Arrays.copyOf(byteBuf, count1); //从byteBuf中截取包含有效字节数的字节段
                } else {
                    copy=byteBuf;
                }
                out.write(copy, 0, copy.length);
                out.flush();
                doLenth = doLenth + copy.length;
                System.out.println("已完成："+percent(doLenth,total));
            }
            Date end=new Date();
            System.out.println("本次复制总耗时："+(DateUtil.dateDiff(start,end))/10+" s");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(in !=null) in.close();
                if(out !=null) out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    public static void init(String dir){
        File file=new File(dir);
        if(!file.exists()){
            file.mkdirs();
        }
    }

    public static String percent(long p1, long p2){
        String str;
        float f=p1/(float)p2;
        NumberFormat nf=NumberFormat.getPercentInstance();
        nf.setMinimumFractionDigits(2);
        str=nf.format(f);

        return str;
    }

}









