package com.ydd.oms.service.sys.impl;

import com.github.pagehelper.PageHelper;
import com.ydd.framework.core.common.Pagination;
import com.ydd.framework.core.entity.enums.StatusEnum;
import com.ydd.framework.core.service.impl.BaseServiceImpl;
import com.ydd.oms.common.exception.OmsException;
import com.ydd.oms.config.shiro.ShiroConfiguration;
import com.ydd.oms.entity.sys.Admin;
import com.ydd.oms.mapper.sys.AdminMapper;
import com.ydd.oms.mapper.sys.AdminRoleMapper;
import com.ydd.oms.service.sys.AdminService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.RandomStringGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Service - 管理员
 *
 * @author xingkong1221
 * @since 2017-09-26
 */
@Service
@Transactional(readOnly = true)
public class AdminServiceImpl extends BaseServiceImpl implements AdminService {

    @Resource
    private AdminMapper adminMapper;

    @Resource
    private AdminRoleMapper adminRoleMapper;

    /**
     * 根据账号查询管理员
     *
     * @param account 账号
     * @return 管理员
     */
    @Override
    public Admin findByAccount(String account) {
        return adminMapper.findByAccount(account);
    }

    /**
     * 查询管理员
     *
     * @param id 编号
     * @return 管理员
     */
    @Override
    public Admin findById(Integer id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    /**
     * 保存管理员
     *
     * @param admin 管理员
     * @param roleIds 角色编号
     */
    @Override
    @Transactional
    public void save(Admin admin, Integer[] roleIds) {
        if (StringUtils.isNotBlank(admin.getPassword())
                && !"NOT_MODIFIED".equalsIgnoreCase(admin.getPassword())) {
            encryptPassword(admin);
        } else {
            admin.setPassword("");
        }

        if (admin.getId() != null && admin.getId() > 0) {
            // 更新
            adminMapper.updateByPrimaryKeySelective(admin);
        } else {
            // 新建
            adminMapper.insertSelective(admin);
        }

        adminRoleMapper.deleteByAdminId(admin.getId());
        if (roleIds != null && roleIds.length > 0) {
            adminRoleMapper.saveRoles(admin.getId(), roleIds);
        }
    }

    /**
     * 删除管理员
     *
     * @param ids 编号
     * @return 删除数量
     */
    @Override
    @Transactional
    public Integer delete(Integer[] ids) {
        if (ids == null || ids.length == 0) {
            return 0;
        }

        for (Integer id : ids) {
            if (id == 1) {
                throw new OmsException("请勿删除超级管理员");
            }
        }

        return adminMapper.deleteByIds(ids);
    }

    /**
     * 更新状态
     *
     * @param ids 编号
     * @param status 状态
     * @return 更新数量
     */
    @Override
    @Transactional
    public Integer updateStatus(Integer[] ids, Byte status) {
        if (ids == null) {
            return 0;
        }
        if (status == null) {
            status = StatusEnum.DISABLE.value;
        }

        return adminMapper.updateStatus(ids, status);
    }

    /**
     * 查询管理员
     *
     * @param pagination 分页信息
     * @return 分页结果
     */
    @Override
    public Pagination findPage(Pagination pagination) {
        PageHelper.startPage(pagination.getPage(), pagination.getPageSize());
        PageHelper.orderBy("id desc");
        pagination.setQueryResult(adminMapper.findAll());
        return pagination;
    }

    /**
     * 手机号码是否存在
     *
     * @param mobile 手机号码
     * @param excludeId 排除编号
     * @return 存在返回 {@code true}，反之 {@code false}
     */
    @Override
    public Boolean isMobileExists(String mobile, Integer excludeId) {
        return adminMapper.countByMobileAndExcludeId(mobile, excludeId) != 0;
    }

    /**
     * 邮箱是否存在
     *
     * @param email 邮箱
     * @param excludeId 排除编号
     * @return 存在返回 {@code true}，反之 {@code false}
     */
    @Override
    public Boolean isEmailExists(String email, Integer excludeId) {
        return adminMapper.countByEmailAndExcludeId(email, excludeId) != 0;
    }

    /**
     * 查询角色编号列表
     *
     * @param adminId 管理员编号
     * @return 角色编号列表
     */
    @Override
    public List<Integer> findRoleIdList(Integer adminId) {
        return adminMapper.findRoleIdList(adminId);
    }

    /**
     * 密码加密
     *
     * @param admin 管理员
     */
    private void encryptPassword(Admin admin) {
        RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .withinRange('a', 'z').build();
        admin.setSalt(generator.generate(4));
        admin.setPassword(
                new SimpleHash(
                        ShiroConfiguration.HASH_ALGORITHM_NAME,
                        admin.getPassword(),
                        admin.getSalt(),
                        ShiroConfiguration.HASH_ITERATIONS).toString());
    }
}
