package org.blue.helper.StringHelper.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.blue.helper.StringHelper.common.enums.SysCode;
import org.blue.helper.StringHelper.controller.bookkeeping.BaseController;
import org.blue.helper.StringHelper.controller.support.req.FileReq;
import org.blue.helper.StringHelper.utils.ResultUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @Description <P></P>
 * @Author allen
 * @Date 2018/12/27
 * @Version 1.0.0
 **/
@Controller
@RequestMapping("/cs/")
public class CloudStorageController extends BaseController {

    @GetMapping("login")
    public String login(HttpServletRequest request){
        return "cloudstorage/login";
    }
    @GetMapping("main")
    public String mainPage(HttpServletRequest request){
        return "cloudstorage/main";
    }

    @GetMapping("getFileList")
    @ResponseBody
    public Map<String,Object> getFileList(@RequestBody FileReq fileReq, HttpServletRequest request){
        String userId=super.getUserIdByToken(request,null);
        if (userId==null) return ResultUtil.creComErrorResult(SysCode.LOGIN_CHECK_ERROR.getCode(),SysCode.LOGIN_CHECK_ERROR.getMsg());


        return ResultUtil.commonSuccess();
    }
}
