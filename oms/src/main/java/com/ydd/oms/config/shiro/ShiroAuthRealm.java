package com.ydd.oms.config.shiro;

import com.ydd.framework.core.entity.enums.StatusEnum;
import com.ydd.oms.entity.sys.Admin;
import com.ydd.oms.service.sys.AdminService;
import com.ydd.oms.service.sys.PermissionService;
import com.ydd.oms.service.sys.RoleService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

/**
 * shiro权限认证
 *
 * @author xingkong1221
 * @since 2017-09-26
 */
public class ShiroAuthRealm extends AuthorizingRealm {

    @Resource
    private AdminService adminService;

    @Resource
    private RoleService roleService;

    @Resource
    private PermissionService permissionService;

    /**
     * 请求鉴权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        ShiroAdmin shiroAdmin = (ShiroAdmin) principals.getPrimaryPrincipal();

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        authorizationInfo.addRoles(roleService.findRoleNameByAdminId(shiroAdmin.getId()));
        authorizationInfo.addStringPermissions(permissionService.findPermissionKeyListByAdminId(shiroAdmin.getId()));

        return authorizationInfo;
    }

    /**
     * 登录鉴权
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authToken;
        Admin admin = adminService.findByAccount(token.getUsername());

        if (admin == null) {
            throw new UnknownAccountException("账户不存在");
        }

        // 判断管理员是否被禁用
        if (StatusEnum.DISABLE.value.equals(admin.getStatus())) {
            throw new DisabledAccountException("管理员【" + admin.getUsername() + "】已被禁用，无法登录！");
        }

        return new SimpleAuthenticationInfo(
                new ShiroAdmin(admin.getId(), admin.getMobile(), admin.getEmail(), admin.getUsername(), admin.getGender()),
                admin.getPassword(),
                ByteSource.Util.bytes(admin.getSalt()),
                getName());
    }

}
