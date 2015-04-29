package com.chenbaocheng.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class CookieUtil {

    private static final Logger logger = LoggerFactory.getLogger(CookieUtil.class);

    private CookieUtil() {
    }

    public static Cookie getCookie(HttpServletRequest request, String key) {
        if (request.getCookies() == null) {
            return null;
        }
        for (Cookie c : request.getCookies()) {
            if (c.getName().equals(key)) {
                return c;
            }
        }
        return null;
    }

    public static String getValue(HttpServletRequest request, String key) {
        Cookie cookie = getCookie(request, key);
        return cookie == null ? null : cookie.getValue();
    }

    public static boolean setCookie(HttpServletResponse response, String name, String value) {
        return setCookie(response, name, value);
    }

    public static boolean setCookie(HttpServletResponse response, String name, String value, String domain, int time) {
        try {
            Cookie cookie = new Cookie(name, value);
            cookie.setPath("/");
            if (!domain.equals("localhost")) {
                cookie.setDomain(domain);
            }
            if (time > 0) {
                cookie.setMaxAge(time);
            }
            response.addCookie(cookie);
            return true;
        } catch (Exception e) {
            logger.error("write cookie(" + name + ":" + value + ") error:" + e.getMessage());
            return false;
        }
    }

    public static void delCookie(HttpServletRequest request, HttpServletResponse response, String domain, String name) {
        Cookie cookie = null;
        if (request.getCookies() != null) {
            for (Cookie c : request.getCookies()) {
                if (c.getName().equals(name)) {
                    c.setValue(null);
                    c.setMaxAge(0);
                    if (!domain.equals("localhost")) {
                        c.setDomain(domain);
                    }
                    c.setPath("/");
                    cookie = c;
                }
            }
        }
        if (cookie != null) {
            response.addCookie(cookie);
        }
    }
}
