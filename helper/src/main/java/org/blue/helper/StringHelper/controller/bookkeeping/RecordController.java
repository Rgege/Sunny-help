package org.blue.helper.StringHelper.controller.bookkeeping;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.blue.helper.StringHelper.aop.annotation.NeedLogin;
import org.blue.helper.StringHelper.aop.annotation.ParamsCheck;
import org.blue.helper.StringHelper.aop.support.CheckType;
import org.blue.helper.StringHelper.common.enums.SysCode;
import org.blue.helper.StringHelper.controller.bookkeeping.support.ReqParam.RecordReqParam;
import org.blue.helper.StringHelper.service.bookkeeping.intf.FileService;
import org.blue.helper.StringHelper.service.bookkeeping.intf.RecordService;
import org.blue.helper.StringHelper.utils.ResultUtil;
import org.blue.helper.StringHelper.utils.StringZipUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import redis.clients.util.Hashing;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.nio.file.Files;
import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("/record/")
public class RecordController extends BaseController {
    private static final Logger LOGGER=LoggerFactory.getLogger(RecordController.class);

    @Autowired
    private FileService fileService;
    @Autowired
    private RecordService recordService;


    @PostMapping("getRecordId")
    @ResponseBody
    public Map<String, Object> getRecordId(String token,HttpServletRequest request) {
        String userId=super.getUserIdByToken(request,null);

        if (userId==null) return ResultUtil.creComErrorResult(SysCode.LOGIN_CHECK_ERROR.getCode(),SysCode.LOGIN_CHECK_ERROR.getMsg());

        return ResultUtil.objSuccess(creatRecordCode(userId));
    }

//    @ResponseBody
//    @PostMapping("voucherUpload")
//    public Map<String, Object> voucherUpload(@RequestParam("voucherFiles") MultipartFile[] voucherFiles,String recordId, HttpServletRequest request) {
//        List<MultipartFile> fileList=new ArrayList<MultipartFile>();
//        String baseDir=vouchersUrl+recordId;
//        try {
//            fileService.saveFiles(fileList,baseDir);
//            return ResultUtil.commonSuccess();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResultUtil.commonError();
//        }
//    }

    @NeedLogin
    @ResponseBody
    @PostMapping("addRecord")
    @ParamsCheck(param = "RecordReqParam", paramClass = RecordReqParam.class)
    public Map<String, Object> addRecord(@RequestBody RecordReqParam reqParam, HttpServletRequest request){
        LOGGER.debug("============addRecord:"+ reqParam.toString());
        String userId=super.getUserIdByToken(request,null);

        if (userId==null) return ResultUtil.creComErrorResult(SysCode.LOGIN_CHECK_ERROR.getCode(),SysCode.LOGIN_CHECK_ERROR.getMsg());
        reqParam.setUserId(userId);
        if(!validateRecordCode(reqParam.getRecordCode(),userId)){
            return ResultUtil.creComErrorResult(SysCode.RECORD_RESUBMIT.getCode(),SysCode.RECORD_RESUBMIT.getMsg());
        }
        int addRow=recordService.newRecord(reqParam);

        if (addRow!=0){
            return ResultUtil.commonSuccess();
        }else {
            return ResultUtil.msgError("操作失败");
        }
    }
}
