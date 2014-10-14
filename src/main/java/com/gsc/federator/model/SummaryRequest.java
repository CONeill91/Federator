package com.gsc.federator.model;

/**
 * User: msaccotelli
 * Date: 10/13/2014
 */
public class SummaryRequest implements ModelObject {
    private final String source;

    private final String url;

    public SummaryRequest(final String source, final String url) {
        this.source = source;
        this.url = url;
    }

    public String getSource() {

        return source;
    }


    public String getUrl() {
        return url;
    }


}
