package com.gsc.federator.search.impl;

import com.gsc.federator.model.SearchQuery;
import com.gsc.federator.model.SearchResult;
import com.gsc.federator.model.SearchResultContainer;
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
    public void peformSearch(final SearchQuery searchQuery, final SearchResultContainer searchResultContainer) throws IOException {

        final Document doc = Jsoup.connect("https://confluence.guidewire.com/dosearchsite.action?queryString=" + searchQuery.getQuery()).
                cookie("seraph.confluence", "32407755%3Aeb8165980ba3c95e4ce79dec7e8cab032b35f7f5").
                cookie("JSESSIONID", "4875ED3BE473C9CC34BC27814C4E1DD0").
                get();

        final Elements results = doc.select("ul.search-results li");

        for (final Element result : results) {
            if (result.select("a").size() == 0 || result.select("span.search-result-summary").size() == 0) {
                continue;
            }

            try {
                final SearchResult searchResult = new SearchResult();

                searchResult.setSource("Confluence");
                searchResult.setHref("https://confluence.guidewire.com" + result.select("a").first().attr("href"));
                searchResult.setTitle(result.select("div.result").text());
                searchResult.setContent(result.select("span.search-result-summary").first().text());
                searchResultContainer.getSearchResults().add(searchResult);
            } catch (Exception ex) {
                logger.error("Error selecting element {}", result.outerHtml(), ex);
            }
        }

    }
}
