package com.baizhi.service.impl;

import com.baizhi.dao.AlbumDao;
import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumDao albumDao;

    @Override
    public Map listAllAlbum(int page, int rows) {
        System.out.println("page  " + page);
        System.out.println("rows  " + rows);
        Map map = new HashMap<>();
        int starts = (page - 1) * rows;//每页的起始条
        List<Album> albums = albumDao.listAllAlbum(starts, rows);

        for (Album carousel : albums) {
            System.out.println("list  --> " + carousel);
        }
        int total = albumDao.countAlbum();
        map.put("total", total);
        map.put("rows", albums);
        return map;
    }
}
