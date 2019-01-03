package org.blue.helper.test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Assemble {
    /**
     * 拼装接口请求参数
     * @param type 1：会员识别参数   2：折扣计算参数
     * @return
     */
    private  String getInvokParam(int type){
        String param="";
        com.alibaba.fastjson.JSONObject json=new com.alibaba.fastjson.JSONObject();
        if(type==1){//会员识别参数
            Map<String,Object> paraMap=new LinkedHashMap<String, Object>();
            paraMap.put("identityNo","15801983447");
            paraMap.put("checkType",1);
            paraMap.put("sysid","1107");
            param=com.alibaba.fastjson.JSONObject.toJSONString(paraMap);
        }else if(type==2){//折扣计算参数
            ArrayList<Map<String,Object>> details=new ArrayList<Map<String,Object>>();
            Map<String,Object> detailsMap=new LinkedHashMap<String, Object>();
            detailsMap.put("brandSid","01PR");
            detailsMap.put("categoryid","72010700");
            detailsMap.put("goodsDetSid","37830766");
            detailsMap.put("goodsName","运动鞋");
            detailsMap.put("itemId","1");
            detailsMap.put("origPrice","1099");
            detailsMap.put("salePrice","1099");
            detailsMap.put("saleQuantity","1");
            detailsMap.put("supplySid","082777");
            details.add(detailsMap);
            ArrayList<Map<String,Object>> titles=new ArrayList<Map<String,Object>>();
            Map<String,Object> titlesMap=new LinkedHashMap<String, Object>();
            titlesMap.put("cardNo","15801983447");
            titlesMap.put("cashierId","204");
            titlesMap.put("channelId","5");
            titlesMap.put("checkType","3");
            titlesMap.put("docType","1");
            titlesMap.put("orderId","204201808090006");
            titlesMap.put("payTime","20180809113515");
            titlesMap.put("sendTime","20180809113515");
            titlesMap.put("shopId","001107");
            titlesMap.put("ticketId","204201808090006");
            titlesMap.put("yetaiId","1020");
            titles.add(titlesMap);
            json.put("sysid","1107");
            json.put("titles",titles);
            json.put("details",details);
            param=json.toJSONString();
        }
        return param;
    }

    public static String rongduan(String node,int Channel){
        //组装前置机三通道接口参数
        List<Map<String,Object>> configs=new ArrayList<Map<String, Object>>();
        Map<String,Object> paraMap=new LinkedHashMap<String, Object>();
        paraMap.put("paraName",String.format("BUDI_DATA_INVOKING_TYPE&%s", node));
        paraMap.put("paraValue",Channel);
        paraMap.put("paraType","INVOKING");
        paraMap.put("comment","数据查询类型：0-接口调用；1-中台数据库；2-本地数据库");
        configs.add(paraMap);
        com.alibaba.fastjson.JSONObject json=new com.alibaba.fastjson.JSONObject();
        json.put("configs",configs);
        return json.toJSONString();
    }
    public static void main(String[] args) {
        System.out.println(rongduan("10.201.36.121",0));
    }
}
