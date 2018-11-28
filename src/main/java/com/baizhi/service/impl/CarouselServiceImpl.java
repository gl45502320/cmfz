package com.baizhi.service.impl;

import com.baizhi.dao.CarouselDao;
import com.baizhi.entity.Carousel;
import com.baizhi.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CarouselServiceImpl implements CarouselService {

    @Autowired
    private CarouselDao carouselDao;

    @Override
    public Map selectCarouselAll(int page, int rows) {
        Map map = new HashMap<>();
        int start = (page - 1) * rows;//每页的起始条
        List<Carousel> carousels = carouselDao.selectCarouselAll(start, rows);
        for (Carousel carousel : carousels) {
            System.out.println(carousel);
        }
        int total = carouselDao.selectCarouselCount();
        map.put("total", total);
        map.put("rows", carousels);
        return map;
    }

    @Override
    public boolean updateCarouselStatus(int id, int status) {
        boolean bool = false;
        try {

            carouselDao.updateCarouselStatus(id, status);
            bool = true;
        } catch (Exception e) {
            e.printStackTrace();
            bool = false;
        }
        return bool;
    }

    @Override
    public boolean deleteOneCarousel(int id) {
        boolean bool = false;
        try {
            carouselDao.deleteOneCarousel(id);
            bool = true;
        } catch (Exception e) {
            e.printStackTrace();
            bool = false;
        }
        return bool;
    }

    @Override
    public boolean addOneCarousel(Carousel carousel) {
        boolean bool = false;
        try {
            carouselDao.addOneCarousel(carousel);
            bool = true;
        } catch (Exception e) {
            e.printStackTrace();
            bool = false;
        }
        return bool;
    }
}
