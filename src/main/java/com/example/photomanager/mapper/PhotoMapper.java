package com.example.photomanager.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.photomanager.entity.Photo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PhotoMapper extends BaseMapper<Photo> {
}
