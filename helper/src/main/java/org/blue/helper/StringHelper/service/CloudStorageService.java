package org.blue.helper.StringHelper.service;

import com.github.pagehelper.PageInfo;
import org.blue.helper.StringHelper.controller.support.req.FileReq;
import org.blue.helper.StringHelper.controller.support.rsp.FileRsp;

import java.util.Map;


/**
 * @Description <P></P>
 * @Author v-Rui.Xiong@bl.com
 * @Date 2018/12/27
 * @Version 1.0.0
 **/
public interface CloudStorageService {
    /**
     * 根据userId以及文件夹名获取文件夹下的文件列表   当文件夹名为空时默认获取顶级目录
     * @param userId
     * @param fileReq
     * @return
     */
    PageInfo<FileRsp> qureFileList(String userId, FileReq fileReq);

    Map<String,Object> addNewFile(String userId, FileReq fileReq);
}
