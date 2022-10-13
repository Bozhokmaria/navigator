package com.solvd.navigator.services;

import com.solvd.navigator.dao.impl.DistanceDAOImpl;
import com.solvd.navigator.model.City;
import com.solvd.navigator.model.Distance;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DistanceService {

    private static final Logger LOGGER = LogManager.getLogger(DistanceService.class);
    private static final Scanner scanner = new Scanner(System.in);
    public static final DistanceDAOImpl distanceDao = new DistanceDAOImpl();

    public DistanceService() {

    }

    public static List<List<Distance>> getDistancesForEveryNode(List<String> cityNames) {
            List<List<Distance>> result = new ArrayList<>();
            for(int i = 0; i < cityNames.size(); i++) {
                result.add(distanceDao.getAllDistancesByCityId(CityService.cityDao.getIdByName(cityNames.get(i))));
            }

            return result;
    }
}
