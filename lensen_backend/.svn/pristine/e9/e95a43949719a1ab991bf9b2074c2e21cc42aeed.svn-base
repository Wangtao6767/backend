package com.lensen.backend.interceptor;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * 过滤特殊字符的拦截器.
 */
public final class InjectionInterceptor extends HandlerInterceptorAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(InjectionInterceptor.class);

    /**
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @param handler  Object
     * @return 是否通过
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String[] values = request.getParameterValues(name);
            for (String value : values) {
                if (StringUtils.isNotBlank(value)) {
                    String replaceValue = this.replaceTag(value);
                    if (!value.equals(replaceValue)) {
                        LOGGER.error("发现特殊字符，有可能被注入攻击！clineIP为：" + this.getClientIP(request) + ", 过滤后的字符为：" + replaceValue);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object, Exception exception)
            throws Exception {

    }

    /**
     * 基本功能：替换标记以正常显示
     *
     * @param input
     * @return String
     */
    private String replaceTag(String input) {
        StringBuilder filtered = new StringBuilder(input.length());
        char c;
        for (int i = 0; i <= input.length() - 1; i++) {
            c = input.charAt(i);
            switch (c) {
                case '<':
                    filtered.append("&lt;");
                    break;
                case '>':
                    filtered.append("&gt;");
                    break;
                case '"':
                    filtered.append("&quot;");
                    break;
                case '\'':
                    filtered.append("&#39;");
                    break;
//			case '|':
//				filtered.append("&#124;");
//				break;
                /*
                 * case '(': filtered.append("&#40;"); break; case ')':
                 * filtered.append("&#41;"); break;
                 */
                case '\\':
                    filtered.append("&#92;");
                    break;
//			case '&':
//				filtered.append("&amp;");
//				break;
//			case '/':
//				filtered.append("&frasl;");
//				break;
                default:
                    filtered.append(c);
            }

        }
        return filtered.toString();
    }

    private String getClientIP(HttpServletRequest request) {
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
}
