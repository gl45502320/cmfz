package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("/login")
    public String login(Admin admin, String code, HttpSession session) {

        System.out.println("session" + session.getAttribute("Kaptcha"));
        System.out.println("code" + code);

        System.out.println("adminService" + adminService);
        System.out.println("admin" + admin);
        String kaptcha = (String) session.getAttribute("Kaptcha");
        //session中验证码为空跳转到登录页面
        if (kaptcha != null) {
            //判断验证码是否正确
            if (code != null && kaptcha.equalsIgnoreCase(code)) {
                Admin admins = adminService.login(admin);
                //判断用户名和密码是否正确
                if (admins != null) {
                    session.setAttribute("admins", admins);
                    return "forward:/main/main.jsp";
                } else {
                    return "forward:/error.jsp";
                }
            } else {
                return "forward:/error.jsp";
            }

        } else {
            return "forward:/login.jsp";
        }

    }

}
