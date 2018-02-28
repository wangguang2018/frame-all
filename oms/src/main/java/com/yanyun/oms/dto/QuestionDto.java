package com.yanyun.oms.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 问卷调查问题
 */
public class QuestionDto implements Serializable{

    /**
     * 问卷调查题目类型 1多选 2单选 3文本
     */
    private Integer type;

    /**
     * 问题
     */
    private String question;

    /**
     * 单选框
     */
    private List<String> radios;

    /**
     * 多选框
     */
    private List<String> checkboxs;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getRadios() {
        return radios;
    }

    public void setRadios(List<String> radios) {
        this.radios = radios;
    }

    public List<String> getCheckboxs() {
        return checkboxs;
    }

    public void setCheckboxs(List<String> checkboxs) {
        this.checkboxs = checkboxs;
    }
}
