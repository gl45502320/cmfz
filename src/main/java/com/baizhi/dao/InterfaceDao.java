package com.baizhi.dao;

import com.baizhi.entity.Album;
import com.baizhi.entity.Audio;
import com.baizhi.entity.Carousel;
import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InterfaceDao {
    List<Carousel> showAllCarouse();

    List<Album> showAllAlbum();

    List<Album> showOneAlbum(@Param("id") int id);

    List<Audio> showOneAlbumAllAudio(@Param("id") int id);

    User loginOne(@Param("phone") String phone);

    void logonOneUser(User user);

    User selectOneUserUpdate(@Param("id") int id);

    void updateOneUser(User user);
}
