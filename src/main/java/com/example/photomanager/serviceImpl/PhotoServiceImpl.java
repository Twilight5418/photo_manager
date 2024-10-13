package com.example.photomanager.serviceImpl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.photomanager.entity.Photo;
import com.example.photomanager.mapper.PhotoMapper;
import com.example.photomanager.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoMapper photoMapper;

    @Override
    public List<Photo> getPhotosByLocationId(Long locationId) {
        return photoMapper.selectList(new QueryWrapper<Photo>().eq("location_id", locationId));
    }

    @Override
    public void savePhoto(Photo photo) {
        photoMapper.insert(photo);
    }

    @Override
    public void deletePhoto(Long id) {
        photoMapper.deleteById(id);
    }
}
