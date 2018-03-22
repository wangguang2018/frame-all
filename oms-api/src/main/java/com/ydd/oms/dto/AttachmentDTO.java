package com.ydd.oms.dto;

/**
 * created by zhaoziyu
 * 2017-10-17
 * 附件DTO
 */
public class AttachmentDTO {
    private String name;
    private String url;

    public AttachmentDTO(){

    }
    public AttachmentDTO(String fileName,String uri){
        this.name = fileName;
        this.url = uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
