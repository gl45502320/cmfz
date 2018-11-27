package com.baizhi.service.impl;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    public boolean login(Admin admin) {
        boolean bool = false;
        Admin admins = adminDao.login(admin);
        if (admins != null) {
            bool = true;
        } else {
            bool = false;
        }

        return bool;
    }
}
