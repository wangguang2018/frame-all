package com.yanyun.oms.entity;

import java.io.Serializable;

/**
 * 问卷调查题目选项
 */
public class QuestionOption implements Serializable{
    private Integer id;
    private Integer questionId;
    private String name;

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
