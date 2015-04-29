package com.chenbaocheng.dao;

import com.chenbaocheng.domain.AdminUser;

/**
 * Created by CBC on 2015-04-16 17:39.
 */
public interface AdminUserDao extends BaseDao<AdminUser, Integer> {
    AdminUser findByUserName(String userName);
}
