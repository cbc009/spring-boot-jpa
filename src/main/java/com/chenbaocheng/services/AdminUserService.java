package com.chenbaocheng.services;

import com.chenbaocheng.domain.AdminUser;

/**
 * Created by CBC on 2015-04-16 17:35.
 */
public interface AdminUserService {

    /**
     * 根据用户名查找
     *
     * @param userName
     * @return
     */
    AdminUser findByUserName(String userName);


    /**
     * 修改用户信息
     *
     * @param adminUser
     * @return
     */
    boolean save(AdminUser adminUser);
}
