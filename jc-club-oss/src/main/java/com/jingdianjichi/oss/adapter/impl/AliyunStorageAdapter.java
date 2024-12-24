package com.jingdianjichi.oss.adapter.impl;

import com.jingdianjichi.oss.entity.FileInfo;
import com.jingdianjichi.oss.adapter.StorageAdapter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;

/**
 * @author jay
 * @since 2024/12/18 下午8:59
 */
@Service
public class AliyunStorageAdapter implements StorageAdapter {
    /**
     * 创建一个bucket
     *
     * @param bucketName bucket名称
     */
    @Override
    public void createBucket(String bucketName) {

    }

    /**
     * 获取全部bucket名称
     *
     * @return bucket名称集合
     */
    @Override
    public List<String> getAllBucketsNames() {
        return Collections.emptyList();
    }

    /**
     * 上传文件
     *
     * @param uploadFile 文件流
     * @param bucketName bucket名称
     * @param objectName 文件名称
     */
    @Override
    public void uploadFile(MultipartFile uploadFile, String bucketName, String objectName) {

    }

    /**
     * 获取全部bucket
     *
     * @param bucketName bucket名称
     * @return bucket名称
     */
    @Override
    public List<String> getAllObjects(String bucketName) {
        return Collections.emptyList();
    }

    /**
     * 获取指定bucket下的所有文件与文件夹名称
     *
     * @param bucketName bucket名称
     * @return 文件夹名称集合
     */
    @Override
    public List<FileInfo> getAllFiles(String bucketName) {
        return Collections.emptyList();
    }

    /**
     * 下载文件
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @return 输入流
     */
    @Override
    public InputStream downloadFile(String bucketName, String objectName) {
        return null;
    }

    /**
     * 删除指定的存储桶
     *
     * @param bucketName 存储桶名称
     */
    @Override
    public void removeBucket(String bucketName) {

    }

    /**
     * 删除指定bucket中的文件
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     */
    @Override
    public void removeFile(String bucketName, String objectName) {

    }

    /**
     * 获取文件下载地址
     *
     * @param bucketName bucket名称
     * @param objName    文件名称
     * @return 文件下载地址
     */
    @Override
    public String getUrl(String bucketName, String objName) {
        return "";
    }
}
