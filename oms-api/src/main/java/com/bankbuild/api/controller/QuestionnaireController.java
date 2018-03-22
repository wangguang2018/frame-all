package com.bankbuild.api.controller;

import com.bankbuild.api.service.QuestionnaireService;
import com.ydd.framework.core.common.Pagination;
import com.ydd.framework.core.common.dto.ResponseDTO;
import com.ydd.framework.core.entity.enums.StatusEnum;
import com.ydd.model.entity.Questionnaire;
import com.ydd.oms.controller.OmsController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Controller - 问卷调查
 *
 * @author zhaoziyu
 * @since 2018-02-27
 */
@RestController
public class QuestionnaireController extends OmsController {

	private final Logger logger = LoggerFactory.getLogger(QuestionnaireController.class);
	
	@Resource
	private QuestionnaireService questionnaireService;

	/**
	 * 分页查询问卷调查
	 */
	@RequestMapping(value = "/questionnaires", method = RequestMethod.GET)
	public ResponseDTO findPage(Pagination pagination) {
		return ResponseDTO.ok()
				.setPagination(questionnaireService.findPage(pagination));
	}

	/**
	 * 查询问卷调查信息
	 */
	@RequestMapping(value = "/questionnaire/{id}", method = RequestMethod.GET)
	public ResponseDTO findById(@PathVariable("id") Integer id) {
		return ResponseDTO.ok()
				.addAttribute("questionnaire", questionnaireService.findById(id));
	}

	/**
	 * 保存问卷调查
	 */
	@RequestMapping(value = "/questionnaire", method = RequestMethod.POST)
	public ResponseDTO save(Questionnaire questionnaire) {
		questionnaireService.save(questionnaire);
		return ResponseDTO.ok("保存成功");
	}

	/**
	 * 删除问卷调查
	 */
	@RequestMapping(value = "/questionnaire/delete", method = RequestMethod.POST)
	public ResponseDTO delete(@RequestParam("id[]") Integer[] ids) {
		questionnaireService.deleteByIds(ids);
		return ResponseDTO.ok("删除成功");
	}

	/**
	 * 变更问卷调查状态
	 */
	@RequestMapping(value = "/questionnaire/status", method = RequestMethod.POST)
	public ResponseDTO updateStatus(@RequestParam("id[]") Integer[] ids, Byte status) {
		questionnaireService.updateStatus(ids, status);
		String operationName = (StatusEnum.ENABLE.value.equals(status) ? "启用" : "禁用") + "成功";
		return ResponseDTO.ok(operationName);
	}

	/**
	 * 查询问卷调查信息
	 */
	@RequestMapping(value = "/questionnaire/statistics/{id}", method = RequestMethod.GET)
	public ResponseDTO statistics(@PathVariable("id") Integer id) {
		return ResponseDTO.ok()
				.addAttribute("questionnaire", questionnaireService.statistics(id));
	}

}
