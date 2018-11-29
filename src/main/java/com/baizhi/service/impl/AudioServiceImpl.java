package com.baizhi.service.impl;

import com.baizhi.dao.AudioDao;
import com.baizhi.entity.Audio;
import com.baizhi.service.AudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AudioServiceImpl implements AudioService {
    @Autowired
    private AudioDao audioDao;

    @Override
    public boolean addOneAudio(Audio audio) {
        boolean bool = false;
        try {
            audioDao.addOneAudio(audio);
            bool = true;
        } catch (Exception e) {
            e.printStackTrace();
            bool = false;
        }

        return bool;

    }
}
