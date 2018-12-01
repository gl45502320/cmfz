package com.baizhi.controller;


import com.baizhi.entity.User;
import com.baizhi.entity.UserFieldsDTO;
import com.baizhi.service.UserService;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/selectAllUser")
    @ResponseBody
    public Map selectAllUser(int page, int rows, User user) {


        return userService.selectAllUser(page, rows, user);
    }

    @RequestMapping("/selectWeekOneLogonUser")
    @ResponseBody
    public Map selectWeekOneLogonUser() {

        return userService.selectWeekOneLogonUser();
    }

    @RequestMapping("/listAllMan")
    @ResponseBody
    public Map listAllMan() {

        return userService.listAllMan();
    }

    @RequestMapping("/listAllWoman")
    @ResponseBody
    public Map listAllWoman() {
        return userService.listAllWoman();
    }


    @RequestMapping("/exportUserMessage")
    @ResponseBody
    public boolean exportUserMessage(HttpServletResponse response) {
        boolean bool = false;
        List<User> users = userService.exportAllUser();
        List<UserFieldsDTO> userFieldsDTOS = userService.selectAllfields();

        /**
         * 1.创建工作薄对象
         * 2.创建一张工作表
         * 3.写入标题栏数据
         * 4.填充用户数据
         * 5.文件输出到本地
         */
//        1.创建工作薄对象
        HSSFWorkbook workbook = new HSSFWorkbook();
//        2.创建一张工作表
        HSSFSheet userSheet = workbook.createSheet();
//            2.1设置样式
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setColor((short) 10);
        font.setFontName("楷体");
        cellStyle.setFont(font);
//            2.2设置日期格式
        HSSFCellStyle dateStyle = workbook.createCellStyle();
//            2.3创建日期格式化对象
        HSSFDataFormat dataFormat = workbook.createDataFormat();
        short format = dataFormat.getFormat("yyyy年mm月dd日");
        dateStyle.setDataFormat(format);


//        3.写入标题栏数据
        HSSFRow fields = userSheet.createRow(0);
        for (int i = 0; i < userFieldsDTOS.size(); i++) {
            UserFieldsDTO userFieldsDTO = userFieldsDTOS.get(i);//获取对象
            String alias = userFieldsDTO.getAlias();                //获取对象的属性
            HSSFCell cell = fields.createCell(i);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(alias);
        }
//        4.填充用户数据
        for (int i = 0; i < users.size(); i++) {
            HSSFRow row = userSheet.createRow(i + 1);
            User user = users.get(i);
//            1.获取类对象
            Class<? extends User> aClass = user.getClass();
//            2.获取属性名数组
            Field[] fields1 = aClass.getDeclaredFields();
            for (int j = 0; j < fields1.length; j++) {
                String name = fields1[j].getName();
                String getName = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
                System.out.println("getName--->" + getName);
                try {
                    Object invoke = aClass.getDeclaredMethod(getName, null).invoke(user, null);
                    if (invoke instanceof Date) {
                        System.out.println("userSheet.setColumnWidth(i, 22*256)--->" + j);
                        userSheet.setColumnWidth(j, 20 * 256);
                        HSSFCell cell = row.createCell(j);
                        cell.setCellStyle(dateStyle);
                        cell.setCellValue((Date) invoke);
                    } else if (invoke instanceof Integer) {
                        row.createCell(j).setCellValue((Integer) invoke);
                    } else {
                        row.createCell(j).setCellValue((String) invoke);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    bool = false;
                }
                System.out.println("  i-->" + i + ",    j-->  " + j);
            }
        }
        try {
            workbook.write(new FileOutputStream(new File("F://user.xls")));
            bool = true;
        } catch (IOException e) {
            e.printStackTrace();
            bool = false;
        }

        return bool;
    }
}
