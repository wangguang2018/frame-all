package com.ydd.oms.service.common.impl;

import com.github.pagehelper.PageHelper;
import com.ldnz.oms.dto.comment.GoodsCommentDto;
import com.ydd.framework.core.common.Pagination;
import com.ydd.framework.core.service.impl.BaseServiceImpl;
import com.ydd.oms.config.mybatis.ParamsUtils;
import com.ydd.oms.entity.common.Comment;
import com.ydd.oms.entity.enums.CommentStatusEnum;
import com.ydd.oms.entity.enums.CommentTypeEnum;
import com.ydd.oms.mapper.common.CommentMapper;
import com.ydd.oms.service.common.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Service - 评论
 *
 * @author zhaoziyu
 * @since 2017-10-12
 */
@Service
@Transactional(readOnly = true)
public class CommentServiceImpl extends BaseServiceImpl implements CommentService {

	private final Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);

	@Resource
	private CommentMapper commentMapper;



	/**
	 * 商品评论
	 * @param goodsId
	 * @return
	 */
	@Override
	public Pagination findGoodsComment(Pagination pagination, Integer goodsId) {
		PageHelper.startPage(pagination.getPage(), pagination.getPageSize());
		PageHelper.orderBy("c.created_time desc");
		List<GoodsCommentDto> comments = commentMapper.findGoodsComment(goodsId);
		ParamsUtils.clearParams();
		for(GoodsCommentDto c : comments){
			c.setImgs(findCommentImgs(c.getId()));
		}
		pagination.setQueryResult(comments);
		return pagination;
	}

	/**
	 * 评论图片
	 * @param commentId
	 * @return
	 */
	@Override
	public List<String> findCommentImgs(Integer commentId) {
		return commentMapper.findCommentImgs(commentId);
	}

	@Override
	@Transactional
	public void deleteComment(Integer[] id) {
		commentMapper.updateStatus(id, CommentStatusEnum.HIDE.value);
	}

	@Override
	@Transactional
	public void updateCommentStatus(Integer[] id, int status) {
		commentMapper.updateStatus(id,(byte)status);
	}

	/**
	 * 回复评论
	 * @param commentIds
	 * @param content
	 */
	@Override
	@Transactional
	public void replyComment(Integer[] commentIds, String content) {
		for(int id : commentIds){
			Comment reply = commentMapper.findByCommentId(id);
			Comment comment = new Comment();
			comment.setStatus(CommentStatusEnum.SHOW.value.intValue());
			comment.setContent(content);
			if(reply != null){
				comment.setId(reply.getId());
				commentMapper.update(comment);
			}else{
				comment.setCommentId(id);
				comment.setContent(content);
				comment.setMemberId(0);
				comment.setType(CommentTypeEnum.GOODS_REPLY.value.intValue());
				commentMapper.insert(comment);
			}
		}
	}

	@Override
	public Pagination findArticleComment(Pagination pagination) {
		PageHelper.startPage(pagination.getPage(), pagination.getPageSize());
		pagination.setQueryResult(commentMapper.findArticleComment());
		return pagination;
	}

	/**
	 * 查询互动评论
	 *
	 * @param pagination 分页信息
	 * @return 分页结果
	 */
	@Override
	public Pagination findCommentPage(Pagination pagination,Integer threadId) {
		PageHelper.startPage(pagination.getPage(), pagination.getPageSize());
		PageHelper.orderBy("id desc");
		pagination.setQueryResult(commentMapper.findThreadComment(threadId));
		return pagination;
	}
}

