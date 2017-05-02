package com.jonki.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CookieService {
    public void addCookie(final HttpServletResponse response,
                          final String name,
                          final String value);
    public void removeCookie(final HttpServletRequest request,
                             final HttpServletResponse response,
                             final String name);
    public boolean isCookie(final HttpServletRequest request,
                            final String name);
    public String getValueCookie(final HttpServletRequest request,
                                 final String name);
}
