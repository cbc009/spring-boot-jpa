package com.chenbaocheng.controller.manager;

import com.alibaba.fastjson.JSONObject;
import com.chenbaocheng.consts.Consts;
import com.chenbaocheng.controller.BaseController;
import com.chenbaocheng.domain.AdminUser;
import com.chenbaocheng.services.AdminUserService;
import com.chenbaocheng.utils.IPUtil;
import com.chenbaocheng.utils.MD5Util;
import com.chenbaocheng.utils.ResultUtil;
import com.chenbaocheng.utils.SessionUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by CBC on 2015-04-11 15:21.
 */
@Controller
@RequestMapping(Consts.MANAGER_URI_PREFIX)
public class LoginController extends BaseController {

    @Value("${weixin.app.id}")
    private String weixinAppId;


    @Autowired
    private AdminUserService adminUserService;

    @RequestMapping("login")
    public ModelAndView login() {
        return new ModelAndView("manager/admin/login");
    }

    @ResponseBody
    @RequestMapping(value = "loginAjax", method = RequestMethod.POST)
    public JSONObject loginAjax(HttpServletRequest request,
                                HttpServletResponse response,
                                @RequestParam String userName,
                                @RequestParam String password,
                                @RequestParam String verifyCode
    ) {
        if (StringUtils.isBlank(verifyCode)) {
            return ResultUtil.fail("验证码错误");
        }

        String verifyCodeRe = (String) SessionUtil.getValue(request, Consts.CAPTCHA_SESSION_ID);
        logger.info("===> verifyCode : {}, verifyCodeRe : {}", verifyCode, verifyCodeRe);

        if (!verifyCode.equalsIgnoreCase(verifyCodeRe)) {
            return ResultUtil.fail("验证码错误");
        }

        AdminUser adminUser = adminUserService.findByUserName(userName);
        logger.info("===>adminUser:{}", adminUser);
        if (adminUser == null || !adminUser.getPassword().equals(MD5Util.md5(password))) {
            return ResultUtil.fail("用户名或密码错误");
        }

        long ip = IPUtil.toLong(IPUtil.ip(request));
        adminUser.setLastLoginIp(ip);
        boolean isOk = adminUserService.save(adminUser);

        SessionUtil.setValue(request, Consts.ADMIN_USER_SESSION_ID, adminUser);

        return ResultUtil.ok("login success.");
    }

    @RequestMapping("logout")
    public ModelAndView logout(HttpServletRequest request) {
        SessionUtil.setValue(request, Consts.ADMIN_USER_SESSION_ID, null);
        return new ModelAndView(new RedirectView(Consts.MANAGER_URI_PREFIX + "login"));
    }
}