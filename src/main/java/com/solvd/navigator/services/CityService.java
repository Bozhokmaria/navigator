package com.solvd.navigator.services;

import com.solvd.navigator.dao.impl.CityDAOImpl;
import com.solvd.navigator.model.City;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CityService {

    private static final Logger LOGGER = LogManager.getLogger(CityService.class);
    private static final Scanner scanner = new Scanner(System.in);
    public static final CityDAOImpl cityDao = new CityDAOImpl();
    private static final StringBuilder stringBuilder = new StringBuilder();
    public CityService() {

    }

    public static List<String>  getAndShowAllCitiesNames() {
        List<City> cities = CityService.cityDao.getAll();
        List<String> cityNames = new ArrayList<>();
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
        return cityNames;
    }

    public static int[] getCityFromAndCityTo(List<String> cityNames) {
        LOGGER.info("Choose your starting city: ");
        int[] result = new int[2];
        String cityFrom = scanner.nextLine();
        while(!cityNames.contains(cityFrom)) {
            LOGGER.info("Please, make your choice more correctly!");
            LOGGER.info(stringBuilder.toString());
            cityFrom = scanner.nextLine();
        }
        result[0] = cityNames.indexOf(cityFrom);
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
            LOGGER.info("Now choose the city where you want to go: ");
            cityTo = scanner.nextLine();
        }
        result[1] = cityNames.indexOf(cityTo);

        return result;
    }
}
