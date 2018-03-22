package com.ydd.model.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Entity - 用户问卷信息
 *
 * @author zhaoziyu
 * @since 2018-03-01
 */
public class MemberQuestionnaire implements Serializable {


    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private Integer memberId;

    /**
     * 
     */
    private Integer questionnaireId;

    /**
     * 问题ID
     */
    private Integer questionId;

    /**
     * 用户答案
     */
    private String answer;

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
     * 设置
     *
     * @param memberId 
     */
    public void setMemberId(Integer memberId){
        this.memberId = memberId;
    }

    /**
     * 获取
     *
     * @return 
     */
    public Integer getMemberId(){
        return memberId;
    }


    /**
     * 设置
     *
     * @param questionnaireId 
     */
    public void setQuestionnaireId(Integer questionnaireId){
        this.questionnaireId = questionnaireId;
    }

    /**
     * 获取
     *
     * @return 
     */
    public Integer getQuestionnaireId(){
        return questionnaireId;
    }


    /**
     * 设置问题ID
     *
     * @param questionId 问题ID
     */
    public void setQuestionId(Integer questionId){
        this.questionId = questionId;
    }

    /**
     * 获取问题ID
     *
     * @return 问题ID
     */
    public Integer getQuestionId(){
        return questionId;
    }


    /**
     * 设置用户答案
     *
     * @param answer 用户答案
     */
    public void setAnswer(String answer){
        this.answer = answer;
    }

    /**
     * 获取用户答案
     *
     * @return 用户答案
     */
    public String getAnswer(){
        return answer;
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