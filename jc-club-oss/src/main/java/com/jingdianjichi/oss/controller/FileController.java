package com.jingdianjichi.oss.controller;

import com.jingdianjichi.oss.adapter.StorageAdapter;
import com.jingdianjichi.oss.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * 文件管理 controller
 *
 * @author jay
 * @since 2024/12/18 下午8:09
 */
@Slf4j
@RestController
public class FileController {

    @Resource
    private StorageAdapter storageAdapter;

    /**
     * 获取所有存储桶名称
     *
     * @return 所有存储桶名称
     */
    @RequestMapping("/getAllBucketsNames")
    public List<String> getAllBucketsNames() {
        return storageAdapter.getAllBucketsNames();
    }

    /**
     * 获取文件url
     *
     * @param bucketName 存储桶名称
     * @param objName    文件名称
     * @return 文件地址
     */
    @RequestMapping("/getUrl")
    public String getUrl(String bucketName, String objName) {
        return storageAdapter.getUrl(bucketName, objName);
    }

    /**
     * 上传文件
     *
     * @param uploadFile 文件
     * @param bucketName     存储桶名称
     * @param dir 文件名称
     * @return 文件地址
     */
    @PostMapping("/upload")
    public Result<String> upload(@RequestParam("uploadFile") MultipartFile uploadFile,
                                 @RequestParam("bucket") String bucketName,
                                 @RequestParam("objectName") String dir) {
        log.info("uploadFile:{},bucket:{},objectName:{}", uploadFile, bucketName, dir);
        String fileName = UUID.randomUUID().toString();
        storageAdapter.uploadFile(uploadFile, bucketName, dir, fileName);
        return Result.success(storageAdapter.getUrl(bucketName, dir, fileName));
    }
}
