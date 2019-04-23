package org.blue.helper.StringHelper.service.bookkeeping.impl;

import org.apache.commons.lang3.StringUtils;
import org.blue.helper.StringHelper.common.exception.HelperException;
import org.blue.helper.StringHelper.service.bookkeeping.intf.FileService;
import org.blue.helper.StringHelper.utils.Base64Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service("BookFileServiceImpl")
public class FileServiceImpl implements FileService {
    private static final Logger LOGGER=LoggerFactory.getLogger(FileServiceImpl.class);

    @Async
    @Override
    public void saveFiles(List<MultipartFile> files, String targetDir) {
        if (StringUtils.isBlank(targetDir)) {
            throw new HelperException("A valid save address must be specified");
        }
        for (MultipartFile file : files) {
            String filePath = targetDir + "/" + file.getOriginalFilename();
            File desFile = new File(filePath);
            if (!desFile.getParentFile().exists()) {
                desFile.mkdirs();
            }
            try {
                file.transferTo(desFile);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
                continue;
            }
        }

    }

    @Async
    @Override
    public void saveFile(MultipartFile file, String targetDir) {
        List<MultipartFile> files =new ArrayList<MultipartFile>();
        LOGGER.debug("===========fileName:"+file.getOriginalFilename());
        files.add(file);
        this.saveFiles(files,targetDir);
    }

    @Override
    public void saveBase64File(String fileBase64, String targetDir,String fileName) {
        Base64Utils.Base64ToImage(fileBase64,targetDir,fileName);
    }
}
