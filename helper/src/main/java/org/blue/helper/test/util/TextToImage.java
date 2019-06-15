package org.blue.helper.test.util;

/**
 * @Description <P></P>
 * @Author allen
 * @Date 2019/1/7
 * @Version 1.0.0
 **/

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sun.image.codec.jpeg.JPEGCodec;
import org.blue.helper.StringHelper.utils.ImageUtil;

public class TextToImage {

    /**
     * 文本文件
     */
    private File textFile;
    /**
     * 图片文件
     */
    private File imageFile;
    /**
     * 图片
     */
    private BufferedImage image;
    /**
     * 图片宽度
     */
    private final int IMAGE_WIDTH = 500;
    /**
     * 图片高度
     */
    private final int IMAGE_HEIGHT = 570;
    /**
     * 图片类型
     */
    private final int IMAGE_TYPE = BufferedImage.TYPE_INT_RGB;

    /**
     * 构造函数
     *
     * @param textFile  文本文件
     * @param imageFile 图片文件
     */
    public TextToImage(File textFile, File imageFile) {
        this.textFile = textFile;
        this.imageFile = imageFile;
        if (imageFile.exists()){
            imageFile.delete();
        }
        try {
            imageFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.image = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, IMAGE_TYPE);
    }

    /**
     * 将文本文件里文字，写入到图片中保存
     *
     * @return boolean  true，写入成功；false，写入失败
     */
    public boolean convert() {

        //获取图像上下文
        Graphics g = createGraphics(image);
        String line;
        //图片中文本行高
        final int Y_LINEHEIGHT = 15;
        int lineNum = 1;
        try (BufferedReader reader = new BufferedReader(new FileReader(textFile));
             FileOutputStream fos = new FileOutputStream(imageFile);) {
            while ((line = reader.readLine()) != null) {
                g.drawString(line, 0, lineNum * Y_LINEHEIGHT);
                lineNum++;
            }
            g.dispose();

            //保存为jpg图片
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fos);
            encoder.encode(image);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 获取到图像上下文
     *
     * @param image 图片
     * @return Graphics
     */
    private Graphics createGraphics(BufferedImage image) {
        Graphics g = image.createGraphics();
        g.setColor(null); //设置背景色
        g.fillRect(0, 0, IMAGE_WIDTH, IMAGE_HEIGHT);//绘制背景
        g.setColor(Color.BLACK); //设置前景色
        g.setFont(new Font("微软雅黑", Font.PLAIN, 15)); //设置字体
        return g;
    }

    public static void main(String[] args) {
        try {
            for (int i = 1; i <73 ; i++) {
                int x=i*36;
                File file = new File("C:\\Users\\User\\Desktop\\zifuji\\" + x + ".txt");
                File imageFile = new File("C:\\Users\\User\\Desktop\\zifuji\\imgs\\" + x + ".jpg");
                TextToImage texttoimage = new TextToImage(file, imageFile);
                texttoimage.convert();
            }
            File textFile = new File("C:\\Users\\User\\Desktop\\zifuji\\2615.txt");
            File imageFile = new File("C:\\Users\\User\\Desktop\\zifuji\\imgs\\2615.jpg");
            TextToImage texttoimage = new TextToImage(textFile, imageFile);
            texttoimage.convert();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
