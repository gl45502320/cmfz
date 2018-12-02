package com.baizhi.service.impl;

import com.baizhi.dao.InterfaceDao;
import com.baizhi.entity.Album;
import com.baizhi.entity.Audio;
import com.baizhi.entity.Carousel;
import com.baizhi.entity.User;
import com.baizhi.service.InterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InterfaceServiceImpl implements InterfaceService {


    @Autowired
    private InterfaceDao interfaceDao;

    @Override
    public Map showAllCarouse() {
        Map map = new HashMap();
        List<Carousel> carousels = interfaceDao.showAllCarouse();
        List<Album> albums = interfaceDao.showAllAlbum();
        map.put("header", carousels);
        map.put("body", albums);
        return map;
    }

    @Override
    public Map showAllAlbum() {
        Map map = new HashMap();
        List<Album> albums = interfaceDao.showAllAlbum();
        map.put("body", albums);
        return map;
    }

    @Override
    public Map showOneAlbum(int id) {
        Map map = new HashMap();
        List<Album> albums = interfaceDao.showOneAlbum(id);
        List<Audio> audio = interfaceDao.showOneAlbumAllAudio(id);
        map.put("introduction", albums);
        map.put("list", audio);
        return map;
    }

    @Override
    public User loginOne(String phone) {
        return interfaceDao.loginOne(phone);
    }

    @Override
    public User logonOne(String phone) {
        return interfaceDao.loginOne(phone);
    }

    @Override
    public void logonOneUser(User user) {
        interfaceDao.logonOneUser(user);
    }

    @Override
    public User selectOneUserUpdate(int id) {

        return interfaceDao.selectOneUserUpdate(id);
    }

    @Override
    public void updateOneUser(User user) {
        interfaceDao.updateOneUser(user);
    }
}
