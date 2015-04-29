package com.chenbaocheng.controller;

import com.chenbaocheng.consts.Consts;
import com.chenbaocheng.domain.AdminUser;
import com.chenbaocheng.utils.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by CBC on 2015-04-11 15:22.
 */
public abstract class BaseController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    public BaseController() {
    }

    /**
     * 获取管理员用户
     *
     * @param request
     * @return
     */
    protected AdminUser getAdminUser(HttpServletRequest request) {
        return (AdminUser) SessionUtil.getValue(request, Consts.ADMIN_USER_SESSION_ID);

    }
}
