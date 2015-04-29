package com.chenbaocheng.controller.manager;

import com.alibaba.fastjson.JSONObject;
import com.chenbaocheng.consts.Consts;
import com.chenbaocheng.controller.BaseController;
import com.chenbaocheng.domain.AdminUser;
import com.chenbaocheng.services.AdminUserService;
import com.chenbaocheng.utils.MD5Util;
import com.chenbaocheng.utils.ResultUtil;
import com.chenbaocheng.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by CBC on 2015-04-11 15:21.
 */
@Controller
@RequestMapping(Consts.MANAGER_URI_PREFIX + "admin/")
public class AdminUserController extends BaseController {

    @Autowired
    private AdminUserService adminUserService;

    /**
     * 修改用户信息
     *
     * @return
     */
    @RequestMapping("edit")
    public ModelAndView edit(HttpServletRequest request) {
        AdminUser adminUser = this.getAdminUser(request);
        ModelAndView mav = new ModelAndView("manager/admin/edit");
        mav.addObject("adminUser", adminUser);
        return mav;
    }

    /**
     * 保存用户信息
     *
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("save")
    public JSONObject save(HttpServletRequest request, @RequestParam String userName
            , @RequestParam String oldPassword
            , @RequestParam String newPassword
            , @RequestParam String verifyPassword
    ) {
        AdminUser sessionAdminUser = this.getAdminUser(request);

        if (!userName.equals(sessionAdminUser.getUserName())) {
            return ResultUtil.fail("用户名错误.");
        }

        String oldPasswordMd5 = MD5Util.md5(oldPassword);
        if (!oldPasswordMd5.equals(sessionAdminUser.getPassword())) {
            return ResultUtil.fail("当前密码错误.");
        }

        if (!newPassword.equals(verifyPassword)) {
            return ResultUtil.fail("两次密码输入不一致.");
        }

        sessionAdminUser.setPassword(MD5Util.md5(newPassword));

        boolean isOK = adminUserService.save(sessionAdminUser);

        //退出登陆
        SessionUtil.setValue(request, Consts.ADMIN_USER_SESSION_ID, null);

        return isOK ? ResultUtil.ok("保存成功.") : ResultUtil.fail("保存失败.");
    }
}
