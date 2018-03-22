package com.ydd.oms.dto.sys;

import java.io.Serializable;

/**
 * DTO - 权限点
 *
 * @author xingkong1221
 * @since 2017-10-13
 */
public class PermissionDTO implements Serializable {

    private static final long serialVersionUID = 3424641236414956015L;

    /**
     * ID
     */
    private Integer id;

    /**
     * 权限点名称
     */
    private String name;

    /**
     * 权限点
     */
    private String key;

    /**
     * 是否选中
     */
    private Boolean checked = Boolean.FALSE;


    /**
     * 设置ID
     *
     * @param id ID
     */
    public void setId(Integer id){
        this.id=id;
    }

    /**
     * 获取ID
     *
     * @return ID
     */
    public Integer getId(){
        return id;
    }


    /**
     * 设置权限点名称
     *
     * @param name 权限点名称
     */
    public void setName(String name){
        this.name=name;
    }

    /**
     * 获取权限点名称
     *
     * @return 权限点名称
     */
    public String getName(){
        return name;
    }


    /**
     * 设置权限点
     *
     * @param key 权限点
     */
    public void setKey(String key){
        this.key=key;
    }

    /**
     * 获取权限点
     *
     * @return 权限点
     */
    public String getKey(){
        return key;
    }

    /**
     * 是否选中
     *
     * @return 是否选中
     */
    public Boolean getChecked() {
        return checked;
    }

    /**
     * 设置是否选中
     *
     * @param checked 是否选中
     */
    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
