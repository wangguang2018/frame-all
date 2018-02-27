package com.ydd.oms.service.common;

import com.ydd.framework.core.common.Pagination;
import com.ydd.framework.core.service.BaseService;

import java.util.List;

/**
 * Service - 评论
 *
 * @author zhaoziyu
 * @since 2017-10-12
 */
public interface CommentService extends BaseService {

    /**
     * 获取商品的评论列表
     * @param goodsId
     * @return
     */
    Pagination findGoodsComment(Pagination pagination, Integer goodsId);


    /**
     * 获取评论的图片
     * @param commentId
     * @return
     */
    List<String> findCommentImgs(Integer commentId);

    void deleteComment(Integer[] id);

    void updateCommentStatus(Integer[] id, int status);

    /**
     * 回复评论
     * @param commentId
     * @param content
     */
    void replyComment(Integer[] commentId, String content);

    /**
     * 文章评论
     * @return
     * @param pagination
     */
    Pagination findArticleComment(Pagination pagination);

    /**
     * 互动标签评论
     * @param ThreadId
     * @return
     */
    Pagination findCommentPage(Pagination pagination, Integer ThreadId);
}

