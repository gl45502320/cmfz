package com.baizhi.dao;

import com.baizhi.entity.Carousel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CarouselDao {

    public List<Carousel> selectCarouselAll(@Param("start") int start, @Param("row") int row);

    public int selectCarouselCount();

    public void updateCarouselStatus(@Param("id") int id, @Param("status") int status);

    public void deleteOneCarousel(@Param("id") int id);

    public void addOneCarousel(@Param("carousel") Carousel carousel);


}
