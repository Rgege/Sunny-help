package org.blue.helper.StringHelper.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.blue.helper.StringHelper.service.QRCodeService;
import org.blue.helper.StringHelper.utils.Base64Utils;
import org.blue.helper.StringHelper.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;

@Controller
@RequestMapping("/QRCode/")
public class QRCodeController {
    private static final Logger logger=LoggerFactory.getLogger(QRCodeController.class);

    @Autowired
    QRCodeService qrCodeService;
    @Value("${QRCodePath}")
    private String qRCodePath;

    @GetMapping("qrCodePage")
    public String qrCodePage(){
        return "demo";
    }

    @PostMapping("creatQRCode")
    @ResponseBody
    public Map<String,Object> creatQRCode(@RequestBody String saveData,HttpServletRequest request){
        logger.debug("==================Into CreateQRCode Param:{}",saveData);
        JSONObject jsonObject=JSONObject.parseObject(saveData);
        if(saveData==null||saveData==""){
            return ResultUtil.commonError();
        }
        Map<String,Object> rspMap=qrCodeService.createNewQRCode(jsonObject.get("saveData").toString());
        return ResultUtil.objSuccess(rspMap);
    }

    @GetMapping("getQRCodeImg")
    public void getQRCodeImg(String QRName,HttpServletRequest request, HttpServletResponse response){
        File file=new File(qRCodePath,QRName);
        String base64str= Base64Utils.ImageToBase64ByLocal(file.getPath());
        //decoder
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] b = new byte[0];
        try {
            b = decoder.decodeBuffer(base64str);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            response.setHeader("Content-Type", "image/jpeg");
            response.getOutputStream().write(b);
            response.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
