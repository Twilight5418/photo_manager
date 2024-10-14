package com.example.photomanager.controller;

import com.example.photomanager.entity.Photo;
import com.example.photomanager.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/photos")
public class PhotoController {

    @Value("${file.upload-dir}")  // 从 application.yml 中读取文件路径
    private String uploadDir;

    @Autowired
    private PhotoService photoService;

    // 上传照片
    @PostMapping("/upload")
    public ResponseEntity<String> uploadPhoto(@RequestParam("locationId") Long locationId,
                                              @RequestParam("file") MultipartFile file) {
        try {
            // 上传文件并保存文件路径
            String filePath = saveFile(file);
            if (filePath == null) {
                return new ResponseEntity<>("File saving failed", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            // 保存照片记录到数据库
            Photo photo = new Photo();
            photo.setLocationId(locationId);
            photo.setUrl(filePath);
            photoService.savePhoto(photo);

            return new ResponseEntity<>("File uploaded successfully: " + filePath, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to upload file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 根据地点ID获取照片
    @GetMapping("/{locationId}")
    public ResponseEntity<List<Photo>> getPhotosByLocationId(@PathVariable Long locationId) {
        List<Photo> photos = photoService.getPhotosByLocationId(locationId);
        if (photos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(photos, HttpStatus.OK);
    }

    // 删除照片
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePhoto(@PathVariable Long id) {
        try {
            photoService.deletePhoto(id);
            return new ResponseEntity<>("Photo deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete photo", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 文件保存方法
    private String saveFile(MultipartFile file) {
        try {
            // 使用项目目录的绝对路径
            String absoluteUploadDir = new File(uploadDir).getAbsolutePath();

            // 检查目录是否存在，不存在则创建
            File dir = new File(absoluteUploadDir);
            if (!dir.exists()) {
                if (!dir.mkdirs()) {
                    System.out.println("Failed to create directory!");
                    return null;
                }
            }

            // 获取文件的完整路径
            String filePath = absoluteUploadDir + File.separator + file.getOriginalFilename();

            // 保存文件
            File destinationFile = new File(filePath);
            file.transferTo(destinationFile);

            return filePath;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
