package com.ydd.model.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Entity - 问卷调查
 *
 * @author zhaoziyu
 * @since 2018-02-27
 */
public class Questionnaire implements Serializable {


    /**
     * 
     */
    private Integer id;

    /**
     * 问卷调查标题
     */
    private String title;

    /**
     * 
     */
    private String question;

    /**
     * 0下线 1草稿 2上线
     *
     * @see com.ydd.framework.core.entity.enums.StatusEnum
     */
    private Integer status;

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
     * 删除标识位
     *
     * @see com.ydd.framework.core.entity.enums.DeletedEnum
     */
    private Integer deleted;



    /**
     * 设置
     *
     * @param id 
     */
    public void setId(Integer id){
        this.id = id;
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
     * 设置问卷调查标题
     *
     * @param title 问卷调查标题
     */
    public void setTitle(String title){
        this.title = title;
    }

    /**
     * 获取问卷调查标题
     *
     * @return 问卷调查标题
     */
    public String getTitle(){
        return title;
    }


    /**
     * 设置
     *
     * @param question 
     */
    public void setQuestion(String question){
        this.question = question;
    }

    /**
     * 获取
     *
     * @return 
     */
    public String getQuestion(){
        return question;
    }


    /**
     * 设置0下线 1草稿 2上线
     *
     * @see com.ydd.framework.core.entity.enums.StatusEnum
     * @param status 0下线 1草稿 2上线
     */
    public void setStatus(Integer status){
        this.status = status;
    }

    /**
     * 获取0下线 1草稿 2上线
     *
     * @see com.ydd.framework.core.entity.enums.StatusEnum
     * @return 0下线 1草稿 2上线
     */
    public Integer getStatus(){
        return status;
    }


    /**
     * 设置创建者编号
     *
     * @param createdId 创建者编号
     */
    public void setCreatedId(Integer createdId){
        this.createdId = createdId;
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
        this.modifiedId = modifiedId;
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
        this.createdTime = createdTime;
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
        this.modifiedTime = modifiedTime;
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
     * 设置删除标识位
     *
     * @see com.ydd.framework.core.entity.enums.DeletedEnum
     * @param deleted 删除标识位
     */
    public void setDeleted(Integer deleted){
        this.deleted = deleted;
    }

    /**
     * 获取删除标识位
     *
     * @see com.ydd.framework.core.entity.enums.DeletedEnum
     * @return 删除标识位
     */
    public Integer getDeleted(){
        return deleted;
    }

}