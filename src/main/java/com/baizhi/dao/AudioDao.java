package com.baizhi.dao;

import com.baizhi.entity.Audio;

public interface AudioDao {
    int deleteByPrimaryKey(String id);

    int insert(Audio record);

    public void addOneAudio(Audio record);

    int insertSelective(Audio record);

    Audio selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Audio record);

    int updateByPrimaryKey(Audio record);
}