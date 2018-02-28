package com.yanyun.oms.service.impl;
import com.github.pagehelper.PageHelper;
import com.google.gson.reflect.TypeToken;
import com.yanyun.oms.dto.QuestionDto;
import com.yanyun.oms.dto.QuestionnaireDto;
import com.ydd.framework.core.common.Pagination;
import com.ydd.framework.core.entity.enums.StatusEnum;
import com.ydd.framework.core.service.impl.BaseServiceImpl;

import com.yanyun.oms.entity.Questionnaire;
import com.yanyun.oms.mapper.QuestionnaireMapper;
import com.yanyun.oms.service.QuestionnaireService;

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

	/**
	 * 创建问卷调查
	 *
	 * @param questionnaire 问卷调查
	 */
	@Override
	@Transactional
    public void save(Questionnaire questionnaire) {
		if (questionnaire.getId() != null && questionnaire.getId() > 0) {
			// 更新
			questionnaireMapper.update(questionnaire);
		} else {
			// 新建
			questionnaireMapper.insert(questionnaire);
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
		questionnaire.setQuestionList((List<QuestionDto>) GsonUtil.fromJson(questionnaire.getQuestion(),new TypeToken<List<QuestionDto>>() {}.getType()));
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

		return questionnaireMapper.updateStatus(ids, status);
	}

}

