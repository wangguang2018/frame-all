
package com.yanyun.oms.service;
import com.yanyun.oms.dto.QuestionnaireDto;
import com.ydd.framework.core.common.Pagination;
import com.ydd.framework.core.service.BaseService;

import com.yanyun.oms.entity.Questionnaire;


/**
 * Service - 问卷调查
 *
 * @author zhaoziyu
 * @since 2018-02-27
 */
public interface QuestionnaireService extends BaseService {

    /**
     * 保存问卷调查
     *
     * @param questionnaire 问卷调查
     */
    void save(Questionnaire questionnaire);

    /**
     * 更新问卷调查
     *
     * @param questionnaire 问卷调查
     */
    void update(Questionnaire questionnaire);

    /**
     * 删除问卷调查
     *
     * @param id 编号
     * @return 删除数量
     */
    Integer delete(Integer id);

    /**
     * 删除问卷调查
     *
     * @param ids 编号数组
     * @return 删除数量
     */
    Integer deleteByIds(Integer[] ids);

    /**
     * 查询问卷调查
     *
     * @param id 编号
     * @return 问卷调查
     */
    QuestionnaireDto findById(Integer id);

    /**
     * 查询问卷调查
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

    /**
     * 问卷调查统计
     * @param questionnaireId
     * @return
     */
    QuestionnaireDto statistics(Integer questionnaireId);
}

