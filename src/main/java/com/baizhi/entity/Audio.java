package com.baizhi.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Audio {
    private String id;

    private Integer parentId;

    private String title;

    private String size;

    private String duration;

    private String downpath;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //将String转化为日期格式
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")          //将日期格式转化为String
    private Date uploaddate;

}