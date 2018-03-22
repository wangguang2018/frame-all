package com.bankbuild.oms.service.impl;

import com.github.pagehelper.PageHelper;
import com.google.gson.reflect.TypeToken;
import com.bankbuild.oms.dto.MemberQuestionnaireDTO;
import com.bankbuild.oms.dto.QuestionDto;
import com.bankbuild.oms.dto.QuestionOptionDto;
import com.bankbuild.oms.dto.QuestionnaireDto;
import com.ydd.model.entity.Question;
import com.ydd.model.entity.QuestionOption;
import com.ydd.model.enums.QuestionTypeEnum;
import com.bankbuild.oms.service.MemberQuestionnaireService;
import com.ydd.framework.core.common.Pagination;
import com.ydd.framework.core.entity.enums.StatusEnum;
import com.ydd.framework.core.service.impl.BaseServiceImpl;

import com.ydd.model.entity.Questionnaire;
import com.bankbuild.oms.mapper.QuestionnaireMapper;
import com.bankbuild.oms.service.QuestionnaireService;

import com.ydd.oms.util.GsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Service - 问卷调查
 *
 * @author zhaoziyu
 * @since 2018-02-27
 */
@Service
@Transactional(readOnly = true)
public class QuestionnaireServiceImpl extends BaseServiceImpl implements QuestionnaireService {

    private final Logger logger = LoggerFactory.getLogger(QuestionnaireServiceImpl.class);

    @Resource
    private QuestionnaireMapper questionnaireMapper;

    @Resource
    private MemberQuestionnaireService memberQuestionnaireService;


    /**
     * 创建问卷调查
     *
     * @param questionnaire 问卷调查
     */
    @Override
    @Transactional
    public void save(Questionnaire questionnaire) {
        String questionJson = questionnaire.getQuestion();
        List<QuestionDto> questions = (List<QuestionDto>) GsonUtil.fromJson(questionJson, new TypeToken<List<QuestionDto>>() {
        }.getType());
        if (questionnaire.getId() != null && questionnaire.getId() > 0) {
            // 更新
            questionnaireMapper.update(questionnaire);
            questionnaireMapper.deleteQuestion(questionnaire.getId());
            questionnaireMapper.deleteQuestionOption(questionnaire.getId());
            for (QuestionDto questionDto : questions) {
                Question question = new Question();
                question.setType((byte) questionDto.getType().intValue());
                question.setQuestion(questionDto.getQuestion());
                question.setId(questionDto.getId());
                if (questionDto.getId() != null) {
                    questionnaireMapper.insertQuestionWithId(question, questionnaire.getId());
                    if (question.getType().intValue() != QuestionTypeEnum.TEXT.value.intValue())
                        questionnaireMapper.insertQuestionOptionsWithId(questionDto.getOptions(), question.getId(), questionnaire.getId());
                } else {
                    questionnaireMapper.insertQuestion(question, questionnaire.getId());
                    if (question.getType().intValue() != QuestionTypeEnum.TEXT.value.intValue())
                        questionnaireMapper.insertQuestionOptions(questionDto.getOptions(), question.getId(), questionnaire.getId());
                }
            }
        } else {
            // 新建
            questionnaireMapper.insert(questionnaire);
            for (QuestionDto questionDto : questions) {
                Question question = new Question();
                question.setType((byte) questionDto.getType().intValue());
                question.setQuestion(questionDto.getQuestion());
                questionnaireMapper.insertQuestion(question, questionnaire.getId());
                //不是文本问题 需要保存问题的选项
                if (question.getType().intValue() != QuestionTypeEnum.TEXT.value.intValue()) {
                    List<QuestionOptionDto> questionOptions = questionDto.getOptions();
                    questionnaireMapper.insertQuestionOptions(questionOptions, question.getId(), questionnaire.getId());
                }
            }
        }
    }

    /**
     * 更新问卷调查
     *
     * @param questionnaire 问卷调查
     */
    @Override
    @Transactional
    public void update(Questionnaire questionnaire) {
        questionnaireMapper.update(questionnaire);
    }

    /**
     * 删除问卷调查
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
        return questionnaireMapper.delete(id);
    }

    /**
     * 删除问卷调查
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
        return questionnaireMapper.deleteByIds(ids);
    }

    /**
     * 查询问卷调查
     *
     * @param id 编号
     * @return 问卷调查
     */
    public QuestionnaireDto findById(Integer id) {
        QuestionnaireDto questionnaire = questionnaireMapper.findById(id);
        return questionnaire;
    }

    /**
     * 查询问卷调查
     *
     * @param pagination 分页信息
     * @return 分页结果
     */
    @Override
    public Pagination findPage(Pagination pagination) {
        PageHelper.startPage(pagination.getPage(), pagination.getPageSize());
        PageHelper.orderBy("id desc");
        pagination.setQueryResult(questionnaireMapper.findAll());
        return pagination;
    }

    /**
     * 更新状态
     *
     * @param ids    编号
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

        return questionnaireMapper.updateStatus(ids, status);
    }

    /**
     * 问卷调查统计
     * @param questionnaireId
     * @return
     */
    @Override
    public QuestionnaireDto statistics(Integer questionnaireId) {
        QuestionnaireDto questionnaire = questionnaireMapper.findById(questionnaireId);
        if (questionnaire == null)
            return null;

        for (QuestionDto questionDto : questionnaire.getQuestionList()) {
            if (questionDto.getType().intValue() == QuestionTypeEnum.RADIO.value.intValue()
                    || questionDto.getType().intValue() == QuestionTypeEnum.CHECKBOX.value.intValue()) {
                for(QuestionOptionDto option : questionDto.getOptions()){
                    //获取该选项有多少用户选择
                    Integer count = memberQuestionnaireService.countQuestionOptionByMember(questionnaireId,questionDto.getId(),option.getId());
                    option.setCount(count);
                }
            }
//            else if(questionDto.getType().intValue() == QuestionTypeEnum.TEXT.value.intValue()){
//
//            }
        }
        return questionnaire;
    }
}

