package com.lensen.backend.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Description
 * @Author Yang Cheng
 * @Date: Feb 9, 2012 2:03:44 AM
 * @Version
 */
public class ServletUtils {

    /**
     * @return String
     * @Description: 获取根目录
     * @Author Yang Cheng
     * @Date: Feb 9, 2012 2:07:30 AM
     */
    public static String serverRootDirectory() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getRealPath(File.separator);
    }

    /**
     * @return String
     * @Description: 获取请求地址
     * @Author Yang Cheng
     * @Date: Feb 9, 2012 2:08:15 AM
     */
    public static String serverUrl() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attr.getRequest();
        String temp = request.getServerPort() == 80 ? "" : ":" + request.getServerPort();
        return request.getScheme() + "://" + request.getServerName() + temp + request.getContextPath() + "/";
    }

    public static String serverUrl1() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attr.getRequest();
        String temp = request.getServerPort() == 80 ? "" : ":" + request.getServerPort();
        return request.getScheme() + "://" + request.getServerName() + temp + request.getContextPath() + "/";
    }

    /**
     * 获取请求地址 不要项目名称
     *
     * @return
     */
    public static String getServerUrl() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attr.getRequest();
        String temp = request.getServerPort() == 80 ? "" : ":" + request.getServerPort();
        return request.getScheme() + "://" + request.getServerName() + temp;
    }

    /**
     * @param request
     * @return String
     * @Description: 获取当前请求的URL地址域参数
     * @Author Yang Cheng
     * @Date: Feb 9, 2012 2:10:02 AM
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> getPrams(HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }
        return params;
    }

    public static String getRemortIp() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attr.getRequest();
        if (request.getHeader("x-forwarded-for") == null) {
            return request.getRemoteAddr();
        }
        return request.getHeader("x-forwarded-for");
    }

    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        String localIP = "127.0.0.1";
        if ((ip == null) || (ip.length() == 0) || (ip.equalsIgnoreCase(localIP)) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if ((ip == null) || (ip.length() == 0) || (ip.equalsIgnoreCase(localIP)) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if ((ip == null) || (ip.length() == 0) || (ip.equalsIgnoreCase(localIP)) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
