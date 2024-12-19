package com.jingdianjichi.oss.util;

import com.google.common.collect.Lists;
import com.jingdianjichi.oss.entity.FileInfo;
import io.minio.*;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

/**
 * minio工具类
 *
 * @author jay
 * @since 2024/12/18 下午7:38
 */
@Component
public class MinioUtil {

    @Resource
    private MinioClient minioClient;

    /**
     * 创建一个bucket
     *
     * @param bucketName bucket名称
     */
    public void createBucket(String bucketName) throws Exception {
        boolean exists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        if (!exists) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
    }

    /**
     * 获取全部bucket
     * @return bucket名称集合
     * @throws Exception 错误信息
     */
    public List<String> getAllBucketsNames() throws Exception {
        return minioClient.listBuckets().stream().map(Bucket::name).collect(Collectors.toList());
    }

    /**
     * 上传文件
     *
     * @param inputStream 文件流
     * @param bucketName  bucket名称
     * @param objectName  文件名称
     * @throws Exception 错误信息
     */
    public void uploadFile(InputStream inputStream, String bucketName, String objectName) throws Exception {
        minioClient.putObject(PutObjectArgs.builder().bucket(bucketName).object(objectName)
                .stream(inputStream, -1, Integer.MAX_VALUE).build());
    }

    /**
     * 获取全部bucket
     *
     * @param bucketName bucket名称
     * @return bucket名称
     * @throws Exception 错误信息
     */
    public List<String> getAllObjects(String bucketName) throws Exception {
        return minioClient.listBuckets().stream().map(Bucket::name).collect(Collectors.toList());
    }

    /**
     * 获取指定bucket下的所有文件与文件夹名称
     * @param bucketName bucket名称
     * @return 文件夹名称集合
     * @throws Exception 错误信息
     */
    public List<FileInfo> getAllFiles(String bucketName) throws Exception {
        Iterable<Result<Item>> results = minioClient.listObjects(ListObjectsArgs.builder()
                .bucket(bucketName).build());
        List<FileInfo> fileList = Lists.newArrayList();
        for (Result<Item> result : results) {
            Item item = result.get();
            FileInfo fileInfo = new FileInfo();
            fileInfo.setFileName(item.objectName());
            fileInfo.setDirectoryFlag(item.isDir());
            fileInfo.setEtag(item.etag());
            fileList.add(fileInfo);
        }
        return fileList;
    }

    /**
     * 下载文件
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @return 输入流
     * @throws Exception 错误信息
     */
    public InputStream downloadFile(String bucketName, String objectName) throws Exception {
        return minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(objectName).build());
    }


    /**
     * 删除指定的存储桶
     *
     * @param bucketName 存储桶名称
     * @throws Exception 如果存储桶不存在或MinIO服务器连接异常等，会抛出异常
     */
    public void removeBucket(String bucketName) throws Exception {
        // 使用MinIO Java SDK的removeBucket方法删除存储桶
        minioClient.removeBucket(RemoveBucketArgs.builder().bucket(bucketName).build());
    }

    /**
     * 删除指定bucket中的文件
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @throws Exception 如果文件不存在或MinIO服务器连接异常等，会抛出异常
     */
    public void removeFile(String bucketName, String objectName) throws Exception {
        // 使用MinIO Java SDK的removeObject方法删除指定bucket中的文件
        minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(objectName).build());
    }

}
