package com.solvd.navigator.services;

import com.solvd.navigator.dao.impl.CityDAOImpl;
import com.solvd.navigator.dao.impl.DistanceDAOImpl;
import com.solvd.navigator.model.City;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DistanceService {

    private static final Logger LOGGER = LogManager.getLogger(DistanceService.class);
    private static final Scanner scanner = new Scanner(System.in);
    private static final CityDAOImpl cityDao = new CityDAOImpl();
    private static final DistanceDAOImpl distanceDao = new DistanceDAOImpl();

    public DistanceService() {

    }

    public static void task() {
        List<City> cities = cityDao.getAll();
        List<String> cityNames = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < cities.size(); i++) {
            stringBuilder.append(cities.get(i).getName());
            cityNames.add(cities.get(i).getName());
            if (i == cities.size() - 1) {
                stringBuilder.append(". ");
            } else {
                stringBuilder.append(", ");
            }

            if (i != cities.size() - 1 && (i + 1) % 6 == 0) {
                stringBuilder.append(System.lineSeparator());
            }
        }
        LOGGER.info(stringBuilder.toString());
        // get and show all cities
        LOGGER.info("Choose your starting city: ");
        String cityFrom = scanner.nextLine();
        while(!cityNames.contains(cityFrom)) {
                LOGGER.info("Please, make your choice more correctly!");
                LOGGER.info(stringBuilder.toString());
                cityFrom = scanner.nextLine();
        }
        LOGGER.info("Now choose the city where you want to go: ");
        String cityTo = scanner.nextLine();
        while(!cityNames.contains(cityTo) || cityTo.equals(cityFrom)) {
            if (cityFrom.equals(cityTo)) {
                LOGGER.info("Are you sure that you want to go from " + cityFrom + " to " + cityTo + "? " +
                        "I do not think so!");
            } else {
                LOGGER.info("Please, make your choice more correctly!");
            }
            LOGGER.info(stringBuilder.toString());
            cityTo = scanner.nextLine();
        }
        // get cityFrom and cityTo




    }
}
