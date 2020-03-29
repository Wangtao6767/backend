package com.lensen.backend.utils;

import com.lensen.backend.constants.IConstants;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class ManagerContext {

    public final static String LOGIN_USER = "loginUser";

    public static LoginManager getLoginUser() {
        try {
            RequestAttributes ra = RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = ((ServletRequestAttributes) ra).getRequest();
            return (LoginManager) request.getSession().getAttribute(IConstants.SESSION_ADMIN);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static String getServerURL() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) ra).getRequest();
        return request.getRequestURI();
    }

    public static void saveLoginManager(LoginManager loginUser) {
        if (null != loginUser) {
            RequestAttributes ra = RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = ((ServletRequestAttributes) ra).getRequest();
            request.getSession().setAttribute(IConstants.SESSION_ADMIN, loginUser);
        }
    }

    /**
     * 获取验证码
     *
     * @return
     */
    public static String getCheckCode() {
        try {
            RequestAttributes ra = RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = ((ServletRequestAttributes) ra).getRequest();
            return (String) request.getSession().getAttribute(IConstants.SESSION_CHECK_CODE);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * 保存验证码
     *
     * @param checkCode
     */
    public static void saveCheckCode(String checkCode) {
        if (null != checkCode) {
            RequestAttributes ra = RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = ((ServletRequestAttributes) ra).getRequest();
            request.getSession().setAttribute(IConstants.SESSION_CHECK_CODE, checkCode);
        }
    }

    public static void clearSession() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) ra).getRequest();
        request.getSession().invalidate();
    }
}
