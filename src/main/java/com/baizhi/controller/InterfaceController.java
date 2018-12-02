package com.baizhi.controller;

import com.baizhi.entity.Hint;
import com.baizhi.entity.User;
import com.baizhi.service.InterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class InterfaceController {
    @Autowired
    private InterfaceService interfaceService;


    //1.一级页面接口
    @RequestMapping("/showAllData")
    @ResponseBody
    public Map showAllData(int uid, String type, String sub_type) {
        Map map = new HashMap();
        if (type.equals("all")) {
            return interfaceService.showAllCarouse();
        } else if (type.equals("wen")) {
            return interfaceService.showAllAlbum();
        } else if (type.equals("si")) {
            if (sub_type.equals("ssyj")) {

            } else if (sub_type.equals("xmfy")) {

            } else {
                map.put("error", "error");
                return map;
            }
        } else {
            map.put("error", "error");
            return map;
        }
        return null;
    }

    //    3.闻的详情页接口
    @RequestMapping("/showAlbum")
    @ResponseBody
    public Map showAlbum(int id, int uid) {
        Map map = new HashMap();
        if (id != 0) {
            return interfaceService.showOneAlbum(id);
        } else {
            map.put("error", "error");
            return map;
        }

    }

    //    4.登录接口
    @RequestMapping("/loginOne")
    @ResponseBody
    public Object login(String phone, String password, String code) {
        Hint hint = new Hint();
        if (code.equalsIgnoreCase("code")) {
            User user = interfaceService.loginOne(phone);
            if (user != null) {
                if (user.getPassword().equals(password)) {
                    return user;
                } else {
                    hint.setErrno("-300");
                    hint.setErrmsg("密码错误！");
                    return hint;
                }
            } else {
                hint.setErrno("-200");
                hint.setErrmsg("用户不存在，请确认账号！");
                return hint;
            }
        } else {
            hint.setErrno("-100");
            hint.setErrmsg("验证码错误，请重新输入！");
            return hint;
        }
    }

    //  5.注册接口
    @RequestMapping("/logonOne")
    @ResponseBody
    public Object logonOne(String phone, String password) {
        Hint hint = new Hint();
        if (phone != null && password != null) {
            User user = interfaceService.logonOne(phone);
            if (user != null) {
                hint.setErrno("-200");
                hint.setErrmsg("用户名已存在，请重新输入！");
                return hint;
            } else {
                User user1 = new User();
                user1.setPhonenum(phone);
                user1.setPassword(password);
                interfaceService.logonOneUser(user1);
                return user1;
            }
        }

        return null;
    }

    //    6.1修改个人信息（查询）
    @RequestMapping("/selectOneUserUpdate")
    @ResponseBody
    public Object selectOneUserUpdate(int id) {
        Hint hint = new Hint();
        if (id != 0) {
            User user = interfaceService.selectOneUserUpdate(id);
            if (user != null) {
                return user;
            } else {
                hint.setErrno("-500");
                hint.setErrmsg("用户id有误！");
                return hint;
            }
        } else {
            hint.setErrno("-500");
            hint.setErrmsg("id不能为空！");
            return hint;
        }
    }

    //    6.1修改个人信息（修改）
    @RequestMapping("/updateOneUser")
    @ResponseBody
    public Object updateOneUser(User user) {
        Hint hint = new Hint();
        if (user != null) {
            interfaceService.updateOneUser(user);
            hint.setErrno("000");
            hint.setErrmsg("修改成功！");
            return hint;
        } else {
            hint.setErrno("100");
            hint.setErrmsg("修改失败！");
            return hint;
        }
    }
}
