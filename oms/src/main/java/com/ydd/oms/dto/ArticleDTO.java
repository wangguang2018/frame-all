package com.ydd.oms.dto;

import com.ydd.oms.entity.common.Article;

import java.util.List;

public class ArticleDTO extends Article {

    private String categoryName;

    private Boolean isPublish;

    private List<Integer> tags;

    private List<String> tagNames;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Boolean getPublish() {
        return isPublish;
    }

    public void setPublish(Boolean publish) {
        isPublish = publish;
    }

    public List<Integer> getTags() {
        return tags;
    }

    public void setTags(List<Integer> tags) {
        this.tags = tags;
    }

    public List<String> getTagNames() {
        return tagNames;
    }

    public void setTagNames(List<String> tagNames) {
        this.tagNames = tagNames;
    }
}
