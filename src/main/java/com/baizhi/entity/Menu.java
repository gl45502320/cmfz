package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
    private int id;
    private int parent_id;
    private String title;
    private String iconcls;
    private String url;
    private List<Menu> listManu;
}
