package com.baizhi.service.impl;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public Map selectAllUser(int page, int rows, User user) {

        Map map = new HashMap<>();
        int start = (page - 1) * rows;//每页的起始条
        List<User> users = userDao.selectAllUser(start, rows, user);
//        for (Carousel carousel : carousels) {
//            System.out.println(carousel);
//        }
//        int total = carouselDao.selectCarouselCount();
//        map.put("total", total);
        map.put("rows", users);
        return map;
    }
}
