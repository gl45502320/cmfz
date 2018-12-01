package com.baizhi.service.impl;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import com.baizhi.entity.UserDTO;
import com.baizhi.entity.UserFieldsDTO;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public Map selectWeekOneLogonUser() {
        Map map = new HashMap<>();
        int one = userDao.selectWeekOneLogonUser();
        int two = userDao.selectWeekTwoLogonUser();
        int three = userDao.selectWeekThreeLogonUser();
        int four = userDao.selectWeekFourLogonUser();
        int five = userDao.selectWeekFiveLogonUser();
        List<Integer> list = new ArrayList();
        list.add(one);
        list.add(two);
        list.add(three);
        list.add(four);
        list.add(five);
        for (Integer d : list) {
            System.out.println("d  -->" + d);
        }


        map.put("date", list);
        return map;
    }

    @Override
    public Map listAllMan() {
        Map map = new HashMap<>();
        List<UserDTO> man = userDao.listAllMan();
        List<UserDTO> woman = userDao.listAllWoman();
        map.put("data", man);
        map.put("date", woman);

        return map;
    }

    @Override
    public Map listAllWoman() {
        Map map = new HashMap<>();
        List<UserDTO> woman = userDao.listAllWoman();
        map.put("data", woman);
        return map;
    }

    @Override
    public List<User> exportAllUser() {
        return userDao.exportAllUser();
    }

    @Override
    public List<UserFieldsDTO> selectAllfields() {
        return userDao.selectAllfields();
    }
}
