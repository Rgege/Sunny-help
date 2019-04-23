//package org.blue.helper.test;
//
//import com.github.jaiimageio.plugins.tiff.TIFFImageWriteParam;
//import com.sun.media.jai.codec.ImageCodec;
//import com.sun.media.jai.codec.ImageEncoder;
//import com.sun.media.jai.codec.TIFFEncodeParam;
//import javafx.concurrent.Task;
//import javafx.scene.Cursor;
//import javafx.scene.control.Alert;
//import javafx.scene.control.ButtonType;
//import net.sourceforge.tess4j.util.ImageIOHelper;
//import org.blue.helper.StringHelper.common.exception.HelperException;
//import org.blue.helper.StringHelper.utils.CmdComandUtil;
//import org.blue.helper.test.util.TextToImage;
//
//import javax.imageio.*;
//import javax.imageio.metadata.IIOMetadata;
//import javax.imageio.stream.ImageInputStream;
//import javax.imageio.stream.ImageOutputStream;
//import javax.media.jai.JAI;
//import javax.media.jai.RenderedOp;
//import java.awt.image.BufferedImage;
//import java.io.*;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Locale;
//
///**
// * @Description <P></P>
// * @Author XR@bl
// * @Date 2018/12/12 18:17
// * @Version 1.0.0
// **/
//public class AAA {
//
//    public static void main(String[] args) {
//        int lineNum = 32;
//        File f1 = new File("C:\\Users\\User\\Desktop\\111.txt");
//        File f2 = new File("C:\\Users\\User\\Desktop\\chars.txt");
////        try (BufferedReader reader = new BufferedReader(new FileReader(f1));
////             BufferedWriter writer = new BufferedWriter(new FileWriter(f2));
////        ) {
////            String l = null;
////            StringBuilder sb = new StringBuilder();
////            while ((l = reader.readLine()) != null) {
////                sb.append(l);
////            }
////            int size = sb.length();
////            int off = 0;
////            int count = size / lineNum;
////            if (size % lineNum != 0) {
////                count += 1;
////            }
////
////            for (int i = 0; i < count; i++) {
////                if (i == (count - 1)) {
////                    writer.write(sb.toString(), off, size - off);
////                } else {
////                    writer.write(sb.toString(), off, lineNum);
////                    writer.write("\n");
////                    writer.write("\n");
////                }
////                off += lineNum;
////            }
////            writer.flush();
////        } catch (FileNotFoundException e) {
////            e.printStackTrace();
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////        slipt(f2);
////        for (int i = 1; i <2593 ; i++) {
////            if (i % 36 == 0) {
////                jpg2Tif("C:\\Users\\Administrator\\Desktop\\zifuji\\imgs\\" + i + ".jpg",
////                        "C:\\Users\\Administrator\\Desktop\\zifuji\\imgs\\ch.normal.exp" + i + ".tif");
////            }
////        }
////        try {
////            CmdComandUtil.executeCmd("tesseract ch.normal.exp0.tif ch.normal.exp0 -l chi_sim batch.nochop makebox");
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
//        for (int i = 1; i <2593 ; i++) {
//            File xFile=new File("C:\\Users\\Administrator\\Desktop\\zifuji\\tmp\\wait.tmp");
//            if (!xFile.exists()){
//                try {
//                    xFile.createNewFile();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (i % 36 == 0) {
//                try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File("C:\\Users\\Administrator\\Desktop\\zifuji\\bat\\"+i+".bat")))) {
//                    bw.write("@echo off\n" +
//                            ">nul 2>&1 \"%SYSTEMROOT%\\system32\\cacls.exe\" \"%SYSTEMROOT%\\system32\\config\\system\"\n" +
//                            "if '%errorlevel%' NEQ '0' (\n" +
//                            "goto UACPrompt\n" +
//                            ") else ( goto gotAdmin )\n" +
//                            ":UACPrompt\n" +
//                            "echo Set UAC = CreateObject^(\"Shell.Application\"^) > \"%temp%\\getadmin.vbs\"\n" +
//                            "echo UAC.ShellExecute \"%~s0\", \"\", \"\", \"runas\", 1 >> \"%temp%\\getadmin.vbs\"\n" +
//                            "\"%temp%\\getadmin.vbs\"\n" +
//                            "exit /B\n" +
//                            ":gotAdmin\n" +
//                            "if exist \"%temp%\\getadmin.vbs\" ( del \"%temp%\\getadmin.vbs\" )\n" +
//                            "\n");
//                    bw.write("@echo off");
//                    bw.write("\n");
//                    bw.write("cd C:\\Users\\Administrator\\Desktop\\zifuji\\imgs");
//                    bw.write("\n");
//                    bw.write("tesseract ch.normal.exp"+i+".tif ch.normal.exp"+i+" -l chi_sim batch.nochop makebox");
//                    bw.write("\n");
//                    bw.write("del C:\\Users\\Administrator\\Desktop\\zifuji\\tmp\\wait.tmp");
//                    bw.flush();
//                } catch (IOException e) {
//                    throw new HelperException(e);
//                }
//                CmdComandUtil.excutBat("C:\\Users\\Administrator\\Desktop\\zifuji\\bat\\"+i+".bat",xFile);
//            }
//
//        }
//    }
//
//    public static void slipt(File f2) {
//        try (BufferedReader reader = new BufferedReader(new FileReader(f2))) {
//            int line = 1;
//            String l = null;
//            StringBuilder sb = new StringBuilder();
//            while ((l = reader.readLine()) != null) {
//                sb.append("          ").append(l).append("\n");
//                if (line % 36 == 0 && line != 0) {
//                    File file = new File("C:\\Users\\User\\Desktop\\zifuji\\" + line + ".txt");
//                    write(sb.toString(), file);
//                    sb.setLength(0);
//                }
//                line++;
//            }
//            File file = new File("C:\\Users\\User\\Desktop\\zifuji\\" + line + ".txt");
//            write(sb.toString(), file);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void write(String s, File file) {
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file));) {
//            writer.write(s);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void mergeTiff(final List<File> var10, final String tifFileName,final File tifFile) {
//        Task<Void> worker = new Task<Void>() {
//            @Override
//            protected Void call() throws Exception {
//                ImageIOHelper.mergeTiff(var10.toArray(new File[0]), tifFile);
//                return null;
//            }
//
//            @Override
//            protected void succeeded() {
//                super.succeeded();
//                System.out.println(tifFileName + " 成功");
//            }
//
//            @Override
//            protected void failed() {
//                super.failed();
//                System.out.println(tifFileName + " 失败");
//            }
//        };
//        new Thread(worker).start();
//    }
//
//    public static void jpg2Tif(String fileAbsolutePath,String tifFilePath) {
//        OutputStream outputStream = null;
//        try {
//            RenderedOp renderOp = JAI.create("fileload", fileAbsolutePath);
//            outputStream = new FileOutputStream(tifFilePath);
//            TIFFEncodeParam tiffParam = new TIFFEncodeParam();
//            ImageEncoder imageEncoder = ImageCodec.createImageEncoder("TIFF", outputStream, tiffParam);
//            imageEncoder.encode(renderOp);
//        } catch (FileNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } finally {
//            if (outputStream != null) {
//                try {
//                    outputStream.close();
//                } catch (IOException e) {
//                }
//                outputStream = null;
//            }
//        }
//    }
//}
