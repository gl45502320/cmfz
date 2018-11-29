package com.baizhi.controller;

import com.baizhi.entity.Carousel;
import com.baizhi.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.Map;

@Controller
public class CarouselController {

    @Autowired
    private CarouselService carouselService;

    @RequestMapping("/selectCarouselAll")
    @ResponseBody
    public Map selectCarouselAll(int page, int rows) {
        System.out.println("page  " + page);
        System.out.println("rows  " + rows);

        return carouselService.selectCarouselAll(page, rows);
    }

    @RequestMapping("/updateCarouselStatus")
    @ResponseBody
    public boolean updateCarouselStatus(int id, int status) {
        System.out.println("status  " + status);
        System.out.println("id  " + id);
        return carouselService.updateCarouselStatus(id, status);
    }

    @RequestMapping("/deleteOneCarousel")
    @ResponseBody
    public boolean deleteOneCarousel(int id) {
        System.out.println("id  " + id);
        return carouselService.deleteOneCarousel(id);
    }

    @RequestMapping("/addOneCarousel")
    @ResponseBody
    public boolean addOneCarousel(Carousel carousel, MultipartFile file, HttpServletRequest request) throws Exception {
        String Filename = file.getOriginalFilename();//获取文件名
        Long time = (new Date()).getTime();
        String[] split = Filename.split("\\.");

        String Filenames = time + Filename;
        String img = "image";
        String realPath1 = request.getRealPath(img);
        String realPath = request.getSession().getServletContext().getRealPath(img);

        file.transferTo(new File(realPath + "\\" + Filenames));
        carousel.setImgPath(Filenames);

        return carouselService.addOneCarousel(carousel);

    }
}
