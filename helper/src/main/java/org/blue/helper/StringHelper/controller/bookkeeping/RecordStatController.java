package org.blue.helper.StringHelper.controller.bookkeeping;

import com.github.pagehelper.PageInfo;
import org.blue.helper.StringHelper.aop.annotation.NeedLogin;
import org.blue.helper.StringHelper.aop.annotation.ParamsCheck;
import org.blue.helper.StringHelper.common.enums.SysCode;
import org.blue.helper.StringHelper.controller.bookkeeping.support.ReqParam.StatListReqParam;
import org.blue.helper.StringHelper.controller.bookkeeping.support.RspParam.BillStatRspParam;
import org.blue.helper.StringHelper.service.bookkeeping.intf.BillStatService;
import org.blue.helper.StringHelper.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/recordView/")
public class RecordStatController extends BaseController{
    private static final Logger LOGGER=LoggerFactory.getLogger(RecordStatController.class);
    @Autowired
    private BillStatService statService;

    @NeedLogin
    @PostMapping("getStatList")
    @ResponseBody
    @ParamsCheck(param = "StatListReqParam",paramClass = StatListReqParam.class)
    public Map<String,Object> getStatList(@RequestBody StatListReqParam reqParam, HttpServletRequest request){
        String userId=getUserIdByToken(request,null);
        if (userId==null) return ResultUtil.creComErrorResult(SysCode.LOGIN_CHECK_ERROR.getCode(),SysCode.LOGIN_CHECK_ERROR.getMsg());
        PageInfo<BillStatRspParam> pageRsp = statService.doBillStat(reqParam,Long.valueOf(userId));
        return ResultUtil.objSuccess(pageRsp);
    }
}
