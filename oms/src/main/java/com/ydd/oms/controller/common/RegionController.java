package com.ydd.oms.controller.common;

import com.ydd.framework.core.common.dto.ResponseDTO;
import com.ydd.oms.dto.RegionDto;
import com.ydd.oms.entity.common.Region;
import com.ydd.oms.service.common.RegionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller - 区域
 *
 * @author xingkong1221
 * @since 2017-07-10
 */
@RestController
@RequestMapping(value = "/regions")
public class RegionController {

    @Resource
    private RegionService regionService;

    /**
     * 查询区域信息
     */
    @RequestMapping(method = RequestMethod.GET,value = "")
    public ResponseDTO findByParentId(@RequestParam(value = "parentId", required = false) Integer parentId) {
        return new ResponseDTO().addAttribute("regions", regionService.selectByParentId(parentId));
    }

    /**
     * 查询所有区域
     * @param regionId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/all")
    public ResponseDTO findAllRegions(@RequestParam(value = "regionId",required = false) Integer regionId) {
        List<Region> regionList=regionService.selectByParentId(regionId);
        List<RegionDto> regionDtoList=new ArrayList<>();
        for (Region region:regionList){
            RegionDto regionDto=new RegionDto();
            regionDto.setId(region.getId());
            regionDto.setCode(region.getCode());
            regionDto.setName(region.getName());
            if (region.getLevel()>2){
                regionDto.setCities(null);
            }else {
                regionDto.setCities(new ArrayList<>());
            }
            regionDtoList.add(regionDto);
        }
        return new ResponseDTO().addAttribute("regionList",regionDtoList);
    }


}
