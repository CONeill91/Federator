package com.gsc.federator.search.impl;

/**
 * Created by coneill on 11/09/2015.
 */


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.gsc.federator.model.*;
import com.gsc.federator.search.SearchAdapter;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;


import jdk.nashorn.internal.ir.RuntimeNode;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.io.Charsets;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.util.Base64;

@Component
public class ElasticSearchAdapter implements SearchAdapter {

    private static final Logger logger = LoggerFactory.getLogger(ElasticSearchAdapter.class);

    @Override
    public String getName() {
        return "ElasticSearch";
    }

    @Override
    public void performSearch(SearchQuery searchQuery, SearchResultContainer searchResultContainer) throws IOException {
        // Connect to URL
        String url = "http://localhost:9200/test/_search?-q=attachment:" + searchQuery.getQuery();
        HttpClient client = new HttpClient();

        GetMethod get = new GetMethod(url);

        // Provide custom retry handler is necessary
        get.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler(3, false));


        try {
            // Execute the method.
            int statusCode = client.executeMethod(get);

            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: " + get.getStatusLine());
            }

            // Read the response body.
            byte[] responseBody = get.getResponseBody();

            // Deal with the response.
            // Use caution: ensure correct character encoding and is not binary data
            JsonParser parser = new JsonParser();
            JsonObject obj = parser.parse(new String(responseBody, Charsets.UTF_8)).getAsJsonObject();
            logger.info("Took " + obj.get("took"));

        } catch (HttpException e) {
            System.err.println("Fatal protocol violation: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Fatal transport error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Release the connection.
            get.releaseConnection();
        }







/*
        byte [] decoded = Base64.getDecoder().decode(json);
        final SearchResult searchResult = new SearchResult();
        searchResult.setTitle(decoded.toString());
        searchResult.setHref("N/A");
        searchResult.setSource(this.getName());
        searchResult.setContent(decoded.toString());
        searchResultContainer.addSearchResult(searchResult);*/

    }



    @Override
    public SummaryResult summarize(SummaryRequest summaryRequest) throws IOException {
        return null;
    }


}
