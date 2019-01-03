package org.blue.helper.StringHelper.controller.bookkeeping;

import org.blue.helper.StringHelper.controller.bookkeeping.support.RspParam.BillTypeRspParam;
import org.blue.helper.StringHelper.persistence.entity.model.BillType;
import org.blue.helper.StringHelper.service.bookkeeping.intf.BillService;
import org.blue.helper.StringHelper.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/bill/")
public class BillController extends BaseController{
    @Autowired
    private BillService billService;

    @GetMapping("getBillType")
    @ResponseBody
    public Map<String,Object> getBillType(String pCode,Integer page, HttpServletRequest request){
        if (page==null)page=0;
        BillTypeRspParam rspParam=billService.getBillType(pCode,page,3);
        if (rspParam==null) return ResultUtil.commonError();
        else return ResultUtil.objSuccess(rspParam);
    }
}
