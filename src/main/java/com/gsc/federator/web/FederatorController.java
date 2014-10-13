package com.gsc.federator.web;

import com.gsc.federator.model.SearchQuery;
import com.gsc.federator.model.SearchResultContainer;
import com.gsc.federator.services.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FederatorController {

    private static final Logger logger = LoggerFactory.getLogger(FederatorController.class);

    @Autowired
    private SearchService searchService;

    public SearchService getSearchService() {
        return searchService;
    }


    @RequestMapping("/search")
    public SearchResultContainer search(@RequestParam(value = "q", required = true) String query) {
        final SearchResultContainer searchResultContainer = new SearchResultContainer(query);
        final SearchQuery searchQuery = new SearchQuery(query);

        getSearchService().peformSearch(searchQuery, searchResultContainer);

        return searchResultContainer;
    }
}
