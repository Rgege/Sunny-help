package org.blue.helper.StringHelper.utils;

import de.idyl.winzipaes.AesZipFileDecrypter;
import de.idyl.winzipaes.AesZipFileEncrypter;
import de.idyl.winzipaes.impl.*;
import org.apache.tomcat.util.http.fileupload.FileUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;


public class ZipUtil {

    public static void decompress(File zipFile, String descDir) throws IOException {
        ZipFile zip = new ZipFile(zipFile, Charset.forName("GBK"));
        String name = zip.getName().substring(zip.getName().lastIndexOf('\\') + 1, zip.getName().lastIndexOf('.'));
        File pathFile = new File(descDir + name);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }
        for (Enumeration<? extends ZipEntry> entries = zip.entries(); entries.hasMoreElements(); ) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            String zipEntryName = entry.getName();
            InputStream in = zip.getInputStream(entry);
            String outPath = (descDir + name + "/" + zipEntryName).replaceAll("\\*", "/");
            // 判断路径是否存在,不存在则创建文件路径
            File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
            if (!file.exists()) {
                file.mkdirs();
            }
            FileOutputStream out = new FileOutputStream(outPath);
            byte[] buf1 = new byte[1024];
            int len;
            while ((len = in.read(buf1)) > 0) {
                out.write(buf1, 0, len);
            }
            in.close();
            out.close();
        }
        System.out.println("******************解压完毕********************");
        return;
    }

    public static void test() throws Exception {
        URL url = new URL("https://blog.csdn.net/xueba8/article/details/78571629");
        URLConnection connection = url.openConnection();
        try (BufferedInputStream bis = new BufferedInputStream(connection.getInputStream())) {
            byte[] bytes = new byte[bis.available()];
            while (bis.read() != -1) {
                bis.read(bytes);
            }
            String s = new String(bytes, "UTF-8");
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用指定密码将给定文件或文件夹压缩成指定的输出ZIP文件
     * @param srcFile 需要压缩的文件或文件夹
     * @param destPath 输出路径
     * @param passwd 压缩文件使用的密码
     */
    public static void zip(String srcFile,String destPath,String passwd) {
        AESEncrypter encrypter = new AESEncrypterBC();
        AesZipFileEncrypter zipFileEncrypter = null;
        try {
            zipFileEncrypter = new AesZipFileEncrypter(destPath, encrypter);
            /**
             * 此方法是修改源码后添加,用以支持中文文件名
             */
//            zipFileEncrypter.setEncoding("utf8");
            File sFile = new File(srcFile);
            /**
             * AesZipFileEncrypter提供了重载的添加Entry的方法,其中:
             * add(File f, String passwd)
             * 			方法是将文件直接添加进压缩文件
             *
             * add(File f,  String pathForEntry, String passwd)
             * 			方法是按指定路径将文件添加进压缩文件
             * pathForEntry - to be used for addition of the file (path within zip file)
             */
            doZip(sFile, zipFileEncrypter, "", passwd);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                zipFileEncrypter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 具体压缩方法,将给定文件添加进压缩文件中,并处理压缩文件中的路径
     * @param file 给定磁盘文件(是文件直接添加,是目录递归调用添加)
     * @param encrypter AesZipFileEncrypter实例,用于输出加密ZIP文件
     * @param pathForEntry ZIP文件中的路径
     * @param passwd 压缩密码
     * @throws IOException
     */
    private static void doZip(File file, AesZipFileEncrypter encrypter,
                              String pathForEntry, String passwd) throws IOException {
        if (file.isFile()) {
            pathForEntry += file.getName();
            encrypter.add(file, pathForEntry, passwd);
            return;
        }
        pathForEntry += file.getName() + File.separator;
        for(File subFile : file.listFiles()) {
            doZip(subFile, encrypter, pathForEntry, passwd);
        }
    }

    /**
     * 使用给定密码解压指定压缩文件到指定目录
     * @param inFile 指定Zip文件
     * @param outDir 解压目录
     * @param passwd 解压密码
     */
    public static void unzip(String inFile, String outDir, String passwd) throws Exception{
        File outDirectory = new File(outDir);
        if (!outDirectory.exists()) {
            outDirectory.mkdir();
        }
        AESDecrypter decrypter = new AESDecrypterBC();
        AesZipFileDecrypter zipDecrypter = null;
        try {
            zipDecrypter = new AesZipFileDecrypter(new File(inFile), decrypter);
            AesZipFileDecrypter.charset = "utf-8";
            /**
             * 得到ZIP文件中所有Entry,但此处好像与JDK里不同,目录不视为Entry
             * 需要创建文件夹,entry.isDirectory()方法同样不适用,不知道是不是自己使用错误
             * 处理文件夹问题处理可能不太好
             */
            List<ExtZipEntry> entryList = zipDecrypter.getEntryList();
            for(ExtZipEntry entry : entryList) {
                String eName = entry.getName();
                String dir = eName.substring(0, eName.lastIndexOf(File.separator) + 1);
                File extractDir = new File(outDir, dir);
                if (!extractDir.exists()) {
                    FileUtils.forceMkdir(extractDir);
                }
                /**
                 * 抽出文件
                 */
                File extractFile = new File(outDir + File.separator + eName);
                zipDecrypter.extractEntry(entry, extractFile, passwd);
            }
        } finally {
            try {
                zipDecrypter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        try {
            decompress(new File("F:\\Download\\BaiDuYunDownLoad\\新建文件夹\\21个vip福利视频.zip"),"C:\\Users\\My\\Desktop\\tt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
