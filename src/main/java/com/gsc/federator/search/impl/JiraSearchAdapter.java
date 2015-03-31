package com.gsc.federator.search.impl;

import com.gsc.federator.model.*;
import com.gsc.federator.search.CookieReader;
import com.gsc.federator.search.SearchAdapter;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * User: msaccotelli
 * Date: 10/13/2014
 */
@Component
public class JiraSearchAdapter implements SearchAdapter {

    private static final Logger logger = LoggerFactory.getLogger(JiraSearchAdapter.class);

    @Autowired
    private CookieReader cookieReader;


    @Override
    public String getName() {
        return "Jira";
    }

    @Override
    public void peformSearch(final SearchQuery searchQuery, final SearchResultContainer searchResultContainer) throws IOException {

        // https://jira.guidewire.com/issues/?jql=text%20~%20%22ISO8601DateFormat%22

        final List<Cookie> cookies = cookieReader.readCookies(this);

        final Connection connection = Jsoup.connect("https://jira.guidewire.com/issues/?jql=text%20~%20%22" + searchQuery.getQuery() + "%22");

        for (final Cookie cookie : cookies) {
            connection.cookie(cookie.getName(), cookie.getValue());
        }

        final Document doc = connection.get();

        final Elements results = doc.select("div ol li");

        for (final Element result : results) {
            try {
                final SearchResult searchResult = new SearchResult();

                searchResult.setSource(this.getName());
                searchResult.setHref("https://jira.guidewire.com" + result.select("a").first().attr("href"));
                searchResult.setTitle(result.select("a").first().text() + " - " + result.select("a").last().text());
                searchResult.setContent(result.select("img").first().attr("title"));
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
