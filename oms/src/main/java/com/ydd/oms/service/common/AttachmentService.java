package com.ydd.oms.service.common;

import com.ydd.framework.core.common.Pagination;
import com.ydd.framework.core.service.BaseService;
import com.ydd.oms.entity.common.Attachment;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service - 附件管理
 *
 * @author Demon
 * @since 2017-10-16
 */
public interface AttachmentService extends BaseService {

    /**
     * 保存附件管理
     *
     * @param attachment 附件管理
     */
    void save(Attachment attachment);

    /**
     * 更新附件管理
     *
     * @param attachment 附件管理
     */
    void update(Attachment attachment);

    /**
     * 删除附件管理
     *
     * @param id 编号
     * @return 删除数量
     */
    Integer delete(Integer id);

    /**
     * 删除附件管理
     *
     * @param ids 编号数组
     * @return 删除数量
     */
    Integer deleteByIds(Integer[] ids);

    /**
     * 查询附件管理
     *
     * @param id 编号
     * @return 附件管理
     */
    Attachment findById(Integer id);

    /**
     * 查询附件管理
     *
     * @param pagination 分页信息
     * @return 分页结果
     */
    Pagination findPage(Pagination pagination);

    Attachment upload(MultipartFile file);
}

