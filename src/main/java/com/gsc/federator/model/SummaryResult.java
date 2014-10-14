package com.gsc.federator.model;

/**
 * User: msaccotelli
 * Date: 10/13/2014
 */
public class SummaryResult implements ModelObject {
    private final SummaryRequest summaryRequest;

    private String content;

    public SummaryResult(final SummaryRequest summaryRequest) {
        this.summaryRequest = summaryRequest;
    }

    public String getContent() {
        return content;
    }

    public void setContent(final String content) {
        this.content = content;
    }
}
