package com.solvd.navigator.services;

import com.solvd.navigator.model.Distance;

import java.util.*;

public class NodeService {

    public static class Node {
        private String name;

        private Double totalDistance;

        private List<Node> shortestPath = new LinkedList<>();

        private Double distance = Double.MAX_VALUE;

        Map<Node, Double> adjacentNodes = new HashMap<>();

        public void addDestination(Node destination, double distance) {
            adjacentNodes.put(destination, distance);
        }

        public Node(String name) {
            this.name = name;
        }

        public Double getTotalDistance() {
            return totalDistance;
        }

        public void setTotalDistance(Double totalDistance) {
            this.totalDistance = totalDistance;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Node> getShortestPath() {
            return shortestPath;
        }

        public void setShortestPath(List<Node> shortestPath) {
            this.shortestPath = shortestPath;
        }

        public Double getDistance() {
            return distance;
        }

        public void setDistance(Double distance) {
            this.distance = distance;
        }

        public Map<Node, Double> getAdjacentNodes() {
            return adjacentNodes;
        }

        public void setAdjacentNodes(Map<Node, Double> adjacentNodes) {
            this.adjacentNodes = adjacentNodes;
        }

        @Override
        public String toString() {
            return "'" + name + '\'';
        }
    }


    public static Node getLowestDistanceNode(Set<Node> unsettledNodes) {
        Node lowestDistanceNode = null;
        double lowestDistance = Double.MAX_VALUE;
        for (Node node: unsettledNodes) {
            double nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

    public static Node[] getNodes(List<String> cityNames) {
        NodeService.Node[] nodes = new NodeService.Node[cityNames.size()];
        for(int i = 0; i < cityNames.size(); i++) {
            nodes[i] = new NodeService.Node(cityNames.get(i));
        }
        return nodes;
    }

    public static void addAllRelatedNodesToNode(Node[] nodes, List<List<Distance>> distancesForNodes) {
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
    }

    public static Node[] getResultPath(Node[] nodes, int[] citiesFromAndTo) {
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

        return resultPath;
    }

}