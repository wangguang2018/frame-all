package com.ydd.oms.service.common.impl;

import com.ldnz.oms.mapper.AreaMapper;
import com.ydd.framework.core.service.impl.BaseServiceImpl;
import com.ydd.oms.entity.common.Region;
import com.ydd.oms.entity.enums.RegionLevelEnum;
import com.ydd.oms.entity.enums.RegionOpenStatusEnum;
import com.ydd.oms.mapper.common.RegionMapper;
import com.ydd.oms.service.common.RegionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Service - 区域
 *
 * @author xingkong1221
 * @since 2017-07-06
 */
@Service
@Transactional(readOnly = true)
public class RegionServiceImpl extends BaseServiceImpl implements RegionService {

    @Resource
    private RegionMapper regionMapper;

    @Resource
    private AreaMapper areaMapper;


    @Override
    public List<Region> selectByParentId(Integer parentId) {
        return regionMapper.selectByParentId(parentId);
    }

    @Override
    public Region save(String code, String name, RegionLevelEnum level, Integer parentId) {
        return null;
    }

    /**
     * 更新开放城市id缓存
     */
    @Override
    @Transactional
    public void updateOpenIdCache() {
        // 查询全部镇编号
        Set<Integer> villageIds = areaMapper.findVillageIdSet();

        // 查询省市区编号
        Set<Integer> countyIds = regionMapper.findParentIdSet(villageIds);
        Set<Integer> cityIds = regionMapper.findParentIdSet(countyIds);
        Set<Integer> provinceIds = regionMapper.findParentIdSet(cityIds);
        Set<Integer> ids = new HashSet<>();
        ids.addAll(countyIds);
        ids.addAll(cityIds);
        ids.addAll(provinceIds);

        regionMapper.updateOpenStatus(null, RegionOpenStatusEnum.CLOSE.value);
        regionMapper.updateOpenStatus(ids, RegionOpenStatusEnum.OPEN.value);
    }
}
