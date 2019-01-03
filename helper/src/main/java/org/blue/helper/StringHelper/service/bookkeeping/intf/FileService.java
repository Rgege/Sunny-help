package org.blue.helper.StringHelper.service.bookkeeping.intf;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    /**
     * 保存文件到指定文件夹 批量
     * @param files    要保存的文件
     * @param targetDir  目标文件夹
     */
     void saveFiles(List<MultipartFile> files,String targetDir);

    /**
     * 保存文件到指定文件夹 单个
     * @param file    要保存的文件
     * @param targetDir  目标文件夹
     */
     void saveFile(MultipartFile file,String targetDir);

    /**
     * 保存文件到指定文件夹 单个
     * @param fileBase64    要保存的文件Base64j加密串
     * @param targetDir  目标文件夹
     */
    void saveBase64File(String fileBase64, String targetDir,String fileName);
}
