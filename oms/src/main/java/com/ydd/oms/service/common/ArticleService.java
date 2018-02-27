package com.ydd.oms.service.common;

import com.ydd.framework.core.common.Pagination;
import com.ydd.framework.core.service.BaseService;
import com.ydd.oms.dto.ArticleDTO;
import com.ydd.oms.entity.common.Article;

/**
 * Service - 文章
 *
 * @author zhaoziyu
 * @since 2017-10-16
 */
public interface ArticleService extends BaseService {

    /**
     * 保存文章
     *
     * @param article 文章
     */
    void save(ArticleDTO article);

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
    Integer delete(Integer id);

    /**
     * 删除文章
     *
     * @param ids 编号数组
     * @return 删除数量
     */
    Integer deleteByIds(Integer[] ids);

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
     * @param pagination 分页信息
     * @return 分页结果
     */
    Pagination findPage(Pagination pagination);

    /**
     * 更新状态
     *
     * @param ids 编号
     * @param status 状态
     * @return 更新数量
     */
    Integer updateStatus(Integer[] ids, Byte status);
}

