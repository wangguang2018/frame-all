package com.ydd.oms.config.shiro;

import java.io.Serializable;

/**
 * Shiro 管理员
 *
 * @author xingkong1221
 * @since 2017-09-26
 */
public class ShiroAdmin implements Serializable {

    private static final long serialVersionUID = 8152565640636103437L;

    /**
     * 编号
     */
    private Integer id;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 姓名
     */
    private String username;

    /**
     * 性别
     *
     * @see com.ydd.framework.core.entity.enums.GenderEnum
     */
    private Byte gender;

    /**
     * 实例化shiro管理员
     *
     * @param id 编号
     * @param mobile 手机号码
     * @param email 邮箱
     * @param username 姓名
     * @param gender 性别
     */
    public ShiroAdmin(Integer id, String mobile, String email, String username, Byte gender) {
        this.id = id;
        this.mobile = mobile;
        this.email = email;
        this.username = username;
        this.gender = gender;
    }

    /**
     * 获取编号
     *
     * @return 编号
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置编号
     *
     * @param id 编号
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取手机号码
     *
     * @return 手机号码
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机号码
     *
     * @param mobile 手机号码
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取邮箱
     *
     * @return 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取真实姓名
     *
     * @return 真实姓名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置真实姓名
     *
     * @param username 真实姓名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取性别
     *
     * @see com.ydd.framework.core.entity.enums.GenderEnum
     * @return 性别
     */
    public Byte getGender() {
        return gender;
    }

    /**
     * 设置性别
     *
     * @see com.ydd.framework.core.entity.enums.GenderEnum
     * @param gender 性别
     */
    public void setGender(Byte gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        ShiroAdmin shiroAdmin = (ShiroAdmin) obj;

        if (shiroAdmin.getEmail() != null
                && shiroAdmin.getEmail().equals(getEmail())) {
            return true;
        } else if (shiroAdmin.getMobile() != null
                && shiroAdmin.getMobile().equals(getMobile())) {
            return true;
        }

        return false;
    }
}
