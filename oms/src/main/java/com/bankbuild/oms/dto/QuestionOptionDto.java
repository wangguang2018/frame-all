package com.bankbuild.oms.dto;

import com.ydd.model.entity.QuestionOption;

public class QuestionOptionDto extends QuestionOption {
    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
