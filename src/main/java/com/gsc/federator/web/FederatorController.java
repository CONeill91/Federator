package com.gsc.federator.web;

import com.gsc.federator.domain.LinkClickedRecord;
import com.gsc.federator.domain.LinkClickedRepository;
import com.gsc.federator.domain.SearchQueryRecord;
import com.gsc.federator.domain.SearchQueryRepository;
import com.gsc.federator.model.SearchQuery;
import com.gsc.federator.model.SearchResultContainer;
import com.gsc.federator.model.SummaryRequest;
import com.gsc.federator.model.SummaryResult;
import com.gsc.federator.services.SearchService;
import com.gsc.federator.services.SummaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;

@RestController
public class FederatorController {

    private static final Logger logger = LoggerFactory.getLogger(FederatorController.class);

    @Autowired
    private SearchService searchService;

    @Autowired
    private SummaryService summaryService;

    //initialize the db objects
    @Autowired
    private SearchQueryRepository queryRepository;

    @Autowired
    private LinkClickedRepository linkRepository;


    @RequestMapping("/search")
    public SearchResultContainer search(
            @RequestBody SearchQuery searchQuery) {

        logger.info("The query data that was passed to this function is: "+searchQuery.getQuery()+"...");

        logger.info("Searching for [{}]", searchQuery);
        final SearchQueryRecord searchQueryRecord = new SearchQueryRecord();
        searchQueryRecord.setQuery(searchQuery.getQuery());

        queryRepository.save(searchQueryRecord);
        logger.info("Your search query was stored in the db!");

        //todo just for demo purpose prints out all values from the db
        for (final SearchQueryRecord sq : queryRepository.findAll()) {
            logger.info("From db {}", sq);
        }

        return searchService.peformSearch(searchQuery);
    }

    @RequestMapping("/summarize")
    public SummaryResult summarize(
            @RequestParam(value = "s", required = true) String source,
            @RequestParam(value = "u", required = true) String url) {

        final SummaryRequest summaryRequest = new SummaryRequest(source, url);

        return summaryService.summarize(summaryRequest);
    }
    @RequestMapping("/store")
    public void store(@RequestBody String[] queryData) {
        logger.info("The link that was entered is: "+queryData[0] +" The query that was enetered is: "+queryData[1]);

        // record in the database
        final LinkClickedRecord linkClickedRecord = new LinkClickedRecord();
        linkClickedRecord.setQueryId(queryData[1]);
        //searchQueryRecord.setDate(Calendar.getInstance().getTime()); // moved in constructor?
        linkClickedRecord.setLink(queryData[0]);

        linkRepository.save(linkClickedRecord);

        //todo just for demo purpose prints out all values from the db
        for (final LinkClickedRecord sq : linkRepository.findAll()) {
            logger.info("From db {}", sq);
        }



    }

}
