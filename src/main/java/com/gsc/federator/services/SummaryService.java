package com.gsc.federator.services;

import com.gsc.federator.model.SummaryRequest;
import com.gsc.federator.model.SummaryResult;
import org.springframework.stereotype.Service;

/**
 * User: msaccotelli
 * Date: 10/13/2014
 */
@Service
public interface SummaryService {
    SummaryResult summarize(SummaryRequest summaryRequest);
}
