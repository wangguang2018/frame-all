package com.ydd.oms.mapper.common;

import com.ldnz.oms.dto.comment.ArticleCommentDto;
import com.ldnz.oms.dto.comment.GoodsCommentDto;
import com.ydd.oms.dto.CommentDTO;
import com.ydd.oms.entity.common.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Mapper - 评论
 *
 * @author zhaoziyu
 * @since 2017-10-12
 */
public interface CommentMapper {


    List<GoodsCommentDto> findGoodsComment(@Param("goodsId") Integer goodsId);

    List<String> findCommentImgs(@Param("commentId") Integer commentId);

    void deleteComment(@Param("id") Integer id);

    void insert(Comment comment);

    List<ArticleCommentDto> findArticleComment();

    /**
     * 互动评论
     * @param threadId
     * @return
     */
    List<CommentDTO> findThreadComment(Integer threadId);

    /**
     * 更新状态
     *
     * @param ids 编号
     * @param status 状态
     * @return 更新数量
     */
    int updateStatus(@Param("ids") Integer[] ids, @Param("status") Byte status);

    Comment findByCommentId(@Param("commentId") Integer commentId);

    void update(Comment comment);
}