package com.ydd.oms.dto;

import com.ydd.oms.entity.common.Region;

import java.util.List;

public class RegionDto extends Region{

    private List<String> cities;

    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }
}
