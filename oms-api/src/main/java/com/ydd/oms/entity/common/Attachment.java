package com.ydd.oms.entity.common;

import java.io.Serializable;
import java.util.Date;

/**
 * Entity - 附件管理
 *
 * @author Demon
 * @since 2017-10-16
 */
public class Attachment implements Serializable{


    /**
     * ID
     */
    private Integer id;

    /**
     * 文件类型：0其他 1图片 2视频 3音频 4压缩包 5WORD 6EXCEL 7PPT 8TXT
     */
    private Integer type;

    /**
     * 名称
     */
    private String name;

    /**
     * 文件原名称
     */
    private String filename;

    /**
     * 附件地址
     */
    private String uri;

    /**
     * 附件大小（KB）
     */
    private Integer fileSize;

    /**
     * 创建者编号
     */
    private Integer createdId;

    /**
     * 创建者编号
     */
    private Integer modifiedId;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */
    private Date modifiedTime;

    /**
     * 删除标识位: 0正常 1删除
     */
    private Integer deleted;



    /**
     * 设置ID
     *
     * @param id ID
     */
    public void setId(Integer id){
        this.id=id;
    }

    /**
     * 获取ID
     *
     * @return ID
     */
    public Integer getId(){
        return id;
    }


    /**
     * 设置文件类型：0其他 1图片 2视频 3音频 4压缩包 5WORD 6EXCEL 7PPT 8TXT
     *
     * @param type 文件类型：0其他 1图片 2视频 3音频 4压缩包 5WORD 6EXCEL 7PPT 8TXT
     */
    public void setType(Integer type){
        this.type=type;
    }

    /**
     * 获取文件类型：0其他 1图片 2视频 3音频 4压缩包 5WORD 6EXCEL 7PPT 8TXT
     *
     * @return 文件类型：0其他 1图片 2视频 3音频 4压缩包 5WORD 6EXCEL 7PPT 8TXT
     */
    public Integer getType(){
        return type;
    }


    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name){
        this.name=name;
    }

    /**
     * 获取名称
     *
     * @return 名称
     */
    public String getName(){
        return name;
    }


    /**
     * 设置文件原名称
     *
     * @param filename 文件原名称
     */
    public void setFilename(String filename){
        this.filename=filename;
    }

    /**
     * 获取文件原名称
     *
     * @return 文件原名称
     */
    public String getFilename(){
        return filename;
    }


    /**
     * 设置附件地址
     *
     * @param uri 附件地址
     */
    public void setUri(String uri){
        this.uri=uri;
    }

    /**
     * 获取附件地址
     *
     * @return 附件地址
     */
    public String getUri(){
        return uri;
    }


    /**
     * 设置附件大小（KB）
     *
     * @param fileSize 附件大小（KB）
     */
    public void setFileSize(Integer fileSize){
        this.fileSize=fileSize;
    }

    /**
     * 获取附件大小（KB）
     *
     * @return 附件大小（KB）
     */
    public Integer getFileSize(){
        return fileSize;
    }


    /**
     * 设置创建者编号
     *
     * @param createdId 创建者编号
     */
    public void setCreatedId(Integer createdId){
        this.createdId=createdId;
    }

    /**
     * 获取创建者编号
     *
     * @return 创建者编号
     */
    public Integer getCreatedId(){
        return createdId;
    }


    /**
     * 设置创建者编号
     *
     * @param modifiedId 创建者编号
     */
    public void setModifiedId(Integer modifiedId){
        this.modifiedId=modifiedId;
    }

    /**
     * 获取创建者编号
     *
     * @return 创建者编号
     */
    public Integer getModifiedId(){
        return modifiedId;
    }


    /**
     * 设置创建时间
     *
     * @param createdTime 创建时间
     */
    public void setCreatedTime(Date createdTime){
        this.createdTime=createdTime;
    }

    /**
     * 获取创建时间
     *
     * @return 创建时间
     */
    public Date getCreatedTime(){
        return createdTime;
    }


    /**
     * 设置更新时间
     *
     * @param modifiedTime 更新时间
     */
    public void setModifiedTime(Date modifiedTime){
        this.modifiedTime=modifiedTime;
    }

    /**
     * 获取更新时间
     *
     * @return 更新时间
     */
    public Date getModifiedTime(){
        return modifiedTime;
    }


    /**
     * 设置删除标识位: 0正常 1删除
     *
     * @param deleted 删除标识位: 0正常 1删除
     */
    public void setDeleted(Integer deleted){
        this.deleted=deleted;
    }

    /**
     * 获取删除标识位: 0正常 1删除
     *
     * @return 删除标识位: 0正常 1删除
     */
    public Integer getDeleted(){
        return deleted;
    }

}