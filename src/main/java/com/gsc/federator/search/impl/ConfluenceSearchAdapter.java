package com.gsc.federator.search.impl;

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

/**
 * User: msaccotelli
 * Date: 10/13/2014
 */
@Component
public class ConfluenceSearchAdapter implements SearchAdapter {

    private static final Logger logger = LoggerFactory.getLogger(ConfluenceSearchAdapter.class);

    @Override
    public String getName() {
        return "Confluence";
    }

    @Override
    public void peformSearch(final SearchQuery searchQuery, final SearchResultContainer searchResultContainer) throws IOException {

        final Document doc = Jsoup.connect(
                "https://confluence.guidewire.com/dosearchsite.action?queryString=" + searchQuery.getQuery()).get();

        final Elements results = doc.select("ol.search-results li");
        logger.info(results.size() + "");

        for (final Element result : results) {
            try {
                final SearchResult searchResult = new SearchResult();
                searchResult.setSource(this.getName());
                searchResult.setHref("https://confluence.guidewire.com" + result.select("a").first().attr("href"));
                searchResult.setTitle(result.select("a").first().text());
                searchResult.setContent(result.select("span.date").first().text());
                searchResultContainer.addSearchResult(searchResult);
            } catch (Exception ex) {
                logger.error("Error selecting element {}", result.outerHtml(), ex);
            }
        }

    }

    @Override
    public SummaryResult summarize(final SummaryRequest summaryRequest) throws IOException {
        final SummaryResult summaryResult = new SummaryResult(summaryRequest);

        summaryResult.setContent(Jsoup.connect(summaryRequest.getUrl()).get().outerHtml());
        return summaryResult;
    }
}
