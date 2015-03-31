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
//@Component
public class KnowledgeBaseSearchAdapter implements SearchAdapter {

    private static final Logger logger = LoggerFactory.getLogger(KnowledgeBaseSearchAdapter.class);

    @Override
    public String getName() {
        return "KnowledgeBase";
    }

    @Override
    public void peformSearch(final SearchQuery searchQuery, final SearchResultContainer searchResultContainer) throws IOException {

        final Document doc = Jsoup.connect("https://guidewire.custhelp.com/app/answers/list/kw/" + searchQuery.getQuery() + "/search/1").
                cookie("cp_session", "bU8CFmiMGMoeoDT0VUHUSXXMzoMmlvG9GDStnmoWwTr7A%7EFZl1s0xIs9aIUxVeOXeu092gwX9%7EmvYvZBKMVDN59zyQwdVMtFW8wEod7u6SHlE6cv0YxmQ2%7EjlZLx0hcBb87psQ1XU_SdxtcIj5xVCzRO%7ESuaQYLzDMc8vF3VQWgzX1SyVw3vp6_ltAtnXtJB4K1YJMBajdl5AqqEalKIyrpXL7Jhu5VmPBbifegM7WB9Yy8bob0Cqx3bEH9DcUA8MFatePgbMeu8ZtonCbMmPgJ4Mfh8FyDN1j6kxLzbul5454pNEkHp733qQxZuW%7EO6nvjFmWhdUAtsw%21").
                cookie("cp_profile", "bU9PmSw_CAntuiNPBSiDfu17YSEqodafdMf7xpW7lwRSzdQH9dLWvep8uUNLi%7EHFyCMYXkuz_Ou0xjs2yCnbVeV8ZrTXhZYLcs7njJPNZPeXaTe6_REzPPrGYBbQK8PFFyiXIDzWNdNHNhSvjkMCU918Kw_eWeUkZJS0y5ogSDDFhqq4AOBsW1eM6tFXB__bnLczl8B5VOCRR38aSrtSoh7NoPIxSDfyBUJ0I1kd3H1W%7E7WCN3ZKyu6b1FdIoxW_KV5x8dTMgNxJjyI6hDQbzTZXcySTSMEjoK").
                get();

        final Elements results = doc.select("div ol li");

        for (final Element result : results) {
              try {
                final SearchResult searchResult = new SearchResult();

                searchResult.setSource(this.getName());
                searchResult.setHref("https://guidewire.custhelp.com" + result.select("a").first().attr("href"));
                searchResult.setTitle(result.select("a").first().text());
                searchResult.setContent(result.select("span.rn_Element3").first().text());
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
