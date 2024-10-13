package com.example.photomanager.serviceImpl;



import com.example.photomanager.entity.Location;
import com.example.photomanager.mapper.LocationMapper;
import com.example.photomanager.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationMapper locationMapper;

    @Override
    public List<Location> getAllLocations() {
        return locationMapper.selectList(null);
    }

    @Override
    public void createLocation(Location location) {
        locationMapper.insert(location);
    }

    @Override
    public Location getLocationById(Long id) {
        return locationMapper.selectById(id);
    }

    @Override
    public void updateLocation(Location location) {
        locationMapper.updateById(location);
    }

    @Override
    public void deleteLocation(Long id) {
        locationMapper.deleteById(id);
    }
}
