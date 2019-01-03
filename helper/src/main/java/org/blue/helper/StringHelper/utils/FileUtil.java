package org.blue.helper.StringHelper.utils;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

/**
 * @Description <P>文件处理类</P>
 * @Author v-Rui.Xiong@bl.com
 * @Date 2018/12/28
 * @Version 1.0.0
 **/
@Component
public class FileUtil {
    private static final Logger LOGGER=LoggerFactory.getLogger(FileUtil.class);
    private static final String tmpSuffix=".tmp";
    public static int mb=1024*1024;

    /**
     * 当前目录路径
     */
    public static String currentWorkDir = System.getProperty("user.dir") + "\\";

    /**
     * 左填充
     *
     * @param str
     * @param length
     * @param ch
     * @return
     */
    public static String leftPad(String str, int length, char ch) {
        if (str.length() >= length) {
            return str;
        }
        char[] chs = new char[length];
        Arrays.fill(chs, ch);
        char[] src = str.toCharArray();
        System.arraycopy(src, 0, chs, length - src.length, src.length);
        return new String(chs);

    }

    /**
     * 删除文件
     *
     * @param fileName
     *            待删除的完整文件名
     * @return
     */
    public static boolean delete(String fileName) {
        boolean result = false;
        File f = new File(fileName);
        if (f.exists()) {
            result = f.delete();

        } else {
            result = true;
        }
        return result;
    }

    /***
     * 递归获取指定目录下的所有的文件（不包括文件夹）
     *
     * @param obj
     * @return
     */
    public static ArrayList<File> getAllFiles(String dirPath) {
        File dir = new File(dirPath);

        ArrayList<File> files = new ArrayList<File>();

        if (dir.isDirectory()) {
            File[] fileArr = dir.listFiles();
            for (int i = 0; i < fileArr.length; i++) {
                File f = fileArr[i];
                if (f.isFile()) {
                    files.add(f);
                } else {
                    files.addAll(getAllFiles(f.getPath()));
                }
            }
        }
        return files;
    }

    /**
     * 获取指定目录下的所有文件(不包括子文件夹)
     *
     * @param dirPath
     * @return
     */
    public static ArrayList<File> getDirFiles(String dirPath) {
        File path = new File(dirPath);
        File[] fileArr = path.listFiles();
        ArrayList<File> files = new ArrayList<File>();

        for (File f : fileArr) {
            if (f.isFile()) {
                files.add(f);
            }
        }
        return files;
    }

    /**
     * 获取指定目录下特定文件后缀名的文件列表(不包括子文件夹)
     *
     * @param dirPath
     *            目录路径
     * @param suffix
     *            文件后缀
     * @return
     */
    public static ArrayList<File> getDirFiles(String dirPath,
                                              final String suffix) {
        File path = new File(dirPath);
        File[] fileArr = path.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                String lowerName = name.toLowerCase();
                String lowerSuffix = suffix.toLowerCase();
                if (lowerName.endsWith(lowerSuffix)) {
                    return true;
                }
                return false;
            }

        });
        ArrayList<File> files = new ArrayList<File>();

        for (File f : fileArr) {
            if (f.isFile()) {
                files.add(f);
            }
        }
        return files;
    }

    /**
     * 读取文件内容
     *
     * @param fileName
     *            待读取的完整文件名
     * @return 文件内容
     * @throws IOException
     */
    public static String read(String fileName) throws IOException {
        File f = new File(fileName);
        FileInputStream fs = new FileInputStream(f);
        String result = null;
        byte[] b = new byte[fs.available()];
        fs.read(b);
        fs.close();
        result = new String(b);
        return result;
    }

    /**
     * 写文件
     *
     * @param fileName
     *            目标文件名
     * @param fileContent
     *            写入的内容
     * @return
     * @throws IOException
     */
    public static boolean write(String fileName, String fileContent)
            throws IOException {
        boolean result = false;
        File f = new File(fileName);
        FileOutputStream fs = new FileOutputStream(f);
        byte[] b = fileContent.getBytes();
        fs.write(b);
        fs.flush();
        fs.close();
        result = true;
        return result;
    }

    /**
     * 追加内容到指定文件
     *
     * @param fileName
     * @param fileContent
     * @return
     * @throws IOException
     */
    public static boolean append(String fileName, String fileContent)
            throws IOException {
        boolean result = false;
        File f = new File(fileName);
        if (f.exists()) {
            RandomAccessFile rFile = new RandomAccessFile(f, "rw");
            byte[] b = fileContent.getBytes();
            long originLen = f.length();
            rFile.setLength(originLen + b.length);
            rFile.seek(originLen);
            rFile.write(b);
            rFile.close();
        }
        result = true;
        return result;
    }

    public static void main(String[] args) {
        FileUtil fileUtil=getFileUtil();
////        try {
////            List<String> list=fileUtil.splitBySize("C:\\Users\\User\\Desktop\\test\\c.rar",10*1024);
////            System.out.println(list);
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
//        File file=new File("C:\\Users\\User\\Desktop\\test\\c.rar");
//        System.out.println(file.getPath());
//        System.out.println(file.getAbsolutePath());
//        System.out.println(file.getParent());
        fragmentUpload(new File("C:\\Users\\User\\Desktop\\test\\c.rar"),10*mb);

    }
    public static FileUtil getFileUtil(){
        FileUtil fileUtil=new FileUtil();
        return fileUtil;
    }
    public static void fragmentUpload(File file,int splitSize){
        FileUtil fileUtil=getFileUtil();
        String parentPath=file.getParent();
        String filePath=file.getPath();
        long fileSize=file.length();
        try {
            File tmp=new File(parentPath+File.separator+"Tmp");
            if (tmp.exists()) tmp.delete();
            tmp.mkdirs();
            List<String> list=fileUtil.splitBySize(file,splitSize,fileSize);
            while (FileSize.getFileSize(parentPath+File.separator+"Tmp") <fileSize){
                LOGGER.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>split ing tmp size:"+ FileSize.getFileSize(parentPath+File.separator+"Tmp"));
            }
            fileUtil.mergePartFiles(file.getParent(),splitSize,filePath+".new");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 拆分文件
     *
     * @param fileName
     *            待拆分的完整文件名
     * @param byteSize
     *            按多少字节大小拆分
     * @return 拆分后的文件名列表
     * @throws IOException
     */
    public List<String> splitBySize(String fileName, int byteSize) throws IOException {
        File file = new File(fileName);
        return this.splitBySize( fileName, byteSize,file.length());

    }
    /**
     * 拆分文件
     *
     * @param fileName
     *            待拆分的完整文件名
     * @param byteSize
     *            按多少字节大小拆分
     * @return 拆分后的文件名列表
     * @throws IOException
     */
    public List<String> splitBySize(String fileName, int byteSize,double fileSize) throws IOException {
        List<String> parts = new ArrayList<String>();
        File file = new File(fileName);

        int count = (int) Math.ceil(fileSize / (double) byteSize);

        int countLen = (count + "").length();
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(count,
                count * 3, 1, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(count * 2));

        for (int i = 0; i < count; i++) {
            String partFileName = fileName + "."
                    + leftPad((i + 1) + "", countLen, '0') + ".tmp";
            threadPool.execute(new SplitRunnable(byteSize, i * byteSize,
                    partFileName, file));
            parts.add(partFileName);
        }
        return parts;
    }
    /**
     * 拆分文件
     *
     * @param fileName
     *            待拆分的完整文件名
     * @param byteSize
     *            按多少字节大小拆分
     * @return 拆分后的文件名列表
     * @throws IOException
     */
    public List<String> splitBySize(File file, int byteSize,long fileSize) throws IOException {
        List<String> parts = new ArrayList<String>();

        int count = (int) Math.ceil(fileSize / (double) byteSize);

        int countLen = (count + "").length();
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(count,
                count * 3, 1, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(count * 2));
        String fileName=file.getParent();
        for (int i = 0; i < count; i++) {
            String partFileName = fileName + File.separator+"Tmp"+File.separator+"."
                    + leftPad((i + 1) + "", countLen, '0') + ".tmp";
            threadPool.execute(new SplitRunnable(byteSize, i * byteSize,
                    partFileName, file));
            parts.add(partFileName);
        }
        return parts;
    }

    /**
     * 合并文件
     *
     * @param dirPath
     *            拆分文件所在目录名
     * @param partFileSuffix
     *            拆分文件后缀名
     * @param partFileSize
     *            拆分文件的字节数大小
     * @param mergeFileName
     *            合并后的文件名
     * @throws IOException
     */
    public void mergePartFiles(String dirPath, int partFileSize, String mergeFileName) throws IOException {
        ArrayList<File> partFiles = FileUtil.getDirFiles(dirPath,
                tmpSuffix);
        Collections.sort(partFiles, new FileComparator());

        RandomAccessFile randomAccessFile = new RandomAccessFile(mergeFileName,
                "rw");
        randomAccessFile.setLength(partFileSize * (partFiles.size() - 1)
                + partFiles.get(partFiles.size() - 1).length());
        randomAccessFile.close();

        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                partFiles.size(), partFiles.size() * 3, 1, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(partFiles.size() * 2));

        for (int i = 0; i < partFiles.size(); i++) {
            threadPool.execute(new MergeRunnable(i * partFileSize,
                    mergeFileName, partFiles.get(i)));
        }

    }

    /**
     * 根据文件名，比较文件
     *
     * @author yjmyzz@126.com
     *
     */
    private class FileComparator implements Comparator<File> {
        public int compare(File o1, File o2) {
            return o1.getName().compareToIgnoreCase(o2.getName());
        }
    }

    /**
     * 分割处理Runnable
     *
     * @author yjmyzz@126.com
     *
     */
    private class SplitRunnable implements Runnable {
        int byteSize;
        String partFileName;
        File originFile;
        int startPos;

        public SplitRunnable(int byteSize, int startPos, String partFileName,
                             File originFile) {
            this.startPos = startPos;
            this.byteSize = byteSize;
            this.partFileName = partFileName;
            this.originFile = originFile;
        }

        public void run() {
            RandomAccessFile rFile;
            OutputStream os;
            try {
                rFile = new RandomAccessFile(originFile, "r");
                byte[] b = new byte[byteSize];
                rFile.seek(startPos);// 移动指针到每“段”开头
                int s = rFile.read(b);
                os = new FileOutputStream(partFileName);
                os.write(b, 0, s);
                os.flush();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 合并处理Runnable
     *
     * @author yjmyzz@126.com
     *
     */
    private class MergeRunnable implements Runnable {
        long startPos;
        String mergeFileName;
        File partFile;

        public MergeRunnable(long startPos, String mergeFileName, File partFile) {
            this.startPos = startPos;
            this.mergeFileName = mergeFileName;
            this.partFile = partFile;
        }

        public void run() {
            RandomAccessFile rFile;
            try {
                rFile = new RandomAccessFile(mergeFileName, "rw");
                rFile.seek(startPos);
                FileInputStream fs = new FileInputStream(partFile);
                byte[] b = new byte[fs.available()];
                fs.read(b);
                fs.close();
                rFile.write(b);
                rFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static class FileSize {
        private final static ForkJoinPool forkJoinPool = new ForkJoinPool();

        public static long getFileSize(String fileName){
            return forkJoinPool.invoke(new FileSizeFinder(new File(fileName)));
        }
        private static class FileSizeFinder extends RecursiveTask<Long> {
            final File file;
            public FileSizeFinder(final File theFile) {
                file = theFile;
            }
            @Override
            public Long compute() {
                long size = 0;
                if (file.isFile()) {
                    size = file.length();
                } else {
                    final File[] children = file.listFiles();
                    if (children != null) {
                        List<ForkJoinTask<Long>> tasks = new ArrayList<ForkJoinTask<Long>>();
                        for (final File child : children) {
                            if (child.isFile()) {
                                size += child.length();
                            } else {
                                tasks.add(new FileSizeFinder(child));
                            }
                        }
                        for (final ForkJoinTask<Long> task : invokeAll(tasks)) {
                            size += task.join();
                        }
                    }
                }
                return size;
            }
        }

    }
}
