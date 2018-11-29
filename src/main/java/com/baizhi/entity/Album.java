package com.baizhi.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Album {
    private Integer id;

    private String title;

    private String coverimg;

    private Integer count;

    private Integer score;

    private String author;

    private String broadcast;

    private String brief;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //将String转化为日期格式
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")          //将日期格式转化为String
    private Date publishdate;

    private List<Audio> children;

}