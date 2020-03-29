package com.lensen.backend.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public final class RequestUtils {
    private static final Logger log = LoggerFactory.getLogger(RequestUtils.class);

    private static String contextPathName = "contextPath";
    /**
     * 默认关键字命名.
     */
    public static final String GZIP = "__gzip";
    private List regexs;
    private List viewRegexList;

    public void setRegexs(List regexs) {
        this.regexs = regexs;
    }

    public void setViewRegexList(List viewRegexList) {
        this.viewRegexList = viewRegexList;
    }

    /**
     * 获取上下文路径.
     *
     * @param request HttpServletRequest.
     * @return 上下文路径.
     */
    public static String getContextPath(HttpServletRequest request) {
        String contextPath = (String) request.getAttribute(contextPathName);

        if (contextPath == null) {
            contextPath = request.getContextPath();
            if (contextPath != null && contextPath.length() > 0) {
                if ("/".equals(StringUtils.right(contextPath, 1))) {
                    contextPath = StringUtils.substringBeforeLast(contextPath, "/");
                }
            } else {
                contextPath = "";
            }
            request.setAttribute(contextPathName, contextPath);
        }

        return contextPath;
    }

    /**
     * 获取偏移量.
     *
     * @param s
     * @return 偏移量.
     */
    public static int[] getRange(String s) {
        int[] ranges = new int[2];
        ranges[0] = -1;
        ranges[1] = -1;

        s = StringUtils.trimToEmpty(s);
        s = StringUtils.removeStart(s, "bytes=");
        char[] cs = s.toCharArray();

        StringBuffer s1 = new StringBuffer();
        StringBuffer s2 = new StringBuffer();

        boolean first = true;
        if (cs != null && cs.length > 0) {
            for (int i = 0; i < cs.length; i++) {
                if ('-' == cs[i]) {
                    first = false;
                    continue;
                }

                if (first)
                    s1.append(cs[i]);
                else
                    s2.append(cs[i]);
            }
        }

        ranges[0] = NumberUtils.toInt(s1.toString(), -1);
        ranges[1] = NumberUtils.toInt(s2.toString(), -1);

        return ranges;
    }

    /**
     * 判断客户端浏览器是否支持GZip压缩.
     *
     * @param request HttpServletRequest.
     * @return 是否支持.
     */
    public static boolean isSupport(HttpServletRequest request) {

        // 手工设置不使用压缩
        String enableGzip = request.getParameter(GZIP);
        if ("false".equalsIgnoreCase(StringUtils.trimToEmpty(enableGzip))) {
            return false;
        }

        if (isIncluded(request)) {
            return false;
        }

        boolean support = false;
        String header = request.getHeader("Accept-Encoding");

        if (StringUtils.isNotBlank(header)) {
            header = header.toLowerCase(Locale.US);
            // 如果存在字符串gzip就表示支持
            support = header.indexOf("gzip") > -1;
        }

        return support;
    }

    /**
     * Checks if the request uri is an include.
     * These cannot be gzipped.
     *
     * @param request HttpServletRequest.
     * @return 是否是jsp include.
     */
    public static boolean isIncluded(final HttpServletRequest request) {
        final String uri = (String) request.getAttribute("javax.servlet.include.request_uri");
        final boolean includeRequest = !(uri == null);

        if (includeRequest && log.isDebugEnabled()) {
            log.debug(request.getRequestURL() + " resulted in an include request. This is unusable, because"
                    + "the response will be assembled into the overrall response. Not gzipping.");
        }
        return includeRequest;
    }

    /**
     * If a request is an ajax request
     *
     * @param request HttpServletRequest
     * @return
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String header = request.getHeader("X-Requested-With");
        return header != null && "XMLHttpRequest".equals(header);
    }

    /**
     * 获取客户端ip地址.
     *
     * @param request
     * @return ip地址.
     */
    public static String getClientIP(HttpServletRequest request) {
        String xForwardedFor = null;

        xForwardedFor = StringUtils.trimToNull(request.getHeader("$wsra"));
        if (xForwardedFor != null) {
            return xForwardedFor;
        }

        xForwardedFor = StringUtils.trimToNull(request.getHeader("X-Real-IP"));
        if (xForwardedFor != null) {
            return xForwardedFor;
        }

        xForwardedFor = StringUtils.trimToNull(request.getHeader("X-Forwarded-For"));
        if (xForwardedFor != null) {
            int spaceIndex = xForwardedFor.indexOf(',');
            if (spaceIndex > 0) {
                return xForwardedFor.substring(0, spaceIndex);
            } else {
                return xForwardedFor;
            }
        }

        return request.getRemoteAddr();
    }

    /**
     * Get start number from HttpServletRequest.
     *
     * @param _page page number .
     * @param num   record number per page.
     * @return record start number.
     */
    public static int getStartNum(int _page, int num) {
        // get page value from HttpServletRequest
        int page = _page;
        if (page < 1) {
            page = 1;
        }
        page--;
        return page * num;
    }

    /**
     * Get start number from HttpServletRequest.
     *
     * @param request       HttpServletRequest.
     * @param pageParamName page paramenter name in the request object.
     * @param num           record number per page.
     * @return record start number.
     */
    public static int getStartNum(HttpServletRequest request, String pageParamName, int num) {
        // get page value from HttpServletRequest
        int page = NumberUtils.toInt(StringUtils.trimToNull(request.getParameter(pageParamName)), 1);
        if (page < 1) {
            page = 1;
        }
        page--;
        return page * num;
    }


    /**
     * Get start number from HttpServletRequest.
     *
     * @param request HttpServletRequest.
     * @param num     record number per page.
     * @return record start number.
     */
    public static int getStartNum(HttpServletRequest request, int num) {
        return getStartNum(request, "page", num);
    }

    /**
     * Get start number from HttpServletRequest.
     *
     * @param request       HttpServletRequest.
     * @param pageParamName page paramenter name in the request object.
     * @return record start number.
     */
    public static int getStartNum(HttpServletRequest request, String pageParamName) {
        return getStartNum(request, pageParamName, 10);
    }

    /**
     * 判断是否是移动端请求.
     *
     * @param request
     * @return
     */
    public static boolean isMobileRequest(HttpServletRequest request) {
        /*Iterator it = this.viewRegexList.iterator();
        while (it.hasNext()) {
            String[] rs = (String[]) it.next();
            String headerName = rs[0];
            String regexStr = rs[1];
            String value = rs[2];

            String headerValue = StringUtils.trimToNull(request.getHeader(headerName));
            if (headerValue == null) {
                if (log.isDebugEnabled()) {
                    log.debug("header '{}' is null.", headerValue);
                }
                continue;
            }
            if (headerValue.matches(regexStr)) {
                if (log.isDebugEnabled()) {
                    log.debug("regex is match '{}', '{}'", headerName, value);
                }
                return true;
            }
        }*/
        return false;
    }

    public void afterPropertiesSet() throws Exception {
        if (regexs == null) {
            regexs = new ArrayList();
        }

        this.viewRegexList = new LinkedList();

        Iterator it = regexs.iterator();
        while (it.hasNext()) {
            String str = StringUtils.trimToNull((String) it.next());
            log.debug("regex '{}'", str);
            if (str == null) {
                log.error("regex is null");
                continue;
            }
            log.debug("view filter regex:{}", str);
            String[] rs = StringUtils.split(str, ":", 3);
            if (rs == null || rs.length != 3) {
                log.error("regex '{}' is bad format.", str);
                continue;
            }

            String headerName = StringUtils.trimToNull(rs[0]);
            String regexStr = StringUtils.trimToNull(rs[1]);
            String value = StringUtils.trimToNull(rs[2]);

            if (headerName == null || regexStr == null || value == null) {
                log.error("headerName==null || regexStr==null || value==null");
                continue;
            }

            if (value.indexOf("{}") < 0) {
                log.error("'{}' is bad format.", value);
                continue;
            }

            String[] result = new String[]{headerName, regexStr, value};
            this.viewRegexList.add(result);
        }

    }
}
