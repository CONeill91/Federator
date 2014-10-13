package com.gsc.federator.services.impl;

import com.gsc.federator.model.SearchQuery;
import com.gsc.federator.model.SearchResultContainer;
import com.gsc.federator.search.SearchAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Set;

/**
 * User: msaccotelli
 * Date: 10/13/2014
 */
@Service
public class SearchService implements com.gsc.federator.services.SearchService {
    private static final Logger logger = LoggerFactory.getLogger(SearchService.class);

    @Autowired
    private Set<SearchAdapter> searchAdapters;


    @Override
    public void peformSearch(final SearchQuery searchQuery, final SearchResultContainer searchResultContainer) {
        for (final SearchAdapter searchAdapter : searchAdapters) {
            try {
                logger.debug("Searching in [{}]", searchAdapter);

                searchAdapter.peformSearch(searchQuery, searchResultContainer);

                logger.info("Searched in [{}]", searchAdapter);
            } catch (IOException e) {
                logger.error("Unable to search [{}]", searchAdapter, e);
            }
        }
    }
}
