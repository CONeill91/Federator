package com.gsc.federator.services;

import com.gsc.federator.model.SearchQuery;
import com.gsc.federator.model.SearchResultContainer;
import org.springframework.stereotype.Service;

/**
 * User: msaccotelli
 * Date: 10/13/2014
 */
@Service
public interface SearchService {
    void peformSearch(SearchQuery searchQuery, SearchResultContainer searchResultContainer);

}
