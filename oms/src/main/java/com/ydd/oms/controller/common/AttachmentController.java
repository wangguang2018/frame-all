package com.ydd.oms.controller.common;

import com.ydd.framework.core.common.Pagination;
import com.ydd.framework.core.common.dto.ResponseDTO;
import com.ydd.oms.controller.OmsController;
import com.ydd.oms.dto.AttachmentDTO;
import com.ydd.oms.entity.common.Attachment;
import com.ydd.oms.service.common.AttachmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Controller - 附件
 *
 * @author Demon
 * @since 2017-10-17
 */
@RestController
public class AttachmentController extends OmsController {

	private final Logger logger = LoggerFactory.getLogger(AttachmentController.class);
	
	@Resource
	private AttachmentService attachmentService;

	/**
	 * 分页查询附件
	 */
	@RequestMapping(value = "/attachments", method = RequestMethod.GET)
	public ResponseDTO findPage(Pagination pagination) {
		return ResponseDTO.ok()
				.setPagination(attachmentService.findPage(pagination));
	}

	/**
	 * 查询附件信息
	 */
	@RequestMapping(value = "/attachment/{id}", method = RequestMethod.GET)
	public ResponseDTO findById(@PathVariable("id") Integer id) {
		return ResponseDTO.ok()
				.addAttribute("attachment", attachmentService.findById(id));
	}

	/**
	 * 保存附件
	 */
	@RequestMapping(value = "/attachment", method = RequestMethod.POST)
	public ResponseDTO save(Attachment attachment) {
		attachmentService.save(attachment);
		return ResponseDTO.ok("保存成功");
	}

	/**
	 * 删除附件
	 */
	@RequestMapping(value = "/attachment/delete", method = RequestMethod.POST)
	public ResponseDTO delete(@RequestParam("id[]") Integer[] ids) {
		attachmentService.deleteByIds(ids);
		return ResponseDTO.ok("删除成功");
	}

	/** 上传图片 */
	@ResponseBody
	@RequestMapping(value = "/upload/image", method = RequestMethod.POST)
	public ResponseDTO updateImage(@RequestParam("image") MultipartFile image) throws IOException {
		Attachment attachment = attachmentService.upload(image);
		return ResponseDTO.ok().addAttribute("img",new AttachmentDTO(attachment.getFilename(),attachment.getUri()));
	}

	/** 上传视频 */
	@ResponseBody
	@RequestMapping(value = "/upload/video", method = RequestMethod.POST)
	public ResponseDTO updateVideo(@RequestParam("video") MultipartFile video) throws IOException {
		Attachment attachment = attachmentService.upload(video);
		return ResponseDTO.ok().addAttribute("video",new AttachmentDTO(attachment.getFilename(),attachment.getUri()));
	}

}
