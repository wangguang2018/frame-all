package com.ydd.oms.service.common.impl;

import com.github.pagehelper.PageHelper;
import com.ydd.framework.core.common.Pagination;
import com.ydd.framework.core.common.storage.Bucket;
import com.ydd.framework.core.common.utils.ValidationUtils;
import com.ydd.framework.core.exception.ServiceException;
import com.ydd.framework.core.service.impl.BaseServiceImpl;
import com.ydd.oms.entity.common.Attachment;
import com.ydd.oms.entity.enums.AttachmentTypeEnum;
import com.ydd.oms.mapper.common.AttachmentMapper;
import com.ydd.oms.service.common.AttachmentService;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Service - 附件
 *
 * @author Demon
 * @since 2017-10-17
 */
@Service
@Transactional(readOnly = true)
public class AttachmentServiceImpl extends BaseServiceImpl implements AttachmentService {

	private final Logger logger = LoggerFactory.getLogger(AttachmentServiceImpl.class);

	@Resource
	private AttachmentMapper attachmentMapper;


	@Resource
	private Bucket bucket;

	/**
	 * 创建附件
	 *
	 * @param attachment 附件
	 */
	@Override
	@Transactional
	public void save(Attachment attachment) {
		if (attachment.getId() != null && attachment.getId() > 0) {
			// 更新
			attachmentMapper.update(attachment);
		} else {
			// 新建
			attachmentMapper.insert(attachment);
		}
	}

	/**
	 * 更新附件
	 *
	 * @param attachment 附件
	 */
	@Override
	@Transactional
	public void update(Attachment attachment) {
		attachmentMapper.update(attachment);
	}

	/**
	 * 删除附件
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
		return attachmentMapper.delete(id);
	}

	/**
	 * 删除附件
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
		return attachmentMapper.deleteByIds(ids);
	}

	/**
	 * 查询附件
	 *
	 * @param id 编号
	 * @return 附件
	 */
	public Attachment findById(Integer id) {
		return attachmentMapper.findById(id);
	}

	/**
	 * 查询附件
	 *
	 * @param pagination 分页信息
	 * @return 分页结果
	 */
	@Override
	public Pagination findPage(Pagination pagination) {
		PageHelper.startPage(pagination.getPage(), pagination.getPageSize());
		PageHelper.orderBy("id desc");
		pagination.setQueryResult(attachmentMapper.findAll());
		return pagination;
	}

	@Override
	@Transactional
	public Attachment upload(MultipartFile file) {
		ValidationUtils.assertNotNull(file);
		String fileName = file.getOriginalFilename();
		String extension = FilenameUtils.getExtension(fileName).toLowerCase();
		fileName = System.currentTimeMillis() + "." + extension;
		// 附件上传
		String uri = null;
		try {
			uri = bucket.putObject(file.getOriginalFilename(), file.getInputStream());
		} catch (IOException e) {
			throw new ServiceException("附件上传失败", e);
		}
		Attachment attachment = new Attachment();
		attachment.setName(fileName);
		attachment.setFilename(file.getOriginalFilename());
		attachment.setUri(uri);
		attachment.setType((int)getType(fileName).value);
		attachment.setCreatedId(0);
		attachment.setFileSize((int)file.getSize()/1024);
		attachment.setModifiedId(0);
		attachmentMapper.insert(attachment);
		return attachment;
	}

	private AttachmentTypeEnum getType(String filename) {
		AttachmentTypeEnum type = AttachmentTypeEnum.UNKNOW;
		switch (FilenameUtils.getExtension(filename).toLowerCase()) {
			case "jpg":
			case "gif":
			case "png":
			case "tif":
			case "bmp":
			case "dwg":
				type = AttachmentTypeEnum.IMAGE;
				break;
			case "rmvb":
			case "mp4":
			case "avi":
			case "mkv":
				type = AttachmentTypeEnum.VIDEO;
				break;
			case "rar":
			case "zip":
			case "tar.gz":
				type = AttachmentTypeEnum.PACKAGE;
				break;
			case "doc":
			case "docx":
				type = AttachmentTypeEnum.WORD;
				break;
			case "xls":
			case "xlsx":
				type = AttachmentTypeEnum.EXCEL;
				break;
			case "pdf":
				type = AttachmentTypeEnum.PDF;
				break;
		}
		return type;
	}

}

