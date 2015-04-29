package com.chenbaocheng.services.impl;

import com.chenbaocheng.dao.AdminUserDao;
import com.chenbaocheng.domain.AdminUser;
import com.chenbaocheng.services.AdminUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by CBC on 2015-04-16 17:37.
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {

    private static final Logger logger = LoggerFactory.getLogger(AdminUserServiceImpl.class);

    @Autowired
    private AdminUserDao adminUserDao;

    /**
     * 根据用户名查找
     *
     * @param userName
     * @return
     */
    @Override
    public AdminUser findByUserName(String userName) {
        return adminUserDao.findByUserName(userName);
    }

    @Override
    public boolean save(AdminUser adminUser) {

        try {
            adminUserDao.save(adminUser);
            return true;
        } catch (Exception e) {
            logger.error("===> error : {}", e.getMessage());
            return false;
        }

    }
}
