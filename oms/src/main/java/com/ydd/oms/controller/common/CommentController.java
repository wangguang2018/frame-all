package com.ydd.oms.controller.common;

import com.ydd.framework.core.common.Pagination;
import com.ydd.framework.core.common.dto.ResponseDTO;
import com.ydd.oms.controller.OmsController;
import com.ydd.oms.service.common.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Controller - 评论
 *
 * @author zhaoziyu
 * @since 2017-10-12 16:40
 */
@RestController
public class CommentController extends OmsController {

	private final Logger logger = LoggerFactory.getLogger(CommentController.class);
	
	@Resource
	private CommentService commentService;

	/**
	 * 商品评论分页
	 */
	@RequestMapping(value = "/goods/comments", method = RequestMethod.GET)
	public ResponseDTO findPage(Pagination pagination,@RequestParam(value = "id",required = false) Integer goodsId) {
		return ResponseDTO.ok()
				.setPagination(commentService.findGoodsComment(pagination,goodsId));
	}

	/**
	 * 屏蔽回复
	 * @param comemntIds
	 * @return
	 */
	@RequestMapping(value = "/delete/comments", method = RequestMethod.POST)
	public ResponseDTO deleteComment(@RequestParam("id") Integer[] comemntIds){
		commentService.deleteComment(comemntIds);
		return ResponseDTO.ok();
	}

	@RequestMapping(value = "/update/comments/status", method = RequestMethod.POST)
	public ResponseDTO updateStatus(@RequestParam("id") Integer[] comemntIds,@RequestParam("status") Integer status){
		commentService.updateCommentStatus(comemntIds,status);
		return ResponseDTO.ok();
	}

	@RequestMapping(value = "/reply/comments", method = RequestMethod.POST)
	public ResponseDTO reply(@RequestParam("id") Integer[] commentId,@RequestParam("content") String content){
		commentService.replyComment(commentId,content);
		return ResponseDTO.ok("回复成功");
	}

	/**
	 * 文章评论分页
	 */
	@RequestMapping(value = "/article/comments", method = RequestMethod.GET)
	public ResponseDTO findArticlePage(Pagination pagination) {
		return ResponseDTO.ok()
				.setPagination(commentService.findArticleComment(pagination));
	}

	/**
	 * 互动评论分页
	 * @param threadId
	 * @param pagination
	 * @return
	 */
	@RequestMapping(value = "/thread/comments",method =RequestMethod.GET)
	public ResponseDTO findThreads(@RequestParam("id") Integer threadId,Pagination pagination){
		return ResponseDTO.ok().setPagination(commentService.findCommentPage(pagination,threadId));
	}
}
