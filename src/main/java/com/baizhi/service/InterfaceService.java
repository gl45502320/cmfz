package com.baizhi.service;

import com.baizhi.entity.User;

import java.util.Map;

public interface InterfaceService {

    public Map showAllCarouse();

    public Map showAllAlbum();

    public Map showOneAlbum(int id);

    User loginOne(String phone);

    User logonOne(String phone);

    void logonOneUser(User user);

    User selectOneUserUpdate(int id);

    void updateOneUser(User user);
}
