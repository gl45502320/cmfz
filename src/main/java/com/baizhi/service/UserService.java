package com.baizhi.service;

import com.baizhi.entity.User;

import java.util.Map;

public interface UserService {
    public Map selectAllUser(int page, int rows, User user);
}
