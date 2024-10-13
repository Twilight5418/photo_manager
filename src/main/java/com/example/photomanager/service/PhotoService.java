package com.example.photomanager.service;

import com.example.photomanager.entity.Photo;

import java.util.List;

public interface PhotoService {
    List<Photo> getPhotosByLocationId(Long locationId);
    void savePhoto(Photo photo);
    void deletePhoto(Long id);
}
