package org.blue.helper.StringHelper.utils;

import com.swetake.util.Qrcode;
import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.exception.DecodingFailedException;
import org.blue.helper.StringHelper.utils.support.QRCodeImg;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class QRCodeUtils {

    /**
     * 生成二维码(QRCode)图片
     * @param content 存储内容
     * @param imgPath 图片路径
     */
    private void encoderQRCode(String content, String imgPath) throws Exception{
        this.encoderQRCode(content, imgPath, "png", 7);
    }

    /**
     * 生成二维码(QRCode)图片
     * @param content 存储内容
     * @param output 输出流
     */
    private void encoderQRCode(String content, OutputStream output) {
        this.encoderQRCode(content, output, "png", 7);
    }

    /**
     * 生成二维码(QRCode)图片
     * @param content 存储内容
     * @param imgPath 图片路径
     * @param imgType 图片类型
     */
    private void encoderQRCode(String content, String imgPath, String imgType) throws Exception{
        this.encoderQRCode(content, imgPath, imgType, 7);
    }

    /**
     * 生成二维码(QRCode)图片
     * @param content 存储内容
     * @param output 输出流
     * @param imgType 图片类型
     */
    private void encoderQRCode(String content, OutputStream output, String imgType) {
        this.encoderQRCode(content, output, imgType, 7);
    }

    /**
     * 生成二维码(QRCode)图片
     * @param content 存储内容
     * @param imgPath 图片路径
     * @param imgType 图片类型
     * @param size 二维码尺寸
     */
    private void encoderQRCode(String content, String imgPath, String imgType, int size) throws IOException {

            BufferedImage bufImg = this.qRCodeCommon(content, imgType, size);

            File imgFile = new File(imgPath);
            // 生成二维码QRCode图片
            ImageIO.write(bufImg, imgType, imgFile);

    }

    /**
     * 生成二维码(QRCode)图片
     * @param content 存储内容
     * @param output 输出流
     * @param imgType 图片类型
     * @param size 二维码尺寸
     */
    private void encoderQRCode(String content, OutputStream output, String imgType, int size) {
        try {
            BufferedImage bufImg = this.qRCodeCommon(content, imgType, size);
            // 生成二维码QRCode图片
            ImageIO.write(bufImg, imgType, output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成二维码(QRCode)图片的公共方法
     * @param content 存储内容
     * @param imgType 图片类型
     * @param size 二维码尺寸
     * @return
     */
    private BufferedImage qRCodeCommon(String content, String imgType, int size) {
        BufferedImage bufImg = null;
        try {
            Qrcode qrcodeHandler = new Qrcode();
            // 设置二维码排错率，可选L(7%)、M(15%)、Q(25%)、H(30%)，排错率越高可存储的信息越少，但对二维码清晰度的要求越小
            qrcodeHandler.setQrcodeErrorCorrect('M');
            qrcodeHandler.setQrcodeEncodeMode('B');
            // 设置设置二维码尺寸，取值范围1-40，值越大尺寸越大，可存储的信息越大
            qrcodeHandler.setQrcodeVersion(size);
            // 获得内容的字节数组，设置编码格式
            byte[] contentBytes = content.getBytes("utf-8");
            // 图片尺寸
            int imgSize = 67 + 12 * (size - 1);
            bufImg = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_RGB);
            Graphics2D gs = bufImg.createGraphics();
            // 设置背景颜色
            gs.setBackground(Color.WHITE);
            gs.clearRect(0, 0, imgSize, imgSize);

            // 设定图像颜色> BLACK
            gs.setColor(Color.BLACK);
            // 设置偏移量，不设置可能导致解析出错
            int pixoff = 2;
            // 输出内容> 二维码
            if (contentBytes.length > 0 && contentBytes.length < 800) {
                boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);
                for (int i = 0; i < codeOut.length; i++) {
                    for (int j = 0; j < codeOut.length; j++) {
                        if (codeOut[j][i]) {
                            gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
                        }
                    }
                }
            } else {
                throw new Exception("QRCode content bytes length = " + contentBytes.length + " not in [0, 800].");
            }
            gs.dispose();
            bufImg.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bufImg;
    }

    /**
     * 解析二维码（QRCode）
     * @param imgPath 图片路径
     * @return
     */
    public static String decoderQRCode(String imgPath) {
        // QRCode 二维码图片的文件
        File imageFile = new File(imgPath);
        BufferedImage bufImg = null;
        String content = null;
        try {
            bufImg = ImageIO.read(imageFile);
            QRCodeDecoder decoder = new QRCodeDecoder();
            content = new String(decoder.decode(new QRCodeImg(bufImg)), "utf-8");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        } catch (DecodingFailedException dfe) {
            System.out.println("Error: " + dfe.getMessage());
            dfe.printStackTrace();
        }
        return content;
    }

    /**
     * 解析二维码（QRCode）
     * @param input 输入流
     * @return
     */
    public static String decoderQRCode(InputStream input) {
        BufferedImage bufImg = null;
        String content = null;
        try {
            bufImg = ImageIO.read(input);
            QRCodeDecoder decoder = new QRCodeDecoder();
            content = new String(decoder.decode(new QRCodeImg(bufImg)), "utf-8");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        } catch (DecodingFailedException dfe) {
            System.out.println("Error: " + dfe.getMessage());
            dfe.printStackTrace();
        }
        return content;
    }

    /**
     * 生成二维码
     * @param member_id
     * @return
     */
    private  String createQRCodeImg(String data) throws Exception{
        String QRCodeName = UUID.randomUUID().toString().replaceAll("-", "") + ".png";//定义二维码文件名，采用uuid命名，存储格式为png
        String imgPath = "C:\\Users\\xr33\\Desktop\\"+QRCodeName;
        String encoderContent = data;
        QRCodeUtils handler = new QRCodeUtils();
        handler.encoderQRCode(encoderContent, imgPath, "png");
        return QRCodeName;
    }

    /**
     * 生成二维码图片
     * @param data     要储存的数据
     * @param imgPath  保存位置
     * @return
     */
    public static Map<String,String> createQRCodeImg(String data,String imgPath) {
        String QRCodeName = UUID.randomUUID().toString().replaceAll("-", "") + ".png";//定义二维码文件名，采用uuid命名，存储格式为png
        imgPath = imgPath+QRCodeName;
        String encoderContent = data;
        QRCodeUtils handler = new QRCodeUtils();
        Map<String,String> map=new HashMap<String, String>();
        try {
            handler.encoderQRCode(encoderContent, imgPath, "png");
            map.put("QRCodeName",QRCodeName);
            map.put("imgPath",imgPath);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("ERROR",e.getMessage());
        }

        return map;
    }

    /**
     * 生成二维码图片
     * @param data 要保存的数据
     * @param imgPath 保存位置
     * @param size  图片大小  size越大可储存的数据越多
     * @return
     */
    public static Map<String,String> createQRCodeImg(String data,String imgPath,int size) {
        String QRCodeName = UUID.randomUUID().toString().replaceAll("-", "") + ".png";//定义二维码文件名，采用uuid命名，存储格式为png
        imgPath = imgPath+QRCodeName;
        String encoderContent = data;
        QRCodeUtils handler = new QRCodeUtils();
        Map<String,String> map=new HashMap<String, String>();
        try {
            handler.encoderQRCode(encoderContent, imgPath, "png",size);
            map.put("QRCodeName",QRCodeName);
            map.put("imgPath",imgPath);
        } catch (IOException e) {
            e.printStackTrace();
            map.put("ERROR",e.getMessage());
        }

        return map;
    }

}
