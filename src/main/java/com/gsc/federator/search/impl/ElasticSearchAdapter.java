package com.gsc.federator.search.impl;

/**
 * Created by coneill on 11/09/2015.
 */

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
import java.util.Base64;

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

        String results = Jsoup.connect("http://localhost:9200/test/_search?-q=attachment:" + searchQuery.getQuery()).ignoreContentType(true).execute().body();
        JsonParser parser = new JsonParser();
        JsonObject obj = (JsonObject)parser.parse(results).getAsJsonObject();
        logger.info(obj.getAsString());



/*
        byte [] decoded = Base64.getDecoder().decode(json);
        final SearchResult searchResult = new SearchResult();
        searchResult.setTitle(decoded.toString());
        searchResult.setHref("N/A");
        searchResult.setSource(this.getName());
        searchResult.setContent(decoded.toString());
        searchResultContainer.addSearchResult(searchResult);*/






    }

    @Override
    public SummaryResult summarize(SummaryRequest summaryRequest) throws IOException {
        return null;
    }
}
