package com.bankbuild.api.mapper;

import com.bankbuild.api.dto.MemberQuestionnaireDTO;
import com.ydd.model.entity.MemberQuestionnaire;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Mapper - 用户问卷信息
 *
 * @author zhaoziyu
 * @since 2018-03-01
 */
public interface MemberQuestionnaireMapper {

    /**
     * 新建用户问卷信息
     *
     * @param memberQuestionnaire 用户问卷信息
     */
    void insert(MemberQuestionnaire memberQuestionnaire);

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
    int delete(Integer id);

    /**
     * 删除用户问卷信息
     *
     * @param ids 编号数组
     * @return 删除数量
     */
    int deleteByIds(Integer[] ids);

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
     * @return 用户问卷信息集合
     */
    List<MemberQuestionnaire> findAll();

    List<MemberQuestionnaireDTO> findByQuestionId(@Param("questionnaireId") Integer questionnaireId, @Param("questionId") Integer questionId);

    Integer countQuestionnaireByMember(@Param("questionnaireId") Integer questionnaireId);

    Integer countQuestionOptionByMember(@Param("questionnaireId") Integer questionnaireId, @Param("questionId") Integer questionId, @Param("optionId") Integer optionId);

}