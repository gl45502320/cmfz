package com.baizhi.controller;

import com.baizhi.entity.Audio;
import com.baizhi.service.AudioService;
import com.baizhi.util.FileUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

@Controller
public class AudioController {
    @Autowired
    private AudioService audioService;

    @RequestMapping("/addOneAudio")
    @ResponseBody
    public boolean addOneAudio(int id, Audio audio, MultipartFile file, HttpServletRequest request) {
        String realPath = request.getSession().getServletContext().getRealPath("/");
        File file1 = new File(realPath + "/audio");
        if (!file1.exists()) {
            file1.mkdir();
        }

        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        Long time = (new Date()).getTime();
        long size = file.getSize();
        double l = size / 1024.0 / 1024.0;//大小
        l = ((int) (l * 100)) * 0.01;
        String sizes = l + "M" + "";


        String Filenames = time + "." + extension; //名字


        System.out.println("父id" + id);
        System.out.println("标题：title" + audio.getTitle());
        System.out.println("大小：size l  " + l);
        System.out.println("路径：downPath Filenames  " + Filenames);

        try {
            file.transferTo(new File(file1.getAbsolutePath(), Filenames));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        double duration = FileUtil.getDuration(file1);
        File file2 = new File(file1 + "/" + Filenames);
        long lo = FileUtil.getDuration(file2);
        String duration = (((int) (lo / 60)) + (((double) (lo % 60)) / 100)) + "";

//        System.out.println("时长：duration   "+duration+"");

        audio.setParentId(id);
        audio.setSize(sizes);
        audio.setDownpath(Filenames);
        audio.setDuration(duration);
        return audioService.addOneAudio(audio);
    }

    @RequestMapping("/downloadFile")
    public void downloadFile(Audio audio, HttpServletRequest request, HttpServletResponse response) {
        String realPath = request.getSession().getServletContext().getRealPath("audio");
        String path = realPath + "/" + audio.getDownpath();
        File file = new File(path);


        String name = file.getName();
        int i = name.lastIndexOf(".");
        int s = name.lastIndexOf("3") + 1;
        System.out.println("i > " + i);
        System.out.println("s > " + s);
        String substring = name.substring(i, s);
        String s1 = (audio.getTitle()) + substring;
        System.out.println("s1 > " + s1);
        try {
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(s1, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setContentType("audio/mpeg");


        System.out.println("realPath > " + realPath);

        try {
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(FileUtils.readFileToByteArray(file));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
