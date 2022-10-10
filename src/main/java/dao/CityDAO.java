package dao;

import model.City;

import java.util.List;

public interface CityDAO {
    List<City> getAll();
    City getById(int id);
}
