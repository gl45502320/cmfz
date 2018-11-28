package com.baizhi.service;


import com.baizhi.entity.Carousel;

import java.util.Map;

public interface CarouselService {
    public Map selectCarouselAll(int page, int rows);

    public boolean updateCarouselStatus(int id, int status);

    public boolean deleteOneCarousel(int id);

    public boolean addOneCarousel(Carousel carousel);
}
