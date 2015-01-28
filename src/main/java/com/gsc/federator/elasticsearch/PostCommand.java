package com.gsc.federator.elasticsearch;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import java.io.IOException;

/**
 * User: msaccotelli
 * Date: 1/28/2015
 */
public class PostCommand {

    public String postData(final PostCommandContainer postCommandContainer) throws IOException {

        final PostMethod postMethod = new PostMethod(postCommandContainer.getUrl());

        try {

            final StringRequestEntity requestEntity = new StringRequestEntity(
                    postCommandContainer.getData(),
                    postCommandContainer.getContentType(),
                    postCommandContainer.getEncoding());

            postMethod.setRequestEntity(requestEntity);

            final HttpClient httpClient = new HttpClient();
            httpClient.executeMethod(postMethod);

            return postMethod.getResponseBodyAsString();
        } finally {
            postMethod.releaseConnection();
        }

    }
}
