package com.gsc.federator.model;

/**
 * User: msaccotelli
 * Date: 10/10/2014
 */
public class SearchResult implements ModelObject {
    private String content;

    private String href;

    private String source;

    private String title;


    public String getContent() {
        return content;
    }

    public void setContent(final String content) {
        this.content = content;
    }

    public String getHref() {
        return href;
    }

    public void setHref(final String href) {
        this.href = href;
    }

    public String getSource() {
        return source;
    }

    public void setSource(final String source) {
        this.source = source;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

}
