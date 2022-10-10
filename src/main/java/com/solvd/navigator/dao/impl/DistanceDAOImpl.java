package com.solvd.navigator.dao.impl;

import com.solvd.navigator.connection.ConnectionPool;
import com.solvd.navigator.dao.DistanceDAO;
import com.solvd.navigator.model.City;
import com.solvd.navigator.model.Distance;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DistanceDAOImpl implements DistanceDAO {

    private static final Logger LOGGER = LogManager.getLogger(DistanceDAOImpl.class);

    private static final String GET_BY_ID = "SELECT * FROM distance WHERE id=?";

    private static final String GET_ALL = "SELECT * FROM distance";

    private static final String GET_ALL_BY_CITY_ID = "SELECT * FROM distance WHERE city1_id=? or city2_id=?";

    @Override
    public List<Distance> getAll() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(GET_ALL);
            List<Distance> distances = new ArrayList<>();

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Distance distance = new Distance();
                distance.setId(rs.getInt("id"));
                distance.setDistance(rs.getDouble("distance"));
                distance.setCity1_id(rs.getInt("city1_id"));
                distance.setCity2_id(rs.getInt("city2_id"));
                distances.add(distance);
            }
            return distances;

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            ConnectionPool.closePreparedStatement(preparedStatement);
            ConnectionPool.closeConnection(connection);
        }
        return null;

    }

    @Override
    public Distance getById(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionPool.getConnection();
            ps = connection.prepareStatement(GET_BY_ID);
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Distance distance = new Distance();
                distance.setId(rs.getInt("id"));
                distance.setDistance(rs.getDouble("distance"));
                distance.setCity1_id(rs.getInt("city1_id"));
                distance.setCity2_id(rs.getInt("city2_id"));
                return distance;
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            ConnectionPool.closePreparedStatement(ps);
            ConnectionPool.closeConnection(connection);
        }
        return null;
    }

    @Override
    public List<Distance> getAllDistancesByCityId(int cityId) {
        Connection connection = null;
        PreparedStatement ps = null;
        List<Distance> distances = new ArrayList<>();
        try {
            connection = ConnectionPool.getConnection();
            ps = connection.prepareStatement(GET_ALL_BY_CITY_ID);
            ps.setInt(1, cityId);
            ps.setInt(2, cityId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Distance distance = new Distance();
                distance.setId(rs.getInt("id"));
                distance.setDistance(rs.getDouble("distance"));
                distance.setCity1_id(rs.getInt("city1_id"));
                distance.setCity2_id(rs.getInt("city2_id"));
                distances.add(distance);
            }
            return distances;

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            ConnectionPool.closePreparedStatement(ps);
            ConnectionPool.closeConnection(connection);
        }
        return null;
    }
}
