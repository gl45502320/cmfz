package com.baizhi.service.impl;

import com.baizhi.dao.MenuDao;
import com.baizhi.entity.Menu;
import com.baizhi.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Override
    public Map selectAll() {
        Map map = new HashMap();
        List<Menu> menus = menuDao.selectAll();
        for (Menu menu : menus) {
            System.out.println(menu);
        }
        map.put("menus", menus);
        return map;
    }
}
