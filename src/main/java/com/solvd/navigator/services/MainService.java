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
        // get and show all cities
        int[] citiesFromAndTo = CityService.getCityFromAndCityTo(cityNames); // [0] - from, [1] - to
        // get cityFrom and cityTo
        NodeService.Node[] nodes = new NodeService.Node[cityNames.size()];
        for(int i = 0; i < cityNames.size(); i++) {
            nodes[i] = new NodeService.Node(cityNames.get(i));
        }
        List<List<Distance>> distancesForNodes = DistanceService.getDistancesForEveryNode(cityNames);

        for(int i = 0; i < nodes.length; i++) {
            for (int j = 0; j < distancesForNodes.get(i).size(); j++) {
                if (distancesForNodes.get(i).get(j).getCity1_id() == (i + 1)) {
                    int tmpId2 = distancesForNodes.get(i).get(j).getCity2_id();
                    nodes[i].addDestination(nodes[tmpId2 - 1], distancesForNodes.get(i).get(j).getDistance());

                } else if (distancesForNodes.get(i).get(j).getCity2_id() == (i + 1)) {
                    int tmpId1 = distancesForNodes.get(i).get(j).getCity1_id();
                    nodes[i].addDestination(nodes[tmpId1 - 1], distancesForNodes.get(i).get(j).getDistance());
                }
            }
        }

        GraphService.Graph graph = new GraphService.Graph();
        for(int i = 0; i < nodes.length; i++) {
            graph.addNode(nodes[i]);
        }
        graph = GraphService.calculateShortestPathFromSource(graph, nodes[citiesFromAndTo[0]]);
        NodeService.Node[] resultPath = new NodeService.Node[nodes[citiesFromAndTo[1]].getShortestPath().size() + 1];
        for(int i = 0; i < resultPath.length - 1; i++) {
            resultPath[i] = nodes[citiesFromAndTo[1]].getShortestPath().get(i);
        }
        resultPath[resultPath.length - 1] = nodes[citiesFromAndTo[1]];

        LOGGER.info(Arrays.toString(resultPath) + " ; Distance -> " + resultPath[resultPath.length - 1].getDistance());

    }


}
