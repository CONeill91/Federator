package com.gsc.federator.search;

import com.gsc.federator.model.SearchQuery;
import com.gsc.federator.model.SearchResultContainer;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * User: msaccotelli
 * Date: 10/13/2014
 */
@Component
public interface SearchAdapter {
    void peformSearch(final SearchQuery searchQuery, final SearchResultContainer searchResultContainer) throws IOException;
}
