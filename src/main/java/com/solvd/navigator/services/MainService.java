package com.solvd.navigator.services;

import com.solvd.navigator.model.City;
import com.solvd.navigator.model.Distance;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;

public class MainService {

    private static final Logger LOGGER = LogManager.getLogger(MainService.class);

    public static void task() {
        List<String> cityNames = CityService.getAndShowAllCitiesNames();
        int[] citiesFromAndTo = CityService.getCityFromAndCityTo(cityNames); // [0] - from, [1] - to
        NodeService.Node[] nodes = NodeService.getNodes(cityNames);
        List<List<Distance>> distancesForNodes = DistanceService.getDistancesForEveryNode(cityNames);
        NodeService.addAllRelatedNodesToNode(nodes, distancesForNodes);
        NodeService.Node[] resultPath = NodeService.getResultPath(nodes, citiesFromAndTo);
        LOGGER.info("The shortest way will be through: " + Arrays.toString(resultPath) + " ; Distance -> " + resultPath[resultPath.length - 1].getDistance());

    }


}
