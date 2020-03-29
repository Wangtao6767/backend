package com.lensen.backend.interceptor;

import com.lensen.backend.constants.IConstants;
import com.lensen.backend.utils.LoginManager;
import com.lensen.backend.utils.ManagerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户登录的拦截器.
 */
public final class AuthHeaderInterceptor extends HandlerInterceptorAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthHeaderInterceptor.class);

    /**
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @param handler  Object
     * @return 是否通过
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String contextPath = request.getContextPath();
        String path = request.getServletPath();
        if (path.matches(IConstants.NOT_INTERCEPT_PATH)) {
            return true;
        }

        // 如果未登录
        LoginManager loginUser = ManagerContext.getLoginUser();
        if (loginUser == null) {
            // 跳转到登录页面
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + contextPath + "/";
            response.getWriter().write("<script>window.parent.location.href = '" + basePath + "login';</script>");
            return false;
        }
        return true;
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Object object, Exception exception) {

    }
}
