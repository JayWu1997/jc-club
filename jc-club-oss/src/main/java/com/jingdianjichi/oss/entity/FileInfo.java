package com.jingdianjichi.oss.entity;

/**
 * 文件信息
 * @author jay
 * @since 2024/12/18 下午7:55
 */
public class FileInfo {

    private String fileName;

    private boolean directoryFlag;

    private String etag;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean isDirectoryFlag() {
        return directoryFlag;
    }

    public void setDirectoryFlag(boolean directoryFlag) {
        this.directoryFlag = directoryFlag;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }
}
