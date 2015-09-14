package com.gsc.federator.search.impl;

/**
 * Created by coneill on 11/09/2015.
 */

import com.gsc.federator.model.*;
import com.gsc.federator.search.SearchAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


import java.io.IOException;

@Component
public class ElasticSearchAdapter implements SearchAdapter {

    private static final Logger logger = LoggerFactory.getLogger(ElasticSearchAdapter.class);

    @Override
    public String getName() {
        return "ElasticSearch";
    }

    @Override
    public void performSearch(SearchQuery searchQuery, SearchResultContainer searchResultContainer) throws IOException {
        // Connect to URL
        final Document doc = Jsoup.connect("http://localhost:9200/test/_search?q=attachment:" + searchQuery.getQuery()).ignoreContentType(true).get();
        Elements elements = doc.select("*");
        for(Element element : elements){
            final SearchResult searchResult = new SearchResult();
            searchResult.setContent(element.toString());
            searchResult.setTitle(element.toString());
            searchResult.setHref("N/A");
            searchResult.setSource(this.getName());
            searchResultContainer.addSearchResult(searchResult);
        }




    }

    @Override
    public SummaryResult summarize(SummaryRequest summaryRequest) throws IOException {
        return null;
    }
}
