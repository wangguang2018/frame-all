package com.yanyun.oms.service.impl;

import com.github.pagehelper.PageHelper;
import com.yanyun.oms.dto.MemberQuestionnaireDTO;
import com.ydd.framework.core.common.Pagination;
import com.ydd.framework.core.service.impl.BaseServiceImpl;
import com.yanyun.oms.entity.MemberQuestionnaire;
import com.yanyun.oms.mapper.MemberQuestionnaireMapper;
import com.yanyun.oms.service.MemberQuestionnaireService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Service - 用户问卷信息
 *
 * @author zhaoziyu
 * @since 2018-03-01
 */
@Service
@Transactional(readOnly = true)
public class MemberQuestionnaireServiceImpl extends BaseServiceImpl implements MemberQuestionnaireService {

	private final Logger logger = LoggerFactory.getLogger(MemberQuestionnaireServiceImpl.class);

	@Resource
	private MemberQuestionnaireMapper memberQuestionnaireMapper;

	/**
	 * 创建用户问卷信息
	 *
	 * @param memberQuestionnaire 用户问卷信息
	 */
	@Override
	@Transactional
    public void save(MemberQuestionnaire memberQuestionnaire) {
		if (memberQuestionnaire.getId() != null && memberQuestionnaire.getId() > 0) {
			// 更新
			memberQuestionnaireMapper.update(memberQuestionnaire);
		} else {
			// 新建
			memberQuestionnaireMapper.insert(memberQuestionnaire);
		}
	}

	/**
	 * 更新用户问卷信息
 	 *
	 * @param memberQuestionnaire 用户问卷信息
	 */
	@Override
	@Transactional
    public void update(MemberQuestionnaire memberQuestionnaire) {
		memberQuestionnaireMapper.update(memberQuestionnaire);
	}

	/**
	 * 删除用户问卷信息
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
		return memberQuestionnaireMapper.delete(id);
	}

	/**
	 * 删除用户问卷信息
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
		return memberQuestionnaireMapper.deleteByIds(ids);
	}

	/**
	 * 查询用户问卷信息
	 *
	 * @param id 编号
	 * @return 用户问卷信息
	 */
	public MemberQuestionnaire findById(Integer id) {
		return memberQuestionnaireMapper.findById(id);
	}

	/**
	 * 查询用户问卷信息
	 *
	 * @param pagination 分页信息
	 * @return 分页结果
	 */
	@Override
	public Pagination findPage(Pagination pagination) {
		PageHelper.startPage(pagination.getPage(), pagination.getPageSize());
		PageHelper.orderBy("id desc");
		pagination.setQueryResult(memberQuestionnaireMapper.findAll());
		return pagination;
	}

	/**
	 * 查询某个问卷 某个题目 用户回答的内容
	 * @param questionnaireId
	 * @param questionId
	 * @return
	 */
	@Override
	public List<MemberQuestionnaireDTO> findByQuestionId(Integer questionnaireId, Integer questionId) {
		return memberQuestionnaireMapper.findByQuestionId(questionnaireId,questionId);
	}

	/**
	 * 查询该问卷有多少人回答过
	 * @param questionnaireId
	 * @return
	 */
	@Override
	public Integer countQuestionnaireByMember(Integer questionnaireId) {
		return memberQuestionnaireMapper.countQuestionnaireByMember(questionnaireId);
	}


	/**
	 * 查询问卷下某个题目的某个选项的用户选择数量
	 * @param questionnaireId
	 * @param questionId
	 * @param optionId
	 * @return
	 */
	@Override
	public Integer countQuestionOptionByMember(Integer questionnaireId, Integer questionId, Integer optionId) {
		Integer count = memberQuestionnaireMapper.countQuestionOptionByMember(questionnaireId,questionId,optionId);
		return count == null ? 0 : count;
	}
}

