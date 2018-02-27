package com.ydd.oms.controller.common;

import com.ydd.framework.core.common.Pagination;
import com.ydd.framework.core.common.dto.ResponseDTO;
import com.ydd.framework.core.entity.enums.StatusEnum;
import com.ydd.oms.controller.OmsController;
import com.ydd.oms.dto.ArticleDTO;
import com.ydd.oms.service.common.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Controller - 文章
 *
 * @author zhaoziyu
 * @since 2017-10-16 14:45
 */
@RestController
public class ArticleController extends OmsController {

	private final Logger logger = LoggerFactory.getLogger(ArticleController.class);
	
	@Resource
	private ArticleService articleService;

	/**
	 * 分页查询文章
	 */
	@RequestMapping(value = "/articles", method = RequestMethod.GET)
	public ResponseDTO findPage(Pagination pagination) {
		return ResponseDTO.ok()
				.setPagination(articleService.findPage(pagination));
	}

	/**
	 * 查询文章信息
	 */
	@RequestMapping(value = "/article/{id}", method = RequestMethod.GET)
	public ResponseDTO findById(@PathVariable("id") Integer id) {
		return ResponseDTO.ok()
				.addAttribute("article", articleService.findById(id));
	}

	/**
	 * 保存文章
	 */
	@RequestMapping(value = "/article", method = RequestMethod.POST)
	public ResponseDTO save(ArticleDTO article) {
		articleService.save(article);
		return ResponseDTO.ok("保存成功");
	}

	/**
	 * 删除文章
	 */
	@RequestMapping(value = "/article/delete", method = RequestMethod.POST)
	public ResponseDTO delete(@RequestParam("id[]") Integer[] ids) {
		articleService.deleteByIds(ids);
		return ResponseDTO.ok("删除成功");
	}
	/**
	 * 变更文章状态
	 */
	@RequestMapping(value = "/article/status", method = RequestMethod.POST)
	public ResponseDTO updateStatus(@RequestParam("id[]") Integer[] ids, Boolean isPublish) {
		Byte status;
		if (isPublish==true){
			status=1;
		}else {
			status=0;
		}
		articleService.updateStatus(ids, status);
		String operationName = (StatusEnum.ENABLE.value.equals(status) ? "发布" : "隐藏") + "成功";
		return ResponseDTO.ok(operationName);
	}

}
