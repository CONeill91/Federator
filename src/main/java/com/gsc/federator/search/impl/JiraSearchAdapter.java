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
public class JiraSearchAdapter implements SearchAdapter {

    private static final Logger logger = LoggerFactory.getLogger(JiraSearchAdapter.class);

    @Override
    public String getName() {
        return "Jira";
    }

    @Override
    public void peformSearch(final SearchQuery searchQuery, final SearchResultContainer searchResultContainer) throws IOException {

        // https://jira.guidewire.com/issues/?jql=text%20~%20%22ISO8601DateFormat%22

        final Document doc = Jsoup.connect("https://jira.guidewire.com/issues/?jql=text%20~%20%22" + searchQuery.getQuery() + "%22").
                cookie("seraph.rememberme.cookie", "41660%3A9b87214f5d7aaf62fef567016896c2c3386f0b57").
                cookie("_ga", "GA1.2.982632285.1422026701").
                cookie("JSESSIONID", "A8905E669A066AF71E70DB58C18CB5D0").
                cookie("atlassian.xsrf.token", "AFZ7-DEUI-835B-CBSC|796e069bd7fe2a12a00341d5a86ce2d3928f3124|lin").
                get();

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
        return null;
    }
}
