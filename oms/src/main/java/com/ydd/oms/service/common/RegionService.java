package com.ydd.oms.service.common;

import com.ydd.framework.core.service.BaseService;
import com.ydd.oms.entity.common.Region;
import com.ydd.oms.entity.enums.RegionLevelEnum;

import java.util.List;

/**
 * Service - 区域
 *
 * @author xingkong1221
 * @since 2017-07-06
 */
public interface RegionService extends BaseService {

    /**
     * 查询区域信息
     *
     * @param parentId 上级编号
     * @return 区域信息
     */
    List<Region> selectByParentId(Integer parentId);

    /**
     * 保存区域信息
     *
     * @param code 代码
     * @param name 名称
     * @param level 区域等级
     * @param parentId 上级编号
     * @return 区域
     */
    Region save(String code, String name, RegionLevelEnum level, Integer parentId);

    /**
     * 更新开放城市id缓存
     */
    void updateOpenIdCache();
}
