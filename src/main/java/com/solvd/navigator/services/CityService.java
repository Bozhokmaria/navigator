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

    public static List<String> getAndShowAllCitiesNames() {
        List<City> cities = CityService.cityDao.getAll();
        List<String> cityNames = new ArrayList<>();
        stringBuilder.append("The list of the available cities for your journey: \n");
        for (int i = 0; i < cities.size(); i++) {
            stringBuilder.append(cities.get(i).getName());
            cityNames.add(cities.get(i).getName());
            if (i == cities.size() - 1) {
                stringBuilder.append(". ");
            } else {
                stringBuilder.append(", \n");
            }

//            if (i != cities.size() - 1 && (i + 1) % 6 == 0) {
//                stringBuilder.append(System.lineSeparator());
//            }
        }
        LOGGER.info(stringBuilder.toString());
        return cityNames;
    }

    public static int[] getCityFromAndCityTo(List<String> cityNames) {
        LOGGER.info("Type the name of the city where you want to start your journey: ");
        int[] result = new int[2];
        String cityFrom = scanner.nextLine();
        boolean present = checkIfCityIsPresent(cityFrom);
        while (!checkIfCityIsPresent(cityFrom)) {
            LOGGER.info("Please, type your choice more correctly!");
            LOGGER.info(stringBuilder.toString());
            cityFrom = scanner.nextLine();
        }
        cityFrom = getFirstLetterCapitalizedCity(cityFrom);
        result[0] = cityNames.indexOf(cityFrom);
        LOGGER.info("Now type the name of the city where you want to go: ");
        String cityTo = scanner.nextLine();
        while (!checkIfCityIsPresent(cityTo) || cityTo.equalsIgnoreCase(cityFrom)) {
            if (cityFrom.equalsIgnoreCase(cityTo)) {
                LOGGER.info("Are you sure that you want to go from " + getFirstLetterCapitalizedCity(cityFrom) + " to " + getFirstLetterCapitalizedCity(cityTo) + "? " +
                        "0 km journey, no sense !");
            } else {
                LOGGER.info("Please, type your choice more correctly!");
            }
            LOGGER.info(stringBuilder.toString());
            LOGGER.info("Now type the name of the city where you want to go: ");
            cityTo = scanner.nextLine();
        }
        cityTo = getFirstLetterCapitalizedCity(cityTo);
        result[1] = cityNames.indexOf(cityTo);

        return result;
    }

    private static String getFirstLetterCapitalizedCity(String cityFrom) {
        cityFrom.toLowerCase();
        char[] chars = cityFrom.toCharArray();
        chars[0] = Character.toUpperCase(chars[0]);
        cityFrom = new String(chars);
        return cityFrom;
    }

    private static boolean checkIfCityIsPresent(String city) {
        List<City> cities = CityService.cityDao.getAll();
        for (int i = 0; i < cities.size(); i++) {
            if (cities.get(i).getName().equalsIgnoreCase(city))
                return true;
        }
        return false;
    }
}
