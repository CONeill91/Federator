package com.gsc.federator.model;

import java.util.ArrayList;
import java.util.List;

/**
 * User: msaccotelli
 * Date: 10/10/2014
 */
public class SearchResultContainer implements ModelObject {
    private final List<SearchResult> searchResults;

    private final String query;

    public SearchResultContainer(final String query) {
        this.searchResults = new ArrayList<>();
        this.query = query;
    }

    public List<SearchResult> getSearchResults() {
        return searchResults;
    }

    public void addSearchResult(final SearchResult searchResult) {
        if (searchResult.getTitle() == null || searchResult.getTitle().length() == 0) {
            return;
        }

        for (final SearchResult searchResultExisting : searchResults) {
            if (searchResultExisting.getTitle().equalsIgnoreCase(searchResult.getTitle())) {
                return;
            }
        }

        searchResults.add(searchResult);
    }

}
