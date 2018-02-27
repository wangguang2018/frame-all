package com.ydd.oms.config.mybatis;

import java.io.Serializable;

public class SearchParam implements Serializable{
    private String key;
    private String value;

    public SearchParam(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public SearchParam() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
