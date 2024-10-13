package com.example.photomanager.controller;

import com.example.photomanager.entity.Location;
import com.example.photomanager.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    @Autowired
    private LocationService locationService;

    // 获取所有地点
    @GetMapping
    public List<Location> getAllLocations() {
        return locationService.getAllLocations();
    }

    // 创建新地点
    @PostMapping
    public void createLocation(@RequestBody Location location) {
        locationService.createLocation(location);
    }

    // 根据ID获取地点信息
    @GetMapping("/{id}")
    public Location getLocationById(@PathVariable Long id) {
        return locationService.getLocationById(id);
    }

    // 更新地点信息
    @PutMapping("/{id}")
    public void updateLocation(@PathVariable Long id, @RequestBody Location location) {
        location.setId(id);
        locationService.updateLocation(location);
    }

    // 删除地点
    @DeleteMapping("/{id}")
    public void deleteLocation(@PathVariable Long id) {
        locationService.deleteLocation(id);
    }
}
