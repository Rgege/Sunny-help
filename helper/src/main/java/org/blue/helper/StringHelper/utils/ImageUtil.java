package org.blue.helper.StringHelper.utils;

import org.blue.helper.StringHelper.utils.support.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * @Description <P></P>
 * @Author v-Rui.Xiong@bl.com
 * @Date 2019/1/4
 * @Version 1.0.0
 **/
public class ImageUtil {
    private static BASE64Encoder encoder ;
    private static BASE64Decoder decoder ;
    static {
        encoder= Base64.ENCODER.getEncoder();
        decoder=Base64.DECODER.getDecoder();
    }

    /**
     * 将其他格式的图片转换成CDR或其它文件格式
     * @param sourceFileName
     * @param newFileName
     */
    public static void base64StringToImage(String sourceFileName,String newFileName){
        try {
            String base64String =getImageBinary(sourceFileName);
            byte[] bytes1 = decoder.decodeBuffer(base64String);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes1);
            BufferedImage bi1 =ImageIO.read(inputStream);
            File w2 = new File(newFileName);//可以是jpg,png,gif格式
            ImageIO.write(bi1, getFileType(sourceFileName), w2);//不管输出什么格式图片，此处不需改动
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static String getImageBinary(String fileName){
        File f = new File(fileName);
        BufferedImage bi;
        try {
            bi = ImageIO.read(f);
            String fileType=getFileType(fileName);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(bi, fileType, outputStream);
            byte[] bytes = outputStream.toByteArray();

            return encoder.encodeBuffer(bytes).trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static String getFileType(String fileName){
        return fileName.substring(fileName.indexOf(".")+1,fileName.length());
    }

}
