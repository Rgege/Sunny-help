package org.blue.helper.test.file;


import java.io.*;

/**
 * @Description <P></P>
 * @Author allen
 * @Date 2018/12/29
 * @Version 1.0.0
 **/
public class WriteByThreads {
    private static final String TEMP=".tmp";
    private static int kb=1024;

    public static void copy(File file,String targetPath) throws IOException {
        String fileName=file.getName();
        File tmp=new File(targetPath+File.separator+fileName+TEMP);
        if (tmp.exists()){
            tmp.delete();
        }else {
            tmp.createNewFile();
        }
        long length=file.length();
        int count=Integer.parseInt(String.valueOf(length%kb==0?length/kb:(length/kb+1)));
        int startIndex;
        int k;
        for (int i = 0; i <count ; i++) {
            if (i==(count-1)){
                startIndex=Integer.parseInt(String.valueOf(length-(count-1)*kb));
                 k=Integer.parseInt(String.valueOf(length-startIndex));
            }else{
                startIndex=i*kb;
                k=kb;
            }
            Thread thread=new Thread(new FileRun(k,startIndex,file,tmp));
            thread.start();
        }
    }


    public static void main(String[] args) {
        File file=new File("C:\\Users\\User\\Desktop\\test\\org\\a.txt");
        try {
            copy(file,"C:\\Users\\User\\Desktop\\test\\new");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static class FileRun implements Runnable{
        private int length;
        private int startIndex;
        private File file;
        private File tmp;

        public FileRun(int length, int startIndex, File file,File tmp) {
            this.length = length;
            this.startIndex = startIndex;
            this.file = file;
            this.tmp=tmp;
        }

        @Override
        public void run() {
            try (RandomAccessFile tmpRaf=new RandomAccessFile(tmp,"rw");
                 RandomAccessFile fileRaf=new RandomAccessFile(file,"rw");){
                fileRaf.seek(startIndex);
//                fileRaf.skipBytes(startIndex);
                tmpRaf.seek(startIndex);
//                tmpRaf.skipBytes(startIndex);
                byte[] b=new byte[length];
                fileRaf.read(b);
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>thread【"+Thread.currentThread().getName()+"】");
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>write【"+new String(b)+"】");
                tmpRaf.write(b);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    static class InsertContent {
        public static void insert(String fileName, long pos, String insertContent) throws IOException {
            File tmp = File.createTempFile("tmp", null);
            tmp.deleteOnExit();
            try (RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
                 FileOutputStream tmpOut = new FileOutputStream(tmp);
                 FileInputStream tmpIn = new FileInputStream(tmp)) {
                raf.seek(pos);
                byte[] buf = new byte[64];
                int hasRead = 0;
                while ((hasRead = raf.read(buf)) > 0) {
                    tmpOut.write(buf, 0, hasRead);
                }

                raf.seek(pos);
                raf.write(insertContent.getBytes());
                while ((hasRead = tmpIn.read(buf)) > 0) {
                    raf.write(buf, 0, hasRead);
                }
            }
        }
    }
}











