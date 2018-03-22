package com.bankbuild.oms.service;

import com.bankbuild.oms.dto.MemberQuestionnaireDTO;
import com.ydd.framework.core.common.Pagination;
import com.ydd.framework.core.service.BaseService;
import com.ydd.model.entity.MemberQuestionnaire;

import java.util.List;

/**
 * Service - 用户问卷信息
 *
 * @author zhaoziyu
 * @since 2018-03-01
 */
public interface MemberQuestionnaireService extends BaseService {

    /**
     * 保存用户问卷信息
     *
     * @param memberQuestionnaire 用户问卷信息
     */
    void save(MemberQuestionnaire memberQuestionnaire);

    /**
     * 更新用户问卷信息
     *
     * @param memberQuestionnaire 用户问卷信息
     */
    void update(MemberQuestionnaire memberQuestionnaire);

    /**
     * 删除用户问卷信息
     *
     * @param id 编号
     * @return 删除数量
     */
    Integer delete(Integer id);

    /**
     * 删除用户问卷信息
     *
     * @param ids 编号数组
     * @return 删除数量
     */
    Integer deleteByIds(Integer[] ids);

    /**
     * 查询用户问卷信息
     *
     * @param id 编号
     * @return 用户问卷信息
     */
    MemberQuestionnaire findById(Integer id);

    /**
     * 查询用户问卷信息
     *
     * @param pagination 分页信息
     * @return 分页结果
     */
    Pagination findPage(Pagination pagination);

    List<MemberQuestionnaireDTO> findByQuestionId(Integer questionnaireId, Integer questionId);

    /**
     * 查询该问卷有多少人回答过
     * @param questionnaireId
     * @return
     */
    Integer countQuestionnaireByMember(Integer questionnaireId);

    /**
     * 查询问卷下某个题目的某个选项的用户选择数量
     * @param questionnaireId
     * @param questionId
     * @param optionId
     * @return
     */
    Integer countQuestionOptionByMember(Integer questionnaireId,Integer questionId,Integer optionId);

}

