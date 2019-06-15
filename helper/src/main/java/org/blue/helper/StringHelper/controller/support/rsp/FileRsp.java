package org.blue.helper.StringHelper.controller.support.rsp;

/**
 * @Description <P></P>
 * @Author allen
 * @Date 2018/12/27
 * @Version 1.0.0
 **/
public class FileRsp {
    private Long   fileId;
    private Long   pId;
    private String fileName;
    private Double fileSzie;
    private String fileType;

    @Override
    public String toString() {
        return "FileRsp{" +
                "fileId=" + fileId +
                ", pId=" + pId +
                ", fileName='" + fileName + '\'' +
                ", fileSzie=" + fileSzie +
                ", fileType='" + fileType + '\'' +
                '}';
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Double getFileSzie() {
        return fileSzie;
    }

    public void setFileSzie(Double fileSzie) {
        this.fileSzie = fileSzie;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
