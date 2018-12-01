package com.baizhi.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private Integer id;

    private String phonenum;

    private String username;

    private String password;

    private String salt;

    private String dharmname;

    private String province;

    private String city;

    private String sex;

    private String sign;

    private String headpic;

    private Integer status;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //将String转化为日期格式
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")          //将日期格式转化为String
    private Date date;


}