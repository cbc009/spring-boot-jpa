package com.chenbaocheng.utils;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.Map;

/**
 * Created by CBC on 14/9/3.
 * <p/>
 * {
 * "result" : 0 | 1,
 * "message" : "",
 * }
 */
public final class ResultUtil {

    private ResultUtil() {
    }

    public static JSONObject ok(String message) {
        return result(ResultCode.SUCCESS, message, "", Collections.EMPTY_MAP);
    }

    public static JSONObject ok(String message, String url) {
        return result(ResultCode.SUCCESS, message, url, Collections.EMPTY_MAP);
    }

    public static JSONObject fail(String message) {
        Map<String, Object> map = Maps.newHashMap();
        return result(ResultCode.FAIL, message, "", map);
    }

    public static JSONObject fail(String message, String url) {
        Map<String, Object> map = Maps.newHashMap();
        return result(ResultCode.FAIL, message, url, map);
    }

    public static JSONObject result(ResultCode statusCode, String message, String nextUrl, Map<String, Object> data) {
        JSONObject result = new JSONObject();
        result.put("code", statusCode.getCode());
        if (StringUtils.isNotBlank(message)) {
            result.put("message", message);
        }
        if (StringUtils.isNotBlank(nextUrl)) {
            result.put("nextUrl", nextUrl);
        }
        if (data != null) {
            result.putAll(data);
        }
        return result;
    }
}
