package com.solvd.navigator.dao;

import com.solvd.navigator.model.Distance;

import java.util.List;

public interface DistanceDAO {
    List<Distance> getAll();
    Distance getById(int id);
    List<Distance> getAllDistancesByCityId(int cityId);
}
