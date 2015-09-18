package com.gsc.federator.elasticsearch;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * User: msaccotelli
 * Date: 1/28/2015
 */
public class IndexApp {

    private static final Logger logger = LoggerFactory.getLogger(IndexApp.class);

    public static void main(final String[] args) {
        //final String fName = args[0];
        //final String fName = "C:\\Users\\msaccotelli\\Downloads\\isl99201.pdf";

        final String fDir = "c:\\Users\\coneill\\Documents\\Haskell";

        for (final File f : FileUtils.listFiles(new File(fDir),
                new String[]{"doc", "docx", "pdf", "ppt", "pptx", "xls", "xlsx","txt"}, true)) {
            try {
                String postData = "{ \"file\": \"" +
                        Base64.encodeBase64String(FileUtils.readFileToByteArray(f)) + "\" }";

                final PostCommand postCommand = new PostCommand();
                final PostCommandContainer postCommandContainer = new PostCommandContainer();


                postCommandContainer.setUrl("http://localhost:9200/test/attachment/");
                postCommandContainer.setData(postData);

                final String response = postCommand.postData(postCommandContainer);

                logger.info("Got {}", response);

            } catch (Exception e) {
                logger.error("", e);
            }
        }
    }

}
