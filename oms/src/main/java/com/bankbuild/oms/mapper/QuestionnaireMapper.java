package com.bankbuild.oms.mapper;
import com.bankbuild.oms.dto.QuestionOptionDto;
import com.bankbuild.oms.dto.QuestionnaireDto;
import com.ydd.model.entity.Question;
import com.ydd.model.entity.QuestionOption;
import com.ydd.model.entity.Questionnaire;
import org.apache.ibatis.annotations.Param;


import java.util.List;

/**
 * Mapper - 问卷调查
 *
 * @author zhaoziyu
 * @since 2018-02-27
 */
public interface QuestionnaireMapper {

    /**
     * 新建问卷调查
     *
     * @param questionnaire 问卷调查
     */
    void insert(Questionnaire questionnaire);

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
    int delete(Integer id);

    /**
     * 删除问卷调查
     *
     * @param ids 编号数组
     * @return 删除数量
     */
    int deleteByIds(Integer[] ids);

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
     * @return 问卷调查集合
     */
    List<Questionnaire> findAll();

    /**
     * 更新状态
     *
     * @param ids 编号
     * @param status 状态
     * @return 更新数量
     */
    int updateStatus(@Param("ids") Integer[] ids, @Param("status") Byte status);

    void insertQuestion(@Param("question") Question question,@Param("questionnaireId") Integer questionnaireId);

    void insertQuestionOptions(@Param("options") List<QuestionOptionDto> questionOptions, @Param("questionId") Integer questionId, @Param("questionnaireId") Integer questionnaireId);

    void insertQuestionWithId(@Param("question") Question question,@Param("questionnaireId") Integer questionnaireId);

    void insertQuestionOptionsWithId(@Param("options") List<QuestionOptionDto> questionOptions,@Param("questionId") Integer questionId,@Param("questionnaireId") Integer questionnaireId);

    void deleteQuestion(@Param("questionnaireId") Integer questionnaireId);

    void deleteQuestionOption(@Param("questionnaireId") Integer questionnaireId);
}