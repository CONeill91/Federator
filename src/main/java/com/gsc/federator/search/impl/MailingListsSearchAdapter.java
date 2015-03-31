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
public class MailingListsSearchAdapter implements SearchAdapter {

    private static final Logger logger = LoggerFactory.getLogger(MailingListsSearchAdapter.class);

    @Override
    public String getName() {
        return "Mail";
    }

    @Override
    public void peformSearch(final SearchQuery searchQuery, final SearchResultContainer searchResultContainer) throws IOException {
        final String[] mailingLists = new String[]{
//                "claimcenterdevmm",
//                "billingcenterdevmm",
//                "policycenterdevmm", 
//                "platform-devmm",
                "platform-supportmm",
                "cc-supportmm",
                "pc-supportmm",
                "bc-supportmm"};

        final int[] pages = new int[]{1, 2, 3};

        for (final String mailingList : mailingLists) {

            if (searchQuery.contains(mailingList)) {

                for (int page : pages) {

                    doSearch(searchQuery, searchResultContainer, mailingList, page);
                }
            }
        }

    }

    private void doSearch(final SearchQuery searchQuery, final SearchResultContainer searchResultContainer,
                          final String mailingList, final int page) {
        try {
            final Document doc = Jsoup.connect("http://itdev1.guidewire.com/cgi-bin/htsearch").
                    data("method", "and").
                    data("format", "builtin-long").
                    data("sort", "score").
                    data("config", mailingList).
                    data("restrict", "").
                    data("exclude", "").
                    data("page", "" + page).
                    data("words", searchQuery.getQuery()).
                    post();

            final Elements results = doc.select("dl");

            for (final Element result : results) {
                try {
                    final SearchResult searchResult = new SearchResult();

                    searchResult.setSource(this.getName() + ":" + mailingList.substring(0, 2).toUpperCase());
                    searchResult.setHref(result.select("a").first().attr("href"));
                    searchResult.setTitle(result.select("a").first().text());
                    searchResult.setContent(result.select("dd").first().text());
                    searchResultContainer.addSearchResult(searchResult);
                } catch (Exception ex) {
                    logger.error("Error selecting element {}", result.outerHtml(), ex);
                }
            }
        } catch (Exception ex) {
            logger.error("Page not available {}", page);
        }
    }

    @Override
    public SummaryResult summarize(final SummaryRequest summaryRequest) throws IOException {
        final SummaryResult summaryResult = new SummaryResult(summaryRequest);

        summaryResult.setContent(Jsoup.connect(summaryRequest.getUrl()).get().outerHtml());
        return summaryResult;
    }
}
