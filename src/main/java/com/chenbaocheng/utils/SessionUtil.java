package com.chenbaocheng.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by CBC on 2015-04-16 21:49.
 */
public class SessionUtil {

    private static final Logger logger = LoggerFactory.getLogger(SessionUtil.class);

    public static Object getValue(HttpServletRequest request, String sessionId) {
        Object value = request.getSession(true).getAttribute(sessionId);
        logger.info("===> getValue - sessionId : {}, value : {}", sessionId, value);
        return value;
    }

    public static void setValue(HttpServletRequest request, String sessionId, Object value) {
        logger.info("===> setValue - sessionId : {}, value : {}", sessionId, value);
        request.getSession(true).setAttribute(sessionId, value);
    }
}
