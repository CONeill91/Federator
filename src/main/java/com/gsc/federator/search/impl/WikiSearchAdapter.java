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
public class WikiSearchAdapter implements SearchAdapter {

    private static final Logger logger = LoggerFactory.getLogger(WikiSearchAdapter.class);

    @Override
    public void peformSearch(final SearchQuery searchQuery, final SearchResultContainer searchResultContainer) throws IOException {
        final Document doc = Jsoup.connect("http://search:8080/search/?query=" + searchQuery.getQuery() + "&go=Go").get();
        final Elements results = doc.select("div#yschweb ol li");

        for (final Element result : results) {
            try {
                final SearchResult searchResult = new SearchResult();

                searchResult.setSource("Wiki");
                searchResult.setHref(result.select("a.yschttl").first().attr("href"));
                searchResult.setTitle(result.select("a.yschttl").first().text());
                searchResult.setContent(result.select("div.yschabstr").first().text());
                searchResultContainer.getSearchResults().add(searchResult);
            } catch (Exception ex) {
                logger.error("Error selecting element {}", result.outerHtml(), ex);
            }
        }
    }

}
