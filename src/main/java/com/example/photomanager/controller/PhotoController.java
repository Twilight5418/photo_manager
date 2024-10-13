package com.example.photomanager.controller;

import com.example.photomanager.entity.Photo;
import com.example.photomanager.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/photos")
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    // 上传照片
    @PostMapping("/upload")
    public void uploadPhoto(@RequestParam("locationId") Long locationId,
                            @RequestParam("file") MultipartFile file) {
        // 上传文件并保存文件路径
        String filePath = saveFile(file);
        Photo photo = new Photo();
        photo.setLocationId(locationId);
        photo.setUrl(filePath);
        photoService.savePhoto(photo);
    }

    // 根据地点ID获取照片
    @GetMapping("/{locationId}")
    public List<Photo> getPhotosByLocationId(@PathVariable Long locationId) {
        return photoService.getPhotosByLocationId(locationId);
    }

    // 删除照片
    @DeleteMapping("/{id}")
    public void deletePhoto(@PathVariable Long id) {
        photoService.deletePhoto(id);
    }

    // 模拟文件保存方法，实际需要根据你项目需求进行改造
    private String saveFile(MultipartFile file) {
        // 保存文件的逻辑，这里仅为示例，返回文件的路径
        return "/path/to/save/" + file.getOriginalFilename();
    }
}
