package com.solvd.navigator.dao.impl;

import com.solvd.navigator.connection.ConnectionPool;
import com.solvd.navigator.dao.CityDAO;
import com.solvd.navigator.model.City;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityDAOImpl implements CityDAO {

    private static final Logger LOGGER = LogManager.getLogger(CityDAOImpl.class);

    private static final String GET_BY_ID = "SELECT * FROM city WHERE id=?";

    private static final String GET_ALL = "SELECT * FROM city";


    @Override
    public List<City> getAll() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(GET_ALL);
            List<City> cities = new ArrayList<>();

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                City city = new City();
                city.setId(rs.getInt("id"));
                city.setName(rs.getString("name"));
                cities.add(city);
            }
            return cities;

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            ConnectionPool.closePreparedStatement(preparedStatement);
            ConnectionPool.closeConnection(connection);
        }
        return null;
    }

    @Override
    public City getById(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionPool.getConnection();
            ps = connection.prepareStatement(GET_BY_ID);
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                City city = new City();
                city.setId(rs.getInt("id"));
                city.setName(rs.getString("name"));
                return city;
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            ConnectionPool.closePreparedStatement(ps);
            ConnectionPool.closeConnection(connection);
        }
        return null;
    }
}
