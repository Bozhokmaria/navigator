package com.solvd.navigator.services;

import java.util.*;

public class NodeService {

    private String name;

    private List<NodeService> shortestPath = new LinkedList<>();

    private Integer distance = Integer.MAX_VALUE;

    Map<NodeService, Integer> adjacentNodes = new HashMap<>();

    public void addDestination(NodeService destination, int distance) {
        adjacentNodes.put(destination, distance);
    }

    public NodeService(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<NodeService> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<NodeService> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Map<NodeService, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void setAdjacentNodes(Map<NodeService, Integer> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }

    public static NodeService getLowestDistanceNode(Set <NodeService> unsettledNodes) {
        NodeService lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (NodeService node: unsettledNodes) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }



}