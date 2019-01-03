package org.blue.helper.StringHelper.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.blue.helper.StringHelper.common.constants.FileTypes;
import org.blue.helper.StringHelper.controller.support.req.FileReq;
import org.blue.helper.StringHelper.controller.support.rsp.FileRsp;
import org.blue.helper.StringHelper.persistence.CloudStorageMapper;
import org.blue.helper.StringHelper.persistence.entity.model.CloudStorage;
import org.blue.helper.StringHelper.persistence.entity.model.CloudStorageExample;
import org.blue.helper.StringHelper.service.CloudStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

/**
 * @Description <P></P>
 * @Author v-Rui.Xiong@bl.com
 * @Date 2018/12/27
 * @Version 1.0.0
 **/
@Service
public class CloudStorageServiceImpl implements CloudStorageService {

    @Autowired
    private CloudStorageMapper cloudStorageMapper;

    @Override
    public Map<String,Object> addNewFile(String userId, FileReq fileReq) {
        Map<String,Object> map=new HashMap<String, Object>();
        CloudStorageExample example=new CloudStorageExample();
        CloudStorageExample.Criteria criteria=example.createCriteria();
        criteria.andUserIdEqualTo(Long.parseLong(userId));
        criteria.andPIdEqualTo(Long.parseLong(fileReq.getpId()));
        criteria.andFileNameEqualTo(fileReq.getFileName()==null?fileReq.getFolderName():fileReq.getFileName());
        criteria.andFileTypeEqualTo(fileReq.getFileType());
        List<CloudStorage> cloudStorageList=cloudStorageMapper.selectByExample(example);
        String fileName=fileReq.getFileName()==null?fileReq.getFolderName():fileReq.getFileName();
        Date date=new Date();
        if (cloudStorageList !=null && !cloudStorageList.isEmpty()){//同用户 相同目录下 同类型的文件命名必须唯一
            fileName=fileName+date.getTime();
        }

        Timestamp timestamp=new Timestamp(date.getTime());
        CloudStorage cloudStorage=new CloudStorage();
        cloudStorage.setFileName(fileReq.getFileName()==null?fileReq.getFolderName():fileReq.getFileName());
        cloudStorage.setUserId(Long.parseLong(userId));
        cloudStorage.setFileType(fileReq.getFileType());
        cloudStorage.setFileSize(Double.parseDouble(fileReq.getFileSize()));
        cloudStorage.setpId(Long.parseLong(fileReq.getpId()));
        cloudStorage.setCreatedTime(timestamp);
        cloudStorage.setUpdateTime(timestamp);
        cloudStorageMapper.insert(cloudStorage);

        return null;
    }

    @Override
    public PageInfo<FileRsp> qureFileList(String userId, FileReq fileReq) {

        PageHelper.startPage(fileReq.getCurrentPage(), fileReq.getPageSize());

        List<CloudStorage> cloudStorageList=null;
        CloudStorageExample example=new CloudStorageExample();
        CloudStorageExample.Criteria criteria=example.createCriteria();
        criteria.andUserIdEqualTo(Long.parseLong(userId));
        if (StringUtils.isNotBlank(fileReq.getFileName())){//查询文件或文件夹
            criteria.andFileNameLike(fileReq.getFileName());
            cloudStorageList=cloudStorageMapper.selectByExample(example);
        } else if (StringUtils.isNotBlank(fileReq.getFolderName())){//查询某一文件夹下所有文件
            CloudStorage cloudStorage=new CloudStorage();
            cloudStorage.setUserId(Long.parseLong(userId));
            cloudStorage.setFileName(fileReq.getFolderName());
            cloudStorage.setFileType(FileTypes.FOLDER);
            Long id=cloudStorageMapper.selectIdByFileNameAndFileType(cloudStorage);
            if (id !=null){
                criteria.andPIdEqualTo(id );
                cloudStorageList=cloudStorageMapper.selectByExample(example);
            }
        } else {//初始化查询
             cloudStorageList=cloudStorageMapper.selectByExample(example);
        }
        PageInfo<FileRsp> pageInfo=new PageInfo<FileRsp>(buildRspList(cloudStorageList));
        return null;
    }

    private List<FileRsp> buildRspList(List<CloudStorage> cloudStorageList){
        List<FileRsp> list=new ArrayList<FileRsp>();
        if (cloudStorageList !=null && !cloudStorageList.isEmpty()){
            for (CloudStorage cloudStorage:cloudStorageList){
                FileRsp fileRsp=new FileRsp();
                fileRsp.setFileName(cloudStorage.getFileName());
                fileRsp.setFileSzie(cloudStorage.getFileSize());
                fileRsp.setFileType(cloudStorage.getFileType());
                fileRsp.setpId(cloudStorage.getpId());
                fileRsp.setFileId(cloudStorage.getId());
                list.add(fileRsp);
            }
        }
        return list;
    }
}
