package com.ydd.oms.mapper.common;

import com.ydd.oms.entity.common.Attachment;

import java.util.List;

/**
 * Mapper - 附件
 *
 * @author Demon
 * @since 2017-10-17
 */
public interface AttachmentMapper {

    /**
     * 新建附件
     *
     * @param attachment 附件
     */
    void insert(Attachment attachment);

    /**
     * 更新附件
     *
     * @param attachment 附件
     */
    void update(Attachment attachment);

    /**
     * 删除附件
     *
     * @param id 编号
     * @return 删除数量
     */
    int delete(Integer id);

    /**
     * 删除附件
     *
     * @param ids 编号数组
     * @return 删除数量
     */
    int deleteByIds(Integer[] ids);

    /**
     * 查询附件
     *
     * @param id 编号
     * @return 附件
     */
    Attachment findById(Integer id);

    /**
     * 查询附件
     *
     * @return 附件集合
     */
    List<Attachment> findAll();

}