package org.blue.helper.StringHelper.service;

import org.blue.helper.StringHelper.persistence.entity.model.QRCodeInfo;

import java.util.Map;

public interface QRCodeService {
    Map<String,Object> createNewQRCode(String saveData);
}
