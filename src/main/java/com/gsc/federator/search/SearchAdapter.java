package com.gsc.federator.search;

import com.gsc.federator.model.SearchQuery;
import com.gsc.federator.model.SearchResultContainer;
import com.gsc.federator.model.SummaryRequest;
import com.gsc.federator.model.SummaryResult;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * User: msaccotelli
 * Date: 10/13/2014
 */
@Component
public interface SearchAdapter {
    /**
     * @return Adapter name
     */
    String getName();

    /**
     * Performs search
     *
     * @param searchQuery
     * @param searchResultContainer
     * @throws IOException
     */
    void peformSearch(SearchQuery searchQuery, SearchResultContainer searchResultContainer) throws IOException;


    /**
     * Summarizes page's content
     *
     * @param summaryRequest
     * @return
     * @throws IOException
     */
    SummaryResult summarize(SummaryRequest summaryRequest) throws IOException;
}
