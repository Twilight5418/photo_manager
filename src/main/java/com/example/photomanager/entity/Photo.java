package com.example.photomanager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("photo")  // 对应数据库表 photo
public class Photo {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long locationId;
    private String url;
    private LocalDateTime uploadedAt;
}
