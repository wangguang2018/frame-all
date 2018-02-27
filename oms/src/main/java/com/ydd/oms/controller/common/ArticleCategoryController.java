package com.ydd.oms.controller.common;

import com.ydd.framework.core.common.Pagination;
import com.ydd.framework.core.common.dto.ResponseDTO;
import com.ydd.oms.controller.OmsController;
import com.ydd.oms.entity.common.ArticleCategory;
import com.ydd.oms.service.common.ArticleCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Controller - 文章分类
 *
 * @author Demon
 * @since 2017-10-17
 */
@RestController
public class ArticleCategoryController extends OmsController {

	private final Logger logger = LoggerFactory.getLogger(ArticleCategoryController.class);
	
	@Resource
	private ArticleCategoryService articleCategoryService;

	/**
	 * 分页查询文章分类
	 */
	@RequestMapping(value = "/article/categorys", method = RequestMethod.GET)
	public ResponseDTO findPage(Pagination pagination) {
		return ResponseDTO.ok()
				.setPagination(articleCategoryService.findPage(pagination));
	}

	/**
	 * 查询文章分类信息
	 */
	@RequestMapping(value = "/article/category/{id}", method = RequestMethod.GET)
	public ResponseDTO findById(@PathVariable("id") Integer id) {
		return ResponseDTO.ok()
				.addAttribute("articleCategory", articleCategoryService.findById(id));
	}

	/**
	 * 保存文章分类
	 */
	@RequestMapping(value = "/article/category", method = RequestMethod.POST)
	public ResponseDTO save(ArticleCategory articleCategory) {
		articleCategoryService.save(articleCategory);
		return ResponseDTO.ok("保存成功");
	}

	/**
	 * 删除文章分类
	 */
	@RequestMapping(value = "/article/category/delete", method = RequestMethod.POST)
	public ResponseDTO delete(@RequestParam("id[]") Integer[] ids) {
		articleCategoryService.deleteByIds(ids);
		return ResponseDTO.ok("删除成功");
	}

}
