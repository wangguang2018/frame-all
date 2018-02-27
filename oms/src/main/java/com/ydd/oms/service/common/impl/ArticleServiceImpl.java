package com.ydd.oms.service.common.impl;

import com.github.pagehelper.PageHelper;
import com.ldnz.oms.mapper.ArticleTagMapper;
import com.ydd.framework.core.common.Pagination;
import com.ydd.framework.core.entity.enums.StatusEnum;
import com.ydd.framework.core.service.impl.BaseServiceImpl;
import com.ydd.oms.dto.ArticleDTO;
import com.ydd.oms.dto.ArticleTagsDto;
import com.ydd.oms.dto.TagDto;
import com.ydd.oms.entity.common.Article;
import com.ydd.oms.mapper.common.ArticleMapper;
import com.ydd.oms.service.common.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Service - 文章
 *
 * @author zhaoziyu
 * @since 2017-10-16
 */
@Service
@Transactional(readOnly = true)
public class ArticleServiceImpl extends BaseServiceImpl implements ArticleService {

	private final Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);

	@Resource
	private ArticleMapper articleMapper;

	@Resource
	private ArticleTagMapper articleTagMapper;

	/**
	 * 创建文章
	 *
	 * @param article 文章
	 */
	@Override
	@Transactional
    public void save(ArticleDTO article) {
		Article article1=new Article();
		article1.setStatus(article.getStatus());
		article1.setcategoryId(article.getcategoryId());
		article1.setTitle(article.getTitle());
		article1.setCover(article.getCover());
		article1.setContent(article.getContent());
		article1.setVideo(article.getVideo());
		article1.setViewCount(article.getViewCount());
		article1.setSort(article.getSort());
		if (article.getId() != null && article.getId() > 0) {
			// 更新
			articleMapper.deleteArticleTags(article.getId()); //删除文章之前的标签
			articleMapper.update(article);
		} else {
			// 新建
			articleMapper.insert(article);
		}
		List<Integer> tagsList =article.getTags(); //插入文章标签
		for (Integer id : tagsList){
			ArticleTagsDto articleTagsDto = new ArticleTagsDto();
			articleTagsDto.setArticleId(article.getId());
			articleTagsDto.setTagId(id);
			articleMapper.insertArticleTags(articleTagsDto);
		}

	}

	/**
	 * 更新文章
 	 *
	 * @param article 文章
	 */
	@Override
	@Transactional
    public void update(Article article) {
		articleMapper.update(article);
	}

	/**
	 * 删除文章
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
		return articleMapper.delete(id);
	}

	/**
	 * 删除文章
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
		return articleMapper.deleteByIds(ids);
	}

	/**
	 * 查询文章
	 *
	 * @param id 编号
	 * @return 文章
	 */
	public ArticleDTO findById(Integer id) {
		ArticleDTO article = articleMapper.findById(id);
		List<TagDto> tagDtoList=articleMapper.findArticleTags(id);
		List<Integer> tags = new ArrayList<>();
		for (TagDto tagDto:tagDtoList){
			tags.add(tagDto.getId());
		}
		article.setTags(tags);
		return article;
	}

	/**
	 * 查询文章
	 *
	 * @param pagination 分页信息
	 * @return 分页结果
	 */
	@Override
	public Pagination findPage(Pagination pagination) {
		PageHelper.startPage(pagination.getPage(), pagination.getPageSize());
		PageHelper.orderBy("id desc");
		List<ArticleDTO> articleDTOList=articleMapper.findAll();
		for (ArticleDTO articleDTO:articleDTOList){
			List<TagDto> tagDtoList=articleMapper.findArticleTags(articleDTO.getId());
			List<String> strings=new ArrayList<>();
			for (TagDto tagDto:tagDtoList){
				strings.add(tagDto.getName());
			}
			articleDTO.setTagNames(strings);  //设置标签
			if (articleDTO.getStatus()==1){
				articleDTO.setPublish(true);
			}else {
				articleDTO.setPublish(false);
			}
		}
		pagination.setQueryResult(articleDTOList);
		return pagination;
	}

	/**
	 * 更新状态
	 *
	 * @param ids 编号
	 * @param status 状态
	 * @return 更新数量
	 */
	@Override
	@Transactional
	public Integer updateStatus(Integer[] ids, Byte status) {
		if (ids == null) {
			return 0;
		}
		if (status == null) {
			status = StatusEnum.DISABLE.value;
		}

		return articleMapper.updateStatus(ids, status);
	}
}

