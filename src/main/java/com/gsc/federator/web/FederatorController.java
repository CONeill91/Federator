package com.gsc.federator.web;

import com.gsc.federator.model.SearchQuery;
import com.gsc.federator.model.SearchResultContainer;
import com.gsc.federator.model.SummaryRequest;
import com.gsc.federator.model.SummaryResult;
import com.gsc.federator.services.SearchService;
import com.gsc.federator.services.SummaryService;
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

    @Autowired
    private SummaryService summaryService;


    @RequestMapping("/search")
    public SearchResultContainer search(@RequestParam(value = "q", required = true) String query) {
        final SearchQuery searchQuery = new SearchQuery(query);
        final SearchResultContainer searchResultContainer = new SearchResultContainer(query);

        searchService.peformSearch(searchQuery, searchResultContainer);

        return searchResultContainer;
    }

    @RequestMapping("/summarize")
    public SummaryResult summarize(
            @RequestParam(value = "s", required = true) String source,
            @RequestParam(value = "u", required = true) String url) {

        final SummaryRequest summaryRequest = new SummaryRequest(source, url);

        return summaryService.summarize(summaryRequest);
    }
}
