package com.solvd.navigator.dao;

import com.solvd.navigator.model.City;

import java.util.List;

public interface CityDAO {
    List<City> getAll();
    City getById(int id);
}
