package com.ydd.oms.entity.common;

import java.io.Serializable;
import java.util.Date;

/**
 * Entity - 评论
 *
 * @author zhaoziyu
 * @since 2017-11-14
 */
public class Comment implements Serializable {


    /**
     * 编号
     */
    private Integer id;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 文章编号
     */
    private Integer articleId;

    /**
     * 互动问答编号
     */
    private Integer threadId;

    /**
     * 商品编号
     */
    private Integer goodsId;

    /**
     * 订单编号
     */
    private Integer orderId;

    /**
     * 评价编号
     */
    private Integer commentId;

    /**
     * 会员编号
     */
    private Integer memberId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 星级
     */
    private Integer stars;

    /**
     * 点赞人数
     */
    private Integer likeCount;

    /**
     * 匿名
     */
    private Integer anonymous;

    /**
     * 创建者编号
     */
    private Integer createdId;

    /**
     * 更新者编号
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
     * 状态：0隐藏 1显示
     */
    private Integer status;



    /**
     * 设置编号
     *
     * @param id 编号
     */
    public void setId(Integer id){
        this.id = id;
    }

    /**
     * 获取编号
     *
     * @return 编号
     */
    public Integer getId(){
        return id;
    }


    /**
     * 设置类型
     *
     * @param type 类型
     */
    public void setType(Integer type){
        this.type = type;
    }

    /**
     * 获取类型
     *
     * @return 类型
     */
    public Integer getType(){
        return type;
    }


    /**
     * 设置文章编号
     *
     * @param articleId 文章编号
     */
    public void setArticleId(Integer articleId){
        this.articleId = articleId;
    }

    /**
     * 获取文章编号
     *
     * @return 文章编号
     */
    public Integer getArticleId(){
        return articleId;
    }


    /**
     * 设置互动问答编号
     *
     * @param threadId 互动问答编号
     */
    public void setThreadId(Integer threadId){
        this.threadId = threadId;
    }

    /**
     * 获取互动问答编号
     *
     * @return 互动问答编号
     */
    public Integer getThreadId(){
        return threadId;
    }


    /**
     * 设置商品编号
     *
     * @param goodsId 商品编号
     */
    public void setGoodsId(Integer goodsId){
        this.goodsId = goodsId;
    }

    /**
     * 获取商品编号
     *
     * @return 商品编号
     */
    public Integer getGoodsId(){
        return goodsId;
    }


    /**
     * 设置订单编号
     *
     * @param orderId 订单编号
     */
    public void setOrderId(Integer orderId){
        this.orderId = orderId;
    }

    /**
     * 获取订单编号
     *
     * @return 订单编号
     */
    public Integer getOrderId(){
        return orderId;
    }


    /**
     * 设置评价编号
     *
     * @param commentId 评价编号
     */
    public void setCommentId(Integer commentId){
        this.commentId = commentId;
    }

    /**
     * 获取评价编号
     *
     * @return 评价编号
     */
    public Integer getCommentId(){
        return commentId;
    }


    /**
     * 设置会员编号
     *
     * @param memberId 会员编号
     */
    public void setMemberId(Integer memberId){
        this.memberId = memberId;
    }

    /**
     * 获取会员编号
     *
     * @return 会员编号
     */
    public Integer getMemberId(){
        return memberId;
    }


    /**
     * 设置评论内容
     *
     * @param content 评论内容
     */
    public void setContent(String content){
        this.content = content;
    }

    /**
     * 获取评论内容
     *
     * @return 评论内容
     */
    public String getContent(){
        return content;
    }


    /**
     * 设置星级
     *
     * @param stars 星级
     */
    public void setStars(Integer stars){
        this.stars = stars;
    }

    /**
     * 获取星级
     *
     * @return 星级
     */
    public Integer getStars(){
        return stars;
    }


    /**
     * 设置点赞人数
     *
     * @param likeCount 点赞人数
     */
    public void setLikeCount(Integer likeCount){
        this.likeCount = likeCount;
    }

    /**
     * 获取点赞人数
     *
     * @return 点赞人数
     */
    public Integer getLikeCount(){
        return likeCount;
    }


    /**
     * 设置匿名
     *
     * @param anonymous 匿名
     */
    public void setAnonymous(Integer anonymous){
        this.anonymous = anonymous;
    }

    /**
     * 获取匿名
     *
     * @return 匿名
     */
    public Integer getAnonymous(){
        return anonymous;
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
     * 设置更新者编号
     *
     * @param modifiedId 更新者编号
     */
    public void setModifiedId(Integer modifiedId){
        this.modifiedId = modifiedId;
    }

    /**
     * 获取更新者编号
     *
     * @return 更新者编号
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}