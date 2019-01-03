package org.blue.helper.StringHelper.controller.bailian;

import org.blue.helper.StringHelper.controller.bailian.support.GSVReqParam;
import org.blue.helper.StringHelper.executor.MultithreadHttpPosExecutor;
import org.blue.helper.StringHelper.service.bailian.GrowthValueService;
import org.blue.helper.StringHelper.utils.ResultUtil;
import org.blue.helper.StringHelper.utils.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/bl/grv/")
public class GrowthValueController {
    private static final Logger logger=LoggerFactory.getLogger(GrowthValueController.class);

    private static final String filePath= "C:\\Users\\User\\Desktop\\退货.xlsx";

    @Autowired
    private GrowthValueService growthValueService;

    @RequestMapping("makeUpGrowthValue")
    @ResponseBody
    public Map<String,Object> makeUpGrowthValue(@RequestBody GSVReqParam param){
        logger.info("ReqParams:"+ param);
        List<String> params=growthValueService.getParams(filePath);
        growthValueService.sendHttpPos(param.getUrl(),params);
        return ResultUtil.commonSuccess();
    }

}


