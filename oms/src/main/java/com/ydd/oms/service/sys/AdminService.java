package com.ydd.oms.service.sys;

import com.ydd.framework.core.common.Pagination;
import com.ydd.framework.core.service.BaseService;
import com.ydd.oms.entity.sys.Admin;

import java.util.List;


/**
 * Service - 管理员
 *
 * @author xingkong1221
 * @since 2017-09-26
 */
public interface AdminService extends BaseService {

    /**
     * 根据账号查询管理员
     *
     * @param account 账号
     * @return 管理员
     */
    Admin findByAccount(String account);

    /**
     * 查询管理员
     *
     * @param id 编号
     * @return 管理员
     */
    Admin findById(Integer id);

    /**
     * 保存管理员
     *
     * @param admin 管理员
     * @param roleIds 角色编号
     */
    void save(Admin admin, Integer[] roleIds);

    /**
     * 删除管理员
     *
     * @param ids 编号
     * @return 删除数量
     */
    Integer delete(Integer[] ids);

    /**
     * 更新状态
     *
     * @param ids 编号
     * @param status 状态
     * @return 更新数量
     */
    Integer updateStatus(Integer[] ids, Byte status);

    /**
     * 查询管理员
     *
     * @param pagination 分页信息
     * @return 分页结果
     */
    Pagination findPage(Pagination pagination);

    /**
     * 手机号码是否存在
     *
     * @param mobile 手机号码
     * @param excludeId 排除编号
     * @return 存在返回 {@code true}，反之 {@code false}
     */
    Boolean isMobileExists(String mobile, Integer excludeId);

    /**
     * 邮箱是否存在
     *
     * @param email 邮箱
     * @param excludeId 排除编号
     * @return 存在返回 {@code true}，反之 {@code false}
     */
    Boolean isEmailExists(String email, Integer excludeId);

    /**
     * 查询角色编号列表
     *
     * @param adminId 管理员编号
     * @return 角色编号列表
     */
    List<Integer> findRoleIdList(Integer adminId);
}
