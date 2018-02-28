package com.yanyun.oms.dto;

import com.yanyun.oms.entity.Questionnaire;

import java.util.List;

public class QuestionnaireDto extends Questionnaire{

    private List<QuestionDto> questionList;

    public List<QuestionDto> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<QuestionDto> questionList) {
        this.questionList = questionList;
    }
}
