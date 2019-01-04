package org.blue.helper.StringHelper.service.impl;

import org.blue.helper.StringHelper.service.Tess4JService;
import org.blue.helper.StringHelper.utils.Tess4JUtil;
import org.blue.helper.StringHelper.utils.support.Tess4jLanguages;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Description <P></P>
 * @Author v-Rui.Xiong@bl.com
 * @Date 2019/1/4
 * @Version 1.0.0
 **/
@Service
public class Tess4JServiceImpl implements Tess4JService {

    @Override
    public String extractCharFromImg(MultipartFile file) {
        String result="";
        try (InputStream is=file.getInputStream()){
            BufferedImage bi= Tess4JUtil.getBufferedImage(is);
            result=Tess4JUtil.doOCR_BufferedImage(bi,Tess4jLanguages.CNSMP.getLan());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String extractCharFromPdf(MultipartFile file) {
        String result="";
        try (InputStream is=file.getInputStream()){
            BufferedImage bi= Tess4JUtil.getBufferedImage(is);
            result=Tess4JUtil.doOCR_BufferedImage(bi,Tess4jLanguages.CNSMP.getLan());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
