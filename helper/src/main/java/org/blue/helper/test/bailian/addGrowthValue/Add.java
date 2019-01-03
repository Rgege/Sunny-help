//package org.blue.helper.test.bailian.addGrowthValue;
//
//import com.alibaba.fastjson.JSON;
//import org.blue.helper.StringHelper.controller.support.rsp.HttpPosRsp;
//import org.blue.helper.StringHelper.persistence.HttpPostRspMapper;
//import org.blue.helper.StringHelper.utils.HttpRequestUtil;
//import org.blue.helper.test.util.ExcelUtil;
//import org.blue.helper.test.util.support.ExcelTb;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class Add {
//    private static final Logger logger=LoggerFactory.getLogger(Add.class);
//    @Autowired
//    private HttpPostRspMapper mapper;
//
//    public void sendHttpPos(){
//        String url="";
//        ExcelTb tb=addCustomerInfo("C:\\Users\\User\\Desktop\\补成长值.xlsx");
//        if(tb!=null){
//            List<String> paramsList=createParams(tb);
//            logger.info("==================正向:"+paramsList.size());
//            for (String param:paramsList) {
//                HttpPosRsp posRsp = HttpRequestUtil.doPostV2(url,param);
//                posRsp.booleanSuccess("00100000");
//                mapper.insert(posRsp);
//            }
//        }
//
//    }
//
//    public ExcelTb addCustomerInfo(String filePath) {
//        ExcelTb tb=null;
//        try {
//            tb=ExcelUtil.readExcelContentzNew(filePath);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return tb;
//    }
//
//    private List<String> createParams(ExcelTb tb){
//        List<String> params=new ArrayList<String>();
//
//        int rows=tb.getTotalRows();
//        int lines=tb.getTotalLines();
//        logger.info("==================excel表共:"+rows+"行"+lines+"列");
//        int decrese=0;
//        for (int i = 1; i <=rows ; i++) {
//            if(!tb.getCell(i,4).getValue().equals("1")){
//                decrese++;
//                continue;
//            }
//            Params param=new Params();
//            param.setMemberId(Long.valueOf(tb.getCell(i,1).getValue()));
//            param.setBillId(tb.getCell(i,8).getValue());
//            param.setBuId(tb.getCell(i,5).getValue());
//            param.setChannelId(tb.getCell(i,6).getValue());
//            param.setStoreId(tb.getCell(i,7).getValue());
//            param.setGrowthType("01");
//            param.setMoney(Double.valueOf(tb.getCell(i,10).getValue()));
//
//            params.add(JSON.toJSONString(param));
//        }
//        logger.info("==================共:"+decrese+"条逆向");
//        return params;
//    }
//
//    public static void main(String[] args) throws Exception {
////        ExcelTb tb=new Add().addCustomerInfo("C:\\Users\\User\\Desktop\\补成长值.xlsx");
////        int rows=tb.getTotalRows();
////        int lines=tb.getTotalLines();
////        System.out.println("======================总行数:"+rows +"总列数:"+lines);
////        System.out.println(tb.getCell(1,1));
////        for (int i = 0; i <rows ; i++) {
////            for (int j = 0; j <lines ; j++) {
////                System.out.println("第"+(i+1)+"行 第"+(j+1)+"列 的值是"+tb.getCell(i,j));
////            }
////        }
//        new Add().sendHttpPos();
//    }
//
//}
