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
public class WikiSearchAdapter implements SearchAdapter {

    private static final Logger logger = LoggerFactory.getLogger(WikiSearchAdapter.class);

    @Override
    public String getName() {
        return "Wiki";
    }

    @Override
    public void peformSearch(final SearchQuery searchQuery, final SearchResultContainer searchResultContainer) throws IOException {
        final Document doc = Jsoup.connect("http://wiki/index.php?title=Special%3ASearch&search=" + searchQuery.getQuery() + "&fulltext=Search").get();
        final Elements results = doc.select("ul.mw-search-results li");

        for (final Element result : results) {
            try {
                final SearchResult searchResult = new SearchResult();

                searchResult.setSource(this.getName());
                searchResult.setHref("http://wiki" + result.select("a").first().attr("href"));
                searchResult.setTitle(result.select("a").first().text());
                searchResult.setContent(result.select("div.searchresult").first().text());
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
