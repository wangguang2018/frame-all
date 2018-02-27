package com.ydd.oms.mapper.sys;

import com.ydd.oms.entity.sys.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Mapper - 管理员
 *
 * @author xingkong1221
 * @since 2017-09-26
 */
public interface AdminMapper {

    /**
     * 查询管理员
     *
     * @param account 账号
     * @return 管理员
     */
    Admin findByAccount(String account);

    /**
     * 查询管理员
     *
     * @return 管理员
     */
    List<Admin> findAll();

    /**
     * 删除管理员
     *
     * @param ids 编号
     * @return 删除数量
     */
    int deleteByIds(Integer[] ids);

    /**
     * 更新状态
     *
     * @param ids 编号
     * @param status 状态
     * @return 更新数量
     */
    int updateStatus(@Param("ids") Integer[] ids, @Param("status") Byte status);

    /**
     * 删除管理员
     *
     * @param id 编号
     * @return 删除数量
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 查询管理员数量
     *
     * @param mobile 手机号码
     * @param excludeId 排除编号
     * @return 数量
     */
    int countByMobileAndExcludeId(@Param("mobile") String mobile, @Param("excludeId") Integer excludeId);

    /**
     * 查询管理员数量
     *
     * @param email 邮箱
     * @param excludeId 排除编号
     * @return 数量
     */
    int countByEmailAndExcludeId(@Param("email") String email, @Param("excludeId") Integer excludeId);

    /**
     * 查询角色编号列表
     *
     * @param adminId 管理员编号
     * @return 角色编号列表
     */
    List<Integer> findRoleIdList(Integer adminId);



    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
}