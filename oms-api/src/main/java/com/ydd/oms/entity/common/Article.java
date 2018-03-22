package com.ydd.oms.entity.common;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Entity - 文章
 *
 * @author zhaoziyu
 * @since 2017-10-16
 */
public class Article implements Serializable{


    /**
     * 
     */
    private Integer id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 分类ID
     */
    private Integer categoryId;

    /**
     * 列表图;隔开
     */
    private String cover;


    /**
     * 文章内容
     */
    private String content;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 状态 0隐藏 1发布
     */
    private Integer status;

    /**
     * 浏览量
     */
    private Integer viewCount;
    /**
     * 文章视频
     */
    private String video;

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
     * 设置
     *
     * @param id 
     */
    public void setId(Integer id){
        this.id=id;
    }

    /**
     * 获取
     *
     * @return 
     */
    public Integer getId(){
        return id;
    }


    /**
     * 设置文章标题
     *
     * @param title 文章标题
     */
    public void setTitle(String title){
        this.title=title;
    }

    /**
     * 获取文章标题
     *
     * @return 文章标题
     */
    public String getTitle(){
        return title;
    }


    /**
     * 设置分类ID
     *
     * @param categoryId 分类ID
     */
    public void setcategoryId(Integer categoryId){
        this.categoryId=categoryId;
    }

    /**
     * 获取分类ID
     *
     * @return 分类ID
     */
    public Integer getcategoryId(){
        return categoryId;
    }

    /**
     * 设置文章内容
     *
     * @param content 文章内容
     */
    public void setContent(String content){
        this.content=content;
    }

    /**
     * 获取文章内容
     *
     * @return 文章内容
     */
    public String getContent(){
        return content;
    }


    /**
     * 设置排序
     *
     * @param sort 排序
     */
    public void setSort(Integer sort){
        this.sort=sort;
    }

    /**
     * 获取排序
     *
     * @return 排序
     */
    public Integer getSort(){
        return sort;
    }

    /**
     * 设置状态 0上架 1下架
     *
     * @param status 状态 0上架 1下架
     */
    public void setStatus(Integer status){
        this.status=status;
    }

    /**
     * 获取状态 0上架 1下架
     *
     * @return 状态 0上架 1下架
     */
    public Integer getStatus(){
        return status;
    }


    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone="GMT+8")
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