package com.chenbaocheng.interceptors;

import com.chenbaocheng.consts.Consts;
import com.chenbaocheng.domain.AdminUser;
import com.chenbaocheng.utils.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by CBC on 2015-04-12 20:44.
 */
public class AdminInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(AdminInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        AdminUser adminUser = (AdminUser) SessionUtil.getValue(request, Consts.ADMIN_USER_SESSION_ID);
        logger.info("===>adminUser : {}", adminUser == null ? null : adminUser.getUserName());
        if (adminUser == null) {
            logger.info("===> need login.");
            response.sendRedirect(Consts.MANAGER_URI_PREFIX + "login");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}
