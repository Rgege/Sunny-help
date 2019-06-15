//package org.blue.helper.test.imagerRecognition;
//
//import net.sourceforge.tess4j.ITesseract;
//import net.sourceforge.tess4j.Tesseract;
//import net.sourceforge.tess4j.TesseractException;
//import sun.misc.BASE64Decoder;
//import sun.misc.BASE64Encoder;
//
//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;
//import java.io.*;
//
///**
// * @Description <P></P>
// * @Author allen
// * @Date 2019/1/4
// * @Version 1.0.0
// **/
//public class ImageRecognitionDemo {
//    static BASE64Encoder encoder = new sun.misc.BASE64Encoder();
//    static BASE64Decoder decoder = new sun.misc.BASE64Decoder();
//    public static void main(String[] args) {//D:\t.jpg
//        String classPath=ImageRecognitionDemo.class.getClassLoader().getResource("").getPath().
//                replace("/target/classes/","").substring(1);
//
//        base64StringToImage("D:\\t.jpg","D:\\t.jpg");
//        File imageFile = new File("D:\\t.jpg");
//        ITesseract instance = new Tesseract();
//
//        /**
//         *  获取项目根路径，例如： D:\IDEAWorkSpace\tess4J
//         */
//        File directory = new File(classPath);
//        String courseFile = null;
//        try {
//            courseFile = directory.getCanonicalPath();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        //设置训练库的位置
//        instance.setDatapath(courseFile + "//tessdata");
//
//        instance.setLanguage("chi_sim");//chi_sim ：简体中文， eng	根据需求选择语言库
//        String result = null;
//        try {
//            long startTime = System.currentTimeMillis();
//            result =  instance.doOCR(imageFile);
//            long endTime = System.currentTimeMillis();
//            System.out.println("Time is：" + (endTime - startTime) + " 毫秒");
//        } catch (TesseractException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("result: ");
//        System.out.println(result);
//
//    }
//    /**
//     * 将其他格式的图片转换成CDR或其它文件格式
//     * @param sourceFileName
//     * @param newFileName
//     */
//    public static void base64StringToImage(String sourceFileName,String newFileName){
//        try {
//            String base64String =getImageBinary(sourceFileName);
//            byte[] bytes1 = decoder.decodeBuffer(base64String);
//            ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes1);
//            BufferedImage bi1 =ImageIO.read(inputStream);
//            File w2 = new File(newFileName);//可以是jpg,png,gif格式
//            ImageIO.write(bi1, getFileType(sourceFileName), w2);//不管输出什么格式图片，此处不需改动
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    static String getImageBinary(String fileName){
//        File f = new File(fileName);
//        BufferedImage bi;
//        try {
//            bi = ImageIO.read(f);
//            String fileType=getFileType(fileName);
//            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//            ImageIO.write(bi, fileType, outputStream);
//            byte[] bytes = outputStream.toByteArray();
//
//            return encoder.encodeBuffer(bytes).trim();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//    static String getFileType(String fileName){
//        return fileName.substring(fileName.indexOf(".")+1,fileName.length());
//    }
//
//
//
//}
