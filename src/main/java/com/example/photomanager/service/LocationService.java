package com.example.photomanager.service;


import com.example.photomanager.entity.Location;

import java.util.List;

public interface LocationService {
    List<Location> getAllLocations();//获取所有地点信息
    void createLocation(Location location);
    Location getLocationById(Long id);
    void updateLocation(Location location);
    void deleteLocation(Long id);
}
