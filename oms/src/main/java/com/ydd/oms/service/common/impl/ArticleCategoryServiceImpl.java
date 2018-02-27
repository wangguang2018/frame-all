package com.ydd.oms.service.common.impl;

import com.github.pagehelper.PageHelper;
import com.ydd.framework.core.common.Pagination;
import com.ydd.framework.core.service.impl.BaseServiceImpl;
import com.ydd.oms.entity.common.ArticleCategory;
import com.ydd.oms.mapper.common.ArticleCategoryMapper;
import com.ydd.oms.service.common.ArticleCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Service - 文章分类
 *
 * @author Demon
 * @since 2017-10-17
 */
@Service
@Transactional(readOnly = true)
public class ArticleCategoryServiceImpl extends BaseServiceImpl implements ArticleCategoryService {

	private final Logger logger = LoggerFactory.getLogger(ArticleCategoryServiceImpl.class);

	@Resource
	private ArticleCategoryMapper articleCategoryMapper;

	/**
	 * 创建文章分类
	 *
	 * @param articleCategory 文章分类
	 */
	@Override
	@Transactional
    public void save(ArticleCategory articleCategory) {
		if (articleCategory.getId() != null && articleCategory.getId() > 0) {
			// 更新
			articleCategoryMapper.update(articleCategory);
		} else {
			// 新建
			articleCategoryMapper.insert(articleCategory);
		}
	}

	/**
	 * 更新文章分类
 	 *
	 * @param articleCategory 文章分类
	 */
	@Override
	@Transactional
    public void update(ArticleCategory articleCategory) {
		articleCategoryMapper.update(articleCategory);
	}

	/**
	 * 删除文章分类
	 *
	 * @param id 编号
   	 * @return 删除数量
	 */
	@Override
	@Transactional
    public Integer delete(Integer id) {
		if (id == null || id <= 0) {
			return 0;
		}
		return articleCategoryMapper.delete(id);
	}

	/**
	 * 删除文章分类
	 *
	 * @param ids 编号数组
	 * @return 删除数量
	 */
	@Override
	@Transactional
    public Integer deleteByIds(Integer[] ids) {
		if (ids == null || ids.length == 0) {
			return 0;
		}
		return articleCategoryMapper.deleteByIds(ids);
	}

	/**
	 * 查询文章分类
	 *
	 * @param id 编号
	 * @return 文章分类
	 */
	public ArticleCategory findById(Integer id) {
		return articleCategoryMapper.findById(id);
	}

	/**
	 * 查询文章分类
	 *
	 * @param pagination 分页信息
	 * @return 分页结果
	 */
	@Override
	public Pagination findPage(Pagination pagination) {
		PageHelper.startPage(pagination.getPage(), pagination.getPageSize());
		PageHelper.orderBy("id desc");
		pagination.setQueryResult(articleCategoryMapper.findAll());
		return pagination;
	}

}

