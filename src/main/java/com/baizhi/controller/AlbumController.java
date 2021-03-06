package com.baizhi.controller;

import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @RequestMapping("/listAllAlbum")
    @ResponseBody
    public Map listAllAlbum(int page, int rows) {
        System.out.println("page" + page);
        System.out.println("rows" + rows);
        return albumService.listAllAlbum(page, rows);
    }
}
