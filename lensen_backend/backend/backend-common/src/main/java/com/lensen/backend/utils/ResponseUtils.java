package com.lensen.backend.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

public final class ResponseUtils {
    private final static String CONTENT_TYPE_HTML = "text/html";
    private final static String UTF8 = "UTF-8";

    /**
     * 设置Response的过期头.
     *
     * @param response  HttpServletResponse
     * @param seconds   秒数.
     * @param isPrivate 是否需要在代理服务器上缓存.
     */
    public static void setExpired(final HttpServletResponse response, final long seconds, final boolean isPrivate) {
        StringBuilder cacheControl = new StringBuilder(128);

        cacheControl.append(isPrivate ? "private" : "public");
        cacheControl.append(",max-age=").append(seconds);

        // 设置头信息
        long nowMs = System.currentTimeMillis();
        //response.setDateHeader("Last-Modified",nowMs);
        response.setHeader("Cache-Control", cacheControl.toString());
        response.setDateHeader("Expires", nowMs + seconds * 1000);
    }

    public static void setLastModified(final HttpServletResponse response, final long lastModified) {
        response.setDateHeader("Last-Modified", lastModified);
    }

    /**
     * 默认构造函数.
     */
    private ResponseUtils() {
        // 工具类不可实例化.
    }

    public static void toJson(Object obj, HttpServletResponse response) throws IOException {
        toJson(obj, response, null);
    }

    public static void toJson(Object obj, HttpServletResponse response, String[] fields) throws IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType(CONTENT_TYPE_HTML + "; charset=" + UTF8);
        print(JsonUtils.toString(null, obj, fields), response);
    }

    public static void toJson(String rootName, Object obj, HttpServletResponse response) throws IOException {
        response.setContentType(CONTENT_TYPE_HTML + "; charset=" + UTF8);
        print(JsonUtils.toString(rootName, obj), response);
    }

    private static void print(String content, HttpServletResponse response) throws IOException {
        Writer out = response.getWriter();
        out.write(content);
    }
}
