package com.gsc.federator.services.impl;

import com.gsc.federator.model.SummaryRequest;
import com.gsc.federator.model.SummaryResult;
import com.gsc.federator.search.SearchAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Set;

/**
 * User: msaccotelli
 * Date: 10/13/2014
 */
@Service
public class SummaryService implements com.gsc.federator.services.SummaryService {
    private static final Logger logger = LoggerFactory.getLogger(SummaryService.class);

    @Autowired
    private Set<SearchAdapter> searchAdapters;

    @Override
    public SummaryResult summarize(final SummaryRequest summaryRequest) {
        try {
            for (final SearchAdapter searchAdapter : searchAdapters) {
                if (searchAdapter.getName().equals(summaryRequest.getSource())) {

                    return searchAdapter.summarize(summaryRequest);
                }
            }
        } catch (IOException ex) {
            logger.error("", ex);
        }

        return null;
    }
}
