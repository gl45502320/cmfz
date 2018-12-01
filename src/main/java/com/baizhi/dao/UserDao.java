package com.baizhi.dao;

import com.baizhi.entity.User;
import com.baizhi.entity.UserDTO;
import com.baizhi.entity.UserFieldsDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> selectAllUser(@Param("start") int start, @Param("row") int row, @Param("user") User user);

    int selectWeekOneLogonUser();

    int selectWeekTwoLogonUser();

    int selectWeekThreeLogonUser();

    int selectWeekFourLogonUser();

    int selectWeekFiveLogonUser();

    List<UserDTO> listAllMan();

    List<UserDTO> listAllWoman();


    List<User> exportAllUser();

    List<UserFieldsDTO> selectAllfields();



}