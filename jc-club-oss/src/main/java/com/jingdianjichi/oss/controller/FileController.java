package com.jingdianjichi.oss.controller;

import com.jingdianjichi.oss.adapter.StorageAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * 文件管理 controller
 * @author jay
 * @since 2024/12/18 下午8:09
 */
@RestController
@RefreshScope
public class FileController {

    @Resource
    private StorageAdapter storageAdapter;

    @Value("${storage.service.type}")
    private String storageServiceType;

    /**
     * 获取所有存储桶名称
     * @return 所有存储桶名称
     */
    @RequestMapping("/getAllBucketsNames")
    public List<String> getAllBucketsNames() {
        return storageAdapter.getAllBucketsNames();
    }

    @RequestMapping("/testNacos")
    public String testNacos() {
        return storageServiceType;
    }

    /**
     * 获取文件url
     * @param bucketName 存储桶名称
     * @param objName 文件名称
     * @return 文件地址
     */
    @RequestMapping("/getUrl")
    public String getUrl(String bucketName, String objName) {
        return storageAdapter.getUrl(bucketName, objName);
    }

    @RequestMapping("/upload")
    public String upload(MultipartFile multipartFile, String bucketName, String objName) {
        storageAdapter.uploadFile(multipartFile, bucketName, objName);
        return storageAdapter.getUrl(bucketName, objName);
    }
}
