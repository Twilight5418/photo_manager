package com.example.photomanager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("location")  // 对应数据库表 location
public class Location {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Double latitude;
    private Double longitude;
    private String description;
    private LocalDateTime createdAt;    // 创建时间
}
