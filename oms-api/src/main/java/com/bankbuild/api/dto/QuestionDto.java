package com.bankbuild.api.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 问卷调查问题
 */
public class QuestionDto implements Serializable{

    private Integer id;


    /**
     * 问卷调查题目类型 1多选 2单选 3文本
     */
    private Integer type;

    /**
     * 问题
     */
    private String question;

    /**
     * 选项
     */
    private List<QuestionOptionDto> options;


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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<QuestionOptionDto> getOptions() {
        return options;
    }

    public void setOptions(List<QuestionOptionDto> options) {
        this.options = options;
    }
}
