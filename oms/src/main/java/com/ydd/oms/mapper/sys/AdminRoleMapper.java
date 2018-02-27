package com.ydd.oms.mapper.sys;

import com.ydd.oms.entity.sys.AdminRole;
import org.apache.ibatis.annotations.Param;

/**
 * Mapper - 管理员角色
 *
 * @author xingkong1221
 * @since 2017-09-26
 */
public interface AdminRoleMapper {

    int deleteByPrimaryKey(AdminRole key);

    int insert(AdminRole record);

    int insertSelective(AdminRole record);

    /**
     * 删除管理员角色
     *
     * @param adminId 管理员编号
     * @return 删除结果
     */
    int deleteByAdminId(Integer adminId);

    /**
     * 保存管理员角色
     *
     * @param adminId 管理员编号
     * @param roleIds 角色编号集合
     */
    void saveRoles(@Param("adminId") Integer adminId,
                   @Param("roleIds") Integer[] roleIds);
}