package com.ydd.oms.service.common;

import com.ydd.framework.core.common.Pagination;
import com.ydd.framework.core.service.BaseService;
import com.ydd.oms.entity.common.ArticleCategory;

/**
 * Service - 文章分类
 *
 * @author Demon
 * @since 2017-10-17
 */
public interface ArticleCategoryService extends BaseService {

    /**
     * 保存文章分类
     *
     * @param articleCategory 文章分类
     */
    void save(ArticleCategory articleCategory);

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
    Integer delete(Integer id);

    /**
     * 删除文章分类
     *
     * @param ids 编号数组
     * @return 删除数量
     */
    Integer deleteByIds(Integer[] ids);

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
     * @param pagination 分页信息
     * @return 分页结果
     */
    Pagination findPage(Pagination pagination);

}

