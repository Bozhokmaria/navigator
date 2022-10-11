package com.solvd.navigator.services;

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
            return "Node{" +
                    "name='" + name + '\'' +
                    '}';
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



}