package com.ydd.oms.mapper.common;

import com.ydd.oms.entity.common.ArticleCategory;

import java.util.List;

/**
 * Mapper - 文章分类
 *
 * @author Demon
 * @since 2017-10-17
 */
public interface ArticleCategoryMapper {

    /**
     * 新建文章分类
     *
     * @param articleCategory 文章分类
     */
    void insert(ArticleCategory articleCategory);

    /**
     * 更新文章分类
     *
     * @param articleCategory 文章分类
     */
    void update(ArticleCategory articleCategory);

    /**
     * 删除文章分类
     *
     * @param id 编号
     * @return 删除数量
     */
    int delete(Integer id);

    /**
     * 删除文章分类
     *
     * @param ids 编号数组
     * @return 删除数量
     */
    int deleteByIds(Integer[] ids);

    /**
     * 查询文章分类
     *
     * @param id 编号
     * @return 文章分类
     */
    ArticleCategory findById(Integer id);

    /**
     * 查询文章分类
     *
     * @return 文章分类集合
     */
    List<ArticleCategory> findAll();

}