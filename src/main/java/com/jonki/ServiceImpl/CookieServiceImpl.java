package com.jonki.ServiceImpl;

import com.jonki.Service.CookieService;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class CookieServiceImpl implements CookieService {

    @Override
    public void addCookie(final HttpServletResponse response,
                          final String name,
                          final String value) {
        final Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(3600);
        response.addCookie(cookie);
    }

    @Override
    public void removeCookie(final HttpServletRequest request,
                             final HttpServletResponse response,
                             final String name) {
        for (final Cookie cookie : request.getCookies()) {
            if (cookie.getName().equalsIgnoreCase(name)) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
    }

    @Override
    public boolean isCookie(final HttpServletRequest request,
                            final String name) {
        try {
            for (final Cookie cookie : request.getCookies()) {
                if (cookie.getName().equalsIgnoreCase(name))
                    return true;
            }
        } catch (NullPointerException e) {
            return false;
        }

        return false;
    }

    @Override
    public String getValueCookie(final HttpServletRequest request,
                                 final String name) {
        try {
            for (final Cookie cookie : request.getCookies()) {
                if (cookie.getName().equalsIgnoreCase(name))
                    return cookie.getValue();
            }
        } catch (NullPointerException e) {
            return "";
        }

        return "";
    }
}
