package com.gsc.federator.model;

/**
 * User: msaccotelli
 * Date: 10/13/2014
 */
public class SearchQuery implements ModelObject {
    private String query;

    public SearchQuery(final String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

}
