package com.gsc.federator.model;

import java.util.List;

/**
 * User: msaccotelli
 * Date: 10/13/2014
 */
public class SearchQuery implements ModelObject {
    private List<String> searchIn;

    private String query;

    @Override
    public String toString() {
        return "SearchQuery{" +
                "query='" + query + '\'' +
                ", searchIn=" + searchIn +
                '}';
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(final String query) {
        this.query = query != null ? query.trim() : "";
    }

    public List<String> getSearchIn() {
        return searchIn;
    }

    public void setSearchIn(final List<String> searchIn) {
        this.searchIn = searchIn;
    }

    /**
     * Checks if the searchLocation is contained in the searchIn collection.
     * If searchIn is empty or searchLocation is null, returns true.
     * Handles both the 'Mail' as searchLocation and 'Mail:CC' as sub-searchLocation.
     *
     * @param searchLocation
     * @return
     */
    public boolean contains(final String searchLocation) {
        if (searchLocation == null || getSearchIn() == null || getSearchIn().size() == 0 ||
                getSearchIn().contains(searchLocation)) {
            // empty or exact match
            return true;
        }

        for (final String searchInItem : getSearchIn()) {
            if (searchInItem.indexOf("Mail") == 0 && searchLocation.indexOf("Mail") == 0) {
                // begins with 'Mail'
                return true;
            }

            if (searchInItem.indexOf("Mail") == 0 &&
                    searchLocation.toLowerCase().indexOf(searchInItem.split(":")[1].toLowerCase()) == 0) {
                // handles :CC, :PC, :BC mailing lists sub-searchLocations
                return true;
            }
        }
        return false;
    }

}
