package com.jingdianjichi.oss.adapter.impl;

import com.jingdianjichi.oss.adapter.StorageAdapter;
import com.jingdianjichi.oss.entity.FileInfo;
import com.jingdianjichi.oss.util.MinioUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jay
 * @since 2024/12/18 下午8:24
 */
@Service
@RefreshScope
public class MinioStorageAdapter implements StorageAdapter {

    @Resource
    private MinioUtil minioUtil;

    @Value("${minio.url}")
    private String minioUrl;

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
        return minioUtil.getAllBucket();
    }

    /**
     * 上传文件
     *
     * @param uploadFile 文件流
     * @param bucketName bucket名称
     * @param parentDir  文件名称
     * @param fileName
     */
    @Override
    @SneakyThrows
    public void uploadFile(MultipartFile uploadFile, String bucketName, String parentDir, String fileName) {
        minioUtil.createBucket(bucketName);
        if (parentDir == null) {
            parentDir = "";
        } else {
            parentDir = parentDir + "/";
        }
        minioUtil.uploadFile(uploadFile.getInputStream(), bucketName, parentDir + fileName);
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
        return minioUtil.getAllFile(bucketName).stream().map(FileInfo::getFileName).collect(Collectors.toList());
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
        return minioUtil.getAllFile(bucketName);
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
        return minioUtil.downLoad(bucketName, objectName);
    }

    /**
     * 删除指定的存储桶
     *
     * @param bucketName 存储桶名称
     */
    @Override
    @SneakyThrows
    public void removeBucket(String bucketName) {
        minioUtil.deleteBucket(bucketName);
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
        minioUtil.deleteObject(bucketName, objectName);
    }

    /**
     * 获取文件下载地址
     *
     * @param bucketName bucket名称
     * @param dirs
     * @return 文件下载地址
     */
    @Override
    public String getUrl(String bucketName, String... dirs) {
        StringBuilder urlBuilder =  new StringBuilder();
        urlBuilder.append(minioUrl).append("/").append(bucketName);
        Arrays.stream(dirs).forEach(dir->{
            urlBuilder.append("/").append(dir);
        });
        return urlBuilder.toString();
    }
}
