package com.baizhi.service;

import com.baizhi.entity.User;
import com.baizhi.entity.UserFieldsDTO;

import java.util.List;
import java.util.Map;

public interface UserService {
    public Map selectAllUser(int page, int rows, User user);

    public Map selectWeekOneLogonUser();

    public Map listAllMan();

    public Map listAllWoman();

    public List<User> exportAllUser();

    public List<UserFieldsDTO> selectAllfields();
}
