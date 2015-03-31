package com.gsc.federator.search.impl;


import com.gsc.federator.model.Cookie;
import com.gsc.federator.search.SearchAdapter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Component
public class CookieReader implements com.gsc.federator.search.CookieReader {
    private static final Logger logger = LoggerFactory.getLogger(CookieReader.class);
    private static final String basePath = "/opt/federator/";

    @Override
    public List<Cookie> readCookies(final SearchAdapter searchAdapter) {
        final File fCookies = new File(basePath, searchAdapter.getName() + ".cookies");
        final List<Cookie> cookies = new LinkedList<>();

        if (fCookies.exists() && fCookies.canRead()) {
            try {
                for (final String line : FileUtils.readLines(fCookies)) {
                    if (StringUtils.isNotBlank(line)) {
                        final String[] cookiesInLine = StringUtils.split(line, "=", 2);

                        if (cookiesInLine.length == 2) {
                            final Cookie cookie = new Cookie();
                            cookie.setName(cookiesInLine[0]);
                            cookie.setValue(cookiesInLine[1]);

                            cookies.add(cookie);
                        }
                    }
                }

            } catch (IOException e) {
                logger.error("Error reading file {}", fCookies.getAbsolutePath(), e);
            }
        } else{
            logger.warn("No such cookies file {}", fCookies.getAbsolutePath());
        }

        return cookies;
    }
}
