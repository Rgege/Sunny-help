package org.blue.helper.StringHelper.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Description <P></P>
 * @Author v-Rui.Xiong@bl.com
 * @Date 2018/12/28
 * @Version 1.0.0
 **/
public interface FileService {
    /**
     * 上传文件
     * @param saveUrl
     * @param file
     */
    void uploadFile(String saveUrl,double fileSize,MultipartFile file);
}
