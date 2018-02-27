package com.ydd.oms.mapper.common;

import com.ydd.oms.dto.ArticleDTO;
import com.ydd.oms.dto.ArticleTagsDto;
import com.ydd.oms.dto.TagDto;
import com.ydd.oms.entity.common.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Mapper - 文章
 *
 * @author zhaoziyu
 * @since 2017-10-16
 */
public interface ArticleMapper {

    /**
     * 新建文章
     *
     * @param article 文章
     */
    void insert(Article article);

    /**
     * 更新文章
     *
     * @param article 文章
     */
    void update(Article article);

    /**
     * 删除文章
     *
     * @param id 编号
     * @return 删除数量
     */
    int delete(Integer id);

    /**
     * 删除文章
     *
     * @param ids 编号数组
     * @return 删除数量
     */
    int deleteByIds(Integer[] ids);

    /**
     * 查询文章
     *
     * @param id 编号
     * @return 文章
     */
    ArticleDTO findById(Integer id);

    /**
     * 查询文章
     *
     * @return 文章集合
     */
    List<ArticleDTO> findAll();

    /**
     * 更新状态
     *
     * @param ids 编号
     * @param status 状态
     * @return 更新数量
     */
    int updateStatus(@Param("ids") Integer[] ids, @Param("status") Byte status);

    /**
     * 查询文章标签
     * @param articleId
     * @return
     */
    List<TagDto> findArticleTags(Integer articleId);

    /**
     * 插入某一文章标签
     * @param articleTagsDto
     */
    void insertArticleTags(ArticleTagsDto articleTagsDto);

    /**
     * 删除文章标签
     * @param articleId
     */
    void deleteArticleTags(Integer articleId);

}