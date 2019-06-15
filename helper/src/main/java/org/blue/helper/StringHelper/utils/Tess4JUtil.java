//package org.blue.helper.StringHelper.utils;
//
//import com.recognition.software.jdeskew.ImageDeskew;
//import net.sourceforge.tess4j.ITessAPI;
//import net.sourceforge.tess4j.ITesseract;
//import net.sourceforge.tess4j.Tesseract;
//import net.sourceforge.tess4j.Word;
//import net.sourceforge.tess4j.util.ImageHelper;
//import net.sourceforge.tess4j.util.Utils;
//import org.blue.helper.StringHelper.utils.support.Tess4jLanguages;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.imageio.ImageIO;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
///**
// * @Description <P></P>
// * @Author allen
// * @Date 2019/1/4
// * @Version 1.0.0
// **/
//public class Tess4JUtil {
//    private static final Logger logger=LoggerFactory.getLogger(Tess4JUtil.class);
//    private static String tessdataPath;
//    static final double MINIMUM_DESKEW_THRESHOLD ;
//
//    static {
//        tessdataPath=Tess4JUtil.class.getClassLoader().getResource("").getPath().
//                replace("/target/classes/","").substring(1)+ "//tessdata";
//        MINIMUM_DESKEW_THRESHOLD= 0.05d;
//    }
//
//    public static void main(String[] args) {
//        File imageFile = new File("D:\\2.png");
//        try {
////            System.out.println(doOCR_File("D:\\1.pdf",Tess4jLanguages.CNSMP.getLan()));
//
//            System.out.println(doOCR_SkewedImage(getBufferedImage(new FileInputStream(imageFile)),Tess4jLanguages.CNSMP.getLan()));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    public static BufferedImage getBufferedImage(InputStream is) throws IOException {
//        BufferedImage bi = ImageIO.read(is);
//        return bi;
//    }
//
//    /**
//     * Test of doOCR method, of class Tesseract.
//     * 根据图片文件进行识别
//     * @throws Exception while processing image.
//     */
//    public static String doOCR_File(String filePath,String language) throws Exception {
//        logger.info("doOCR on a jpg image");
//        File imageFile = new File(filePath);
//        //set language
//        ITesseract instance = new Tesseract();
//        instance.setDatapath(tessdataPath);
//        if (StringUtil.isBlank(language)){
//            instance.setLanguage(Tess4jLanguages.EN.getLan());
//        }else {
//            instance.setLanguage(language);
//        }
//        String result = instance.doOCR(imageFile);
//        logger.info(result);
//        return result;
//    }
//
//    /**
//     * Test of doOCR method, of class Tesseract.
//     * 根据图片流进行识别
//     * @throws Exception while processing image.
//     */
//    public static String doOCR_BufferedImage(BufferedImage bi,String language) throws Exception {
//        logger.info("doOCR on a buffered image of a PNG");
//
//        //set language
//        ITesseract instance = new Tesseract();
//        instance.setDatapath(tessdataPath);
//        if (StringUtil.isBlank(language)){
//            instance.setLanguage(Tess4jLanguages.EN.getLan());
//        }else {
//            instance.setLanguage(language);
//        }
//
//        String result = instance.doOCR(bi);
//        logger.info(result);
//        return result;
//    }
//
//    /**
//     * Test of getSegmentedRegions method, of class Tesseract.
//     * 得到每一个划分区域的具体坐标
//     * @throws java.lang.Exception
//     */
//    public static void getSegmentedRegions(BufferedImage bi) throws Exception {
//        logger.info("getSegmentedRegions at given TessPageIteratorLevel");
//        int level = ITessAPI.TessPageIteratorLevel.RIL_SYMBOL;
//        logger.info("PageIteratorLevel: " + Utils.getConstantName(level, ITessAPI.TessPageIteratorLevel.class));
//        ITesseract instance = new Tesseract();
//        List<Rectangle> result = instance.getSegmentedRegions(bi, level);
//        for (int i = 0; i < result.size(); i++) {
//            Rectangle rect = result.get(i);
//            logger.info(String.format("Box[%d]: x=%d, y=%d, w=%d, h=%d", i, rect.x, rect.y, rect.width, rect.height));
//        }
//    }
//
//
//    /**
//     * Test of doOCR method, of class Tesseract.
//     * 根据定义坐标范围进行识别
//     * @throws Exception while processing image.
//     */
//    public static String doOCR_File_Rectangle(String filePath,String language) throws Exception {
//        File imageFile = new File(filePath);
//        //设置语言库
//        ITesseract instance = new Tesseract();
//        instance.setDatapath(tessdataPath);
//        if (StringUtil.isBlank(language)){
//            instance.setLanguage(Tess4jLanguages.EN.getLan());
//        }else {
//            instance.setLanguage(language);
//        }        //划定区域
//        // x,y是以左上角为原点，width和height是以xy为基础
//        Rectangle rect = new Rectangle(84, 21, 15, 13);
//        String result = instance.doOCR(imageFile, rect);
//        return result;
//    }
//
//    /**
//     * Test of createDocuments method, of class Tesseract.
//     * 存储结果
//     * @throws java.lang.Exception
//     */
//    public static void createDocuments(String filePath,String language) throws Exception {
//        logger.info("createDocuments for png");
//        File imageFile = new File(filePath);
//        String outputbase = "target/test-classes/docrenderer-2";
//        List<ITesseract.RenderedFormat> formats = new ArrayList<ITesseract.RenderedFormat>(Arrays.asList(ITesseract.RenderedFormat.HOCR, ITesseract.RenderedFormat.TEXT));
//
//        //设置语言库
//        ITesseract instance = new Tesseract();
//        instance.setDatapath(tessdataPath);
//        if (StringUtil.isBlank(language)){
//            instance.setLanguage(Tess4jLanguages.EN.getLan());
//        }else {
//            instance.setLanguage(language);
//        }
//        instance.createDocuments(new String[]{imageFile.getPath()}, new String[]{outputbase}, formats);
//    }
//
//    /**
//     * Test of getWords method, of class Tesseract.
//     * 取词方法
//     * @throws java.lang.Exception
//     */
//    public static List<Word> getWords(BufferedImage bi,String language) throws Exception {
//        logger.info("getWords");
//
//        //设置语言库
//        ITesseract instance = new Tesseract();
//        instance.setDatapath(tessdataPath);
//        if (StringUtil.isBlank(language)){
//            instance.setLanguage(Tess4jLanguages.EN.getLan());
//        }else {
//            instance.setLanguage(language);
//        }
//        //按照每个字取词
//        int pageIteratorLevel = ITessAPI.TessPageIteratorLevel.RIL_SYMBOL;
//        logger.info("PageIteratorLevel: " + Utils.getConstantName(pageIteratorLevel, ITessAPI.TessPageIteratorLevel.class));
//        List<Word> result = instance.getWords(bi, pageIteratorLevel);
//        return result;
//    }
//
//    /**
//     * Test of Invalid memory access.
//     * 处理倾斜
//     * @throws Exception while processing image.
//     */
//    public static String doOCR_SkewedImage(BufferedImage bi,String language) throws Exception {
//        //设置语言库
//        ITesseract instance = new Tesseract();
//        instance.setDatapath(tessdataPath);
//        if (StringUtil.isBlank(language)){
//            instance.setLanguage(Tess4jLanguages.EN.getLan());
//        }else {
//            instance.setLanguage(language);
//        }
//        logger.info("doOCR on a skewed PNG image");
//        ImageDeskew id = new ImageDeskew(bi);
//        double imageSkewAngle = id.getSkewAngle(); // determine skew angle
//        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>imageSkewAngle:"+imageSkewAngle);
//        if ((imageSkewAngle > MINIMUM_DESKEW_THRESHOLD || imageSkewAngle < -(MINIMUM_DESKEW_THRESHOLD))) {
//            bi = ImageHelper.rotateImage(bi, -imageSkewAngle); // deskew image
//        }
//
//        String result = instance.doOCR(bi);
//        logger.info(result);
//        return result;
//    }
//}
