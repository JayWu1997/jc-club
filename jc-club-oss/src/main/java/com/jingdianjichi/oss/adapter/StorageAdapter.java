package com.jingdianjichi.oss.adapter;

import com.jingdianjichi.oss.entity.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * 文件存储服务
 *
 * @author jay
 * @since 2024/12/18 下午8:19
 */
public interface StorageAdapter {
    /**
     * 创建一个bucket
     *
     * @param bucketName bucket名称
     */
    public void createBucket(String bucketName);

    /**
     * 获取全部bucket名称
     * @return bucket名称集合
     */
    public List<String> getAllBucketsNames();

    /**
     * 上传文件
     *
     * @param uploadFile 文件流
     * @param bucketName bucket名称
     * @param parentDir  文件名称
     * @param fileName
     */
    public void uploadFile(MultipartFile uploadFile, String bucketName, String parentDir, String fileName);

    /**
     * 获取全部bucket
     *
     * @param bucketName bucket名称
     * @return bucket名称
     */
    public List<String> getAllObjects(String bucketName);

    /**
     * 获取指定bucket下的所有文件与文件夹名称
     *
     * @param bucketName bucket名称
     * @return 文件夹名称集合
     */
    public List<FileInfo> getAllFiles(String bucketName);

    /**
     * 下载文件
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @return 输入流
     */
    public InputStream downloadFile(String bucketName, String objectName);


    /**
     * 删除指定的存储桶
     *
     * @param bucketName 存储桶名称
     */
    public void removeBucket(String bucketName);

    /**
     * 删除指定bucket中的文件
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     */
    public void removeFile(String bucketName, String objectName);

    /**
     * 获取文件下载地址
     *
     * @param bucketName bucket名称
     * @param dirs
     * @return 文件下载地址
     */
    String getUrl(String bucketName, String... dirs);
}
