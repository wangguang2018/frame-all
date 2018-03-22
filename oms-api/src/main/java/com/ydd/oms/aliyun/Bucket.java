package com.ydd.oms.aliyun;

import com.aliyun.oss.OSSClient;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

/**
 * OSS仓库
 *
 * @author xingkong1221
 * @since 2017-04-17
 */
//@ConfigurationProperties(prefix = "oss")
//@Component
public class Bucket {

    /**
     * 协议
     */
    private String protocol;

    /**
     * 仓库名称
     */
    private String name;

    /**
     * 公网节点
     */
    private String endPoint;

    private String accessKeyId;

    /**
     * API访问值
     */
    private String accessKeySecret;


    /**
     * ossClient实例
     */
    private OSSClient ossClient;

    public Bucket() {
        super();
    }

    @PostConstruct
    public void initMethod() throws Exception {
        initOSSClient();
    }
    /**
     * 初始化OSSClient实例
     */
    private void initOSSClient() {
        // 创建oss实例
        ossClient = new OSSClient(protocol + "://" + endPoint, getAccessKeyId(), getAccessKeySecret());

        // 检查仓库是否存在
        if (!ossClient.doesBucketExist(getName())) {
            throw new RuntimeException("仓库不存在");
        }
    }

    /**
     * 上传流
     *
     * @param filename 名称
     * @param inputStream 流
     * @return 访问地址
     */
    public String putObject(String filename, InputStream inputStream) {
        // 创建上传文件的文件名
        String key = generateUniqueID(filename);
        // 文件上传
        ossClient.putObject(getName(), key, inputStream);
        // 返回公网地址
        return getDomain(key);
    }

    /**
     * 上传文件
     *
     * @param filename 文件名
     * @param file 文件
     * @param isUniqueID 是否唯一编号
     * @return 访问地址
     */
    public String putObject(String filename, File file, Boolean isUniqueID) {
        if (!isUniqueID) {
            // 创建上传文件的文件名
            filename = generateUniqueID(filename);
        }
        // 文件上传
        ossClient.putObject(getName(), filename, file);
        // 返回公网地址
        return getDomain(filename);
    }

    /**
     * 上传字符串
     *
     * @param name 文件名
     * @param content 内容
     * @return 访问地址
     */
    public String putObject(String name, String content) {
        // 创建唯一id
        String key = generateUniqueID();
        // 上传对象
        ossClient.putObject(getName(), key, new ByteArrayInputStream(content.getBytes()));
        // 返回公网地址
        return getDomain(key);
    }

    /**
     * 生成唯一id
     *
     * @param filename 文件名
     * @return 唯一id
     */
    private String generateUniqueID(String filename) {
        String key = DigestUtils.md5Hex(RandomStringUtils.random(6) + System.currentTimeMillis());

        if (StringUtils.isNoneBlank(filename)) {
            key += ("." + FilenameUtils.getExtension(filename));
        }

        return key;
    }

    /**
     * 生成唯一id
     *
     * @return 唯一id
     */
    private String generateUniqueID() {
        return generateUniqueID(null);
    }

    /**
     * 对象销毁方法
     */
    public void destory() {
        ossClient.shutdown();
    }

    /**
     * 获取仓库名称
     *
     * @return 仓库名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置仓库名称
     *
     * @param name 仓库名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取公网节点
     *
     * @return 公网节点
     */
    public String getEndPoint() {
        return endPoint;
    }

    /**
     * 设置公网节点
     *
     * @param endPoint 公网节点
     */
    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }



    /**
     * 获取协议
     *
     * @return 协议
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * 设置协议
     *
     * @param protocol 协议
     */
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    /**
     * 获取公网域名
     *
     * @param key 键
     * @return 公网域名
     */
    public String getDomain(String key) {
        StringBuilder builder = new StringBuilder();

        // 拼装访问地址
        builder.append(protocol).append("://").append(getName()).append(".").append(getEndPoint());
        if (!getEndPoint().endsWith("/")) {
            builder.append("/");
        }
        builder.append(key);

        return builder.toString();
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }
}
