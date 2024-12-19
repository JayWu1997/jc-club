package com.jingdianjichi.oss.adapter.impl;

import com.jingdianjichi.oss.entity.FileInfo;
import com.jingdianjichi.oss.adapter.StorageAdapter;
import com.jingdianjichi.oss.util.MinioUtil;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.List;

/**
 * @author jay
 * @since 2024/12/18 下午8:24
 */
@Service
public class MinioStorageAdapter implements StorageAdapter {

    @Resource
    private MinioUtil minioUtil;

    /**
     * 创建一个bucket
     *
     * @param bucketName bucket名称
     */
    @Override
    @SneakyThrows
    public void createBucket(String bucketName) {
        minioUtil.createBucket(bucketName);
    }

    /**
     * 获取全部bucket名称
     *
     * @return bucket名称集合
     */
    @Override
    @SneakyThrows
    public List<String> getAllBucketsNames() {
        return minioUtil.getAllBucketsNames();
    }

    /**
     * 上传文件
     *
     * @param uploadFile 文件流
     * @param bucketName bucket名称
     * @param objectName 文件名称
     */
    @Override
    @SneakyThrows
    public void uploadFile(MultipartFile uploadFile, String bucketName, String objectName) {
        minioUtil.createBucket(bucketName);
        if (objectName == null) {
            objectName = "";
        } else {
            objectName = objectName + "/";
        }
        minioUtil.uploadFile(uploadFile.getInputStream(), bucketName, objectName + uploadFile.getName());
    }

    /**
     * 获取全部bucket
     *
     * @param bucketName bucket名称
     * @return bucket名称
     */
    @Override
    @SneakyThrows
    public List<String> getAllObjects(String bucketName) {
        return minioUtil.getAllObjects(bucketName);
    }

    /**
     * 获取指定bucket下的所有文件与文件夹名称
     *
     * @param bucketName bucket名称
     * @return 文件夹名称集合
     */
    @Override
    @SneakyThrows
    public List<FileInfo> getAllFiles(String bucketName) {
        return minioUtil.getAllFiles(bucketName);
    }

    /**
     * 下载文件
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @return 输入流
     */
    @Override
    @SneakyThrows
    public InputStream downloadFile(String bucketName, String objectName) {
        return minioUtil.downloadFile(bucketName, objectName);
    }

    /**
     * 删除指定的存储桶
     *
     * @param bucketName 存储桶名称
     */
    @Override
    @SneakyThrows
    public void removeBucket(String bucketName) {
        minioUtil.removeBucket(bucketName);
    }

    /**
     * 删除指定bucket中的文件
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     */
    @Override
    @SneakyThrows
    public void removeFile(String bucketName, String objectName) {
        minioUtil.removeFile(bucketName, objectName);
    }
}
