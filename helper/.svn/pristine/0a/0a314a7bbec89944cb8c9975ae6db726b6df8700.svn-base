package org.blue.helper.StringHelper.service.impl;

import org.blue.helper.StringHelper.controller.support.QRCodeReq;
import org.blue.helper.StringHelper.persistence.QRCodeInfoMapper;
import org.blue.helper.StringHelper.persistence.entity.model.QRCodeInfo;
import org.blue.helper.StringHelper.service.QRCodeService;
import org.blue.helper.StringHelper.utils.FileUtils;
import org.blue.helper.StringHelper.utils.QRCodeUtils;
import org.blue.helper.test.util.QRCodeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class QRCodeServiceImpl implements QRCodeService {
    @Autowired
    private QRCodeInfoMapper qrCodeInfoMapper;
    @Value("${QRCodePath}")
    private String qRCodePath;

    private static final String logoPath = "C:\\Users\\My\\Desktop\\nana.jpg";

    @Override
    public Map<String,Object> createNewQRCode(String saveData) {
        QRCodeInfo qrCodeInfo=new QRCodeInfo();
        qrCodeInfo.setSaveData(saveData);
        Map<String,Object> rspMap=new HashMap<>();
        if(FileUtils.makPathExist(qRCodePath)) {
//            Map<String, String> map = QRCodeUtils.createQRCodeImg(saveData, qRCodePath);
            Map<String, String> map = QRCodeUtil.encode(saveData, logoPath, qRCodePath, true);
            if(map!=null) {
                if(map.get("ERROR")!=null){
                    rspMap.put("ERROR",map.get("ERROR"));
                    return rspMap;
                }
                qrCodeInfo.setImageName(map.get("QRCodeName"));
                qrCodeInfo.setSavePath(map.get("imgPath"));
                qrCodeInfo.setImageType("png");
                qrCodeInfo.setStatus(0);
                qrCodeInfoMapper.insert(qrCodeInfo);
                QRCodeReq qrCodeReq=new QRCodeReq();
                BeanUtils.copyProperties(qrCodeInfo,qrCodeReq);
                rspMap.put("QRCode",qrCodeReq);
            }
        }

        return rspMap;
    }
}
