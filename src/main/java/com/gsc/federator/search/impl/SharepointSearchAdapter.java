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
 * Created by coneill on 26/03/2015.
 */
@Component
public class SharepointSearchAdapter implements SearchAdapter {

    private static final Logger logger = LoggerFactory.getLogger(SharepointSearchAdapter.class);

    @Override
    public String getName() {
        return "Sharepoint";
    }

    @Override
    public void peformSearch(final SearchQuery searchQuery, final SearchResultContainer searchResultContainer) throws IOException {

        final Document doc = Jsoup.connect(
               "https://sharepoint.guidewire.com/searchcenter/pages/Results.aspx?k=" + searchQuery.getQuery() + "&s=All%20Sites&start1=1").get();
                logger.info("Conor");



        final Elements results = doc.getElementsByClass("srch-Title3");
        // Content is not contained in the same tags as Title & Link
        final Elements content = doc.getElementsByClass("srch-Description2");

        for (final Element result : results) {
            int index = results.indexOf(result);

            if (result.select("a").size() == 0) {
                continue;
            }

            try {
                final SearchResult searchResult = new SearchResult();
                searchResult.setSource(this.getName());
                searchResult.setHref(result.select("a").first().attr("href"));
                searchResult.setTitle(result.select("a").first().attr("title"));
                searchResult.setContent(content.get(index).toString());
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
