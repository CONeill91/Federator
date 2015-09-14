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
 * Date: 10/22/2014
 */
@Component
public class IntranetSearchAdapter implements SearchAdapter {

    private static final Logger logger = LoggerFactory.getLogger(IntranetSearchAdapter.class);

    @Override
    public String getName() {
        return "Intranet";
    }

    @Override
    public void performSearch(final SearchQuery searchQuery, final SearchResultContainer searchResultContainer) throws IOException {
        final Document doc = Jsoup.connect("http://search:8080/search/?query=" + searchQuery.getQuery() + "&go=Go").get();
        final Elements results = doc.select("div#yschweb ol li");

        for (final Element result : results) {
            try {
                final SearchResult searchResult = new SearchResult();

                searchResult.setSource(this.getName());
                searchResult.setHref(result.select("a.yschttl").first().attr("href"));
                searchResult.setTitle(result.select("a.yschttl").first().text());
                searchResult.setContent(result.select("div.yschabstr").first().text());
                searchResultContainer.addSearchResult(searchResult);
            } catch (Exception ex) {
                logger.error("Error selecting element {}", result.outerHtml(), ex);
            }
        }
    }

    @Override
    public SummaryResult summarize(final SummaryRequest summaryRequest) throws IOException {
        final SummaryResult summaryResult = new SummaryResult(summaryRequest);

        summaryResult.setContent(
                Jsoup.connect(summaryRequest.getUrl()).get().outerHtml());

        return summaryResult;
    }
}
