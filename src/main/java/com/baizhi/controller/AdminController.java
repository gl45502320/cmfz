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
        if (kaptcha.equalsIgnoreCase(code)) {
            boolean bool = adminService.login(admin);
            if (bool) {
                return "forward:/main/main.jsp";
            } else {

                return "forward:/error.jsp";
            }

        } else {
            return "forward:/error.jsp";
        }
    }

}
