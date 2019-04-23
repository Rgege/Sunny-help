//package org.blue.helper.StringHelper.controller;
//
//import org.blue.helper.StringHelper.controller.bookkeeping.BaseController;
//import org.blue.helper.StringHelper.controller.support.MultipartFileParam;
//import org.blue.helper.StringHelper.service.Tess4JService;
//import org.blue.helper.StringHelper.utils.ResultUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Map;
//
///**
// * @Description <P></P>
// * @Author v-Rui.Xiong@bl.com
// * @Date 2019/1/4
// * @Version 1.0.0
// **/
//@Controller
//@RequestMapping("/ai/extract/")
//public class Tess4JController extends BaseController {
//    @Autowired
//    private Tess4JService service;
//
//    //Extract characters from a picture
//
//    /**
//     *
//     * @param file
//     * @param request
//     * @return
//     */
//    @RequestMapping("dealImg")
//    @ResponseBody
//    public Map<String,Object> dealImg(MultipartFile file, HttpServletRequest request){
//        service.extractCharFromImg(file);
//        return ResultUtil.commonError();
//    }
//    @RequestMapping("dealPdf")
//    @ResponseBody
//    public Map<String,Object> dealPdf(MultipartFile file, HttpServletRequest request){
//        service.extractCharFromPdf(file);
//        return ResultUtil.commonError();
//    }
//}
