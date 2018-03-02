package com.yanyun.oms.controller;

import com.ydd.framework.core.common.Pagination;
import com.ydd.framework.core.common.dto.ResponseDTO;
import com.ydd.oms.controller.OmsController;
import com.yanyun.oms.entity.MemberQuestionnaire;
import com.yanyun.oms.service.MemberQuestionnaireService;
import com.ydd.framework.core.entity.enums.StatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Controller - 用户问卷信息
 *
 * @author zhaoziyu
 * @since 2018-03-01
 */
@RestController
public class MemberQuestionnaireController extends OmsController {

	private final Logger logger = LoggerFactory.getLogger(MemberQuestionnaireController.class);
	
	@Resource
	private MemberQuestionnaireService memberQuestionnaireService;

	/**
	 * 分页查询用户问卷信息
	 */
	@RequestMapping(value = "/member/questionnaires", method = RequestMethod.GET)
	public ResponseDTO findPage(Pagination pagination) {
		return ResponseDTO.ok()
				.setPagination(memberQuestionnaireService.findPage(pagination));
	}

	/**
	 * 查询用户问卷信息信息
	 */
	@RequestMapping(value = "/member/questionnaire/{id}", method = RequestMethod.GET)
	public ResponseDTO findById(@PathVariable("id") Integer id) {
		return ResponseDTO.ok()
				.addAttribute("memberQuestionnaire", memberQuestionnaireService.findById(id));
	}

	/**
	 * 保存用户问卷信息
	 */
	@RequestMapping(value = "/member/questionnaire", method = RequestMethod.POST)
	public ResponseDTO save(MemberQuestionnaire memberQuestionnaire) {
		memberQuestionnaireService.save(memberQuestionnaire);
		return ResponseDTO.ok("保存成功");
	}

	/**
	 * 删除用户问卷信息
	 */
	@RequestMapping(value = "/member/questionnaire/delete", method = RequestMethod.POST)
	public ResponseDTO delete(@RequestParam("id[]") Integer[] ids) {
		memberQuestionnaireService.deleteByIds(ids);
		return ResponseDTO.ok("删除成功");
	}

}
