package com.gsc.federator.elasticsearch;

/**
 * User: msaccotelli
 * Date: 1/28/2015
 */
public class PostCommandContainer {
    private String url;
    private String data;
    private String contentType;
    private String encoding;


    public PostCommandContainer() {
        this.contentType = "application/json";
        this.encoding = "utf-8";
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEncoding() {

        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

}
