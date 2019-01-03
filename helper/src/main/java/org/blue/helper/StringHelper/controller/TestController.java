package org.blue.helper.StringHelper.controller;

import org.blue.helper.StringHelper.aop.annotation.NeedLogin;
import org.blue.helper.StringHelper.persistence.entity.model.QRCodeInfo;
import org.blue.helper.StringHelper.persistence.entity.pojo.TestPOJO;
import org.blue.helper.StringHelper.service.QRCodeService;
import org.blue.helper.StringHelper.service.TestService;
import org.blue.helper.StringHelper.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/test/")
public class TestController {
    private static final Logger logger=LoggerFactory.getLogger(TestController.class);

    @Autowired
    private TestService testService;
    @Autowired
    private QRCodeService qrCodeService;

    @NeedLogin
    @RequestMapping("testAdd")
    @ResponseBody
    public Map<String,Object> testAdd(@RequestBody TestPOJO pojo , HttpServletRequest request){
        testService.testAdd(pojo);
        return ResultUtil.commonSuccess();
    }

    @RequestMapping(value = {"/","/index"})
    public String index(HttpServletRequest request){
        return "testWeUi";
    }

    @NeedLogin
    @RequestMapping(value = {"videoPlayer"})
    public String videoPlayer(HttpServletRequest request){
        return "bus/videoPlayer";
    }

    @PostMapping("testQRCode")
    @ResponseBody
    public Map<String,Object> testQRCode(@RequestBody String jsonStr){
        return ResultUtil.objSuccess(qrCodeService.createNewQRCode(jsonStr));
    }
    @GetMapping("file")
    public String file(HttpServletRequest request){
        return "bus/fileUpDown/file";
    }

    @GetMapping("test")
    public String test(HttpServletRequest request){
        return "user/newLogin";
    }

    @GetMapping("testAsync")
    @ResponseBody
    public  Map<String,Object> testAsync(){
        logger.info("===============>into TestController.testAsync() CurrentThread:"+Thread.currentThread().getName());
        testService.testAsync();
        logger.info("===============>out TestController.testAsync() CurrentThread:"+Thread.currentThread().getName());
        return ResultUtil.commonSuccess();
    }
    @GetMapping("chartPage")
    public String webSocket(){
        return "bus/chartroom/mainPanel";
    }

    @PostMapping("testHttp")
    public String testHttp(HttpServletRequest request){
        return "123456";
    }
}
