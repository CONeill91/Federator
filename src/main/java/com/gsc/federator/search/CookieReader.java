package com.gsc.federator.search;

import com.gsc.federator.model.Cookie;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CookieReader {
    List<Cookie> readCookies(SearchAdapter searchAdapter);
}
